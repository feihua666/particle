package com.particle.component.adapter.user.login;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.dept.adapter.feign.client.rpc.DeptRpcFeignClient;
import com.particle.dept.client.dto.command.representation.DeptQueryListCommand;
import com.particle.dept.client.dto.data.DeptVO;
import com.particle.dept.domain.gateway.DeptDictGateway;
import com.particle.dept.infrastructure.deptuserrel.service.IDeptUserRelService;
import com.particle.dept.infrastructure.dos.DeptDO;
import com.particle.dept.infrastructure.service.IDeptService;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.security.security.login.DeptInfo;
import com.particle.global.security.security.login.UserDeptService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
	@Autowired
	private DeptRpcFeignClient deptRpcFeignClient;


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
					deptDO.getParentId(),
					deptDO.getLevel()
			);
		}
		return null;
	}

	@Override
	public List<DeptInfo> retrieveDeptInfoByDataConstraint() {
		MultiResponse<DeptVO> deptVOMultiResponse = deptRpcFeignClient.dataConstrainList();
		List<DeptVO> data = deptVOMultiResponse.getData();
		if (CollectionUtil.isEmpty(data)) {
			return null;
		}
		return data.stream().map(deptVO ->
			DeptInfo.create(
					deptVO.getId(),
					deptVO.getCode(),
					deptVO.getName(),
					deptVO.getTypeDictId(),
					deptVO.getTypeDictValue(),
					deptVO.getMasterUserId(),
					deptVO.getIsVirtual(),
					deptVO.getIsComp(),
					deptVO.getParentId(),
					deptVO.getLevel()
			)
		).collect(Collectors.toList());
	}
}
