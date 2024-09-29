package com.particle.openplatform.client.openapi.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import com.particle.component.light.share.trans.TransTableNameConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import com.particle.component.light.share.trans.TransConstants;
import java.time.LocalDateTime;
/**
 * <p>
 * 开放接口批量查询记录 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-09-19 11:44:36
 */
@Data
@Schema
public class OpenplatformOpenapiBatchQueryRecordVO extends AbstractBaseIdVO {

    @Schema(description = "开放平台应用id")
    private Long openplatformAppId;

    @TransBy(tableName = TransTableNameConstants.component_openplatform_app, byFieldName = "openplatformAppId", mapValueField = "name")
    @Schema(description = "开放平台应用名称")
    private String openplatformAppName;

    @Schema(description = "开放接口id")
    private Long openplatformOpenapiId;

    @TransBy(tableName = TransTableNameConstants.component_openplatform_openapi, byFieldName = "openplatformOpenapiId", mapValueField = "name")
    @Schema(description = "开放接口名称")
    private String openplatformOpenapiName;

    @Schema(description = "客户id")
    private Long customerId;
    
    @Schema(description = "执行状态")
    private Long executeStatusDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "executeStatusDictId",mapValueField = "name")
    @Schema(description = "执行状态对应字典名称")
    private String executeStatusDictName;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "executeStatusDictId",mapValueField = "value")
    @Schema(description = "执行状态对应字典值")
    private String executeStatusDictValue;
        
    @Schema(description = "成功条数")
    private Integer successCount;
    
    @Schema(description = "失败条数")
    private Integer failCount;
    
    @Schema(description = "总条数")
    private Integer totalCount;
    
    @Schema(description = "用户id")
    private Long userId;

    @TransBy(type = TransConstants.TRANS_USER_BY_ID,byFieldName = "userId",mapValueField = "nickname")
    @Schema(description = "用户昵称")
    private String userNickname;

    @Schema(description = "查询时间")
    private LocalDateTime queryAt;
        
    @Schema(description = "追踪id")
    private String traceId;

	@Schema(description = "上传文件名")
	private String uploadFileName;

	@Schema(description = "导出的文件地址")
	private String exportFileUrl;
    
    @Schema(description = "描述")
    private String remark;
    


}