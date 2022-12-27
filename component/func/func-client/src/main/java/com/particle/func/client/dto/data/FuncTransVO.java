package com.particle.func.client.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 功能菜单 翻译结果
 * </p>
 *
 * @author yw
 * @since 2022-12-27
 */
@Accessors(chain = true)
@Data
@ApiModel
public class FuncTransVO extends AbstractBaseIdVO {
    
    @ApiModelProperty("字典编码,模糊查询，字典组时必填")
    private String code;

    @ApiModelProperty("字典名称,模糊查询")
    private String name;

}
