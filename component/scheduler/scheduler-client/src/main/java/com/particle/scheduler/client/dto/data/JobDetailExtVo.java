package com.particle.scheduler.client.dto.data;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description="任务响应扩展数据对象")
public class JobDetailExtVo extends JobDetailVo {

    @Schema(description = "任务类型")
    private String jobClassType;

    @Schema(description = "请求地址")
    private String httpUrl;

    @Schema(description = "请求方法")
    private String httpMethod;

    @Schema(description = "请求头信息")
    private Map<String,String> httpHeaders;

    @Schema(description = "请求参数")
    private Map<String,Object> httpParams;

    @Schema(description = "bean名称")
    private String beanName;

    @Schema(description = "bean方法名")
    private String beanMethodName;

    @Schema(description = "bean方法参数")
    private List beanMethodParams;


    @Schema(description = "脚本类型")
    private String scriptType;

    @Schema(description = "脚本内容")
    private String scriptContent;

}
