package com.particle.crawler.client.execution.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import com.particle.component.light.share.trans.TransConstants;
import java.time.LocalDateTime;
/**
 * <p>
 * 爬虫执行实例 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:23:13
 */
@Data
@Schema
public class CrawlerExecutionVO extends AbstractBaseIdVO {

    @Schema(description = "爬虫定义ID")
    private Long crawlerDefinitionId;
    
    @Schema(description = "执行时使用的版本ID")
    private Long crawlerDefinitionHistoryId;
    
    @Schema(description = "执行状态字典id：running/success/failed")
    private Long statusDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "statusDictId",mapValueField = "name")
    @Schema(description = "执行状态字典id：running/success/failed对应字典名称")
    private String statusDictName;
        
    @Schema(description = "触发方式字典id：manual/api/schedule")
    private Long triggerTypeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "triggerTypeDictId",mapValueField = "name")
    @Schema(description = "触发方式字典id：manual/api/schedule对应字典名称")
    private String triggerTypeDictName;
        
    @Schema(description = "全局上下文数据json")
    private String contextJson;
    
    @Schema(description = "运行开始时间")
    private LocalDateTime startAt;
        
    @Schema(description = "运行结束时间")
    private LocalDateTime finishAt;
        
    @Schema(description = "错误信息")
    private String errorMsg;
    


}
