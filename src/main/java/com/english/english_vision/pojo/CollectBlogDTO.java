package com.english.english_vision.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author
 * @Description 被收藏的帖子
 * @Date
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CollectBlogDTO {
    private String blogType;
    private Integer blogId;
    private int num;//帖子被收藏的次数
}
