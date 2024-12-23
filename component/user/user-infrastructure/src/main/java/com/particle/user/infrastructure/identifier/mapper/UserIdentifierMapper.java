package com.particle.user.infrastructure.identifier.mapper;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import com.particle.user.infrastructure.identifier.dos.UserIdentifierDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.util.Assert;

import java.util.List;

/**
 * <p>
 * 用户登录标识 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Mapper
public interface UserIdentifierMapper extends IBaseMapper<UserIdentifierDO> {

	default List<UserIdentifierDO> getByUserId(Long userId) {
		Assert.notNull(userId,"userId不能为空");
		return selectList(Wrappers.<UserIdentifierDO>lambdaQuery().eq(UserIdentifierDO::getUserId, userId));
	}
}
