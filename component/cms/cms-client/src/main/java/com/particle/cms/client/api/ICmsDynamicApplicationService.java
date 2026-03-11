package com.particle.cms.client.api;

import com.particle.cms.client.dto.command.directive.CmsChannelDirectivePageQueryCommand;
import com.particle.cms.client.dto.command.directive.CmsContentCategoryDirectivePageQueryCommand;
import com.particle.cms.client.dto.command.directive.CmsContentDirectivePageQueryCommand;
import com.particle.cms.client.dto.command.directive.CmsSiteDirectivePageQueryCommand;
import com.particle.cms.client.dto.data.*;
import com.particle.common.client.api.IBaseApplicationService;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import java.util.List;
/**
 * <p>
 * 动态 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:55
 */
public interface ICmsDynamicApplicationService extends IBaseApplicationService {

	/**
	 * 根据路径获取站点信息
	 * @param siteContextPath
	 * @return
	 */
	public SingleResponse<CmsSiteVO> getSiteByDomainAndSiteContextPath(String domain,String siteContextPath,Boolean isPublic);
    /**
	 * 根据id获取站点信息
	 * @param siteId
	 * @return
	 */
	public SingleResponse<CmsSiteVO> getSiteById(Long siteId,Boolean isPublic);
	/**
	 * 根据域名获取主站点信息
	 * @param domain http://localhost:8080
	 * @return
	 */
	public SingleResponse<CmsSiteVO> getPrimeSiteByDomain(String domain,Boolean isPublic);
	/**
	 * 根据域名获取站点信息
	 * @param domain http://localhost:8080
	 * @return
	 */
	public MultiResponse<CmsSiteVO> listSiteByDomain(String domain,Boolean isPublic);

    /**
     * 根据id列表获取站点信息
     * @param siteIds
     * @return
     */
	public MultiResponse<CmsSiteVO> listSiteByIds(List<Long> siteIds,Boolean isPublic);

	/**
	 * 根据id获取栏目信息
	 * @param channelId
	 * @return
	 */
	public SingleResponse<CmsChannelVO> getChannelById(Long channelId,Boolean isPublic);


	/**
	 * 根据id获取栏目信息
	 * @param channelIds
	 * @return
	 */
	MultiResponse<CmsChannelVO> listChannelByIds(List<Long> channelIds,Boolean isPublic);

	/**
	 * 根据路径和站点id 获取栏目信息
	 * @param channelPath
	 * @param siteId
	 * @return
	 */
	public SingleResponse<CmsChannelVO> getChannelByPathAndSiteId(String channelPath, Long siteId,Boolean isPublic);

	/**
	 * 根据id获取内容信息
	 * @param contentId
	 * @return
	 */
	public SingleResponse<CmsContentVO> getContentByContentId(Long contentId,Boolean isPublic);
    /**
     * 根据id列表获取内容信息
     * @param contentIds
     * @return
     */
    public MultiResponse<CmsContentVO> listContentByContentIds(List<Long> contentIds,Boolean isPublic);



	/**
	 * 列表查询
	 * @param cmsSiteDirectivePageQueryCommand
	 * @return
	 */
	MultiResponse<CmsSiteVO> queryListSite(CmsSiteDirectivePageQueryCommand cmsSiteDirectivePageQueryCommand);

	/**
	 * 分页查询
	 * @param cmsSiteDirectivePageQueryCommand
	 * @return
	 */
	PageResponse<CmsSiteVO> pageQuerySite(CmsSiteDirectivePageQueryCommand cmsSiteDirectivePageQueryCommand);


	/**
	 * 列表查询
	 * @param cmsChannelDirectivePageQueryCommand
	 * @return
	 */
	MultiResponse<CmsChannelVO> queryListChannel(CmsChannelDirectivePageQueryCommand cmsChannelDirectivePageQueryCommand);

	/**
	 * 分页查询
	 * @param cmsChannelDirectivePageQueryCommand
	 * @return
	 */
	PageResponse<CmsChannelVO> pageQueryChannel(CmsChannelDirectivePageQueryCommand cmsChannelDirectivePageQueryCommand);


	/**
	 * 列表查询
	 * @param cmsContentDirectivePageQueryCommand
	 * @return
	 */
	MultiResponse<CmsContentVO> queryListContent(CmsContentDirectivePageQueryCommand cmsContentDirectivePageQueryCommand);

	/**
	 * 分页查询
	 * @param cmsContentDirectivePageQueryCommand
	 * @return
	 */
	PageResponse<CmsContentVO> pageQueryContent(CmsContentDirectivePageQueryCommand cmsContentDirectivePageQueryCommand);

	/**
	 * 列表查询
	 * @param cmsContentCategoryDirectivePageQueryCommand
	 * @return
	 */
	MultiResponse<CmsContentCategoryVO> queryListContentCategory(CmsContentCategoryDirectivePageQueryCommand cmsContentCategoryDirectivePageQueryCommand);

	/**
	 * 分页查询
	 * @param cmsContentCategoryDirectivePageQueryCommand
	 * @return
	 */
	PageResponse<CmsContentCategoryVO> pageQueryContentCategory(CmsContentCategoryDirectivePageQueryCommand cmsContentCategoryDirectivePageQueryCommand);


	/**
	 * 根据内容id获取多媒体信息
	 * @param contentId
	 * @return
	 */
	public MultiResponse<CmsContentMultimediaVO> listContentMultimediaByContentId(Long contentId);

	/**
	 * 根据内容id列表获取多媒体信息
	 * @param contentIds
	 * @return
	 */
	public MultiResponse<CmsContentMultimediaVO> listContentMultimediaByContentIds(List<Long> contentIds);

}
