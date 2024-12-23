package com.particle.component.adapter.dataconstraint.login;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.dataconstraint.adapter.feign.client.rpc.DataObjectRpcFeignClient;
import com.particle.dataconstraint.adapter.feign.client.rpc.DataScopeCustomDataRelRpcFeignClient;
import com.particle.dataconstraint.adapter.feign.client.rpc.DataScopeRpcFeignClient;
import com.particle.dataconstraint.client.dto.data.DataObjectVO;
import com.particle.dataconstraint.client.dto.data.DataScopeCustomDataRelVO;
import com.particle.dataconstraint.client.dto.data.DataScopeVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.security.security.login.GrantedDataConstraint;
import com.particle.global.security.security.login.RoleDataConstraintService;
import com.particle.role.infrastructure.roledatascoperel.dos.RoleDataScopeRelDO;
import com.particle.role.infrastructure.roledatascoperel.service.IRoleDataScopeRelService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色数据范围约束服务实现
 * </p>
 *
 * @author yangwei
 * @since 2024/7/5 13:18
 */
public class RoleDataConstraintServiceImpl implements RoleDataConstraintService {

    private DataObjectRpcFeignClient dataObjectRpcFeignClient;
    private DataScopeRpcFeignClient dataScopeRpcFeignClient;
    private DataScopeCustomDataRelRpcFeignClient dataScopeCustomDataRelRpcFeignClient;

    private IRoleDataScopeRelService roleDataScopeRelService;


    @Override
    public List<GrantedDataConstraint> retrieveRoleDataConstraintByRoleId(Long roleId) {
        List<RoleDataScopeRelDO> roleDataScopeRelDOs = roleDataScopeRelService.getByRoleId(roleId);
        if (CollectionUtil.isEmpty(roleDataScopeRelDOs)) {
            return null;
        }
        List<Long> dataObjectIds = roleDataScopeRelDOs.stream().map(RoleDataScopeRelDO::getDataObjectId).distinct().collect(Collectors.toList());
        List<Long> dataScopeIds = roleDataScopeRelDOs.stream().map(RoleDataScopeRelDO::getDataScopeId).distinct().collect(Collectors.toList());

        MultiResponse<DataObjectVO> dataObjectVOs = dataObjectRpcFeignClient.getByDataObjectIds(dataObjectIds);
        MultiResponse<DataScopeVO> DataScopeVOs = dataScopeRpcFeignClient.getByDataScopeIds(dataScopeIds);
        MultiResponse<DataScopeCustomDataRelVO> dataScopeCustomDataRelVOs = dataScopeCustomDataRelRpcFeignClient.getByDataScopeIds(dataScopeIds);

        Map<Long, DataObjectVO> dataObjectVOMap = dataObjectVOs.getData().stream().collect(Collectors.toMap(DataObjectVO::getId, Function.identity()));
        Map<Long, DataScopeVO> dataScopeVOMap = DataScopeVOs.getData().stream().collect(Collectors.toMap(DataScopeVO::getId, Function.identity()));
        Map<Long, List<DataScopeCustomDataRelVO>> dataScopeCustomeDataRelVOMap;
        if (CollectionUtil.isNotEmpty(dataScopeCustomDataRelVOs.getData())) {
            dataScopeCustomeDataRelVOMap = dataScopeCustomDataRelVOs.getData().stream().collect(Collectors.groupingBy(DataScopeCustomDataRelVO::getDataScopeId, Collectors.toList()));
        } else {
            dataScopeCustomeDataRelVOMap = null;
        }

        List<GrantedDataConstraint> grantedDataConstraintList = roleDataScopeRelDOs.stream().map(roleDataScopeRelDO -> roleDataScopeRelToGrantedDataConstraint(roleDataScopeRelDO, dataObjectVOMap, dataScopeVOMap, dataScopeCustomeDataRelVOMap)).collect(Collectors.toList());

        return grantedDataConstraintList;
    }

    /**
     * 对象转换
     * @param roleDataScopeRelDO
     * @param dataObjectVOMap
     * @param dataScopeVOMap
     * @return
     */
    private GrantedDataConstraint roleDataScopeRelToGrantedDataConstraint(RoleDataScopeRelDO roleDataScopeRelDO,
                                                                          Map<Long, DataObjectVO> dataObjectVOMap,
                                                                          Map<Long, DataScopeVO> dataScopeVOMap,
                                                                          Map<Long, List<DataScopeCustomDataRelVO>> dataScopeCustomeDataRelVOMap){
        DataObjectVO dataObjectVO = dataObjectVOMap.get(roleDataScopeRelDO.getDataObjectId());
        DataScopeVO dataScopeVO = dataScopeVOMap.get(roleDataScopeRelDO.getDataScopeId());

        GrantedDataConstraint.GrantedDataObject grantedDataObject = new GrantedDataConstraint.GrantedDataObject();
        grantedDataObject.setId(dataObjectVO.getId());
        grantedDataObject.setName(dataObjectVO.getName());
        grantedDataObject.setCode(dataObjectVO.getCode());
        grantedDataObject.setIsDisabled(dataObjectVO.getIsDisabled());

        GrantedDataConstraint.GrantedDataScope grantedDataScope = new GrantedDataConstraint.GrantedDataScope();
        grantedDataScope.setId(dataScopeVO.getId());
        grantedDataScope.setName(dataScopeVO.getName());
        grantedDataScope.setIsForOther(dataScopeVO.getIsForOther());
        grantedDataScope.setIsForQuery(dataScopeVO.getIsForQuery());
        grantedDataScope.setIsForUpdate(dataScopeVO.getIsForUpdate());
        grantedDataScope.setIsForDelete(dataScopeVO.getIsForDelete());
        grantedDataScope.setIsCustom(dataScopeVO.getIsCustom());
        grantedDataScope.setConstraintContent(dataScopeVO.getConstraintContent());
        grantedDataScope.setConstraintContentTypeDictValue(dataScopeVO.getConstraintContentTypeDictValue());
        grantedDataScope.setCode(dataScopeVO.getCode());
        // 自定义数据
        if (dataScopeVO.getIsCustom() && dataScopeCustomeDataRelVOMap != null) {
            List<DataScopeCustomDataRelVO> dataScopeCustomDataRelVOS = dataScopeCustomeDataRelVOMap.get(dataScopeVO.getId());
            if (CollectionUtil.isNotEmpty(dataScopeCustomDataRelVOS)) {
                List<Long> customDataIds = dataScopeCustomDataRelVOS.stream().map(dataScopeCustomDataRelVO -> dataScopeCustomDataRelVO.getDataId()).distinct().collect(Collectors.toList());
                grantedDataScope.setCustomDataIds(customDataIds);
            }

        }


        return GrantedDataConstraint.builder()
                .grantedDataObject(grantedDataObject)
                .grantedDataScope(grantedDataScope).build();
    }

    @Autowired
    public void setDataObjectRpcFeignClient(DataObjectRpcFeignClient dataObjectRpcFeignClient) {
        this.dataObjectRpcFeignClient = dataObjectRpcFeignClient;
    }
    @Autowired
    public void setDataScopeRpcFeignClient(DataScopeRpcFeignClient dataScopeRpcFeignClient) {
        this.dataScopeRpcFeignClient = dataScopeRpcFeignClient;
    }
    @Autowired
    public void setDataScopeCustomDataRelRpcFeignClient(DataScopeCustomDataRelRpcFeignClient dataScopeCustomDataRelRpcFeignClient) {
        this.dataScopeCustomDataRelRpcFeignClient = dataScopeCustomDataRelRpcFeignClient;
    }

    @Autowired
    public void setRoleDataScopeRelService(IRoleDataScopeRelService roleDataScopeRelService) {
        this.roleDataScopeRelService = roleDataScopeRelService;
    }
}
