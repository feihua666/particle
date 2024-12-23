package com.particle.dept.client.depttreeuserrel.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 部门树用户关系 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-05-17 10:26:06
 */
@Data
@Schema
public class DeptTreeUserRelPageQueryCommand extends AbstractBasePageQueryCommand {



    @Schema(description = "用户id")
    private Long userId;


    @Schema(description = "部门树id")
    private Long deptTreeId;









}
