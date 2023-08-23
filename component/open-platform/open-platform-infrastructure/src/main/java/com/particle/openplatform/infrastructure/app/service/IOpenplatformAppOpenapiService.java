package com.particle.openplatform.infrastructure.app.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.openplatform.infrastructure.app.dos.OpenplatformAppOpenapiDO;
import com.particle.global.mybatis.plus.crud.IBaseService;

/**
 * <p>
 * 开放平台应用与开放接口配置 服务类
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:49:06
 */
public interface IOpenplatformAppOpenapiService extends IBaseService<OpenplatformAppOpenapiDO> {


	/**
	 * 根据 appId 和 开放接口id 获取
	 * @param appId
	 * @param openplatformOpenapiId
	 * @return
	 */
	OpenplatformAppOpenapiDO getByAppIdAndOpenplatformOpenapiId(String appId, Long openplatformOpenapiId);


	/**
	 * 根据 openplatformAppId 和 开放接口id 获取
	 * @param openplatformAppId
	 * @param openplatformOpenapiId
	 * @return
	 */
	default OpenplatformAppOpenapiDO getByOpenplatformAppIdAndOpenplatformOpenapiId(Long openplatformAppId, Long openplatformOpenapiId) {
		return getOne(Wrappers.<OpenplatformAppOpenapiDO>lambdaQuery()
				.eq(OpenplatformAppOpenapiDO::getOpenplatformAppId, openplatformAppId)
				.eq(OpenplatformAppOpenapiDO::getOpenplatformOpenapiId, openplatformOpenapiId));
	}

}
