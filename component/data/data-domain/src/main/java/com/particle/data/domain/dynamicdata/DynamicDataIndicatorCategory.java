package com.particle.data.domain.dynamicdata;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 动态数据指标分类 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:58
 */
@Data
@Entity
public class DynamicDataIndicatorCategory extends AggreateRoot {

    private DynamicDataIndicatorCategoryId id;

    /**
    * 动态数据分类id
    */
    private Long dynamicDataCategoryId;

    /**
    * 动态数据指标分类名称
    */
    private String name;

    /**
    * 是否禁用，1=是，0=否
    */
    private Boolean isDisabled;

    /**
    * 动态数据指标分类类型，字典id
    */
    private Long typeDictId;

	/**
	 * 指标数量
	 */
	private Integer dynamicDataIndicatorNum;

	/**
	 * 数据数量
	 */
	private Integer dynamicDataIndicatorCategoryDataNum;

    /**
    * 备注信息
    */
    private String remark;

    public void initForAdd() {
        this.dynamicDataIndicatorNum = 0;
        this.dynamicDataIndicatorCategoryDataNum = 0;
    }


    /**
     * 创建动态数据指标分类领域模型对象
     * @return 动态数据指标分类领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DynamicDataIndicatorCategory create(){
        return DomainFactory.create(DynamicDataIndicatorCategory.class);
    }
}
