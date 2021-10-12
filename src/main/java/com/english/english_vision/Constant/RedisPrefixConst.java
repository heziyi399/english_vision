package com.english.english_vision.Constant;

import lombok.Data;

/**
 * @Author
 * @Description 用于常量
 * @Date
 **/

public class RedisPrefixConst {
    /**
     * 验证码过期时间
     */
    public static final long CODE_EXPIRE_TIME = 15 * 60;

    /**
     * 验证码
     */
    public static final String USER_CODE_KEY = "code:";

    /**
     * 博客浏览量
     */
    public static final String BLOG_VIEWS_COUNT = "blog_views_count";
    private static final String USER_NAME = "user:";
    private static final String SONG_NAME = "song:name:";
    public static final String SONG_LIKE_COUNT = "song_likes_count";
    public static final String SONG_PLAY_COUNT = "song_plays_count";//播放量
}
