package com.particle.openplatform.client.doc.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import com.particle.global.light.share.mybatis.anno.OrderBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 开放接口文档示例代码 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-03-18 17:04:11
 */
@OrderBy("seq")
@Data
@Schema
public class OpenplatformDocApiDocExampleCodeQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "开发语言")
    private Long langDictId;


    @Schema(description = "示例代码片段")
    private String exampleCode;


    @Schema(description = "示例代码下载地址")
    private String exampleDownloadUrl;


    @Schema(description = "开放接口文档接口id")
    private Long openplatformDocApiId;


    @Schema(description = "开放接口文档id")
    private Long openplatformDocApiDocId;

	@Schema(description = "排序,默认按该字段升序排序")
	private Integer seq;


    @Schema(description = "描述")
    private String remark;









}