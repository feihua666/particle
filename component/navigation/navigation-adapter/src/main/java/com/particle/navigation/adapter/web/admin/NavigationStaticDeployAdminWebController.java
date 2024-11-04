package com.particle.navigation.adapter.web.admin;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.dataconstraint.DataConstraintContext;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.tool.http.HttpClientTool;
import com.particle.global.tool.str.FilePathTool;
import com.particle.navigation.adapter.web.front.NavigationFrontWebController;
import com.particle.navigation.client.api.INavigationStaticDeployApplicationService;
import com.particle.navigation.client.api.representation.INavigationStaticDeployRepresentationApplicationService;
import com.particle.navigation.client.dto.command.NavigationStaticDeployCreateCommand;
import com.particle.navigation.client.dto.command.NavigationStaticDeployDoDeployCommand;
import com.particle.navigation.client.dto.command.NavigationStaticDeployUpdateCommand;
import com.particle.navigation.client.dto.command.representation.NavigationStaticDeployPageQueryCommand;
import com.particle.navigation.client.dto.command.representation.NavigationStaticDeployQueryListCommand;
import com.particle.navigation.client.dto.data.NavigationStaticDeployVO;
import com.particle.navigation.infrastructure.dos.NavigationCategoryDO;
import com.particle.navigation.infrastructure.dos.NavigationSiteCategoryRelDO;
import com.particle.navigation.infrastructure.dos.NavigationSiteDO;
import freemarker.template.TemplateException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/**
 * <p>
 * 导航网站静态部署后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-11-01 10:02:52
 */
@Slf4j
@Tag(name = "导航网站静态部署pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/navigation_static_deploy")
public class NavigationStaticDeployAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private INavigationStaticDeployApplicationService iNavigationStaticDeployApplicationService;
    @Autowired
    private INavigationStaticDeployRepresentationApplicationService iNavigationStaticDeployRepresentationApplicationService;
    @Autowired
    private NavigationFrontWebController navigationFrontWebController;
    @Autowired
    private freemarker.template.Configuration configuration;
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private FreeMarkerProperties freeMarkerProperties;


    @PreAuthorize("hasAuthority('admin:web:navigationStaticDeploy:create')")
    @Operation(summary = "添加导航网站静态部署")
    @PostMapping("/create")
    @OpLog(name = "添加导航网站静态部署",module = OpLogConstants.Module.navigation,type = OpLogConstants.Type.create)
    public SingleResponse<NavigationStaticDeployVO> create(@RequestBody NavigationStaticDeployCreateCommand navigationStaticDeployCreateCommand){
        return iNavigationStaticDeployApplicationService.create(navigationStaticDeployCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationStaticDeploy:delete')")
    @Operation(summary = "删除导航网站静态部署")
    @DeleteMapping("/delete")
    @OpLog(name = "删除导航网站静态部署",module = OpLogConstants.Module.navigation,type = OpLogConstants.Type.delete)
    public SingleResponse<NavigationStaticDeployVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iNavigationStaticDeployApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationStaticDeploy:update')")
    @Operation(summary = "更新导航网站静态部署")
    @PutMapping("/update")
    @OpLog(name = "更新导航网站静态部署",module = OpLogConstants.Module.navigation,type = OpLogConstants.Type.update)
    public SingleResponse<NavigationStaticDeployVO> update(@RequestBody NavigationStaticDeployUpdateCommand navigationStaticDeployUpdateCommand){
        navigationStaticDeployUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iNavigationStaticDeployApplicationService.update(navigationStaticDeployUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationStaticDeploy:update')")
    @Operation(summary = "导航网站静态部署更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<NavigationStaticDeployVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iNavigationStaticDeployRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationStaticDeploy:detail')")
    @Operation(summary = "导航网站静态部署详情展示")
    @GetMapping("/detail")
    public SingleResponse<NavigationStaticDeployVO> queryDetail(IdCommand detailCommand){
        return iNavigationStaticDeployRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationStaticDeploy:queryList')")
    @Operation(summary = "列表查询导航网站静态部署")
    @GetMapping("/list")
    public MultiResponse<NavigationStaticDeployVO> queryList(NavigationStaticDeployQueryListCommand navigationStaticDeployQueryListCommand){
        navigationStaticDeployQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iNavigationStaticDeployRepresentationApplicationService.queryList(navigationStaticDeployQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationStaticDeploy:pageQuery')")
    @Operation(summary = "分页查询导航网站静态部署")
    @GetMapping("/page")
    public PageResponse<NavigationStaticDeployVO> pageQueryList(NavigationStaticDeployPageQueryCommand navigationStaticDeployPageQueryCommand){
        navigationStaticDeployPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iNavigationStaticDeployRepresentationApplicationService.pageQuery(navigationStaticDeployPageQueryCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:navigationStaticDeploy:deploy')")
    @Operation(summary = "导航网站静态部署")
    @PostMapping("/deploy")
    @OpLog(name = "导航网站静态部署",module = OpLogConstants.Module.navigation,type = OpLogConstants.Type.other)
    public Response deploy(@RequestBody NavigationStaticDeployDoDeployCommand doDeployCommand){

        IdCommand idCommand = new IdCommand();
        idCommand.setId(doDeployCommand.getId());

        SingleResponse<NavigationStaticDeployVO> navigationStaticDeployVOSingleResponse = iNavigationStaticDeployRepresentationApplicationService.queryDetail(idCommand);
        NavigationStaticDeployVO navigationStaticDeployVO = navigationStaticDeployVOSingleResponse.getData();

        // 实际部署
        doDeploy(navigationStaticDeployVO,doDeployCommand.getIsIncrementDeploy());

        // 更新部署时间
        iNavigationStaticDeployApplicationService.updateLastDeployAt(idCommand, LocalDateTime.now());
        return Response.buildSuccess();
    }

    /**
     * 部署
     * @param navigationStaticDeployVO
     * @param isIncrementDeploy
     */
    @SneakyThrows
    private void doDeploy(NavigationStaticDeployVO navigationStaticDeployVO, Boolean isIncrementDeploy){
        String deployPath = navigationStaticDeployVO.getDeployPath();

        log.info("doDeploy start，deployPath：{}",deployPath);
        NavigationFrontWebController.DataDTO dataDTO = navigationFrontWebController.doLoadData();
        NavigationFrontWebController.DeployConfig deployConfig = deployConfig(navigationStaticDeployVO);

        LocalDateTime lastDeployAt = navigationStaticDeployVO.getLastDeployAt();
        if (lastDeployAt == null || !isIncrementDeploy) {
            //全部静态资源
            log.info("doDeploy all static");
            copyStaticFiles(deployPath);

        }

        // 详情页,必须先渲染详情页，因为详情页在部署的同时修改了logoUrl等数据
        for (NavigationSiteDO navigationSiteDO : dataDTO.getNavigationSiteDOS()) {
            // 按网站考虑增量情况
            if (!isIncrementDeploy || lastDeployAt == null || (isIncrementDeploy && lastDeployAt.isBefore(navigationSiteDO.getUpdateAt()))) {
                doDeploySiteDetail(navigationSiteDO.getId(), deployPath, dataDTO, deployConfig,navigationStaticDeployVO.getIsPureStaticDeploy());
            }
        }
        // 增量情况
        if (isIncrementDeploy && lastDeployAt != null){
            // 按分类考虑增量情况
            for (NavigationCategoryDO navigationCategoryDO : dataDTO.getNavigationCategoryDOS()) {
                if (lastDeployAt.isBefore(navigationCategoryDO.getUpdateAt())) {
                    List<NavigationSiteDO> navigationSiteDOS = dataDTO.getCategoryIdGroupBySiteMap().get(navigationCategoryDO.getId());
                    if (CollectionUtil.isNotEmpty(navigationSiteDOS)) {
                        for (NavigationSiteDO navigationSiteDO : navigationSiteDOS) {
                            doDeploySiteDetail(navigationSiteDO.getId(), deployPath, dataDTO, deployConfig,navigationStaticDeployVO.getIsPureStaticDeploy());
                        }
                    }
                }
            }
            // 按分类和网站关系考虑增量情况
            for (NavigationSiteCategoryRelDO navigationSiteCategoryRelDO : dataDTO.getNavigationSiteCategoryRelDOS()) {
                if (lastDeployAt.isBefore(navigationSiteCategoryRelDO.getUpdateAt())) {
                    List<NavigationSiteDO> navigationSiteDOS = dataDTO.getCategoryIdGroupBySiteMap().get(navigationSiteCategoryRelDO.getNavigationCategoryId());
                    if (CollectionUtil.isNotEmpty(navigationSiteDOS)) {
                        for (NavigationSiteDO navigationSiteDO : navigationSiteDOS) {
                            doDeploySiteDetail(navigationSiteDO.getId(), deployPath, dataDTO, deployConfig,navigationStaticDeployVO.getIsPureStaticDeploy());
                        }
                    }
                }
            }
        }

        // 首页
        String indexPath = deployPath + "/index.html";
        log.info("doDeploy index:{}",indexPath);
        ExtendedModelMap indexModel = new ExtendedModelMap();
        String index = navigationFrontWebController.index(indexModel, dataDTO, deployConfig);
        index = buildViewName(index);
        configuration.getTemplate(index).process(indexModel, new FileWriter(indexPath));

        // 关于我们
        String aboutPath = deployPath + "/about.html";
        log.info("doDeploy about:{}",aboutPath);
        ExtendedModelMap aboutModel = new ExtendedModelMap();
        String about = navigationFrontWebController.about(aboutModel, dataDTO, deployConfig);
        about = buildViewName(about);
        configuration.getTemplate(about).process(aboutModel, new FileWriter(aboutPath));

    }


    /**
     * 部署一个网站详情页
     * @param siteId
     * @param deployPath
     * @param dataDTO
     * @param deployConfig
     * @throws IOException
     * @throws TemplateException
     */
    private void doDeploySiteDetail(Long siteId, String deployPath,
                                    NavigationFrontWebController.DataDTO dataDTO,
                                    NavigationFrontWebController.DeployConfig deployConfig,
                                    Boolean isPureStaticDeploy) throws IOException, TemplateException {


        if (isPureStaticDeploy) {
            doDeploySiteDetailImages(siteId, deployPath,dataDTO,deployConfig);
        }

        String detailPath = deployPath + NavigationFrontWebController.frontDetailPath + "/" + siteId + ".html";
        FileUtil.mkParentDirs(detailPath);
        log.info("doDeploy detail:{}",detailPath);
        ExtendedModelMap detailModel = new ExtendedModelMap();
        String detail = navigationFrontWebController.detail(siteId, detailModel,dataDTO,deployConfig);
        detail = buildViewName(detail);
        configuration.getTemplate(detail).process(detailModel, new FileWriter(detailPath));

    }

    /**
     * 纯静态部署图片
     * @param siteId
     * @param deployPath
     * @param dataDTO
     */
    private void doDeploySiteDetailImages(Long siteId, String deployPath,
                                          NavigationFrontWebController.DataDTO dataDTO,
                                          NavigationFrontWebController.DeployConfig deployConfig) {
        NavigationSiteDO navigationSiteDO = dataDTO.getSiteIdMap().get(siteId);
        String logoUrl = navigationSiteDO.getLogoUrl();
        String screenshotUrl = navigationSiteDO.getScreenshotUrl();
        String screenshotThumbnailUrl = navigationSiteDO.getScreenshotThumbnailUrl();


        if (StrUtil.isNotEmpty(logoUrl) && logoUrl.startsWith("http")) {
            String name = parseHttpUrlToPath(logoUrl);
            String path = "/assets/images/logos/" + name;
            String logoPath = deployPath + path;
            FileUtil.mkParentDirs(logoPath);
            try {
                byte[] download = HttpClientTool.download(logoUrl, null);
                FileUtil.writeBytes(download, logoPath);

                navigationSiteDO.setLogoUrl(deployConfig.getFrontFinalAbsoluteSubContextPath() + path);
            } catch (IOException e) {
                log.error("logo download error by url={}",logoUrl, e);
            }
        }

        if (StrUtil.isNotEmpty(screenshotUrl) && screenshotUrl.startsWith("http")) {
            String name = parseHttpUrlToPath(screenshotUrl);
            String path = "/assets/images/screenshot/" + name;
            String screenshotPath = deployPath + path;
            FileUtil.mkParentDirs(screenshotPath);
            try {
                byte[] download = HttpClientTool.download(screenshotUrl, null);
                FileUtil.writeBytes(download, screenshotPath);

                navigationSiteDO.setScreenshotUrl(deployConfig.getFrontFinalAbsoluteSubContextPath() + path);
            } catch (IOException e) {
                log.error("screenshot download error by url={}",screenshotUrl, e);
            }
        }
        if (StrUtil.isNotEmpty(screenshotThumbnailUrl) && screenshotThumbnailUrl.startsWith("http")) {
            String name = parseHttpUrlToPath(screenshotThumbnailUrl);
            String path = "/assets/images/screenshot/" + name;
            String screenshotThumbnailPath = deployPath + path;
            FileUtil.mkParentDirs(screenshotThumbnailPath);
            try {
                byte[] download = HttpClientTool.download(screenshotThumbnailUrl, null);
                FileUtil.writeBytes(download, screenshotThumbnailPath);

                navigationSiteDO.setScreenshotThumbnailUrl(deployConfig.getFrontFinalAbsoluteSubContextPath() + path);
            } catch (IOException e){
                log.error("screenshotThumbnail download error by url={}",screenshotThumbnailUrl, e);
            }
        }
    }


    /**
     * 解析url为简单路径地址
     * @param url 如：http://example.com/xxx/bbb
     * @return /xxx/bbb
     */
    private String parseHttpUrlToPath(String url) {
        String substring = url.substring(url.indexOf("://") + 3);
        return substring.substring(substring.indexOf("/"));
    }
    /**
     * 构建模板文件名
     * @param viewName
     * @return
     */
    private String buildViewName(String viewName) {
        return freeMarkerProperties.getPrefix() + viewName + freeMarkerProperties.getSuffix();
    }
    /**
     * 部署配置
     * @param navigationStaticDeployVO
     * @return
     */
    private NavigationFrontWebController.DeployConfig deployConfig(NavigationStaticDeployVO navigationStaticDeployVO) {
        NavigationFrontWebController.DeployConfig deployConfig = new NavigationFrontWebController.DeployConfig();
        deployConfig.setFrontDomain(navigationStaticDeployVO.getFrontDomain());
        deployConfig.setFrontContextPath(navigationStaticDeployVO.getFrontContextPath());
        deployConfig.setFrontSubContextPath(navigationStaticDeployVO.getFrontSubContextPath());
        return deployConfig;
    }

    @SneakyThrows
    private void copyStaticFiles(String deployPath) {
        String navigationPath = NavigationFrontWebController.frontSubContextPathForBackend;
        List<String> listFiles = listFiles(navigationPath);
        for (String listFile : listFiles) {
            log.info("copy file:{}",listFile);
            InputStream stream = ResourceUtil.getStream(listFile);
            String substring = listFile.substring(listFile.lastIndexOf("/static" + navigationPath));
            substring = substring.substring(substring.indexOf(navigationPath) + navigationPath.length());

            String deployAbsolutePath = FilePathTool.concat(deployPath, substring);
            FileUtil.writeFromStream(stream, deployAbsolutePath);
        }
    }
    /**
     * 获取指定目录下的所有文件路径
     * @param directory
     * @return
     * @throws IOException
     */
    private List<String> listFiles(String directory) throws IOException {
        List<String> fileList = new ArrayList<>();
        Resource resource = resourceLoader.getResource("classpath:static" + directory + "/");

        if (resource.exists()) {
            Path path = Paths.get(resource.getURI());
            try (Stream<Path> paths = Files.walk(path)) {
                fileList = paths
                        .filter(Files::isRegularFile)
                        .map(Path::toString)
                        .collect(Collectors.toList());
            }
        }
        return fileList;
    }
}
