package com.particle.dict.infrastructure.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.dict.infrastructure.dos.DictDO;
import com.particle.dict.infrastructure.mapper.DictMapper;
import com.particle.dict.infrastructure.service.IDictService;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 字典 服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Component
public class DictServiceImpl extends IBaseServiceImpl<DictMapper, DictDO> implements IDictService {


	private IBaseQueryCommandMapStruct<DictDO> queryCommandMapStruct;

	@Override
	protected DictDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DictDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DictDO po) {
		// 如果为字典项，不需要添加编码code，如果字典项编辑code为空，不校验唯一
		if (StrUtil.isNotEmpty(po.getCode())) {
			// 编码已存在不能添加
			assertByColumn(po.getCode(),DictDO::getCode,false);
		}

		if (po.getParentId() != null) {
			DictDO byId = getById(po.getParentId());
			Assert.isTrue(byId.getIsGroup(),"字典项不能添加子节点");
		}
	}

	@Override
	protected void preUpdate(DictDO po) {
		if (po.getParentId() != null) {
			DictDO byId = getById(po.getParentId());
			Assert.isTrue(byId.getIsGroup(),"字典项不能添加子节点");
		}

		if (StrUtil.isNotEmpty(po.getCode())) {
			DictDO byId = getById(po.getId());
			// 如果编码有改动
			if (!StrUtil.equals(po.getCode(), byId.getCode())) {
				// 编码已存在不能修改
				assertByColumn(po.getCode(),DictDO::getCode,false);
			}
		}
	}
}
