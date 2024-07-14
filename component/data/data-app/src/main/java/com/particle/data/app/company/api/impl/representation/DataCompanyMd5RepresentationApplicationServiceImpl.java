package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyMd5QueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyMd5RepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyMd5PageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyMd5QueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyMd5VO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 企业md5 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:59
 */
@Service
@CatchAndLog
public class DataCompanyMd5RepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyMd5RepresentationApplicationService {

    private DataCompanyMd5QueryCommandExecutor dataCompanyMd5QueryCommandExecutor;

    @Override
    public SingleResponse<DataCompanyMd5VO> queryDetail(IdCommand detailCommand) {
        return dataCompanyMd5QueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyMd5VO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyMd5QueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyMd5VO> pageQuery(DataCompanyMd5PageQueryCommand dataCompanyMd5PageQueryCommand) {
        return dataCompanyMd5QueryCommandExecutor.execute(dataCompanyMd5PageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyMd5VO> queryList(DataCompanyMd5QueryListCommand dataCompanyMd5QueryListCommand) {
        return dataCompanyMd5QueryCommandExecutor.execute(dataCompanyMd5QueryListCommand);
    }


    @Autowired
    public void setDataCompanyMd5QueryCommandExecutor(DataCompanyMd5QueryCommandExecutor dataCompanyMd5QueryCommandExecutor) {
        this.dataCompanyMd5QueryCommandExecutor = dataCompanyMd5QueryCommandExecutor;
    }
}
