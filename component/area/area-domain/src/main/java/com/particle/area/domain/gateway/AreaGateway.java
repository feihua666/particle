package com.particle.area.domain.gateway;

import com.particle.area.domain.Area;
import com.particle.area.domain.AreaId;
import com.particle.common.domain.gateway.IBaseGateway;

/**
 * <p>
 * 区域 防腐层
 * </p>
 *
 * @author yw
 * @since 2022-07-18
 */
public interface AreaGateway extends IBaseGateway {


	/**
	 * 根据 id 获取 区域 领域对象
	 * @param areaId
	 * @return 必须包括全部可用属性
	 */
	Area getById(AreaId areaId);

	/**
	 * 保存 区域 领域对象
	 * 如果不存在应该新增,新增时需要将参数areaId设置成功，如果存在应该更新
	 * @param area  区域 领域对象
	 * @return true=保存成功，false=保存失败，注意只要持久化了应该为 {@code true}
	 *         注意在有些 orm 或数据库下数据库中已经存在，更新时影响结果为0，但应该返回 {@code true}
	 */
	boolean save(Area area);


	/**
	 * 删除 区域 领域对象
	 * @param areaId
	 * @return
	 */
	boolean delete(AreaId areaId);
}
