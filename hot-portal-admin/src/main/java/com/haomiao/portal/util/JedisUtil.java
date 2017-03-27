package com.haomiao.portal.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ResourceLoader;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Properties;

/**
 * Created by doing on 13-12-26.
 */
public class JedisUtil {
    private static JedisPool jedisPool;
    private static Logger logger = LoggerFactory.getLogger(JedisUtil.class);

    public static JedisPool initJedisPool(String propertiesFileName) throws ResourceException {
        if (jedisPool == null) {
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            Properties p = PropertiesUtils.loadProperties(ResourceLoader.CLASSPATH_URL_PREFIX + "/" + propertiesFileName);
            String host = p.getProperty("redis.host", "127.0.0.1");
            int port = Integer.valueOf(p.getProperty("redis.port", "6379"));
            int db = Integer.valueOf(p.getProperty("redis.db", "0"));
            String pwd = p.getProperty("redis.pwd");
            int maxActive = Integer.valueOf(p.getProperty("redis.max.active", "100"));
            int maxIdle = Integer.valueOf(p.getProperty("redis.max.idle", "10"));
            int minIdle = Integer.valueOf(p.getProperty("redis.min.idle", "3"));
            int timeout = Integer.valueOf(p.getProperty("redis.timeout", "5000"));
            Boolean testOnBorrow = Boolean.valueOf(p.getProperty("redis.testOnBorrow", "false"));
            Boolean testOnReturn = Boolean.valueOf(p.getProperty("redis.testOnReturn", "false"));
            Boolean testWhileIdle = Boolean.valueOf(p.getProperty("redis.testWhileIdle", "false"));
            int timeBetweenEviction = Integer.valueOf(p.getProperty("redis.timeBetweenEvictionRunsMillis", "600000"));
            int minEvictableIdleTimeMillis = Integer.valueOf(p.getProperty("redis.minEvictableIdleTimeMillis", "1800000"));
            int softMinEvictableIdleTimeMillis = Integer.valueOf(p.getProperty("redis.softMinEvictableIdleTimeMillis", "1800000"));


            jedisPoolConfig.setMaxActive(maxActive);
            jedisPoolConfig.setMaxIdle(maxIdle);
            jedisPoolConfig.setMinIdle(minIdle);
            jedisPoolConfig.setMaxWait(timeout);
            jedisPoolConfig.setTestOnBorrow(testOnBorrow);
            jedisPoolConfig.setTestOnReturn(testOnReturn);
            jedisPoolConfig.setTestWhileIdle(testWhileIdle);
            jedisPoolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEviction);
            jedisPoolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
            jedisPoolConfig.setSoftMinEvictableIdleTimeMillis(softMinEvictableIdleTimeMillis);

            jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, pwd, db);
        } else {
            throw new ResourceException("jedis pool exist! do not init again!");
        }
        return jedisPool;
    }

    public static Jedis getInstance() {
    	Jedis jedis = null;
    	try {
    	    if(jedisPool == null){
                initJedisPool("application.properties");
            }
    		jedis = jedisPool.getResource();
    	}catch(Exception e) {
    		e.printStackTrace();
    		jedis = null;
    	}
        return jedis;
    }

    public static void closeJedis(Jedis jedis) {
        jedisPool.returnResource(jedis);
    }

    public static void closeBrokenJedis(Jedis jedis) {
        jedisPool.returnBrokenResource(jedis);
    }

    public static void closePool() {
        if (jedisPool != null) {
            jedisPool.destroy();
        }
    }

}
