package com.particle.cms.adapter.dynamic.directive;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.cms.client.api.ICmsDynamicApplicationService;
import com.particle.cms.client.dto.data.CmsChannelVO;
import com.particle.cms.client.dto.data.CmsContentMultimediaVO;
import com.particle.cms.client.dto.data.CmsContentVO;
import com.particle.cms.client.dto.data.CmsSiteVO;
import com.particle.cms.client.dto.data.dynamic.*;
import com.particle.global.dto.response.MultiResponse;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2026/1/15 21:47
 */
@Component
public class DirectiveHelper {

    @Autowired
    protected ICmsDynamicApplicationService iCmsDynamicApplicationService;


    /**
     * 根据内容 id 列表查询内容模板模型 vo 列表
     * @param contentIds
     * @param modelContext
     * @return
     */
    public List<CmsContentTemplateModelVO> listCmsContentTemplateModelVOsByContentIds(List<Long> contentIds, CmsTemplateModelContext modelContext,Boolean isPublic) {
        if (CollectionUtil.isEmpty(contentIds)) {
            return Collections.EMPTY_LIST;
        }
        MultiResponse<CmsContentVO> cmsContentVOMultiResponse = iCmsDynamicApplicationService.listContentByContentIds(contentIds,isPublic);
        List<CmsContentVO> cmsContentVOs = cmsContentVOMultiResponse.getData();
        if (CollectionUtil.isNotEmpty(cmsContentVOs)) {
            // 将查询到的数据 找到对应的 site 和 channel
            DirectiveHelper.CmsContentVosDependMaps cmsContentVosDependMaps = this.getCmsContentVosDependMaps(cmsContentVOs, false,modelContext,isPublic);
            return cmsContentVOs.stream().map(cmsContentVO ->
            {
                CmsContentVO contentVO = (CmsContentVO) cmsContentVO;
                return this.cmsContentVOToCmsContentTemplateModelVOMapping(contentVO,
                        cmsContentVosDependMaps.getCmsSiteVOMap().get(contentVO.getCmsSiteId()),
                        contentVO.getCmsChannelId() == null ? null : cmsContentVosDependMaps.getCmsChannelVOMap().get(contentVO.getCmsChannelId()),
                        cmsContentVosDependMaps.getCmsContentMultimediaVOMap(), modelContext);
            }).collect(Collectors.toList());
        }else {
            return Collections.EMPTY_LIST;
        }
    }
     /**
      * 根据站点 id 列表查询站点模板模型 vo 列表
      * @param siteIds
      * @param modelContext
      * @return
      */
    public List<CmsSiteTemplateModelVO> listCmsSiteTemplateModelVOsBySiteIds(List<Long> siteIds, CmsTemplateModelContext modelContext,Boolean isPublic) {
        if (CollectionUtil.isEmpty(siteIds)) {
            return Collections.EMPTY_LIST;
        }
        MultiResponse<CmsSiteVO> cmsSiteVOMultiResponse = iCmsDynamicApplicationService.listSiteByIds(siteIds,isPublic);
        return cmsSiteVOMultiResponse.getData().stream()
                .map(cmsSiteVO -> CmsSiteTemplateModelVO.createByCmsSiteVO(cmsSiteVO, modelContext))
                .collect(Collectors.toList());
    }

    /**
     * 创建内容 vo 相关的依赖 map
     * @param cmsContentVOs
     * @param isIncludeMultimedia 是否包含内容多媒体，默认不包含
     * @return
     */
    public CmsContentVosDependMaps getCmsContentVosDependMaps(List<CmsContentVO> cmsContentVOs, Boolean isIncludeMultimedia, CmsTemplateModelContext modelContext,Boolean isPublic) {
        Map<Long, CmsSiteTemplateModelVO> cmsSiteVOMap = Collections.EMPTY_MAP;
        Map<Long, CmsChannelTemplateModelVO> cmsChannelVOMap = Collections.EMPTY_MAP;
        Map<Long, List<CmsContentMultimediaTemplateModelVO>> cmsContentMultimediaVOMap = Collections.EMPTY_MAP;
        if (CollectionUtil.isNotEmpty(cmsContentVOs)) {
            List<Long> siteIds = cmsContentVOs.stream().map(CmsContentVO::getCmsSiteId).distinct().collect(Collectors.toList());
            List<Long> channelIds = cmsContentVOs.stream().map(CmsContentVO::getCmsChannelId).distinct().collect(Collectors.toList());
            List<Long> contentIds = cmsContentVOs.stream().map(CmsContentVO::getId).distinct().collect(Collectors.toList());

            MultiResponse<CmsSiteVO> cmsSiteVOMultiResponse = iCmsDynamicApplicationService.listSiteByIds(siteIds,isPublic);
            cmsSiteVOMap = cmsSiteVOMultiResponse.getData().stream()
                    .collect(Collectors.toMap(CmsSiteVO::getId, cmsSiteVO -> CmsSiteTemplateModelVO.createByCmsSiteVO(cmsSiteVO, modelContext)));

            // 允许 channel 为空
            if (CollectionUtil.isNotEmpty(channelIds)) {
                MultiResponse<CmsChannelVO> cmsChannelVOMultiResponse = iCmsDynamicApplicationService.listChannelByIds(channelIds,isPublic);
                Map<Long, CmsSiteTemplateModelVO> finalCmsSiteVOMap = cmsSiteVOMap;
                cmsChannelVOMap = cmsChannelVOMultiResponse.getData().stream()
                        .collect(Collectors.toMap(CmsChannelVO::getId, cmsChannelVO ->
                                CmsChannelTemplateModelVO.createByCmsChannelVO(cmsChannelVO, finalCmsSiteVOMap.get(cmsChannelVO.getCmsSiteId()), null, modelContext)));
            }

            if(isIncludeMultimedia != null && isIncludeMultimedia){
                // 内容多媒体
                MultiResponse<CmsContentMultimediaVO> cmsContentMultimediaVOMultiResponse = iCmsDynamicApplicationService.listContentMultimediaByContentIds(contentIds);
                cmsContentMultimediaVOMap = cmsContentMultimediaVOMultiResponse.getData().stream()
                        .collect(Collectors.groupingBy(CmsContentMultimediaVO::getId,
                                Collectors.mapping(
                                        cmsContentMultimediaVO -> CmsContentMultimediaTemplateModelVO.createByCmsContentMultimediaVO(cmsContentMultimediaVO),
                                        Collectors.toList()
                                )));
            }

        }
        return DirectiveHelper.CmsContentVosDependMaps.create(cmsSiteVOMap, cmsChannelVOMap, cmsContentMultimediaVOMap);
    }


    /**
     * 映射结果，添加内容多媒体
     * @param cmsContentVO
     * @return
     */
    public CmsContentTemplateModelVO cmsContentVOToCmsContentTemplateModelVOMapping(CmsContentVO cmsContentVO,
                                                                                     CmsSiteTemplateModelVO cmsSiteTemplateModelVO,
                                                                                     CmsChannelTemplateModelVO cmsChannelTemplateModelVO,
                                                                                     Map<Long, List<CmsContentMultimediaTemplateModelVO>> cmsContentMultimediaVOMap,
                                                                                     CmsTemplateModelContext context) {
        CmsContentTemplateModelVO cmsContentTemplateModelVO = CmsContentTemplateModelVO.createByCmsContentVO(cmsContentVO,cmsSiteTemplateModelVO,cmsChannelTemplateModelVO, context);
        if (cmsContentVO != null) {
            Long contentVOId = cmsContentVO.getId();
            cmsContentTemplateModelVO.setContentMultimedias(cmsContentMultimediaVOMap.get(contentVOId));
        }
        return cmsContentTemplateModelVO;
    }

    /**
     * 内容 vo 相关的依赖 map
     */
    @Getter
    public static class CmsContentVosDependMaps {
        Map<Long, CmsSiteTemplateModelVO> cmsSiteVOMap = Collections.EMPTY_MAP;
        Map<Long, CmsChannelTemplateModelVO> cmsChannelVOMap = Collections.EMPTY_MAP;
        Map<Long, List<CmsContentMultimediaTemplateModelVO>> cmsContentMultimediaVOMap = Collections.EMPTY_MAP;

        public static CmsContentVosDependMaps create(
                Map<Long, CmsSiteTemplateModelVO> cmsSiteVOMap,
                Map<Long, CmsChannelTemplateModelVO> cmsChannelVOMap,
                Map<Long, List<CmsContentMultimediaTemplateModelVO>> cmsContentMultimediaVOMap
        ) {
            CmsContentVosDependMaps cmsContentVosDependMaps = new CmsContentVosDependMaps();
            cmsContentVosDependMaps.cmsSiteVOMap = cmsSiteVOMap;
            cmsContentVosDependMaps.cmsChannelVOMap = cmsChannelVOMap;
            cmsContentVosDependMaps.cmsContentMultimediaVOMap = cmsContentMultimediaVOMap;
            return cmsContentVosDependMaps;
        }
    }
}
