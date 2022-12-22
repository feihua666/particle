package com.particle.area.client.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdTreeVO;
import com.particle.global.light.share.trans.TransConstants;
import com.particle.global.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel
public class AreaVO extends AbstractBaseIdTreeVO {


    @ApiModelProperty("编码，唯一,模糊查询")
    private String code;

    @ApiModelProperty("区域名称,模糊查询")
    private String name;

    @ApiModelProperty("区域名称,模糊查询")
    private String nameSimple;

    @ApiModelProperty("第一个字的首字母")
    private String spellFirst;

    @ApiModelProperty("每个字的首字母")
    private String spellSimple;

    @ApiModelProperty("全拼")
    private String spell;

    @ApiModelProperty("类型，字典id")
    private Long typeDictId;

    @TransBy(tableName = TransConstants.TRANS_DICT_BY_ID,byFieldName = "typeDictId",mapValueField = "name")
    @ApiModelProperty("类型，字典名称")
    private String typeDictName;

    @ApiModelProperty("经度")
    private String longitude;

    @ApiModelProperty("纬度")
    private String latitude;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("排序,默认按该字段升序排序")
    private Integer seq;

    @ApiModelProperty("父级名称")
    @TransBy(tableName = TransTableNameConstants.component_area, byFieldName = "parentId", mapValueField = "name")
    private String parentName;
}
