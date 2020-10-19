package org.zhouhy.securityproject.web;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

@Component
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("TimeFilter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        long time = new Date().getTime();
        chain.doFilter(request,response);
        System.out.println("总共耗时:"+(new Date().getTime()-time));
    }

    @Override
    public void destroy() {
        System.out.println("TimeFilter destroy");
    }
}
