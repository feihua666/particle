package com.particle.crm.adapter.customer.rpc;

import cn.hutool.core.util.StrUtil;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.crm.client.customer.dto.data.CrmCustomerTransVO;
import com.particle.crm.infrastructure.customer.dos.CrmCustomerDO;
import com.particle.crm.infrastructure.customer.service.ICrmCustomerService;
import com.particle.global.trans.api.ITransService;
import com.particle.global.trans.result.TransResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 翻译客户实现
 * @author  yangwei
 * Created at 2024-05-06 16:31:35
 */
@Primary
@Component
public class CrmCustomerTransServiceImpl implements ITransService<CrmCustomerTransVO,Long> {

    @Autowired
    private ICrmCustomerService iCrmCustomerService;

    @Override
    public boolean support(String type) {
        return StrUtil.containsAny(type, TransConstants.TRANS_CRM_CUSTOMER_BY_ID);
    }

    @Override
    public TransResult<CrmCustomerTransVO, Long> trans(String type, Long key) {
        CrmCustomerDO crmCustomerDO = null;
        if (StrUtil.containsAny(type,TransConstants.TRANS_CRM_CUSTOMER_BY_ID)) {
            crmCustomerDO = iCrmCustomerService.getById(key);
        }
        if (crmCustomerDO != null) {
            return new TransResult(newCrmCustomerTransVO(crmCustomerDO),key);
        }
        return null;
    }

    @Override
    public boolean supportBatch(String type) {
        return support(type);
    }

    @Override
    public List<TransResult<CrmCustomerTransVO, Long>> transBatch(String type, Set<Long> keys) {
        if (StrUtil.containsAny(type,TransConstants.TRANS_CRM_CUSTOMER_BY_ID)) {
            List<CrmCustomerDO> crmCustomerDOS = iCrmCustomerService.listByIds(keys);
            return crmCustomerDOS.stream()
                    .map(item->new TransResult<CrmCustomerTransVO, Long>(newCrmCustomerTransVO(item),item.getId()))
                    .collect(Collectors.toList());
        }
        return null;
    }

    private CrmCustomerTransVO newCrmCustomerTransVO(CrmCustomerDO crmCustomerDO){
        if (crmCustomerDO == null) {
            return null;
        }
        CrmCustomerTransVO crmCustomerTransVO = new CrmCustomerTransVO();
        crmCustomerTransVO.setId(crmCustomerDO.getId());
        crmCustomerTransVO.setCode(crmCustomerDO.getCode());
        crmCustomerTransVO.setName(crmCustomerDO.getName());
        crmCustomerTransVO.setAppellation(crmCustomerDO.getAppellation());


        return crmCustomerTransVO;
    }

}
