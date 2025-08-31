package com.particle.user.infrastructure.mapper;

import com.particle.user.infrastructure.dos.UserExtraInfoDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 用户扩展信息 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2025-08-30 23:39:50
 */
@Mapper
public interface UserExtraInfoMapper extends IBaseMapper<UserExtraInfoDO> {

}
