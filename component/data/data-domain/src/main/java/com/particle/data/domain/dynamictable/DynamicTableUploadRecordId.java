package com.particle.data.domain.dynamictable;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 动态数据表格上传记录 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-11-28 15:00:17
 */
public class DynamicTableUploadRecordId extends Id {

	public DynamicTableUploadRecordId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 动态数据表格上传记录 领域模型id
	 * @param id
	 * @return
	 */
	public static DynamicTableUploadRecordId of(Long id){
		return new DynamicTableUploadRecordId(id);
	}
}
