package com.particle.func.domain;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;

/**
 * <p>
 * 菜单功能 领域模型
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Data
@Entity
public class Func extends AggreateRoot {

	private FuncId id;
    /**
     * 编码，模糊查询
     */
    private String code;
    /**
     * 名称，模糊查询
     */
    private String name;
    /**
     * 功能分组id
     */
    private Long funcGroupId;
    /**
     * 图标
     */
    private String icon;
    /**
     * 是否禁用
     */
    private Boolean isDisabled;
    /**
     * 禁用原因
     */
    private String disabledReason;
    /**
     * 地址
     */
    private String url;
    /**
     * shiro权限串，多个以逗号分隔
     */
    private String permissions;
    /**
     * 类型,字典id
     */
    private Long typeDictId;
    /**
     * 描述
     */
    private String remark;
    /**
     * 排序,默认按该字段升序排序
     */
    private Integer seq;


	/**
	 * 创建菜单功能领域模型对象
	 * @return 菜单功能领域模型对象，该对应所有属性为空，需要进行初始化操作
	 */
	public static Func create(){
		return DomainFactory.create(Func.class);
	}
}
