package com.particle.cms.adapter.dynamic.directive;

import com.particle.cms.client.dto.command.directive.CmsChannelDirectivePageQueryCommand;
import com.particle.cms.client.dto.command.directive.CmsDirectivePageQueryCommand;
import com.particle.cms.client.dto.data.CmsChannelVO;
import com.particle.cms.client.dto.data.dynamic.CmsChannelTemplateModelVO;
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

/**
 * 栏目列表 指令
 * Created by yangwei
 * Created at 2018/11/19 17:39
 */
@Component
public class ChannelListDirective extends AbstractDirective {

    private final static String varName = "channelList";

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
            CmsChannelDirectivePageQueryCommand cmsChannelDirectivePageQueryCommand = CmsChannelDirectivePageQueryCommand.create(pageQueryCommand,
                    channelId,siteId,parentId);
            List<CmsChannelVO> cmsChannelVOs = null;
            if (pageQueryCommand.getIsPage()) {
                PageResponse<CmsChannelVO> cmsChannelVOPageResponse = iCmsDynamicApplicationService.pageQueryChannel(cmsChannelDirectivePageQueryCommand);
                cmsChannelVOs = cmsChannelVOPageResponse.getData();
            }else{
                MultiResponse<CmsChannelVO> cmsChannelVOMultiResponse = iCmsDynamicApplicationService.queryListChannel(cmsChannelDirectivePageQueryCommand);
                cmsChannelVOs = cmsChannelVOMultiResponse.getData();
            }

            bodyRender(env, params, loopVars, body, cmsChannelVOs,
                    varName,
                    cmsChannelVO -> CmsChannelTemplateModelVO.createByCmsChannelVO((CmsChannelVO) cmsChannelVO)
            );
        }
    }
}
