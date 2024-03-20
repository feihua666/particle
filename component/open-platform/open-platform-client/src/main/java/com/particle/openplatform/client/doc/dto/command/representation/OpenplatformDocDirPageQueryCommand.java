package com.particle.openplatform.client.doc.dto.command.representation;
import com.particle.common.client.dto.command.tree.AbstractBaseTreePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import com.particle.global.light.share.mybatis.anno.OrderBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 开放接口目录 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:55:42
 */
@OrderBy("seq")
@Data
@Schema
public class OpenplatformDocDirPageQueryCommand extends AbstractBaseTreePageQueryCommand {



    @Like
    @Schema(description = "名称")
    private String name;

    @Like
    @Schema(description = "简称")
    private String nameSimple;


    @Schema(description = "开放接口文档目录名称id")
    private Long openplatformDocDirNameId;


    @Schema(description = "开放接口文档目录名称编码")
    private String openplatformDocDirNameCode;

    @Schema(description = "排序")
    private Integer seq;





















}
