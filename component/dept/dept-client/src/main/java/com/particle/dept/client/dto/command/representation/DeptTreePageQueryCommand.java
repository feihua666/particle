package com.particle.dept.client.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.common.client.dto.command.tree.AbstractBaseTreePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * <p>
 * 部门树 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-12 11:41:43
 */
@Data
@ApiModel
public class DeptTreePageQueryCommand extends AbstractBaseTreePageQueryCommand {



    @ApiModelProperty(value = "部门id")
    private Long deptId;


    @ApiModelProperty(value = "部门树名称id")
    private Long deptTreeNameId;






















}
