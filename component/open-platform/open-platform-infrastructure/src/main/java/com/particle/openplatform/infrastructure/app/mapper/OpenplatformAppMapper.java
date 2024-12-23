package com.particle.openplatform.infrastructure.app.mapper;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import com.particle.openplatform.infrastructure.app.dos.OpenplatformAppDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 开放平台应用 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:40:15
 */
@Mapper
public interface OpenplatformAppMapper extends IBaseMapper<OpenplatformAppDO> {

	/**
	 * 根据 appId 获取
	 * @param appId
	 * @return
	 */
	default OpenplatformAppDO getByAppId(String appId) {
		Assert.notNull(appId,"appId 不能为空");
		return selectOne(Wrappers.<OpenplatformAppDO>lambdaQuery().eq(OpenplatformAppDO::getAppId, appId));
	}

	/**
	 * 只获取id
	 * @param appId
	 * @return
	 */
	default OpenplatformAppDO getIdOnlyByAppId(String appId) {
		Assert.notNull(appId,"appId 不能为空");
		return selectOne(Wrappers.<OpenplatformAppDO>lambdaQuery().select(OpenplatformAppDO::getId).eq(OpenplatformAppDO::getAppId, appId));
	}
}
