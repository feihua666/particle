package com.particle.data.app.company.executor.representation.exwarehousewrap;

import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyExWarehouseCandidateVO;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 包装出库基类
 * </p>
 *
 * @author yangwei
 * @since 2025/5/16 16:33
 */
public class AbstractBaseWrapExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

    private DataCompanyExWarehouseCommandExecutor dataCompanyExWarehouseCommandExecutor;

    /**
     * 根据查询条件获取企业id
     * @param dataCompanyExWarehouseQueryCommand
     * @return
     */
    public Long getCompanyId(DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        DataCompanyExWarehouseCandidateVO data = getCompany(dataCompanyExWarehouseQueryCommand);
        if (data != null) {
            return data.getId();
        }
        return null;
    }

    /**
     * 获取企业主体信息
     * @param dataCompanyExWarehouseQueryCommand
     * @return
     */
    public DataCompanyExWarehouseCandidateVO getCompany(DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        if (dataCompanyExWarehouseQueryCommand == null) {
            return null;
        }
        SingleResponse<DataCompanyExWarehouseCandidateVO> dataCompanyExWarehouseCandidateVOSingleResponse = dataCompanyExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
        DataCompanyExWarehouseCandidateVO data = dataCompanyExWarehouseCandidateVOSingleResponse.getData();
        return data;
    }

    @Autowired
    public void setDataCompanyExWarehouseCommandExecutor(DataCompanyExWarehouseCommandExecutor dataCompanyExWarehouseCommandExecutor) {
        this.dataCompanyExWarehouseCommandExecutor = dataCompanyExWarehouseCommandExecutor;
    }
}
