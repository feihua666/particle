package com.particle.navigation.adapter.web.front;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.global.tool.str.NetPathTool;
import com.particle.navigation.infrastructure.dos.*;
import com.particle.navigation.infrastructure.service.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * web 端 front 首页入口
 * </p>
 *
 * @author yangwei
 * @since 2024/10/24 10:09
 */
@Controller
public class NavigationFrontWebController {

    @Autowired
    private INavigationCategoryService navigationCategoryService;
    @Autowired
    private INavigationSiteService navigationSiteService;
    @Autowired
    private INavigationSiteCategoryRelService iNavigationSiteCategoryRelService;
    @Autowired
    private INavigationFriendshipLinkService iNavigationFriendshipLinkService;
    @Autowired
    private INavigationStaticDeployService iNavigationStaticDeployService;

    /**
     * 注意该路径和resources/nav路径有关系，必须保持名称一样
     */
    public final static String frontSubContextPathForBackend = "/nav";
    /**
     * 详情页目录路径
     */
    public final static String frontDetailPath = "/detail";

    /**
     * 用来动态配置
     */
    private final static String backend_static_deploy = "backend_static_deploy";

    /**
     * 前面需要带 斜杠
     */
    @Value("${server.servlet.context-path:}")
    private String contextPath;

    /**
     * 缓存11秒,11为质数
     */
    private static TimedCache<String, DataDTO> dataDTOCache = CacheUtil.newTimedCache(11000);


    /**
     * 所有的模板存储路径
     * freemarker 集成 springboot 默认在 resources/templates 下,我们这里配置了 resources/templates/navigation/ 作为导航的根模板路径
     */
    private final String prefix = "navigation/";

    /**
     * 导航首页入口
     * @param model
     * @return
     */
    @GetMapping({frontSubContextPathForBackend,frontSubContextPathForBackend + "/index.html"})
    public String index(Model model) {
        DataDTO dataDTO = loadDataWithCache();
        DeployConfig deployConfig = initDataDTOFrontForBackend();
        return index(model, dataDTO, deployConfig);

    }

    /**
     * 首页数据设置
     * @param model
     * @param dataDTO
     * @param deployConfig
     * @return
     */
    public String index(Model model,DataDTO dataDTO,DeployConfig deployConfig) {

        // 根分类
        model.addAttribute("rootCategoryDOS", dataDTO.getRootCategoryDOS());
        // 非根分类
        model.addAttribute("notRootCategoryParentIdGroupByMap", dataDTO.getNotRootCategoryParentIdGroupByMap());
        // 分类与网站信息，按分类id分组的存储map
        model.addAttribute("categoryIdGroupBySiteMap", dataDTO.getCategoryIdGroupBySiteMap());
        // 友情链接
        model.addAttribute("navigationFriendshipLinkDOS", dataDTO.getNavigationFriendshipLinkDOS());

        setDeDeployConfig(model, deployConfig);
        model.addAttribute("frontDetailPath", frontDetailPath);
        return view("index");
    }

    /**
     * 网站详情页
     * @param siteId
     * @param model
     * @return
     */
    @GetMapping({frontSubContextPathForBackend + frontDetailPath + "/{siteId}",frontSubContextPathForBackend + frontDetailPath + "/{siteId}.html"})
    public String detail(@PathVariable("siteId") Long siteId, Model model) {

        DataDTO dataDTO = loadDataWithCache();
        DeployConfig deployConfig = initDataDTOFrontForBackend();

        return detail(siteId,model, dataDTO, deployConfig);
    }

    /**
     * 详情页设置
     * @param model
     * @param siteId
     * @param dataDTO
     * @param deployConfig
     * @return
     */
    public String detail(Long siteId,Model model,DataDTO dataDTO,DeployConfig deployConfig) {

        NavigationSiteDO navigationSiteDO = dataDTO.getSiteIdMap().get(siteId);

        // 当前站点信息
        model.addAttribute("siteDO", navigationSiteDO);
        // 根分类
        model.addAttribute("rootCategoryDOS", dataDTO.getRootCategoryDOS());
        // 非根分类
        model.addAttribute("notRootCategoryParentIdGroupByMap", dataDTO.getNotRootCategoryParentIdGroupByMap());

        List<NavigationCategoryDO> currentSiteCategoryDOS = dataDTO.getSiteIdGroupByCategoryMap().get(siteId);
        // 当前网站所属的分类
        model.addAttribute("currentSiteCategoryDOS", currentSiteCategoryDOS);

        // 当前网站相关的网站处理，默认10条
        int relatedNum = 10;
        List<NavigationSiteDO> currentSiteRelatedSiteDOS = new ArrayList<>(relatedNum);
        if (currentSiteCategoryDOS != null) {
            for (NavigationCategoryDO currentSiteCategoryDO : currentSiteCategoryDOS) {
                List<NavigationSiteDO> navigationSiteDOS = dataDTO.getCategoryIdGroupBySiteMap().get(currentSiteCategoryDO.getId());
                for (NavigationSiteDO siteDO : navigationSiteDOS) {
                    if (currentSiteRelatedSiteDOS.size() >= relatedNum) {
                        break;
                    }
                    if (!siteDO.getId().equals(navigationSiteDO.getId())) {
                        currentSiteRelatedSiteDOS.add(siteDO);
                    }
                }
            }
        }

        // 当前网站相关的网站
        model.addAttribute("currentSiteRelatedSiteDOS", currentSiteRelatedSiteDOS);

        setDeDeployConfig(model, deployConfig);
        model.addAttribute("frontDetailPath", frontDetailPath);
        return view("detail");
    }
    /**
     * 关于我们
     * @param model
     * @return
     */
    @GetMapping({frontSubContextPathForBackend + "/about",frontSubContextPathForBackend + "/about.html"})
    public String about(Model model) {

        DataDTO dataDTO = loadDataWithCache();
        DeployConfig deployConfig = initDataDTOFrontForBackend();

        return about(model, dataDTO, deployConfig);
    }

    /**
     * 关于我们设置
     * @param model
     * @param dataDTO
     * @param deployConfig
     * @return
     */
    public String about(Model model,DataDTO dataDTO,DeployConfig deployConfig) {

        // 根分类
        model.addAttribute("rootCategoryDOS", dataDTO.getRootCategoryDOS());
        // 非根分类
        model.addAttribute("notRootCategoryParentIdGroupByMap", dataDTO.getNotRootCategoryParentIdGroupByMap());
        // 分类与网站信息，按分类id分组的存储map
        model.addAttribute("categoryIdGroupBySiteMap", dataDTO.getCategoryIdGroupBySiteMap());

        setDeDeployConfig(model, deployConfig);
        model.addAttribute("frontDetailPath", frontDetailPath);
        return view("about");
    }

    /**
     * 设置部署相关配置
     * @param model
     * @param deployConfig
     */
    private void setDeDeployConfig(Model model,DeployConfig deployConfig) {
        model.addAttribute("frontFinalAbsoluteContextPath", deployConfig.getFrontFinalAbsoluteContextPath());
        model.addAttribute("frontFinalAbsoluteSubContextPath", deployConfig.getFrontFinalAbsoluteSubContextPath());
        model.addAttribute("frontIndexAbsolutePath", deployConfig.getFrontIndexAbsolutePath());
    }

    /**
     * 初始化前端相关数据，仅限于后台动态调用使用
     */
    private DeployConfig initDataDTOFrontForBackend() {

        DeployConfig deployConfig = new DeployConfig();
        NavigationStaticDeployDO byCode = iNavigationStaticDeployService.getByCode(backend_static_deploy);
        if (byCode != null) {
            deployConfig.setFrontDomain(byCode.getFrontDomain());
            deployConfig.setFrontContextPath(byCode.getFrontContextPath());
            deployConfig.setFrontSubContextPath(byCode.getFrontSubContextPath());
            return deployConfig;
        }


        deployConfig.setFrontDomain(null);
        deployConfig.setFrontContextPath(contextPath);
        deployConfig.setFrontSubContextPath("/nav");

        return deployConfig;
    }

    /**
     * 加载数据
     * @return
     */
    public DataDTO doLoadData() {

        DataDTO dataDTO = new DataDTO();

        // 先查询所有的数据
        List<NavigationCategoryDO> navigationCategoryDOS = navigationCategoryService.list(Wrappers.<NavigationCategoryDO>lambdaQuery().orderByAsc(NavigationCategoryDO::getSeq));
        List<NavigationSiteDO> navigationSiteDOS = navigationSiteService.list(Wrappers.<NavigationSiteDO>lambdaQuery().eq(NavigationSiteDO::getIsPublished,true));
        List<NavigationSiteCategoryRelDO> navigationSiteCategoryRelDOS = iNavigationSiteCategoryRelService.list(Wrappers.<NavigationSiteCategoryRelDO>lambdaQuery().orderByAsc(NavigationSiteCategoryRelDO::getSeq));
        List<NavigationFriendshipLinkDO> navigationFriendshipLinkDOS = iNavigationFriendshipLinkService.list(Wrappers.<NavigationFriendshipLinkDO>lambdaQuery().eq(NavigationFriendshipLinkDO::getIsPublished, true));

        dataDTO.setNavigationCategoryDOS(navigationCategoryDOS);
        dataDTO.setNavigationSiteDOS(navigationSiteDOS);
        dataDTO.setNavigationSiteCategoryRelDOS(navigationSiteCategoryRelDOS);
        dataDTO.setNavigationFriendshipLinkDOS(navigationFriendshipLinkDOS);



        // 网站信息，按id 转 map
        Map<Long, NavigationSiteDO> siteIdMap = navigationSiteDOS.stream().collect(Collectors.toMap(NavigationSiteDO::getId, Function.identity()));
        Map<Long, NavigationCategoryDO> categoryIdMap = navigationCategoryDOS.stream().collect(Collectors.toMap(NavigationCategoryDO::getId, Function.identity()));

        dataDTO.setSiteIdMap(siteIdMap);
        dataDTO.setCategoryIdMap(categoryIdMap);

        // 根分类
        List<NavigationCategoryDO> rootCategoryDOS = navigationCategoryDOS.stream().filter(item -> item.getParentId() == null).collect(Collectors.toList());
        // 非根分类
        List<NavigationCategoryDO> notRootCategoryDOS = navigationCategoryDOS.stream().filter(item -> item.getParentId() != null).collect(Collectors.toList());
        // 非根分类按parentId分组
        Map<Long, List<NavigationCategoryDO>> notRootCategoryParentIdGroupByMap = notRootCategoryDOS.stream().collect(Collectors.groupingBy(NavigationCategoryDO::getParentId));

        dataDTO.setRootCategoryDOS(rootCategoryDOS);
        dataDTO.setNotRootCategoryDOS(notRootCategoryDOS);
        dataDTO.setNotRootCategoryParentIdGroupByMap(notRootCategoryParentIdGroupByMap);


        // 网站信息与分类关系，按分类id分组
        Map<Long, List<NavigationSiteCategoryRelDO>> categoryIdGroupBySiteCategoryRelMap = navigationSiteCategoryRelDOS.stream().collect(Collectors.groupingBy(NavigationSiteCategoryRelDO::getNavigationCategoryId));
        // 网站信息与分类关系，按网站id分组
        Map<Long, List<NavigationSiteCategoryRelDO>> siteIdGroupBySiteCategoryRelMap = navigationSiteCategoryRelDOS.stream().collect(Collectors.groupingBy(NavigationSiteCategoryRelDO::getNavigationSiteId));

        // 分类与网站信息，按分类 id 分组的存储map
        Map<Long, List<NavigationSiteDO>> categoryIdGroupBySiteMap = new HashMap<>();
        categoryIdGroupBySiteCategoryRelMap.forEach((categoryId, relList) -> {
            List<NavigationSiteDO> siteList = relList.stream().map(item -> siteIdMap.get(item.getNavigationSiteId())).collect(Collectors.toList());
            categoryIdGroupBySiteMap.put(categoryId, siteList);
        });
        // 分类与网站信息，按网站 id 分组的存储map
        Map<Long, List<NavigationCategoryDO>> siteIdGroupByCategoryMap = new HashMap<>();
        for (NavigationSiteDO navigationSiteDO : navigationSiteDOS) {
            List<NavigationSiteCategoryRelDO> siteCategoryRelDOS = siteIdGroupBySiteCategoryRelMap.get(navigationSiteDO.getId());
            if (siteCategoryRelDOS == null) {
                continue;
            }
            List<NavigationCategoryDO> navigationCategoryDOList = siteCategoryRelDOS.stream().map(item -> categoryIdMap.get(item.getNavigationCategoryId())).collect(Collectors.toList());
            siteIdGroupByCategoryMap.put(navigationSiteDO.getId(), navigationCategoryDOList);
        }

        dataDTO.setCategoryIdGroupBySiteMap(categoryIdGroupBySiteMap);
        dataDTO.setCategoryIdGroupBySiteCategoryRelMap(categoryIdGroupBySiteCategoryRelMap);
        dataDTO.setSiteIdGroupBySiteCategoryRelMap(siteIdGroupBySiteCategoryRelMap);
        dataDTO.setSiteIdGroupByCategoryMap(siteIdGroupByCategoryMap);

        return dataDTO;
    }
    /**
     * 加载数据
     * @return
     */
    private DataDTO loadDataWithCache() {


        DataDTO cachedDataDTO = dataDTOCache.get("cacheKey");
        if (cachedDataDTO != null) {
            return cachedDataDTO;
        }
        DataDTO dataDTO = doLoadData();

        dataDTOCache.put("cacheKey", dataDTO);
        return dataDTO;
    }

    /**
     * 部署配置信息
     */
    @Data
    public static class DeployConfig {
        /**
         * 前端域名地址，如：http://www.example.com
         */
        String frontDomain;
        /**
         * 前端上下文路径，如：/nav
         */
        String frontContextPath;
        /**
         * 前端上下文路径的下一级路径，如：/nav
         */
        String frontSubContextPath;


        public String getFrontFinalAbsoluteContextPath() {
            return NetPathTool.concat(frontDomain,frontContextPath);
        }

        public String getFrontFinalAbsoluteSubContextPath() {
            return NetPathTool.concat(this.getFrontFinalAbsoluteContextPath(),frontSubContextPath);
        }

        public String getFrontIndexAbsolutePath() {
            return this.getFrontFinalAbsoluteSubContextPath();
        }
    }
    /**
     * 模板视图
     */
    @Data
    public static class DataDTO {

        /**
         * 所有的分类
         */
        List<NavigationCategoryDO> navigationCategoryDOS;
        /**
         * 所有的网站与分类关系
         */
        List<NavigationSiteCategoryRelDO> navigationSiteCategoryRelDOS;
        /**
         * 所有的网站
         */
        List<NavigationSiteDO> navigationSiteDOS;
        /**
         * 所有的友情链接
         */
        List<NavigationFriendshipLinkDO> navigationFriendshipLinkDOS;

        /**
         * 根级分类
         */
        List<NavigationCategoryDO> rootCategoryDOS;
        /**
         * 非根级分类
         */
        List<NavigationCategoryDO> notRootCategoryDOS;

        /**
         * 非根级分类按parentId分组
         */
        Map<Long, List<NavigationCategoryDO>> notRootCategoryParentIdGroupByMap;

        /**
         * 网站信息，按id转map
         */
        Map<Long, NavigationSiteDO> siteIdMap;
        /**
         * 分类信息，按id转map
         */
        Map<Long, NavigationCategoryDO> categoryIdMap;

        /**
         * 分类与网站信息，按分类id分组的存储map
         */
        Map<Long, List<NavigationSiteDO>> categoryIdGroupBySiteMap;

        /**
         * 网站信息与分类关系，按分类id分组
         */
        Map<Long, List<NavigationSiteCategoryRelDO>> categoryIdGroupBySiteCategoryRelMap;
        /**
         * 网站信息与分类关系，按网站id分组
         */
        Map<Long, List<NavigationSiteCategoryRelDO>> siteIdGroupBySiteCategoryRelMap;

        /**
         * 网站与分类关系，按网站id分组
         */
        Map<Long, List<NavigationCategoryDO>> siteIdGroupByCategoryMap;

    }
    /**
     * 拼接视图名称
     * @param viewName
     * @return
     */
    private String view(String viewName) {
        return prefix + viewName;
    }
}
