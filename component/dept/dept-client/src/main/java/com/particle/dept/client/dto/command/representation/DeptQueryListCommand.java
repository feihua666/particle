package com.particle.dept.client.dto.command.representation;


import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.common.client.dto.command.tree.AbstractBaseTreeQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.particle.global.light.share.mybatis.anno.Like;
/**
 * <p>
 * 部门 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-12 14:19:42
 */
@Data
@ApiModel
public class DeptQueryListCommand extends AbstractBaseTreeQueryCommand {



    @Like
        @ApiModelProperty(value = "部门编码,左前缀匹配")
    private String code;


    @Like
        @ApiModelProperty(value = "部门名称,左前缀匹配")
    private String name;


    @ApiModelProperty(value = "类型")
    private Long typeDictId;


    @ApiModelProperty(value = "负责人用户id")
    private Long masterUserId;


    @ApiModelProperty(value = "是否虚拟部门")
    private Boolean isVirtual;


    @ApiModelProperty(value = "是否为公司")
    private Boolean isComp;






















}
