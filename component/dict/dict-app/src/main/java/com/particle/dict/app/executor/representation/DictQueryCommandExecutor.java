package com.particle.dict.app.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dict.app.structmapping.DictAppStructMapping;
import com.particle.dict.client.dto.command.representation.DictPageQueryCommand;
import com.particle.dict.client.dto.command.representation.DictQueryListCommand;
import com.particle.dict.client.dto.data.DictVO;
import com.particle.dict.infrastructure.dos.DictDO;
import com.particle.dict.infrastructure.service.IDictService;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 字典 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2022-07-19
 */
@Component
@Validated
public class DictQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDictService iDictService;

	/**
	 * 执行 字典 列表查询指令
	 * @param dictQueryListCommand
	 * @return
	 */
	public MultiResponse<DictVO> execute(@Valid DictQueryListCommand dictQueryListCommand) {
		List<DictDO> dictDO = iDictService.list(dictQueryListCommand);
		List<DictVO> dictVOs = DictAppStructMapping.instance.dictDOsToDictVOs(dictDO);
		return MultiResponse.of(dictVOs);
	}
	/**
	 * 执行 字典 分页查询指令
	 * @param dictPageQueryCommand
	 * @return
	 */
	public PageResponse<DictVO> execute(@Valid DictPageQueryCommand dictPageQueryCommand) {
		Page<DictDO> page = iDictService.listPage(dictPageQueryCommand);
		return DictAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 字典 展示用详情查询指令
	 * @param dictQueryDetailCommand
	 * @return
	 */
	public SingleResponse<DictVO> executeDetail(IdCommand dictQueryDetailCommand) {
		DictDO byId = iDictService.getById(dictQueryDetailCommand.getId());
		DictVO dictVO = DictAppStructMapping.instance.dictDOToDictVO(byId);
		return SingleResponse.of(dictVO);
	}
	/**
	 * 执行 字典 更新用详情查询指令
	 * @param dictQueryDetailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DictVO> executeDetailForUpdate(IdCommand dictQueryDetailForUpdateCommand) {
		DictDO byId = iDictService.getById(dictQueryDetailForUpdateCommand.getId());
		DictVO dictVO = DictAppStructMapping.instance.dictDOToDictVO(byId);
		return SingleResponse.of(dictVO);
	}

	@Autowired
	public void setIDictService(IDictService iDictService) {
		this.iDictService = iDictService;
	}
}
