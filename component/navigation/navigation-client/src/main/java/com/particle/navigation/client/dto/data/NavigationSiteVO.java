package com.particle.navigation.client.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import com.particle.component.light.share.trans.TransConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
/**
 * <p>
 * 导航网站 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:34:56
 */
@Data
@Schema
public class NavigationSiteVO extends AbstractBaseIdVO {

    @Schema(description = "网站名称")
    private String name;

    @Schema(description = "网站标题")
    private String title;

    @Schema(description = "网站logo图标地址")
    private String logoUrl;

    @Schema(description = "网站地址")
    private String url;

    @Schema(description = "网站截屏地址")
    private String screenshotUrl;

    @Schema(description = "网站截屏缩略图地址")
    private String screenshotThumbnailUrl;

    @Schema(description = "简短描述")
    private String content;

    @Schema(description = "详细描述")
    private String contentDetail;

	@Schema(description = "收费情况，字典id")
	private Long feeSituationDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "feeSituationDictId",mapValueField = "name")
    @Schema(description = "收费情况，字典名称")
    private String feeSituationDictName;

	@Schema(description = "收录时间")
	private LocalDateTime collectionAt;

    @Schema(description = "描述")
    private String remark;



}
