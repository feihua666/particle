package com.particle.report.infrastructure.reportapi.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.report.infrastructure.reportapi.dos.ReportReportApiDO;
import com.particle.report.infrastructure.reportapi.mapper.ReportReportApiMapper;
import com.particle.report.infrastructure.reportapi.service.IReportReportApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 报告接口 服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-09-06 16:28:52
 */
@Component
public class ReportReportApiServiceImpl extends IBaseServiceImpl<ReportReportApiMapper, ReportReportApiDO> implements IReportReportApiService {
	private IBaseQueryCommandMapStruct<ReportReportApiDO> queryCommandMapStruct;

	@Override
	protected ReportReportApiDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<ReportReportApiDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(ReportReportApiDO po) {
	    // 编码 已存在不能添加
		if (StrUtil.isNotEmpty(po.getCode())) {
			assertByColumn(po.getCode(),ReportReportApiDO::getCode,false);
		}

	    // 接口地址 已存在不能添加
		if (StrUtil.isNotEmpty(po.getUrl())) {
			assertByColumn(po.getUrl(),ReportReportApiDO::getUrl,false);
		}

		if (po.getParentId() != null) {
			ReportReportApiDO byParentId = getById(po.getParentId());
			Assert.isTrue(byParentId.getIsGroup(),"接口不能添加子节点");
		}
	}

	@Override
	protected void preUpdate(ReportReportApiDO po) {
	    ReportReportApiDO byId = null;
	    if (StrUtil.isNotEmpty(po.getCode())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果编码有改动
	        if (!po.getCode().equals(byId.getCode())) {
	            // 编码已存在不能修改
	            assertByColumn(po.getCode(),ReportReportApiDO::getCode,false);
	        }
	    }

	    if (StrUtil.isNotEmpty(po.getUrl())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果接口地址有改动
	        if (!po.getUrl().equals(byId.getUrl())) {
	            // 接口地址已存在不能修改
	            assertByColumn(po.getUrl(),ReportReportApiDO::getUrl,false);
	        }
	    }

		if (po.getParentId() != null) {
			ReportReportApiDO byParentIdId = getById(po.getParentId());
			Assert.isTrue(byParentIdId.getIsGroup(),"接口不能添加子节点");
		}
	}
}
