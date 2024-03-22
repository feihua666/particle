package com.particle.dict.infrastructure.service;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.dict.infrastructure.dos.DictDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 字典 服务类
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
public interface IDictService extends IBaseService<DictDO> {


	/**
	 * 根据编码查询
	 * @param code
	 * @return
	 */
	@Cacheable(cacheNames = {"DictServiceImplCache_getByCode"})
	default DictDO getByCode(String code) {
		Assert.hasText(code,"code 不能为空");
		return getOne(Wrappers.<DictDO>lambdaQuery().eq(DictDO::getCode, code));
	}
	/**
	 * 根据id查询子一级
	 * @param groupId
	 * @return
	 */
	@Cacheable(cacheNames = {"DictServiceImplCache_getItemsByGroupId"})
	default List<DictDO> getItemsByGroupId(Long groupId){
		DictDO byCode = getById(groupId);
		if (byCode == null) {
			return Collections.EMPTY_LIST;
		}
		return getChildren(byCode.getId());
	}
	/**
	 * 根据编码查询子一级
	 * @param groupCode
	 * @return
	 */
	@Cacheable(cacheNames = {"DictServiceImplCache_getItemsByGroupCode"})
	default List<DictDO> getItemsByGroupCode(String groupCode){
		DictDO byCode = getByCode(groupCode);
		if (byCode == null) {
			return Collections.EMPTY_LIST;
		}
		return getChildren(byCode.getId());
	}

	/**
	 * 根据编码查询子一级匹配的值
	 * 注意：如果值存在多个，将会返回第一个
	 * @param groupCode
	 * @param value
	 * @return
	 */
	@Cacheable(cacheNames = {"DictServiceImplCache_getByGroupCodeAndItemValue"})
	default DictDO getByGroupCodeAndItemValue(String groupCode, String value){
		Assert.hasText(value,"value 不能为空");
		List<DictDO> items = getItemsByGroupCode(groupCode);
		return items.stream().filter(item -> Objects.equals(value, item.getValue())).findFirst().orElse(null);
	}
}
