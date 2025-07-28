package com.particle.cms.adapter.dynamic.directive;

import com.particle.cms.client.dto.command.directive.CmsContentDirectivePageQueryCommand;
import com.particle.cms.client.dto.command.directive.CmsDirectivePageQueryCommand;
import com.particle.cms.client.dto.data.CmsContentMultimediaVO;
import com.particle.cms.client.dto.data.CmsContentVO;
import com.particle.cms.client.dto.data.dynamic.CmsContentMultimediaTemplateModelVO;
import com.particle.cms.client.dto.data.dynamic.CmsContentTemplateModelVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by yangwei
 * Created at 2018/11/19 17:39
 */
@Component
public class ContentListDirective extends AbstractDirective {


    /**
     * 站点指令支持属性
     */
    protected static final String param_content_categoryId = "categoryId";

    private final static String varName = "contentList";
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
        Long categoryId = getParamLong(param_content_categoryId,params);

        if (body != null) {
            CmsDirectivePageQueryCommand pageQueryCommand = getPageQueryCommand(params);
            CmsContentDirectivePageQueryCommand cmsContentDirectivePageQueryCommand = CmsContentDirectivePageQueryCommand.create(pageQueryCommand,
                    contentId,siteId,channelId,categoryId);
            List<CmsContentVO> cmsContentVOs = null;
            if (pageQueryCommand.getIsPage()) {
                PageResponse<CmsContentVO> cmsContentVOPageResponse = iCmsDynamicApplicationService.pageQueryContent(cmsContentDirectivePageQueryCommand);
                cmsContentVOs = cmsContentVOPageResponse.getData();
            }else{
                MultiResponse<CmsContentVO> cmsContentVOMultiResponse = iCmsDynamicApplicationService.queryListContent(cmsContentDirectivePageQueryCommand);
                cmsContentVOs = cmsContentVOMultiResponse.getData();
            }

            bodyRender(env, params, loopVars, body, cmsContentVOs,
                    varName,
                    cmsContentVO -> cmsContentMapping((CmsContentVO) cmsContentVO)
            );
        }
    }

    /**
     * 映射结果，添加内容多媒体
     * @param cmsContentVO
     * @return
     */
    private CmsContentTemplateModelVO cmsContentMapping(CmsContentVO cmsContentVO) {
        CmsContentTemplateModelVO cmsContentTemplateModelVO = CmsContentTemplateModelVO.createByCmsContentVO(cmsContentVO);
        if (cmsContentVO != null) {
            Long contentVOId = cmsContentVO.getId();
            MultiResponse<CmsContentMultimediaVO> cmsContentMultimediaVOMultiResponse = iCmsDynamicApplicationService.listContentMultimediaByContentId(contentVOId);
            List<CmsContentMultimediaVO> contentMultimediaVOS = cmsContentMultimediaVOMultiResponse.getData();
            if (contentMultimediaVOS != null) {
                List<CmsContentMultimediaTemplateModelVO> contentMultimediaTemplateModelVOS = contentMultimediaVOS
                        .stream()
                        .map(cmsContentMultimediaVO -> CmsContentMultimediaTemplateModelVO.createByCmsContentMultimediaVO(cmsContentMultimediaVO))
                        .collect(Collectors.toList());
                cmsContentTemplateModelVO.setContentMultimedias(contentMultimediaTemplateModelVOS);
            }
        }
        return cmsContentTemplateModelVO;
    }
}
