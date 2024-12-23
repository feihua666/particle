package com.particle.crm.domain.customer.gateway;

import com.particle.common.domain.gateway.IBaseGateway;
import com.particle.crm.domain.customer.CrmCustomer;
import com.particle.crm.domain.customer.CrmCustomerId;

/**
 * <p>
 * 客户 防腐层
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:21:36
 */
public interface CrmCustomerGateway extends IBaseGateway<CrmCustomerId,CrmCustomer> {
}
