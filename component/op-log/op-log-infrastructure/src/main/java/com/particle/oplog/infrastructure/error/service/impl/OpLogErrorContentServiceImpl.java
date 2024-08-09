package com.particle.oplog.infrastructure.error.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.oplog.infrastructure.error.dos.OpLogErrorContentDO;
import com.particle.oplog.infrastructure.error.mapper.OpLogErrorContentMapper;
import com.particle.oplog.infrastructure.error.service.IOpLogErrorContentService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 操作异常日志内容 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-08-09 14:19:59
 */
@Component
public class OpLogErrorContentServiceImpl extends IBaseServiceImpl<OpLogErrorContentMapper, OpLogErrorContentDO> implements IOpLogErrorContentService {
	private IBaseQueryCommandMapStruct<OpLogErrorContentDO> queryCommandMapStruct;

	@Override
	protected OpLogErrorContentDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<OpLogErrorContentDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(OpLogErrorContentDO po) {
	    // 异常日志id 已存在不能添加
	    assertByColumn(po.getOpLogErrorId(),OpLogErrorContentDO::getOpLogErrorId,false);

	}

	@Override
	protected void preUpdate(OpLogErrorContentDO po) {
	    OpLogErrorContentDO byId = null;
	    if (po.getOpLogErrorId() != null) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果异常日志id有改动
	        if (!po.getOpLogErrorId().equals(byId.getOpLogErrorId())) {
	            // 异常日志id已存在不能修改
	            assertByColumn(po.getOpLogErrorId(),OpLogErrorContentDO::getOpLogErrorId,false);
	        }
	    }

    
	}
}
