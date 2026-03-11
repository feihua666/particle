package com.particle.cms.app.api.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.cms.app.executor.representation.*;
import com.particle.cms.app.structmapping.CmsChannelAppStructMapping;
import com.particle.cms.app.structmapping.CmsContentAppStructMapping;
import com.particle.cms.app.structmapping.CmsSiteAppStructMapping;
import com.particle.cms.client.api.ICmsDynamicApplicationService;
import com.particle.cms.client.dto.command.directive.CmsChannelDirectivePageQueryCommand;
import com.particle.cms.client.dto.command.directive.CmsContentCategoryDirectivePageQueryCommand;
import com.particle.cms.client.dto.command.directive.CmsContentDirectivePageQueryCommand;
import com.particle.cms.client.dto.command.directive.CmsSiteDirectivePageQueryCommand;
import com.particle.cms.client.dto.command.representation.*;
import com.particle.cms.client.dto.data.*;
import com.particle.cms.infrastructure.dos.CmsChannelDO;
import com.particle.cms.infrastructure.dos.CmsContentDO;
import com.particle.cms.infrastructure.dos.CmsSiteDO;
import com.particle.cms.infrastructure.service.ICmsChannelService;
import com.particle.cms.infrastructure.service.ICmsContentService;
import com.particle.cms.infrastructure.service.ICmsSiteService;
import com.particle.common.client.dto.command.CommonBatchIdCommand;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2025/7/8 16:51
 */
@Service
@CatchAndLog
public class CmsDynamicApplicationServiceImpl implements ICmsDynamicApplicationService {

    private CmsSiteQueryCommandExecutor cmsSiteQueryCommandExecutor;
    private CmsChannelQueryCommandExecutor cmsChannelQueryCommandExecutor;
    private CmsContentQueryCommandExecutor cmsContentQueryCommandExecutor;
    private CmsContentCategoryQueryCommandExecutor cmsContentCategoryQueryCommandExecutor;
    private CmsContentMultimediaQueryCommandExecutor cmsContentMultimediaQueryCommandExecutor;
    private ICmsSiteService iCmsSiteService;
    private ICmsChannelService iCmsChannelService;
    private ICmsContentService iCmsContentService;

    @Override
    public SingleResponse<CmsSiteVO> getSiteByDomainAndSiteContextPath(String domain,String siteContextPath,Boolean isPublic) {
        CmsSiteQueryListCommand cmsSiteQueryListCommand = new CmsSiteQueryListCommand();
        cmsSiteQueryListCommand.setSiteContextPath(siteContextPath);
        cmsSiteQueryListCommand.setDomain(domain);
        cmsSiteQueryListCommand.setIsPublic(isPublic);
        MultiResponse<CmsSiteVO> cmsSiteVOMultiResponse = cmsSiteQueryCommandExecutor.execute(cmsSiteQueryListCommand);
        if (cmsSiteVOMultiResponse.isNotEmpty()) {
            return SingleResponse.of(cmsSiteVOMultiResponse.getData().get(0));
        }
        return SingleResponse.buildSuccess();
    }

    @Override
    public SingleResponse<CmsSiteVO> getSiteById(Long siteId,Boolean isPublic) {
        CmsSiteDO cmsSiteDO = iCmsSiteService.getByIdAndIsPublic(siteId, isPublic);
        CmsSiteVO cmsSiteVO = CmsSiteAppStructMapping.instance.cmsSiteDOToCmsSiteVO(cmsSiteDO);
        return SingleResponse.of(cmsSiteVO);
    }

    @Override
    public SingleResponse<CmsSiteVO> getPrimeSiteByDomain(String domain,Boolean isPublic) {
        CmsSiteQueryListCommand cmsSiteQueryListCommand = new CmsSiteQueryListCommand();
        cmsSiteQueryListCommand.setDomain(domain);
        cmsSiteQueryListCommand.setIsPrimeSite(true);
        cmsSiteQueryListCommand.setIsPublic(isPublic);
        MultiResponse<CmsSiteVO> cmsSiteVOMultiResponse = cmsSiteQueryCommandExecutor.execute(cmsSiteQueryListCommand);
        if (cmsSiteVOMultiResponse.isNotEmpty()) {
            return SingleResponse.of(cmsSiteVOMultiResponse.getData().get(0));
        }
        return SingleResponse.buildSuccess();
    }

    @Override
    public MultiResponse<CmsSiteVO> listSiteByDomain(String domain,Boolean isPublic) {
        CmsSiteQueryListCommand cmsSiteQueryListCommand = new CmsSiteQueryListCommand();
        cmsSiteQueryListCommand.setDomain(domain);
        cmsSiteQueryListCommand.setIsPublic(isPublic);
        MultiResponse<CmsSiteVO> cmsSiteVOMultiResponse = cmsSiteQueryCommandExecutor.execute(cmsSiteQueryListCommand);
        return cmsSiteVOMultiResponse;
    }

    @Override
    public MultiResponse<CmsSiteVO> listSiteByIds(List<Long> siteIds,Boolean isPublic) {
        List<CmsSiteDO> cmsSiteDOs = iCmsSiteService.listByIdsAndIsPublic(siteIds, isPublic);
        List<CmsSiteVO> cmsSiteVOs = CmsSiteAppStructMapping.instance.cmsSiteDOsToCmsSiteVOs(cmsSiteDOs);
        return MultiResponse.of(cmsSiteVOs);
    }

    @Override
    public SingleResponse<CmsChannelVO> getChannelById(Long channelId,Boolean isPublic) {
        CmsChannelDO cmsChannelDO = iCmsChannelService.getByIdAndIsPublic(channelId, isPublic);
        CmsChannelVO cmsChannelVO = CmsChannelAppStructMapping.instance.cmsChannelDOToCmsChannelVO(cmsChannelDO);
        return SingleResponse.of(cmsChannelVO);
    }

    @Override
    public MultiResponse<CmsChannelVO> listChannelByIds(List<Long> channelIds,Boolean isPublic) {
        List<CmsChannelDO> cmsChannelDOS = iCmsChannelService.listByIdsAndIsPublic(channelIds, isPublic);
        List<CmsChannelVO> cmsChannelVOs = CmsChannelAppStructMapping.instance.cmsChannelDOsToCmsChannelVOs(cmsChannelDOS);
        return MultiResponse.of(cmsChannelVOs);
    }

    @Override
    public SingleResponse<CmsChannelVO> getChannelByPathAndSiteId(String channelPath, Long siteId,Boolean isPublic) {
        CmsChannelQueryListCommand cmsChannelQueryListCommand = new CmsChannelQueryListCommand();
        cmsChannelQueryListCommand.setChannelContextPath(channelPath);
        cmsChannelQueryListCommand.setCmsSiteId(siteId);
        cmsChannelQueryListCommand.setIsPublic(isPublic);
        MultiResponse<CmsChannelVO> cmsChannelVOMultiResponse = cmsChannelQueryCommandExecutor.execute(cmsChannelQueryListCommand);
        if (cmsChannelVOMultiResponse.isNotEmpty()) {
            return SingleResponse.of(cmsChannelVOMultiResponse.getData().get(0));
        }
        return SingleResponse.buildSuccess();
    }

    @Override
    public SingleResponse<CmsContentVO> getContentByContentId(Long contentId,Boolean isPublic) {
        CmsContentDO cmsContentDO = iCmsContentService.getByIdAndIsPublic(contentId, isPublic);
        CmsContentVO cmsContentVO = CmsContentAppStructMapping.instance.cmsContentDOToCmsContentVO(cmsContentDO);
        return SingleResponse.of(cmsContentVO);
    }

    @Override
    public MultiResponse<CmsContentVO> listContentByContentIds(List<Long> contentIds,Boolean isPublic) {
        List<CmsContentDO> cmsContentDOS = iCmsContentService.listByIdsAndIsPublic(contentIds, isPublic);
        List<CmsContentVO> cmsContentVOs = CmsContentAppStructMapping.instance.cmsContentDOsToCmsContentVOs(cmsContentDOS);
        return MultiResponse.of(cmsContentVOs);
    }


    @Override
    public MultiResponse<CmsSiteVO> queryListSite(CmsSiteDirectivePageQueryCommand cmsSiteDirectivePageQueryCommand) {
        CmsSiteQueryListCommand cmsSiteQueryListCommand = new CmsSiteQueryListCommand();
        cmsSiteQueryListCommand.setId(cmsSiteDirectivePageQueryCommand.getId());
        cmsSiteQueryListCommand.setIsPrimeSite(cmsSiteDirectivePageQueryCommand.getIsPrimeSite());
        cmsSiteQueryListCommand.setIsPublic(cmsSiteDirectivePageQueryCommand.getIsPublic());
        MultiResponse<CmsSiteVO> cmsSiteVOMultiResponse = cmsSiteQueryCommandExecutor.execute(cmsSiteQueryListCommand);
        return cmsSiteVOMultiResponse;
    }

    @Override
    public PageResponse<CmsSiteVO> pageQuerySite(CmsSiteDirectivePageQueryCommand cmsSiteDirectivePageQueryCommand) {
        CmsSitePageQueryCommand cmsSiteQueryListCommand = new CmsSitePageQueryCommand();
        cmsSiteQueryListCommand.setId(cmsSiteDirectivePageQueryCommand.getId());
        cmsSiteQueryListCommand.setIsPrimeSite(cmsSiteDirectivePageQueryCommand.getIsPrimeSite());
        cmsSiteQueryListCommand.setIsPublic(cmsSiteDirectivePageQueryCommand.getIsPublic());


        cmsSiteQueryListCommand.setPageNo(cmsSiteDirectivePageQueryCommand.getPageNo());
        cmsSiteQueryListCommand.setPageSize(cmsSiteDirectivePageQueryCommand.getPageSize());

        cmsSiteQueryListCommand.setOrderBy(cmsSiteDirectivePageQueryCommand.getOrderBy());
        PageResponse<CmsSiteVO> cmsSiteVOPageResponse = cmsSiteQueryCommandExecutor.execute(cmsSiteQueryListCommand);
        return cmsSiteVOPageResponse;
    }

    @Override
    public MultiResponse<CmsChannelVO> queryListChannel(CmsChannelDirectivePageQueryCommand cmsChannelDirectivePageQueryCommand) {
        CmsChannelQueryListCommand cmsChannelQueryListCommand = new CmsChannelQueryListCommand();
        cmsChannelQueryListCommand.setId(cmsChannelDirectivePageQueryCommand.getId());
        cmsChannelQueryListCommand.setCmsSiteId(cmsChannelDirectivePageQueryCommand.getCmsSiteId());
        cmsChannelQueryListCommand.setIsPublic(cmsChannelDirectivePageQueryCommand.getIsPublic());

        cmsChannelQueryListCommand.setLevel(cmsChannelDirectivePageQueryCommand.getLevel());
        cmsChannelQueryListCommand.setParentId(cmsChannelDirectivePageQueryCommand.getParentId());
        MultiResponse<CmsChannelVO> cmsChannelVOMultiResponse = cmsChannelQueryCommandExecutor.execute(cmsChannelQueryListCommand);
        return cmsChannelVOMultiResponse;
    }

    @Override
    public PageResponse<CmsChannelVO> pageQueryChannel(CmsChannelDirectivePageQueryCommand cmsChannelDirectivePageQueryCommand) {
        CmsChannelPageQueryCommand cmsChannelQueryListCommand = new CmsChannelPageQueryCommand();
        cmsChannelQueryListCommand.setId(cmsChannelDirectivePageQueryCommand.getId());
        cmsChannelQueryListCommand.setCmsSiteId(cmsChannelDirectivePageQueryCommand.getCmsSiteId());

        cmsChannelQueryListCommand.setLevel(cmsChannelDirectivePageQueryCommand.getLevel());
        cmsChannelQueryListCommand.setIsPublic(cmsChannelDirectivePageQueryCommand.getIsPublic());
        cmsChannelQueryListCommand.setParentId(cmsChannelDirectivePageQueryCommand.getParentId());

        cmsChannelQueryListCommand.setPageNo(cmsChannelDirectivePageQueryCommand.getPageNo());
        cmsChannelQueryListCommand.setPageSize(cmsChannelDirectivePageQueryCommand.getPageSize());

        cmsChannelQueryListCommand.setOrderBy(cmsChannelDirectivePageQueryCommand.getOrderBy());
        PageResponse<CmsChannelVO> cmsChannelVOPageResponse = cmsChannelQueryCommandExecutor.execute(cmsChannelQueryListCommand);
        return cmsChannelVOPageResponse;
    }

    @Override
    public MultiResponse<CmsContentVO> queryListContent(CmsContentDirectivePageQueryCommand cmsContentDirectivePageQueryCommand) {
        CmsContentQueryListCommand cmsContentQueryListCommand = new CmsContentQueryListCommand();
        cmsContentQueryListCommand.setId(cmsContentDirectivePageQueryCommand.getId());
        cmsContentQueryListCommand.setCmsSiteId(cmsContentDirectivePageQueryCommand.getCmsSiteId());
        cmsContentQueryListCommand.setCmsChannelId(cmsContentDirectivePageQueryCommand.getCmsChannelId());
        cmsContentQueryListCommand.setIsChannelIdNull(cmsContentDirectivePageQueryCommand.getIsChannelIdNull());
        cmsContentQueryListCommand.setCmsContentCategoryId(cmsContentDirectivePageQueryCommand.getCmsContentCategoryId());
        cmsContentQueryListCommand.setIsAlsoAsChannel(cmsContentDirectivePageQueryCommand.getIsAlsoAsChannel());
        cmsContentQueryListCommand.setIsPublic(cmsContentDirectivePageQueryCommand.getIsPublic());

        MultiResponse<CmsContentVO> cmsContentVOMultiResponse = cmsContentQueryCommandExecutor.execute(cmsContentQueryListCommand);
        return cmsContentVOMultiResponse;
    }

    @Override
    public PageResponse<CmsContentVO> pageQueryContent(CmsContentDirectivePageQueryCommand cmsContentDirectivePageQueryCommand) {
        CmsContentPageQueryCommand cmsContentQueryListCommand = new CmsContentPageQueryCommand();
        cmsContentQueryListCommand.setId(cmsContentDirectivePageQueryCommand.getId());
        cmsContentQueryListCommand.setCmsSiteId(cmsContentDirectivePageQueryCommand.getCmsSiteId());
        cmsContentQueryListCommand.setCmsChannelId(cmsContentDirectivePageQueryCommand.getCmsChannelId());
        cmsContentQueryListCommand.setIsChannelIdNull(cmsContentDirectivePageQueryCommand.getIsChannelIdNull());
        cmsContentQueryListCommand.setCmsContentCategoryId(cmsContentDirectivePageQueryCommand.getCmsContentCategoryId());
        cmsContentQueryListCommand.setIsAlsoAsChannel(cmsContentDirectivePageQueryCommand.getIsAlsoAsChannel());
        cmsContentQueryListCommand.setIsPublic(cmsContentDirectivePageQueryCommand.getIsPublic());

        cmsContentQueryListCommand.setPageNo(cmsContentDirectivePageQueryCommand.getPageNo());
        cmsContentQueryListCommand.setPageSize(cmsContentDirectivePageQueryCommand.getPageSize());

        cmsContentQueryListCommand.setOrderBy(cmsContentDirectivePageQueryCommand.getOrderBy());
        PageResponse<CmsContentVO> cmsContentVOPageResponse = cmsContentQueryCommandExecutor.execute(cmsContentQueryListCommand);
        return cmsContentVOPageResponse;
    }

    @Override
    public MultiResponse<CmsContentCategoryVO> queryListContentCategory(CmsContentCategoryDirectivePageQueryCommand cmsContentCategoryDirectivePageQueryCommand) {
        CmsContentCategoryQueryListCommand cmsContentCategoryQueryListCommand = new CmsContentCategoryQueryListCommand();
        cmsContentCategoryQueryListCommand.setId(cmsContentCategoryDirectivePageQueryCommand.getId());
        cmsContentCategoryQueryListCommand.setCmsSiteId(cmsContentCategoryDirectivePageQueryCommand.getCmsSiteId());
        cmsContentCategoryQueryListCommand.setCmsChannelId(cmsContentCategoryDirectivePageQueryCommand.getCmsChannelId());
        cmsContentCategoryQueryListCommand.setIsChannelIdNull(cmsContentCategoryDirectivePageQueryCommand.getIsChannelIdNull());

        cmsContentCategoryQueryListCommand.setParentId(cmsContentCategoryDirectivePageQueryCommand.getParentId());
        MultiResponse<CmsContentCategoryVO> cmsContentCategoryVOMultiResponse = cmsContentCategoryQueryCommandExecutor.execute(cmsContentCategoryQueryListCommand);
        return cmsContentCategoryVOMultiResponse;
    }

    @Override
    public PageResponse<CmsContentCategoryVO> pageQueryContentCategory(CmsContentCategoryDirectivePageQueryCommand cmsContentCategoryDirectivePageQueryCommand) {
        CmsContentCategoryPageQueryCommand cmsContentCategoryQueryListCommand = new CmsContentCategoryPageQueryCommand();
        cmsContentCategoryQueryListCommand.setId(cmsContentCategoryDirectivePageQueryCommand.getId());
        cmsContentCategoryQueryListCommand.setCmsSiteId(cmsContentCategoryDirectivePageQueryCommand.getCmsSiteId());
        cmsContentCategoryQueryListCommand.setCmsChannelId(cmsContentCategoryDirectivePageQueryCommand.getCmsChannelId());
        cmsContentCategoryQueryListCommand.setIsChannelIdNull(cmsContentCategoryDirectivePageQueryCommand.getIsChannelIdNull());


        cmsContentCategoryQueryListCommand.setParentId(cmsContentCategoryDirectivePageQueryCommand.getParentId());

        cmsContentCategoryQueryListCommand.setPageNo(cmsContentCategoryDirectivePageQueryCommand.getPageNo());
        cmsContentCategoryQueryListCommand.setPageSize(cmsContentCategoryDirectivePageQueryCommand.getPageSize());

        cmsContentCategoryQueryListCommand.setOrderBy(cmsContentCategoryDirectivePageQueryCommand.getOrderBy());
        PageResponse<CmsContentCategoryVO> cmsContentCategoryVOPageResponse = cmsContentCategoryQueryCommandExecutor.execute(cmsContentCategoryQueryListCommand);
        return cmsContentCategoryVOPageResponse;
    }

    @Override
    public MultiResponse<CmsContentMultimediaVO> listContentMultimediaByContentId(Long contentId) {
        CmsContentMultimediaQueryListCommand cmsContentMultimediaQueryListCommand = new CmsContentMultimediaQueryListCommand();
        cmsContentMultimediaQueryListCommand.setCmsContentId(contentId);
        MultiResponse<CmsContentMultimediaVO> cmsContentMultimediaVOMultiResponse = cmsContentMultimediaQueryCommandExecutor.execute(cmsContentMultimediaQueryListCommand);
        return cmsContentMultimediaVOMultiResponse;
    }

    @Override
    public MultiResponse<CmsContentMultimediaVO> listContentMultimediaByContentIds(List<Long> contentIds) {
        return cmsContentMultimediaQueryCommandExecutor.execute(CommonBatchIdCommand.create(contentIds));
    }

    @Autowired
    public void setCmsSiteQueryCommandExecutor(CmsSiteQueryCommandExecutor cmsSiteQueryCommandExecutor) {
        this.cmsSiteQueryCommandExecutor = cmsSiteQueryCommandExecutor;
    }

    @Autowired
    public void setCmsChannelQueryCommandExecutor(CmsChannelQueryCommandExecutor cmsChannelQueryCommandExecutor) {
        this.cmsChannelQueryCommandExecutor = cmsChannelQueryCommandExecutor;
    }
    @Autowired
    public void setCmsContentQueryCommandExecutor(CmsContentQueryCommandExecutor cmsContentQueryCommandExecutor) {
        this.cmsContentQueryCommandExecutor = cmsContentQueryCommandExecutor;
    }
    @Autowired
    public void setCmsContentCategoryQueryCommandExecutor(CmsContentCategoryQueryCommandExecutor cmsContentCategoryQueryCommandExecutor) {
        this.cmsContentCategoryQueryCommandExecutor = cmsContentCategoryQueryCommandExecutor;
    }
    @Autowired
    public void setCmsContentMultimediaQueryCommandExecutor(CmsContentMultimediaQueryCommandExecutor cmsContentMultimediaQueryCommandExecutor) {
        this.cmsContentMultimediaQueryCommandExecutor = cmsContentMultimediaQueryCommandExecutor;
    }
    @Autowired
    public void setiCmsSiteService(ICmsSiteService iCmsSiteService) {
        this.iCmsSiteService = iCmsSiteService;
    }
    @Autowired
    public void setiCmsChannelService(ICmsChannelService iCmsChannelService) {
        this.iCmsChannelService = iCmsChannelService;
    }
    @Autowired
    public void setiCmsContentService(ICmsContentService iCmsContentService) {
        this.iCmsContentService = iCmsContentService;
    }
}
