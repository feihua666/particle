package com.particle.cms.client.dto.data.dynamic;

import com.particle.cms.client.dto.data.CmsContentCategoryVO;
import com.particle.cms.client.dto.data.CmsContentVO;
import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 内容分类 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:16
 */
@Data
@Schema
public class CmsContentCategoryTemplateModelVO extends AbstractBaseIdVO {

    @Schema(description = "站点id")
    private Long cmsSiteId;

    @Schema(description = "栏目id")
    private Long cmsChannelId;

    @Schema(description = "分类名称")
    private String name;

    @Schema(description = "图片地址")
    private String imageUrl;

    @Schema(description = "图片描述")
    private String imageDescription;

    @Schema(description = "图片地址1")
    private String imageUrl1;

    @Schema(description = "图片描述1")
    private String imageDescription1;

    @Schema(description = "图片地址2")
    private String imageUrl2;

    @Schema(description = "图片描述2")
    private String imageDescription2;

    @Schema(description = "排序")
    private Integer seq;


    public static CmsContentCategoryTemplateModelVO createByCmsContentCategoryVO(CmsContentCategoryVO cmsContentCategoryVO) {
        CmsContentCategoryTemplateModelVO cmsContentTemplateModelVO = new CmsContentCategoryTemplateModelVO();
        cmsContentTemplateModelVO.setId(cmsContentCategoryVO.getId());
        cmsContentTemplateModelVO.setCmsSiteId(cmsContentCategoryVO.getCmsSiteId());
        cmsContentTemplateModelVO.setCmsChannelId(cmsContentCategoryVO.getCmsChannelId());
        cmsContentTemplateModelVO.setName(cmsContentCategoryVO.getName());
        cmsContentTemplateModelVO.setImageUrl(cmsContentCategoryVO.getImageUrl());
        cmsContentTemplateModelVO.setImageDescription(cmsContentCategoryVO.getImageDescription());
        cmsContentTemplateModelVO.setImageUrl1(cmsContentCategoryVO.getImageUrl1());
        cmsContentTemplateModelVO.setImageDescription1(cmsContentCategoryVO.getImageDescription1());
        cmsContentTemplateModelVO.setImageUrl2(cmsContentCategoryVO.getImageUrl2());
        cmsContentTemplateModelVO.setImageDescription2(cmsContentCategoryVO.getImageDescription2());

        cmsContentTemplateModelVO.setSeq(cmsContentCategoryVO.getSeq());
        return cmsContentTemplateModelVO;
    }
}
