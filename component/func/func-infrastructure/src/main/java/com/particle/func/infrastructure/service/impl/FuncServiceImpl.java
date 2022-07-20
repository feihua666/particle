package com.particle.func.infrastructure.service.impl;

import com.particle.func.infrastructure.dos.FuncDO;
import com.particle.func.infrastructure.mapper.FuncMapper;
import com.particle.func.infrastructure.service.IFuncService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单功能 服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Service
public class FuncServiceImpl extends IBaseServiceImpl<FuncMapper, FuncDO> implements IFuncService {

}
