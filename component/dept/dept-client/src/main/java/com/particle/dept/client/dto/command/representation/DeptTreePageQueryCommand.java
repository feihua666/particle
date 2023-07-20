package com.particle.dept.client.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.common.client.dto.command.tree.AbstractBaseTreePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema
public class DeptTreePageQueryCommand extends AbstractBaseTreePageQueryCommand {



    @Schema(description = "部门id")
    private Long deptId;


    @Schema(description = "部门树名称id")
    private Long deptTreeNameId;






















}
