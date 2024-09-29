package com.particle.openplatform.infrastructure.openapi.service.impl;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

	private static String openplatformAppOpenapiDOTableNameCache = null;
	private static String openplatformOpenapiDOTableNameCache = null;
	private static String getQueryWrapperopenplatformAppIdExistSqlCache = null;
	
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
	public QueryWrapper<OpenplatformOpenapiDO> getQueryWrapper(QueryCommand queryForm) {
		QueryWrapper<OpenplatformOpenapiDO> queryWrapper = super.getQueryWrapper(queryForm);

		if (StrUtil.equalsAny(queryForm.getClass().getName(),
				"com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiQueryListCommand")) {
			Object openplatformAppId = ReflectUtil.getFieldValue(queryForm, "openplatformAppId");
			//addExistSqlIfopenplatformAppIdNotNull(queryWrapper, openplatformAppId);
			if (openplatformAppId != null) {
				addInIfOpenplatformAppIdNotNull(queryWrapper, openplatformAppId);
			}

		}


		return queryWrapper;
	}

	/**
	 * exist sql形式
	 * @param queryWrapper
	 * @param openplatformAppId
	 */
	public void addExistSqlIfopenplatformAppIdNotNull(QueryWrapper<OpenplatformOpenapiDO> queryWrapper, Object openplatformAppId) {
		if (openplatformAppOpenapiDOTableNameCache == null) {
			TableName annotation = AnnotationUtil.getAnnotation(OpenplatformAppOpenapiDO.class, TableName.class);
			openplatformAppOpenapiDOTableNameCache = annotation.value();
		}
		if (openplatformOpenapiDOTableNameCache == null) {
			TableName annotation = AnnotationUtil.getAnnotation(OpenplatformOpenapiDO.class, TableName.class);
			openplatformOpenapiDOTableNameCache = annotation.value();
		}
		if (getQueryWrapperopenplatformAppIdExistSqlCache == null) {
			// select id from component_openplatform_app_openapi where component_openplatform_app_openapi.openplatform_openapi_id = component_openplatform_openapi.id and component_openplatform_app_openapi.openplatform_app_id = {openplatformAppId}
			getQueryWrapperopenplatformAppIdExistSqlCache = "select id from " + openplatformAppOpenapiDOTableNameCache + " where"
					+ openplatformAppOpenapiDOTableNameCache + ".openplatform_openapi_id = " + openplatformOpenapiDOTableNameCache + ".id"
					+ openplatformAppOpenapiDOTableNameCache + ".openplatform_app_id = ";

		}
		String finalSql = openplatformAppOpenapiDOTableNameCache + openplatformAppId;

		queryWrapper.exists(openplatformAppId != null, finalSql);

	}

	/**
	 * in 形式
	 * @param queryWrapper
	 * @param openplatformAppId
	 */
	public void addInIfOpenplatformAppIdNotNull(QueryWrapper<OpenplatformOpenapiDO> queryWrapper, Object openplatformAppId){
		if (openplatformAppId != null) {
			List<OpenplatformAppOpenapiDO> openplatformAppOpenapiDOS = openplatformAppOpenapiMapper.selectList(Wrappers.<OpenplatformAppOpenapiDO>lambdaQuery().eq(OpenplatformAppOpenapiDO::getOpenplatformAppId, openplatformAppId));
			List<Long> collect = openplatformAppOpenapiDOS.stream().map(OpenplatformAppOpenapiDO::getOpenplatformOpenapiId).collect(Collectors.toList());
			if (!collect.isEmpty()) {
				// 为空就是不存在，不存在，得加一个不存在的条件
				queryWrapper.apply("false");
			}else {
				queryWrapper.in(OpenplatformOpenapiDO.COLUMN_ID,collect);
			}
		}
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
