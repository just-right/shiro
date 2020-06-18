package com.example.shiro.filter;

import com.alibaba.fastjson.JSONObject;
import com.example.shiro.entity.MyAuthToken;
import com.example.shiro.service.util.HttpContextUtils;
import com.example.shiro.service.util.TokenUtils;
import com.example.shiro.setting.IShiroConst;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义AuthFilter过滤器
 * @className: MyAuthFilter
 * @description
 * @author: luffy
 * @date: 2020/6/16 10:45
 * @version:V1.0
 */
public class MyAuthFilter extends AuthenticatingFilter {

    /**
     * 生成token
     *
     * @param request
     * @param servletResponse
     * @return
     * @throws Exception
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse servletResponse) throws Exception {
       String token = TokenUtils.getRequestToken((HttpServletRequest) request);
       return new MyAuthToken(null,null,token, IShiroConst.TOKEN_REALM_NAME);
    }

    /**
     * 所有请求全部拒绝访问 --
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (((HttpServletRequest) request).getMethod().equals(RequestMethod.OPTIONS.name())) {
            return true;
        }
        return false;
    }

    /**
     * 拒绝访问的请求，会调用onAccessDenied方法，onAccessDenied方法先获取 token
     * 再调用executeLogin方法
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        String token = TokenUtils.getRequestToken((HttpServletRequest) request);
        if (StringUtils.isEmpty(token)) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());
            httpResponse.setCharacterEncoding("UTF-8");
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("status", 403);
            jsonObject.put("msg", "请先登录");

            httpResponse.getWriter().print(jsonObject.toString());
            return false;
        }
        return executeLogin(request, response);
    }

    /**
     * token失效时候调用
     * @param token
     * @param e
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setContentType("application/json;charset=utf-8");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());
        httpResponse.setCharacterEncoding("UTF-8");
        JSONObject jsonObject = new JSONObject();
        //处理登录失败的异常
        jsonObject.put("status", 403);
        jsonObject.put("msg", "登录凭证已失效，请重新登录");
        try {
            httpResponse.getWriter().print(jsonObject.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

}
