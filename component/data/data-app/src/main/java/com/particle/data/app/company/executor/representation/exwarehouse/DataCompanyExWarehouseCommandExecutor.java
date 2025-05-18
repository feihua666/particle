package com.particle.data.app.company.executor.representation.exwarehouse;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyExWarehouseCandidateVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyDO;
import com.particle.data.infrastructure.company.dos.DataCompanyMd5DO;
import com.particle.data.infrastructure.company.service.IDataCompanyMd5Service;
import com.particle.data.infrastructure.company.service.IDataCompanyService;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.global.tool.str.StringTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 企业标识信息出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:24:34
 */
@Component
@Validated
public class DataCompanyExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyService iDataCompanyService;
	private IDataCompanyMd5Service iDataCompanyMd5Service;

	/**
	 * 企业标识信息出库
	 * @param exWarehouseQueryCommand
	 * @return
	 */
	public SingleResponse<DataCompanyExWarehouseCandidateVO> exWarehouse(DataCompanyExWarehouseQueryCommand exWarehouseQueryCommand) {
		// 存储主要是
		DataCompanyMd5DO dataCompanyMd5DO = null;
		// 存储候选的
		List<DataCompanyMd5DO> candidatesDataCompanyMd5DOs = null;

		if (dataCompanyMd5DO == null) {
			String uscc = exWarehouseQueryCommand.getUscc();
			if (StrUtil.isNotEmpty(uscc)) {
				String usccMd5 = StringTool.isMd5(uscc) ? uscc : DigestUtil.md5Hex(uscc);
				dataCompanyMd5DO = iDataCompanyMd5Service.getByUsccMd5(usccMd5);
			}
		}

		if (dataCompanyMd5DO == null) {
			String regNo = exWarehouseQueryCommand.getRegNo();
			if (StrUtil.isNotEmpty(regNo)) {
				String regNoMd5 = StringTool.isMd5(regNo) ? regNo : DigestUtil.md5Hex(regNo);
				dataCompanyMd5DO = iDataCompanyMd5Service.getByRegNoMd5(regNoMd5);
			}
		}

		if (dataCompanyMd5DO == null) {
			String orgCode = exWarehouseQueryCommand.getOrgCode();
			if (StrUtil.isNotEmpty(orgCode)) {
				String orgCodeMd5 = StringTool.isMd5(orgCode) ? orgCode : DigestUtil.md5Hex(orgCode);
				dataCompanyMd5DO = iDataCompanyMd5Service.getByOrgCodeMd5(orgCode);
			}
		}

		if (dataCompanyMd5DO == null) {
			String name = exWarehouseQueryCommand.getName();
			if (StrUtil.isNotEmpty(name)) {
				String nameMd5 = StringTool.isMd5(name) ? name : DigestUtil.md5Hex(name);
				List<DataCompanyMd5DO> byNameMd5 = iDataCompanyMd5Service.getByNameMd5(nameMd5);
				if (CollectionUtil.isNotEmpty(byNameMd5)) {
					for (int i = 0; i < byNameMd5.size(); i++) {
						if (i == 0) {
							dataCompanyMd5DO = byNameMd5.get(i);
						}else {
							if (candidatesDataCompanyMd5DOs == null) {
								candidatesDataCompanyMd5DOs = new ArrayList<>(byNameMd5.size() - 1);
							}
							candidatesDataCompanyMd5DOs.add(byNameMd5.get(i));
						}
					}
				}
			}
		}

		if (dataCompanyMd5DO == null) {
			String enName = exWarehouseQueryCommand.getEnName();
			if (StrUtil.isNotEmpty(enName)) {
				String enNameMd5 = StringTool.isMd5(enName) ? enName : DigestUtil.md5Hex(enName);
				List<DataCompanyMd5DO> byEnNameMd5 = iDataCompanyMd5Service.getByEnNameMd5(enNameMd5);
				if (CollectionUtil.isNotEmpty(byEnNameMd5)) {
					for (int i = 0; i < byEnNameMd5.size(); i++) {
						if (i == 0) {
							dataCompanyMd5DO = byEnNameMd5.get(i);
						}else {
							if (candidatesDataCompanyMd5DOs == null) {
								candidatesDataCompanyMd5DOs = new ArrayList<>(byEnNameMd5.size() - 1);
							}
							candidatesDataCompanyMd5DOs.add(byEnNameMd5.get(i));
						}
					}
				}
			}
		}
		// 有可能没有入参，与会返回数据没有找到
		if (dataCompanyMd5DO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}

		// 处理最终结果
		int size = candidatesDataCompanyMd5DOs == null ? 1 : candidatesDataCompanyMd5DOs.size() + 1;
		// 定义企业id
		List<Long> companyIds = new ArrayList<>(size);
		companyIds.add(dataCompanyMd5DO.getCompanyId());
		if (candidatesDataCompanyMd5DOs != null) {
			for (DataCompanyMd5DO candidatesDataCompanyMd5DO : candidatesDataCompanyMd5DOs) {
				companyIds.add(candidatesDataCompanyMd5DO.getCompanyId());
			}
		}
		List<DataCompanyDO> dataCompanyDOS = iDataCompanyService.listByIds(companyIds);

		// 再重新组装主要数据和候选数据
		DataCompanyExWarehouseCandidateVO dataCompanyExWarehouseVO = null;
		List<DataCompanyExWarehouseVO> candidateDataCompanyExWarehouseVO = new ArrayList<>(size);
		for (DataCompanyDO dataCompanyDO : dataCompanyDOS) {

			if (dataCompanyDO.getId().equals(dataCompanyMd5DO.getCompanyId())) {
				dataCompanyExWarehouseVO = DataCompanyAppStructMapping.instance.mappingExWarehouseCandidateVOCandidate(dataCompanyDO);
			}else {
				candidateDataCompanyExWarehouseVO.add(DataCompanyAppStructMapping.instance.mappingExWarehouseVO(dataCompanyDO));
			}
		}
		dataCompanyExWarehouseVO.setCandidates(candidateDataCompanyExWarehouseVO);

		return SingleResponse.of(dataCompanyExWarehouseVO);
	}


	@Autowired
	public void setIDataCompanyService(IDataCompanyService iDataCompanyService) {
		this.iDataCompanyService = iDataCompanyService;
	}
	@Autowired
	public void setIDataCompanyMd5Service(IDataCompanyMd5Service iDataCompanyMd5Service) {
		this.iDataCompanyMd5Service = iDataCompanyMd5Service;
	}
}
