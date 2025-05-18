package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyJudgmentDocumentDO;
import com.particle.data.infrastructure.company.dto.DataCompanyJudgmentDocumentListPageByCompanyIdParam;
import com.particle.data.infrastructure.company.mapper.DataCompanyJudgmentDocumentMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyJudgmentDocumentService;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 企业裁判文书 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:21
 */
@Component
public class DataCompanyJudgmentDocumentServiceImpl extends IBaseServiceImpl<DataCompanyJudgmentDocumentMapper, DataCompanyJudgmentDocumentDO> implements IDataCompanyJudgmentDocumentService {
	private IBaseQueryCommandMapStruct<DataCompanyJudgmentDocumentDO> queryCommandMapStruct;

	private DataCompanyJudgmentDocumentMapper dataCompanyJudgmentDocumentMapper;

	@Override
	protected DataCompanyJudgmentDocumentDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}


	@Override
	protected void preAdd(DataCompanyJudgmentDocumentDO po) {
	    // 案号 已存在不能添加
	    assertByColumn(po.getCaseNo(),DataCompanyJudgmentDocumentDO::getCaseNo,false);

	}

	@Override
	protected void preUpdate(DataCompanyJudgmentDocumentDO po) {
	    DataCompanyJudgmentDocumentDO byId = null;
	    if (StrUtil.isNotEmpty(po.getCaseNo())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果案号有改动
	        if (!po.getCaseNo().equals(byId.getCaseNo())) {
	            // 案号已存在不能修改
	            assertByColumn(po.getCaseNo(),DataCompanyJudgmentDocumentDO::getCaseNo,false);
	        }
	    }


	}

	@Override
	public Page<DataCompanyJudgmentDocumentDO> listPage(DataCompanyJudgmentDocumentListPageByCompanyIdParam param, PageQueryCommand pageQueryForm) {
		Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
		return dataCompanyJudgmentDocumentMapper.listPage(pageQuery, param);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyJudgmentDocumentDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Autowired
	public void setDataCompanyJudgmentDocumentMapper(DataCompanyJudgmentDocumentMapper dataCompanyJudgmentDocumentMapper) {
		this.dataCompanyJudgmentDocumentMapper = dataCompanyJudgmentDocumentMapper;
	}
}
