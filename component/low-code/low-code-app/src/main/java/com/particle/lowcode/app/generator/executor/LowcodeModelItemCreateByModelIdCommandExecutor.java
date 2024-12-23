package com.particle.lowcode.app.generator.executor;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.Response;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.dto.BaseDO;
import com.particle.global.mybatis.plus.dto.BaseTreeDO;
import com.particle.global.trans.helper.TransHelper;
import com.particle.lowcode.app.generator.structmapping.LowcodeModelAppStructMapping;
import com.particle.lowcode.app.generator.structmapping.LowcodeModelItemAppStructMapping;
import com.particle.lowcode.client.generator.dto.command.LowcodeModelItemCreateByModelIdCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeModelItemVO;
import com.particle.lowcode.client.generator.dto.data.LowcodeModelVO;
import com.particle.lowcode.domain.generator.*;
import com.particle.lowcode.domain.generator.enums.TableType;
import com.particle.lowcode.domain.generator.gateway.*;
import com.particle.lowcode.infrastructure.generator.dos.LowcodeModelItemDO;
import com.particle.lowcode.infrastructure.generator.service.ILowcodeModelItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
	private ILowcodeModelItemService lowcodeModelItemService;
	private TransHelper transHelper;
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

		// 处理额外配置
		String loadLowcodeModelExtJson = loadLowcodeModelExtJson(lowcodeModel.getId());
		lowcodeModel.changeExtJson(loadLowcodeModelExtJson);
		lowcodeModelGateway.save(lowcodeModel);
		return Response.buildSuccess();
	}

	/**
	 * 加载模型扩展json
	 * @param lowcodeModelId
	 * @return
	 */
	private String loadLowcodeModelExtJson(LowcodeModelId lowcodeModelId) {

		LowcodeModel lowcodeModel = lowcodeModelGateway.getById(lowcodeModelId);

		LowcodeModelVO lowcodeModelVO = LowcodeModelAppStructMapping.instance.toLowcodeModelVO(lowcodeModel);
		// 翻译一下字典
		transHelper.trans(lowcodeModelVO);
		String tableTypeDictValue = lowcodeModelVO.getTableTypeDictValue();
		if (TableType.REL.itemValue().equals(tableTypeDictValue)) {
			List<LowcodeModelItemDO> lowcodeModelItemDOS = lowcodeModelItemService.listByColumn(lowcodeModelVO.getId(), LowcodeModelItemDO::getLowcodeModelId);
			List<LowcodeModelItemVO> modelItemVOS = LowcodeModelItemAppStructMapping.instance.lowcodeModelItemDOsToLowcodeModelItemVOs(lowcodeModelItemDOS);
			if (CollectionUtil.isNotEmpty(modelItemVOS)) {
				modelItemVOS.forEach(item->{
					item.initDesignJsonMap();
					item.clearDesignJson();
				});
			}
			// 	这里自动生成rel的配置，主要是根据模型项的配置
			List<String> exclude = new ArrayList<>();
			exclude.add(BaseDO.PROPERTY_ID);
			exclude.add(BaseDO.PROPERTY_VERSION);
			exclude.add(BaseDO.PROPERTY_CREATE_AT);
			exclude.add(BaseDO.PROPERTY_CREATE_BY);
			exclude.add(BaseDO.PROPERTY_UPDATE_AT);
			exclude.add(BaseDO.PROPERTY_UPDATE_BY);
			exclude.add(BaseDO.PROPERTY_TENANT_ID);
			List<LowcodeModelItemVO> collect = modelItemVOS.stream().filter(item -> !exclude.contains(item.getPropertyName())).collect(Collectors.toList());
			LowcodeModelItemVO oneLowcodeModelItemVO = collect.get(0);
			LowcodeModelItemVO twoLowcodeModelItemVO = collect.get(1);

			LowcodeModelExtJson.Rel relPropertyOne = lowcodeModelItemVOToRel(oneLowcodeModelItemVO);
			LowcodeModelExtJson.Rel relPropertyTwo = lowcodeModelItemVOToRel(twoLowcodeModelItemVO);

			LowcodeModelExtJson lowcodeModelExtJson = LowcodeModelExtJson.create(relPropertyOne, relPropertyTwo);
			return lowcodeModelExtJson.toJsonStr();
		}
		return null;
	}

	/**
	 * 将过滤的单个字段提取出来，并尝试合理化
	 * @param oneLowcodeModelItemVO
	 * @return
	 */
	private LowcodeModelExtJson.Rel lowcodeModelItemVOToRel(LowcodeModelItemVO oneLowcodeModelItemVO) {
		LowcodeModelExtJson.Rel rel = LowcodeModelExtJson.Rel.createEmpty();
		rel.setPropertyName(oneLowcodeModelItemVO.getPropertyName());
		rel.setPropertyNameEnEntity(StrUtil.upperFirst(oneLowcodeModelItemVO.getPropertyName()));
		rel.setCommentSimple(oneLowcodeModelItemVO.getCommentSimple());
		// 一般在rel注释中，注释保持一定的规则，如：角色id，这里替换掉id
		rel.setCommentMain(StrUtil.replaceLast(rel.getCommentSimple(),"id",""));
		// 一般在rel字段命名保持一定的规则，如：roleId，这里替换掉Id
		rel.setPropertyNameMainEnEntityVar(StrUtil.replaceLast(oneLowcodeModelItemVO.getPropertyName(),"Id",""));
		rel.setPropertyNameMainEnEntity(StrUtil.upperFirst(rel.getPropertyNameMainEnEntityVar()));

		return rel;
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
	@Autowired
	public void setLowcodeModelItemService(ILowcodeModelItemService lowcodeModelItemService) {
		this.lowcodeModelItemService = lowcodeModelItemService;
	}
	@Autowired
	public void setTransHelper(TransHelper transHelper) {
		this.transHelper = transHelper;
	}
}
