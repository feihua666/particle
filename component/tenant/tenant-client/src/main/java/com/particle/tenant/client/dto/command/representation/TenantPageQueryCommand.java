package com.particle.tenant.client.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema
public class TenantPageQueryCommand extends AbstractBasePageQueryCommand {

	@Schema(description = "图标,支持base64编码,支持http://开头的图片地址,支持本地文件路径地址，支持classpath文件路径")
	private String tenantFaviconJson;



    @Like
        @Schema(description = "租户编码,左前缀匹配")
    private String code;


    @Like
        @Schema(description = "租户名称,左前缀匹配")
    private String name;




    @Like
        @Schema(description = "姓名,左前缀匹配")
    private String userName;


    @Like
        @Schema(description = "邮箱,左前缀匹配")
    private String email;


    @Like
        @Schema(description = "手机号,左前缀匹配")
    private String mobile;


























}