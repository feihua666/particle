package com.particle.dict.domain.gateway;

import com.particle.common.domain.gateway.IBaseGateway;
import com.particle.dict.domain.Dict;
import com.particle.dict.domain.DictId;

/**
 * <p>
 * 字典 防腐层
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
public interface DictGateway extends IBaseGateway<DictId,Dict> {
}
