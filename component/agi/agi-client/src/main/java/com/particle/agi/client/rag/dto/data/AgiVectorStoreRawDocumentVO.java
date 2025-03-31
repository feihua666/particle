package com.particle.agi.client.rag.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import com.particle.component.light.share.trans.TransConstants;
/**
 * <p>
 * 知识存储原始文档 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-01-08 16:54:59
 */
@Data
@Schema
public class AgiVectorStoreRawDocumentVO extends AbstractBaseIdVO {

    @Schema(description = "名称")
    private String name;
    
    @Schema(description = "文件名称")
    private String fileName;
    
    @Schema(description = "类型")
    private Long typeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "typeDictId",mapValueField = "name")
    @Schema(description = "类型对应字典名称")
    private String typeDictName;
        
    @Schema(description = "地址")
    private String url;
    
    @Schema(description = "是否已嵌入")
    private Boolean isEmbedded;
    
    @Schema(description = "描述")
    private String remark;
    


}
