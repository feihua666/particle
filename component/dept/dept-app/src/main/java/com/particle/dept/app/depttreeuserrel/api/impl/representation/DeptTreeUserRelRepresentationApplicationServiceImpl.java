package com.particle.dept.app.depttreeuserrel.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dept.app.depttreeuserrel.executor.representation.DeptTreeUserRelQueryCommandExecutor;
import com.particle.dept.client.depttreeuserrel.api.representation.IDeptTreeUserRelRepresentationApplicationService;
import com.particle.dept.client.depttreeuserrel.dto.command.representation.DeptTreeUserRelPageQueryCommand;
import com.particle.dept.client.depttreeuserrel.dto.command.representation.DeptTreeUserRelQueryListCommand;
import com.particle.dept.client.depttreeuserrel.dto.data.DeptTreeUserRelVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 部门树用户关系 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-05-17 10:26:06
 */
@Service
@CatchAndLog
public class DeptTreeUserRelRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDeptTreeUserRelRepresentationApplicationService {

    private DeptTreeUserRelQueryCommandExecutor deptTreeUserRelQueryCommandExecutor;

    @Override
    public SingleResponse<DeptTreeUserRelVO> queryDetail(IdCommand detailCommand) {
        return deptTreeUserRelQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DeptTreeUserRelVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return deptTreeUserRelQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DeptTreeUserRelVO> pageQuery(DeptTreeUserRelPageQueryCommand deptTreeUserRelPageQueryCommand) {
        return deptTreeUserRelQueryCommandExecutor.execute(deptTreeUserRelPageQueryCommand);
    }

    @Override
    public MultiResponse<DeptTreeUserRelVO> queryList(DeptTreeUserRelQueryListCommand deptTreeUserRelQueryListCommand) {
        return deptTreeUserRelQueryCommandExecutor.execute(deptTreeUserRelQueryListCommand);
    }

    @Autowired
    public void setDeptTreeUserRelQueryCommandExecutor(DeptTreeUserRelQueryCommandExecutor deptTreeUserRelQueryCommandExecutor) {
        this.deptTreeUserRelQueryCommandExecutor = deptTreeUserRelQueryCommandExecutor;
    }
}
