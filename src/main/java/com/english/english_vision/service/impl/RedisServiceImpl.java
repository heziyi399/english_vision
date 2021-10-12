package com.english.english_vision.service.impl;

import com.english.english_vision.service.RedisService;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @Author
 * @Description
 * @Date
 **/
@Service
public class RedisServiceImpl implements RedisService {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Override
    public void set(String key, Object value, long time) {
redisTemplate.opsForValue().set(key,value,time, TimeUnit.SECONDS);
    }

    @Override
    public void set(String key, Object value) {
redisTemplate.opsForValue().set(key,value);
    }

    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public Boolean del(String key) {
        return redisTemplate.delete(key);
    }

    @Override
    public Long del(List<String> keys) {
        return redisTemplate.delete(keys);
    }

    @Override
    public Boolean expire(String key, long time) {
        return redisTemplate.expire(key,time,TimeUnit.SECONDS);
    }

    @Override
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }

    @Override
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public Long incr(String key, long delta) {
        return redisTemplate.opsForValue().increment(key,delta);//增加
    }

    @Override
    public Long decr(String key, long delta) {
        return redisTemplate.opsForValue().decrement(key,delta);
    }

    @Override
    public Object hGet(String key, String hashKey) {
        return redisTemplate.opsForHash().get(key,hashKey);
    }

    @Override
    public Boolean hSet(String key, String hashKey, Object value, long time) {
        redisTemplate.opsForHash().put(key,hashKey,value);
        return expire(key,time);//前面已经有自定义的expire函数
    }

    @Override
    public void hSet(String key, String hashKey, Object value) {
redisTemplate.opsForHash().put(key,hashKey,value);
    }

    @Override
    public Map hGetAll(String key) {
        return redisTemplate.opsForHash().entries(key);//获得键值对
    }

    @Override
    public Boolean hSetAll(String key, Map<String, Object> map, long time) {
      redisTemplate.opsForHash().putAll(key,map);
      return expire(key,time);
    }

    @Override
    public void hSetAll(String key, Map<String, ?> map) {
redisTemplate.opsForHash().putAll(key,map);
    }

    @Override
    public void hDel(String key, Object... hashKey) {
redisTemplate.opsForHash().delete(key, hashKey);
    }

    @Override
    public Boolean hHasKey(String key, String hashKey) {
        return redisTemplate.opsForHash().hasKey(key,hashKey);
    }

    @Override
    public Long hIncr(String key, String hashKey, Long delta) {
        return redisTemplate.opsForHash().increment(key,hashKey,delta);
    }

    @Override
    public Long hDecr(String key, String hashKey, Long delta) {
        return redisTemplate.opsForHash().increment(key,hashKey,-delta);
    }

    @Override
    public Double zIncr(String key, Object value, Double score) {
        return redisTemplate.opsForZSet().incrementScore(key,value,score);
    }

    @Override
    public Double zDecr(String key, Object value, Double score) {
        return redisTemplate.opsForZSet().incrementScore(key,value,-score);
    }

    @Override
    public Map<Object, Double> zReverseRangeWithScore(String key, long start, long end) {
        return redisTemplate.opsForZSet().reverseRangeWithScores(key,start,end).stream().collect(Collectors.toMap(ZSetOperations.TypedTuple::getValue,
                ZSetOperations.TypedTuple::getScore));
        //.range(K key, long start, long end)	Set<V>	获取变量指定区间的元素。
    }
    //reverseRangeWithScores(K key, long start, long end); 通过索引区间返回有序集合成指定区间内的成员对象
//.rangeByScoreWithScores(K key, double min, double max)
// 返回Set<ZSetOperations.TypedTuple<V>>	获取RedisZSetCommands.Tuples的区间值通过分值。
    @Override
    public Double zScore(String key, Object value) {
        return redisTemplate.opsForZSet().score(key,value);//获取元素的分值。
    }

    @Override
    public Map<Object, Double> zAllScore(String key) {
        return Objects.requireNonNull(redisTemplate.opsForZSet().rangeWithScores(key,0,-1))
                .stream().collect(Collectors.toMap(ZSetOperations.TypedTuple::getValue,ZSetOperations.TypedTuple::getScore));
    }

    @Override
    public Set<Object> sMembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    @Override
    public Long sAdd(String key, Object... values) {
        return redisTemplate.opsForSet().add(key,values);
    }

    @Override
    public Long sAddExpire(String key, long time, Object... values) {
        Long count = redisTemplate.opsForSet().add(key, values);
        expire(key, time);
        return count;
    }

    @Override
    public Boolean sIsMember(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key,value);//  //.opsForSet().isMember(key,value)判断key中是否存在value值
    }

    @Override
    public Long sSize(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    @Override
    public Long sRemove(String key, Object... values) {
        return redisTemplate.opsForSet().remove(key,values);
    }

    @Override
    public List<Object> lRange(String key, long start, long end) {
        return redisTemplate.opsForList().range(key,start,end);
    }

    @Override
    public Long lSize(String key) {
        return redisTemplate.opsForList().size(key);
    }

    @Override
    public Object lIndex(String key, long index) {
        return redisTemplate.opsForList().index(key,index);
    }

    @Override
    public Long lPush(String key, Object value) {
        return redisTemplate.opsForList().rightPush(key,value);
    }

    @Override
    public Long lPush(String key, Object value, long time) {
        Long index = redisTemplate.opsForList().rightPush(key, value);
        expire(key, time);
        return index;
    }

    @Override
    public Long lPushAll(String key, Object... values) {
    return redisTemplate.opsForList().rightPushAll(key,values);
    }

    @Override
    public Long lPushAll(String key, Long time, Object... values) {
        return redisTemplate.opsForList().leftPushAll(key,values);
    }

    @Override
    public Long lRemove(String key, long count, Object value) {
        return redisTemplate.opsForList().remove(key, count, value);
    }
}
