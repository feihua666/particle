package com.particle.cms.client.dto.data.dynamic;

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

    @Schema(description = "站点域名")
    private String domain;

    @Schema(description = "部署路径，主要用于动态页前端静态页面路径访问")
    private String deployPath;

    @Schema(description = "站点访问上下文路径")
    private String path;

    @Schema(description = "站点模板路径")
    private String templatePath;

    @Schema(description = "站点首页模板")
    private String templateIndex;

    @Schema(description = "404模板路径")
    private String template404Path;

    @Schema(description = "404内容模板,默认404.html")
    private String template404Index;

    @Schema(description = "站点静态页存放路径")
    private String staticPath;

    @Schema(description = "是否主站点")
    private Boolean isPrimeSite;

    @Schema(description = "页面访问量")
    private Integer pv;

    @Schema(description = "页面访问ip数")
    private Integer iv;

    @Schema(description = "页面访问用户数")
    private Integer uv;

    /**
     * 用于页面访问静态资源
     */
    @Schema(description = "静态资源路径")
    private String staticResourcePath;


    public static CmsSiteTemplateModelVO createByCmsSiteVO(CmsSiteVO cmsSiteVO){
        CmsSiteTemplateModelVO cmsSiteTemplateModelVO = new CmsSiteTemplateModelVO();
        cmsSiteTemplateModelVO.setId(cmsSiteVO.getId());
        cmsSiteTemplateModelVO.setCode(cmsSiteVO.getCode());
        cmsSiteTemplateModelVO.setName(cmsSiteVO.getName());
        cmsSiteTemplateModelVO.setDomain(cmsSiteVO.getDomain());
        cmsSiteTemplateModelVO.setDeployPath(cmsSiteVO.getDeployPath());
        cmsSiteTemplateModelVO.setPath(cmsSiteVO.getPath());
        cmsSiteTemplateModelVO.setTemplatePath(cmsSiteVO.getTemplatePath());
        cmsSiteTemplateModelVO.setTemplateIndex(cmsSiteVO.getTemplateIndex());
        cmsSiteTemplateModelVO.setTemplate404Path(cmsSiteVO.getTemplate404Path());
        cmsSiteTemplateModelVO.setTemplate404Index(cmsSiteVO.getTemplate404Index());
        cmsSiteTemplateModelVO.setStaticPath(cmsSiteVO.getStaticPath());
        cmsSiteTemplateModelVO.setIsPrimeSite(cmsSiteVO.getIsPrimeSite());
        cmsSiteTemplateModelVO.setPv(cmsSiteVO.getPv());
        cmsSiteTemplateModelVO.setIv(cmsSiteVO.getIv());
        cmsSiteTemplateModelVO.setUv(cmsSiteVO.getUv());


        cmsSiteTemplateModelVO.setStaticResourcePath(cmsSiteVO.getDeployPath());
        return cmsSiteTemplateModelVO;

    }
}
