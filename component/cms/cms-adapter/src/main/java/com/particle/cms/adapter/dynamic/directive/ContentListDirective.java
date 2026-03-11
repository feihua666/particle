package com.particle.cms.adapter.dynamic.directive;

import com.particle.cms.client.dto.command.directive.CmsContentDirectivePageQueryCommand;
import com.particle.cms.client.dto.command.directive.CmsDirectivePageQueryCommand;
import com.particle.cms.client.dto.data.CmsContentVO;
import com.particle.cms.client.dto.data.dynamic.*;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by yangwei
 * Created at 2018/11/19 17:39
 */
@Component
public class ContentListDirective extends AbstractDirective {


    /**
     * 内容指令支持属性
     */
    protected static final String param_content_categoryId = "categoryId";
    protected static final String param_content_is_include_multimedia = "isIncludeMultimedia";
    protected static final String param_content_is_channel_id_null = "isChannelIdNull";

    @Autowired
    protected DirectiveHelper directiveHelper;

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
        Long contentId = getContentId(params);
        Long categoryId = getParamLong(param_content_categoryId, params);
        Boolean isIncludeMultimedia = getParamBoolean(param_content_is_include_multimedia, params);
        Boolean isChannelIdNull = getParamBoolean(param_content_is_channel_id_null, params);
        if (isChannelIdNull != null && isChannelIdNull) {
            channelId = null;
        }

        if (body != null) {

            CmsTemplateModelContext modelContext = getModelContext();
            Boolean isPublicCondition = getIsPublicCondition();
            // 查询数据
            CmsDirectivePageQueryCommand pageQueryCommand = getPageQueryCommand(params);
            CmsContentDirectivePageQueryCommand cmsContentDirectivePageQueryCommand =
                    CmsContentDirectivePageQueryCommand.create(pageQueryCommand,
                            contentId,
                            siteId,
                            channelId,
                            isChannelIdNull, categoryId, null,isPublicCondition);
            List<CmsContentVO> cmsContentVOs = null;
            PageResponse pageResponse = null;
            if (pageQueryCommand.getIsPage()) {
                PageResponse<CmsContentVO> cmsContentVOPageResponse = iCmsDynamicApplicationService.pageQueryContent(cmsContentDirectivePageQueryCommand);
                pageResponse = cmsContentVOPageResponse;
                cmsContentVOs = cmsContentVOPageResponse.getData();
            } else {
                MultiResponse<CmsContentVO> cmsContentVOMultiResponse = iCmsDynamicApplicationService.queryListContent(cmsContentDirectivePageQueryCommand);
                cmsContentVOs = cmsContentVOMultiResponse.getData();
            }
            // 将查询到的数据 找到对应的 site 和 channel
            DirectiveHelper.CmsContentVosDependMaps cmsContentVosDependMaps = directiveHelper.getCmsContentVosDependMaps(cmsContentVOs, isIncludeMultimedia, modelContext,isPublicCondition);

            bodyRender(env, params, loopVars, body, cmsContentVOs, pageResponse,
                    cmsContentVO ->
                    {
                        CmsContentVO contentVO = (CmsContentVO) cmsContentVO;
                        return directiveHelper.cmsContentVOToCmsContentTemplateModelVOMapping(contentVO,
                                cmsContentVosDependMaps.getCmsSiteVOMap().get(contentVO.getCmsSiteId()),
                                contentVO.getCmsChannelId() == null ? null : cmsContentVosDependMaps.getCmsChannelVOMap().get(contentVO.getCmsChannelId()),
                                cmsContentVosDependMaps.getCmsContentMultimediaVOMap(), modelContext);
                    }
            );
        }
    }

}
