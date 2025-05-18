package com.particle.dict.client.dto.data;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONNull;
import cn.hutool.json.JSONUtil;
import com.particle.common.client.dto.data.AbstractBaseIdTreeVO;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 字典 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Data
@Schema
public class DictVO extends AbstractBaseIdTreeVO {


    @Schema(description = "字典编码,模糊查询，字典组时必填")
    private String code;

    @Schema(description = "字典名称,模糊查询")
    private String name;

    @Schema(description = "字典值,模糊查询")
    private String value;

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

    @Schema(description = "禁用原因")
    private String disabledReason;

    @Schema(description = "私有标识,模糊查询")
    private String privateFlag;

    @Schema(description = "私有标识备忘")
    private String privateFlagMemo;

    @Schema(description = "分组标识")
    private String groupFlag;

    @Schema(description = "分组标识备忘")
    private String groupFlagMemo;

    @Schema(description = "标签，多个以逗号分隔，用来区分字典项")
    private String tags;

	@Schema(description = "关联字典组编码，用于在字典项下还有字典项的扩展场景")
	private String relatedGroupCode;

	@Schema(description = "映射数组json，用于三方系统对接场景")
	private String mappingArrayJson;

    @Schema(description = "描述")
    private String remark;

    @Schema(description = "排序,默认按该字段升序排序")
    private Integer seq;

    @Schema(description = "父级名称")
    @TransBy(tableName = TransTableNameConstants.component_dict, byFieldName = "parentId", mapValueField = "name")
    private String parentName;

    public List<DictMappingArrayJsonVO> obtainDictMappingArrayJsonVOs() {
        if (StrUtil.isEmpty(mappingArrayJson)) {
            return null;
        }
        return JSONUtil.toList(mappingArrayJson, DictMappingArrayJsonVO.class);
    }

    public boolean mappingByMappingValue(String mappingValue) {
        List<DictMappingArrayJsonVO> dictMappingArrayJsonVOS = obtainDictMappingArrayJsonVOs();
        if (CollectionUtil.isEmpty(dictMappingArrayJsonVOS)) {
            return false;
        }
        for (DictMappingArrayJsonVO dictMappingArrayJsonVO : dictMappingArrayJsonVOS) {
            if (dictMappingArrayJsonVO.match(mappingValue)) {
                return true;
            }
        }
        return false;
    }
}
