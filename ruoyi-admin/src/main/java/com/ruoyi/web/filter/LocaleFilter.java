package com.ruoyi.web.filter;

import cn.hutool.core.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@WebFilter(value = "/api/*",filterName = "localeFilter")
public class LocaleFilter implements Filter {
    Map<String,Locale> localeMap = new HashMap<>();


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        localeMap.put("zh_CN",Locale.SIMPLIFIED_CHINESE);

        Locale.setDefault(Locale.SIMPLIFIED_CHINESE);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest=(HttpServletRequest)request;
        String language = httpServletRequest.getHeader("Language");
        if(ObjectUtil.isEmpty(language)){
            language = "zh_CN";
        }
        Locale locale = localeMap.get(language);
        if(ObjectUtil.isEmpty(locale)){
            String[] s = language.split("_");
            if(s.length>=2){
                locale = new Locale(s[0],s[1]);
                localeMap.put(language,locale) ;
            }else{
                locale = new Locale(s[0],"");
                localeMap.put(language,locale) ;
            }
        }
        if(!Locale.getDefault().equals(locale)){
            Locale.setDefault(locale);
        }
        chain.doFilter(request,response);
    }
}
