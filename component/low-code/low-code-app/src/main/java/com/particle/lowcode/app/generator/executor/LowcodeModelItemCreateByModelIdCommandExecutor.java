package com.particle.lowcode.app.generator.executor;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.global.mybatis.plus.dto.BaseDO;
import com.particle.global.mybatis.plus.dto.BaseTreeDO;
import com.particle.lowcode.app.generator.structmapping.LowcodeModelAppStructMapping;
import com.particle.lowcode.client.generator.dto.command.LowcodeModelCreateCommand;
import com.particle.lowcode.client.generator.dto.command.LowcodeModelItemCreateByModelIdCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeModelVO;
import com.particle.lowcode.domain.generator.*;
import com.particle.lowcode.domain.generator.enums.LowcodeModelItemDesignJsonScope;
import com.particle.lowcode.domain.generator.enums.TableType;
import com.particle.lowcode.domain.generator.gateway.*;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 低代码模型项 根据模型id创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-04
 */
@Component
@Validated
public class LowcodeModelItemCreateByModelIdCommandExecutor extends AbstractBaseExecutor {

	private LowcodeModelGateway lowcodeModelGateway;
	private LowcodeDbTableInfoGateway lowcodeDbTableInfoGateway;
	private LowcodeDatasourceGateway lowcodeDatasourceGateway;
	private LowcodeModelItemGateway lowcodeModelItemGateway;
	private LowcodeDictGateway lowcodeDictGateway;

	/**
	 * 执行低代码模型项装载指令
	 * @param idCommand
	 * @return
	 */
	public Response execute(@Valid LowcodeModelItemCreateByModelIdCommand idCommand) {
		LowcodeModel lowcodeModel = lowcodeModelGateway.getById(LowcodeModelId.of(idCommand.getLowcodeModelId()));
		LowcodeDatasource lowcodeDatasource = lowcodeDatasourceGateway.getById(LowcodeDatasourceId.of(idCommand.getLowcodeDatasourceId()));
		Assert.isTrue(StrUtil.isNotEmpty(lowcodeModel.getTableName()),"表名不存在");

		// 保存建表语句
		String createTableSql = lowcodeDbTableInfoGateway.loadCreateTableSql(
				lowcodeModel.getTableName(),
				lowcodeDatasource.getUrl(),
				lowcodeDatasource.getUsername(),
				lowcodeDatasource.getPassword()
		);
		lowcodeModel.changeTableCreateSql(createTableSql);

		lowcodeModelGateway.save(lowcodeModel);

		List<LowcodeModelItem> lowcodeModelItems = lowcodeDbTableInfoGateway.loadByTableName(
				lowcodeModel.getTableName(),
				lowcodeDatasource.getUrl(),
				lowcodeDatasource.getUsername(),
				lowcodeDatasource.getPassword()
		);
		Map<String, Field> BaseTreeDOFieldMap = ReflectUtil.getFieldMap(BaseTreeDO.class);
		Map<String, Field> BaseDOFieldMap = ReflectUtil.getFieldMap(BaseDO.class);
		String tableTypeDictValue = lowcodeDictGateway.getDictValueById(lowcodeModel.getTableTypeDictId());
		TableType tableType = TableType.valueOf(tableTypeDictValue);
		Map<String, Field> fieldMap = tableType == TableType.TREE ? BaseTreeDOFieldMap : BaseDOFieldMap;
		Set<String> ignorePropertyNames = fieldMap.keySet();


		//先删除以前的
		lowcodeModelItemGateway.clearByLowcodeModelId(lowcodeModel.getId());

		lowcodeModelItems.stream().forEach(item -> {
			item.setLowcodeModelId(lowcodeModel.getId().getId());

			item.initDesignJson(ignorePropertyNames);
			lowcodeModelItemGateway.save(item);
		});

		return Response.buildSuccess();
	}

	/**
	 * 注入使用set方法
	 * @param lowcodeModelGateway
	 */
	@Autowired
	public void setLowcodeModelGateway(LowcodeModelGateway lowcodeModelGateway) {
		this.lowcodeModelGateway = lowcodeModelGateway;
	}

	@Autowired
	public void setLowcodeDbTableInfoGateway(LowcodeDbTableInfoGateway lowcodeDbTableInfoGateway) {
		this.lowcodeDbTableInfoGateway = lowcodeDbTableInfoGateway;
	}
	@Autowired
	public void setLowcodeDatasourceGateway(LowcodeDatasourceGateway lowcodeDatasourceGateway) {
		this.lowcodeDatasourceGateway = lowcodeDatasourceGateway;
	}
	@Autowired
	public void setLowcodeModelItemGateway(LowcodeModelItemGateway lowcodeModelItemGateway) {
		this.lowcodeModelItemGateway = lowcodeModelItemGateway;
	}
	@Autowired
	public void setLowcodeDictGateway(LowcodeDictGateway lowcodeDictGateway) {
		this.lowcodeDictGateway = lowcodeDictGateway;
	}
}
