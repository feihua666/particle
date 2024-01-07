package com.particle.global.big.datasource.bigdatasource.trans;

import com.particle.global.big.datasource.bigdatasource.api.config.BigDatasourceApiTransConfig;
import com.particle.global.big.datasource.bigdatasource.api.config.DictGroup;

import java.util.List;

/**
 * <p>
 * 大数据源接口翻译支持服务
 * </p>
 *
 * @author yangwei
 * @since 2023/12/24 12:49
 */
public interface IBigDatasourceApiTransSupportService {

    /**
     * 数据翻译
     * @param result 要翻译的对象
     * @param bigDatasourceApiTransConfig 提示翻译器翻译的配置
     * @param dictGroups 提示翻译器翻译的数据
     */
    public void trans(Object result, BigDatasourceApiTransConfig bigDatasourceApiTransConfig, List<DictGroup> dictGroups);
}
