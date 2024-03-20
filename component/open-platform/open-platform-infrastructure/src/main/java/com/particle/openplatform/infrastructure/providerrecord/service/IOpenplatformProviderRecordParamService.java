package com.particle.openplatform.infrastructure.providerrecord.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.openplatform.infrastructure.providerrecord.dos.OpenplatformProviderRecordParamDO;
import com.particle.global.mybatis.plus.crud.IBaseService;

/**
 * <p>
 * 开放平台开放接口供应商调用记录参数 服务类
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:18:54
 */
public interface IOpenplatformProviderRecordParamService extends IBaseService<OpenplatformProviderRecordParamDO> {


	/**
	 * 根据供应商调用记录查询
	 * @param openplatformProviderRecordId
	 * @return
	 */
	default OpenplatformProviderRecordParamDO getByOpenplatformProviderRecordId(Long openplatformProviderRecordId) {
		return getOne(Wrappers.<OpenplatformProviderRecordParamDO>lambdaQuery().eq(OpenplatformProviderRecordParamDO::getOpenplatformProviderRecordId,openplatformProviderRecordId));
	}

}
