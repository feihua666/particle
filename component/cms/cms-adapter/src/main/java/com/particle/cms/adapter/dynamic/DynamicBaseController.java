package com.particle.cms.adapter.dynamic;

import cn.hutool.core.util.StrUtil;
import com.particle.cms.client.api.ICmsDynamicApplicationService;
import com.particle.cms.client.constants.CmsConstants;
import com.particle.cms.client.dto.data.CmsChannelVO;
import com.particle.cms.client.dto.data.CmsContentVO;
import com.particle.cms.client.dto.data.CmsSiteVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.tool.servlet.RequestTool;
import com.particle.global.tool.str.NetPathTool;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * cms 前端页面访问入口
 * Created by yangwei
 */
@Slf4j
@Controller
public class DynamicBaseController {
    public static final String requestPathPrefix = "/cms";

    @Autowired
    protected ICmsDynamicApplicationService iCmsDynamicApplicationService;


    /**
     * 获取站点的模板存在根目录
     * @param cmsSiteVO
     * @return
     */
    protected String getTemplatePathForViewResolver(CmsSiteVO cmsSiteVO){
        String templatePathDefault = CmsConstants.templatePathDefault;
        if(cmsSiteVO != null && StringUtils.isNoneEmpty(cmsSiteVO.getTemplatePath())){
            templatePathDefault = cmsSiteVO.getTemplatePath();
        }

        return NetPathTool.concat(CmsConstants.templateRootPath , templatePathDefault);
    }

    /**
     * 获取模板
     * @param cmsSiteVO
     * @param templatePath
     * @param defaultTemplatePath
     * @param template
     * @param defaultTemplate
     * @return
     */
    protected String getTemplate(CmsSiteVO cmsSiteVO, String templatePath,String defaultTemplatePath, String template,String defaultTemplate) {
        String templatePathForViewResolver = getTemplatePathForViewResolver(cmsSiteVO);

        String tempTemplatePath = templatePath;
        if (StrUtil.isEmpty(tempTemplatePath)) {
            tempTemplatePath = defaultTemplatePath;
        }
        String tempTemplate = template;
        if (StrUtil.isEmpty(tempTemplate)) {
            tempTemplate = defaultTemplate;
        }
        String finalTemplatePath = NetPathTool.concat(templatePathForViewResolver,tempTemplatePath, tempTemplate);
        finalTemplatePath = StrUtil.removeSuffix(finalTemplatePath, CmsConstants.templateSuffix);
        return finalTemplatePath;
    }

    /**
     * 404
     * @param cmsSiteVO
     * @return
     */
    protected String getSite404IndexPath(CmsSiteVO cmsSiteVO){
        String template404Path = CmsConstants.template404Path;
        if(!StringUtils.isEmpty(cmsSiteVO.getTemplate404Path())){
            template404Path = cmsSiteVO.getTemplate404Path();
        }
        String template404Index = CmsConstants.templateIndex404Html;
        if(!StringUtils.isEmpty(cmsSiteVO.getTemplate404Index())){
            template404Index = cmsSiteVO.getTemplate404Index();
        }
        String templatePath = NetPathTool.concat(getTemplatePathForViewResolver(cmsSiteVO),template404Path , template404Index);
        templatePath = StrUtil.removeSuffix(templatePath, CmsConstants.templateSuffix);
        return templatePath;
    }
    /**
     * 根据域名获取站点
     * @param request
     * @return
     */
    protected CmsSiteVO getSiteByDomain(HttpServletRequest request){
        String domain = RequestTool.getDomain(request,true,true);
        // 根据域名查找主站点
        SingleResponse<CmsSiteVO> cmsSiteVOSingleResponse = iCmsDynamicApplicationService.getPrimeSiteByDomain(domain);
        CmsSiteVO cmsSiteVO = cmsSiteVOSingleResponse.getData();
        // 如果没有设置主站点，则取站点其中一个作为站点入口
        if (cmsSiteVO == null) {
            MultiResponse<CmsSiteVO> cmsSiteVOMultiResponse = iCmsDynamicApplicationService.listSiteByDomain(domain);
            List<CmsSiteVO> cmsSiteVOs = cmsSiteVOMultiResponse.getData();
            if (cmsSiteVOs != null && !cmsSiteVOs.isEmpty()) {
                cmsSiteVO = cmsSiteVOs.get(0);
            }
        }
        return cmsSiteVO;
    }

    /**
     * 根据路径获取站点
     * @param siteContextPath
     * @return
     */
    protected CmsSiteVO getSiteByPath(String siteContextPath){
        SingleResponse<CmsSiteVO> siteVOSingleResponse = iCmsDynamicApplicationService.getSiteByPath(NetPathTool.ensureBeginSlash(siteContextPath));
        CmsSiteVO cmsSiteVO = siteVOSingleResponse.getData();

        return cmsSiteVO;
    }


    /**
     * 根据id获取栏目
     * @param id
     * @param siteId
     * @return
     */
    protected CmsChannelVO getChannelById(Long id, Long siteId){
        SingleResponse<CmsChannelVO> channelVOSingleResponse = iCmsDynamicApplicationService.getChannelById(id);
        CmsChannelVO cmsChannelVO = channelVOSingleResponse.getData();
        if (cmsChannelVO != null && cmsChannelVO.getCmsSiteId().equals(siteId)) {
            return cmsChannelVO;
        }
        return null;
    }

    /**
     * 根据id和路径获取栏目
     * @param id
     * @param siteId
     * @param channelPath
     * @return
     */
    protected CmsChannelVO getChannelByIdAndPath(Long id,Long siteId,String channelPath){
        SingleResponse<CmsChannelVO> channelVOSingleResponse = iCmsDynamicApplicationService.getChannelById(id);
        CmsChannelVO cmsChannelVO = channelVOSingleResponse.getData();
        if (cmsChannelVO != null && cmsChannelVO.getCmsSiteId().equals(siteId) && channelPath.equals(cmsChannelVO.getPath())) {
            return cmsChannelVO;
        }
        return null;
    }

    /**
     * 根据路径和站点id 获取栏目
     * @param channelPath
     * @param siteId
     * @return
     */
    protected CmsChannelVO getChannelByPathAndSiteId(String channelPath,Long siteId){
        SingleResponse<CmsChannelVO> cmsChannelVOSingleResponse = iCmsDynamicApplicationService.getChannelByPathAndSiteId(channelPath, siteId);
        CmsChannelVO cmsChannelVO = cmsChannelVOSingleResponse.getData();
        return cmsChannelVO;
    }

    /**
     * 根据id获取内容
     * @param contentId
     * @param channelId 并匹配栏目id
     * @param siteId 并匹配站点id
     * @return
     */
    protected CmsContentVO getContentByContentId(Long contentId, Long channelId, Long siteId){
        SingleResponse<CmsContentVO> cmsContentVOSingleResponse = iCmsDynamicApplicationService.getContentByContentId(contentId);
        CmsContentVO cmsContentVO = cmsContentVOSingleResponse.getData();
        if (cmsContentVO != null && cmsContentVO.getCmsChannelId().equals(channelId) && cmsContentVO.getCmsSiteId().equals(siteId)) {
            return cmsContentVO;
        }
        return null;
    }
}
