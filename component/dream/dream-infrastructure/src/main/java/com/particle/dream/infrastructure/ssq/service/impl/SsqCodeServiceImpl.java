package com.particle.dream.infrastructure.ssq.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.dream.infrastructure.ssq.dos.SsqCodeDO;
import com.particle.dream.infrastructure.ssq.mapper.SsqCodeMapper;
import com.particle.dream.infrastructure.ssq.service.ISsqCodeService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 双色球号码 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-05-16 09:47:01
 */
@Component
public class SsqCodeServiceImpl extends IBaseServiceImpl<SsqCodeMapper, SsqCodeDO> implements ISsqCodeService {
	private IBaseQueryCommandMapStruct<SsqCodeDO> queryCommandMapStruct;

	@Override
	protected SsqCodeDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<SsqCodeDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(SsqCodeDO po) {
	    // 序号 已存在不能添加
	    assertByColumn(po.getSeqNo(),SsqCodeDO::getSeqNo,false);

	}

	@Override
	protected void preUpdate(SsqCodeDO po) {
	    SsqCodeDO byId = null;
	    if (po.getSeqNo() != null) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果序号有改动
	        if (!po.getSeqNo().equals(byId.getSeqNo())) {
	            // 序号已存在不能修改
	            assertByColumn(po.getSeqNo(),SsqCodeDO::getSeqNo,false);
	        }
	    }

    
	}
}
