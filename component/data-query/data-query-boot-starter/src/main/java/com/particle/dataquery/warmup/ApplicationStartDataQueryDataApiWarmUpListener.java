package com.particle.dataquery.warmup;

import com.particle.dataquery.client.dataapi.api.representation.IDataQueryDataApiRepresentationApplicationService;
import com.particle.global.bootstrap.boot.OnApplicationRunnerListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;

/**
 * <p>
 * 数据查询接口预热
 * </p>
 *
 * @author yangwei
 * @since 2024-01-29 15:12:52
 */
public class ApplicationStartDataQueryDataApiWarmUpListener implements OnApplicationRunnerListener {

	@Autowired
	private IDataQueryDataApiRepresentationApplicationService iDataQueryDataApiRepresentationApplicationService;


	@Override
	public void run(ApplicationArguments args) throws Exception {
		iDataQueryDataApiRepresentationApplicationService.warmUp();
	}
}
