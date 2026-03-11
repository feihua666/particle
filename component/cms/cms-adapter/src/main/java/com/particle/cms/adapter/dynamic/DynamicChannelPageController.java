package com.particle.cms.adapter.dynamic;

import cn.hutool.extra.servlet.JakartaServletUtil;
import com.particle.cms.client.constants.CmsConstants;
import com.particle.cms.client.dto.data.CmsChannelVO;
import com.particle.cms.client.dto.data.CmsSiteVO;
import com.particle.cms.client.dto.data.dynamic.CmsChannelTemplateModelVO;
import com.particle.cms.client.dto.data.dynamic.CmsPageTemplateModelVO;
import com.particle.cms.client.dto.data.dynamic.CmsSiteTemplateModelVO;
import com.particle.cms.client.dto.data.dynamic.CmsTemplateModelContext;
import com.particle.global.tool.str.NetPathTool;
import com.particle.global.tool.str.StringTool;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * cms前端页面访问入口
 * Created by yangwei
 */
@Controller
@RequestMapping(DynamicBaseController.requestPathPrefix)
public class DynamicChannelPageController extends DynamicBaseController {

    @RequestMapping(value = {
            "/{siteContextPath}" + requestChannelPathPrefix + "/{channelId}/index.{ext:htm|htmp}",
            "/{siteContextPath}" + requestChannelPathPrefix + "/{channelId}.{ext:htm|htmp}"},method = RequestMethod.GET)
    public String channelStandardWithSiteContext(@PathVariable String siteContextPath,
                                                 @PathVariable Long channelId,
                                                 @PathVariable String ext,
                                                 Model model,HttpServletRequest request, HttpServletResponse response){
        boolean isPreview = CmsConstants.htmp.equals(ext);
        if (isPreview) {
            initModelContextForPreview();
            if (!hasPermission()) {

                String domain = getDomain(request);
                CmsSiteVO cmsSiteVO = getSiteByDomainAndSiteContextPath(domain,NetPathTool.ensureBeginSlash(siteContextPath));
                return getSite403IndexPath(cmsSiteVO,response);
            }
        }

        String domain = getDomain(request);
        CmsSiteVO cmsSiteVO = getSiteByDomainAndSiteContextPath(domain,NetPathTool.ensureBeginSlash(siteContextPath));
        if (cmsSiteVO != null) {
            CmsChannelVO cmsChannelVO = getChannelById(channelId,cmsSiteVO.getId());
            return channelDynamic(cmsSiteVO,cmsChannelVO,model,request,response);

        }else{
            return getSite404IndexPath(cmsSiteVO,response);
        }
    }


    @RequestMapping(value = {
            requestChannelPathPrefix + "/{channelId}/index.{ext:htm|htmp}",
            requestChannelPathPrefix + "/{channelId}.{ext:htm|htmp}"},method = RequestMethod.GET)
    public String channelStandard(@PathVariable Long channelId,
                                  @PathVariable String ext,
                                  Model model,HttpServletRequest request, HttpServletResponse response){
        boolean isPreview = CmsConstants.htmp.equals(ext);
        if (isPreview) {
            initModelContextForPreview();
            if (!hasPermission()) {

                CmsSiteVO cmsSiteVO = getSiteByDomain(request);
                return getSite403IndexPath(cmsSiteVO,response);
            }
        }

        CmsSiteVO cmsSiteVO = getSiteByDomain(request);
        if (cmsSiteVO != null) {
            CmsChannelVO cmsChannelVO = getChannelById(channelId,cmsSiteVO.getId());
            return channelDynamic(cmsSiteVO,cmsChannelVO,model,request,response);
        }else{
            return getSite404IndexPath(cmsSiteVO,response);
        }
    }

    // ******************************* 对栏目访问路径的支持

    @RequestMapping(value = {"" +
            "/{siteContextPath}/{channelPath}/{channelId}/index.{ext:htm|htmp}",
            "/{siteContextPath}/{channelPath}/{channelId}.{ext:htm|htmp}"},method = RequestMethod.GET)
    public String channelStandardWithSiteContextSupportPath(@PathVariable String siteContextPath,
                                                            @PathVariable String channelPath,
                                                            @PathVariable Long channelId,
                                                            @PathVariable String ext,
                                                            Model model,HttpServletRequest request, HttpServletResponse response){
        boolean isPreview = CmsConstants.htmp.equals(ext);
        if (isPreview) {
            initModelContextForPreview();
            if (!hasPermission()) {

                String domain = getDomain(request);
                CmsSiteVO cmsSiteVO = getSiteByDomainAndSiteContextPath(domain,NetPathTool.ensureBeginSlash(siteContextPath));
                return getSite403IndexPath(cmsSiteVO,response);
            }
        }

        String domain = getDomain(request);
        CmsSiteVO cmsSiteVO = getSiteByDomainAndSiteContextPath(domain,NetPathTool.ensureBeginSlash(siteContextPath));
        if (cmsSiteVO != null) {

            CmsChannelVO cmsChannelVO = getChannelByIdAndAndSiteIdAndChannelContextPath(channelId,cmsSiteVO.getId(),NetPathTool.ensureBeginSlash(channelPath));
            return channelDynamic(cmsSiteVO,cmsChannelVO,model,request,response);

        }else{
            return getSite404IndexPath(cmsSiteVO,response);
        }
    }

    /**
     * 对栏目访问路径的支持
     * 注意映射路径和{@link DynamicChannelPageController#channelStandardWithSiteContextSupportPathSpecial(String, String, Model, HttpServletRequest)}
     * 冲突，所以加了正则区分
     * @param channelPath
     * @param channelId
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = {
            "/{channelPath}/{channelId:\\d+}/index.{ext:htm|htmp}",
            "/{channelPath}/{channelId:\\d+}.{ext:htm|htmp}"},method = RequestMethod.GET)
    public String channelStandardSupportPath(@PathVariable String channelPath,
                                             @PathVariable Long channelId,
                                             @PathVariable String ext,
                                             Model model,HttpServletRequest request, HttpServletResponse response){
        boolean isPreview = CmsConstants.htmp.equals(ext);
        if (isPreview) {
            initModelContextForPreview();
            if (!hasPermission()) {

                CmsSiteVO cmsSiteVO = getSiteByDomain(request);
                return getSite403IndexPath(cmsSiteVO,response);
            }
        }

        CmsSiteVO cmsSiteVO = getSiteByDomain(request);
        if (cmsSiteVO != null) {
            CmsChannelVO cmsChannelVO = getChannelByIdAndAndSiteIdAndChannelContextPath(channelId,cmsSiteVO.getId(),NetPathTool.ensureBeginSlash(channelPath));
            return channelDynamic(cmsSiteVO,cmsChannelVO,model,request,response);
        }else{
            return getSite404IndexPath(cmsSiteVO,response);
        }
    }

    /******************************* 栏目访问目录的独特访问支持 *****************************/

    @RequestMapping(value = {
            "/{siteContextPath}/{channelPath:^(?!\\d+$).+}/index.{ext:htm|htmp}",
            "/{siteContextPath}/{channelPath:^(?!\\d+$).+}",
            "/{siteContextPath}/{channelPath:^(?!\\d+$).+}/"},method = RequestMethod.GET)
    public String channelStandardWithSiteContextSupportPathSpecial(@PathVariable String siteContextPath,
                                                                   @PathVariable String channelPath,
                                                                   @PathVariable String ext,
                                                                   Model model,HttpServletRequest request, HttpServletResponse response){
        boolean isPreview = CmsConstants.htmp.equals(ext);
        if (isPreview) {
            initModelContextForPreview();
            if (!hasPermission()) {

                String domain = getDomain(request);
                CmsSiteVO cmsSiteVO = getSiteByDomainAndSiteContextPath(domain,NetPathTool.ensureBeginSlash(siteContextPath));
                return getSite403IndexPath(cmsSiteVO,response);
            }
        }

        String domain = getDomain(request);
        CmsSiteVO cmsSiteVO = getSiteByDomainAndSiteContextPath(domain,NetPathTool.ensureBeginSlash(siteContextPath));
        if (cmsSiteVO != null) {
            CmsChannelVO cmsChannelVO = getChannelByPathAndSiteId(NetPathTool.ensureBeginSlash(channelPath),cmsSiteVO.getId());
            return channelDynamic(cmsSiteVO,cmsChannelVO,model,request,response);

        }else{
            return getSite404IndexPath(cmsSiteVO,response);
        }
    }

    /**
     * 动态渲染栏目页面
     * @param cmsSiteVO
     * @param cmsChannelVO
     * @param model
     * @return
     */
    private String channelDynamic(CmsSiteVO cmsSiteVO,
                                  CmsChannelVO cmsChannelVO,
                                  Model model,
                                  HttpServletRequest request,HttpServletResponse response){
        if (cmsChannelVO != null) {
            CmsTemplateModelContext modelContext = getModelContext();
            // 查找栏目模板的过程
            CmsSiteTemplateModelVO cmsSiteTemplateModelVO = CmsSiteTemplateModelVO.createByCmsSiteVO(cmsSiteVO, modelContext);
            CmsChannelTemplateModelVO cmsChannelTemplateModelVO = CmsChannelTemplateModelVO.createByCmsChannelVO(cmsChannelVO,
                    cmsSiteTemplateModelVO,
                    null,
                    modelContext);

            model.addAttribute(CmsConstants.model_channel, cmsChannelTemplateModelVO);
            model.addAttribute(CmsConstants.model_site, cmsSiteTemplateModelVO);
            model.addAttribute(CmsConstants.model_site, cmsSiteTemplateModelVO);
            String requestUri = request.getRequestURI().toString();
            model.addAttribute(CmsConstants.model_page, CmsPageTemplateModelVO.create(
                    requestUri,
                    StringTool.concatWithOverlap(cmsSiteTemplateModelVO.getRootDeployPath(),requestUri),
                    JakartaServletUtil.getParamMap( request)
            ));
            return getTemplate(cmsSiteVO,
                    cmsChannelVO.getTemplatePath(),
                    cmsSiteVO.getTemplateChannelPath(),
                    cmsChannelVO.getTemplateIndex(),
                    cmsSiteVO.getTemplateChannelIndex());
        }else{
            return getSite404IndexPath(cmsSiteVO,response);
        }
    }
}
