package com.particle.scheduler.infrastructure.job.quartzjob;

import com.particle.global.tool.json.JsonTool;
import com.particle.global.tool.spring.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.web.client.RestTemplateBuilderConfigurer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * http 请求任务
 * Created by yangwei
 * Created at 2021/2/4 10:14
 */
@Slf4j
public class HttpInvokerQuartzJob extends AbstractQuartzJob {

    public static enum HttpInvokerQuartzJobDataMapKeys {
        httpUrl, httpMethod, httpHeaders, httpParams,
    }

    /**
     * 默认情况下 spring boot 不会自动装配 restTemplate，而是自动装配了 {@link RestTemplateAutoConfiguration#restTemplateBuilder(RestTemplateBuilderConfigurer)}
     */
    @Autowired(required = false)
    private RestTemplate restTemplate;

    @Override
    public String doExecute(JobExecutionContext context) {
        log.info("任务执行 mergedJobDataMap={}",context.getMergedJobDataMap().toString());
        // 获取参数
        String httpUrl = (String) context.getMergedJobDataMap().get(HttpInvokerQuartzJobDataMapKeys.httpUrl.name());
        String httpMethod = (String) context.getMergedJobDataMap().get(HttpInvokerQuartzJobDataMapKeys.httpMethod.name());
        Map<String,String> httpHeadersParam = (Map<String, String>) context.getMergedJobDataMap().get(HttpInvokerQuartzJobDataMapKeys.httpHeaders.name());
        Map<String,Object> httpParams = (Map<String, Object>) context.getMergedJobDataMap().get(HttpInvokerQuartzJobDataMapKeys.httpParams.name());
        // 封装请求头
        HttpHeaders httpHeaders = new HttpHeaders();
        if (httpHeadersParam != null) {
            for (String key : httpHeadersParam.keySet()) {
                httpHeaders.add(key,httpHeadersParam.get(key));
            }
        }
        HttpEntity tHttpEntity = new HttpEntity(httpParams, httpHeaders);
        if (restTemplate == null) {
            RestTemplateBuilder restTemplateBuilder = SpringContextHolder.getBean(RestTemplateBuilder.class);
            restTemplate = restTemplateBuilder.build();
        }
        // 发起请求
        ResponseEntity<String> exchange = restTemplate.exchange(httpUrl, HttpMethod.resolve(httpMethod.toUpperCase()), tHttpEntity, String.class);

        HttpResult httpResult = httpResultWrap(exchange.getStatusCodeValue(), exchange.getBody(), exchange.getHeaders());

        log.info("任务执行结果 responseStatus={},responseBody={},responseHeaders={}",exchange.getStatusCodeValue(),exchange.getBody(), JsonTool.toJsonStr(httpResult.getHeaders()));


        return JsonTool.toJsonStr(httpResult);
    }
}
