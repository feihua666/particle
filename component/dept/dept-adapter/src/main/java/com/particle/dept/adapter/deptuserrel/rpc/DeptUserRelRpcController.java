package com.particle.dept.adapter.deptuserrel.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.dept.adapter.feign.client.deptuserrel.rpc.DeptUserRelRpcFeignClient;
import com.particle.dept.client.deptuserrel.api.IDeptUserRelApplicationService;
import com.particle.dept.client.deptuserrel.api.representation.IDeptUserRelRepresentationApplicationService;
import com.particle.dept.client.deptuserrel.dto.command.DeptUserRelCreateCommand;
import com.particle.dept.client.deptuserrel.dto.command.DeptUserRelUpdateCommand;
import com.particle.dept.client.deptuserrel.dto.command.representation.DeptUserRelQueryListCommand;
import com.particle.dept.client.deptuserrel.dto.data.DeptUserRelVO;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 部门用户关系远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-05-17 10:28:42
 */
@Tag(name = "部门用户关系远程调用相关接口")
@RestController
@RequestMapping("/rpc/dept_user_rel")
public class DeptUserRelRpcController extends AbstractBaseRpcAdapter implements DeptUserRelRpcFeignClient  {

	@Autowired
	private IDeptUserRelApplicationService iDeptUserRelApplicationService;
    @Autowired
    private IDeptUserRelRepresentationApplicationService iDeptUserRelRepresentationApplicationService;


    @Operation(summary = "添加部门用户关系")
    @Override
    public SingleResponse<DeptUserRelVO> create(@RequestBody DeptUserRelCreateCommand deptUserRelCreateCommand) {
        return iDeptUserRelApplicationService.create(deptUserRelCreateCommand);
    }
    @Operation(summary = "根据用户id删除")
    @Override
	public Response deleteByUserId(@RequestBody IdCommand deleteCommand) {
		return iDeptUserRelApplicationService.deleteByUserId(deleteCommand);
	}

    @Operation(summary = "更新部门用户关系")
    @Override
    public SingleResponse<DeptUserRelVO> update(@RequestBody DeptUserRelUpdateCommand deptUserRelUpdateCommand){
        return iDeptUserRelApplicationService.update(deptUserRelUpdateCommand);
    }

    @Operation(summary = "列表查询部门用户关系")
    @Override
    public MultiResponse<DeptUserRelVO> queryList(DeptUserRelQueryListCommand deptUserRelQueryListCommand) {
        return iDeptUserRelRepresentationApplicationService.queryList(deptUserRelQueryListCommand);
    }

}
