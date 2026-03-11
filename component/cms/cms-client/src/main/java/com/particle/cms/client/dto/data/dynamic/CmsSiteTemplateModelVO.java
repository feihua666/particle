package com.particle.cms.client.dto.data.dynamic;

import cn.hutool.core.util.StrUtil;
import com.particle.cms.client.constants.CmsConstants;
import com.particle.cms.client.dto.data.CmsSiteVO;
import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.global.tool.str.NetPathTool;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 站点 模型响应对象
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:04
 */
@Data
@Schema
public class CmsSiteTemplateModelVO extends AbstractBaseIdVO {

    @Schema(description = "站点编码")
    private String code;

    @Schema(description = "站点名称")
    private String name;

    @Schema(description = "网页标题，用于页面标题")
    private String title;

    @Schema(description = "站点域名")
    private String domain;

    @Schema(description = "站点动态外部域名")
    private String dynamicDomain;

    @Schema(description = "动态访问部署路径，主要用于页面组织访问链接路径")
    private String dynamicDeployPath;

    @Schema(description = "站点访问上下文路径")
    private String siteContextPath;

    @Schema(description = "站点模板路径")
    private String templatePath;

    @Schema(description = "站点首页模板")
    private String templateIndex;

    @Schema(description = "404模板路径")
    private String template404Path;

    @Schema(description = "404内容模板,默认404.html")
    private String template404Index;

    @Schema(description = "是否主站点")
    private Boolean isPrimeSite;

    @Schema(description = "简介")
    private String profile;

    @Schema(description = "页面访问量")
    private Integer pv;

    @Schema(description = "页面访问ip数")
    private Integer iv;

    @Schema(description = "页面访问用户数")
    private Integer uv;


    /***** 下面中额外添加字段 ******/

    @Schema(description = "额外上下文数据")
    private CmsTemplateModelContext context;

    /**
     * 用于页面访问静态资源
     */
    @Schema(description = "静态资源路径")
    private String staticResourcePath;

    /**
     * 首页地址
     * 如果是动态站点，首页地址为 domain + deployPath + path + index.htm。如：http://www.example.com/cms/index.htm
     */
    @Schema(description = "首页地址")
    private String indexUrl;

    @Schema(description = "根部署路径")
    private String rootDeployPath;
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
    /**
     * 初始化
     */
    private void init(boolean isPreview) {
        Boolean isDynamic = context.getIsDynamic();
        if (isDynamic) {
            rootDeployPath = NetPathTool.concat(dynamicDomain, dynamicDeployPath);
            rootPath = NetPathTool.concat(rootDeployPath,CmsConstants.requestPathPrefix, siteContextPath);
            indexUrl = NetPathTool.concat(rootPath, isPreview ? CmsConstants.indexDothtmp :CmsConstants.indexDothtm);
        }else{
            // todo 静态资源路径
        }
        finalTitle = StrUtil.isBlank(title) ? name : title;

    }

    public static CmsSiteTemplateModelVO createByCmsSiteVO(CmsSiteVO cmsSiteVO,CmsTemplateModelContext context){
        CmsSiteTemplateModelVO cmsSiteTemplateModelVO = new CmsSiteTemplateModelVO();
        cmsSiteTemplateModelVO.setId(cmsSiteVO.getId());
        cmsSiteTemplateModelVO.setCode(cmsSiteVO.getCode());
        cmsSiteTemplateModelVO.setName(cmsSiteVO.getName());
        cmsSiteTemplateModelVO.setTitle(cmsSiteVO.getTitle());

        cmsSiteTemplateModelVO.setDomain(cmsSiteVO.getDomain());
        cmsSiteTemplateModelVO.setDynamicDomain(cmsSiteVO.getDynamicDomain());
        cmsSiteTemplateModelVO.setDynamicDeployPath(cmsSiteVO.getDynamicDeployPath());
        cmsSiteTemplateModelVO.setSiteContextPath(cmsSiteVO.getSiteContextPath());
        cmsSiteTemplateModelVO.setTemplatePath(cmsSiteVO.getTemplatePath());
        cmsSiteTemplateModelVO.setTemplateIndex(cmsSiteVO.getTemplateIndex());
        cmsSiteTemplateModelVO.setTemplate404Path(cmsSiteVO.getTemplate404Path());
        cmsSiteTemplateModelVO.setTemplate404Index(cmsSiteVO.getTemplate404Index());

        cmsSiteTemplateModelVO.setIsPrimeSite(cmsSiteVO.getIsPrimeSite());
        cmsSiteTemplateModelVO.setProfile(cmsSiteVO.getProfile());

        cmsSiteTemplateModelVO.setPv(cmsSiteVO.getPv() + cmsSiteVO.getInitPv());
        cmsSiteTemplateModelVO.setIv(cmsSiteVO.getIv());
        cmsSiteTemplateModelVO.setUv(cmsSiteVO.getUv());


        cmsSiteTemplateModelVO.setContext(context);

        cmsSiteTemplateModelVO.init(context.getMode() == CmsTemplateModelContext.Mode.preview);
        return cmsSiteTemplateModelVO;

    }
}
