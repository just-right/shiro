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
 * @className: TokenRedisUtils
 * @description
 * @author: luffy
 * @date: 2020/6/16 12:13
 * @version:V1.0
 */
@Component
public class TokenUserRedisUtils {

    private static JedisPool jedisPool1;
    @Autowired
    private JedisPool jedisPool2;

    @PostConstruct
    private void init() {
        jedisPool1 = jedisPool2;
    }

    public static boolean isExistedKey(String token) {
        if (StringUtils.isEmpty(token))
            return false;
        try (Jedis jedis = jedisPool1.getResource()) {
            return jedis.exists(token);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static String getValueByKey(String token) {
        if (StringUtils.isEmpty(token))
            return null;
        try (Jedis jedis = jedisPool1.getResource()) {
            return jedis.get(token);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void setToken(Integer uid,String token) {
        if (uid == null || StringUtils.isEmpty(token)) {
            return;
        }
        try (Jedis jedis = jedisPool1.getResource()) {
            jedis.setex(token, IShiroConst.EXPIRE_TIME, String.valueOf(uid));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void delToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return;
        }
        try (Jedis jedis = jedisPool1.getResource()) {
            jedis.del(token);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
