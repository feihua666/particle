package com.particle.data.app.company.executor;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.representation.DataCompanyQueryCommandExecutor;
import com.particle.data.client.company.dto.command.*;
import com.particle.data.client.company.dto.command.representation.DataCompanyUniqueExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.DataCompanyUniqueExWarehouseCandidateVO;
import com.particle.data.client.company.dto.data.DataCompanyUniqueExWarehouseVO;
import com.particle.data.client.company.dto.data.DataCompanyVO;
import com.particle.data.domain.company.gateway.DataCompanyGateway;
import com.particle.data.infrastructure.company.dos.DataCompanyMd5DO;
import com.particle.data.infrastructure.company.service.IDataCompanyMd5Service;
import com.particle.data.infrastructure.company.service.IDataCompanyService;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 企业 入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:44
 */
@Component
@Validated
public class DataCompanyWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyGateway dataCompanyGateway;
	private IDataCompanyService iDataCompanyService;

	private DataCompanyQueryCommandExecutor dataCompanyQueryCommandExecutor;
	private DataCompanyCreateCommandExecutor dataCompanyCreateCommandExecutor;
	private DataCompanyMd5CreateCommandExecutor dataCompanyMd5CreateCommandExecutor;
	private DataCompanyUpdateCommandExecutor dataCompanyUpdateCommandExecutor;
	private DataCompanyMd5UpdateCommandExecutor dataCompanyMd5UpdateCommandExecutor;

	private IDataCompanyMd5Service iDataCompanyMd5Service;

	/**
	 * 企业入库
	 * @param dataCompanyWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyUniqueExWarehouseVO> warehouse(@Valid DataCompanyWarehouseCommand dataCompanyWarehouseCommand) {
		Assert.isFalse(dataCompanyWarehouseCommand.allMainFieldEmpty(),"至少填写一个主要字段");
		// 根据参数查询唯一标识
		DataCompanyUniqueExWarehouseQueryCommand uniqueExWarehouseQueryCommand = DataCompanyUniqueExWarehouseQueryCommand.createByWarehouseCommand(dataCompanyWarehouseCommand);
		SingleResponse<DataCompanyUniqueExWarehouseCandidateVO> dataCompanyUniqueExWarehouseCandidateVOSingleResponse = dataCompanyQueryCommandExecutor.uniqueExWarehouse(uniqueExWarehouseQueryCommand);
		DataCompanyUniqueExWarehouseCandidateVO uniqueExWarehouseCandidateVO = dataCompanyUniqueExWarehouseCandidateVOSingleResponse.getData();
		// 没有唯一标识数据，直接企业新增入库
		if (uniqueExWarehouseCandidateVO == null) {
			DataCompanyCreateCommand companyCreateCommand = DataCompanyCreateCommand.createByWarehouseCommand(dataCompanyWarehouseCommand);
			SingleResponse<DataCompanyVO> execute = dataCompanyCreateCommandExecutor.execute(companyCreateCommand);
			// 入库成功，添加md5数据
			DataCompanyVO dataCompanyVO = execute.getData();
			if (dataCompanyVO != null) {
				DataCompanyMd5CreateCommand byDataCompanyVO = DataCompanyMd5CreateCommand.createByDataCompanyVO(dataCompanyVO);
				dataCompanyMd5CreateCommandExecutor.execute(byDataCompanyVO);
			}
			// 新增后重新查询，返回最新数据
			dataCompanyUniqueExWarehouseCandidateVOSingleResponse = dataCompanyQueryCommandExecutor.uniqueExWarehouse(uniqueExWarehouseQueryCommand);
			return SingleResponse.of(dataCompanyUniqueExWarehouseCandidateVOSingleResponse.getData().toDataCompanyUniqueExWarehouseVO());
		}
		// 如果唯一标识数据已存在，继续更新处理
		List<DataCompanyUniqueExWarehouseVO> candidates = uniqueExWarehouseCandidateVO.getCandidates();
		Assert.isTrue(CollectionUtil.isEmpty(candidates),"企业入库出现多个，不能唯一确定一个企业");

		// 仅更新有变化的字段，将相同的字段设置为null不更新
		dataCompanyWarehouseCommand.compareAndSetNullWhenEquals(uniqueExWarehouseCandidateVO);

		// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
		if (dataCompanyWarehouseCommand.allFieldEmpty()) {
			// 如果没有需要更新的字段，则直接返回
			return SingleResponse.of(dataCompanyUniqueExWarehouseCandidateVOSingleResponse.getData().toDataCompanyUniqueExWarehouseVO());
		} else {
			// 更新处理
			DataCompanyUpdateCommand companyUpdateCommand = DataCompanyUpdateCommand.createByWarehouseCommand(
					uniqueExWarehouseCandidateVO.getId(),
					uniqueExWarehouseCandidateVO.getVersion(),
					dataCompanyWarehouseCommand
			);
			SingleResponse<DataCompanyVO> execute = dataCompanyUpdateCommandExecutor.execute(companyUpdateCommand);
			DataCompanyVO dataCompanyVO = execute.getData();
			// 更新完成后，更新md5数据
			if (dataCompanyVO != null) {
				DataCompanyMd5DO dataCompanyMd5DO = iDataCompanyMd5Service.getByCompanyId(dataCompanyVO.getId());
				if (dataCompanyMd5DO == null) {
					// 不存在插入md5数据
					DataCompanyMd5CreateCommand byDataCompanyVO = DataCompanyMd5CreateCommand.createByDataCompanyVO(dataCompanyVO);
					dataCompanyMd5CreateCommandExecutor.execute(byDataCompanyVO);
				}else {
					// 更新md5数据
					DataCompanyMd5UpdateCommand companyMd5UpdateCommand = DataCompanyMd5UpdateCommand.createByDataCompanyVO(dataCompanyMd5DO.getId(), dataCompanyMd5DO.getVersion(), dataCompanyVO);
					dataCompanyMd5UpdateCommandExecutor.execute(companyMd5UpdateCommand);
				}
			}
		}

		// 更新完成后，新增的情况已经在新增逻辑里面直接返回了，查询返回
		dataCompanyUniqueExWarehouseCandidateVOSingleResponse = dataCompanyQueryCommandExecutor.uniqueExWarehouse(uniqueExWarehouseQueryCommand);
		return SingleResponse.of(dataCompanyUniqueExWarehouseCandidateVOSingleResponse.getData().toDataCompanyUniqueExWarehouseVO());
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyGateway
	 */
	@Autowired
	public void setDataCompanyGateway(DataCompanyGateway dataCompanyGateway) {
		this.dataCompanyGateway = dataCompanyGateway;
	}
	@Autowired
	public void setIDataCompanyService(IDataCompanyService iDataCompanyService) {
		this.iDataCompanyService = iDataCompanyService;
	}
	@Autowired
	public void setDataCompanyQueryCommandExecutor(DataCompanyQueryCommandExecutor dataCompanyQueryCommandExecutor) {
		this.dataCompanyQueryCommandExecutor = dataCompanyQueryCommandExecutor;
	}
	@Autowired
	public void setDataCompanyCreateCommandExecutor(DataCompanyCreateCommandExecutor dataCompanyCreateCommandExecutor) {
		this.dataCompanyCreateCommandExecutor = dataCompanyCreateCommandExecutor;
	}
	@Autowired
	public void setDataCompanyMd5CreateCommandExecutor(DataCompanyMd5CreateCommandExecutor dataCompanyMd5CreateCommandExecutor) {
		this.dataCompanyMd5CreateCommandExecutor = dataCompanyMd5CreateCommandExecutor;
	}
	@Autowired
	public void setDataCompanyUpdateCommandExecutor(DataCompanyUpdateCommandExecutor dataCompanyUpdateCommandExecutor) {
		this.dataCompanyUpdateCommandExecutor = dataCompanyUpdateCommandExecutor;
	}
	@Autowired
	public void setDataCompanyMd5UpdateCommandExecutor(DataCompanyMd5UpdateCommandExecutor dataCompanyMd5UpdateCommandExecutor) {
		this.dataCompanyMd5UpdateCommandExecutor = dataCompanyMd5UpdateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyMd5Service(IDataCompanyMd5Service iDataCompanyMd5Service) {
		this.iDataCompanyMd5Service = iDataCompanyMd5Service;
	}
}
