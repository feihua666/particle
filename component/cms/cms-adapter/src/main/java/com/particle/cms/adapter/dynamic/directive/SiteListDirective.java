package com.particle.cms.adapter.dynamic.directive;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.cms.client.dto.command.directive.CmsDirectivePageQueryCommand;
import com.particle.cms.client.dto.command.directive.CmsSiteDirectivePageQueryCommand;
import com.particle.cms.client.dto.data.CmsSiteVO;
import com.particle.cms.client.dto.data.dynamic.CmsSiteTemplateModelVO;
import com.particle.cms.client.dto.data.dynamic.CmsTemplateModelContext;
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
 * 站点列表 自定义指令
 * Created by yangwei
 * Created at 2018/11/19 17:39
 */
@Component
public class SiteListDirective extends AbstractDirective {

    /**
     * 站点指令支持属性
     */
    protected static final String param_site_isPrimeSite = "isPrimeSite";

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
        String isPrimeSite = getParamStr(param_site_isPrimeSite,params);
        Boolean isPrimeSiteBool = null;
        if (StrUtil.isNotEmpty(isPrimeSite)) {
            isPrimeSiteBool = BooleanUtil.toBoolean(isPrimeSite);
        }
        if (body != null) {
            CmsTemplateModelContext modelContext = getModelContext();
            Boolean isPublicCondition = getIsPublicCondition();

            CmsDirectivePageQueryCommand pageQueryCommand = getPageQueryCommand(params);
            CmsSiteDirectivePageQueryCommand cmsSiteDirectivePageQueryCommand = CmsSiteDirectivePageQueryCommand.create(pageQueryCommand,
                    siteId,isPrimeSiteBool,isPublicCondition);
            List<CmsSiteVO> cmsSiteVOs = null;
            PageResponse pageResponse = null;
            if (pageQueryCommand.getIsPage()) {
                PageResponse<CmsSiteVO> cmsSiteVOPageResponse = iCmsDynamicApplicationService.pageQuerySite(cmsSiteDirectivePageQueryCommand);
                pageResponse = cmsSiteVOPageResponse;
                cmsSiteVOs = cmsSiteVOPageResponse.getData();
            }else{
                MultiResponse<CmsSiteVO> cmsSiteVOMultiResponse = iCmsDynamicApplicationService.queryListSite(cmsSiteDirectivePageQueryCommand);
                cmsSiteVOs = cmsSiteVOMultiResponse.getData();
            }

            bodyRender(env, params, loopVars, body, cmsSiteVOs,pageResponse,
                    cmsSiteVO -> CmsSiteTemplateModelVO.createByCmsSiteVO((CmsSiteVO) cmsSiteVO,modelContext)
            );
        }
    }
}
