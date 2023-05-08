package com.particle.global.tool.id;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * <p>
 * 雪花算法id相关辅助工具
 * 支持系统变量配置和 vm options 配置
 * </p>
 *
 * @author yangwei
 * @since 2023-05-05 16:52
 */
@Data
@Slf4j
@Component
public class SnowflakeIdTool implements InitializingBean {

	private static final String workerIdKey = "particle.snowflake.worker-id";
	private static final String workerIdKeyWithDefault = "${" + workerIdKey + ":#{null}}";
	private static final String dataCenterIdKey = "particle.snowflake.data-center-id";
	private static final String dataCenterIdKeyWidthDefault = "${" + dataCenterIdKey + ":#{null}}";
	private static final String userRandomKey = "particle.snowflake.use-random";
	private static final String userRandomKeyWidthDefault = "${" + userRandomKey + ":false}";

	private static volatile Snowflake snowflake;

	@Value(userRandomKeyWidthDefault)
	private boolean useRandomTemp;
	/**
	 * 工作节点id
	 */
	@Value(workerIdKeyWithDefault)
	private Long workerIdTemp;
	/**
	 * 数据中心id
	 */
	@Value(dataCenterIdKeyWidthDefault)
	private Long dataCenterIdTemp;

	/**
	 * 工作节点id
	 */
	private static Long workerId;
	/**
	 * 数据中心id
	 */
	private static Long dataCenterId;

	static {
		// 从系统变量获取
		String propertyWorkerId = System.getProperty(workerIdKey);
		String propertyDataCenterId = System.getProperty(dataCenterIdKey);
		String propertyUseRandom = System.getProperty(userRandomKey);

		boolean useRandom = StrUtil.isNotEmpty(propertyUseRandom) && Boolean.valueOf(propertyUseRandom);

		Long workerId = Optional.ofNullable(StrUtil.emptyToNull(propertyWorkerId)).map(id -> Long.parseLong(propertyWorkerId)).orElse(null);
		Long dataCenterId = Optional.ofNullable(StrUtil.emptyToNull(propertyDataCenterId)).map(id -> Long.parseLong(propertyDataCenterId)).orElse(null);;
		init(useRandom,workerId,dataCenterId,"static");
	}

	public static Long getWorkerId() {
		return workerId;
	}

	public static Long getDataCenterId() {
		return dataCenterId;
	}

	/**
	 * 获取下一个id
	 * @return
	 */
	public static Long nextId() {
		return getSnowflake().nextId();
	}

	/**
	 * 单例获取雪花算法实例
	 * @return
	 */
	private static Snowflake getSnowflake() {
		if (snowflake == null) {
			synchronized (SnowflakeIdTool.class) {
				if (snowflake == null) {
					if (workerId != null && dataCenterId != null) {
						snowflake = new Snowflake(workerId,dataCenterId);
					}else {
						snowflake = new Snowflake();
					}
				}
			}
		}
		return snowflake;
	}
	/**
	 * 初始化
	 * @param useRandom
	 * @param workerId
	 * @param dataCenterId
	 */
	private static void init(boolean useRandom, Long workerId, Long dataCenterId,String logFlag) {
		if (useRandom) {
			SnowflakeIdTool.workerId = RandomUtil.randomLong(1, 31);
			SnowflakeIdTool.dataCenterId = RandomUtil.randomLong(1, 31);
			log.info("{} SnowflakeId inited workerId={},dataCenterId={},useRandom={}",logFlag,workerId,dataCenterId,useRandom);
			return;
		}
		if (workerId != null && dataCenterId != null) {
			SnowflakeIdTool.workerId = workerId;
			SnowflakeIdTool.dataCenterId = dataCenterId;
			log.info("{} SnowflakeId inited workerId={},dataCenterId={},useRandom={}",logFlag,workerId,dataCenterId,useRandom);
		}

	}

	@Override
	public void afterPropertiesSet() throws Exception {
		init(useRandomTemp,workerIdTemp,dataCenterIdTemp,"spring");
	}
}
