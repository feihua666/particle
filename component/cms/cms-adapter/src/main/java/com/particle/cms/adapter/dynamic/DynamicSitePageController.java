package com.particle.cms.adapter.dynamic;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import com.particle.cms.client.constants.CmsConstants;
import com.particle.cms.client.dto.data.CmsSiteVO;
import com.particle.cms.client.dto.data.dynamic.CmsPageTemplateModelVO;
import com.particle.cms.client.dto.data.dynamic.CmsSiteTemplateModelVO;
import com.particle.cms.client.dto.data.dynamic.CmsTemplateModelContext;
import com.particle.global.tool.str.FilePathTool;
import com.particle.global.tool.str.NetPathTool;
import com.particle.global.tool.str.StringTool;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.File;

/**
 * cms前端页面首页访问入口
 * Created by yangwei
 */
@Slf4j
@Controller
@RequestMapping(DynamicBaseController.requestPathPrefix)
public class DynamicSitePageController extends DynamicBaseController {

    /**
     * 首页
     * @return
     */
    @RequestMapping(value = {"","/"},method = RequestMethod.GET)
    public String indexEmpty(Model model, HttpServletRequest request, HttpServletResponse response){
        return indexHtml(model,request,response);
    }

    /**
     * 以动态方式访问静态首页，如果不存在静态页,跳转到动态首页访问
     * @return
     */
    @RequestMapping(value = {"/index.html"},method = RequestMethod.GET)
    public String indexHtml(Model model, HttpServletRequest request, HttpServletResponse response){
        CmsSiteVO cmsSiteVO = getSiteByDomain(request);
        return indexStatic(cmsSiteVO,request,model,response);
    }

    /**
     * 动态首页
     *
     * @return
     */
    @RequestMapping(value = {"/index.{ext:htm|htmp}"}, method = RequestMethod.GET)
    public String indexHtm(Model model,@PathVariable String ext, HttpServletRequest request, HttpServletResponse response) {
        boolean isPreview = CmsConstants.htmp.equals(ext);
        if (isPreview) {
            initModelContextForPreview();
            if (!hasPermission()) {
                CmsSiteVO cmsSiteVO = getSiteByDomain(request);
                return getSite403IndexPath(cmsSiteVO,response);
            }
        }

        CmsSiteVO cmsSiteVO = getSiteByDomain(request);
        CmsTemplateModelContext modelContext = getModelContext();
        if (modelContext.getIsDynamicStaticPriority()) {
            return indexStatic(cmsSiteVO, request, model,response);
        }
        return indexDynamic(cmsSiteVO, model,request,response);

    }
    /**
     * 上下文首页
     * @return
     */
    @RequestMapping(value = {"/{siteContextPath}","/{siteContextPath}/"},method = RequestMethod.GET)
    public String indexEmptyContext(@PathVariable  String siteContextPath,Model model, HttpServletRequest request, HttpServletResponse response){
        return indexHtmContext(siteContextPath,null,model,request,response);
    }

    /**
     * 上下文静态首页
     * @return
     */
    @RequestMapping(value = {"/{siteContextPath}/index.html"},method = RequestMethod.GET)
    public String indexHtmlContext(@PathVariable  String siteContextPath,Model model, HttpServletRequest request, HttpServletResponse response){
        //根据上下文查找站点
        String domain = getDomain(request);
        CmsSiteVO cmsSiteVO = getSiteByDomainAndSiteContextPath(domain,NetPathTool.ensureBeginSlash(siteContextPath));
        return indexStatic(cmsSiteVO,request,model,response);
    }

    /**
     * 上下文动态首页
     * @return
     */
    @RequestMapping(value = {"/{siteContextPath}/index.{ext:htm|htmp}"},method = RequestMethod.GET)
    public String indexHtmContext(@PathVariable  String siteContextPath,@PathVariable String ext,Model model, HttpServletRequest request, HttpServletResponse response){
        boolean isPreview = CmsConstants.htmp.equals(ext);
        if (isPreview) {
            initModelContextForPreview();
            if (!hasPermission()) {
                String domain = getDomain(request);
                CmsSiteVO cmsSiteVO = getSiteByDomainAndSiteContextPath(domain,NetPathTool.ensureBeginSlash(siteContextPath));
                return getSite403IndexPath(cmsSiteVO,response);
            }
        }

        //根据上下文查找站点
        String domain = getDomain(request);
        CmsSiteVO cmsSiteVO = getSiteByDomainAndSiteContextPath(domain,NetPathTool.ensureBeginSlash(siteContextPath));
        CmsTemplateModelContext modelContext = getModelContext();
        if (modelContext.getIsDynamicStaticPriority()) {
            return indexStatic(cmsSiteVO,request,model,response);
        }
        return indexDynamic(cmsSiteVO,model,request,response);
    }

    /**
     * 上下文静态首页
     * @param cmsSiteVO
     * @param request
     * @param model
     * @return
     */
    private String indexStatic(CmsSiteVO cmsSiteVO ,HttpServletRequest request,Model model, HttpServletResponse response){
        if (cmsSiteVO != null) {
            //检查是否存在静态页，如果存在静态页
            String staticSavePath = cmsSiteVO.getStaticSavePath();
            String staticDomain = cmsSiteVO.getStaticDomain();
            if (StrUtil.isNotEmpty(staticSavePath) && StrUtil.isNotEmpty(staticDomain)) {
                String indexRealPath = FilePathTool.concat(staticSavePath, CmsConstants.templateIndexHtml);
                File indexFile = FileUtils.getFile(indexRealPath);
                // 如果存在静态首页，直接重定向到静态首页
                if (indexFile.exists()) {
                    String redirect = NetPathTool.concat(cmsSiteVO.getStaticDomain() ,cmsSiteVO.getStaticDeployPath(), CmsConstants.templateIndexHtml);
                    return "redirect:" + NetPathTool.ensureBeginSlash(redirect);
                }
            }
            //如果静态页不存在，返回动态页内容
            return indexDynamic(cmsSiteVO,model,request,response);
        }else {
            return getSite404IndexPath(cmsSiteVO,response);
        }
    }
    /**
     * 上下文动态首页
     * @param cmsSiteVO
     * @param model
     * @return
     */
    private String indexDynamic(CmsSiteVO cmsSiteVO ,Model model, HttpServletRequest request, HttpServletResponse response){
        if (cmsSiteVO != null) {
            //存在站点，返回站点首页模板
            CmsSiteTemplateModelVO cmsSiteTemplateModelVO = CmsSiteTemplateModelVO.createByCmsSiteVO(cmsSiteVO, getModelContext());
            model.addAttribute(CmsConstants.model_site, cmsSiteTemplateModelVO);
            String requestUri = request.getRequestURI().toString();
            model.addAttribute(CmsConstants.model_page, CmsPageTemplateModelVO.create(
                    requestUri,
                    StringTool.concatWithOverlap(cmsSiteTemplateModelVO.getRootDeployPath(),requestUri),
                    JakartaServletUtil.getParamMap( request)
            ));

            return getTemplate(cmsSiteVO, null, null, cmsSiteVO.getTemplateIndex(), CmsConstants.templateIndexHtml);
        }else {
            return getSite404IndexPath(cmsSiteVO,response);
        }
    }
}
