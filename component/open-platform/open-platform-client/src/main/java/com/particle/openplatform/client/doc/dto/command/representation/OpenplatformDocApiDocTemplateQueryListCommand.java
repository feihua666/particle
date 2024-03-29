package com.particle.openplatform.client.doc.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.mybatis.anno.Like;
/**
 * <p>
 * 开放接口文档模板 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:48:39
 */
@Data
@Schema
public class OpenplatformDocApiDocTemplateQueryListCommand extends AbstractBaseQueryCommand {



    @Like
        @Schema(description = "模板名称,左前缀匹配")
    private String name;


    @Like
        @Schema(description = "请求地址前缀,左前缀匹配")
    private String requestUrlPrefix;


    @Schema(description = "请求类型")
    private Long requestTypeDictId;


    @Schema(description = "请求体类型")
    private Long requestBodyTypeDictId;


    @Schema(description = "响应体类型")
    private Long responseBodyTypeDictId;


    @Schema(description = "最大响应时长")
    private Integer responseMaxDuration;


    @Schema(description = "响应时长文本")
    private String responseMaxDurationDesc;


    @Schema(description = "认证方式")
    private String authenticationType;









}
