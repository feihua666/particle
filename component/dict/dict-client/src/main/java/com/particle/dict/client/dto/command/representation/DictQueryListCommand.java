package com.particle.dict.client.dto.command.representation;

import com.particle.common.client.dto.command.tree.AbstractBaseTreeQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import com.particle.global.light.share.mybatis.anno.OrderBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 字典 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@OrderBy("seq")
@Data
@Schema
public class DictQueryListCommand extends AbstractBaseTreeQueryCommand {



    @Like
    @Schema(description = "字典编码,左前缀匹配")
    private String code;

    @Like
    @Schema(description = "字典名称,左前缀匹配")
    private String name;

    @Schema(description = "是否为系统字典，一般系统字典代码中会做判断，不能修改或删除")
    private Boolean isSystem;

    @Schema(description = "是否为公共字典，如果为公共字典不限制使用，否则按相应数据权限查询")
    private Boolean isPublic;

    @Schema(description = "是否为字典组")
    private Boolean isGroup;

	@Schema(description = "是否为字典项")
	private Boolean isItem;

    @Schema(description = "是否禁用")
    private Boolean isDisabled;

    @Like
    @Schema(description = "私有标识，左前缀匹配")
    private String privateFlag;

    @Like
    @Schema(description = "分组标识，左前缀匹配")
    private String groupFlag;

    @Like
    @Schema(description = "标签，左前缀匹配")
    private String tags;

	@Schema(description = "关联字典组编码，用于在字典项下还有字典项的扩展场景")
	private String relatedGroupCode;

	@Schema(description = "映射数组json，用于三方系统对接场景")
	private String mappingArrayJson;

}