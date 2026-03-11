package com.particle.cms.adapter.dynamic;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import com.particle.cms.client.constants.CmsConstants;
import com.particle.cms.client.dto.data.CmsChannelVO;
import com.particle.cms.client.dto.data.CmsContentMultimediaVO;
import com.particle.cms.client.dto.data.CmsContentVO;
import com.particle.cms.client.dto.data.CmsSiteVO;
import com.particle.cms.client.dto.data.dynamic.*;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.tool.str.NetPathTool;
import com.particle.global.tool.str.StringTool;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * cms前端页面访问入口
 * Created by yangwei
 */
@Controller
@RequestMapping(DynamicBaseController.requestPathPrefix)
public class DynamicContentPageController extends DynamicBaseController {

    private static Logger logger = LoggerFactory.getLogger(DynamicContentPageController.class);


    @RequestMapping(value = {
            "/{siteContextPath}" + requestChannelPathPrefix + "/{channelId}" + requestContentPathPrefix + "/{contentId}/index.{ext:htm|htmp}",
            "/{siteContextPath}" + requestChannelPathPrefix + "/{channelId}" + requestContentPathPrefix + "/{contentId}.{ext:htm|htmp}"},method = RequestMethod.GET)
    public String contentStandardWithSiteContext(@PathVariable String siteContextPath,
                                                 @PathVariable Long channelId,
                                                 @PathVariable Long contentId,
                                                 @PathVariable String ext,
                                                 Model model,HttpServletRequest request, HttpServletResponse response){
        boolean isPreview = CmsConstants.htmp.equals(ext);
        if (isPreview) {
            initModelContextForPreview();
            if (!hasPermission()) {

                String domain = getDomain(request);
                CmsSiteVO cmsSiteVO = getSiteByDomainAndSiteContextPath(domain, NetPathTool.ensureBeginSlash(siteContextPath));
                return getSite403IndexPath(cmsSiteVO,response);
            }
        }

        String domain = getDomain(request);
        CmsSiteVO cmsSiteVO = getSiteByDomainAndSiteContextPath(domain, NetPathTool.ensureBeginSlash(siteContextPath));
        if (cmsSiteVO != null) {
            CmsChannelVO cmsChannelVO = getChannelById(channelId,cmsSiteVO.getId());
            if (cmsChannelVO != null) {
                CmsContentVO cmsContentVO = getContentByContentId(contentId,channelId,cmsSiteVO.getId());
                return contentDynamic(cmsSiteVO,cmsChannelVO,cmsContentVO,model,request,response);
            }else{
                return getSite404IndexPath(cmsSiteVO,response);
            }

        }else{
            return getSite404IndexPath(cmsSiteVO,response);
        }
    }


    @RequestMapping(value = {
            "" + requestChannelPathPrefix + "/{channelId}" + requestContentPathPrefix + "/{contentId}/index.{ext:htm|htmp}",
            "" + requestChannelPathPrefix + "/{channelId}" + requestContentPathPrefix + "/{contentId}.{ext:htm|htmp}"},method = RequestMethod.GET)
    public String contentStandard(@PathVariable Long channelId,
                                  @PathVariable Long contentId,
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
            if (cmsChannelVO != null) {

                CmsContentVO cmsContentVO = getContentByContentId(contentId,channelId,cmsSiteVO.getId());
                return contentDynamic(cmsSiteVO,cmsChannelVO,cmsContentVO,model,request,response);
            }else{
                return getSite404IndexPath(cmsSiteVO,response);
            }
        }else{
            return getSite404IndexPath(cmsSiteVO,response);
        }
    }

    // ******************************* 对栏目访问路径的支持

    @RequestMapping(value = {
            "/{siteContextPath}/{channelPath}/{channelId}" + requestContentPathPrefix + "/{contentId}/index.{ext:htm|htmp}",
            "/{siteContextPath}/{channelPath}/{channelId}" + requestContentPathPrefix + "/{contentId}.{ext:htm|htmp}"},method = RequestMethod.GET)
    public String contentStandardWithSiteContextSupportPath(@PathVariable String siteContextPath,
                                                            @PathVariable String channelPath,
                                                            @PathVariable Long channelId,
                                                            @PathVariable Long contentId,
                                                            @PathVariable String ext,
                                                            Model model,HttpServletRequest request, HttpServletResponse response){
        boolean isPreview = CmsConstants.htmp.equals(ext);
        if (isPreview) {
            initModelContextForPreview();
            if (!hasPermission()) {

                String domain = getDomain(request);
                CmsSiteVO cmsSiteVO = getSiteByDomainAndSiteContextPath(domain, NetPathTool.ensureBeginSlash(siteContextPath));
                return getSite403IndexPath(cmsSiteVO,response);
            }
        }

        String domain = getDomain(request);
        CmsSiteVO cmsSiteVO = getSiteByDomainAndSiteContextPath(domain, NetPathTool.ensureBeginSlash(siteContextPath));
        if (cmsSiteVO != null) {

            CmsChannelVO cmsChannelVO = getChannelByIdAndAndSiteIdAndChannelContextPath(channelId,cmsSiteVO.getId(),NetPathTool.ensureBeginSlash(channelPath));
            if (cmsChannelVO != null) {

                CmsContentVO cmsContentVO = getContentByContentId(contentId,channelId,cmsSiteVO.getId());
                return contentDynamic(cmsSiteVO,cmsChannelVO,cmsContentVO,model,request,response);
            }else{
                return getSite404IndexPath(cmsSiteVO,response);
            }

        }else{
            return getSite404IndexPath(cmsSiteVO,response);
        }
    }

    @RequestMapping(value = {
            "/{channelPath}/{channelId:\\d+}" + requestContentPathPrefix + "/{contentId}/index.{ext:htm|htmp}",
            "/{channelPath}/{channelId:\\d+}" + requestContentPathPrefix + "/{contentId}.{ext:htm|htmp}"},method = RequestMethod.GET)
    public String contentStandardSupportPath(@PathVariable String channelPath,
                                             @PathVariable Long channelId,
                                             @PathVariable Long contentId,
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
            if (cmsChannelVO != null) {

                CmsContentVO cmsContentVO = getContentByContentId(contentId,channelId,cmsSiteVO.getId());
                return contentDynamic(cmsSiteVO,cmsChannelVO,cmsContentVO,model,request,response);
            }else{
                return getSite404IndexPath(cmsSiteVO,response);
            }
        }else{
            return getSite404IndexPath(cmsSiteVO,response);
        }
    }

    // ******************************* 栏目访问目录的独特访问支持

    @RequestMapping(value = {
            "/{siteContextPath}/{channelPath:^(?!\\d+$).+}" + requestContentPathPrefix + "/{contentId}/index.{ext:htm|htmp}",
            "/{siteContextPath}/{channelPath:^(?!\\d+$).+}" + requestContentPathPrefix + "/{contentId}.{ext:htm|htmp}"},method = RequestMethod.GET)
    public String contentStandardWithSiteContextSupportPathSpecial(@PathVariable String siteContextPath,
                                                                   @PathVariable String channelPath,
                                                                   @PathVariable Long contentId,
                                                                   @PathVariable String ext,
                                                                   Model model,HttpServletRequest request, HttpServletResponse response){
        boolean isPreview = CmsConstants.htmp.equals(ext);
        if (isPreview) {
            initModelContextForPreview();
            if (!hasPermission()) {

                String domain = getDomain(request);
                CmsSiteVO cmsSiteVO = getSiteByDomainAndSiteContextPath(domain, NetPathTool.ensureBeginSlash(siteContextPath));
                return getSite403IndexPath(cmsSiteVO,response);
            }
        }

        String domain = getDomain(request);
        CmsSiteVO cmsSiteVO = getSiteByDomainAndSiteContextPath(domain, NetPathTool.ensureBeginSlash(siteContextPath));
        if (cmsSiteVO != null) {

            CmsChannelVO cmsChannelVO = getChannelByPathAndSiteId(NetPathTool.ensureBeginSlash(channelPath),cmsSiteVO.getId());
            if (cmsChannelVO != null) {

                CmsContentVO cmsContentVO = getContentByContentId(contentId,cmsChannelVO.getId(),cmsSiteVO.getId());
                return contentDynamic(cmsSiteVO,cmsChannelVO,cmsContentVO,model,request,response);
            }else{
                return getSite404IndexPath(cmsSiteVO,response);
            }

        }else{
            return getSite404IndexPath(cmsSiteVO,response);
        }
    }


    /**
     * 动态渲染内容
     * @param cmsSiteVO
     * @param cmsChannelVO
     * @param cmsContentVO
     * @param model
     * @return
     */
    private String contentDynamic(CmsSiteVO cmsSiteVO,
                                  CmsChannelVO cmsChannelVO,
                                  CmsContentVO cmsContentVO,
                                  Model model,
                                  HttpServletRequest request,HttpServletResponse response){
        if (cmsContentVO != null) {
            // 查找内容模板的过程
            CmsSiteTemplateModelVO cmsSiteTemplateModelVO = CmsSiteTemplateModelVO.createByCmsSiteVO(cmsSiteVO, getModelContext());
            CmsChannelTemplateModelVO cmsChannelTemplateModelVO = CmsChannelTemplateModelVO.createByCmsChannelVO(cmsChannelVO, cmsSiteTemplateModelVO,null, getModelContext());
            CmsContentTemplateModelVO cmsContentTemplateModelVO = CmsContentTemplateModelVO.createByCmsContentVO(cmsContentVO,cmsSiteTemplateModelVO, cmsChannelTemplateModelVO, getModelContext());

            List<CmsContentMultimediaTemplateModelVO> cmsContentMultimediaTemplateModelVOS = getCmsContentMultimediaTemplateModelVOS(cmsContentVO.getId());
            cmsContentTemplateModelVO.setContentMultimedias(cmsContentMultimediaTemplateModelVOS);

            model.addAttribute(CmsConstants.model_channel, cmsChannelTemplateModelVO);
            model.addAttribute(CmsConstants.model_site, cmsSiteTemplateModelVO);
            model.addAttribute(CmsConstants.model_content, cmsContentTemplateModelVO);
            model.addAttribute(CmsConstants.model_site, cmsSiteTemplateModelVO);
            String requestUri = request.getRequestURI().toString();
            model.addAttribute(CmsConstants.model_page, CmsPageTemplateModelVO.create(
                    requestUri,
                    StringTool.concatWithOverlap(cmsSiteTemplateModelVO.getRootDeployPath(),requestUri),
                    JakartaServletUtil.getParamMap( request)
            ));
            return getTemplate(cmsSiteVO,
                    cmsContentVO.getTemplatePath(),
                    cmsSiteVO.getTemplateContentPath(),
                    cmsContentVO.getTemplateIndex(),
                    cmsSiteVO.getTemplateContentIndex());
        }else{
            return getSite404IndexPath(cmsSiteVO,response);
        }
    }
    /**
     * 获取内容id获取内容多媒体列表
     * @param contentId
     * @return
     */
    private List<CmsContentMultimediaTemplateModelVO> getCmsContentMultimediaTemplateModelVOS(Long contentId){
        MultiResponse<CmsContentMultimediaVO> cmsContentMultimediaVOMultiResponse = iCmsDynamicApplicationService.listContentMultimediaByContentId(contentId);
        List<CmsContentMultimediaVO> contentMultimediaVOS = cmsContentMultimediaVOMultiResponse.getData();
        if (CollectionUtil.isEmpty(contentMultimediaVOS)) {
                return Collections.emptyList();
        }
        return contentMultimediaVOS.stream()
                .map(cmsContentMultimediaVO -> CmsContentMultimediaTemplateModelVO.createByCmsContentMultimediaVO(cmsContentMultimediaVO))
                .collect(Collectors.toList());

    }
}
