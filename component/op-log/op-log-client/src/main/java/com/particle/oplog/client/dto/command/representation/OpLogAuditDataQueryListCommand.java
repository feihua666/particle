package com.particle.oplog.client.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import com.particle.global.light.share.mybatis.anno.OrderBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
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
@Schema
public class OpLogAuditDataQueryListCommand extends AbstractBaseQueryCommand {



    @Like
    @Schema(description = "数据字段名称,左前缀匹配")
    private String name;


    @Like
    @Schema(description = "数据字段英文名称,左前缀匹配")
    private String propertyName;


    @Schema(description = "旧值")
    private String oldValue;


    @Schema(description = "新值")
    private String newValue;


    @Schema(description = "值改变类型字典id")
    private Long changeTypeDictId;


    @Schema(description = "值改变类型")
    private String changeType;


    @Schema(description = "类型对应的字典id")
    private Long typeDictId;


    @Schema(description = "类型")
    private String type;


    @Schema(description = "操作用户id")
    private Long userId;


    @Schema(description = "数据id")
    private Long dataId;

    @OrderBy(value = "dataTable",asc = true,order = 3)
    @Schema(description = "数据表名")
    private String dataTable;

    @OrderBy(value = "dataEntity",asc = true,order = 6)
    @Schema(description = "数据载体")
    private String dataEntity;


    @Schema(description = "操作日志id")
    private Long opLogId;

}
