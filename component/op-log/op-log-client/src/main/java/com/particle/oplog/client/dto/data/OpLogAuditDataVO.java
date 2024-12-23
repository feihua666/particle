package com.particle.oplog.client.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 操作日志审计数据 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-05-08 18:33:30
 */
@Data
@Schema
public class OpLogAuditDataVO extends AbstractBaseIdVO {

    @Schema(description = "数据字段名称")
    private String name;

    @Schema(description = "数据字段英文名称")
    private String propertyName;

    @Schema(description = "旧值")
    private String oldValue;

    @Schema(description = "新值")
    private String newValue;

    @Schema(description = "值改变类型字典id")
    private Long changeTypeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "changeTypeDictId",mapValueField = "name")
    @Schema(description = "值改变类型字典id对应字典名称")
    private String changeTypeDictName;

    @Schema(description = "值改变类型")
    private String changeType;

    @Schema(description = "类型对应的字典id")
    private Long typeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "typeDictId",mapValueField = "name")
    @Schema(description = "类型对应的字典id对应字典名称")
    private String typeDictName;

    @Schema(description = "类型")
    private String type;

    @Schema(description = "操作用户id")
    private Long userId;

    @Schema(description = "数据id")
    private Long dataId;

    @Schema(description = "数据表名")
    private String dataTable;

    @Schema(description = "数据载体")
    private String dataEntity;

    @Schema(description = "操作日志id")
    private Long opLogId;


    @TransBy(tableName = TransTableNameConstants.component_op_log, byFieldName = "opLogId", mapValueField = "name")
    @Schema(description = "操作日志操作名称")
    private String opLogName;

    @Schema(description = "描述")
    private String remark;



}
