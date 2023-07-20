package com.particle.tracking.client.dto.command.representation;

import com.particle.common.client.dto.command.tree.AbstractBaseTreeQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import com.particle.global.light.share.mybatis.anno.OrderBy;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 埋点页面 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:39:06
 */
@OrderBy("seq")
@Data
@Schema
public class TrackingPageQueryListCommand extends AbstractBaseTreeQueryCommand {



    @Like
    @Schema(description = "页面编码,左前缀匹配")
    private String code;

    @Like
    @Schema(description = "页面名称,左前缀匹配")
    private String name;

    @Schema(description = "页面版本")
    private String pageVersion;

    @Like
    @Schema(description = "分组标识,左前缀匹配")
    private String groupFlag;

}
