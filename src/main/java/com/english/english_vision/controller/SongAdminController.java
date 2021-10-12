package com.english.english_vision.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.english.english_vision.Base.ResponseResult;
import com.english.english_vision.enums.BusinessType;
import com.english.english_vision.enums.ResponseEnum;
import com.english.english_vision.pojo.Song;
import com.english.english_vision.service.ISongService;
import com.english.english_vision.util.BeanUtils;
import com.english.english_vision.util.annotation.Log;
import com.english.english_vision.vo.SongVo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @Author
 * @Description 歌曲管理接口
 * @Date
 **/
@RestController
@RequestMapping("/song/admin")
@Api(tags = {"儿童英文歌曲操作接口，管理员端"})
public class SongAdminController {
@Autowired
private ISongService songService;
    //    给歌单添加歌曲
    @Log(title = "上传歌曲",businessType = BusinessType.INSERT)
    @ApiOperation(value="管理员上传歌曲")
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseResult addListSong(@RequestBody SongVo songVo,@RequestParam("file") MultipartFile mpfile){
        if(songVo == null ||  songVo.getName() == null) return ResponseResult.error(ResponseEnum.OPERATE_ERROR);
        Song song = new Song();
        String fileName = mpfile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "song";
        File file1 = new File(filePath);
        if (!file1.exists()){
            file1.mkdir();
        }
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        BeanUtils.copyBeanProp(song,songVo);
        String storeUrlPath = "/song/"+fileName;//存储在数据库中的文件路径
        try {
            mpfile.transferTo(dest);//内置的transferto方法将文件转移到目标路径
            song.setUrl(storeUrlPath);
            song.setCreateTime(new Date());
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        return ResponseResult.success(song);
    }
    @Log(title = "删除歌曲",businessType = BusinessType.DELETE)
    @ApiOperation(value="管理员删除歌曲")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="歌曲id"),
    })
    @DeleteMapping (value = "/delete")
    public ResponseResult deleteListSong(
        //    @RequestParam("id[]")
        @RequestParam(value = "id" ,required=false)
                    List<Integer> id){
        boolean flag = false;
        for(int i:id)
        {
            if(songService.list(new LambdaQueryWrapper<Song>().eq(Song::getId,i))==null) flag = true;
        }
        songService.removeByIds(id);//批量删除用这个方法而不能用remove方法
//        id.stream().forEach(e->{
//
//            songService.remove(new LambdaQueryWrapper<Song>().eq(Song::getId,e));
//        });
        if(flag) return ResponseResult.error(ResponseEnum.ERROR);
return ResponseResult.success(songService.list());
    }
    @Log(title = "修改歌曲",businessType = BusinessType.UPDATE)
    @ApiOperation(value="管理员修改歌曲信息")
    @PostMapping (value = "/update")
    public ResponseResult updateListSong(@RequestBody SongVo songVo){
     songService.saveOrUpdateSong(songVo);
        return ResponseResult.success(songVo);
    }
}
