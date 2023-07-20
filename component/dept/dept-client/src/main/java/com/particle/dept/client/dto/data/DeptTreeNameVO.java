package com.particle.dept.client.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
/**
 * <p>
 * 部门树名称 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-04-12 11:42:10
 */
@Data
@Schema
public class DeptTreeNameVO extends AbstractBaseIdVO {

    @Schema(description = "部门树名称编码")
    private String code;
    
    @Schema(description = "部门树名称")
    private String name;
    
    @Schema(description = "描述")
    private String remark;
    


}
