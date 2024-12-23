package com.particle.data.app.temp.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.app.temp.executor.representation.DataCompanyMd5IdsQueryCommandExecutor;
import com.particle.data.client.temp.api.representation.IDataCompanyMd5IdsRepresentationApplicationService;
import com.particle.data.client.temp.dto.command.representation.DataCompanyMd5IdsPageQueryCommand;
import com.particle.data.client.temp.dto.command.representation.DataCompanyMd5IdsQueryListCommand;
import com.particle.data.client.temp.dto.data.DataCompanyMd5IdsVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 企业md5ids 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:24:11
 */
@Service
@CatchAndLog
public class DataCompanyMd5IdsRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyMd5IdsRepresentationApplicationService {

    private DataCompanyMd5IdsQueryCommandExecutor dataCompanyMd5IdsQueryCommandExecutor;

    @Override
    public SingleResponse<DataCompanyMd5IdsVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyMd5IdsQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyMd5IdsVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyMd5IdsQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyMd5IdsVO> pageQuery(DataCompanyMd5IdsPageQueryCommand dataCompanyMd5IdsPageQueryCommand) {
        return dataCompanyMd5IdsQueryCommandExecutor.execute(dataCompanyMd5IdsPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyMd5IdsVO> queryList(DataCompanyMd5IdsQueryListCommand dataCompanyMd5IdsQueryListCommand) {
        return dataCompanyMd5IdsQueryCommandExecutor.execute(dataCompanyMd5IdsQueryListCommand);
    }


    @Autowired
    public void setDataCompanyMd5IdsQueryCommandExecutor(DataCompanyMd5IdsQueryCommandExecutor dataCompanyMd5IdsQueryCommandExecutor) {
        this.dataCompanyMd5IdsQueryCommandExecutor = dataCompanyMd5IdsQueryCommandExecutor;
    }
}
