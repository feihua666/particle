package com.particle.lowcode.infrastructure.generator.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.lowcode.infrastructure.generator.dos.LowcodeSegmentTemplateDO;
import com.particle.lowcode.infrastructure.generator.mapper.LowcodeSegmentTemplateMapper;
import com.particle.lowcode.infrastructure.generator.service.ILowcodeSegmentTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 低代码片段模板 服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-01-06
 */
@Component
public class LowcodeSegmentTemplateServiceImpl extends IBaseServiceImpl<LowcodeSegmentTemplateMapper, LowcodeSegmentTemplateDO> implements ILowcodeSegmentTemplateService {
	private IBaseQueryCommandMapStruct<LowcodeSegmentTemplateDO> queryCommandMapStruct;

	@Override
	protected LowcodeSegmentTemplateDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<LowcodeSegmentTemplateDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}
	@Override
	protected void preAdd(LowcodeSegmentTemplateDO po) {
		// 编码已存在不能添加
		if (StrUtil.isNotEmpty(po.getCode())) {
			assertByColumn(po.getCode(), LowcodeSegmentTemplateDO::getCode, false);
		}
	}

	@Override
	protected void preUpdate(LowcodeSegmentTemplateDO po) {
		if (StrUtil.isNotEmpty(po.getCode())) {
			LowcodeSegmentTemplateDO byId = getById(po.getId());
			// 如果编码有改动
			if (!StrUtil.equals(po.getCode(), byId.getCode())) {
				// 编码已存在不能修改
				assertByColumn(po.getCode(),LowcodeSegmentTemplateDO::getCode,false);
			}
		}
	}
}
