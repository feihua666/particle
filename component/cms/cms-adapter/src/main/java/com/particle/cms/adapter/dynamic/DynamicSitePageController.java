package com.particle.cms.adapter.dynamic;

import com.particle.cms.client.constants.CmsConstants;
import com.particle.cms.client.dto.data.CmsSiteVO;
import com.particle.cms.client.dto.data.dynamic.CmsSiteTemplateModelVO;
import com.particle.global.tool.servlet.RequestTool;
import com.particle.global.tool.str.FilePathTool;
import com.particle.global.tool.str.NetPathTool;
import jakarta.servlet.http.HttpServletRequest;
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
    @RequestMapping(value = {""},method = RequestMethod.GET)
    public String indexEmpty(Model model, HttpServletRequest request){
        return indexHtml(model,request);
    }
    /**
     * 首页
     * @return
     */
    @RequestMapping(value = {"/"},method = RequestMethod.GET)
    public String indexRoot(Model model, HttpServletRequest request){
        return indexHtml(model,request);
    }

    /**
     * 以动态方式访问静态首页，如果不存在静态页,跳转到动态首页访问
     * @return
     */
    @RequestMapping(value = {"/index.html"},method = RequestMethod.GET)
    public String indexHtml(Model model, HttpServletRequest request){
        CmsSiteVO cmsSiteVO = getSiteByDomain(request);
        return indexStatic(cmsSiteVO,request,model);
    }

    /**
     * 动态首页
     * @return
     */
    @RequestMapping(value = {"/index.htm"},method = RequestMethod.GET)
    public String indexHtm(Model model, HttpServletRequest request){
        CmsSiteVO cmsSiteVO = getSiteByDomain(request);
        return indexDynamic(cmsSiteVO,model);
    }
    /**
     * 上下文首页
     * @return
     */
    @RequestMapping(value = {"/{siteContextPath}"},method = RequestMethod.GET)
    public String indexEmptyContext(@PathVariable  String siteContextPath,Model model, HttpServletRequest request){
        return indexHtmlContext(siteContextPath,model,request);
    }
    /**
     * 上下文首页
     * @return
     */
    @RequestMapping(value = {"/{siteContextPath}/"},method = RequestMethod.GET)
    public String indexRootContext(@PathVariable  String siteContextPath,Model model, HttpServletRequest request){
        return indexHtmlContext(siteContextPath,model,request);
    }

    /**
     * 上下文静态首页
     * @return
     */
    @RequestMapping(value = {"/{siteContextPath}/index.html"},method = RequestMethod.GET)
    public String indexHtmlContext(@PathVariable  String siteContextPath,Model model, HttpServletRequest request){
        //根据上下文查找站点
        CmsSiteVO cmsSiteVO = getSiteByPath(NetPathTool.ensureBeginSlash(siteContextPath));
        return indexStatic(cmsSiteVO,request,model);
    }

    /**
     * 上下文动态首页
     * @return
     */
    @RequestMapping(value = {"/{siteContextPath}/index.htm"},method = RequestMethod.GET)
    public String indexHtmContext(@PathVariable  String siteContextPath,Model model, HttpServletRequest request){
        //根据上下文查找站点
        CmsSiteVO cmsSiteVO = getSiteByPath(NetPathTool.ensureBeginSlash(siteContextPath));
        return indexDynamic(cmsSiteVO,model);
    }
    private String indexStatic(CmsSiteVO cmsSiteVO ,HttpServletRequest request,Model model){
        if (cmsSiteVO != null) {
            //检查是否存在静态页，如果存在静态页
            String webAppRealPath = RequestTool.getWebappRealPath(request);
            String indexRealPath = FilePathTool.concat(webAppRealPath, cmsSiteVO.getStaticPath(), CmsConstants.templateIndexHtml);
            File indexFile = FileUtils.getFile(indexRealPath);
            // 如果存在静态首页，直接重定向到静态首页
            if (indexFile.exists()) {
                String redirect = NetPathTool.concat(cmsSiteVO.getStaticPath() + CmsConstants.templateIndexHtml);
                return "redirect:" + NetPathTool.ensureBeginSlash(redirect);
            }
            //如果静态页不存在，返回动态页内容
            return indexDynamic(cmsSiteVO,model);
        }else {
            return getSite404IndexPath(cmsSiteVO);
        }
    }
    private String indexDynamic(CmsSiteVO cmsSiteVO ,Model model){
        if (cmsSiteVO != null) {
            //存在站点
            // 查找模板
            model.addAttribute(CmsConstants.model_site, CmsSiteTemplateModelVO.createByCmsSiteVO(cmsSiteVO));

            return getTemplate(cmsSiteVO, null, null, cmsSiteVO.getTemplateIndex(), CmsConstants.templateIndexHtml);
        }else {
            return getSite404IndexPath(cmsSiteVO);
        }
    }
}
