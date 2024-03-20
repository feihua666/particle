package com.particle.dataquery.infrastructure.dataapi.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.dataquery.infrastructure.dataapi.dos.DataQueryDataApiDO;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

/**
 * <p>
 * 数据查询数据接口 服务类
 * </p>
 *
 * @author yw
 * @since 2023-03-21 13:11:05
 */
public interface IDataQueryDataApiService extends IBaseService<DataQueryDataApiDO> {

	/**
	 * 根据url查询，url是唯一键
	 * @param url
	 * @return
	 */
	default DataQueryDataApiDO getByUrl(String url) {
		Assert.notNull(url,"url 不能为空");
		return getOne(Wrappers.<DataQueryDataApiDO>lambdaQuery().eq(DataQueryDataApiDO::getUrl,url));
	}


}
