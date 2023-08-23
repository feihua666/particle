package com.particle.openplatform.infrastructure.openapirecord.service;

import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.openplatform.infrastructure.openapirecord.dos.OpenplatformOpenapiRecordParamDO;

/**
 * <p>
 * 开放平台开放接口调用记录参数 服务类
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:15:03
 */
public interface IOpenplatformOpenapiRecordParamService extends IBaseService<OpenplatformOpenapiRecordParamDO> {


	/**
	 * 根据 调用记录id查询
	 * @param openplatformOpenapiRecordId
	 * @return
	 */
	default OpenplatformOpenapiRecordParamDO getByOpenplatformOpenapiRecordId(Long openplatformOpenapiRecordId) {
		return getOneByColumn(openplatformOpenapiRecordId, OpenplatformOpenapiRecordParamDO::getOpenplatformOpenapiRecordId);
	}

}
