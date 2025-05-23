package com.particle.dept.client.deptuserrel.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 部门用户关系 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-05-17 10:28:42
 */
@Data
@Schema
public class DeptUserRelPageQueryCommand extends AbstractBasePageQueryCommand {



    @Schema(description = "用户id")
    private Long userId;


    @Schema(description = "部门id")
    private Long deptId;









}
