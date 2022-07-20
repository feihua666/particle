package com.particle.global.security.security.login;

import cn.hutool.core.io.IoUtil;
import com.particle.global.tool.json.JsonTool;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * scatter默认认证成功处理器，直接写入json的响应数据
 * @author yangwei
 * @since 2020/12/11 13:30
 */
public class DefaultAuthenticationSuccessHandler extends DefaultAbstractAuthenticationHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        Object principal = authentication.getPrincipal();
        if (principal instanceof LoginUser) {
            ((LoginUser) principal).setPassword(null);
            LoginUserTool.saveToSession((LoginUser) principal,httpServletRequest);
        }
        out.write(JsonTool.toJsonStr(principal));
        out.flush();
        IoUtil.close(out);
        // 通知自定义认证结果调用
        super.tryNotifyIAuthenticationResultServicesOnSuccess(httpServletRequest,httpServletResponse,authentication);
    }
}
