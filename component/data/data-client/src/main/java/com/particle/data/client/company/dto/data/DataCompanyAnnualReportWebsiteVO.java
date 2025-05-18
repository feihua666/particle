package com.particle.data.client.company.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import com.particle.component.light.share.trans.TransConstants;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业年报网站网店 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:16
 */
@Data
@Schema
public class DataCompanyAnnualReportWebsiteVO extends AbstractBaseIdVO {

    @Schema(description = "企业表ID")
    private Long companyId;
    
    @Schema(description = "企业年报表ID")
    private Long companyAnnualReportId;
    
    @Schema(description = "年报年度")
    private Integer year;
    
    @Schema(description = "类型")
    private Long typeDictId;

	@Schema(description = "类型名称，如：网站、网店")
	private String typeName;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "typeDictId",mapValueField = "name")
    @Schema(description = "类型对应字典名称")
    private String typeDictName;
        
    @Schema(description = "名称")
    private String name;
    
    @Schema(description = "网址")
    private String url;

	@Schema(description = "数据md5,type_name + name + url")
	private String dataMd5;
    
    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
        


}