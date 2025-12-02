package com.particle.global.mybatis.plus.mapper;

import com.baomidou.mybatisplus.core.injector.methods.Insert;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import com.particle.global.mybatis.plus.table.DynamicDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 动态实体 Mapper
 * 目前仅对 insert 方法有效，因为只重写了 insert 方法注入逻辑
 * {@link Insert}
 * </p>
 *
 * @author yangwei
 * @since 2025/11/16 16:50
 */
@Mapper
public interface DynamicDOMapper extends IBaseMapper<DynamicDO> {
}
