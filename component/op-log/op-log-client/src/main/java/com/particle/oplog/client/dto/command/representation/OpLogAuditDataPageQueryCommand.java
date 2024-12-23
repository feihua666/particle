package com.particle.oplog.client.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 操作日志审计数据 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-05-08 18:33:30
 */
@Data
@Schema
public class OpLogAuditDataPageQueryCommand extends AbstractBasePageQueryCommand {



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


    @Schema(description = "数据表名")
    private String dataTable;


    @Schema(description = "数据载体")
    private String dataEntity;


    @Schema(description = "操作日志id")
    private Long opLogId;

}
