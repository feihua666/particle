package com.particle.cms.client.dto.data.dynamic;

import com.particle.cms.client.dto.data.CmsContentMultimediaVO;
import com.particle.cms.client.dto.data.CmsContentVO;
import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 内容多媒体 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-07-08 16:32:29
 */
@Data
@Schema
public class CmsContentMultimediaTemplateModelVO extends AbstractBaseIdVO {

    @Schema(description = "站点id")
    private Long cmsSiteId;

    @Schema(description = "内容id")
    private Long cmsContentId;

    @Schema(description = "文本内容")
    private String content;

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

    @Schema(description = "文件名称")
    private String fileName;

    @Schema(description = "文件地址")
    private String fileUrl;

    @Schema(description = "文件大小")
    private Integer fileSize;

    @Schema(description = "文件大小字符串")
    private String fileSizeStr;

    @Schema(description = "媒体类型")
    private String mediaType;

    @Schema(description = "排序")
    private Integer seq;



    public static CmsContentMultimediaTemplateModelVO createByCmsContentMultimediaVO(CmsContentMultimediaVO cmsContentMultimediaVO) {
        CmsContentMultimediaTemplateModelVO cmsContentTemplateModelVO = new CmsContentMultimediaTemplateModelVO();
        cmsContentTemplateModelVO.setId(cmsContentMultimediaVO.getId());
        cmsContentTemplateModelVO.setCmsSiteId(cmsContentMultimediaVO.getCmsSiteId());
        cmsContentTemplateModelVO.setCmsContentId(cmsContentMultimediaVO.getCmsContentId());
        cmsContentTemplateModelVO.setContent(cmsContentMultimediaVO.getContent());
        cmsContentTemplateModelVO.setImageUrl(cmsContentMultimediaVO.getImageUrl());
        cmsContentTemplateModelVO.setImageDescription(cmsContentMultimediaVO.getImageDescription());
        cmsContentTemplateModelVO.setImageUrl1(cmsContentMultimediaVO.getImageUrl1());
        cmsContentTemplateModelVO.setImageDescription1(cmsContentMultimediaVO.getImageDescription1());
        cmsContentTemplateModelVO.setImageUrl2(cmsContentMultimediaVO.getImageUrl2());
        cmsContentTemplateModelVO.setImageDescription2(cmsContentMultimediaVO.getImageDescription2());
        cmsContentTemplateModelVO.setFileName(cmsContentMultimediaVO.getFileName());
        cmsContentTemplateModelVO.setFileUrl(cmsContentMultimediaVO.getFileUrl());
        cmsContentTemplateModelVO.setFileSize(cmsContentMultimediaVO.getFileSize());
        cmsContentTemplateModelVO.setFileSizeStr(cmsContentMultimediaVO.getFileSizeStr());
        cmsContentTemplateModelVO.setMediaType(cmsContentMultimediaVO.getMediaType());
        cmsContentTemplateModelVO.setSeq(cmsContentMultimediaVO.getSeq());
        return cmsContentTemplateModelVO;
    }
}
