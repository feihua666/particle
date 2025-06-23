package com.particle.data.adapter.company.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.data.client.company.api.IDataCompanyDeliveryAnnouncementPartyApplicationService;
import com.particle.data.adapter.feign.client.company.rpc.DataCompanyDeliveryAnnouncementPartyRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业送达公告当事人远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:33
 */
@Tag(name = "企业送达公告当事人远程调用相关接口")
@RestController
@RequestMapping("/rpc/data_company_delivery_announcement_party")
public class DataCompanyDeliveryAnnouncementPartyRpcController extends AbstractBaseRpcAdapter implements DataCompanyDeliveryAnnouncementPartyRpcFeignClient  {

	@Autowired
	private IDataCompanyDeliveryAnnouncementPartyApplicationService iDataCompanyDeliveryAnnouncementPartyApplicationService;


}