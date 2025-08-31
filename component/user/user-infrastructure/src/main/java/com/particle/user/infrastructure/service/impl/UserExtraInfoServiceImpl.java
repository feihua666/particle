package com.particle.user.infrastructure.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.user.infrastructure.dos.UserExtraInfoDO;
import com.particle.user.infrastructure.mapper.UserExtraInfoMapper;
import com.particle.user.infrastructure.service.IUserExtraInfoService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 用户扩展信息 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-08-30 23:39:50
 */
@Component
public class UserExtraInfoServiceImpl extends IBaseServiceImpl<UserExtraInfoMapper, UserExtraInfoDO> implements IUserExtraInfoService {
	private IBaseQueryCommandMapStruct<UserExtraInfoDO> queryCommandMapStruct;

	@Override
	protected UserExtraInfoDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<UserExtraInfoDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(UserExtraInfoDO po) {
	    // 用户id 已存在不能添加
	    assertByColumn(po.getUserId(),UserExtraInfoDO::getUserId,false);

	}

	@Override
	protected void preUpdate(UserExtraInfoDO po) {
	    UserExtraInfoDO byId = null;
	    if (po.getUserId() != null) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果用户id有改动
	        if (!po.getUserId().equals(byId.getUserId())) {
	            // 用户id已存在不能修改
	            assertByColumn(po.getUserId(),UserExtraInfoDO::getUserId,false);
	        }
	    }

    
	}
}
