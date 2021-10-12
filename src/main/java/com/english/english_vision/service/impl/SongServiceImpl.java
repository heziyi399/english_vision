package com.english.english_vision.service.impl;
import static com.english.english_vision.Constant.RedisPrefixConst.SONG_LIKE_COUNT;//一定要加static 不然无法导入
import static com.english.english_vision.Constant.RedisPrefixConst.SONG_PLAY_COUNT;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.english.english_vision.pojo.Song;
import com.english.english_vision.mapper.SongMapper;
import com.english.english_vision.pojo.User;
import com.english.english_vision.service.ISongService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.english.english_vision.service.RedisService;
import com.english.english_vision.util.BeanUtils;
import com.english.english_vision.util.StringUtils;
import com.english.english_vision.vo.SongVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hehe
 * @since 2021-10-01
 */
@Service
public class SongServiceImpl extends ServiceImpl<SongMapper, Song> implements ISongService {
    @Autowired
    private RedisService redisService;
    @Autowired
    private SongMapper songMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public void saveOrUpdateSong(SongVo songVo) {
        Song song =songMapper.selectOne(new LambdaQueryWrapper<Song>().eq(Song::getName,songVo.getName()));
        if(Objects.nonNull(song)) System.out.println("歌曲已存在");
Song song1 = new Song();
        BeanUtils.copyBeanProp(song1,songVo);
        song1.setCreateTime(new Date());
        this.saveOrUpdate(song1);
        //注意这里
    }

    @Override
    public void saveSongLike(Integer songId) {
        User principal = (User) SecurityUtils.getSubject().getPrincipal();
        //一个用户对应一个赞，每个歌曲可以有来自多个用户的赞，每次点赞时都数量加一
        String commentLikeKey = SONG_LIKE_COUNT + principal.getId();//导入这个常量时一定记得import static
        if (redisService.sIsMember(commentLikeKey, songId)) {
            // 点过赞则删除评论id
            redisService.sRemove(commentLikeKey, songId);
            // 评论点赞量-1
            redisService.hDecr(SONG_LIKE_COUNT,songId.toString(), -1L);
        } else {
            // 未点赞则增加评论id
            redisService.sAdd(commentLikeKey, songId);
            // 评论点赞量+1
            redisService.hIncr(SONG_LIKE_COUNT, songId.toString(), 1L);
        }
    }

    @Override
    public List<Song> listbyName(String name) {
        List<Song> songs = null;
        String key = "songlist:"+name ;
        ValueOperations<String, List<Song>> operations = redisTemplate.opsForValue();

        //判断redis中是否有键为key的缓存
        boolean hasKey = redisTemplate.hasKey(key);
        if(hasKey) {
            songs = operations.get(key);
            return songs;
        }
        else {
songs = songMapper.listbyName(name);
operations.set(key,songs,30, TimeUnit.DAYS);
        }
        System.out.println("mapper查询");
        return songMapper.listbyName(name);
    }

    @Override
    public void playSong(Integer songId) {
        User principal = (User) SecurityUtils.getSubject().getPrincipal();
        //每个用户对应某一首歌曲的播放量增加
        Object o = redisService.hGet("user:" + principal.getId(), SONG_PLAY_COUNT+":"+songId);//如果不存在此值则返回null
        if(Objects.isNull(o))
            redisService.hSet("user:" + principal.getId(), SONG_PLAY_COUNT+":"+songId,1L);
        else
            redisService.hIncr("user:" + principal.getId(), SONG_PLAY_COUNT+":"+songId,1L);

    }

    @Override
    public List<SongVo> getUserSong() {
        User principal = (User) SecurityUtils.getSubject().getPrincipal();
        // 查询redis的评论点赞数据
        Map<String, Object> likeCountMap = redisService.hGetAll("user:" + principal.getId());

       Set<SongVo> songVos = new TreeSet<>();//播放量从高到低来排序
        for(Map.Entry entry:likeCountMap.entrySet())
        {
            String key = (String) entry.getKey();
            String songid = key.split(":")[1];
            Song song = this.getOne(new LambdaQueryWrapper<Song>().eq(Song::getId,songid));
            SongVo  songVo= new SongVo();
            BeanUtils.copyBeanProp(songVo,song);
            Integer count = (Integer) entry.getValue();
            songVo.setPlayCount(count);
            songVos.add(songVo);//根据用户播放过的歌曲查找歌曲id，添加到链表中
        }
        songVos.forEach(System.out::println);
        List<SongVo> mysong = new LinkedList<>(songVos);
        return mysong;
    }

    @Override
  //  @Cacheable(value = "song",key= "#id")
    public Song getByCache(int id) {
        String key = "song"+id+":";
        ValueOperations<String, Song> operations = redisTemplate.opsForValue();
Song song = null;
        //判断redis中是否有键为key的缓存
        boolean hasKey = redisTemplate.hasKey(key);
        if(hasKey) {
            song = operations.get(key);
            return song;
        }
        else {
            song = songMapper.selectSongById(id);
            operations.set(key,song,30, TimeUnit.DAYS);
            return song;
        }
    }
}
