package com.particle.cms.client.dto.data.dynamic;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.HttpUtil;
import com.particle.common.client.dto.data.AbstractBaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Map;

/**
 * <p>
 * 页面 模型响应对象
 * </p>
 *
 * @author yw
 * @since 2026-01-27 20:35:57
 */
@Data
@Schema
public class CmsPageTemplateModelVO extends AbstractBaseVO {

    @Schema(description = "当前路径地址，不包括参数，如：/cms/xxxx/xxx.htm")
    private String currentUri;

    @Schema(description = "当前地址，不包括参数，如：http://xxxx.xx.com/cms/xxxx/xxx.htm")
    private String currentUrl;

    @Schema(description = "当前地址，包括参数，如：http://xxxx.xx.com/cms/xxxx/xxx.htm?a=xxx&c=xxx")
    private String currentFullUrl;

    @Schema(description = "请求页码，从1开始，不传默认为1")
    private Long pageNo = 1L;

    @Schema(description = "请求每页条数，不传默认为10")
    private Long pageSize = 10L;

    @Schema(description = "所有请求参数")
    private Map<String, String> params;

    public static CmsPageTemplateModelVO create(String currentUri,String currentUrl, Map<String, String> params) {

        CmsPageTemplateModelVO cmsPageTemplateModelVO = new CmsPageTemplateModelVO();
        cmsPageTemplateModelVO.currentUri = currentUri;
        cmsPageTemplateModelVO.currentUrl = currentUrl;
        Map paramsMap = params;
        cmsPageTemplateModelVO.currentFullUrl = HttpUtil.urlWithFormUrlEncoded(currentUrl, paramsMap, CharsetUtil.CHARSET_UTF_8);
        cmsPageTemplateModelVO.pageNo = MapUtil.getLong(params, "pageNo", 1L);
        cmsPageTemplateModelVO.pageSize = MapUtil.getLong(params, "pageSize", 10L);

        cmsPageTemplateModelVO.params = params;

        return cmsPageTemplateModelVO;
    }
}
