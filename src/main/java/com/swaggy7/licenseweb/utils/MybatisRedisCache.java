package com.swaggy7.licenseweb.utils;


import com.swaggy7.licenseweb.config.RedisConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.CollectionUtils;

import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author shuangyueliao
 * @create 2019/9/10 14:02
 * @Version 0.1
 */




/*
*
*
*
*
*
*
*
* 似乎不好使
*
*
*
*
*
*
*
*
*
*
* */
@Slf4j
public class MybatisRedisCache implements Cache {


    // 读写锁
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

    //这里使用了redis缓存，使用springboot自动注入
    private RedisTemplate<String, Object> redisTemplate;

    private String id;

    public MybatisRedisCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        System.out.println("put----------------");
        if (redisTemplate == null) {
            //由于启动期间注入失败，只能运行期间注入，这段代码可以删除
            redisTemplate = (RedisTemplate<String, Object>) SpringUtil.getBean("strRedisTemplate");
            System.out.println("redisA:    " + redisTemplate);
        }
        if (value != null) {
            redisTemplate = (RedisTemplate<String, Object>) SpringUtil.getBean("strRedisTemplate");
            redisTemplate.opsForValue().set(key.toString(), value);
        }
    }

    @Override
    public Object getObject(Object key) {
        System.out.println("get----------------");
        if (redisTemplate == null) {
            //由于启动期间注入失败，只能运行期间注入，这段代码可以删除
            redisTemplate = (RedisTemplate<String, Object>) SpringUtil.getBean("strRedisTemplate");
        }
        try {
            if (key != null) {
//                System.out.println("keys:  " + redisTemplate.opsForValue().get(key.toString()));
                System.out.println("get1---------------");
                Object res = redisTemplate.opsForValue().get(key.toString());
//                return redisTemplate.opsForValue().get(key.toString());
                return res;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("缓存出错 ");
        }
        System.out.println("get2---------------");
        return null;
    }

    @Override
    public Object removeObject(Object key) {
//        System.out.println("remove----------------");
        if (redisTemplate == null) {
            //由于启动期间注入失败，只能运行期间注入，这段代码可以删除
            redisTemplate = (RedisTemplate<String, Object>) SpringUtil.getBean("strRedisTemplate");
        }
        if (key != null) {
            redisTemplate.delete(key.toString());
        }
        return null;
    }

    @Override
    public void clear() {
//        System.out.println("clear----------------");
        log.debug("清空缓存");
        if (redisTemplate == null) {
            redisTemplate = (RedisTemplate<String, Object>) SpringUtil.getBean("strRedisTemplate");
        }
        Set<String> keys = redisTemplate.keys("*:" + this.id + "*");
        if (!CollectionUtils.isEmpty(keys)) {
            redisTemplate.delete(keys);
        }
    }

    @Override
    public int getSize() {
//        System.out.println("size----------------");
        if (redisTemplate == null) {
            //由于启动期间注入失败，只能运行期间注入，这段代码可以删除
            redisTemplate = (RedisTemplate<String, Object>) SpringUtil.getBean("strRedisTemplate");
        }
        Long size = redisTemplate.execute((RedisCallback<Long>) RedisServerCommands::dbSize);
        return size.intValue();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }

    private RedisTemplate getRedisTemplate() {
        //从上下文中获取redisTemplate
        RedisTemplate redisTemplate = (RedisTemplate) SpringUtil.getBean("redisTemplate");
        //设置key是string类型的序列
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置hashKey是string类型的序列
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }

}
