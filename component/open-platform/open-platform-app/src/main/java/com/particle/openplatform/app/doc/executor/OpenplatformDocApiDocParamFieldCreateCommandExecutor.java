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
import com.particle.openplatform.app.doc.structmapping.OpenplatformDocApiDocParamFieldAppStructMapping;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocParamFieldCreateCommand;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocParamFieldParseAndCreateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocParamFieldVO;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocParamField;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocApiDocParamFieldGateway;
import com.particle.openplatform.domain.gateway.OpenplatformDictGateway;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocDO;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocApiDocService;
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
 * 开放接口文档参数字段 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:55
 */
@Component
@Validated
public class OpenplatformDocApiDocParamFieldCreateCommandExecutor extends AbstractBaseExecutor {

    private OpenplatformDocApiDocParamFieldGateway openplatformDocApiDocParamFieldGateway;

    private IOpenplatformDocApiDocService iOpenplatformDocApiDocService;

    private OpenplatformDictGateway openplatformDictGateway;

    /**
     * 执行开放接口文档参数字段添加指令
     *
     * @param openplatformDocApiDocParamFieldCreateCommand
     * @return
     */
    public SingleResponse<OpenplatformDocApiDocParamFieldVO> execute(@Valid OpenplatformDocApiDocParamFieldCreateCommand openplatformDocApiDocParamFieldCreateCommand) {
        OpenplatformDocApiDocParamField openplatformDocApiDocParamField = createByOpenplatformDocApiDocParamFieldCreateCommand(openplatformDocApiDocParamFieldCreateCommand);
        OpenplatformDocApiDocDO openplatformDocApiDocDO = iOpenplatformDocApiDocService.getById(openplatformDocApiDocParamField.getOpenplatformDocApiDocId());
        openplatformDocApiDocParamField.changeOpenplatformDocApiId(openplatformDocApiDocDO.getOpenplatformDocApiId());

        Long dictGroupDictId = openplatformDocApiDocParamFieldCreateCommand.getDictGroupDictId();
        String dictGroupDictCode = openplatformDocApiDocParamFieldCreateCommand.getDictGroupDictCode();
        if (dictGroupDictId == null && StrUtil.isNotEmpty(dictGroupDictCode)) {
            Long idByCode = openplatformDictGateway.getIdByCode(dictGroupDictCode);
            if (idByCode != null) {
                openplatformDocApiDocParamField.changeDictGroupDictId(idByCode);
            }
        }

        openplatformDocApiDocParamField.setAddControl(openplatformDocApiDocParamFieldCreateCommand);
        boolean save = openplatformDocApiDocParamFieldGateway.save(openplatformDocApiDocParamField);
        if (save) {
            return SingleResponse.of(OpenplatformDocApiDocParamFieldAppStructMapping.instance.toOpenplatformDocApiDocParamFieldVO(openplatformDocApiDocParamField));
        }
        return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
    }

    /**
     * 执行开放接口文档参数字段解析并添加指令
     *
     * @param command
     * @return
     */
    public Response parseAndCreate(@Valid OpenplatformDocApiDocParamFieldParseAndCreateCommand command) {
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
            add(openplatformDocApiDocParamFieldList, command.getOpenplatformDocApiDocId(), command.getCategoryDictId(), null);
        }

        return Response.buildSuccess();
    }

    private void add(List openplatformDocApiDocParamFieldList,
                     Long openplatformDocApiDocId,
                     Long categoryDictId,
                     Long parentId) {
        if (CollectionUtil.isNotEmpty(openplatformDocApiDocParamFieldList)) {
            for (Object object : openplatformDocApiDocParamFieldList) {
                // 只处理 JSONObject、JSONArray
                if (object instanceof JSONObject) {
                    JSONObject jsonObject = (JSONObject) object;
                    List<ParseResult> parseResultList = parse(jsonObject, openplatformDocApiDocId, categoryDictId, parentId);
                    if (CollectionUtil.isNotEmpty(parseResultList)) {
                        for (ParseResult parseResult : parseResultList) {
                            OpenplatformDocApiDocParamFieldCreateCommand openplatformDocApiDocParamFieldCreateCommand = parseResult.command;
                            SingleResponse<OpenplatformDocApiDocParamFieldVO> execute = execute(openplatformDocApiDocParamFieldCreateCommand);
                            if (execute.isSuccess()) {
                                OpenplatformDocApiDocParamFieldVO openplatformDocApiDocParamFieldVO = execute.getData();
                                Long id = openplatformDocApiDocParamFieldVO.getId();
                                if (CollectionUtil.isNotEmpty(parseResult.children)) {
                                    add(parseResult.children, openplatformDocApiDocId, categoryDictId, id);
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
     *
     * @param jsonObject
     * @param openplatformDocApiDocId
     * @param categoryDictId
     * @param parentId
     * @return
     */
    private List<ParseResult> parse(JSONObject jsonObject,
                                    Long openplatformDocApiDocId,
                                    Long categoryDictId,
                                    Long parentId) {
        List<ParseResult> results = new ArrayList<>();
        for (String key : jsonObject.keySet()) {
            Object keyValue = jsonObject.get(key);
            OpenplatformDocApiDocParamFieldCreateCommand openplatformDocApiDocParamFieldCreateCommand = new OpenplatformDocApiDocParamFieldCreateCommand();

            // 字段名
            openplatformDocApiDocParamFieldCreateCommand.setName(key);
            // 字段类型
            FieldType fieldType = FieldType.parseByValue(keyValue);
            if (fieldType == null) {
                fieldType = FieldType.object;
            }
            openplatformDocApiDocParamFieldCreateCommand.setTypeDictId(openplatformDictGateway.getDictIdByGroupCodeAndItemValue(fieldType.groupCode(), fieldType.itemValue()));
            if (FieldType.array == fieldType) {
                Collection keyValueCollection = (Collection) keyValue;
                if (!keyValueCollection.isEmpty()) {
                    Object nestObj = keyValueCollection.iterator().next();
                    FieldType nestFieldType = FieldType.parseByValue(nestObj);
                    if (nestFieldType != null) {
                        openplatformDocApiDocParamFieldCreateCommand.setNestTypeDictId(openplatformDictGateway.getDictIdByGroupCodeAndItemValue(nestFieldType.groupCode(), nestFieldType.itemValue()));
                    }
                }

            }
            // 是否一定有值
            openplatformDocApiDocParamFieldCreateCommand.setIsRequired(keyValue != null);
            // 字段说明
            openplatformDocApiDocParamFieldCreateCommand.setExplanation(key);
            // 开放接口文档id
            openplatformDocApiDocParamFieldCreateCommand.setOpenplatformDocApiDocId(openplatformDocApiDocId);
            // 分类
            openplatformDocApiDocParamFieldCreateCommand.setCategoryDictId(categoryDictId);
            openplatformDocApiDocParamFieldCreateCommand.setSeq(10);
            openplatformDocApiDocParamFieldCreateCommand.setParentId(parentId);

            List<Object> children = new ArrayList<>();
            if (keyValue instanceof JSONObject) {
                children.add(keyValue);
            } else if (keyValue instanceof JSONArray) {
                JSONArray keyValueJsonArray = (JSONArray) keyValue;
                if (!keyValueJsonArray.isEmpty()) {
                    children.add(keyValueJsonArray.iterator().next());
                }
            }
            results.add(ParseResult.create(openplatformDocApiDocParamFieldCreateCommand, children));
        }
        return results;
    }

    private static class ParseResult {
        OpenplatformDocApiDocParamFieldCreateCommand command;
        List<Object> children;

        public static ParseResult create(OpenplatformDocApiDocParamFieldCreateCommand command, List<Object> children) {
            ParseResult parseResult = new ParseResult();
            parseResult.command = command;
            parseResult.children = children;
            return parseResult;
        }
    }

    /**
     * 根据开放接口文档参数字段创建指令创建开放接口文档参数字段模型
     *
     * @param openplatformDocApiDocParamFieldCreateCommand
     * @return
     */
    private OpenplatformDocApiDocParamField createByOpenplatformDocApiDocParamFieldCreateCommand(OpenplatformDocApiDocParamFieldCreateCommand openplatformDocApiDocParamFieldCreateCommand) {
        OpenplatformDocApiDocParamField openplatformDocApiDocParamField = OpenplatformDocApiDocParamField.create();
        OpenplatformDocApiDocParamFieldCreateCommandToOpenplatformDocApiDocParamFieldMapping.instance.fillOpenplatformDocApiDocParamFieldByOpenplatformDocApiDocParamFieldCreateCommand(openplatformDocApiDocParamField, openplatformDocApiDocParamFieldCreateCommand);
        return openplatformDocApiDocParamField;
    }

    @Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
    interface OpenplatformDocApiDocParamFieldCreateCommandToOpenplatformDocApiDocParamFieldMapping {
        OpenplatformDocApiDocParamFieldCreateCommandToOpenplatformDocApiDocParamFieldMapping instance = Mappers.getMapper(OpenplatformDocApiDocParamFieldCreateCommandToOpenplatformDocApiDocParamFieldMapping.class);

        /**
         * 同名属性会自动映射，包括枚举
         *
         * @param openplatformDocApiDocParamField
         * @param openplatformDocApiDocParamFieldCreateCommand
         */
        void fillOpenplatformDocApiDocParamFieldByOpenplatformDocApiDocParamFieldCreateCommand(@MappingTarget OpenplatformDocApiDocParamField openplatformDocApiDocParamField, OpenplatformDocApiDocParamFieldCreateCommand openplatformDocApiDocParamFieldCreateCommand);
    }

    /**
     * 注入使用set方法
     *
     * @param openplatformDocApiDocParamFieldGateway
     */
    @Autowired
    public void setOpenplatformDocApiDocParamFieldGateway(OpenplatformDocApiDocParamFieldGateway openplatformDocApiDocParamFieldGateway) {
        this.openplatformDocApiDocParamFieldGateway = openplatformDocApiDocParamFieldGateway;
    }

    @Autowired
    public void setiOpenplatformDocApiDocService(IOpenplatformDocApiDocService iOpenplatformDocApiDocService) {
        this.iOpenplatformDocApiDocService = iOpenplatformDocApiDocService;
    }

    @Autowired
    public void setOpenplatformDictGateway(OpenplatformDictGateway openplatformDictGateway) {
        this.openplatformDictGateway = openplatformDictGateway;
    }
}
