package com.particle.tenant.client.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.particle.global.light.share.mybatis.anno.Like;
/**
 * <p>
 * 租户 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-11 22:25:27
 */
@Data
@ApiModel
public class TenantPageQueryCommand extends AbstractBasePageQueryCommand {



    @Like
        @ApiModelProperty(value = "租户编码,左前缀匹配")
    private String code;


    @Like
        @ApiModelProperty(value = "租户名称,左前缀匹配")
    private String name;




    @Like
        @ApiModelProperty(value = "联系人姓名,左前缀匹配")
    private String contactUserName;


    @Like
        @ApiModelProperty(value = "联系人邮箱,左前缀匹配")
    private String contactUserEmail;


    @Like
        @ApiModelProperty(value = "联系人电话,左前缀匹配")
    private String contactUserPhone;


























}