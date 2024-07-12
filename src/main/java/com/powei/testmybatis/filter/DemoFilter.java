package com.powei.testmybatis.filter;

import com.alibaba.fastjson.JSONObject;
import com.powei.testmybatis.pojo.Result;
import com.powei.testmybatis.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DemoFilter implements Filter {

    @Override//初始化
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init初始化了Filter");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("doFilter过滤了");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String url = req.getRequestURL().toString();

//如果开启filter，下面这部分解除注释
        if(url.contains("login")) {
            System.out.println("放行登录操作");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        } else {
            String jwt = req.getHeader("token");//这里
            System.out.println("收到的令牌");
            System.out.println(jwt);
            if(!StringUtils.hasLength(jwt)) {//jwt令牌不存在
                System.out.println("用户未登录");
                Result result = Result.error("Not logged, permission denied");
                String JSONStr = JSONObject.toJSONString(result);
                resp.getWriter().write(JSONStr);
                return;
            }
            //jwt令牌存在
            try {
                JwtUtils.parseJWT(jwt);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Invalid token");
                Result result = Result.error("Invalid token, permission denied");
                String JSONStr = JSONObject.toJSONString(result);
                resp.getWriter().write(JSONStr);
                return;
            }
            //没有被return,说明token有效

            filterChain.doFilter(servletRequest, servletResponse);
        }
        //判断当前的是否为登录请求
        //如果是登录请求，则直接放行
        //如果不是登录请求，获取JWT令牌，验证是否登录
//        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("放行后逻辑");
    }

    @Override//销毁
    public void destroy() {
        System.out.println("destroy销毁了Filter");
        Filter.super.destroy();
    }
}
