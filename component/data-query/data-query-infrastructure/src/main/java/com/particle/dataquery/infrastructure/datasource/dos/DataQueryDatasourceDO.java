package com.particle.dataquery.infrastructure.datasource.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
/**
 * <p>
 * 数据查询数据源表
 * </p>
 *
 * @author yw
 * @since 2023-03-08 14:29:00
 */
@Data
@TableName("component_data_query_datasource")
public class DataQueryDatasourceDO extends BaseDO {

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


}
