package com.particle.oplog.infrastructure.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseTreeDO;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * <p>
 * 操作日志表
 * </p>
 *
 * @author yw
 * @since 2023-05-08 18:32:34
 */
@Data
@TableName("component_op_log")
public class OpLogDO extends BaseTreeDO {

    /**
    * 操作名称,模糊查询
    */
    private String name;

    /**
    * 模块对应的字典id
    */
    private Long moduleDictId;

    /**
    * 模块，一个模块英语字符串标识
    */
    private String module;

    /**
    * 类型对应的字典id
    */
    private Long typeDictId;

    /**
    * 类型，一个操作类型英文字符串标识，如：create、delete
    */
    private String type;

    /**
    * 操作用户id
    */
    private Long userId;

    /**
    * 操作用户姓名
    */
    private String userName;

    /**
    * 操作用户昵称
    */
    private String userNickname;

    /**
    * 操作用户头像
    */
    private String userAvatar;

    /**
    * 请求地址，要求带http
    */
    private String url;

    /**
    * 请求ip
    */
    private String ip;

    /**
    * 主数据id
    */
    private Long mainDataId;

    /**
    * 主数据表名
    */
    private String mainDataTable;

    /**
    * 主数据载体，一般为实体DO类全路径
    */
    private String mainDataEntity;

    /**
    * 操作时间
    */
    private LocalDateTime operateAt;

    /**
    * 描述
    */
    private String remark;


}
