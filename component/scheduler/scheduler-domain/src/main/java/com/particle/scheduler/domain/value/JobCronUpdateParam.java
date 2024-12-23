package com.particle.scheduler.domain.value;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * 更新任务表单对象
 * Created by yangwei
 * Created at 2021/2/2 13:25
 */
@Setter
@Getter
public class JobCronUpdateParam extends NameAndGroupParam {

    /**
     * 任务计划名称
     */
    @NotEmpty(message = "任务计划名称不能为空")
    private String schedulerName;

    /**
     * 任务计划实例id
     */
    @NotEmpty(message = "任务计划实例id不能为空")
    private String schedulerInstanceId;

    /**
     * cron表达式
     */
    @NotEmpty(message = "cron表达式不能为空")
    private String cronExpression;

    /**
     * 任务类型
     */
    @NotEmpty(message = "任务类型不能为空")
    private String jobClassType;

    /**
     * 任务类全名
     */
    private String jobClassName;

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
    private List<Object> beanMethodParams;

    /**
     * 描述信息
     */
    @NotEmpty(message = "描述信息不能为空")
    private String description;

    /**
     * 额外数据
     */
    private Map<String, Object> dataMap;


    /**
     * 名称
     */
    @NotEmpty(message = "名称不能为空")
    private String oldName;

    /**
     * 组
     */
    @NotEmpty(message = "组不能为空")
    private String oldGroup;
}
