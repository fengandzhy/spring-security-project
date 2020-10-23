package org.zhouhy.securityproject.browser.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.zhouhy.securityproject.browser.support.SimpleContent;
import org.zhouhy.securityproject.core.properties.SecurityProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController
public class BrowserSecurityController {


    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;


    @RequestMapping(value="/authentication/request")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleContent requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {

        System.out.println(securityProperties.getBrowser().getLoginPage());
        SavedRequest savedRequest = requestCache.getRequest(request,response);
        if(savedRequest!=null){
            String targetUrl = savedRequest.getRedirectUrl();
            log.info("引发跳转的路径是:"+targetUrl);
            if(StringUtils.endsWithIgnoreCase(targetUrl,".html")){
                redirectStrategy.sendRedirect(request,response,securityProperties.getBrowser().getLoginPage());
            }
        }


        return new SimpleContent("请先登录");
    }
}
