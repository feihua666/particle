package com.particle.dataquery.client.dataapi.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 数据查询数据接口 查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-03-21 21:51:10
 */
@Data
@ApiModel
public class DataQueryDataApiQueryCommand extends AbstractBaseCommand {

    @NotNull(message = "数据接口地址 不能为空")
    @ApiModelProperty(value = "数据接口地址",required = true)
    private String url;

    /**
     * 参数对象
     */
    @ApiModelProperty(value = "参数对象",required = true)
    private Object param;

    /**
     * 查询参数一般在 url后面以 & 分隔
     */
    @ApiModelProperty(value = "查询参数")
    private String queryString;
}
