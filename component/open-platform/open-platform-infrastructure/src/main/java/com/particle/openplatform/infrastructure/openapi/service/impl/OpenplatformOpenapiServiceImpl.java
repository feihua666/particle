package com.particle.openplatform.infrastructure.openapi.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.global.exception.Assert;
import com.particle.openplatform.infrastructure.app.dos.OpenplatformAppOpenapiDO;
import com.particle.openplatform.infrastructure.app.mapper.OpenplatformAppOpenapiMapper;
import com.particle.openplatform.infrastructure.openapi.dos.OpenplatformOpenapiDO;
import com.particle.openplatform.infrastructure.openapi.mapper.OpenplatformOpenapiMapper;
import com.particle.openplatform.infrastructure.openapi.service.IOpenplatformOpenapiService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


/**
 * <p>
 * 开放平台开放接口 服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-08-08 11:13:24
 */
@Component
public class OpenplatformOpenapiServiceImpl extends IBaseServiceImpl<OpenplatformOpenapiMapper, OpenplatformOpenapiDO> implements IOpenplatformOpenapiService {
	private IBaseQueryCommandMapStruct<OpenplatformOpenapiDO> queryCommandMapStruct;

	private OpenplatformAppOpenapiMapper openplatformAppOpenapiMapper;
	@Override
	protected OpenplatformOpenapiDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<OpenplatformOpenapiDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Autowired
	public void setOpenplatformAppOpenapiMapper(OpenplatformAppOpenapiMapper openplatformAppOpenapiMapper) {
		this.openplatformAppOpenapiMapper = openplatformAppOpenapiMapper;
	}

	@Override
	protected void preAdd(OpenplatformOpenapiDO po) {

		if (StrUtil.isNotEmpty(po.getCode())) {
			// 编码 已存在不能添加
			assertByColumn(po.getCode(),OpenplatformOpenapiDO::getCode,false);
		}


		if (StrUtil.isNotEmpty(po.getUrl())) {
			// 接口地址 已存在不能添加
			assertByColumn(po.getUrl(),OpenplatformOpenapiDO::getUrl,false);
		}

		if (po.getParentId() != null) {
			OpenplatformOpenapiDO byParentId = getById(po.getParentId());
			Assert.isTrue(byParentId.getIsGroup(),"接口不能添加子节点");
		}

	}

	@Override
	protected void preUpdate(OpenplatformOpenapiDO po) {


	    OpenplatformOpenapiDO byId = null;
	    if (StrUtil.isNotEmpty(po.getCode())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果编码有改动
	        if (!po.getCode().equals(byId.getCode())) {
	            // 编码已存在不能修改
	            assertByColumn(po.getCode(),OpenplatformOpenapiDO::getCode,false);
	        }
	    }


	    if (StrUtil.isNotEmpty(po.getUrl())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果接口地址有改动
	        if (!po.getUrl().equals(byId.getUrl())) {
	            // 接口地址已存在不能修改
	            assertByColumn(po.getUrl(),OpenplatformOpenapiDO::getUrl,false);
	        }
	    }


		if (po.getParentId() != null) {
			OpenplatformOpenapiDO byParentId = getById(po.getParentId());
			Assert.isTrue(byParentId.getIsGroup(),"接口不能添加子节点");
		}
	}

	@Override
	public List<String> getPermissionsByAppId(Long openPlatformAppId) {
		Assert.notNull(openPlatformAppId,"openPlatformAppId 不能为空");
		List<OpenplatformAppOpenapiDO> openplatformAppOpenapiDOS = openplatformAppOpenapiMapper.selectList(
				Wrappers.<OpenplatformAppOpenapiDO>lambdaQuery()
						.select(OpenplatformAppOpenapiDO::getOpenplatformOpenapiId)
				.eq(OpenplatformAppOpenapiDO::getOpenplatformAppId, openPlatformAppId));
		List<Long> collect = openplatformAppOpenapiDOS.stream().map(OpenplatformAppOpenapiDO::getOpenplatformOpenapiId).collect(Collectors.toList());
		if (CollectionUtil.isEmpty(collect)) {
			return Collections.emptyList();
		}

		List<OpenplatformOpenapiDO> list = list(Wrappers.<OpenplatformOpenapiDO>lambdaQuery().select(OpenplatformOpenapiDO::getPermissions).in(OpenplatformOpenapiDO::getId, collect));
		return list.stream().map(OpenplatformOpenapiDO::getPermissions).collect(Collectors.toList());
	}
}
