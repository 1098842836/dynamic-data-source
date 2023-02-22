package com.yl.dynamicdatasource.config;


import javax.servlet.*;
import java.io.IOException;

/**
 * @description:
 * @author: yl
 * @date: 2022-08-13
 **/
public class XssFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }
}
