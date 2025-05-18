package com.particle.openplatform.app.doc.executor;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.component.light.share.dict.FieldType;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.doc.structmapping.OpenplatformDocApiAppStructMapping;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiCreateCommand;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocParamFieldCreateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocParamFieldVO;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiVO;
import com.particle.openplatform.domain.doc.OpenplatformDocApi;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDirRel;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDoc;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocApiDirRelGateway;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocApiDocGateway;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocApiGateway;
import com.particle.openplatform.domain.enums.OpenPlatformDocParamFieldType;
import com.particle.openplatform.domain.gateway.OpenplatformDictGateway;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapi;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiId;
import com.particle.openplatform.domain.openapi.gateway.OpenplatformOpenapiGateway;
import jakarta.validation.Valid;
import lombok.Data;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 开放接口文档接口 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:01
 */
@Component
@Validated
public class OpenplatformDocApiCreateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformDocApiGateway openplatformDocApiGateway;
	private OpenplatformDocApiDirRelGateway openplatformDocApiDirRelGateway;
	private OpenplatformOpenapiGateway  openplatformOpenapiGateway;
	private OpenplatformDocApiDocGateway openplatformDocApiDocGateway;
	private OpenplatformDocApiDocParamFieldCreateCommandExecutor docApiDocParamFieldCreateCommandExecutor;
	private OpenplatformDictGateway openplatformDictGateway;
	/**
	 * 执行开放接口文档接口添加指令
	 * @param openplatformDocApiCreateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiVO> execute(@Valid OpenplatformDocApiCreateCommand openplatformDocApiCreateCommand) {
		OpenplatformDocApi openplatformDocApi = createByOpenplatformDocApiCreateCommand(openplatformDocApiCreateCommand);
		openplatformDocApi.setAddControl(openplatformDocApiCreateCommand);
		boolean save = openplatformDocApiGateway.save(openplatformDocApi);
		if (save) {
			// 添加到目录
			Long openplatformDocDirId = openplatformDocApiCreateCommand.getOpenplatformDocDirId();
			if (openplatformDocDirId != null) {
				openplatformDocApiDirRelGateway.save(OpenplatformDocApiDirRel.create(openplatformDocApi.getId().getId(), openplatformDocDirId));
			}
			// 文档内容
			String requestUrl = null;
			Long openplatformOpenapiId = openplatformDocApiCreateCommand.getOpenplatformOpenapiId();
            if (openplatformOpenapiId != null) {
				OpenplatformOpenapi openplatformOpenapi = openplatformOpenapiGateway.getById(OpenplatformOpenapiId.of(openplatformOpenapiId));
				requestUrl = openplatformOpenapi.getUrl();
			}
			// 解析swagger文档
			String swaggerOpenDoc = openplatformDocApiCreateCommand.getSwaggerOpenDoc();
			SwaggerOpenDocParseResult swaggerOpenDocParseResult = null;
            if (StrUtil.isNotEmpty(swaggerOpenDoc)) {
				swaggerOpenDocParseResult = parseSwaggerOpenDoc(swaggerOpenDoc);
                if (requestUrl == null && swaggerOpenDocParseResult.getRequestUrl() != null) {
                    requestUrl = swaggerOpenDocParseResult.getRequestUrl();
                }
			}

			Long openplatformDocApiDocId = null;
            if (StrUtil.isNotEmpty(requestUrl)) {

				OpenplatformDocApiDoc openplatformDocApiDoc = OpenplatformDocApiDoc.create();
				openplatformDocApiDoc.changeOpenplatformDocApiId(openplatformDocApi.getId().getId());
				openplatformDocApiDoc.changeRequestUrl(requestUrl);
				// 参数示例这里都设置为空
				openplatformDocApiDoc.changeRequestParamExample("{}");
				openplatformDocApiDoc.changeResponseParamExample("{}");

				openplatformDocApiDoc.changeOpenplatformDocApiDocTemplateId(openplatformDocApiCreateCommand.getOpenplatformDocApiDocTemplateId());
				openplatformDocApiDocGateway.save(openplatformDocApiDoc);
				openplatformDocApiDocId = openplatformDocApiDoc.getId().getId();
            }
			// 添加swagger解析的请求参数和响应参数
            if (swaggerOpenDocParseResult != null && openplatformDocApiDocId != null) {
				addOpenplatformDocApiDocParamField(swaggerOpenDocParseResult, openplatformDocApiDocId);
            }
			return SingleResponse.of(OpenplatformDocApiAppStructMapping.instance.toOpenplatformDocApiVO(openplatformDocApi));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 添加swagger解析的请求参数和响应参数
	 * @param swaggerOpenDocParseResult
	 * @param openplatformDocApiDocId
	 */
	private void addOpenplatformDocApiDocParamField(SwaggerOpenDocParseResult swaggerOpenDocParseResult,Long openplatformDocApiDocId) {
		List<Property> requestProperties = swaggerOpenDocParseResult.getRequestProperties();
        if (CollectionUtil.isNotEmpty(requestProperties)) {
			Long categoryDictId = openplatformDictGateway.getDictIdByGroupCodeAndItemValue(OpenPlatformDocParamFieldType.Group.open_platform_doc_param_field_type.groupCode(),
					OpenPlatformDocParamFieldType.request_param.itemValue());
			addOpenplatformDocApiDocParamField(requestProperties, openplatformDocApiDocId, null, categoryDictId);
        }
		List<Property> responseProperties = swaggerOpenDocParseResult.getResponseProperties();
		if (CollectionUtil.isNotEmpty(responseProperties)) {

			Long categoryDictId = openplatformDictGateway.getDictIdByGroupCodeAndItemValue(OpenPlatformDocParamFieldType.Group.open_platform_doc_param_field_type.groupCode(),
					OpenPlatformDocParamFieldType.response_param.itemValue());
			addOpenplatformDocApiDocParamField(responseProperties, openplatformDocApiDocId, null, categoryDictId);
		}
	}

	/**
	 * 递归添加接口文档参数字段
	 * @param properties
	 * @param openplatformDocApiDocId
	 * @param openplatformDocApiDocParamFieldParentId
	 * @param categoryDictId
	 */
	private void addOpenplatformDocApiDocParamField(List<Property> properties,
													Long openplatformDocApiDocId,
													Long openplatformDocApiDocParamFieldParentId,
													Long categoryDictId) {
		Integer seq = 100;
		for (Property property : properties) {
			seq = seq + 10;
			SingleResponse<OpenplatformDocApiDocParamFieldVO> openplatformDocApiDocParamFieldVOSingleResponse = addOpenplatformDocApiDocParamField(property,
					openplatformDocApiDocId,
					openplatformDocApiDocParamFieldParentId, categoryDictId, seq);
			List<Property> children = property.getChildren();
			if (CollectionUtil.isNotEmpty(children)) {
				addOpenplatformDocApiDocParamField(children,
						openplatformDocApiDocId,
						openplatformDocApiDocParamFieldVOSingleResponse.getData().getId(),
						categoryDictId);
			}
		}
	}

	/**
	 * 添加接口文档参数字段
	 * @param property
	 * @param openplatformDocApiDocId
	 * @param openplatformDocApiDocParamFieldParentId
	 * @param categoryDictId
	 * @param seq
	 * @return
	 */
	private SingleResponse<OpenplatformDocApiDocParamFieldVO> addOpenplatformDocApiDocParamField(Property property,
													Long openplatformDocApiDocId,
													Long openplatformDocApiDocParamFieldParentId,
													Long categoryDictId,Integer seq) {
		OpenplatformDocApiDocParamFieldCreateCommand openplatformDocApiDocParamFieldCreateCommand = new OpenplatformDocApiDocParamFieldCreateCommand();
		openplatformDocApiDocParamFieldCreateCommand.setName(property.getName());
		Long typeDictId = openplatformDictGateway.getDictIdByGroupCodeAndItemValue(FieldType.Group.field_type.groupCode(),
				FieldType.parseBySwaggerType(property.getType()).itemValue());
		openplatformDocApiDocParamFieldCreateCommand.setTypeDictId(typeDictId);
        if (property.getNestType() != null) {
			Long nestTypeDictId = openplatformDictGateway.getDictIdByGroupCodeAndItemValue(FieldType.Group.field_type.groupCode(),
					FieldType.parseBySwaggerType(property.getNestType()).itemValue());
			openplatformDocApiDocParamFieldCreateCommand.setNestTypeDictId(nestTypeDictId);
        }
		openplatformDocApiDocParamFieldCreateCommand.setIsRequired(property.getIsRequired() == null ? false : property.getIsRequired());
		openplatformDocApiDocParamFieldCreateCommand.setExplanation(property.getExplanation() == null ? "" : property.getExplanation());
		openplatformDocApiDocParamFieldCreateCommand.setOpenplatformDocApiDocId(openplatformDocApiDocId);

		openplatformDocApiDocParamFieldCreateCommand.setCategoryDictId(categoryDictId);
		openplatformDocApiDocParamFieldCreateCommand.setSeq(seq);

		openplatformDocApiDocParamFieldCreateCommand.setParentId(openplatformDocApiDocParamFieldParentId);
		SingleResponse<OpenplatformDocApiDocParamFieldVO> execute = docApiDocParamFieldCreateCommandExecutor.execute(openplatformDocApiDocParamFieldCreateCommand);
		return execute;
	}
	/**
	 * 解析swagger文档，只针对单个接口情况
	 * @param swaggerOpenDoc
	 * @return
	 */
	private SwaggerOpenDocParseResult parseSwaggerOpenDoc(String swaggerOpenDoc) {
		List<Property> requestProperties = new ArrayList<>();
		List<Property> responseProperties = new ArrayList<>();
		JSONObject swaggerOpenDocJsonObject = JSONUtil.parseObj(swaggerOpenDoc);
		JSONObject pathsJsonObject = swaggerOpenDocJsonObject.getJSONObject("paths");
		JSONObject componentsJsonObject = swaggerOpenDocJsonObject.getJSONObject("components");

		String pathsFirstUrl = pathsJsonObject.keySet().iterator().next();

		requestProperties = parsePostRequestBodyRequestProperties(pathsFirstUrl, pathsJsonObject, componentsJsonObject);
		responseProperties = parsePostResponseProperties(pathsFirstUrl, pathsJsonObject, componentsJsonObject);
		return SwaggerOpenDocParseResult.create(pathsFirstUrl, requestProperties, responseProperties);
	}

	/**
	 * 解析一个地址的 post请求 requestBody 的入参属性
	 * @param pathsUrl
	 * @param pathsJsonObject
	 * @param componentsJsonObject
	 * @return
	 */
	private List<Property> parsePostRequestBodyRequestProperties(String pathsUrl,JSONObject pathsJsonObject,JSONObject componentsJsonObject) {
		JSONObject pathsFirstUrlJsonObject = pathsJsonObject.getJSONObject(pathsUrl);
		JSONObject postJsonObject = pathsFirstUrlJsonObject.getJSONObject("post");
        if (postJsonObject == null) {
            return Collections.emptyList();
        }
		// 接口名称
		String summary = postJsonObject.getStr("summary");
		// 这里只考虑了post requestBody
		JSONObject requestBodyJsonObject = postJsonObject.getJSONObject("requestBody");
        if (requestBodyJsonObject == null) {
            return Collections.emptyList();
        }
		JSONObject contentJsonObject = requestBodyJsonObject.getJSONObject("content");
		JSONObject schemaJsonObject = contentJsonObject.getJSONObject("application/json").getJSONObject("schema");
		JSONObject propertiesJsonObject = schemaJsonObject.getJSONObject("properties");

		List<Property> requestProperties = new ArrayList<>();
		if (propertiesJsonObject != null) {
			// 将多个参数合并到一起
			for (String key : propertiesJsonObject.keySet()) {
				JSONObject jsonObject = propertiesJsonObject.getJSONObject(key);
				String $ref = jsonObject.getStr("$ref");
				if ($ref != null) {
					JSONObject componentJsonObject = parseComponentJsonObject($ref, componentsJsonObject);
					List<Property> properties = parseComponentProperties(componentJsonObject, componentsJsonObject);
					requestProperties.addAll(properties);
				}
			}
		}else {
			String $ref = schemaJsonObject.getStr("$ref");
			if ($ref != null) {
				JSONObject componentJsonObject = parseComponentJsonObject($ref, componentsJsonObject);
				List<Property> properties = parseComponentProperties(componentJsonObject, componentsJsonObject);
				requestProperties.addAll(properties);
			}
		}


		return requestProperties;
	}
	/**
	 * 解析一个地址的 post请求 responses 200 的出参属性
	 * @param pathsUrl
	 * @param pathsJsonObject
	 * @param componentsJsonObject
	 * @return
	 */
	private List<Property> parsePostResponseProperties(String pathsUrl,JSONObject pathsJsonObject,JSONObject componentsJsonObject) {
		JSONObject pathsFirstUrlJsonObject = pathsJsonObject.getJSONObject(pathsUrl);
		JSONObject postJsonObject = pathsFirstUrlJsonObject.getJSONObject("post");
		if (postJsonObject == null) {
			return Collections.emptyList();
		}
		// 接口名称
		String summary = postJsonObject.getStr("summary");
		// 这里只考虑了post requestBody
		JSONObject responsesJsonObject = postJsonObject.getJSONObject("responses");
		if (responsesJsonObject == null) {
			return Collections.emptyList();
		}
		JSONObject status200JsonObject = responsesJsonObject.getJSONObject("200");
		JSONObject schemaJsonObject = status200JsonObject.getJSONObject("content").getJSONObject("*/*").getJSONObject("schema");

		List<Property> responseProperties = new ArrayList<>();
		String $ref = schemaJsonObject.getStr("$ref");
		if ($ref != null) {
			JSONObject componentJsonObject = parseComponentJsonObject($ref, componentsJsonObject);
			List<Property> properties = parseComponentProperties(componentJsonObject, componentsJsonObject);
			responseProperties.addAll(properties);
		}
		return responseProperties;
	}

	/**
	 * 解析对象属性信息
	 * @param componentJsonObject
	 * @param componentsJsonObject
	 * @return
	 */
	private List<Property> parseComponentProperties(JSONObject componentJsonObject,JSONObject componentsJsonObject) {
		List<Property> properties = parseComponentProperties(componentJsonObject, componentsJsonObject,new ArrayList<>());
		return properties;
	}
	/**
	 * 解析对象属性信息，refs用来防止循环引用
	 * @param componentJsonObject
	 * @param componentsJsonObject
	 * @param refs 用来控制在递归解析时避免循环引用
	 */
	private List<Property> parseComponentProperties(JSONObject componentJsonObject,JSONObject componentsJsonObject,List<String> refs) {
		JSONArray requiredJsonArray = componentJsonObject.getJSONArray("required");
		JSONObject propertiesJsonObject = componentJsonObject.getJSONObject("properties");
		if (requiredJsonArray == null) {
			requiredJsonArray = new JSONArray(0);
        }

		List<Property> properties = new ArrayList<>();
		for (String name : propertiesJsonObject.keySet()) {
			JSONObject propertyJsonObject = propertiesJsonObject.getJSONObject(name);
			String $ref = propertyJsonObject.getStr("$ref");
			if ($ref == null) {
				JSONObject itemsJsonObject = propertyJsonObject.getJSONObject("items");
                if (itemsJsonObject != null) {
                    $ref = itemsJsonObject.getStr("$ref");
                }
			}
			JSONObject $refComponentJsonObject = null;
			if ($ref != null) {
				$refComponentJsonObject = parseComponentJsonObject($ref, componentsJsonObject);
			}
			String type = propertyJsonObject.getStr("type");
			// 根据数据看，没有类型就是一个object类型
			if (type == null) {
				type = "object";
			}
			String nestType = null;
			if (nestType == null && $refComponentJsonObject != null && "array".equals(type)) {
				nestType = $refComponentJsonObject.getStr("type");
			}
			Boolean isRequired = requiredJsonArray.contains(name);
			String explanation = propertyJsonObject.getStr("description");
			if (explanation == null && $refComponentJsonObject != null) {
				explanation = $refComponentJsonObject.getStr("description");
			}
			if (explanation == null) {
				explanation = name;
			}
			Property property = Property.create(name, type, nestType, isRequired, explanation);
			if ($refComponentJsonObject != null) {
				String final$ref = $ref;
				refs.add(final$ref);
				long refCount = refs.stream().filter(ref -> ref.equals(final$ref)).count();
				if (refCount <= 2) {
					property.setChildren(parseComponentProperties($refComponentJsonObject, componentsJsonObject,refs));
				}
			}
			properties.add(property);
		}
		return properties;
	}

	/**
	 * 解析对象信息
	 * @param $ref 如：#/components/schemas/XxxxVO
	 * @param $ref 如：#/components/schemas/XxxxCommand
	 * @param componentsJsonObject
	 * @return
	 */
	private JSONObject parseComponentJsonObject(String $ref,JSONObject componentsJsonObject) {
		String componentKey = $ref.substring($ref.lastIndexOf("/") + 1);
		JSONObject componentJsonObject = componentsJsonObject.getJSONObject(componentKey);
		return componentJsonObject;
	}

	/**
	 * 根据开放接口文档接口创建指令创建开放接口文档接口模型
	 * @param openplatformDocApiCreateCommand
	 * @return
	 */
	private OpenplatformDocApi createByOpenplatformDocApiCreateCommand(OpenplatformDocApiCreateCommand openplatformDocApiCreateCommand){
		OpenplatformDocApi openplatformDocApi = OpenplatformDocApi.create();
		OpenplatformDocApiCreateCommandToOpenplatformDocApiMapping.instance.fillOpenplatformDocApiByOpenplatformDocApiCreateCommand(openplatformDocApi, openplatformDocApiCreateCommand);
		return openplatformDocApi;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  OpenplatformDocApiCreateCommandToOpenplatformDocApiMapping{
		OpenplatformDocApiCreateCommandToOpenplatformDocApiMapping instance = Mappers.getMapper( OpenplatformDocApiCreateCommandToOpenplatformDocApiMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformDocApi
		 * @param openplatformDocApiCreateCommand
		 */
		void fillOpenplatformDocApiByOpenplatformDocApiCreateCommand(@MappingTarget OpenplatformDocApi openplatformDocApi, OpenplatformDocApiCreateCommand openplatformDocApiCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformDocApiGateway
	 */
	@Autowired
	public void setOpenplatformDocApiGateway(OpenplatformDocApiGateway openplatformDocApiGateway) {
		this.openplatformDocApiGateway = openplatformDocApiGateway;
	}

	@Autowired
	public void setOpenplatformDocApiDirRelGateway(OpenplatformDocApiDirRelGateway openplatformDocApiDirRelGateway) {
		this.openplatformDocApiDirRelGateway = openplatformDocApiDirRelGateway;
	}
	@Autowired
	public void setOpenplatformOpenapiGateway(OpenplatformOpenapiGateway openplatformOpenapiGateway) {
		this.openplatformOpenapiGateway = openplatformOpenapiGateway;
	}
	@Autowired
	public void setOpenplatformDocApiDocGateway(OpenplatformDocApiDocGateway openplatformDocApiDocGateway) {
		this.openplatformDocApiDocGateway = openplatformDocApiDocGateway;
	}
	@Autowired
	public void setDocApiDocParamFieldCreateCommandExecutor(OpenplatformDocApiDocParamFieldCreateCommandExecutor docApiDocParamFieldCreateCommandExecutor) {
		this.docApiDocParamFieldCreateCommandExecutor = docApiDocParamFieldCreateCommandExecutor;
	}
	@Autowired
	public void setOpenplatformDictGateway(OpenplatformDictGateway openplatformDictGateway) {
		this.openplatformDictGateway = openplatformDictGateway;
	}

	/**
	 * swagger解析结果
	 */
	@Data
	private static class SwaggerOpenDocParseResult {
		/**
		 * 请求地址
		 */
		private String requestUrl;
		/**
		 * 请求参数
		 */
		private List<Property> requestProperties;
		/**
		 * 响应参数
		 */
		private List<Property> responseProperties;
		private static SwaggerOpenDocParseResult create(String requestUrl, List<Property> requestProperties,List<Property> responseProperties) {
			SwaggerOpenDocParseResult swaggerOpenDocParseResult = new SwaggerOpenDocParseResult();
			swaggerOpenDocParseResult.requestUrl = requestUrl;
			swaggerOpenDocParseResult.requestProperties = requestProperties;
			swaggerOpenDocParseResult.responseProperties = responseProperties;
			return swaggerOpenDocParseResult;
		}
	}

	/**
	 * 属性
	 */
	@Data
	private static class Property{

		/**
		 * 字段名称
		 */
		private String name;

		/**
		 * 字段类型，如：string、array
		 */
		private String type;

		/**
		 * 嵌套字段类型，一般用于字段类型为array时里面的类型，如：string、object
		 */
		private String nestType;

		/**
		 * 是否一定有值
		 */
		private Boolean isRequired;

		/**
		 * 字段说明
		 */
		private String explanation;

		/**
		 * 子字段
		 */
		private List<Property> children;

		public  static Property create(String name, String type, String nestType, Boolean isRequired, String explanation){
			Property property = new Property();
			property.name = name;
			property.type = type;
			property.nestType = nestType;
			property.isRequired = isRequired;
			property.explanation = explanation;
			return property;
		}

	}
}
