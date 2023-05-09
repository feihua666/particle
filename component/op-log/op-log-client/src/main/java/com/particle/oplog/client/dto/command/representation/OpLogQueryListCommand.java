package com.particle.oplog.client.dto.command.representation;

import com.particle.common.client.dto.command.tree.AbstractBaseTreeQueryCommand;
import com.particle.global.light.share.mybatis.anno.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel
public class OpLogQueryListCommand extends AbstractBaseTreeQueryCommand {



    @Like
    @ApiModelProperty(value = "操作名称,左前缀匹配")
    private String name;


    @ApiModelProperty(value = "模块对应的字典id")
    private Long moduleDictId;


    @Like
    @ApiModelProperty(value = "模块,左前缀匹配")
    private String module;


    @ApiModelProperty(value = "类型对应的字典id")
    private Long typeDictId;


    @Like
    @ApiModelProperty(value = "类型,左前缀匹配")
    private String type;


    @ApiModelProperty(value = "操作用户id")
    private Long userId;


    @Like
    @ApiModelProperty(value = "操作用户姓名,左前缀匹配")
    private String userName;


    @Like
    @ApiModelProperty(value = "操作用户昵称,左前缀匹配")
    private String userNickname;


    @ApiModelProperty(value = "请求地址")
    private String url;

    @ApiModelProperty(value = "主数据id")
    private Long mainDataId;


    @ApiModelProperty(value = "主数据表名")
    private String mainDataTable;


    @ApiModelProperty(value = "主数据载体")
    private String mainDataEntity;


    @Gt(value = "operateAt", eq = true)
    @ApiModelProperty(value = "操作时间")
    private LocalDateTime operateAtStart;

    @Lt(value = "operateAt", eq = true)
    @ApiModelProperty(value = "操作时间")
    private LocalDateTime operateAtEnd;


}
