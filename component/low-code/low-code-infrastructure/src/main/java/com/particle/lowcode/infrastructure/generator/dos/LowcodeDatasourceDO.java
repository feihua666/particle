package com.particle.lowcode.infrastructure.generator.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * <p>
 * 低代码数据源表
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Data
@TableName("component_lowcode_datasource")
public class LowcodeDatasourceDO extends BaseDO {
    /**
    * 编码
    */
    private String code;
    /**
    * 名称
    */
    private String name;
    /**
    * 驱动类名
    */
    private String driverClassName;
    /**
    * 地址
    */
    private String url;
    /**
    * 用户名
    */
    private String username;
    /**
    * 密码
    */
    private String password;
    /**
    * 描述,注意事项等
    */
    private String remark;

}
