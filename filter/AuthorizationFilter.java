package com.gvt.apos.common.filter;

import com.gvt.apos.common.config.UmsAuthConfig;
import com.gvt.apos.common.config.UserTokenInfo;
import com.gvt.apos.common.util.AposTokenHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author BlueWang
 * @ClassName: AuthorizationFilter
 * @Description:
 * @date 2019/10/11 14:17
 */

@Slf4j
@WebFilter(filterName = "AuthorizationFilter", urlPatterns = {"/mall/**","/api/**"})
public class AuthorizationFilter implements Filter {

    private UmsAuthConfig umsAuthConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext context = filterConfig.getServletContext();
        ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(context);
        umsAuthConfig = applicationContext.getBean(UmsAuthConfig.class);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if(request.getHeader("Authorization")!=null){
            final UserTokenInfo tokenInfo = AposTokenHelper.resolveUserToken(request.getHeader("Authorization"),
                    umsAuthConfig.getUserPubSecretKey());
            TokenHolder.add(tokenInfo);
            filterChain.doFilter(servletRequest,servletResponse);
            TokenHolder.remove();
        }else{
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
