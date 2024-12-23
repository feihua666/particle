package com.particle.openplatform.client.doc.dto.data;

import com.particle.global.light.share.trans.anno.TransField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 开放接口文档接口 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:01
 */
@Data
@Schema
public class OpenplatformDocApiDetailVO extends OpenplatformDocApiVO {

    @TransField
    @Schema(description = "文档内容")
    private OpenplatformDocApiDocVO docApiDocVO;

    @TransField
    @Schema(description = "文档参数字段，key为分类字典值")
    private Map<String,List<OpenplatformDocApiDocParamFieldBasicVO>> docApiDocParamFieldVOsMap;

    @TransField
    @Schema(description = "文档响应码")
    private List<OpenplatformDocApiDocResponseCodeVO> docApiDocResponseCodeVOs;

    @TransField
    @Schema(description = "文档示例代码")
    private List<OpenplatformDocApiDocExampleCodeVO> docApiDocExampleCodeVOs;


    public static OpenplatformDocApiDetailVO create(OpenplatformDocApiVO openplatformDocApiVO) {
        OpenplatformDocApiDetailVO openplatformDocApiDetailVO = new OpenplatformDocApiDetailVO();
        openplatformDocApiDetailVO.setId(openplatformDocApiVO.getId());

        openplatformDocApiDetailVO.setCode(openplatformDocApiVO.getCode());
        openplatformDocApiDetailVO.setName(openplatformDocApiVO.getName());
        openplatformDocApiDetailVO.setNameSimple(openplatformDocApiVO.getNameSimple());
        openplatformDocApiDetailVO.setSeq(openplatformDocApiVO.getSeq());
        openplatformDocApiDetailVO.setDescription(openplatformDocApiVO.getDescription());

        openplatformDocApiDetailVO.setPricePerTime(openplatformDocApiVO.getPricePerTime());
        openplatformDocApiDetailVO.setPricePerTimeDesc(openplatformDocApiVO.getPricePerTimeDesc());
        openplatformDocApiDetailVO.setVersion(openplatformDocApiVO.getVersion());
        return openplatformDocApiDetailVO;
    }
}
