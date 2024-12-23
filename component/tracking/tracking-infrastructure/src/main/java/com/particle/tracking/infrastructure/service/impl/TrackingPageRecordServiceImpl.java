package com.particle.tracking.infrastructure.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.tracking.infrastructure.dos.TrackingPageRecordDO;
import com.particle.tracking.infrastructure.mapper.TrackingPageRecordMapper;
import com.particle.tracking.infrastructure.service.ITrackingPageRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 页面埋点记录 服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:44:01
 */
@Component
public class TrackingPageRecordServiceImpl extends IBaseServiceImpl<TrackingPageRecordMapper, TrackingPageRecordDO> implements ITrackingPageRecordService {
	private IBaseQueryCommandMapStruct<TrackingPageRecordDO> queryCommandMapStruct;

	@Override
	protected TrackingPageRecordDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<TrackingPageRecordDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}


	@Override
	protected void preAdd(TrackingPageRecordDO po) {
		// 编码已存在不能添加
		assertByColumn(po.getTrackingPageCode(),TrackingPageRecordDO::getTrackingPageCode,false);
	}

	@Override
	protected void preUpdate(TrackingPageRecordDO po) {
		if (StrUtil.isNotEmpty(po.getTrackingPageCode())) {
			TrackingPageRecordDO byId = getById(po.getId());
			// 如果编码有改动
			if (!StrUtil.equals(po.getTrackingPageCode(), byId.getTrackingPageCode())) {
				// 编码已存在不能修改
				assertByColumn(po.getTrackingPageCode(),TrackingPageRecordDO::getTrackingPageCode,false);
			}
		}
	}
}
