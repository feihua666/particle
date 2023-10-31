package com.particle.global.security.security.login;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.security.GlobalSecurityProperties;
import com.particle.global.security.security.ApplicationContextForSecurityHelper;
import com.particle.global.swagger.SwaggerInfo;
import com.particle.global.tool.json.JsonTool;
import com.particle.global.tool.servlet.RequestTool;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Optional;

/**
 * scatter默认认证成功处理器，直接写入json的响应数据
 * @author yangwei
 * @since 2020/12/11 13:30
 */
public class DefaultAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private GlobalSecurityAuthenticationHandler globalSecurityAuthenticationHandler;

    @Autowired(required = false)
    private ServerProperties serverProperties;
    @Autowired(required = false)
    private GlobalSecurityProperties globalSecurityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws ServletException, IOException {

        if (globalSecurityProperties != null && BooleanUtil.isTrue(globalSecurityProperties.getForceWriteLoginHeaderToken())) {
            HttpSession session = httpServletRequest.getSession(false);
            if (session != null) {
                /**
                 * 设置请求头，这在使用spring session时，目前是使用了header和cookie两个方式，如果在已经登录的情况下重新登录，可能请求头中没有token的情况，这里强制设置一下
                 * 原因是默认在spring commit 时检查了当前session是否有效，如果有效的情况下没有设置，但用户确实已经重新登录了，参见：{@link org.springframework.session.web.http.SessionRepositoryFilter.SessionRepositoryRequestWrapper#commitSession()}
                 * 可通过 server.servlet.session.cookie.name 修改,与{@link com.particle.global.session.auto.GlobalSessionAutoConfiguration#customDelegateHttpSessionIdResolver(ServerProperties, ObjectProvider)} 保持一致的可配置自定义cookie名称的兼容
                 * 注意在{@link  com.particle.global.session.auto.CustomDefaultCookieSerializer} 中也使用了该变量
                 */
                String tokenName = Optional.ofNullable(serverProperties)
                        .map(ServerProperties::getServlet)
                        .map(ServerProperties.Servlet::getSession)
                        .map(Session::getCookie)
                        .map(Session.Cookie::getName).orElse(SwaggerInfo.token);
                httpServletResponse.setHeader(tokenName, session.getId());
            }
        }


        Object principal = authentication.getPrincipal();
        if (principal instanceof LoginUser) {
            ((LoginUser) principal).setPassword(null);
            LoginUserTool.saveToSession((LoginUser) principal,httpServletRequest);
        }
        SingleResponse<Object> singleResponse = SingleResponse.of(principal);

        boolean ajaxRequest = RequestTool.isAjaxRequest(httpServletRequest);
        String accept = ServletUtil.getHeader(httpServletRequest, "Accept", CharsetUtil.UTF_8.toUpperCase());
        if (ajaxRequest || StrUtil.contains(accept,MediaType.APPLICATION_JSON_VALUE)) {
            outJson(httpServletResponse,singleResponse);
        }
        // 通知自定义认证结果调用
        globalSecurityAuthenticationHandler.tryNotifyIAuthenticationResultServicesOnSuccess(httpServletRequest,httpServletResponse,authentication,singleResponse);

        if (!ajaxRequest) {
            super.onAuthenticationSuccess(httpServletRequest,httpServletResponse,authentication);
        }

    }

    /**
     * 输出json
     * @param httpServletResponse
     * @param singleResponse
     * @throws IOException
     */
    protected void outJson(HttpServletResponse httpServletResponse,SingleResponse<Object> singleResponse)  throws IOException{
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = ApplicationContextForSecurityHelper.getBean(MappingJackson2HttpMessageConverter.class);
        String toJsonStrForHttp = JsonTool.toJsonStrForHttp(singleResponse, jackson2HttpMessageConverter.getObjectMapper(), DefaultAuthenticationSuccessHandler.class);
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        PrintWriter out = httpServletResponse.getWriter();
        out.write(toJsonStrForHttp);
        out.flush();
        IoUtil.close(out);
    }
}
