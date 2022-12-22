package com.particle.func.domain;

import cn.hutool.core.util.StrUtil;
import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;

/**
 * <p>
 * 功能组 领域模型
 * </p>
 *
 * @author yw
 * @since 2022-12-02
 */
@Data
@Entity
public class FuncGroup extends AggreateRoot {

	private FuncGroupId id;
    /**
     * 编码，模糊查询
     */
    private String code;
    /**
     * 名称，模糊查询
     */
    private String name;
    /**
     * 描述
     */
    private String remark;

	/**
	 * 将 code 设置为null，如果为空
	 */
	public void setCodeNullIfEmpty() {
		if (StrUtil.isEmpty(code)) {
			code = null;
		}
	}

	/**
	 * 创建功能组领域模型对象
	 * @return 功能组领域模型对象，该对应所有属性为空，需要进行初始化操作
	 */
	public static FuncGroup create(){
		return DomainFactory.create(FuncGroup.class);
	}
}
