package com.particle.cms.adapter.dynamic;

import com.particle.cms.client.constants.CmsConstants;
import com.particle.cms.client.dto.data.CmsChannelVO;
import com.particle.cms.client.dto.data.CmsContentVO;
import com.particle.cms.client.dto.data.CmsSiteVO;
import com.particle.cms.client.dto.data.dynamic.CmsChannelTemplateModelVO;
import com.particle.cms.client.dto.data.dynamic.CmsContentTemplateModelVO;
import com.particle.cms.client.dto.data.dynamic.CmsSiteTemplateModelVO;
import com.particle.global.tool.str.NetPathTool;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class DynamicContentPageController extends DynamicBaseController {

    private static Logger logger = LoggerFactory.getLogger(DynamicContentPageController.class);



    @RequestMapping(value = {"/{siteContextPath}/channel/{channelId}/content/{contentId}/index.htm"},method = RequestMethod.GET)
    public String contentStandardWithSiteContext(@PathVariable String siteContextPath, @PathVariable Long channelId,@PathVariable Long contentId, Model model){
        CmsSiteVO cmsSiteVO = getSiteByPath(NetPathTool.ensureBeginSlash(siteContextPath));
        if (cmsSiteVO != null) {
            CmsChannelVO cmsChannelVO = getChannelById(channelId,cmsSiteVO.getId());
            if (cmsChannelVO != null) {
                CmsContentVO cmsContentVO = getContentByContentId(contentId,channelId,cmsSiteVO.getId());
                return contentDynamic(cmsSiteVO,cmsChannelVO,cmsContentVO,model);
            }else{
                return getSite404IndexPath(cmsSiteVO);
            }

        }else{
            return getSite404IndexPath(cmsSiteVO);
        }
    }

    @RequestMapping(value = {"/{siteContextPath}/channel/{channelId}/content/{contentId}.htm"},method = RequestMethod.GET)
    public String contentSimpleWithSiteContext(@PathVariable String siteContextPath,@PathVariable Long channelId,@PathVariable Long contentId,Model model){
        return contentStandardWithSiteContext(siteContextPath,channelId,contentId,model);
    }
    @RequestMapping(value = {"/channel/{channelId}/content/{contentId}/index.htm"},method = RequestMethod.GET)
    public String contentStandard(@PathVariable Long channelId,@PathVariable Long contentId,Model model,HttpServletRequest request){
        CmsSiteVO cmsSiteVO = getSiteByDomain(request);
        if (cmsSiteVO != null) {
            CmsChannelVO cmsChannelVO = getChannelById(channelId,cmsSiteVO.getId());
            if (cmsChannelVO != null) {

                CmsContentVO cmsContentVO = getContentByContentId(contentId,channelId,cmsSiteVO.getId());
                return contentDynamic(cmsSiteVO,cmsChannelVO,cmsContentVO,model);
            }else{
                return getSite404IndexPath(cmsSiteVO);
            }
        }else{
            return getSite404IndexPath(cmsSiteVO);
        }
    }

    @RequestMapping(value = {"/channel/{channelId}/content/{contentId}.htm"},method = RequestMethod.GET)
    public String contentSimple(@PathVariable Long channelId,@PathVariable Long contentId,Model model,HttpServletRequest request){
        return contentStandard(channelId,contentId,model,request);
    }

    // ******************************* 对栏目访问路径的支持

    @RequestMapping(value = {"/{siteContextPath}/{channelPath}/{channelId}/content/{contentId}/index.htm"},method = RequestMethod.GET)
    public String contentStandardWithSiteContextSupportPath(@PathVariable String siteContextPath,@PathVariable String channelPath,@PathVariable Long channelId,@PathVariable Long contentId, Model model){
        CmsSiteVO cmsSiteVO = getSiteByPath(NetPathTool.ensureBeginSlash(siteContextPath));
        if (cmsSiteVO != null) {

            CmsChannelVO cmsChannelVO = getChannelByIdAndPath(channelId,cmsSiteVO.getId(),channelPath);
            if (cmsChannelVO != null) {

                CmsContentVO cmsContentVO = getContentByContentId(contentId,channelId,cmsSiteVO.getId());
                return contentDynamic(cmsSiteVO,cmsChannelVO,cmsContentVO,model);
            }else{
                return getSite404IndexPath(cmsSiteVO);
            }

        }else{
            return getSite404IndexPath(cmsSiteVO);
        }
    }
    @RequestMapping(value = {"/{siteContextPath}/{channelPath}/{channelId}/content/{contentId}.htm"},method = RequestMethod.GET)
    public String contentSimpleWithSiteContextSupportPath(@PathVariable String siteContextPath,@PathVariable String channelPath,@PathVariable Long channelId, @PathVariable Long contentId, Model model){
        return contentStandardWithSiteContextSupportPath(siteContextPath,channelPath,channelId,contentId,model);
    }
    @RequestMapping(value = {"/{channelPath}/{channelId}/content/{contentId}/index.htm"},method = RequestMethod.GET)
    public String contentStandardSupportPath(@PathVariable String channelPath, @PathVariable Long channelId, @PathVariable Long contentId, Model model, HttpServletRequest request){
        CmsSiteVO cmsSiteVO = getSiteByDomain(request);
        if (cmsSiteVO != null) {
            CmsChannelVO cmsChannelVO = getChannelByIdAndPath(channelId,cmsSiteVO.getId(),channelPath);
            if (cmsChannelVO != null) {

                CmsContentVO cmsContentVO = getContentByContentId(contentId,channelId,cmsSiteVO.getId());
                return contentDynamic(cmsSiteVO,cmsChannelVO,cmsContentVO,model);
            }else{
                return getSite404IndexPath(cmsSiteVO);
            }
        }else{
            return getSite404IndexPath(cmsSiteVO);
        }
    }

    @RequestMapping(value = {"/{channelPath}/{channelId}/content/{contentId}.htm"},method = RequestMethod.GET)
    public String contentSimpleSupportPath(@PathVariable String channelPath,@PathVariable Long channelId,@PathVariable Long contentId, Model model, HttpServletRequest request){
        return contentStandardSupportPath(channelPath,channelId,contentId,model, request);
    }

    // ******************************* 栏目访问目录的独特访问支持

    @RequestMapping(value = {"/{siteContextPath}/{channelPath}/content/{contentId}/index.htm"},method = RequestMethod.GET)
    public String contentStandardWithSiteContextSupportPathSpecial(@PathVariable String siteContextPath,@PathVariable String channelPath,@PathVariable Long contentId, Model model){
        CmsSiteVO cmsSiteVO = getSiteByPath(NetPathTool.ensureBeginSlash(siteContextPath));
        if (cmsSiteVO != null) {

            CmsChannelVO cmsChannelVO = getChannelByPathAndSiteId(NetPathTool.ensureBeginSlash(channelPath),cmsSiteVO.getId());
            if (cmsChannelVO != null) {

                CmsContentVO cmsContentVO = getContentByContentId(contentId,cmsChannelVO.getId(),cmsSiteVO.getId());
                return contentDynamic(cmsSiteVO,cmsChannelVO,cmsContentVO,model);
            }else{
                return getSite404IndexPath(cmsSiteVO);
            }

        }else{
            return getSite404IndexPath(cmsSiteVO);
        }
    }
    @RequestMapping(value = {"/{siteContextPath}/{channelPath}/content/{contentId}.htm"},method = RequestMethod.GET)
    public String contentSimpleWithSiteContextSupportPathSpecial(@PathVariable String siteContextPath,@PathVariable String channelPath,@PathVariable Long contentId,Model model){
        return contentStandardWithSiteContextSupportPathSpecial(siteContextPath,channelPath,contentId,model);
    }

    private String contentDynamic(CmsSiteVO cmsSiteVO, CmsChannelVO cmsChannelVO, CmsContentVO cmsContentVO, Model model){
        if (cmsChannelVO != null) {
            // 查找栏目模板的过程
            model.addAttribute(CmsConstants.model_channel, CmsChannelTemplateModelVO.createByCmsChannelVO(cmsChannelVO));
            model.addAttribute(CmsConstants.model_site, CmsSiteTemplateModelVO.createByCmsSiteVO(cmsSiteVO));
            model.addAttribute(CmsConstants.model_content, CmsContentTemplateModelVO.createByCmsContentVO(cmsContentVO));

            return getTemplate(cmsSiteVO, cmsContentVO.getTemplatePath(), CmsConstants.templateContentPath, cmsContentVO.getTemplateIndex(), CmsConstants.templateIndexHtml);
        }else{
            return getSite404IndexPath(cmsSiteVO);
        }
    }
}
