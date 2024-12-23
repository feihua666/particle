package com.particle.navigation.client.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * <p>
 * 导航提交 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-11-03 11:09:19
 */
@Data
@Schema
public class NavigationSubmitVO extends AbstractBaseIdVO {

    @Schema(description = "网站名称")
    private String name;

    @Schema(description = "网站标题")
    private String title;

    @Schema(description = "网站地址")
    private String url;

    @Schema(description = "提交时间")
    private LocalDateTime submitAt;

    @Schema(description = "状态")
    private Long statusDictId;

	@Schema(description = "网站数据json，数据补充时先补充到这里，最后提交到网站表中")
	private String siteDataJson;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "statusDictId",mapValueField = "name")
    @Schema(description = "状态对应字典名称")
    private String statusDictName;

    @Schema(description = "描述")
    private String remark;



}
