package com.particle.area.domain;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;

/**
 * <p>
 * 区域领域模型
 * </p>
 *
 * @author yangwei
 * @since 2022-04-30 17:14
 */
@Data
@Entity
public class Area extends AggreateRoot {

	private AreaId id;

	/**
	 * 编码，全局唯一
	 */
	private String code;

	/**
	 * 区域名称
	 */
	private String name;

	/**
	 * 类型
	 */
	private AreaTypeEnum type;

	/**
	 * 经度，百度地图
	 */
	private String longitude;

	/**
	 * 纬度，百度地图
	 */
	private String latitude;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 排序,默认按该字段升序排序
	 */
	private Integer seq = 10;

	/**
	 * 父级id
	 */
	private Long parentId;

	/**
	 * 创建区域领域模型对象
	 * @return 区域领域模型对象，该对应所有属性为空，需要进行初始化操作
	 */
	public static Area create(){
		return DomainFactory.create(Area.class);
	}
}
