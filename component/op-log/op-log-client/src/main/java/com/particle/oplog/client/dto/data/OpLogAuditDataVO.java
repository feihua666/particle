package com.particle.oplog.client.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransTableNameConstants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import com.particle.component.light.share.trans.TransConstants;
/**
 * <p>
 * 操作日志审计数据 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-05-08 18:33:30
 */
@Data
@ApiModel
public class OpLogAuditDataVO extends AbstractBaseIdVO {

    @ApiModelProperty("数据字段名称")
    private String name;
    
    @ApiModelProperty("数据字段英文名称")
    private String propertyName;
    
    @ApiModelProperty("旧值")
    private String oldValue;
    
    @ApiModelProperty("新值")
    private String newValue;
    
    @ApiModelProperty("值改变类型字典id")
    private Long changeTypeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "changeTypeDictId",mapValueField = "name")
    @ApiModelProperty("值改变类型字典id对应字典名称")
    private String changeTypeDictName;
        
    @ApiModelProperty("值改变类型")
    private String changeType;
    
    @ApiModelProperty("类型对应的字典id")
    private Long typeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "typeDictId",mapValueField = "name")
    @ApiModelProperty("类型对应的字典id对应字典名称")
    private String typeDictName;
        
    @ApiModelProperty("类型")
    private String type;
    
    @ApiModelProperty("操作用户id")
    private Long userId;
    
    @ApiModelProperty("数据id")
    private Long dataId;
    
    @ApiModelProperty("数据表名")
    private String dataTable;
    
    @ApiModelProperty("数据载体")
    private String dataEntity;
    
    @ApiModelProperty("操作日志id")
    private Long opLogId;


    @TransBy(tableName = TransTableNameConstants.component_op_log, byFieldName = "opLogId", mapValueField = "name")
    @ApiModelProperty("操作日志操作名称")
    private String opLogName;
    
    @ApiModelProperty("描述")
    private String remark;
    


}
