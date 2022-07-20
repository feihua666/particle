package com.particle.func.domain.gateway;

import com.particle.func.domain.FuncGroup;
import com.particle.func.domain.FuncGroupId;
import com.particle.common.domain.gateway.IBaseGateway;

/**
 * <p>
 * 功能组 防腐层
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
public interface FuncGroupGateway extends IBaseGateway {


	/**
	 * 根据 id 获取 功能组 领域对象
	 * @param funcGroupId
	 * @return 必须包括全部可用属性
	 */
	FuncGroup getById(FuncGroupId funcGroupId);

	/**
	 * 保存 功能组 领域对象
	 * 如果不存在应该新增,新增时需要将参数areaId设置成功，如果存在应该更新
	 * @param funcGroup  功能组 领域对象
	 * @return true=保存成功，false=保存失败，注意只要持久化了应该为 {@code true}
	 *         注意在有些 orm 或数据库下数据库中已经存在，更新时影响结果为0，但应该返回 {@code true}
	 */
	boolean save(FuncGroup funcGroup);


	/**
	 * 删除 功能组 领域对象
	 * @param funcGroupId
	 * @return
	 */
	boolean delete(FuncGroupId funcGroupId);
}
