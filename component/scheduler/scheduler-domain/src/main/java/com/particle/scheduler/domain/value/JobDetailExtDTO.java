package com.particle.scheduler.domain.value;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * 任务响应扩展数据对象
 * 主要是为了更新时回显
 * Created by yangwei
 * Created at 2021/2/2 13:45
 */
@Setter
@Getter
public class JobDetailExtDTO extends JobDetailDTO {

    /**
     * 任务类型
     */
    private String jobClassType;

    /**
     * 请求地址
     */
    private String httpUrl;

    /**
     * 请求方法
     */
    private String httpMethod;

    /**
     * 请求头信息
     */
    private Map<String,String> httpHeaders;

    /**
     * 请求参数
     */
    private Map<String,Object> httpParams;

    /**
     * bean名称
     */
    private String beanName;

    /**
     * bean方法名
     */
    private String beanMethodName;

    /**
     * bean方法参数
     */
    private List beanMethodParams;

}
