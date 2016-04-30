package com.shike.cart.redis.service;

import com.shike.cart.redis.exception.RedisException;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by shike on 16/4/28.
 */
public interface RedisService {
    /*有序集合,删除*/
    Long zrem(String key, String[] member) throws RedisException;
    /*有序集合,增加*/
    Long zadd(String key, double score, String member) throws RedisException;
    /*有序集合,获取*/
    Set<String> zrange(String key, long start, long end) throws RedisException;
    /*有序集合,统计*/
    Long zcard(String key) throws RedisException;
    /*有序集合, 获取指定成员score*/
    Double zscore(String key, String menber) throws RedisException;
    /*散列, 设置*/
    String hmset(String key, Map<String, String> map) throws RedisException;
    /*散列, 获取*/
    List<String> hmget(String key, String [] fields) throws RedisException;
    /*散列, 删除key里面键值对*/
    Long hdel(String key, String [] fields) throws RedisException;
    /*散列, 包含的键值对个数*/
    Long hlen(String key) throws RedisException;
    /*散列, 判断给定键值对是否存在*/
    Boolean hexists(String key, String field) throws RedisException;
    /*散列,获取给定键的所有键值对*/
    Map<String, String> hgetAll(String key) throws RedisException;
}
