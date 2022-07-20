package com.particle.dict.domain.gateway;

import com.particle.dict.domain.Dict;
import com.particle.dict.domain.DictId;
import com.particle.common.domain.gateway.IBaseGateway;

/**
 * <p>
 * 字典 防腐层
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
public interface DictGateway extends IBaseGateway {


	/**
	 * 根据 id 获取 字典 领域对象
	 * @param dictId
	 * @return 必须包括全部可用属性
	 */
	Dict getById(DictId dictId);

	/**
	 * 保存 字典 领域对象
	 * 如果不存在应该新增,新增时需要将参数areaId设置成功，如果存在应该更新
	 * @param dict  字典 领域对象
	 * @return true=保存成功，false=保存失败，注意只要持久化了应该为 {@code true}
	 *         注意在有些 orm 或数据库下数据库中已经存在，更新时影响结果为0，但应该返回 {@code true}
	 */
	boolean save(Dict dict);


	/**
	 * 删除 字典 领域对象
	 * @param dictId
	 * @return
	 */
	boolean delete(DictId dictId);
}
