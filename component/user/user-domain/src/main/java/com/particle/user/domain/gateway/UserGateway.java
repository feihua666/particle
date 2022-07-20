package com.particle.user.domain.gateway;

import com.particle.user.domain.User;
import com.particle.user.domain.UserId;
import com.particle.common.domain.gateway.IBaseGateway;

/**
 * <p>
 * 后台管理用户 防腐层
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
public interface UserGateway extends IBaseGateway {


	/**
	 * 根据 id 获取 后台管理用户 领域对象
	 * @param userId
	 * @return 必须包括全部可用属性
	 */
	User getById(UserId userId);

	/**
	 * 保存 后台管理用户 领域对象
	 * 如果不存在应该新增,新增时需要将参数areaId设置成功，如果存在应该更新
	 * @param user  后台管理用户 领域对象
	 * @return true=保存成功，false=保存失败，注意只要持久化了应该为 {@code true}
	 *         注意在有些 orm 或数据库下数据库中已经存在，更新时影响结果为0，但应该返回 {@code true}
	 */
	boolean save(User user);


	/**
	 * 删除 后台管理用户 领域对象
	 * @param userId
	 * @return
	 */
	boolean delete(UserId userId);
}
