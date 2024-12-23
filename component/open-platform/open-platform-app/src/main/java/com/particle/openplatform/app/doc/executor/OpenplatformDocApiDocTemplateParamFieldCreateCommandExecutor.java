package com.particle.openplatform.app.doc.executor;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.component.light.share.dict.FieldType;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.ExceptionFactory;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.doc.structmapping.OpenplatformDocApiDocTemplateParamFieldAppStructMapping;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocTemplateParamFieldCreateCommand;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocTemplateParamFieldParseAndCreateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocTemplateParamFieldVO;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocTemplateParamField;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocApiDocTemplateParamFieldGateway;
import com.particle.openplatform.domain.gateway.OpenplatformDictGateway;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 开放接口文档模板参数字段 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:48:56
 */
@Component
@Validated
public class OpenplatformDocApiDocTemplateParamFieldCreateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformDocApiDocTemplateParamFieldGateway openplatformDocApiDocTemplateParamFieldGateway;

	private OpenplatformDictGateway openplatformDictGateway;
	/**
	 * 执行开放接口文档模板参数字段添加指令
	 * @param openplatformDocApiDocTemplateParamFieldCreateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDocTemplateParamFieldVO> execute(@Valid OpenplatformDocApiDocTemplateParamFieldCreateCommand openplatformDocApiDocTemplateParamFieldCreateCommand) {
		OpenplatformDocApiDocTemplateParamField openplatformDocApiDocTemplateParamField = createByOpenplatformDocApiDocTemplateParamFieldCreateCommand(openplatformDocApiDocTemplateParamFieldCreateCommand);

		Long dictGroupDictId = openplatformDocApiDocTemplateParamFieldCreateCommand.getDictGroupDictId();
		String dictGroupDictCode = openplatformDocApiDocTemplateParamFieldCreateCommand.getDictGroupDictCode();
		if (dictGroupDictId == null && StrUtil.isNotEmpty(dictGroupDictCode)) {
			Long idByCode = openplatformDictGateway.getIdByCode(dictGroupDictCode);
			if (idByCode != null) {
				openplatformDocApiDocTemplateParamField.changeDictGroupDictId(idByCode);
			}
		}

		openplatformDocApiDocTemplateParamField.setAddControl(openplatformDocApiDocTemplateParamFieldCreateCommand);
		boolean save = openplatformDocApiDocTemplateParamFieldGateway.save(openplatformDocApiDocTemplateParamField);
		if (save) {
			return SingleResponse.of(OpenplatformDocApiDocTemplateParamFieldAppStructMapping.instance.toOpenplatformDocApiDocTemplateParamFieldVO(openplatformDocApiDocTemplateParamField));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 执行开放接口文档参数字段解析并添加指令
	 * @param command
	 * @return
	 */
	public Response parseAndCreate(@Valid OpenplatformDocApiDocTemplateParamFieldParseAndCreateCommand command) {
		JSONObject jsonObject = null;
		JSONArray jsonArray = null;
		// 尝试解析
		try {
			jsonObject = JSONUtil.parseObj(command.getJsonDataStr());
		} catch (Exception e) {
			try {
				jsonArray = JSONUtil.parseArray(command.getJsonDataStr());
			} catch (Exception ex) {
				throw ExceptionFactory.bizException(ErrorCodeGlobalEnum.BAD_REQUEST_ERROR, "json解析失败");
			}
		}

		List openplatformDocApiDocParamFieldList = new ArrayList<>();
		if (jsonObject != null) {
			openplatformDocApiDocParamFieldList.add(jsonObject);
		}
		if (jsonArray != null) {
			// 这里数组只取一条，认为数组下面数据类型是一样的
			openplatformDocApiDocParamFieldList.add(jsonArray.iterator().next());
		}
		// 添加
		if (CollectionUtil.isNotEmpty(openplatformDocApiDocParamFieldList)) {
			add(openplatformDocApiDocParamFieldList, command.getOpenplatformDocApiDocTemplateId(), command.getCategoryDictId(),null);
		}

		return Response.buildSuccess();
	}

	private void add(List openplatformDocApiDocParamFieldList,
					 Long openplatformDocApiDocTemplateId,
					 Long categoryDictId,
					 Long parentId) {
		if (CollectionUtil.isNotEmpty(openplatformDocApiDocParamFieldList)) {
			for (Object object : openplatformDocApiDocParamFieldList) {
				// 只处理 JSONObject、JSONArray
				if (object instanceof JSONObject) {
					JSONObject jsonObject = (JSONObject) object;
					List<ParseResult> parseResultList = parse(jsonObject, openplatformDocApiDocTemplateId, categoryDictId, parentId);
					if (CollectionUtil.isNotEmpty(parseResultList)) {
						for (ParseResult parseResult : parseResultList) {
							OpenplatformDocApiDocTemplateParamFieldCreateCommand openplatformDocApiDocTemplateParamFieldCreateCommand = parseResult.command;
							SingleResponse<OpenplatformDocApiDocTemplateParamFieldVO> execute = execute(openplatformDocApiDocTemplateParamFieldCreateCommand);
							if (execute.isSuccess()) {
								OpenplatformDocApiDocTemplateParamFieldVO openplatformDocApiDocParamFieldVO = execute.getData();
								Long id = openplatformDocApiDocParamFieldVO.getId();
								if (CollectionUtil.isNotEmpty(parseResult.children)) {
									add(parseResult.children,openplatformDocApiDocTemplateId,categoryDictId,id);
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 解析
	 * @param jsonObject
	 * @param openplatformDocApiDocTemplateId
	 * @param categoryDictId
	 * @param parentId
	 * @return
	 */
	private List<ParseResult> parse(JSONObject jsonObject,
																						 Long openplatformDocApiDocTemplateId,
																						 Long categoryDictId,
																						 Long parentId) {
		List<ParseResult> results = new ArrayList<>();
		for (String key : jsonObject.keySet()) {
			Object keyValue = jsonObject.get(key);
			OpenplatformDocApiDocTemplateParamFieldCreateCommand openplatformDocApiDocTemplateParamFieldCreateCommand = new OpenplatformDocApiDocTemplateParamFieldCreateCommand();

			// 字段名
			openplatformDocApiDocTemplateParamFieldCreateCommand.setName(key);
			// 字段类型
			FieldType fieldType = FieldType.parseByValue(keyValue);
			if (fieldType == null) {
				fieldType = FieldType.object;
			}
			openplatformDocApiDocTemplateParamFieldCreateCommand.setTypeDictId(openplatformDictGateway.getDictIdByGroupCodeAndItemValue(fieldType.groupCode(),fieldType.itemValue()));
			if (FieldType.array == fieldType) {
				Collection keyValueCollection = (Collection) keyValue;
				if (!keyValueCollection.isEmpty()) {
					Object nestObj = keyValueCollection.iterator().next();
					FieldType nestFieldType = FieldType.parseByValue(nestObj);
					if (nestFieldType != null) {
						openplatformDocApiDocTemplateParamFieldCreateCommand.setNestTypeDictId(openplatformDictGateway.getDictIdByGroupCodeAndItemValue(nestFieldType.groupCode(),nestFieldType.itemValue()));
					}
				}

			}
			// 是否一定有值
			openplatformDocApiDocTemplateParamFieldCreateCommand.setIsRequired(keyValue != null);
			// 字段说明
			openplatformDocApiDocTemplateParamFieldCreateCommand.setExplanation(key);
			// 开放接口文档id
			openplatformDocApiDocTemplateParamFieldCreateCommand.setOpenplatformDocApiDocTemplateId(openplatformDocApiDocTemplateId);
			// 分类
			openplatformDocApiDocTemplateParamFieldCreateCommand.setCategoryDictId(categoryDictId);
			openplatformDocApiDocTemplateParamFieldCreateCommand.setSeq(10);
			openplatformDocApiDocTemplateParamFieldCreateCommand.setParentId(parentId);

			List<Object> children = new ArrayList<>();
			if (keyValue instanceof JSONObject) {
				children.add(keyValue);
			} else if (keyValue instanceof JSONArray) {
				JSONArray keyValueJsonArray = (JSONArray) keyValue;
				if (!keyValueJsonArray.isEmpty()) {
					children.add(keyValueJsonArray.iterator().next());
				}
			}
			results.add(ParseResult.create(openplatformDocApiDocTemplateParamFieldCreateCommand, children));
		}
		return results;
	}

	private static class ParseResult{
		OpenplatformDocApiDocTemplateParamFieldCreateCommand command;
		List<Object> children;

		public static ParseResult create(OpenplatformDocApiDocTemplateParamFieldCreateCommand command, List<Object> children) {
			ParseResult parseResult = new ParseResult();
			parseResult.command = command;
			parseResult.children = children;
			return parseResult;
		}
	}
	/**
	 * 根据开放接口文档模板参数字段创建指令创建开放接口文档模板参数字段模型
	 * @param openplatformDocApiDocTemplateParamFieldCreateCommand
	 * @return
	 */
	private OpenplatformDocApiDocTemplateParamField createByOpenplatformDocApiDocTemplateParamFieldCreateCommand(OpenplatformDocApiDocTemplateParamFieldCreateCommand openplatformDocApiDocTemplateParamFieldCreateCommand){
		OpenplatformDocApiDocTemplateParamField openplatformDocApiDocTemplateParamField = OpenplatformDocApiDocTemplateParamField.create();
		OpenplatformDocApiDocTemplateParamFieldCreateCommandToOpenplatformDocApiDocTemplateParamFieldMapping.instance.fillOpenplatformDocApiDocTemplateParamFieldByOpenplatformDocApiDocTemplateParamFieldCreateCommand(openplatformDocApiDocTemplateParamField, openplatformDocApiDocTemplateParamFieldCreateCommand);
		return openplatformDocApiDocTemplateParamField;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  OpenplatformDocApiDocTemplateParamFieldCreateCommandToOpenplatformDocApiDocTemplateParamFieldMapping{
		OpenplatformDocApiDocTemplateParamFieldCreateCommandToOpenplatformDocApiDocTemplateParamFieldMapping instance = Mappers.getMapper( OpenplatformDocApiDocTemplateParamFieldCreateCommandToOpenplatformDocApiDocTemplateParamFieldMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformDocApiDocTemplateParamField
		 * @param openplatformDocApiDocTemplateParamFieldCreateCommand
		 */
		void fillOpenplatformDocApiDocTemplateParamFieldByOpenplatformDocApiDocTemplateParamFieldCreateCommand(@MappingTarget OpenplatformDocApiDocTemplateParamField openplatformDocApiDocTemplateParamField, OpenplatformDocApiDocTemplateParamFieldCreateCommand openplatformDocApiDocTemplateParamFieldCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformDocApiDocTemplateParamFieldGateway
	 */
	@Autowired
	public void setOpenplatformDocApiDocTemplateParamFieldGateway(OpenplatformDocApiDocTemplateParamFieldGateway openplatformDocApiDocTemplateParamFieldGateway) {
		this.openplatformDocApiDocTemplateParamFieldGateway = openplatformDocApiDocTemplateParamFieldGateway;
	}
	/**
	 * 注入使用set方法
	 * @param openplatformDictGateway
	 */
	@Autowired
	public void setOpenplatformDictGateway(OpenplatformDictGateway openplatformDictGateway) {
		this.openplatformDictGateway = openplatformDictGateway;
	}
}
