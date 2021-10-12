package com.english.english_vision.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.english.english_vision.Base.PageResult;
import com.english.english_vision.Base.ResponseResult;
import com.english.english_vision.enums.ResponseEnum;
import com.english.english_vision.pojo.Comment;
import com.english.english_vision.pojo.Diary;
import com.english.english_vision.pojo.Song;
import com.english.english_vision.service.ISongService;
import com.english.english_vision.util.BeanUtils;
import com.english.english_vision.vo.SongVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;


import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hehe
 * @since 2021-10-01
 */
@Controller
@RequestMapping("/song")
@Api(tags = {"儿童英文歌曲操作接口，用户端"})
public class SongUserController {
    @Autowired
    private ISongService songService;

    @ResponseBody
    @Cacheable(value = "song-key")//cacheable的属性中cacheNames和value:用于指定缓存组件的名字 key:缓存数据使用的key 默认是fang%fa参数的值
  @ApiOperation(value="用户查看系统歌单")
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public ResponseResult<PageResult<SongVo>> list(
          @ApiParam(name="pageNum",value="当前页",required=true)
          @RequestParam("pageNum")Integer pageNum,
          @ApiParam(name="pageSize",value="每页的记录条数",required=true)
          @RequestParam("pageSize")Integer pageSize
  )
  {
List<Song> songs = songService.list();

      List<SongVo> songVos = songs.stream().map(e->{
    SongVo songVo = new SongVo();
    BeanUtils.copyBeanProp(songVo,e);
    return songVo;
}).collect(Collectors.toList());
if(songs == null) return ResponseResult.success(new PageResult<>());
int numbers = songService.count();//查询总记录数
PageResult pageResult = new PageResult<>(songVos,numbers);
return ResponseResult.success(pageResult);

  }
    @ResponseBody
    @ApiOperation(value="用户按歌名模糊查询歌曲")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public ResponseResult querySong(
            @ApiParam(name="name",value="歌名",required=true)
            @RequestParam("name")String name)
    {

      //  List<Song> songs = songService.listbyName(new LambdaQueryWrapper<Song>().eq(Song::getName,name));
        List<Song> songs = songService.listbyName(name);
        if(songs == null) return ResponseResult.error(ResponseEnum.DIARY_NOT_EXIST);
        List<SongVo> songVos = songs.stream().map(e->{
            SongVo songVo = new SongVo();
            BeanUtils.copyBeanProp(songVo,e);
            return songVo;
        }).collect(Collectors.toList());
        int numbers = songVos.size();
                PageResult pageResult = new PageResult<>(songVos,numbers);
        return ResponseResult.success(pageResult);
    }
    @ResponseBody
    @ApiOperation(value = "返回列表中指定某一歌曲的歌曲详情")
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ResponseResult songOfId(  @ApiParam(name="id",value="歌曲id",required=true)
                                 @RequestParam("id")String id){
        Song song = songService.getOne(new LambdaQueryWrapper<Song>().eq(Song::getId,id));
        SongVo songVo = new SongVo();
        BeanUtils.copyBeanProp(songVo,song);
        if(Objects.nonNull(songVo))
        return ResponseResult.success(songVo);
        else return ResponseResult.success(new SongVo());
    }
    @ResponseBody
    @ApiOperation(value = "歌曲点赞")
    @PostMapping("/{songId}/like")
    public ResponseResult<?> saveSongLike(@PathVariable("songId") Integer songId) {
        songService.saveSongLike(songId);
        return ResponseResult.success();
    }
    @ResponseBody
    @ApiOperation(value = "页面上点击播放按钮，开始放出音乐，用户的歌曲播放量加一")
    @PostMapping("/play/{songId}")
    public ResponseResult<?> playSong(@PathVariable("songId") Integer songId)
    {
        songService.playSong(songId);
        return ResponseResult.success();
    }
    @ResponseBody
    @ApiOperation(value = "用户查看自己播放列表的歌曲(自己播放过的歌曲）")
            @GetMapping("/usersong")
            public ResponseResult<PageInfo>getUserSong( @ApiParam(name="pageNum",value="当前页",required=true)
                                                            @RequestParam("pageNum")Integer pageNum,
                                                        @ApiParam(name="pageSize",value="每页的记录条数",required=true)
                                                            @RequestParam("pageSize")Integer pageSize)
    {
        List<SongVo> mySong = songService.getUserSong();
        PageInfo pageInfo = new PageInfo(mySong);
        pageInfo.setPageNum(pageNum);
        pageInfo.setPageSize(pageSize);
        pageInfo.setList(mySong);
        return ResponseResult.success(pageInfo);

    }

    @GetMapping("/{id}")
    public Song getById(@PathVariable("id") int id)
    {
        return songService.getById(id);
    }
    @ResponseBody
    @GetMapping("/cache/{id}")
    public Song getByCache(@PathVariable("id") int id)
    {
       Song song=songService.getByCache(id);
       String str = JSONObject.toJSONString(song);
       Song jsonSong = JSONObject.parseObject(str,Song.class);
       return jsonSong;
    }
}
