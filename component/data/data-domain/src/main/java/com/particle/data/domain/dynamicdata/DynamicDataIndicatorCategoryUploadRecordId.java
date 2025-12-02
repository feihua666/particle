package com.particle.data.domain.dynamicdata;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 动态数据指标分类上传记录 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-11-28 15:00:59
 */
public class DynamicDataIndicatorCategoryUploadRecordId extends Id {

	public DynamicDataIndicatorCategoryUploadRecordId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 动态数据指标分类上传记录 领域模型id
	 * @param id
	 * @return
	 */
	public static DynamicDataIndicatorCategoryUploadRecordId of(Long id){
		return new DynamicDataIndicatorCategoryUploadRecordId(id);
	}
}
