package com.particle.dataquery.warmup;

import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.dataquery.client.dataapi.api.representation.IDataQueryDataApiRepresentationApplicationService;
import com.particle.global.bootstrap.boot.OnApplicationRunnerListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;

/**
 * <p>
 * 应用启动关闭通知监听
 * </p>
 *
 * @author yangwei
 * @since 2021-08-31 10:07
 */
public class ApplicationStartDataQueryDataApiWarmUpListener implements OnApplicationRunnerListener {

	@Autowired
	private IDataQueryDataApiRepresentationApplicationService iDataQueryDataApiRepresentationApplicationService;


	@Override
	public void run(ApplicationArguments args) throws Exception {
		iDataQueryDataApiRepresentationApplicationService.warmUp();
	}
}
