package com.particle.tracking.infrastructure.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.tracking.infrastructure.dos.TrackingPageDO;
import com.particle.tracking.infrastructure.mapper.TrackingPageMapper;
import com.particle.tracking.infrastructure.service.ITrackingPageService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 埋点页面 服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:39:06
 */
@Component
public class TrackingPageServiceImpl extends IBaseServiceImpl<TrackingPageMapper, TrackingPageDO> implements ITrackingPageService {
	private IBaseQueryCommandMapStruct<TrackingPageDO> queryCommandMapStruct;

	@Override
	protected TrackingPageDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<TrackingPageDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(TrackingPageDO po) {
	    // 页面编码 已存在不能添加
	    assertByColumn(po.getCode(),TrackingPageDO::getCode,false);

	}

	@Override
	protected void preUpdate(TrackingPageDO po) {

	    TrackingPageDO byId = null;
	    if (StrUtil.isNotEmpty(po.getCode())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果页面编码有改动
	        if (!po.getCode().equals(byId.getCode())) {
	            // 页面编码已存在不能修改
	            assertByColumn(po.getCode(),TrackingPageDO::getCode,false);
	        }
	    }

    
	}
}
