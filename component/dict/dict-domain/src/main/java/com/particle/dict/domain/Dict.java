package com.particle.dict.domain;

import cn.hutool.core.util.StrUtil;
import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;

/**
 * <p>
 * 字典 领域模型
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Data
@Entity
public class Dict extends AggreateRoot {

	private DictId id;
    /**
     * 字典编码,模糊查询，字典组时必填
     */
    private String code;
    /**
     * 字典名称,模糊查询
     */
    private String name;
    /**
     * 字典值,模糊查询
     */
    private String value;
	/**
	 * 字典值单位
	 */
	private String valueUnit;
    /**
     * 是否为系统字典，一般系统字典代码中会做判断，不能修改或删除
     */
    private Boolean isSystem;
    /**
     * 是否为公共字典，如果为公共字典不限制使用，否则按相应数据权限查询
     */
    private Boolean isPublic;
    /**
     * 是否为字典组
     */
    private Boolean isGroup;

	/**
	 * 是否为字典项
	 */
	private Boolean isItem;
    /**
     * 是否禁用
     */
    private Boolean isDisabled;
    /**
     * 禁用原因
     */
    private String disabledReason;
    /**
     * 私有标识,模糊查询
     */
    private String privateFlag;
    /**
     * 私有标识备忘
     */
    private String privateFlagMemo;
    /**
     * 分组标识
     */
    private String groupFlag;
    /**
     * 分组标识备忘
     */
    private String groupFlagMemo;
    /**
     * 标签，多个以逗号分隔，用来区分字典项
     */
    private String tags;

	/**
	 * 关联字典组编码，用于在字典项下还有字典项的扩展场景
	 */
	private String relatedGroupCode;

	/**
	 * 映射数组json，用于三方系统对接场景
	 */
	private String mappingArrayJson;
    /**
     * 描述
     */
    private String remark;
    /**
     * 排序,默认按该字段升序排序
     */
    private Integer seq;
	/**
	 * 父级id
	 */
	private Long parentId;


	/**
	 * 将 code 设置为null，如果为空
	 */
	public void setCodeNullIfEmpty() {
		if (StrUtil.isEmpty(code)) {
			code = null;
		}
	}

	/**
	 * 创建字典领域模型对象
	 * @return 字典领域模型对象，该对应所有属性为空，需要进行初始化操作
	 */
	public static Dict create(){
		return DomainFactory.create(Dict.class);
	}
}