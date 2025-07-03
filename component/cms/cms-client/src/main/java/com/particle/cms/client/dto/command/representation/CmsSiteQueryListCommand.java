package com.particle.cms.client.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 站点 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:04
 */
@Data
@Schema
public class CmsSiteQueryListCommand extends AbstractBaseQueryCommand {



    @Like
    @Schema(description = "站点编码,左前缀匹配")
    private String code;

    @Like
    @Schema(description = "站点名称,左前缀匹配")
    private String name;


    @Schema(description = "站点域名")
    private String domain;


    @Schema(description = "站点访问上下文路径")
    private String path;


    @Schema(description = "站点模板路径")
    private String templatePath;


    @Schema(description = "站点首页模板")
    private String templateIndex;


    @Schema(description = "站点静态页存放路径")
    private String staticPath;


    @Schema(description = "是否主站点")
    private Boolean isPrimeSite;


    @Schema(description = "页面访问量")
    private Integer pv;


    @Schema(description = "页面访问ip数")
    private Integer iv;


    @Schema(description = "页面访问用户数")
    private Integer uv;









}
