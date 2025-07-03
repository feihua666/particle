package com.particle.cms.infrastructure.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseTreeDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * <p>
 * 栏目表
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:55
 */
@Accessors(chain = true)
@Data
@TableName("component_cms_channel")
public class CmsChannelDO extends BaseTreeDO {

    /**
    * 站点id
    */
    private Long cmsSiteId;

    /**
    * 栏目编码
    */
    private String code;

    /**
    * 栏目名称
    */
    private String name;

    /**
    * 栏目模板路径
    */
    private String templatePath;

    /**
    * 栏目模板,默认index.html
    */
    private String templateIndex;

    /**
    * 栏目静态页存放路径
    */
    private String staticPath;

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

    /**
    * 排序,默认按该字段升序排序
    */
    private Integer seq;


}
