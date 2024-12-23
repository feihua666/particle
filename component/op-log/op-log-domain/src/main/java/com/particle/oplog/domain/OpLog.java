package com.particle.oplog.domain;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * <p>
 * 操作日志 领域模型
 * </p>
 *
 * @author yw
 * @since 2023-05-08 18:32:34
 */
@Data
@Entity
public class OpLog extends AggreateRoot {

    private OpLogId id;

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

    /**
    * 父级
    */
    private Long parentId;



    /**
     * 创建操作日志领域模型对象
     * @return 操作日志领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static OpLog create(){
        return DomainFactory.create(OpLog.class);
    }
}
