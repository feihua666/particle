/*
 *  * Copyright (c) 2018 yaduo.com. All Rights Reserved.
 */

package com.particle.global.web.filter;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.global.tool.json.JsonTool;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.AbstractRequestLoggingFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 记录http请求的参数/响应结果/耗时
 * 异步 servlet 暂不支持
 * 2021年08月02日
 * @author yw
 */
@Slf4j
public class RequestResponseLogFilter extends AbstractRequestLoggingFilter {


    @Autowired(required = false)
    private List<RequestResponseLogMatchResponseResolver> requestResponseLogMatchResponseResolvers;


    /**
     * 换行符
     */
    private static final String SEP = System.lineSeparator();

    /**
     * 是否打印请求日志
     */
    @Value("${particle.web.filter.log.request:true}")
    private boolean isLogRequest;
    /**
     * 是否打印响应日志
     */
    @Value("${particle.web.filter.log.response:true}")
    private boolean isLogResponse;


    /**
     * 打印请求Content-Type的白名单
     */
    private static final List<String> REQUEST_CONTENT_TYPE_WHITE_SET =
            Arrays.asList(ContentType.APPLICATION_JSON.getMimeType(), ContentType.APPLICATION_ATOM_XML.getMimeType(),
                    ContentType.APPLICATION_FORM_URLENCODED.getMimeType(), ContentType.APPLICATION_XML.getMimeType(),
                    ContentType.APPLICATION_XHTML_XML.getMimeType(), ContentType.TEXT_HTML.getMimeType(),
                    ContentType.TEXT_PLAIN.getMimeType(), ContentType.TEXT_XML.getMimeType());

    /**
     * 打印请求后缀的黑名单
     */
    private static final List<String> REQUEST_EXTENSION_BLACK_SET =
            Arrays.asList(".js", ".css","html");

    /**
     * 打印响应Content-Type的白名单
     */
    private static final List<String> RESPONSE_CONTENT_TYPE_WHITE_SET = REQUEST_CONTENT_TYPE_WHITE_SET;
    /**
     * 打印响应请求后缀的黑名单
     */
    private static final List<String> RESPONSE_EXTENSION_BLACK_SET =
            Arrays.asList(".js", ".css","html","png","jpg","jpeg","gif");


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String requestUrl = request.getRequestURL().toString();
        log.info("请求开始 url={}",requestUrl);
        boolean isFirstRequest = !isAsyncDispatch(request);
        HttpServletRequest requestToUse = request;

        HttpServletResponse responseToUse = response;

        boolean matchResponse = ((isMatchContentType(response.getContentType(),RESPONSE_CONTENT_TYPE_WHITE_SET)) && !matchResponseExtensionBlack(requestUrl))||
                isMatchContentType(request.getHeader(HttpHeaders.ACCEPT),RESPONSE_CONTENT_TYPE_WHITE_SET) ;

        if (!matchResponse) {
            if (requestResponseLogMatchResponseResolvers != null) {
                for (RequestResponseLogMatchResponseResolver requestResponseLogMatchResponseResolver : requestResponseLogMatchResponseResolvers) {
                    if (requestResponseLogMatchResponseResolver.matchs(request)) {
                        matchResponse = true;
                    }
                }
            }
        }

        if(isLogResponse && matchResponse && !(response instanceof ContentCachingResponseWrapper) &&  !isAsyncDispatch(request)){
            responseToUse = new ContentCachingResponseWrapper(response);
        }
        boolean matchRequest = (request.getContentType() == null ||isMatchContentType(request.getContentType(),REQUEST_CONTENT_TYPE_WHITE_SET)) && !mathRequestExtensionBlack(requestUrl);


        if (isLogRequest && matchRequest && isFirstRequest) {
            String requestMessage = getRequestMessage(requestToUse);
            if (!StrUtil.isEmpty(requestMessage)) {
                // 不要空行，日志一行打印，查日志时多行麻烦
                log.info("请求内容：" + requestMessage.replace(SEP,""));
            }
        }
        long start = System.currentTimeMillis();
        try {
            filterChain.doFilter(requestToUse, responseToUse);
        }
        finally {
            boolean isAsyncStarted = isAsyncStarted(requestToUse);

            if(isLogResponse && matchResponse ){
                if(!isAsyncStarted){
                    // 自定义打印请求响应日志
                    String responseMessagePayload = getResponseMessagePayload(responseToUse);
                    String responseHeaders = getResponseHeaders(responseToUse);
                    // 不要空行，日志一行打印，查日志时多行麻烦
                    log.info("请求响应：status={},payload={},headers={}" ,
                            responseToUse.getStatus(),
                            StrUtil.isEmpty(responseMessagePayload) ? "" : responseMessagePayload.replace(SEP,""),
                            responseHeaders);
                }
            }
            if(!isAsyncStarted){
                long end = System.currentTimeMillis();
                log.info("请求结束：耗时: {}ms",end - start);
            }else {
                log.warn("异步servlet 不支持响应日志打印");
            }
        }
    }


    /**
     * 是否匹配白名单
     * @param contentType
     * @param whiteList
     * @return
     */
    private boolean isMatchContentType(String contentType,List<String> whiteList){
        boolean match = false;
        if (StrUtil.isEmpty(contentType)) {
            return match;
        }
        match = whiteList.stream().filter(item -> item.equalsIgnoreCase(contentType) || contentType.toLowerCase().contains(item)).count() > 0;

        return match;
    }


    private String getRequestMessage(HttpServletRequest request){
        return createMessage(request, "", "");
    }

    /**
     * 获取响应内容
     * @param response
     * @return
     */
    public static String getResponseMessagePayload(HttpServletResponse response){
        ContentCachingResponseWrapper wrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        if (wrapper != null) {
            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                String result = null;
                try {
                    result =  new String(buf, 0, buf.length, wrapper.getCharacterEncoding());
                }
                catch (UnsupportedEncodingException ex) {
                    return "[UnsupportedEncodingException]";
                }

                try {
                    wrapper.copyBodyToResponse();
                } catch (IOException e) {
                    //   wrapper.copyBodyToResponse(); 声明的异常
                }

                return result;
            }
        }
        return null;
    }

	/**
	 * 获取响应头
     * @param response
	 * @return
	 */
    private String getResponseHeaders(HttpServletResponse response){
        Map<String, String> headerMapResult = Collections.emptyMap();
        Collection<String> headerNames = response.getHeaderNames();
        if (CollectionUtil.isNotEmpty(headerNames)) {
            headerMapResult = headerNames.stream().collect(Collectors.toMap(Function.identity(), (item) -> response.getHeader(item),(v1,v2)-> v1));
        }
        return JsonTool.toJsonStr(headerMapResult);
    }

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        // 已失效
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        // 已失效
    }


    @Override
    protected boolean isIncludeQueryString() {
        return true;
    }

    @Override
    protected boolean isIncludeClientInfo() {
        return true;
    }

    @Override
    protected boolean isIncludeHeaders() {
        return true;
    }

    @Override
    protected boolean isIncludePayload() {
        return true;
    }

    @Override
    protected String getMessagePayload(HttpServletRequest request) {
        try {
            return IoUtil.readUtf8(request.getInputStream());
        } catch (IOException e) {
            log.error("获取request utf-8 字符串异常",e);
        }
        return null;
    }

    @Override
    protected boolean shouldLog(HttpServletRequest request) {
        return true;
    }

    private static boolean mathRequestExtensionBlack(String url) {
        return REQUEST_EXTENSION_BLACK_SET.stream().anyMatch(e -> url.endsWith(e));
    }
    private static boolean matchResponseExtensionBlack(String url) {
        return RESPONSE_EXTENSION_BLACK_SET.stream().anyMatch(e -> url.endsWith(e));
    }
}
