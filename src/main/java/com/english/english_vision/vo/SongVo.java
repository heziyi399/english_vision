package com.english.english_vision.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author
 * @Description
 * @Date
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongVo implements Serializable,Comparable<Object> {//重写了comparable的方法用于在treeset中加入元素按照自定义的顺序排序
    @ApiModelProperty(name = "id", value = "歌曲id", dataType = "Integer")
    private Integer id;
    @ApiModelProperty(name = "name", value = "歌曲名字", dataType = "String")
    private String name;
    @ApiModelProperty(name = "introduction", value = "歌曲介绍", dataType = "String")
    private String introduction;

    @ApiModelProperty(name = "pic", value = "歌曲封面", dataType = "String")
    private String pic;
    @ApiModelProperty(name = "lyric", value = "歌曲歌词", dataType = "String")
    private String lyric;
    @ApiModelProperty(name = "playCount", value = "用户的播放次数，默认为0", dataType = "Integer")
private Integer playCount = 0;

    @Override
    public String toString() {
        return "SongVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", introduction='" + introduction + '\'' +
                ", pic='" + pic + '\'' +
                ", lyric='" + lyric + '\'' +
",playCount'"+playCount+'\''+
                '}';
    }

    @Override
    public int compareTo(Object o) {
        SongVo songVo = (SongVo) o;
        return songVo.playCount - this.playCount;
    }
    @Override
    public int hashCode() {
        StringBuilder bd = new StringBuilder();
        bd.append(name);
        bd.append(introduction);bd.append(pic);bd.append(lyric);
        char[]charayy = bd.toString().toCharArray();
        int hash = 0;
        for(char ch:charayy)
            hash = hash*131+ch;
        return hash;
    }
    @Override
    public boolean equals(Object ob)
    {
        SongVo vo = (SongVo) ob;
        if(vo.getName() == this.getName()&& vo.getId() == this.getId()) return true;
        else return false;
    }
}
