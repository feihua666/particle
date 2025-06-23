package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkDO;
import com.particle.data.infrastructure.company.dto.DataCompanyIprTrademarkListPageByCompanyIdParam;
import com.particle.data.infrastructure.company.mapper.DataCompanyIprTrademarkMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkService;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 企业知识产权商标 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:14:45
 */
@Component
public class DataCompanyIprTrademarkServiceImpl extends IBaseServiceImpl<DataCompanyIprTrademarkMapper, DataCompanyIprTrademarkDO> implements IDataCompanyIprTrademarkService {
	private IBaseQueryCommandMapStruct<DataCompanyIprTrademarkDO> queryCommandMapStruct;

	private DataCompanyIprTrademarkMapper dataCompanyIprTrademarkMapper;

	@Override
	protected DataCompanyIprTrademarkDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyIprTrademarkDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyIprTrademarkDO po) {
	    // 申请号 已存在不能添加
	    assertByColumn(po.getApplyNo(),DataCompanyIprTrademarkDO::getApplyNo,false);

	}

	@Override
	protected void preUpdate(DataCompanyIprTrademarkDO po) {
	    DataCompanyIprTrademarkDO byId = null;
	    if (StrUtil.isNotEmpty(po.getApplyNo())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果申请号有改动
	        if (!po.getApplyNo().equals(byId.getApplyNo())) {
	            // 申请号已存在不能修改
	            assertByColumn(po.getApplyNo(),DataCompanyIprTrademarkDO::getApplyNo,false);
	        }
	    }


	}

	@Override
	public Page<DataCompanyIprTrademarkDO> listPage(DataCompanyIprTrademarkListPageByCompanyIdParam param, PageQueryCommand pageQueryForm) {
		Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
		return dataCompanyIprTrademarkMapper.listPage(pageQuery,param);
	}

	@Autowired
	public void setDataCompanyIprTrademarkMapper(DataCompanyIprTrademarkMapper dataCompanyIprTrademarkMapper) {
		this.dataCompanyIprTrademarkMapper = dataCompanyIprTrademarkMapper;
	}
}
