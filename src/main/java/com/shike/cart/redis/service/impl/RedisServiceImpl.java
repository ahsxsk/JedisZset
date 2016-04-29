package com.shike.cart.redis.service.impl;

import com.shike.cart.redis.exception.RedisException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.*;

/**
 * Created by shike on 16/4/28.
 */
@Service("redisService")
public class RedisServiceImpl {
    /*默认过期时间, 7天*/
    public static final int defaultExpire = 604800;
    @Autowired
    @Qualifier("shardedJedisPool")
    private ShardedJedisPool shardedJedisPool;

    public RedisServiceImpl() {}

    /**
     * 删除有序集合中部分元素
     * @param key
     * @param members
     * @return num
     * @throws RedisException
     */
    public Long zrem(String key, String [] members) throws RedisException {
        ShardedJedis jedis = this.getRedisPool();

        Long num = 0L;
        try {
            num = jedis.zrem(key.trim(), members);
        } catch (Exception e) {
            throw new RedisException(e);
        } finally {
            if (jedis != null) {
                this.shardedJedisPool.returnResource(jedis);
            }
        }
        return num;
    }

    /**
     * 向有序集合中增加元素
     * @param key
     * @param score
     * @param member
     * @return num
     * @throws RedisException
     */
    public Long zadd(String key, double score, String member) throws RedisException {
        ShardedJedis jedis = this.getRedisPool();
        Long num = 0L;
        try {
            num = jedis.zadd(key.trim(), score, member);
        } catch (Exception e) {
            throw new RedisException(e);
        } finally {
            if (jedis != null) {
                this.shardedJedisPool.returnResource(jedis);
            }
        }
        return num;
    }

    /**
     * 统计有序集合key中的元素数量
     * @param key
     * @return num
     * @throws RedisException
     */
    public Long zcard(String key) throws RedisException {
        ShardedJedis jedis = this.getRedisPool();
        Long num = 0L;
        try {
            num = jedis.zcard(key.trim());
        } catch (Exception e) {
            throw new RedisException(e);
        } finally {
            if (jedis != null) {
                this.shardedJedisPool.returnResource(jedis);
            }
        }
        return num;
    }

    public Set<String> zrange(String key, long start, long end) throws RedisException {
        ShardedJedis jedis = this.getRedisPool();
        Set<String> result = new HashSet<String>();
        try {
            result = jedis.zrange(key.trim(), start, end);
        } catch (Exception e) {
            throw new RedisException(e);
        } finally {
            if (jedis != null) {
                this.shardedJedisPool.returnResource(jedis);
            }
        }
        return result;
    }

    /**
     * 设置散列
     * @param key
     * @param map
     * @return  OK or Exception
     * @throws RedisException
     */
    public String hmset(String key, Map<String, String> map) throws RedisException {
        ShardedJedis jedis = this.getRedisPool();
        String result = "";
        try {
            result = jedis.hmset(key.trim(), map);
        } catch (Exception e) {
            throw new RedisException(e);
        } finally {
            if (jedis != null) {
                this.shardedJedisPool.returnResource(jedis);
            }
        }
        return result;
    }

    /**
     * 获取散列指定键的值
     * @param key
     * @param fields
     * @return Multi Bulk Reply specifically a list of all the values, in the same order of the request.
     * @throws RedisException
     */
    public List<String> hmget(String key, String [] fields) throws RedisException {
        ShardedJedis jedis = this.getRedisPool();
        List<String> result = new ArrayList<String>();
        try {
            result = jedis.hmget(key.trim(), fields);
        } catch (Exception e) {
            throw new RedisException(e);
        } finally {
            if (jedis != null) {
                this.shardedJedisPool.returnResource(jedis);
            }
        }
        return result;
    }

    /**
     * 删除散列指定键值对
     * @param key
     * @param fields
     * @return If the field was present in the hash it is deleted and 1 is returned, otherwise 0
     * @throws RedisException
     */
    public Long hdel(String key, String [] fields) throws RedisException {
        ShardedJedis jedis = this.getRedisPool();
        Long num = 0L;
        try {
            num = jedis.hdel(key.trim(), fields);
        } catch (Exception e) {
            throw new RedisException(e);
        } finally {
            if (jedis != null) {
                this.shardedJedisPool.returnResource(jedis);
            }
        }
        return num;
    }

    /**
     * 获取散列中键值对个数
     * @param key
     * @return he number of entries (fields) contained in the hash stored at key
     * @throws RedisException
     */
    public Long hlen(String key) throws RedisException {
        ShardedJedis jedis = this.getRedisPool();
        Long num = 0L;
        try {
            num = jedis.hlen(key);
        } catch (Exception e) {
            throw new RedisException(e);
        } finally {
            if (jedis != null) {
                this.shardedJedisPool.returnResource(jedis);
            }
        }
        return num;
    }

    /**
     * 返回散列中键值对是否存在
     * @param key
     * @param field
     * @return Return 1 if the hash stored at key contains the specified field
     * @throws RedisException
     */
    public Boolean hexists(String key, String field) throws RedisException {
        ShardedJedis jedis = this.getRedisPool();
        Boolean result = Boolean.FALSE;
        try {
            result = jedis.hexists(key.trim(), field);
        } catch (Exception e) {
            throw new RedisException(e);
        } finally {
            if (jedis != null) {
                this.shardedJedisPool.returnResource(jedis);
            }
        }
        return result;
    }

    /**
     * 返回给定散列的所有键值对
     * @param key
     * @return All the fields and values contained into a hash
     * @throws RedisException
     */
    public Map<String, String> hgetAll(String key) throws RedisException {
        ShardedJedis jedis = this.getRedisPool();
        Map<String, String> result = new HashMap<String, String>();
        try {
            result = jedis.hgetAll(key.trim());
        } catch (Exception e) {
            throw new RedisException(e);
        } finally {
            if (jedis != null) {
                this.shardedJedisPool.returnResource(jedis);
            }
        }
        return result;
    }
    /**
     * 获取Redis连接
     * @return jedis
     * @throws RedisException
     */
    private ShardedJedis getRedisPool() throws RedisException {
        ShardedJedis jedis = null;

        try {
            jedis = this.shardedJedisPool.getResource();
        } catch (Exception var3) {
            throw new RedisException(var3);
        }

        if(null == jedis) {
            throw new RedisException("获取redis连接异常");
        } else {
            return jedis;
        }
    }
}
