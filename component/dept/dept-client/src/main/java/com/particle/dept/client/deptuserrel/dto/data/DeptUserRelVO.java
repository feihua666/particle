package com.particle.dept.client.deptuserrel.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
/**
 * <p>
 * 部门用户关系 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-05-17 10:28:42
 */
@Data
@ApiModel
public class DeptUserRelVO extends AbstractBaseIdVO {

    @ApiModelProperty("用户id")
    private Long userId;
    
    @ApiModelProperty("部门id")
    private Long deptId;
    


}
