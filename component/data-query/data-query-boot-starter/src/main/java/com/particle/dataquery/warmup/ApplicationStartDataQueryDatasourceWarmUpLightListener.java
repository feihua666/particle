package com.particle.dataquery.warmup;

import com.particle.dataquery.client.datasource.api.representation.IDataQueryDatasourceApiRepresentationApplicationService;
import com.particle.dataquery.client.datasource.api.representation.IDataQueryDatasourceRepresentationApplicationService;
import com.particle.global.bootstrap.boot.OnApplicationRunnerListener;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;

/**
 * <p>
 * 数据源经量级预热
 * </p>
 *
 * @author yangwei
 * @since 2024-01-29 15:13:00
 */
@Setter
public class ApplicationStartDataQueryDatasourceWarmUpLightListener implements OnApplicationRunnerListener {

	@Autowired
	private IDataQueryDatasourceRepresentationApplicationService iDataQueryDatasourceRepresentationApplicationService;


	@Override
	public void run(ApplicationArguments args) throws Exception {
		iDataQueryDatasourceRepresentationApplicationService.warmUpForLight();
	}
}
