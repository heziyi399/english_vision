package com.english.english_vision.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.english.english_vision.pojo.Song;
import com.baomidou.mybatisplus.extension.service.IService;
import com.english.english_vision.vo.SongVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hehe
 * @since 2021-10-01
 */
public interface ISongService extends IService<Song> {
   void saveOrUpdateSong(SongVo songVo);

   void saveSongLike(Integer songId);

    List<Song> listbyName(String name);

    void playSong(Integer songId);

    List<SongVo> getUserSong();
    Song getByCache(int id);
}
