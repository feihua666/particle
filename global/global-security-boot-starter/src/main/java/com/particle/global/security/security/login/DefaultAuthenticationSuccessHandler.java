package com.particle.global.security.security.login;

import cn.hutool.core.io.IoUtil;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.security.security.ApplicationContextForSecurityHelper;
import com.particle.global.tool.json.JsonTool;
import com.particle.global.tool.servlet.RequestTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * scatter默认认证成功处理器，直接写入json的响应数据
 * @author yangwei
 * @since 2020/12/11 13:30
 */
public class DefaultAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private GlobalSecurityAuthenticationHandler globalSecurityAuthenticationHandler;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws ServletException, IOException {

        Object principal = authentication.getPrincipal();
        if (principal instanceof LoginUser) {
            ((LoginUser) principal).setPassword(null);
            LoginUserTool.saveToSession((LoginUser) principal,httpServletRequest);
        }
        SingleResponse<Object> singleResponse = SingleResponse.of(principal);

        boolean ajaxRequest = RequestTool.isAjaxRequest(httpServletRequest);
        if (ajaxRequest) {
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
