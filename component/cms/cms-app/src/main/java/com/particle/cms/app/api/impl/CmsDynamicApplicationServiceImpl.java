package com.particle.cms.app.api.impl;

import com.particle.cms.app.executor.representation.*;
import com.particle.cms.client.api.ICmsDynamicApplicationService;
import com.particle.cms.client.dto.command.directive.CmsChannelDirectivePageQueryCommand;
import com.particle.cms.client.dto.command.directive.CmsContentCategoryDirectivePageQueryCommand;
import com.particle.cms.client.dto.command.directive.CmsContentDirectivePageQueryCommand;
import com.particle.cms.client.dto.command.directive.CmsSiteDirectivePageQueryCommand;
import com.particle.cms.client.dto.command.representation.*;
import com.particle.cms.client.dto.data.*;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    @Override
    public SingleResponse<CmsSiteVO> getSiteByPath(String siteContextPath) {
        CmsSiteQueryListCommand cmsSiteQueryListCommand = new CmsSiteQueryListCommand();
        cmsSiteQueryListCommand.setPath(siteContextPath);
        MultiResponse<CmsSiteVO> cmsSiteVOMultiResponse = cmsSiteQueryCommandExecutor.execute(cmsSiteQueryListCommand);
        if (cmsSiteVOMultiResponse.isNotEmpty()) {
            return SingleResponse.of(cmsSiteVOMultiResponse.getData().get(0));
        }
        return SingleResponse.buildSuccess();
    }

    @Override
    public SingleResponse<CmsSiteVO> getPrimeSiteByDomain(String domain) {
        CmsSiteQueryListCommand cmsSiteQueryListCommand = new CmsSiteQueryListCommand();
        cmsSiteQueryListCommand.setDomain(domain);
        cmsSiteQueryListCommand.setIsPrimeSite(true);
        MultiResponse<CmsSiteVO> cmsSiteVOMultiResponse = cmsSiteQueryCommandExecutor.execute(cmsSiteQueryListCommand);
        if (cmsSiteVOMultiResponse.isNotEmpty()) {
            return SingleResponse.of(cmsSiteVOMultiResponse.getData().get(0));
        }
        return SingleResponse.buildSuccess();
    }

    @Override
    public MultiResponse<CmsSiteVO> listSiteByDomain(String domain) {
        CmsSiteQueryListCommand cmsSiteQueryListCommand = new CmsSiteQueryListCommand();
        cmsSiteQueryListCommand.setDomain(domain);
        MultiResponse<CmsSiteVO> cmsSiteVOMultiResponse = cmsSiteQueryCommandExecutor.execute(cmsSiteQueryListCommand);
        return cmsSiteVOMultiResponse;
    }

    @Override
    public SingleResponse<CmsChannelVO> getChannelById(Long channelId) {
        return cmsChannelQueryCommandExecutor.executeDetail(IdCommand.create(channelId));
    }

    @Override
    public SingleResponse<CmsChannelVO> getChannelByPathAndSiteId(String channelPath, Long siteId) {
        CmsChannelQueryListCommand cmsChannelQueryListCommand = new CmsChannelQueryListCommand();
        cmsChannelQueryListCommand.setPath(channelPath);
        cmsChannelQueryListCommand.setCmsSiteId(siteId);
        MultiResponse<CmsChannelVO> cmsChannelVOMultiResponse = cmsChannelQueryCommandExecutor.execute(cmsChannelQueryListCommand);
        if (cmsChannelVOMultiResponse.isNotEmpty()) {
            return SingleResponse.of(cmsChannelVOMultiResponse.getData().get(0));
        }
        return SingleResponse.buildSuccess();
    }

    @Override
    public SingleResponse<CmsContentVO> getContentByContentId(Long contentId) {
        return cmsContentQueryCommandExecutor.executeDetail(IdCommand.create(contentId));
    }

    @Override
    public MultiResponse<CmsSiteVO> queryListSite(CmsSiteDirectivePageQueryCommand cmsSiteDirectivePageQueryCommand) {
        CmsSiteQueryListCommand cmsSiteQueryListCommand = new CmsSiteQueryListCommand();
        cmsSiteQueryListCommand.setId(cmsSiteDirectivePageQueryCommand.getId());
        cmsSiteQueryListCommand.setIsPrimeSite(cmsSiteDirectivePageQueryCommand.getIsPrimeSite());
        MultiResponse<CmsSiteVO> cmsSiteVOMultiResponse = cmsSiteQueryCommandExecutor.execute(cmsSiteQueryListCommand);
        return cmsSiteVOMultiResponse;
    }

    @Override
    public PageResponse<CmsSiteVO> pageQuerySite(CmsSiteDirectivePageQueryCommand cmsSiteDirectivePageQueryCommand) {
        CmsSitePageQueryCommand cmsSiteQueryListCommand = new CmsSitePageQueryCommand();
        cmsSiteQueryListCommand.setId(cmsSiteDirectivePageQueryCommand.getId());
        cmsSiteQueryListCommand.setIsPrimeSite(cmsSiteDirectivePageQueryCommand.getIsPrimeSite());

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

        cmsChannelQueryListCommand.setParentId(cmsChannelDirectivePageQueryCommand.getParentId());
        MultiResponse<CmsChannelVO> cmsChannelVOMultiResponse = cmsChannelQueryCommandExecutor.execute(cmsChannelQueryListCommand);
        return cmsChannelVOMultiResponse;
    }

    @Override
    public PageResponse<CmsChannelVO> pageQueryChannel(CmsChannelDirectivePageQueryCommand cmsChannelDirectivePageQueryCommand) {
        CmsChannelPageQueryCommand cmsChannelQueryListCommand = new CmsChannelPageQueryCommand();
        cmsChannelQueryListCommand.setId(cmsChannelDirectivePageQueryCommand.getId());
        cmsChannelQueryListCommand.setCmsSiteId(cmsChannelDirectivePageQueryCommand.getCmsSiteId());

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
        cmsContentQueryListCommand.setCmsContentCategoryId(cmsContentDirectivePageQueryCommand.getCmsContentCategoryId());

        MultiResponse<CmsContentVO> cmsContentVOMultiResponse = cmsContentQueryCommandExecutor.execute(cmsContentQueryListCommand);
        return cmsContentVOMultiResponse;
    }

    @Override
    public PageResponse<CmsContentVO> pageQueryContent(CmsContentDirectivePageQueryCommand cmsContentDirectivePageQueryCommand) {
        CmsContentPageQueryCommand cmsContentQueryListCommand = new CmsContentPageQueryCommand();
        cmsContentQueryListCommand.setId(cmsContentDirectivePageQueryCommand.getId());
        cmsContentQueryListCommand.setCmsSiteId(cmsContentDirectivePageQueryCommand.getCmsSiteId());
        cmsContentQueryListCommand.setCmsChannelId(cmsContentDirectivePageQueryCommand.getCmsChannelId());
        cmsContentQueryListCommand.setCmsContentCategoryId(cmsContentDirectivePageQueryCommand.getCmsContentCategoryId());

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
}
