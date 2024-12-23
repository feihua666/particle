package com.particle.usagecount.infrastructure.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.usagecount.infrastructure.dos.UsageCountDefineDO;
import com.particle.usagecount.infrastructure.mapper.UsageCountDefineMapper;
import com.particle.usagecount.infrastructure.service.IUsageCountDefineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 使用次数定义 服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:14:08
 */
@Component
public class UsageCountDefineServiceImpl extends IBaseServiceImpl<UsageCountDefineMapper, UsageCountDefineDO> implements IUsageCountDefineService {
	private IBaseQueryCommandMapStruct<UsageCountDefineDO> queryCommandMapStruct;

	@Override
	protected UsageCountDefineDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<UsageCountDefineDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(UsageCountDefineDO po) {
		if (StrUtil.isNotEmpty(po.getCode())) {
			// 编码 已存在不能添加
			assertByColumn(po.getCode(),UsageCountDefineDO::getCode,false);
		}

		if (StrUtil.isNotEmpty(po.getUrlPattern())) {
			// 匹配的url地址 已存在不能添加
			assertByColumn(po.getUrlPattern(),UsageCountDefineDO::getUrlPattern,false);
		}
		if (po.getParentId() != null) {
			UsageCountDefineDO byParentId = getById(po.getParentId());
			Assert.isTrue(byParentId.getIsGroup(),"定义不能添加子节点");
		}
	}

	@Override
	protected void preUpdate(UsageCountDefineDO po) {
	    UsageCountDefineDO byId = null;
	    if (StrUtil.isNotEmpty(po.getCode())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果编码有改动
	        if (!po.getCode().equals(byId.getCode())) {
	            // 编码已存在不能修改
	            assertByColumn(po.getCode(),UsageCountDefineDO::getCode,false);
	        }
	    }

	    if (StrUtil.isNotEmpty(po.getUrlPattern())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果匹配的url地址有改动
	        if (!po.getUrlPattern().equals(byId.getUrlPattern())) {
	            // 匹配的url地址已存在不能修改
	            assertByColumn(po.getUrlPattern(),UsageCountDefineDO::getUrlPattern,false);
	        }
	    }
		if (po.getParentId() != null) {
			UsageCountDefineDO byParentId = getById(po.getParentId());
			Assert.isTrue(byParentId.getIsGroup(),"定义不能添加子节点");
		}

	}
}
