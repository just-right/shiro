package com.example.shiro.service.util;

import com.example.shiro.setting.IShiroConst;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @className: TokenUtils
 * @description
 * @author: luffy
 * @date: 2020/6/16 10:47
 * @version:V1.0
 */
public class TokenUtils {
    public static String getRequestToken(HttpServletRequest httpRequest) {
        //从header中获取token
        String token = httpRequest.getHeader(IShiroConst.REQUEST_TOKEN_FIELD);
        //如果header中不存在token，则从参数中获取token
        if (StringUtils.isEmpty(token)) {
            token = httpRequest.getParameter(IShiroConst.REQUEST_TOKEN_FIELD);
        }
        return token;
    }
}
