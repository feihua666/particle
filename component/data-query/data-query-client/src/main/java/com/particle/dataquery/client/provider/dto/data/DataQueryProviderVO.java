package com.particle.dataquery.client.provider.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
/**
 * <p>
 * 数据查询供应商 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-03-03 19:19:55
 */
@Data
@ApiModel
public class DataQueryProviderVO extends AbstractBaseIdVO {

    @ApiModelProperty("供应商名称")
    private String name;
    
    @ApiModelProperty("是否禁用")
    private Boolean isDisabled;

    @ApiModelProperty("禁用原因")
    private String disabledReason;

    @ApiModelProperty("姓名")
    private String userName;
    
    @ApiModelProperty("邮箱")
    private String email;
    
    @ApiModelProperty("手机号")
    private String mobile;
    
    @ApiModelProperty("描述")
    private String remark;
    


}
