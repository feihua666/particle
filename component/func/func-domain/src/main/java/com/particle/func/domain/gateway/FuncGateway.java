package com.particle.func.domain.gateway;

import com.particle.common.domain.gateway.IBaseGateway;
import com.particle.func.domain.Func;
import com.particle.func.domain.FuncId;

/**
 * <p>
 * 菜单功能 防腐层
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
public interface FuncGateway extends IBaseGateway<FuncId,Func> {


	/**
	 * 根据 id 获取 菜单功能 领域对象
	 * @param funcId
	 * @return 必须包括全部可用属性
	 */
	Func getById(FuncId funcId);

	/**
	 * 保存 菜单功能 领域对象
	 * 如果不存在应该新增,新增时需要将参数areaId设置成功，如果存在应该更新
	 * @param func  菜单功能 领域对象
	 * @return true=保存成功，false=保存失败，注意只要持久化了应该为 {@code true}
	 *         注意在有些 orm 或数据库下数据库中已经存在，更新时影响结果为0，但应该返回 {@code true}
	 */
	boolean save(Func func);


	/**
	 * 删除 菜单功能 领域对象
	 * @param funcId
	 * @return
	 */
	boolean delete(FuncId funcId);
}
