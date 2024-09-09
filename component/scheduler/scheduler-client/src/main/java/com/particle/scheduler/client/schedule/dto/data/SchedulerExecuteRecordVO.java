package com.particle.scheduler.client.schedule.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import com.particle.component.light.share.trans.TransConstants;
import java.time.LocalDateTime;
/**
 * <p>
 * 任务计划执行记录 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-09-03 15:25:23
 */
@Data
@Schema
public class SchedulerExecuteRecordVO extends AbstractBaseIdVO {

    @Schema(description = "schedulerName")
    private String schedulerName;
    
    @Schema(description = "schedulerInstanceId")
    private String schedulerInstanceId;
    
    @Schema(description = "任务名称")
    private String name;
    
    @Schema(description = "任务组")
    private String groupName;
    
    @Schema(description = "执行参数")
    private String params;
    
    @Schema(description = "执行状态")
    private Long executeStatusDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "executeStatusDictId",mapValueField = "name")
    @Schema(description = "执行状态对应字典名称")
    private String executeStatusDictName;
        
    @Schema(description = "运行开始时间")
    private LocalDateTime startAt;
        
    @Schema(description = "运行结束时间")
    private LocalDateTime finishAt;
        
    @Schema(description = "本地主机ip")
    private String localHostIp;
    
    @Schema(description = "本地主机名称")
    private String localHostName;
    
    @Schema(description = "链路追踪id")
    private String traceId;
    
    @Schema(description = "运行结果")
    private String result;
    


}
