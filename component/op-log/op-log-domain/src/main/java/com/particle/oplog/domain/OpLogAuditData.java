package com.particle.oplog.domain;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 操作日志审计数据 领域模型
 * </p>
 *
 * @author yw
 * @since 2023-05-08 18:33:30
 */
@Data
@Entity
public class OpLogAuditData extends AggreateRoot {

    private OpLogAuditDataId id;

    /**
    * 数据字段名称,模糊查询
    */
    private String name;

    /**
    * 数据字段英文名称
    */
    private String propertyName;

    /**
    * 旧值
    */
    private String oldValue;

    /**
    * 新值
    */
    private String newValue;

    /**
    * 值改变类型字典id
    */
    private Long changeTypeDictId;

    /**
    * 值改变类型，添加、删除、修改
    */
    private String changeType;

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
    * 数据id
    */
    private Long dataId;

    /**
    * 数据表名
    */
    private String dataTable;

    /**
    * 数据载体，一般为实体DO类全路径
    */
    private String dataEntity;

    /**
    * 操作日志id
    */
    private Long opLogId;

    /**
    * 描述
    */
    private String remark;



    /**
     * 创建操作日志审计数据领域模型对象
     * @return 操作日志审计数据领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static OpLogAuditData create(){
        return DomainFactory.create(OpLogAuditData.class);
    }
}
