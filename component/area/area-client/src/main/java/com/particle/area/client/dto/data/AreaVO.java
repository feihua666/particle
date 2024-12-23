package com.particle.area.client.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdTreeVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 区域 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2022-07-18
 */
@Data
@Schema
public class AreaVO extends AbstractBaseIdTreeVO {


    @Schema(description = "编码，唯一,模糊查询")
    private String code;

    @Schema(description = "区域名称,模糊查询")
    private String name;

    @Schema(description = "区域名称,模糊查询")
    private String nameSimple;

    @Schema(description = "第一个字的首字母")
    private String spellFirst;

    @Schema(description = "每个字的首字母")
    private String spellSimple;

    @Schema(description = "全拼")
    private String spell;

    @Schema(description = "类型，字典id")
    private Long typeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "typeDictId",mapValueField = "name")
    @Schema(description = "类型，字典名称")
    private String typeDictName;

    @Schema(description = "经度")
    private String longitude;

    @Schema(description = "纬度")
    private String latitude;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "排序,默认按该字段升序排序")
    private Integer seq;

    @Schema(description = "父级名称")
    @TransBy(tableName = TransTableNameConstants.component_area, byFieldName = "parentId", mapValueField = "name")
    private String parentName;
}
