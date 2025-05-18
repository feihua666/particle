package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentDO;
import com.particle.data.infrastructure.company.dto.DataCompanyIprPatentListPageByCompanyIdParam;
import com.particle.data.infrastructure.company.mapper.DataCompanyIprPatentMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentService;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 企业知识产权专利 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:39:48
 */
@Component
public class DataCompanyIprPatentServiceImpl extends IBaseServiceImpl<DataCompanyIprPatentMapper, DataCompanyIprPatentDO> implements IDataCompanyIprPatentService {
	private IBaseQueryCommandMapStruct<DataCompanyIprPatentDO> queryCommandMapStruct;

	private DataCompanyIprPatentMapper dataCompanyIprPatentMapper;

	@Override
	protected DataCompanyIprPatentDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}


	@Override
	protected void preAdd(DataCompanyIprPatentDO po) {
	    // 原始申请号 已存在不能添加
	    assertByColumn(po.getApplyNo(),DataCompanyIprPatentDO::getApplyNo,false);

	}

	@Override
	protected void preUpdate(DataCompanyIprPatentDO po) {
	    DataCompanyIprPatentDO byId = null;
	    if (StrUtil.isNotEmpty(po.getApplyNo())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果原始申请号有改动
	        if (!po.getApplyNo().equals(byId.getApplyNo())) {
	            // 原始申请号已存在不能修改
	            assertByColumn(po.getApplyNo(),DataCompanyIprPatentDO::getApplyNo,false);
	        }
	    }


	}

	@Override
	public Page<DataCompanyIprPatentDO> listPage(DataCompanyIprPatentListPageByCompanyIdParam param, PageQueryCommand pageQueryForm) {
		Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
		return dataCompanyIprPatentMapper.listPage(pageQuery, param);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyIprPatentDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}
	@Autowired
	public void setDataCompanyIprPatentMapper(DataCompanyIprPatentMapper dataCompanyIprPatentMapper) {
		this.dataCompanyIprPatentMapper = dataCompanyIprPatentMapper;
	}
}
