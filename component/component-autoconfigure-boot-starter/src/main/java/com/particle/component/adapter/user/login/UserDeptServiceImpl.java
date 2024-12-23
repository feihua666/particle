package com.particle.component.adapter.user.login;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.dept.adapter.feign.client.rpc.DeptRpcFeignClient;
import com.particle.dept.client.dto.data.DeptVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.security.security.login.DeptInfo;
import com.particle.global.security.security.login.UserDeptService;
import org.springframework.beans.factory.annotation.Autowired;

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
	private DeptRpcFeignClient deptRpcFeignClient;


	@Override
	public DeptInfo retrieveUserDeptInfoByUserId(Long userId) {
		SingleResponse<DeptVO> byUserId = deptRpcFeignClient.getByUserId(userId);
		DeptVO deptVO = byUserId.getData();
		if (deptVO != null) {
			return map(deptVO);
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
		return data.stream().map(deptVO -> map(deptVO)).collect(Collectors.toList());
	}

	/**
	 * 对象映射转换
	 * @param deptVO
	 * @return
	 */
	private DeptInfo map(DeptVO deptVO) {
		if (deptVO == null) {
			return null;
		}
		return DeptInfo.create(
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
		);
	}
}
