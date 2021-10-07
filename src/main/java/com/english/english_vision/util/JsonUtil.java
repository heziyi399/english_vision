package com.english.english_vision.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author
 * @Description
 * @Date
 **/
public class JsonUtil {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    static {
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> String toJsonStr(T o) {
        try {
            return MAPPER.writeValueAsString(o);//json转化为string
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public static <T> T toJsonObject(String json, Class<T> valueType) {
        try {
            return MAPPER.<T>readValue(json, valueType);//用于反序列化，将json string字符串转为对象
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }


    public static <T> List<T> toJsonListObject(String json, Class<T> valueType) {
        try {
            JavaType getCollectionType = MAPPER.getTypeFactory().constructParametricType(List.class, valueType);
            List<T> list = MAPPER.readValue(json, getCollectionType);
            return list;
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public static <T> T toJsonObject(InputStream stream, Class<T> valueType) {
        try {
            T object = MAPPER.<T>readValue(stream, valueType);
            return object;
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
