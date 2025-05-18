package com.particle.data.adapter.company.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.data.client.company.api.IDataCompanyOpenCourtAnnouncementPartyApplicationService;
import com.particle.data.adapter.feign.client.company.rpc.DataCompanyOpenCourtAnnouncementPartyRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业开庭公告当事人远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:03
 */
@Tag(name = "企业开庭公告当事人远程调用相关接口")
@RestController
@RequestMapping("/rpc/data_company_open_court_announcement_party")
public class DataCompanyOpenCourtAnnouncementPartyRpcController extends AbstractBaseRpcAdapter implements DataCompanyOpenCourtAnnouncementPartyRpcFeignClient  {

	@Autowired
	private IDataCompanyOpenCourtAnnouncementPartyApplicationService iDataCompanyOpenCourtAnnouncementPartyApplicationService;


}