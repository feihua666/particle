package com.particle.dataquery.warmup;

import com.particle.dataquery.client.datasource.api.representation.IDataQueryDatasourceApiRepresentationApplicationService;
import com.particle.global.bootstrap.boot.OnApplicationRunnerListener;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;

/**
 * <p>
 * 数据源接口经量级预热
 * </p>
 *
 * @author yangwei
 * @since 2024-01-29 15:13:00
 */
@Setter
public class ApplicationStartDataQueryDatasourceApiWarmUpLightListener implements OnApplicationRunnerListener {

	@Autowired
	private IDataQueryDatasourceApiRepresentationApplicationService iDataQueryDatasourceApiRepresentationApplicationService;


	@Override
	public void run(ApplicationArguments args) throws Exception {
		iDataQueryDatasourceApiRepresentationApplicationService.warmUpForLight();
	}
}
