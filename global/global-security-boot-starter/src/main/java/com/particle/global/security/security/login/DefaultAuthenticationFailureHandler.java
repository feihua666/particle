package com.particle.global.security.security.login;

import cn.hutool.core.io.IoUtil;
import com.particle.global.dto.response.Response;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.global.tool.json.JsonTool;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * particle 默认的登录失败处理器，写入json响应数据
 * @Author yangwei
 * Created at 2020/12/11 13:32
 */
public class DefaultAuthenticationFailureHandler extends DefaultAbstractAuthenticationHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        PrintWriter out = httpServletResponse.getWriter();
        Response response = Response.buildFailure(ErrorCodeGlobalEnum.AUTHENTICATION_ERROR, "用户名或密码不正确");

        out.write(JsonTool.toJsonStr(response));

        out.flush();
        IoUtil.close(out);
        super.tryNotifyIAuthenticationResultServicesOnFailure(httpServletRequest,httpServletResponse,e);
    }
}
