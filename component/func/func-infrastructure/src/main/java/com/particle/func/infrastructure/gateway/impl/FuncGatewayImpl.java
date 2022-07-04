package com.particle.func.infrastructure.gateway.impl;

import com.particle.func.domain.Func;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.func.domain.gateway.FuncGateway;
import org.springframework.stereotype.Component;

/**
 * @program: particle
 * @description:
 * @author: 许宝华
 * @create: 2022-07-02 12:57
 */

@Component
public class FuncGatewayImpl extends AbstractBaseGatewayImpl implements FuncGateway {

    @Override
    public boolean save(Func func) {
        return false;
    }
}
