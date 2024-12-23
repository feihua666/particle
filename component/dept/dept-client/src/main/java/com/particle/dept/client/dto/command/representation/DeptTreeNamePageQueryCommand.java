package com.particle.dept.client.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 部门树名称 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-12 11:42:10
 */
@Data
@Schema
public class DeptTreeNamePageQueryCommand extends AbstractBasePageQueryCommand {



    @Like
        @Schema(description = "部门树名称编码,左前缀匹配")
    private String code;


    @Like
        @Schema(description = "部门树名称,左前缀匹配")
    private String name;










}
