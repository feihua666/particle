package com.particle.oplog.client.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import com.particle.global.light.share.mybatis.anno.OrderBy;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.particle.global.light.share.mybatis.anno.Like;
/**
 * <p>
 * 操作日志审计数据 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-05-08 18:33:30
 */
@OrderBy(value = "createAt",asc = false,order = 0)
@Data
@ApiModel
public class OpLogAuditDataQueryListCommand extends AbstractBaseQueryCommand {



    @Like
    @ApiModelProperty(value = "数据字段名称,左前缀匹配")
    private String name;


    @Like
    @ApiModelProperty(value = "数据字段英文名称,左前缀匹配")
    private String propertyName;


    @ApiModelProperty(value = "旧值")
    private String oldValue;


    @ApiModelProperty(value = "新值")
    private String newValue;


    @ApiModelProperty(value = "值改变类型字典id")
    private Long changeTypeDictId;


    @ApiModelProperty(value = "值改变类型")
    private String changeType;


    @ApiModelProperty(value = "类型对应的字典id")
    private Long typeDictId;


    @ApiModelProperty(value = "类型")
    private String type;


    @ApiModelProperty(value = "操作用户id")
    private Long userId;


    @ApiModelProperty(value = "数据id")
    private Long dataId;

    @OrderBy(value = "dataTable",asc = true,order = 3)
    @ApiModelProperty(value = "数据表名")
    private String dataTable;

    @OrderBy(value = "dataEntity",asc = true,order = 6)
    @ApiModelProperty(value = "数据载体")
    private String dataEntity;


    @ApiModelProperty(value = "操作日志id")
    private Long opLogId;

}