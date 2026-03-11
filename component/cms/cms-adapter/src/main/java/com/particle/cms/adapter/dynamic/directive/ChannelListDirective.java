package com.particle.cms.adapter.dynamic.directive;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Pair;
import com.particle.cms.client.dto.command.directive.CmsChannelDirectivePageQueryCommand;
import com.particle.cms.client.dto.command.directive.CmsContentDirectivePageQueryCommand;
import com.particle.cms.client.dto.command.directive.CmsDirectivePageQueryCommand;
import com.particle.cms.client.dto.data.CmsChannelVO;
import com.particle.cms.client.dto.data.CmsContentVO;
import com.particle.cms.client.dto.data.CmsSiteVO;
import com.particle.cms.client.dto.data.dynamic.CmsChannelTemplateModelVO;
import com.particle.cms.client.dto.data.dynamic.CmsContentTemplateModelVO;
import com.particle.cms.client.dto.data.dynamic.CmsSiteTemplateModelVO;
import com.particle.cms.client.dto.data.dynamic.CmsTemplateModelContext;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 栏目列表 指令
 * Created by yangwei
 * Created at 2018/11/19 17:39
 */
@Component
public class ChannelListDirective extends AbstractDirective {

    protected static final String param_channel_is_include_content_channel = "isIncludeContentChannel";

    @Autowired
    private DirectiveHelper directiveHelper;

    /**
     *
     * @param env
     * @param params
     * @param loopVars
     * @param body
     * @throws TemplateException
     * @throws IOException
     */
    @Override
    public void doExecute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        Long siteId = getSiteId(params);
        Long channelId = getChannelId(params);

        Boolean isIncludeContentChannel = getParamBoolean(param_channel_is_include_content_channel,params);
        isIncludeContentChannel = isIncludeContentChannel == null ? true : isIncludeContentChannel;

        Integer level = getLevel(params);
        Long parentId = getParentId(params);
        Long parentId1 = getParentId1(params);
        Long parentId2 = getParentId2(params);
        Long parentId3 = getParentId3(params);
        Long parentId4 = getParentId4(params);
        Long parentId5 = getParentId5(params);
        Long parentId6 = getParentId6(params);
        Long parentId7 = getParentId7(params);
        Long parentId8 = getParentId8(params);
        Long parentId9 = getParentId9(params);
        Long parentId10 = getParentId10(params);
        if (body != null) {
            CmsTemplateModelContext modelContext = getModelContext();
            Boolean isPublicCondition = getIsPublicCondition();

            // 根据参数查询
            CmsDirectivePageQueryCommand pageQueryCommand = getPageQueryCommand(params);
            Pair<List<CmsChannelVO>, PageResponse> listPageResponsePair = queryChannelList(pageQueryCommand, siteId, channelId, level, parentId);
            List<CmsChannelVO> cmsChannelVOs = listPageResponsePair.getKey();
            PageResponse pageResponse = listPageResponsePair.getValue();

            // 栏目映射
            List<CmsChannelTemplateModelVO> dataList = new ArrayList<>();
            List<CmsChannelTemplateModelVO> channelDataList = mappingCmsChannelTemplateModelVOs(cmsChannelVOs, modelContext,isPublicCondition);
            dataList.addAll(channelDataList);

            // 内容栏目的支持
            List<CmsChannelTemplateModelVO> channelContentDataList = channelContentCmsChannelTemplateModelVO(isIncludeContentChannel,
                    pageQueryCommand.getIsPage(),siteId,parentId,modelContext,isPublicCondition);
            dataList.addAll(channelContentDataList);
            // 从小到大排序
            if (CollectionUtil.isNotEmpty(channelContentDataList)) {
                dataList.sort(Comparator.comparingInt(CmsChannelTemplateModelVO::getSeq));
            }

            bodyRender(env, params, loopVars, body, dataList,pageResponse,
                    Function.identity()
            );
        }
    }

    /**
     * 查询栏目列表
     * @param pageQueryCommand
     * @param siteId
     * @param channelId
     * @param level
     * @param parentId
     * @return
     */
    private Pair<List<CmsChannelVO>,PageResponse> queryChannelList(CmsDirectivePageQueryCommand pageQueryCommand,
                                                 Long siteId,
                                                 Long channelId,
                                                 Integer level,
                                                 Long parentId) {
        CmsChannelDirectivePageQueryCommand cmsChannelDirectivePageQueryCommand = CmsChannelDirectivePageQueryCommand.create(pageQueryCommand,
                channelId,siteId,level,getIsPublicCondition(),parentId);
        List<CmsChannelVO> cmsChannelVOs = null;
        PageResponse pageResponse = null;
        if (pageQueryCommand.getIsPage()) {
            PageResponse<CmsChannelVO> cmsChannelVOPageResponse = iCmsDynamicApplicationService.pageQueryChannel(cmsChannelDirectivePageQueryCommand);
            pageResponse = cmsChannelVOPageResponse;
            cmsChannelVOs = cmsChannelVOPageResponse.getData();
        }else{
            MultiResponse<CmsChannelVO> cmsChannelVOMultiResponse = iCmsDynamicApplicationService.queryListChannel(cmsChannelDirectivePageQueryCommand);
            cmsChannelVOs = cmsChannelVOMultiResponse.getData();
        }
        return Pair.of(cmsChannelVOs,pageResponse);
    }
    /**
     * 将栏目列表转换为模板模型
     * @param cmsChannelVOs
     * @param modelContext
     * @return
     */
    private List<CmsChannelTemplateModelVO> mappingCmsChannelTemplateModelVOs(List<CmsChannelVO> cmsChannelVOs,
                                                                              CmsTemplateModelContext modelContext,Boolean isPublic) {
        if (CollectionUtil.isEmpty(cmsChannelVOs)) {
            return Collections.emptyList();
        }
        // 查询出栏目后，需要将栏目所属的站点也查询出来，因为栏目列表指令需要根据站点来渲染

        Map<Long, CmsSiteTemplateModelVO> siteIdToCmsSiteTemplateModelMap = Collections.emptyMap();
        Map<Long, CmsContentTemplateModelVO> contentIdCmsContentTemplateModelVOMap = Collections.emptyMap();
        List<Long> siteIds = cmsChannelVOs.stream().map(CmsChannelVO::getCmsSiteId).distinct().collect(Collectors.toList());
        List<CmsSiteTemplateModelVO> cmsSiteTemplateModelVOs = directiveHelper.listCmsSiteTemplateModelVOsBySiteIds(siteIds, modelContext,isPublic);
        siteIdToCmsSiteTemplateModelMap = cmsSiteTemplateModelVOs.stream().collect(Collectors.toMap(CmsSiteTemplateModelVO::getId, a -> a));


        // 栏目引用内容的支持
        List<Long> relatedCmsContentIds = cmsChannelVOs.stream().map(CmsChannelVO::getRelatedCmsContentId).filter(Objects::nonNull).distinct().collect(Collectors.toList());
        if (CollectionUtil.isNotEmpty(relatedCmsContentIds)) {
            contentIdCmsContentTemplateModelVOMap =
                    directiveHelper.listCmsContentTemplateModelVOsByContentIds(relatedCmsContentIds,modelContext,isPublic)
                            .stream().collect(Collectors.toMap(CmsContentTemplateModelVO::getId, a -> a));
        }

        Map<Long, CmsSiteTemplateModelVO> finalSiteIdToCmsSiteTemplateModelMap = siteIdToCmsSiteTemplateModelMap;
        Map<Long, CmsContentTemplateModelVO> finalContentIdCmsContentTemplateModelVOMap = contentIdCmsContentTemplateModelVOMap;

        // 栏目列表需要根据站点来渲染
        List<CmsChannelTemplateModelVO> channelDataList = cmsChannelVOs.stream().map(cmsChannelVO ->{
            CmsChannelVO channelVO = (CmsChannelVO) cmsChannelVO;
            return CmsChannelTemplateModelVO.createByCmsChannelVO(
                    channelVO,
                    finalSiteIdToCmsSiteTemplateModelMap.get((channelVO).getCmsSiteId()),
                    (channelVO).getRelatedCmsContentId() == null ? null : finalContentIdCmsContentTemplateModelVOMap.get((channelVO).getRelatedCmsContentId()),
                    modelContext);
        }).collect(Collectors.toList());
        return channelDataList;
    }

    /**
     * 查询内容栏目的模板模型
     * @param isIncludeContentChannel
     * @param isPage
     * @param siteId
     * @param channelId
     * @param modelContext
     * @return
     */
    private List<CmsChannelTemplateModelVO> channelContentCmsChannelTemplateModelVO(Boolean isIncludeContentChannel,
                                                                                    Boolean isPage,
                                                                                    Long siteId,
                                                                                    Long channelId,
                                                                                    CmsTemplateModelContext modelContext,Boolean isPublic) {
        // 内容栏目的支持
        if (isIncludeContentChannel != null && isIncludeContentChannel && !isPage) {
            CmsContentDirectivePageQueryCommand cmsContentDirectivePageQueryCommand = CmsContentDirectivePageQueryCommand.create(CmsDirectivePageQueryCommand.createEmpty(),
                    null,siteId,channelId,channelId == null,null,true,isPublic);
            MultiResponse<CmsContentVO> cmsContentVOMultiResponse = iCmsDynamicApplicationService.queryListContent(cmsContentDirectivePageQueryCommand);

            List<CmsContentVO> cmsContentVOs = cmsContentVOMultiResponse.getData();
            if (CollectionUtil.isNotEmpty(cmsContentVOs)) {
                // 将查询到的数据 找到对应的 site 和 channel
                DirectiveHelper.CmsContentVosDependMaps cmsContentVosDependMaps = directiveHelper.getCmsContentVosDependMaps(cmsContentVOs, false,modelContext,isPublic);
                List<CmsContentTemplateModelVO> channelCmsContentTemplateModelVOS = cmsContentVOs.stream().map(cmsContentVO ->
                {
                    CmsContentVO contentVO = (CmsContentVO) cmsContentVO;
                    return directiveHelper.cmsContentVOToCmsContentTemplateModelVOMapping(contentVO,
                            cmsContentVosDependMaps.getCmsSiteVOMap().get(contentVO.getCmsSiteId()),
                            contentVO.getCmsChannelId() == null ? null : cmsContentVosDependMaps.getCmsChannelVOMap().get(contentVO.getCmsChannelId()),
                            cmsContentVosDependMaps.getCmsContentMultimediaVOMap(), modelContext);
                }).collect(Collectors.toList());


                List<CmsChannelTemplateModelVO> channelContentDataList = channelCmsContentTemplateModelVOS.stream().map(channelCmsContentTemplateModelVO -> {
                    return CmsChannelTemplateModelVO.createByChannelCmsContentTemplateModelVO(
                            cmsContentVosDependMaps.getCmsSiteVOMap().get(channelCmsContentTemplateModelVO.getCmsSiteId()),
                            channelCmsContentTemplateModelVO,modelContext);
                }).collect(Collectors.toList());
                return channelContentDataList;
            }else {
                return  Collections.emptyList();
            }
        }else {
            return  Collections.emptyList();
        }
    }
}
