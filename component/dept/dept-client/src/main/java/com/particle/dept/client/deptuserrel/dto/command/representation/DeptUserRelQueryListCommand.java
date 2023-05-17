package com.particle.dept.client.deptuserrel.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * <p>
 * 部门用户关系 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-05-17 10:28:42
 */
@Data
@ApiModel
public class DeptUserRelQueryListCommand extends AbstractBaseQueryCommand {



    @ApiModelProperty(value = "用户id")
    private Long userId;


    @ApiModelProperty(value = "部门id")
    private Long deptId;









}
