package com.particle.func.domain.application;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 功能应用 领域模型
 * </p>
 *
 * @author yw
 * @since 2023-04-17 10:12:23
 */
@Data
@Entity
public class FuncApplication extends AggreateRoot {

    private FuncApplicationId id;

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

    /**
    * 父级
    */
    private Long parentId;



    /**
     * 创建功能应用领域模型对象
     * @return 功能应用领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static FuncApplication create(){
        return DomainFactory.create(FuncApplication.class);
    }
}
