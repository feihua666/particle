package com.particle.scheduler.client.dto.data;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * 主要是为了更新时回显
 * Created by yangwei
 * Created at 2021/2/2 13:45
 */
@Setter
@Getter
@ApiModel(value="任务响应扩展数据对象")
public class JobDetailExtVo extends JobDetailVo {

    @ApiModelProperty(value = "任务类型")
    private String jobClassType;

    @ApiModelProperty(value = "请求地址")
    private String httpUrl;

    @ApiModelProperty(value = "请求方法")
    private String httpMethod;

    @ApiModelProperty(value = "请求头信息")
    private Map<String,String> httpHeaders;

    @ApiModelProperty(value = "请求参数")
    private Map<String,Object> httpParams;

    @ApiModelProperty(value = "bean名称")
    private String beanName;

    @ApiModelProperty(value = "bean方法名")
    private String beanMethodName;

    @ApiModelProperty(value = "bean方法参数")
    private List beanMethodParams;


    @ApiModelProperty(value = "脚本类型")
    private String scriptType;

    @ApiModelProperty(value = "脚本内容")
    private String scriptContent;

}
