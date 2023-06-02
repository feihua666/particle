package com.particle.func.infrastructure.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.func.infrastructure.dos.FuncGroupDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import org.springframework.util.Assert;

/**
 * <p>
 * 功能组 服务类
 * </p>
 *
 * @author yw
 * @since 2022-12-02
 */
public interface IFuncGroupService extends IBaseService<FuncGroupDO> {

	/**
	 * 根据编码查询
	 * @param code
	 * @return
	 */
	default FuncGroupDO getByCode(String code) {
		Assert.hasText(code,"code 不能为空");
		return getOne(Wrappers.<FuncGroupDO>lambdaQuery().eq(FuncGroupDO::getCode, code));
	}
}
