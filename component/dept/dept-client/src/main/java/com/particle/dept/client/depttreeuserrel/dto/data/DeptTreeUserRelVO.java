package com.particle.dept.client.depttreeuserrel.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
/**
 * <p>
 * 部门树用户关系 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-05-17 10:26:06
 */
@Data
@Schema
public class DeptTreeUserRelVO extends AbstractBaseIdVO {

    @Schema(description = "用户id")
    private Long userId;
    
    @Schema(description = "部门树id")
    private Long deptTreeId;
    


}
