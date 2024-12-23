package com.particle.func.infrastructure.application.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.func.infrastructure.application.dos.FuncApplicationDO;
import com.particle.func.infrastructure.application.mapper.FuncApplicationMapper;
import com.particle.func.infrastructure.application.service.IFuncApplicationService;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 功能应用 服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-04-17 10:12:23
 */
@Component("FuncAppServiceImpl")
public class FuncApplicationServiceImpl extends IBaseServiceImpl<FuncApplicationMapper, FuncApplicationDO> implements IFuncApplicationService {
	private IBaseQueryCommandMapStruct<FuncApplicationDO> queryCommandMapStruct;

	@Override
	protected FuncApplicationDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<FuncApplicationDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}


	@Override
	protected void preAdd(FuncApplicationDO po) {
		// 如果为应用，不需要添加编码code，如果应用编辑code为空，不校验唯一
		if (StrUtil.isNotEmpty(po.getCode())) {
			// 编码已存在不能添加
			assertByColumn(po.getCode(),FuncApplicationDO::getCode,false);
		}

		if (po.getParentId() != null) {
			FuncApplicationDO byParentId = getById(po.getParentId());
			Assert.isTrue(byParentId.getIsGroup(),"应用不能添加子节点");
		}
	}

	@Override
	protected void preUpdate(FuncApplicationDO po) {


		if (StrUtil.isNotEmpty(po.getCode())) {
			FuncApplicationDO byId = getById(po.getId());
			// 如果编码有改动
			if (!StrUtil.equals(po.getCode(), byId.getCode())) {
				// 编码已存在不能修改
				assertByColumn(po.getCode(),FuncApplicationDO::getCode,false);
			}
		}
		if (po.getParentId() != null) {
			FuncApplicationDO byId = getById(po.getParentId());
			Assert.isTrue(byId.getIsGroup(),"应用不能添加子节点");
		}
	}
}
