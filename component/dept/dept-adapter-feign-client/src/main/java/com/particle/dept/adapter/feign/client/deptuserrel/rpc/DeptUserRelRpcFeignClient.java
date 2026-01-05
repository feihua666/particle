package com.particle.dept.adapter.feign.client.deptuserrel.rpc;

import com.particle.common.client.dto.command.IdCommand;
import com.particle.dept.client.deptuserrel.dto.command.DeptUserRelCreateCommand;
import com.particle.dept.client.deptuserrel.dto.command.DeptUserRelUpdateCommand;
import com.particle.dept.client.deptuserrel.dto.command.representation.DeptUserRelQueryListCommand;
import com.particle.dept.client.deptuserrel.dto.data.DeptUserRelVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 部门用户关系远程调用
 * </p>
 *
 * @author yw
 * @since 2023-05-17 10:28:42
 */
@FeignClient(name = "${particle.feign-client.name.dept:dept}",path = "/rpc/dept_user_rel")
public interface DeptUserRelRpcFeignClient {

    /**
     * 添加部门用户关系
     * @param deptUserRelCreateCommand
     * @return
     */
    @PostMapping("/create")
    public SingleResponse<DeptUserRelVO> create(@RequestBody DeptUserRelCreateCommand deptUserRelCreateCommand);
	/**
	 * 根据用户ID删除部门用户关系
	 * @param
	 * @return
	 */
	@DeleteMapping("/deleteByUserId")
	public Response deleteByUserId(@RequestBody IdCommand deleteCommand);

    /**
     * 更新部门用户关系
     *
     * @param deptUserRelUpdateCommand
     * @return
     */
    @PutMapping("/update")
    public SingleResponse<DeptUserRelVO> update(@RequestBody DeptUserRelUpdateCommand deptUserRelUpdateCommand);

    /**
     * 列表查询部门用户关系
     * @param deptUserRelQueryListCommand
     * @return
     */
    @GetMapping("/list")
    public MultiResponse<DeptUserRelVO> queryList(DeptUserRelQueryListCommand deptUserRelQueryListCommand);
}
