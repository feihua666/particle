package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyMd5CreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyMd5UpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.*;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyExWarehouseCandidateVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyExWarehouseVO;
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
 * 企业标识信息入库 指令执行器
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

	private DataCompanyExWarehouseCommandExecutor dataCompanyExWarehouseCommandExecutor;
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
	public SingleResponse<DataCompanyExWarehouseVO> warehouse(@Valid DataCompanyWarehouseCommand dataCompanyWarehouseCommand) {
		Assert.isFalse(dataCompanyWarehouseCommand.allMainFieldEmpty(),"至少填写一个主要字段");
		// 根据参数查询唯一标识
		DataCompanyExWarehouseQueryCommand exWarehouseQueryCommand = DataCompanyExWarehouseQueryCommand.createByWarehouseCommand(dataCompanyWarehouseCommand);
		SingleResponse<DataCompanyExWarehouseCandidateVO> dataCompanyExWarehouseCandidateVOSingleResponse = dataCompanyExWarehouseCommandExecutor.exWarehouse(exWarehouseQueryCommand);
		DataCompanyExWarehouseCandidateVO exWarehouseCandidateVO = dataCompanyExWarehouseCandidateVOSingleResponse.getData();
		// 没有唯一标识数据，直接企业新增入库
		if (exWarehouseCandidateVO == null) {
			DataCompanyCreateCommand companyCreateCommand = DataCompanyCreateCommand.createByWarehouseCommand(dataCompanyWarehouseCommand);
			SingleResponse<DataCompanyVO> execute = dataCompanyCreateCommandExecutor.execute(companyCreateCommand);
			// 入库成功，添加md5数据
			DataCompanyVO dataCompanyVO = execute.getData();
			if (dataCompanyVO != null) {
				DataCompanyMd5CreateCommand byDataCompanyVO = DataCompanyMd5CreateCommand.createByDataCompanyVO(dataCompanyVO);
				dataCompanyMd5CreateCommandExecutor.execute(byDataCompanyVO);
			}
			// 新增后重新查询，返回最新数据
			dataCompanyExWarehouseCandidateVOSingleResponse = dataCompanyExWarehouseCommandExecutor.exWarehouse(exWarehouseQueryCommand);
			return SingleResponse.of(dataCompanyExWarehouseCandidateVOSingleResponse.getData().toDataCompanyExWarehouseVO());
		}else {
			// 	存在，尝试入库
			List<DataCompanyExWarehouseVO> candidates = exWarehouseCandidateVO.getCandidates();
			Assert.isTrue(CollectionUtil.isEmpty(candidates),"企业入库出现多个，不能唯一确定一个企业");

			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyWarehouseCommand.compareAndSetNullWhenEquals(exWarehouseCandidateVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyService.updateLatestHandleAt(exWarehouseCandidateVO.getId());
				// 如果没有需要更新的字段，则直接返回
				return SingleResponse.of(dataCompanyExWarehouseCandidateVOSingleResponse.getData().toDataCompanyExWarehouseVO());
			} else {
				// 更新处理
				DataCompanyUpdateCommand companyUpdateCommand = DataCompanyUpdateCommand.createByWarehouseCommand(
						exWarehouseCandidateVO.getId(),
						exWarehouseCandidateVO.getVersion(),
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
			dataCompanyExWarehouseCandidateVOSingleResponse = dataCompanyExWarehouseCommandExecutor.exWarehouse(exWarehouseQueryCommand);
			return SingleResponse.of(dataCompanyExWarehouseCandidateVOSingleResponse.getData().toDataCompanyExWarehouseVO());
		}

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
	public void setDataCompanyExWarehouseCommandExecutor(DataCompanyExWarehouseCommandExecutor dataCompanyExWarehouseCommandExecutor) {
		this.dataCompanyExWarehouseCommandExecutor = dataCompanyExWarehouseCommandExecutor;
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
