package com.particle.dept.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.dept.adapter.feign.client.rpc.DeptRpcFeignClient;
import com.particle.dept.adapter.feign.client.rpc.DeptTransRpcFeignClient;
import com.particle.dept.client.api.IDeptApplicationService;
import com.particle.dept.client.api.representation.IDeptRepresentationApplicationService;
import com.particle.dept.client.dto.command.representation.DeptQueryListCommand;
import com.particle.dept.client.dto.data.DeptTransVO;
import com.particle.dept.client.dto.data.DeptVO;
import com.particle.global.dto.dataconstraint.DataConstraintContext;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.trans.result.TransResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 部门远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-04-12 14:19:42
 */
@Tag(name = "部门远程调用相关接口")
@RestController
@RequestMapping("/rpc/dept")
public class DeptRpcController extends AbstractBaseRpcAdapter implements DeptRpcFeignClient, DeptTransRpcFeignClient {

	@Autowired
	private IDeptApplicationService iDeptApplicationService;
	@Autowired
	private IDeptRepresentationApplicationService iDeptRepresentationApplicationService;
	@Autowired
	private DeptTransServiceImpl deptTransService;

	@Override
	public boolean supportBatch(String type) {
		return deptTransService.supportBatch(type);
	}

	@Override
	public List<TransResult<DeptTransVO, Long>> transBatch(String type, Set<Long> keys) {
		return deptTransService.transBatch(type, keys);
	}

	/**
	 * 主要用于获取当前登录用户的数据权限内的部门信息
	 * @return
	 */
	@Operation(summary = "列表查询数据权限内的部门")
	@GetMapping("/dataConstrainList")
	public MultiResponse<DeptVO> dataConstrainList(){
		DeptQueryListCommand deptQueryListCommand = new DeptQueryListCommand();
		deptQueryListCommand.dcdo(DataConstraintConstants.data_object_dept_dept, DataConstraintContext.Action.query.name());
		return iDeptRepresentationApplicationService.queryList(deptQueryListCommand);
	}
}