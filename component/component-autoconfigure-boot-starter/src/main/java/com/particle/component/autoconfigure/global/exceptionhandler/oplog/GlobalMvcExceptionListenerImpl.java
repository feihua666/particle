package com.particle.component.autoconfigure.global.exceptionhandler.oplog;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import com.particle.global.dto.login.LoginUser;
import com.particle.global.exception.handle.GlobalMvcExceptionDTO;
import com.particle.global.exception.handle.GlobalMvcExceptionListener;
import com.particle.global.tool.json.JsonTool;
import com.particle.global.tool.log.TraceTool;
import com.particle.global.tool.login.LoginUserTool;
import com.particle.global.tool.servlet.RequestTool;
import com.particle.oplog.adapter.feign.client.error.rpc.OpLogErrorRpcFeignClient;
import com.particle.oplog.client.error.dto.command.OpLogErrorCreateCommand;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * <p>
 * 全局异常监听，用于存储异常日志
 * </p>
 *
 * @author yangwei
 * @since 2024/8/9 14:44
 */
@Slf4j
public class GlobalMvcExceptionListenerImpl implements GlobalMvcExceptionListener {

    @Autowired
    private OpLogErrorRpcFeignClient opLogErrorRpcFeignClient;
    @Override
    public void onException(GlobalMvcExceptionDTO exceptionDTO) {
        OpLogErrorCreateCommand opLogErrorCreateCommand = map(exceptionDTO);
        opLogErrorRpcFeignClient.create(opLogErrorCreateCommand);
    }

    /**
     * 异常信息映射转为创建命令
     * @param exceptionDTO
     * @return
     */
    private OpLogErrorCreateCommand map(GlobalMvcExceptionDTO exceptionDTO) {
        OpLogErrorCreateCommand opLogErrorCreateCommand = OpLogErrorCreateCommand.create(TraceTool.getTraceId(), exceptionDTO.getStacktrace());
        LoginUser loginUser = LoginUserTool.getLoginUser();
        if (loginUser != null) {
            opLogErrorCreateCommand.setUserId(loginUser.getId());
            opLogErrorCreateCommand.setUserName(loginUser.getName());
            opLogErrorCreateCommand.setUserNickname(loginUser.getNickname());
            opLogErrorCreateCommand.setUserAvatar(loginUser.getAvatar());
        }
        String requestUrl = exceptionDTO.getRequest().getRequestURL().toString();
        String queryString = exceptionDTO.getRequest().getQueryString();
        if (StrUtil.isNotEmpty(queryString)) {
            requestUrl = requestUrl + "?" + queryString;
        }
        opLogErrorCreateCommand.setRequestUrl(requestUrl);
        opLogErrorCreateCommand.setRequestMethod(exceptionDTO.getRequest().getMethod());
        opLogErrorCreateCommand.setRequestHeaders(JsonTool.toJsonStr(JakartaServletUtil.getHeaderMap(exceptionDTO.getRequest())));
        opLogErrorCreateCommand.setRequestParams(JsonTool.toJsonStr(exceptionDTO.getRequest().getParameterMap()));
        opLogErrorCreateCommand.setRequestBody(getMessagePayload(exceptionDTO.getRequest()));
        opLogErrorCreateCommand.setRequestIp(RequestTool.getClientRealIP(exceptionDTO.getRequest()));

        opLogErrorCreateCommand.setResponseStatus(exceptionDTO.getResponseHttpStatus());
        // 此处无法获取响应头，忽略
        // opLogErrorCreateCommand.setResponseHeaders();
        opLogErrorCreateCommand.setResponseBody(JsonTool.toJsonStr(exceptionDTO.getResponseBody()));

        return opLogErrorCreateCommand;
    }

    /**
     * 注意请求体必须是可读的，否则后面可能无法获取内容，因为request只能读取一次
     * 参考了{@link com.particle.global.web.filter.RequestResponseLogFilter#getMessagePayload(HttpServletRequest)}
     * @param request
     * @return
     */
    private String getMessagePayload(HttpServletRequest request) {
        try {
            return IoUtil.readUtf8(request.getInputStream());
        } catch (IOException e) {
            log.error("getMessagePayload error",e);
            return "getMessagePayload error.detail for this error can be search in log";

        }
    }
}
