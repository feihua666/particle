package com.particle.navigation.client.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 导航网站标签 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-11-07 09:38:23
 */
@Data
@Schema
public class NavigationSiteTagVO extends AbstractBaseIdVO {

    @Schema(description = "标签编码")
    private String code;

    @Schema(description = "标签名称")
    private String name;

    @Schema(description = "分组")
    private Long groupDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "groupDictId",mapValueField = "name")
    @Schema(description = "分组对应字典名称")
    private String groupDictName;

    @Schema(description = "排序")
    private Integer seq;

    @Schema(description = "备注")
    private String remark;



}
