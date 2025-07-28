package com.particle.cms.client.dto.data.dynamic;

import com.particle.cms.client.dto.data.CmsChannelVO;
import com.particle.common.client.dto.data.AbstractBaseIdTreeVO;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 栏目 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:55
 */
@Data
@Schema
public class CmsChannelTemplateModelVO extends AbstractBaseIdTreeVO {

    @Schema(description = "站点id")
    private Long cmsSiteId;

    @Schema(description = "栏目编码")
    private String code;

    @Schema(description = "栏目名称")
    private String name;

	@Schema(description = "栏目访问上下文路径，主要应用于动态页访问，可以实现在一个站点下不同的栏目")
	private String path;

    @Schema(description = "栏目模板路径")
    private String templatePath;

    @Schema(description = "栏目模板")
    private String templateIndex;

    @Schema(description = "栏目静态页存放路径")
    private String staticPath;

    @Schema(description = "页面访问量")
    private Integer pv;

    @Schema(description = "页面访问ip数")
    private Integer iv;

    @Schema(description = "页面访问用户数")
    private Integer uv;

    @Schema(description = "排序")
    private Integer seq;


    public static CmsChannelTemplateModelVO createByCmsChannelVO(CmsChannelVO cmsChannelVO){
        CmsChannelTemplateModelVO vo = new CmsChannelTemplateModelVO();
        vo.setId(cmsChannelVO.getId());
        vo.setCmsSiteId(cmsChannelVO.getCmsSiteId());
        vo.setName(cmsChannelVO.getName());
        vo.setCode(cmsChannelVO.getCode());
        vo.setPath(cmsChannelVO.getPath());
        vo.setTemplatePath(cmsChannelVO.getTemplatePath());
        vo.setTemplateIndex(cmsChannelVO.getTemplateIndex());
        vo.setStaticPath(cmsChannelVO.getStaticPath());
        vo.setPv(cmsChannelVO.getPv());
        vo.setIv(cmsChannelVO.getIv());
        vo.setUv(cmsChannelVO.getUv());
        vo.setSeq(cmsChannelVO.getSeq());

        vo.setParentId(cmsChannelVO.getParentId());
        vo.setLevel(cmsChannelVO.getLevel());
        vo.setVersion(cmsChannelVO.getVersion());

        return vo;

    }

}
