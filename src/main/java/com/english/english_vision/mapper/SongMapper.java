package com.english.english_vision.mapper;

import com.english.english_vision.pojo.Song;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hehe
 * @since 2021-10-01
 */
@Mapper
public interface SongMapper extends BaseMapper<Song> {


    List<Song> listbyName(String name);
@Select("select * from t_song where id = #{id}")
    Song selectSongById(int id);
}
