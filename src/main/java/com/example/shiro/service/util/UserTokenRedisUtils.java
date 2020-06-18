package com.example.shiro.service.util;

import com.example.shiro.setting.IShiroConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * @className: RedisUtils
 * @description
 * @author: luffy
 * @date: 2020/6/16 12:13
 * @version:V1.0
 */
@Component
public class UserTokenRedisUtils {

    private static final String USERTOKEN_KEYPREFIX = "uid:token:";
    private static JedisPool jedisPool1;
    @Autowired
    private JedisPool jedisPool2;

    @PostConstruct
    private void init() {
        jedisPool1 = jedisPool2;
    }

    public static boolean isExistedKey(Integer uid) {
        if (uid == null)
            return false;
        String key = USERTOKEN_KEYPREFIX + uid;
        try (Jedis jedis = jedisPool1.getResource()) {
            return jedis.exists(key);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static String getValueByKey(Integer uid) {
        if (uid == null)
            return null;
        String key = USERTOKEN_KEYPREFIX + uid;
        try (Jedis jedis = jedisPool1.getResource()) {
            return jedis.get(key);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String setToken(Integer uid) {
        if (uid == null) {
            return null;
        }
        String key = USERTOKEN_KEYPREFIX + uid;
        String token = UUID.randomUUID().toString();
        try (Jedis jedis = jedisPool1.getResource()) {
            jedis.setex(key, IShiroConst.EXPIRE_TIME, token);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return token;
    }

    public static void delToken(Integer uid) {
        if (uid == null) {
            return;
        }
        String key = USERTOKEN_KEYPREFIX + uid;
        try (Jedis jedis = jedisPool1.getResource()) {
            jedis.del(key);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
