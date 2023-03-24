package com.particle.dataquery.client.datasource.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 数据查询数据源接口 查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-03-14 22:21:40
 */
@Data
@ApiModel
public class DataQueryDatasourceApiQueryCommand extends AbstractBaseCommand {

    @NotNull(message = "数据源接口id 不能为空")
    @ApiModelProperty(value = "数据源接口id",required = true)
    private Long dataQueryDatasourceApiId;

    /**
     * 参数对象
     */
    @ApiModelProperty(value = "参数对象",required = true)
    private Object param;
}
