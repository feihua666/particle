package com.particle.cms.adapter.dynamic;

import com.particle.cms.client.constants.CmsConstants;
import com.particle.cms.client.dto.data.CmsChannelVO;
import com.particle.cms.client.dto.data.CmsSiteVO;
import com.particle.cms.client.dto.data.dynamic.CmsChannelTemplateModelVO;
import com.particle.cms.client.dto.data.dynamic.CmsSiteTemplateModelVO;
import com.particle.global.tool.str.NetPathTool;
import jakarta.servlet.http.HttpServletRequest;
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

    @RequestMapping(value = {"/{siteContextPath}/channel/{channelId}/index.htm"},method = RequestMethod.GET)
    public String channelStandardWithSiteContext(@PathVariable String siteContextPath,@PathVariable Long channelId, Model model){
        CmsSiteVO cmsSiteVO = getSiteByPath(NetPathTool.ensureBeginSlash(siteContextPath));
        if (cmsSiteVO != null) {

            CmsChannelVO cmsChannelVO = getChannelById(channelId,cmsSiteVO.getId());
            return channelDynamic(cmsSiteVO,cmsChannelVO,model);

        }else{
            return getSite404IndexPath(cmsSiteVO);
        }
    }

    @RequestMapping(value = {"/{siteContextPath}/channel/{channelId}.htm"},method = RequestMethod.GET)
    public String channelSimpleWithSiteContext(@PathVariable String siteContextPath,@PathVariable Long channelId,Model model){
        return channelStandardWithSiteContext(siteContextPath,channelId,model);
    }
    @RequestMapping(value = {"/channel/{channelId}/index.htm"},method = RequestMethod.GET)
    public String channelStandard(@PathVariable Long channelId,Model model,HttpServletRequest request){
        CmsSiteVO cmsSiteVO = getSiteByDomain(request);
        if (cmsSiteVO != null) {
            CmsChannelVO cmsChannelVO = getChannelById(channelId,cmsSiteVO.getId());
            return channelDynamic(cmsSiteVO,cmsChannelVO,model);
        }else{
            return getSite404IndexPath(cmsSiteVO);
        }
    }

    @RequestMapping(value = {"/channel/{channelId}.htm"},method = RequestMethod.GET)
    public String channelSimple(@PathVariable Long channelId,Model model,HttpServletRequest request){
        return channelStandard(channelId,model,request);
    }

    // ******************************* 对栏目访问路径的支持

    @RequestMapping(value = {"/{siteContextPath}/{channelPath}/{channelId}/index.htm"},method = RequestMethod.GET)
    public String channelStandardWithSiteContextSupportPath(@PathVariable String siteContextPath,@PathVariable String channelPath,@PathVariable Long channelId, Model model){
        CmsSiteVO cmsSiteVO = getSiteByPath(NetPathTool.ensureBeginSlash(siteContextPath));
        if (cmsSiteVO != null) {

            CmsChannelVO cmsChannelVO = getChannelByIdAndPath(channelId,cmsSiteVO.getId(),channelPath);
            return channelDynamic(cmsSiteVO,cmsChannelVO,model);

        }else{
            return getSite404IndexPath(cmsSiteVO);
        }
    }
    @RequestMapping(value = {"/{siteContextPath}/{channelPath}/{channelId}.htm"},method = RequestMethod.GET)
    public String channelSimpleWithSiteContextSupportPath(@PathVariable String siteContextPath,@PathVariable String channelPath,@PathVariable Long channelId,Model model){
        return channelStandardWithSiteContextSupportPath(siteContextPath,channelPath,channelId,model);
    }
    @RequestMapping(value = {"/{channelPath}/{channelId}/index.htm"},method = RequestMethod.GET)
    public String channelStandardSupportPath(@PathVariable String channelPath, @PathVariable Long channelId, Model model, HttpServletRequest  request){
        CmsSiteVO cmsSiteVO = getSiteByDomain(request);
        if (cmsSiteVO != null) {
            CmsChannelVO cmsChannelVO = getChannelByIdAndPath(channelId,cmsSiteVO.getId(),channelPath);
            return channelDynamic(cmsSiteVO,cmsChannelVO,model);
        }else{
            return getSite404IndexPath(cmsSiteVO);
        }
    }

    @RequestMapping(value = {"/{channelPath}/{channelId}.htm"},method = RequestMethod.GET)
    public String channelSimpleSupportPath(@PathVariable String channelPath,@PathVariable Long channelId,Model model,HttpServletRequest  request){
        return channelStandardSupportPath(channelPath,channelId,model,request);
    }

    // ******************************* 栏目访问目录的独特访问支持

    @RequestMapping(value = {"/{siteContextPath}/{channelPath}/index.htm"},method = RequestMethod.GET)
    public String channelStandardWithSiteContextSupportPathSpecial(@PathVariable String siteContextPath,@PathVariable String channelPath, Model model){
        CmsSiteVO cmsSiteVO = getSiteByPath(NetPathTool.ensureBeginSlash(siteContextPath));
        if (cmsSiteVO != null) {
            CmsChannelVO cmsChannelVO = getChannelByPathAndSiteId(NetPathTool.ensureBeginSlash(channelPath),cmsSiteVO.getId());
            return channelDynamic(cmsSiteVO,cmsChannelVO,model);

        }else{
            return getSite404IndexPath(cmsSiteVO);
        }
    }
    @RequestMapping(value = {"/{siteContextPath}/{channelPath}"},method = RequestMethod.GET)
    public String channelSimpleWithSiteContextSupportPathSpecial(@PathVariable String siteContextPath,@PathVariable String channelPath,@PathVariable Long channelId,Model model){
        return channelStandardWithSiteContextSupportPathSpecial(siteContextPath,channelPath,model);
    }
    @RequestMapping(value = {"/{siteContextPath}/{channelPath}/"},method = RequestMethod.GET)
    public String channelSimpleWithSiteContextSupportPathSpecial_1(@PathVariable String siteContextPath,@PathVariable String channelPath,@PathVariable Long channelId,Model model){
        return channelStandardWithSiteContextSupportPathSpecial(siteContextPath,channelPath,model);
    }

    private String channelDynamic(CmsSiteVO cmsSiteVO, CmsChannelVO cmsChannelVO, Model model){
        if (cmsChannelVO != null) {
            // 查找栏目模板的过程
            model.addAttribute(CmsConstants.model_channel, CmsChannelTemplateModelVO.createByCmsChannelVO(cmsChannelVO));
            model.addAttribute(CmsConstants.model_site, CmsSiteTemplateModelVO.createByCmsSiteVO(cmsSiteVO));

            return getTemplate(cmsSiteVO, cmsChannelVO.getTemplatePath(), CmsConstants.templateChannelPath, cmsChannelVO.getTemplateIndex(), CmsConstants.templateIndexHtml);
        }else{
            return getSite404IndexPath(cmsSiteVO);
        }
    }
}
