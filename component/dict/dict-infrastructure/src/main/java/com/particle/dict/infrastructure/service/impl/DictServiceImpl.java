package com.particle.dict.infrastructure.service.impl;

import com.particle.dict.infrastructure.dos.DictDO;
import com.particle.dict.infrastructure.mapper.DictMapper;
import com.particle.dict.infrastructure.service.IDictService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 字典 服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Service
public class DictServiceImpl extends IBaseServiceImpl<DictMapper, DictDO> implements IDictService {

}
