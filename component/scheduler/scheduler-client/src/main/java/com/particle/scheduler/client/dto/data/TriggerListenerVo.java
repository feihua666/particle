package com.particle.scheduler.client.dto.data;

import com.particle.global.dto.basic.VO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by yangwei
 * Created at 2021/2/2 13:45
 */
@Setter
@Getter
@ApiModel(value="触发器监听响应数据对象")
public class TriggerListenerVo extends VO {

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "监听类名称")
    private String className;
}