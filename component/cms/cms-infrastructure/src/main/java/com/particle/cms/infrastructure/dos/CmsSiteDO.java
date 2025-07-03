package com.particle.cms.infrastructure.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * <p>
 * 站点表
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:04
 */
@Accessors(chain = true)
@Data
@TableName("component_cms_site")
public class CmsSiteDO extends BaseDO {

    /**
    * 站点编码
    */
    private String code;

    /**
    * 站点名称
    */
    private String name;

    /**
    * 站点域名
    */
    private String domain;

    /**
    * 站点访问上下文路径，主要应用于动态页访问，可以实现在一个域名下区分不同的站点
    */
    private String path;

    /**
    * 站点模板路径，站点模板存放路径，pc默认default,移动端默认mobile,站点首页模板默认index.html
    */
    private String templatePath;

    /**
    * 站点首页模板,默认index.html
    */
    private String templateIndex;

    /**
    * 站点静态页存放路径
    */
    private String staticPath;

    /**
    * 是否主站点，同一个域名即domain字段下，只能有一个主站
    */
    private Boolean isPrimeSite;

    /**
    * 页面访问量,页面展示次数
    */
    private Integer pv;

    /**
    * 页面访问ip数,一天之内相同IP地址只被计算一次
    */
    private Integer iv;

    /**
    * 页面访问用户数,页面访问电脑客户端数,一天之内相同cookie的访问只被计算1次
    */
    private Integer uv;


}
