package com.particle.dept.app.deptuserrel.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dept.app.deptuserrel.executor.representation.DeptUserRelQueryCommandExecutor;
import com.particle.dept.client.deptuserrel.api.representation.IDeptUserRelRepresentationApplicationService;
import com.particle.dept.client.deptuserrel.dto.command.representation.DeptUserRelPageQueryCommand;
import com.particle.dept.client.deptuserrel.dto.command.representation.DeptUserRelQueryListCommand;
import com.particle.dept.client.deptuserrel.dto.data.DeptUserRelVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 部门用户关系 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-05-17 10:28:42
 */
@Service
@CatchAndLog
public class DeptUserRelRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDeptUserRelRepresentationApplicationService {

    private DeptUserRelQueryCommandExecutor deptUserRelQueryCommandExecutor;

    @Override
    public SingleResponse<DeptUserRelVO> queryDetail(IdCommand detailCommand) {
        return deptUserRelQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DeptUserRelVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return deptUserRelQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DeptUserRelVO> pageQuery(DeptUserRelPageQueryCommand deptUserRelPageQueryCommand) {
        return deptUserRelQueryCommandExecutor.execute(deptUserRelPageQueryCommand);
    }

    @Override
    public MultiResponse<DeptUserRelVO> queryList(DeptUserRelQueryListCommand deptUserRelQueryListCommand) {
        return deptUserRelQueryCommandExecutor.execute(deptUserRelQueryListCommand);
    }

    @Autowired
    public void setDeptUserRelQueryCommandExecutor(DeptUserRelQueryCommandExecutor deptUserRelQueryCommandExecutor) {
        this.deptUserRelQueryCommandExecutor = deptUserRelQueryCommandExecutor;
    }
}
