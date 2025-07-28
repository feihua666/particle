package com.particle.cms.adapter.dynamic.directive;

import com.particle.cms.client.dto.command.directive.CmsContentCategoryDirectivePageQueryCommand;
import com.particle.cms.client.dto.command.directive.CmsDirectivePageQueryCommand;
import com.particle.cms.client.dto.data.CmsContentCategoryVO;
import com.particle.cms.client.dto.data.dynamic.CmsContentCategoryTemplateModelVO;
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

import static com.particle.cms.adapter.dynamic.directive.ContentListDirective.param_content_categoryId;

/**
 * 分类列表 指令
 * Created by yangwei
 * Created at 2018/11/19 17:39
 */
@Component
public class ContentCategoryListDirective extends AbstractDirective {

    private final static String varName = "categoryList";

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

        Long categoryId = getParamLong(param_content_categoryId,params);

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
            CmsDirectivePageQueryCommand pageQueryCommand = getPageQueryCommand(params);
            CmsContentCategoryDirectivePageQueryCommand cmsContentCategoryDirectivePageQueryCommand = CmsContentCategoryDirectivePageQueryCommand.create(pageQueryCommand,
                    categoryId,siteId,channelId,parentId);
            List<CmsContentCategoryVO> cmsContentCategoryVOs = null;
            if (pageQueryCommand.getIsPage()) {
                PageResponse<CmsContentCategoryVO> cmsContentCategoryVOPageResponse = iCmsDynamicApplicationService.pageQueryContentCategory(cmsContentCategoryDirectivePageQueryCommand);
                cmsContentCategoryVOs = cmsContentCategoryVOPageResponse.getData();
            }else{
                MultiResponse<CmsContentCategoryVO> cmsContentCategoryVOMultiResponse = iCmsDynamicApplicationService.queryListContentCategory(cmsContentCategoryDirectivePageQueryCommand);
                cmsContentCategoryVOs = cmsContentCategoryVOMultiResponse.getData();
            }

            bodyRender(env, params, loopVars, body, cmsContentCategoryVOs,
                    varName,
                    cmsContentCategoryVO -> CmsContentCategoryTemplateModelVO.createByCmsContentCategoryVO((CmsContentCategoryVO) cmsContentCategoryVO)
            );
        }
    }
}
