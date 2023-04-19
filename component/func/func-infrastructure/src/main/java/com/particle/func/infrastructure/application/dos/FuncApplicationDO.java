package com.particle.func.infrastructure.application.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseTreeDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
/**
 * <p>
 * 功能应用表
 * </p>
 *
 * @author yw
 * @since 2023-04-17 10:12:23
 */
@Data
@TableName("component_func_application")
public class FuncApplicationDO extends BaseTreeDO {

    /**
    * 应用编码,模糊查询
    */
    private String code;

    /**
    * 功能应用名称,模糊查询
    */
    private String name;

    /**
    * 是否禁用
    */
    private Boolean isDisabled;

    /**
    * 禁用原因
    */
    private String disabledReason;

    /**
    * 应用主题，一个代表客户端主题样式的字符串
    */
    private String applicationTheme;

    /**
    * 应用默认的页面路由
    */
    private String applicationDefaultRoute;
    /**
     * 应用logo地址
     */
    private String applicationLogoUrl;
    /**
     * 应用图标地址
     */
    private String applicationIconUrl;

    /**
    * 额外配置json
    */
    private String configJson;

    /**
    * 是否为分组，不是分组就是应用，没有其它的
    */
    private Boolean isGroup;

    /**
    * 排序,默认按该字段升序排序
    */
    private Integer seq;

    /**
    * 描述
    */
    private String remark;


}
