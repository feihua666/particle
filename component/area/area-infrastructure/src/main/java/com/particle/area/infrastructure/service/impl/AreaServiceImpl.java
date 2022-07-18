package com.particle.area.infrastructure.service.impl;

import com.particle.area.infrastructure.dos.AreaDO;
import com.particle.area.infrastructure.mapper.AreaMapper;
import com.particle.area.infrastructure.service.IAreaService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 区域 服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-07-18
 */
@Service
public class AreaServiceImpl extends IBaseServiceImpl<AreaMapper, AreaDO> implements IAreaService {

}
