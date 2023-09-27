package com.particle.report.infrastructure.template.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.report.infrastructure.template.dos.ReportSegmentTemplateDO;
import com.particle.report.infrastructure.template.mapper.ReportSegmentTemplateMapper;
import com.particle.report.infrastructure.template.service.IReportSegmentTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 报告片段模板 服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-09-05 17:49:31
 */
@Component
public class ReportSegmentTemplateServiceImpl extends IBaseServiceImpl<ReportSegmentTemplateMapper, ReportSegmentTemplateDO> implements IReportSegmentTemplateService {
	private IBaseQueryCommandMapStruct<ReportSegmentTemplateDO> queryCommandMapStruct;

	@Override
	protected ReportSegmentTemplateDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<ReportSegmentTemplateDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(ReportSegmentTemplateDO po) {
		// 编码已存在不能添加
		if (StrUtil.isNotEmpty(po.getCode())) {
			assertByColumn(po.getCode(), ReportSegmentTemplateDO::getCode, false);
		}
	}

	@Override
	protected void preUpdate(ReportSegmentTemplateDO po) {
		if (StrUtil.isNotEmpty(po.getCode())) {
			ReportSegmentTemplateDO byId = getById(po.getId());
			// 如果编码有改动
			if (!StrUtil.equals(po.getCode(), byId.getCode())) {
				// 编码已存在不能修改
				assertByColumn(po.getCode(),ReportSegmentTemplateDO::getCode,false);
			}
		}
	}
}
