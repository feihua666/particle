package com.particle.test.domain.gateway;

import com.particle.test.domain.UserSimple;
import com.particle.test.domain.UserSimpleId;
import com.particle.common.domain.gateway.IBaseGateway;

/**
 * <p>
 * 简单用户 防腐层
 * </p>
 *
 * @author yw
 * @since 2022-07-15
 */
public interface UserSimpleGateway extends IBaseGateway {


	/**
	 * 根据 id 获取 简单用户 领域对象
	 * @param userSimpleId
	 * @return 必须包括全部可用属性
	 */
	UserSimple getById(UserSimpleId userSimpleId);

	/**
	 * 保存 简单用户 领域对象
	 * 如果不存在应该新增,新增时需要将参数areaId设置成功，如果存在应该更新
	 * @param userSimple  简单用户 领域对象
	 * @return true=保存成功，false=保存失败，注意只要持久化了应该为 {@code true}
	 *         注意在有些 orm 或数据库下数据库中已经存在，更新时影响结果为0，但应该返回 {@code true}
	 */
	boolean save(UserSimple userSimple);


	/**
	 * 删除 简单用户 领域对象
	 * @param userSimpleId
	 * @return
	 */
	boolean delete(UserSimpleId userSimpleId);
}
