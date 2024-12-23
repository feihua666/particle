package com.particle.dept.client.dto.command.representation;

import com.particle.common.client.dto.command.tree.AbstractBaseTreePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 部门 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-12 14:19:42
 */
@Data
@Schema
public class DeptPageQueryCommand extends AbstractBaseTreePageQueryCommand {



    @Like
    @Schema(description = "部门编码,左前缀匹配")
    private String code;


    @Like
    @Schema(description = "部门名称,左前缀匹配")
    private String name;


    @Schema(description = "类型")
    private Long typeDictId;


    @Schema(description = "负责人用户id")
    private Long masterUserId;


    @Schema(description = "是否虚拟部门")
    private Boolean isVirtual;


    @Schema(description = "是否为公司")
    private Boolean isComp;


}
