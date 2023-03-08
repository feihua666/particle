package com.particle.dataquery.domain.datasource;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 数据查询数据源 领域模型
 * </p>
 *
 * @author yw
 * @since 2023-03-08 14:29:00
 */
@Data
@Entity
public class DataQueryDatasource extends AggreateRoot {

    private DataQueryDatasourceId id;

    /**
    * 编码
    */
    private String code;

    /**
    * 名称
    */
    private String name;

    /**
    * 类型，字典id
    */
    private Long typeDictId;

    /**
    * json配置，根据数据源类型对应不同的配置信息
    */
    private String configJson;

    /**
    * 用户名
    */
    private String username;

    /**
    * 密码
    */
    private String password;

    /**
    * 数据查询供应商id
    */
    private Long dataQueryProviderId;

    /**
    * 描述,注意事项等
    */
    private String remark;



    /**
     * 创建数据查询数据源领域模型对象
     * @return 数据查询数据源领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataQueryDatasource create(){
        return DomainFactory.create(DataQueryDatasource.class);
    }
}
