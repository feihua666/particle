package com.particle.cms.adapter.dynamic;

import cn.hutool.core.util.StrUtil;
import com.particle.cms.adapter.dynamic.service.ICmsPermissionService;
import com.particle.cms.client.api.ICmsDynamicApplicationService;
import com.particle.cms.client.constants.CmsConstants;
import com.particle.cms.client.dto.data.CmsChannelVO;
import com.particle.cms.client.dto.data.CmsContentVO;
import com.particle.cms.client.dto.data.CmsSiteVO;
import com.particle.cms.client.dto.data.dynamic.CmsTemplateModelContext;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.tool.servlet.RequestTool;
import com.particle.global.tool.str.NetPathTool;
import com.particle.global.tool.thread.ThreadContextTool;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    public static final String requestPathPrefix = CmsConstants.requestPathPrefix;
    public static final String requestChannelPathPrefix = CmsConstants.requestChannelPathPrefix;
    public static final String requestContentPathPrefix = CmsConstants.requestContentPathPrefix;

    /**
     * 预览 权限码
     */
    private static final String permission_code = "cms:dynamic:preview";

    private static final String thread_local_key_model_context = "cms_model_context";

    @Autowired
    protected ICmsDynamicApplicationService iCmsDynamicApplicationService;

    @Autowired(required = false)
    private ICmsPermissionService ICmsPermissionService;

    /**
     * 默认模板上下文
     */
    private CmsTemplateModelContext defaultModelContext;

    /**
     * 初始化模板上下文
     */
    protected void initModelContextForPreview() {
        CmsTemplateModelContext modelContext = CmsTemplateModelContext.create(true,false,CmsTemplateModelContext.Mode.preview);
        ThreadContextTool.put(thread_local_key_model_context,modelContext);
    }
    /**
     * 清理模板上下文
     */
    protected void clearModelContext() {
        ThreadContextTool.remove(thread_local_key_model_context);
    }
    /**
     * 获取模板上下文
     * @return
     */
    protected CmsTemplateModelContext getModelContext() {
        CmsTemplateModelContext threadLocalModelContext = (CmsTemplateModelContext)ThreadContextTool.get(thread_local_key_model_context);
        if (threadLocalModelContext != null) {
            return threadLocalModelContext;
        }

        if (defaultModelContext == null) {
            defaultModelContext = CmsTemplateModelContext.create(true,true,CmsTemplateModelContext.Mode.publish);
        }
        return defaultModelContext;
    }

    /**
     * 获取是否公开条件
     * @return
     */
    protected Boolean getIsPublicCondition() {
        CmsTemplateModelContext.Mode mode = getModelContext().getMode();
        Boolean isPublic = true;
        if (mode == CmsTemplateModelContext.Mode.publish) {
            isPublic = true;
        } else if (mode == CmsTemplateModelContext.Mode.preview) {
            isPublic = null;
        }
        return isPublic;
    }
    /**
     * 校验权限
     * @return
     */
    protected boolean hasPermission() {
        if (ICmsPermissionService == null) {
            return true;
        }
        return ICmsPermissionService.hasPermission(permission_code);
    }
    /**
     * 获取站点的模板存在根目录
     * @param cmsSiteVO
     * @return
     */
    protected String getSiteTemplatePathForViewResolver(CmsSiteVO cmsSiteVO){
        String templatePathDefault = CmsConstants.templatePathGlobal;
        if(cmsSiteVO != null && StringUtils.isNoneEmpty(cmsSiteVO.getTemplatePath())){
            templatePathDefault = cmsSiteVO.getTemplatePath();
        }

        return NetPathTool.concat(CmsConstants.templateRootPath , templatePathDefault);
    }
    /**
     * 获取全局的模板存在根目录
     * @return
     */
    protected String getGlobalTemplatePathForViewResolver(){
        String templatePathDefault = CmsConstants.templatePathGlobal;
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
        String templatePathForViewResolver = getSiteTemplatePathForViewResolver(cmsSiteVO);

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
    protected String getSite404IndexPath(CmsSiteVO cmsSiteVO, HttpServletResponse response){
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        // 站点不存在或未配置，使用全局404模板
        if (cmsSiteVO == null || StrUtil.isEmpty(cmsSiteVO.getTemplate404Index())) {
            String globalTemplatePathForViewResolver = getGlobalTemplatePathForViewResolver();
            String template404Index = CmsConstants.templateIndex404Html;

            String templatePath = NetPathTool.concat(globalTemplatePathForViewResolver , template404Index);
            templatePath = StrUtil.removeSuffix(templatePath, CmsConstants.templateSuffix);
            return templatePath;
        }


        String templatePathForViewResolver = getSiteTemplatePathForViewResolver(cmsSiteVO);
        String template404Path = CmsConstants.template404Path;
        if(!StringUtils.isEmpty(cmsSiteVO.getTemplate404Path())){
            template404Path = cmsSiteVO.getTemplate404Path();
        }
        String template404Index = CmsConstants.templateIndex404Html;
        if(!StringUtils.isEmpty(cmsSiteVO.getTemplate404Index())){
            template404Index = cmsSiteVO.getTemplate404Index();
        }
        String templatePath = NetPathTool.concat(templatePathForViewResolver,template404Path , template404Index);
        templatePath = StrUtil.removeSuffix(templatePath, CmsConstants.templateSuffix);
        return templatePath;
    }
    /**
     * 403
     * @param cmsSiteVO
     * @return
     */
    protected String getSite403IndexPath(CmsSiteVO cmsSiteVO, HttpServletResponse response){
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        // 站点不存在或未配置，使用全局403模板
        if (cmsSiteVO == null || StrUtil.isEmpty(cmsSiteVO.getTemplate403Index())) {
            String globalTemplatePathForViewResolver = getGlobalTemplatePathForViewResolver();
            String template403Index = CmsConstants.templateIndex403Html;

            String templatePath = NetPathTool.concat(globalTemplatePathForViewResolver , template403Index);
            templatePath = StrUtil.removeSuffix(templatePath, CmsConstants.templateSuffix);
            return templatePath;
        }


        String templatePathForViewResolver = getSiteTemplatePathForViewResolver(cmsSiteVO);
        String template403Path = CmsConstants.template403Path;
        if(!StringUtils.isEmpty(cmsSiteVO.getTemplate403Path())){
            template403Path = cmsSiteVO.getTemplate403Path();
        }
        String template403Index = CmsConstants.templateIndex403Html;
        if(!StringUtils.isEmpty(cmsSiteVO.getTemplate403Index())){
            template403Index = cmsSiteVO.getTemplate403Index();
        }
        String templatePath = NetPathTool.concat(templatePathForViewResolver,template403Path , template403Index);
        templatePath = StrUtil.removeSuffix(templatePath, CmsConstants.templateSuffix);
        return templatePath;
    }
    /**
     * 获取域名
     * @param request
     * @return
     */
    protected String getDomain(HttpServletRequest request){
        return RequestTool.getRealDomain(request,true,true);
    }
    /**
     * 根据id获取站点
     * @param cmsSiteId
     * @return
     */
    protected CmsSiteVO getSiteById(Long cmsSiteId){
        SingleResponse<CmsSiteVO> cmsSiteVOSingleResponse = iCmsDynamicApplicationService.getSiteById(cmsSiteId, getIsPublicCondition());
        CmsSiteVO cmsSiteVO = cmsSiteVOSingleResponse.getData();
        return cmsSiteVO;
    }
    /**
     * 根据域名获取站点
     * @param request
     * @return
     */
    protected CmsSiteVO getSiteByDomain(HttpServletRequest request){
        String domain = getDomain(request);
        // 根据域名查找主站点
        SingleResponse<CmsSiteVO> cmsSiteVOSingleResponse = iCmsDynamicApplicationService.getPrimeSiteByDomain(domain,getIsPublicCondition());
        CmsSiteVO cmsSiteVO = cmsSiteVOSingleResponse.getData();
        // 如果没有设置主站点，则取站点其中一个作为站点入口
        if (cmsSiteVO == null) {
            MultiResponse<CmsSiteVO> cmsSiteVOMultiResponse = iCmsDynamicApplicationService.listSiteByDomain(domain,getIsPublicCondition());
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
    protected CmsSiteVO getSiteByDomainAndSiteContextPath(String domain, String siteContextPath){
        SingleResponse<CmsSiteVO> siteVOSingleResponse = iCmsDynamicApplicationService.getSiteByDomainAndSiteContextPath(domain,
                NetPathTool.ensureBeginSlash(siteContextPath),getIsPublicCondition());
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
        SingleResponse<CmsChannelVO> channelVOSingleResponse = iCmsDynamicApplicationService.getChannelById(id,getIsPublicCondition());
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
    protected CmsChannelVO getChannelByIdAndAndSiteIdAndChannelContextPath(Long id, Long siteId, String channelPath){
        SingleResponse<CmsChannelVO> channelVOSingleResponse = iCmsDynamicApplicationService.getChannelById(id,getIsPublicCondition());
        CmsChannelVO cmsChannelVO = channelVOSingleResponse.getData();
        if (cmsChannelVO != null && cmsChannelVO.getCmsSiteId().equals(siteId) && channelPath.equals(cmsChannelVO.getChannelContextPath())) {
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
        SingleResponse<CmsChannelVO> cmsChannelVOSingleResponse = iCmsDynamicApplicationService.getChannelByPathAndSiteId(channelPath, siteId,getIsPublicCondition());
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
        SingleResponse<CmsContentVO> cmsContentVOSingleResponse = iCmsDynamicApplicationService.getContentByContentId(contentId,getIsPublicCondition());
        CmsContentVO cmsContentVO = cmsContentVOSingleResponse.getData();
        if (cmsContentVO != null && cmsContentVO.getCmsChannelId().equals(channelId) && cmsContentVO.getCmsSiteId().equals(siteId)) {
            return cmsContentVO;
        }
        return null;
    }
    /**
     * 根据id获取内容
     * @param contentId
     * @return
     */
    protected CmsContentVO getContentByContentId(Long contentId){
        SingleResponse<CmsContentVO> cmsContentVOSingleResponse = iCmsDynamicApplicationService.getContentByContentId(contentId,getIsPublicCondition());
        CmsContentVO cmsContentVO = cmsContentVOSingleResponse.getData();
        return cmsContentVO;
    }
}
