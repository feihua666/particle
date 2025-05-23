package com.particle.data.app.company.executor.warehousewrap;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehouse.DataCompanyWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyExWarehouseCandidateVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyExWarehouseVO;
import com.particle.data.common.tool.CompanyNameCheckTool;
import com.particle.data.common.tool.DataDictItemInfo;
import com.particle.data.common.tool.PersonNameCheckTool;
import com.particle.data.common.tool.SomeStrTool;
import com.particle.data.domain.gateway.DataAreaGateway;
import com.particle.data.domain.gateway.DataDictGateway;
import com.particle.global.dto.response.SingleResponse;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 包装入库基类
 * </p>
 *
 * @author yangwei
 * @since 2025/5/16 16:33
 */
public class AbstractBaseWrapWarehouseCommandExecutor extends AbstractBaseQueryExecutor {
    protected DataAreaGateway dataAreaGateway;
    protected DataDictGateway dataDictGateway;
    private DataCompanyExWarehouseCommandExecutor dataCompanyExWarehouseCommandExecutor;
    private DataCompanyWarehouseCommandExecutor dataCompanyWarehouseCommandExecutor;

    /**
     * 映射字典
     * @param mappingValue
     * @param dictGroupCode
     * @return
     */
    public DataDictItemInfo mappingDictItem(String mappingValue,String dictGroupCode) {
        if (StrUtil.isBlank(mappingValue)) {
            return null;
        }
        DataDictItemInfo dataDictItemInfo = dataDictGateway.matchWithMappingValue(mappingValue, dictGroupCode, true, true);
        return dataDictItemInfo;
    }

    /**
     * 映射字典id
     * @param mappingValue
     * @param dictGroupCode
     * @return
     */
    public Long mappingDictItemGetDictId(String mappingValue, String dictGroupCode) {
        DataDictItemInfo dataDictItemInfo = mappingDictItem(mappingValue, dictGroupCode);
        return dataDictItemInfo == null ? null : dataDictItemInfo.getId();
    }
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

    /**
     * 入库
     * @param companyName
     * @return
     */
    public DataCompanyExWarehouseVO warehouseCompany(String companyName) {
        return warehouseCompany(DataCompanyWarehouseCommand.create(companyName, null));
    }
    /**
     * 入库
     * @param companyName
     * @param companyUscc
     * @return
     */
    public DataCompanyExWarehouseVO warehouseCompany(String companyName,String companyUscc) {
        return warehouseCompany(DataCompanyWarehouseCommand.create(companyName, companyUscc));
    }
    /**
     * 入库
     * @param companyName
     * @param companyUscc
     * @return
     */
    public DataCompanyExWarehouseVO warehouseCompany(String companyName,String companyUscc,String regNo) {
        return warehouseCompany(DataCompanyWarehouseCommand.create(companyName, companyUscc,regNo));
    }
    /**
     * 入库
     * @param dataCompanyWarehouseCommand
     * @return
     */
    public DataCompanyExWarehouseVO warehouseCompany(DataCompanyWarehouseCommand dataCompanyWarehouseCommand) {
        SingleResponse<DataCompanyExWarehouseVO> dataCompanyExWarehouseVOSingleResponse = dataCompanyWarehouseCommandExecutor.warehouse(dataCompanyWarehouseCommand);
        return dataCompanyExWarehouseVOSingleResponse.getData();
    }
    /**
     * 入库
     * @param companyName
     * @return
     */
    public Long warehouseCompanyGetCompanyId(String companyName) {
        DataCompanyExWarehouseVO dataCompanyExWarehouseCandidateVO = warehouseCompany(companyName);
        return dataCompanyExWarehouseCandidateVO == null ? null : dataCompanyExWarehouseCandidateVO.getId();
    }
    /**
     * 入库
     * @param companyName
     * @return
     */
    public Long warehouseCompanyGetCompanyId(String companyName,String uscc) {
        DataCompanyExWarehouseVO dataCompanyExWarehouseCandidateVO = warehouseCompany(companyName,uscc);
        return dataCompanyExWarehouseCandidateVO == null ? null : dataCompanyExWarehouseCandidateVO.getId();
    }
    /**
     * 入库
     * @param companyName
     * @return
     */
    public Long warehouseCompanyGetCompanyId(String companyName,String uscc,String regNo) {
        DataCompanyExWarehouseVO dataCompanyExWarehouseCandidateVO = warehouseCompany(companyName,uscc,regNo);
        return dataCompanyExWarehouseCandidateVO == null ? null : dataCompanyExWarehouseCandidateVO.getId();
    }

    /**
     * 统一检测一个字符串是否为自然人或企业，并返回其id
     * @param someStr
     * @param originCompanyId
     * @param originPersonId
     * @param originNaturePerson
     */
    public NaturePerson checkNaturePerson(String someStr,Long originCompanyId,Long originPersonId,Boolean originNaturePerson) {
        if (StrUtil.isBlank(someStr)) {
            return null;
        }
        Boolean isNaturePerson = originNaturePerson;
        Long companyId = originCompanyId;
        Long personId = originPersonId;
        if (isNaturePerson == null) {
            if (CompanyNameCheckTool.checkIsCompanyName(someStr)) {
                isNaturePerson = false;
            }else if(PersonNameCheckTool.checkIsPersonName(someStr)){
                isNaturePerson = true;
            }
        }
        // 没有判断出来
        if (isNaturePerson == null) {
            return null;
        }
        // 自然人
        if (isNaturePerson) {
            if (personId == null) {
                // todo 自然人处理
            }
        }
        // 企业
        else{
            if (companyId == null) {
                companyId = warehouseCompanyGetCompanyId(someStr);
            }
        }
        return NaturePerson.create(companyId, personId, isNaturePerson);
    }

    @Data
    public static class NaturePerson{
        Long companyId;
        Long personId;
        Boolean isNaturePerson;

        public static NaturePerson create(Long companyId,Long personId,Boolean isNaturePerson){
            NaturePerson naturePerson = new NaturePerson();
            naturePerson.setCompanyId(companyId);
            naturePerson.setPersonId(personId);
            naturePerson.setIsNaturePerson(isNaturePerson);
            return naturePerson;
        }
    }

    @Autowired
    public void setDataAreaGateway(DataAreaGateway dataAreaGateway) {
        this.dataAreaGateway = dataAreaGateway;
    }
    @Autowired
    public void setDataDictGateway(DataDictGateway dataDictGateway) {
        this.dataDictGateway = dataDictGateway;
    }
    @Autowired
    public void setDataCompanyExWarehouseCommandExecutor(DataCompanyExWarehouseCommandExecutor dataCompanyExWarehouseCommandExecutor) {
        this.dataCompanyExWarehouseCommandExecutor = dataCompanyExWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyWarehouseCommandExecutor(DataCompanyWarehouseCommandExecutor dataCompanyWarehouseCommandExecutor) {
        this.dataCompanyWarehouseCommandExecutor = dataCompanyWarehouseCommandExecutor;
    }
}
