package com.particle.cms.client.dto.data;

import com.particle.global.dto.basic.VO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 通用首页地址 响应对象
 * </p>
 *
 * @author yw
 * @since 2026-01-20 22:23:51
 */
@Data
@Schema
public class CmsIndexItemUrlVO extends VO {

    @Schema(description = "地址类型")
    private String type;

    @Schema(description = "地址类型名称")
    private String typeName;

    @Schema(description = "地址")
    private String indexUrl;

    public static CmsIndexItemUrlVO createDynamic(String indexUrl){
        return create("dynamic","动态地址",indexUrl);
    }
    public static CmsIndexItemUrlVO createDynamicPreview(String indexUrl){
        return create("dynamic_preview","动态预览地址",indexUrl);
    }
    public static CmsIndexItemUrlVO create(String type,String typeName,String indexUrl){
        CmsIndexItemUrlVO cmsIndexItemUrlVO = new CmsIndexItemUrlVO();
        cmsIndexItemUrlVO.setType(type);
        cmsIndexItemUrlVO.setTypeName(typeName);
        cmsIndexItemUrlVO.setIndexUrl(indexUrl);
        return cmsIndexItemUrlVO;
    }
}
