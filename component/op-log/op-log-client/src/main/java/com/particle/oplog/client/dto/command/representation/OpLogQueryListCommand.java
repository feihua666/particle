package com.particle.oplog.client.dto.command.representation;

import com.particle.common.client.dto.command.tree.AbstractBaseTreeQueryCommand;
import com.particle.global.light.share.mybatis.anno.*;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.mybatis.anno.Like;
import java.time.LocalDateTime;
/**
 * <p>
 * 操作日志 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-05-08 18:32:34
 */
@OrderBy(value = "operateAt",asc = false)
@Data
@Schema
public class OpLogQueryListCommand extends AbstractBaseTreeQueryCommand {



    @Like
    @Schema(description = "操作名称,左前缀匹配")
    private String name;


    @Schema(description = "模块对应的字典id")
    private Long moduleDictId;


    @Like
    @Schema(description = "模块,左前缀匹配")
    private String module;


    @Schema(description = "类型对应的字典id")
    private Long typeDictId;


    @Like
    @Schema(description = "类型,左前缀匹配")
    private String type;


    @Schema(description = "操作用户id")
    private Long userId;


    @Like
    @Schema(description = "操作用户姓名,左前缀匹配")
    private String userName;


    @Like
    @Schema(description = "操作用户昵称,左前缀匹配")
    private String userNickname;


    @Schema(description = "请求地址")
    private String url;

    @Schema(description = "主数据id")
    private Long mainDataId;


    @Schema(description = "主数据表名")
    private String mainDataTable;


    @Schema(description = "主数据载体")
    private String mainDataEntity;


    @Gt(value = "operateAt", eq = true)
    @Schema(description = "操作时间")
    private LocalDateTime operateAtStart;

    @Lt(value = "operateAt", eq = true)
    @Schema(description = "操作时间")
    private LocalDateTime operateAtEnd;


}
