package com.particle.cms.client.dto.data.dynamic;

import cn.hutool.core.util.StrUtil;
import com.particle.cms.client.constants.CmsConstants;
import com.particle.cms.client.dto.data.CmsChannelVO;
import com.particle.common.client.dto.data.AbstractBaseIdTreeVO;
import com.particle.global.tool.priority.PriorityProcessTool;
import com.particle.global.tool.str.NetPathTool;
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

    @Schema(description = "网页标题，用于页面标题")
    private String title;

	@Schema(description = "栏目访问上下文路径，主要应用于动态页访问，可以实现在一个站点下不同的栏目")
	private String channelContextPath;

    @Schema(description = "栏目模板路径")
    private String templatePath;

    @Schema(description = "栏目模板")
    private String templateIndex;

    @Schema(description = "栏目静态页存放路径")
    private String staticSavePath;


    @Schema(description = "简介")
    private String profile;

    @Schema(description = "页面访问量")
    private Integer pv;

    @Schema(description = "页面访问ip数")
    private Integer iv;

    @Schema(description = "页面访问用户数")
    private Integer uv;


    @Schema(description = "关联的内容id,适用点击栏目访问内容详情的场景")
    private Long relatedCmsContentId;

    @Schema(description = "自定义url,适用于点击栏目访问自定义url的场景")
    private String customUrl;

    @Schema(description = "排序")
    private Integer seq;

    /***** 下面中额外添加字段 ******/

    @Schema(description = "额外上下文数据")
    private CmsTemplateModelContext context;
    /**
     * 首页地址
     * 如果是动态站点，首页地址为 domain + deployPath + path + index.htm。如：http://www.example.com/cms/index.htm
     */
    @Schema(description = "首页地址")
    private String indexUrl;

    /**
     * 根路径
     * 如果是动态站点，根路径为 domain + deployPath + path。如：http://www.example.com/cms
     */
    @Schema(description = "根路径")
    private String rootPath;
    /**
     * 最终标题，用于页面标题
     */
    @Schema(description = "最终标题，用于页面标题")
    private String finalTitle;

    @Schema(description = "用来标识是否为内容栏目")
    private Boolean isContentChannel = false;

    /**
     * 初始化
     * @param cmsSiteTemplateModelVO
     * @param relatedContentTemplateModelVO
     * @param ChannelCmsContentTemplateModelVO
     */
    private void init(CmsSiteTemplateModelVO cmsSiteTemplateModelVO,
                      CmsContentTemplateModelVO relatedContentTemplateModelVO,
                      CmsContentTemplateModelVO ChannelCmsContentTemplateModelVO,
                      boolean isPreview) {
        Boolean isDynamic = context.getIsDynamic();
        if (isDynamic) {
            rootPath = NetPathTool.concat(cmsSiteTemplateModelVO.getRootPath(),
                    StrUtil.emptyToDefault(channelContextPath, CmsConstants.requestChannelPathPrefix),
                    getId() + "");

            // 按顺序处理，第一个不为空的，就使用它
            indexUrl = PriorityProcessTool.<String>create()
                    .add(()->StrUtil.isNotEmpty(customUrl) ,() -> customUrl)
                    .add(()-> relatedContentTemplateModelVO != null,() -> relatedContentTemplateModelVO.getIndexUrl())
                    .add(()-> ChannelCmsContentTemplateModelVO != null,() -> ChannelCmsContentTemplateModelVO.getIndexUrl())
                    .withDefault(()-> NetPathTool.concat(rootPath, isPreview ? CmsConstants.indexDothtmp :CmsConstants.indexDothtm))
                    .execute();

        }else{
            // todo 静态资源路径
        }
        finalTitle = StrUtil.isBlank(title) ? name : title;
    }

    /**
     * 创建内容栏目模板模型对象
     * @param cmsChannelVO
     * @param cmsSiteTemplateModelVO
     * @param relatedContentTemplateModelVO 注意是：栏目关联的内容
     * @param context
     * @return
     */
    public static CmsChannelTemplateModelVO createByCmsChannelVO(CmsChannelVO cmsChannelVO,
                                                                 CmsSiteTemplateModelVO cmsSiteTemplateModelVO,
                                                                 CmsContentTemplateModelVO relatedContentTemplateModelVO,
                                                                 CmsTemplateModelContext context){

        if (cmsChannelVO == null) {
            return null;
        }
        CmsChannelTemplateModelVO vo = new CmsChannelTemplateModelVO();
        vo.setId(cmsChannelVO.getId());



        vo.setCmsSiteId(cmsChannelVO.getCmsSiteId());
        vo.setName(cmsChannelVO.getName());
        vo.setCode(cmsChannelVO.getCode());
        vo.setTitle(cmsChannelVO.getTitle());
        vo.setChannelContextPath(cmsChannelVO.getChannelContextPath());

        vo.setTemplatePath(cmsChannelVO.getTemplatePath());
        vo.setTemplateIndex(cmsChannelVO.getTemplateIndex());
        vo.setStaticSavePath(cmsChannelVO.getStaticSavePath());
        vo.setProfile(cmsChannelVO.getProfile());

        vo.setPv(cmsChannelVO.getPv() + cmsChannelVO.getInitPv());
        vo.setIv(cmsChannelVO.getIv());
        vo.setUv(cmsChannelVO.getUv());

        vo.setRelatedCmsContentId(cmsChannelVO.getRelatedCmsContentId());
        vo.setCustomUrl(cmsChannelVO.getCustomUrl());

        vo.setSeq(cmsChannelVO.getSeq());

        vo.setParentId(cmsChannelVO.getParentId());
        vo.setParentId1(cmsChannelVO.getParentId1());
        vo.setParentId2(cmsChannelVO.getParentId2());
        vo.setParentId3(cmsChannelVO.getParentId3());
        vo.setParentId4(cmsChannelVO.getParentId4());
        vo.setParentId5(cmsChannelVO.getParentId5());
        vo.setParentId6(cmsChannelVO.getParentId6());
        vo.setParentId7(cmsChannelVO.getParentId7());
        vo.setParentId8(cmsChannelVO.getParentId8());
        vo.setParentId9(cmsChannelVO.getParentId9());
        vo.setParentId10(cmsChannelVO.getParentId10());
        vo.setLevel(cmsChannelVO.getLevel());
        vo.setVersion(cmsChannelVO.getVersion());


        vo.setIsContentChannel(false);

        vo.setContext(context);

        vo.init(cmsSiteTemplateModelVO,
                relatedContentTemplateModelVO,
                null,
                context.getMode() == CmsTemplateModelContext.Mode.preview);

        return vo;

    }
    /**
     * 创建内容栏目模板模型对象
     * 将内容作为栏目使用时，使用该方法创建
     * @param cmsSiteTemplateModelVO
     * @param ChannelCmsContentTemplateModelVO 内容栏目模板模型对象
     * @return
     */
    public static CmsChannelTemplateModelVO createByChannelCmsContentTemplateModelVO(
                                                                    CmsSiteTemplateModelVO cmsSiteTemplateModelVO,
                                                                    CmsContentTemplateModelVO ChannelCmsContentTemplateModelVO,
                                                                    CmsTemplateModelContext context
                                                                              ){
        CmsChannelTemplateModelVO vo = new CmsChannelTemplateModelVO();

        vo.setName(ChannelCmsContentTemplateModelVO.getTitle());
        vo.setSeq(ChannelCmsContentTemplateModelVO.getAlsoAsChannelSeq());
        vo.setIsContentChannel(true);

        vo.setContext(context);

        vo.init(cmsSiteTemplateModelVO,
                null,
                ChannelCmsContentTemplateModelVO,
                context.getMode() == CmsTemplateModelContext.Mode.preview);

        return vo;
    }

}
