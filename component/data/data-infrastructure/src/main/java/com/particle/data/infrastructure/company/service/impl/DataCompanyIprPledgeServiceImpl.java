package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPledgeDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyIprPledgeMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPledgeService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业知识产权出质 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:21
 */
@Component
public class DataCompanyIprPledgeServiceImpl extends IBaseServiceImpl<DataCompanyIprPledgeMapper, DataCompanyIprPledgeDO> implements IDataCompanyIprPledgeService {
	private IBaseQueryCommandMapStruct<DataCompanyIprPledgeDO> queryCommandMapStruct;

	@Override
	protected DataCompanyIprPledgeDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyIprPledgeDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyIprPledgeDO po) {
	    // 知识产权登记证号 已存在不能添加
	    assertByColumn(po.getRegNo(),DataCompanyIprPledgeDO::getRegNo,false);

	}

	@Override
	protected void preUpdate(DataCompanyIprPledgeDO po) {
	    DataCompanyIprPledgeDO byId = null;
	    if (StrUtil.isNotEmpty(po.getRegNo())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果知识产权登记证号有改动
	        if (!po.getRegNo().equals(byId.getRegNo())) {
	            // 知识产权登记证号已存在不能修改
	            assertByColumn(po.getRegNo(),DataCompanyIprPledgeDO::getRegNo,false);
	        }
	    }

    
	}
}
