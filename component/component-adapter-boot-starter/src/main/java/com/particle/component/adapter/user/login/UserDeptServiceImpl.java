package com.particle.component.adapter.user.login;

import com.particle.dept.domain.gateway.DeptDictGateway;
import com.particle.dept.infrastructure.deptuserrel.service.IDeptUserRelService;
import com.particle.dept.infrastructure.dos.DeptDO;
import com.particle.dept.infrastructure.service.IDeptService;
import com.particle.global.security.security.login.DeptInfo;
import com.particle.global.security.security.login.UserDeptService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 * 获取用户部门信息
 * </p>
 *
 * @author yangwei
 * @since 2023-05-30 16:32
 */
public class UserDeptServiceImpl implements UserDeptService {

	@Autowired
	private IDeptService deptService;
	@Autowired
	private DeptDictGateway deptDictGateway;

	@Override
	public DeptInfo retrieveUserDeptInfoByUserId(Long userId) {
		DeptDO deptDO = deptService.getByUserId(userId);
		if (deptDO != null) {
			String dictValueById = deptDictGateway.getDictValueById(deptDO.getTypeDictId());
			return DeptInfo.create(
					deptDO.getId(),
					deptDO.getCode(),
					deptDO.getName(),
					deptDO.getTypeDictId(),
					dictValueById,
					deptDO.getMasterUserId(),
					deptDO.getIsVirtual(),
					deptDO.getIsComp(),
					deptDO.getParentId()
			);
		}
		return null;
	}
}
