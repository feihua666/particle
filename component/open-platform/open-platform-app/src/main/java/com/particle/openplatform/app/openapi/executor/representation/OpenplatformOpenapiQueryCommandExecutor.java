package com.particle.openplatform.app.openapi.executor.representation;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.net.url.UrlQuery;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dict.FieldType;
import com.particle.component.light.share.dict.HttpContentType;
import com.particle.component.light.share.dict.HttpMethod;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.biz.BizException;
import com.particle.global.mybatis.plus.crud.ServiceHelperTool;
import com.particle.global.tool.document.excel.ExcelTool;
import com.particle.global.tool.http.OpenApiClientTool;
import com.particle.global.tool.id.SnowflakeIdTool;
import com.particle.global.tool.json.JsonTool;
import com.particle.global.tool.log.TraceTool;
import com.particle.global.tool.str.FilePathTool;
import com.particle.global.tool.str.NetPathTool;
import com.particle.openplatform.app.OpenplatformAppConfiguration;
import com.particle.openplatform.app.doc.executor.representation.OpenplatformDocApiQueryCommandExecutor;
import com.particle.openplatform.app.openapi.executor.OpenplatformOpenapiBatchQueryRecordCreateCommandExecutor;
import com.particle.openplatform.app.openapi.executor.OpenplatformOpenapiBatchQueryRecordDetailCreateCommandExecutor;
import com.particle.openplatform.app.openapi.structmapping.OpenplatformOpenapiAppStructMapping;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocParamFieldBasicVO;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocVO;
import com.particle.openplatform.client.openapi.dto.command.OpenplatformOpenapiBatchQueryRecordCreateCommand;
import com.particle.openplatform.client.openapi.dto.command.OpenplatformOpenapiBatchQueryRecordDetailCreateCommand;
import com.particle.openplatform.client.openapi.dto.command.representation.*;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiBatchQueryRecordDetailVO;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiBatchQueryRecordVO;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiDownloadBatchQueryTemplateVO;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiVO;
import com.particle.openplatform.domain.doc.enums.OpenPlatformDocParamFieldType;
import com.particle.openplatform.domain.enums.OpenPlatformBatchQueryExecuteStatus;
import com.particle.openplatform.domain.gateway.OpenplatformDictGateway;
import com.particle.openplatform.domain.gateway.OpenplatformOauth2RegisteredClientGateway;
import com.particle.openplatform.domain.gateway.OpenplatformOssClientGateway;
import com.particle.openplatform.infrastructure.app.dos.OpenplatformAppDO;
import com.particle.openplatform.infrastructure.app.dos.OpenplatformAppOpenapiDO;
import com.particle.openplatform.infrastructure.app.service.IOpenplatformAppOpenapiService;
import com.particle.openplatform.infrastructure.app.service.IOpenplatformAppService;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDO;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocApiDocService;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocApiService;
import com.particle.openplatform.infrastructure.openapi.dos.OpenplatformOpenapiBatchQueryRecordDO;
import com.particle.openplatform.infrastructure.openapi.dos.OpenplatformOpenapiBatchQueryRecordDetailDO;
import com.particle.openplatform.infrastructure.openapi.dos.OpenplatformOpenapiDO;
import com.particle.openplatform.infrastructure.openapi.service.IOpenplatformOpenapiBatchQueryRecordDetailService;
import com.particle.openplatform.infrastructure.openapi.service.IOpenplatformOpenapiBatchQueryRecordService;
import com.particle.openplatform.infrastructure.openapi.service.IOpenplatformOpenapiService;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;
/**
 * <p>
 * 开放平台开放接口 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-08-08 11:13:24
 */
@Slf4j
@Component
@Validated
public class OpenplatformOpenapiQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private static final String batch_query_export_path = "/batchquery";

	private IOpenplatformOpenapiService iOpenplatformOpenapiService;
	private IOpenplatformAppOpenapiService iOpenplatformAppOpenapiService;
	private IOpenplatformAppService iOpenplatformAppService;

	private IOpenplatformDocApiDocService iOpenplatformDocApiDocService;
	private IOpenplatformDocApiService iOpenplatformDocApiService;

	private OpenplatformOauth2RegisteredClientGateway openplatformOauth2RegisteredClientGateway;
	private OpenplatformDictGateway openplatformDictGateway;

	private OpenplatformDocApiQueryCommandExecutor openplatformDocApiQueryCommandExecutor;

	private OpenplatformOpenapiBatchQueryRecordCreateCommandExecutor openplatformOpenapiBatchQueryRecordCreateCommandExecutor;
	private OpenplatformOpenapiBatchQueryRecordDetailCreateCommandExecutor openplatformOpenapiBatchQueryRecordDetailCreateCommandExecutor;

	private IOpenplatformOpenapiBatchQueryRecordService iOpenplatformOpenapiBatchQueryRecordService;
	private IOpenplatformOpenapiBatchQueryRecordDetailService iOpenplatformOpenapiBatchQueryRecordDetailService;

	@Qualifier(OpenplatformAppConfiguration.openplatformAppBatchQueryExecutor)
	@Autowired
	private ExecutorService openplatformAppBatchQueryExecutor;
	@Autowired
	private OpenplatformOssClientGateway openplatformOssClientGateway;
	/**
	 * 执行 开放平台开放接口 列表查询指令
	 * @param openplatformOpenapiQueryListCommand
	 * @return
	 */
	public MultiResponse<OpenplatformOpenapiVO> execute(@Valid OpenplatformOpenapiQueryListCommand openplatformOpenapiQueryListCommand) {

		List<OpenplatformOpenapiDO> resultOpenplatformOpenapiDOs = Collections.emptyList();
		Long openplatformAppId = openplatformOpenapiQueryListCommand.getOpenplatformAppId();
		List<OpenplatformOpenapiDO> openplatformOpenapiDOs = iOpenplatformOpenapiService.list(openplatformOpenapiQueryListCommand);

		if (openplatformAppId != null) {
			List<OpenplatformAppOpenapiDO> openplatformAppOpenapiDOS = iOpenplatformAppOpenapiService.listByOpenplatformAppId(openplatformAppId);
			if (!openplatformAppOpenapiDOS.isEmpty()) {
				List<Long> openplatformOpenapiIds = openplatformAppOpenapiDOS.stream().map(item -> item.getOpenplatformOpenapiId()).collect(Collectors.toList());
				// 过滤
				resultOpenplatformOpenapiDOs = openplatformOpenapiDOs.stream().filter(item -> openplatformOpenapiIds.contains(item.getId())).collect(Collectors.toList());

			}
		}else {
			resultOpenplatformOpenapiDOs = openplatformOpenapiDOs;
		}
		Long filterOpenplatformAppId = openplatformOpenapiQueryListCommand.getFilterOpenplatformAppId();
		if (filterOpenplatformAppId != null) {
			List<OpenplatformAppOpenapiDO> openplatformAppOpenapiDOS = iOpenplatformAppOpenapiService.listByOpenplatformAppId(filterOpenplatformAppId);
			if (!openplatformAppOpenapiDOS.isEmpty()) {
				List<Long> openplatformOpenapiIds = openplatformAppOpenapiDOS.stream().map(item -> item.getOpenplatformOpenapiId()).collect(Collectors.toList());
				// 过滤时保留组，方便展示上下级
				resultOpenplatformOpenapiDOs = openplatformOpenapiDOs.stream().filter(item -> item.getIsGroup() || openplatformOpenapiIds.contains(item.getId())).collect(Collectors.toList());
			}
			// 如果组下面没有接口了，不需要组
			if (!resultOpenplatformOpenapiDOs.isEmpty()) {
				Map<Long, OpenplatformOpenapiDO> openapiDOMap = resultOpenplatformOpenapiDOs.stream().filter(item -> !item.getIsGroup()).collect(Collectors.toMap(OpenplatformOpenapiDO::getParentId, item -> item,(v1, v2) -> v1));
				resultOpenplatformOpenapiDOs = resultOpenplatformOpenapiDOs.stream().filter(item -> !item.getIsGroup() ||  item.getIsGroup() && openapiDOMap.containsKey(item.getId())).collect(Collectors.toList());
			}
		}else {
			resultOpenplatformOpenapiDOs = openplatformOpenapiDOs;
		}
		List<OpenplatformOpenapiVO> openplatformOpenapiVOs = OpenplatformOpenapiAppStructMapping.instance.openplatformOpenapiDOsToOpenplatformOpenapiVOs(resultOpenplatformOpenapiDOs);
		return MultiResponse.of(openplatformOpenapiVOs);
	}
	/**
	 * 执行 开放平台开放接口 分页查询指令
	 * @param openplatformOpenapiPageQueryCommand
	 * @return
	 */
	public PageResponse<OpenplatformOpenapiVO> execute(@Valid OpenplatformOpenapiPageQueryCommand openplatformOpenapiPageQueryCommand) {
		Page<OpenplatformOpenapiDO> page = iOpenplatformOpenapiService.listPage(openplatformOpenapiPageQueryCommand);
		return OpenplatformOpenapiAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 开放平台开放接口 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiVO> executeDetail(IdCommand detailCommand) {
		OpenplatformOpenapiDO byId = iOpenplatformOpenapiService.getById(detailCommand.getId());
		OpenplatformOpenapiVO openplatformOpenapiVO = OpenplatformOpenapiAppStructMapping.instance.openplatformOpenapiDOToOpenplatformOpenapiVO(byId);
		return SingleResponse.of(openplatformOpenapiVO);
	}
	/**
	 * 执行 开放平台开放接口 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		OpenplatformOpenapiDO byId = iOpenplatformOpenapiService.getById(detailForUpdateCommand.getId());
		OpenplatformOpenapiVO openplatformOpenapiVO = OpenplatformOpenapiAppStructMapping.instance.openplatformOpenapiDOToOpenplatformOpenapiVO(byId);
		return SingleResponse.of(openplatformOpenapiVO);
	}
	/**
	 * 开放接口单次查询
	 * @param openplatformOpenapiSingleQueryCommand
	 * @return
	 */
	public SingleResponse<String> singleQuery(@Valid OpenplatformOpenapiSingleQueryCommand openplatformOpenapiSingleQueryCommand) {

		//  开放接口id
		Long openplatformOpenapiId = openplatformOpenapiSingleQueryCommand.getOpenplatformOpenapiId();

		OpenplatformOpenapiMergedDTO openplatformOpenapiMergedDTO = getOpenplatformOpenapiMergedDTO(openplatformOpenapiId);

		// 请求参数，查询字符串
		Map<String, String> queryStringParam = openplatformOpenapiSingleQueryCommand.getQueryStringParam();
		// 查询字符串，如：name=zhangsan&age=18
		String queryString = null;
		if (queryStringParam != null && !queryStringParam.isEmpty()) {
			queryString = UrlQuery.of(queryStringParam).toString();
		}
		// 请求参数，请求体
		Map<String, Object> bodyParam = openplatformOpenapiSingleQueryCommand.getBodyParam();

		String bodyString = (String) paramConvert(bodyParam, null,
				openplatformOpenapiMergedDTO.getIsRequestHttpContentTypeJson(),
				true,
				openplatformOpenapiMergedDTO.getRequestParamFieldType(),
				openplatformOpenapiMergedDTO.getRequestParamNestFieldType()
		);

		// 	根据开放接口 appid 获取 app 相关信息
		OpenplatformAppDO openplatformAppDO = iOpenplatformAppService.getById(openplatformOpenapiSingleQueryCommand.getOpenplatformAppId());
		String clientId = openplatformAppDO.getAppId();
		// 	根据appid获取密钥
		String clientSecret= openplatformOauth2RegisteredClientGateway.getSecretByAppId(clientId);

		OpenApiClientTool.ClientConfig withSHA256DigestAndNoSign = OpenApiClientTool.ClientConfig.createWithSHA256DigestAndNoSign(clientId, clientSecret);
		withSHA256DigestAndNoSign.setNonce(UUID.fastUUID().toString(true));
		// 发起请求
		String doRequest = doRequest(openplatformOpenapiMergedDTO.getUrl(), bodyString, queryString,
				withSHA256DigestAndNoSign,
				openplatformOpenapiMergedDTO.getRequestType(), openplatformOpenapiMergedDTO.getRequestHttpContentType());


		return SingleResponse.of(doRequest);
	}

	/**
	 * 下载批量查询模板
	 * @param openplatformOpenapiDownloadBatchQueryTemplateCommand
	 * @return
	 */
	public OpenplatformOpenapiDownloadBatchQueryTemplateVO downloadBatchQueryTemplate(@Valid OpenplatformOpenapiDownloadBatchQueryTemplateCommand openplatformOpenapiDownloadBatchQueryTemplateCommand) {
		//  开放接口id
		Long openplatformOpenapiId = openplatformOpenapiDownloadBatchQueryTemplateCommand.getOpenplatformOpenapiId();

		OpenplatformOpenapiMergedDTO openplatformOpenapiMergedDTO = getOpenplatformOpenapiMergedDTO(openplatformOpenapiId);

		// 获取请求参数字段
		List<OpenplatformDocApiDocParamFieldBasicVO> rootRequestParamOpenplatformDocApiDocParamFieldBasicVOS = openplatformOpenapiMergedDTO.getRootRequestParamOpenplatformDocApiDocParamFieldBasicVOS();

		String openplatformDocApiName = openplatformOpenapiMergedDTO.getOpenplatformDocApiDO().getName();

		ExcelWriter writer = ExcelUtil.getWriter(true);
		writer.renameSheet(openplatformDocApiName);

		// 写入请求参数 标题
		writeParam(writer, 0,0, rootRequestParamOpenplatformDocApiDocParamFieldBasicVOS,null,null,true);

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		writer.flush(byteArrayOutputStream);
		byte[] byteArray = byteArrayOutputStream.toByteArray();
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
		return OpenplatformOpenapiDownloadBatchQueryTemplateVO.create(byteArrayInputStream,
				openplatformDocApiName,
				ExcelTool.xlsx_extension_suffix);
	}

	/**
	 * 开放接口批量查询
	 * 思路：1. 建立一个查询记录
	 * 2. 解析上传的文件作为查询明细
	 * 3. 根据查询明细去请求接口，再更新明细数据
	 * @param openplatformOpenapiBatchQueryCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiBatchQueryRecordVO> batchQuery(@Valid OpenplatformOpenapiBatchQueryCommand openplatformOpenapiBatchQueryCommand) {
		MultipartFile file = openplatformOpenapiBatchQueryCommand.getFile();
        ExcelReader excelReader = null;
        try {
            excelReader = ExcelUtil.getReader(file.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<List<Object>> listList = excelReader.read(1);

		Integer totalCount = listList.size();
		Assert.isTrue(totalCount > 0, "未获取到文件中的数据，请检查");

		//  开放接口id
		Long openplatformOpenapiId = openplatformOpenapiBatchQueryCommand.getOpenplatformOpenapiId();

		OpenplatformOpenapiMergedDTO openplatformOpenapiMergedDTO = getOpenplatformOpenapiMergedDTO(openplatformOpenapiId);
		// 获取请求参数字段
		List<OpenplatformDocApiDocParamFieldBasicVO> rootRequestParamOpenplatformDocApiDocParamFieldBasicVOS = openplatformOpenapiMergedDTO.getRootRequestParamOpenplatformDocApiDocParamFieldBasicVOS();

		// 未开始字典id
		Long notStartDictId = openplatformDictGateway.getDictIdByGroupCodeAndItemValue(OpenPlatformBatchQueryExecuteStatus.not_start.groupCode(), OpenPlatformBatchQueryExecuteStatus.not_start.itemValue());

		// 添加批量查询记录
		String uploadFileName = file.getOriginalFilename();
		SingleResponse<OpenplatformOpenapiBatchQueryRecordVO> recordVOSingleResponse = createBatchQueryRecord(openplatformOpenapiBatchQueryCommand,notStartDictId,totalCount, uploadFileName);

		// 数据为空，直接返回，可能是出问题了
		if (recordVOSingleResponse.getData() == null) {
			return recordVOSingleResponse;
		}

		// excel中的字段顺序和定义的字段顺序是一致的
		for (List<Object> objectList : listList) {
			Map<String,Object> mapValue = new HashMap<>();
			// 按定义的字段顺序，取excel中的字段顺序
			for (int i = 0; i < rootRequestParamOpenplatformDocApiDocParamFieldBasicVOS.size(); i++) {
				OpenplatformDocApiDocParamFieldBasicVO openplatformDocApiDocParamFieldBasicVO = rootRequestParamOpenplatformDocApiDocParamFieldBasicVOS.get(i);
				Object objectListValueByIndex = getObjectListValueByIndex(i, objectList);
				mapValue.put(openplatformDocApiDocParamFieldBasicVO.getName(), objectListValueByIndex);
			}
			String bodyParam = (String) paramConvert(mapValue, null,
					openplatformOpenapiMergedDTO.getIsRequestHttpContentTypeJson(), true,
					openplatformOpenapiMergedDTO.getRequestParamFieldType(), openplatformOpenapiMergedDTO.getRequestParamNestFieldType());
			// 	添加批量查询记录明细，准备执行,暂不支持queryString
			createBatchQueryRecordDetail(recordVOSingleResponse.getData().getId(), notStartDictId, bodyParam, null);
		}

		return recordVOSingleResponse;
	}

	/**
	 * 异步执行批量查询和导出
	 * @param openplatformOpenapiBatchQueryRecordId
	 */
	public void asyncBatchQueryAndExport(Long openplatformOpenapiBatchQueryRecordId) {
		// 异步执行查询和导出
		openplatformAppBatchQueryExecutor.execute(()->{
			doBatchQuery(openplatformOpenapiBatchQueryRecordId);
			batchQueryExport(openplatformOpenapiBatchQueryRecordId);

			// 	处理完成后更新状态
			OpenplatformOpenapiBatchQueryRecordDO openapiBatchQueryRecordDOForUpdate = iOpenplatformOpenapiBatchQueryRecordService.getById(openplatformOpenapiBatchQueryRecordId);
			Long finishedDictId = openplatformDictGateway.getDictIdByGroupCodeAndItemValue(OpenPlatformBatchQueryExecuteStatus.finished.groupCode(), OpenPlatformBatchQueryExecuteStatus.finished.itemValue());
			// 修改状态为执行完成
			openapiBatchQueryRecordDOForUpdate.setExecuteStatusDictId(finishedDictId);
			iOpenplatformOpenapiBatchQueryRecordService.updateById(openapiBatchQueryRecordDOForUpdate);

		});
	}
	/**
	 * 执行批量查询
	 * @param openplatformOpenapiBatchQueryRecordId
	 */
	public void doBatchQuery(Long openplatformOpenapiBatchQueryRecordId) {
		OpenplatformOpenapiBatchQueryRecordDO openapiBatchQueryRecordDO = iOpenplatformOpenapiBatchQueryRecordService.getById(openplatformOpenapiBatchQueryRecordId);
		Long runningDictId = openplatformDictGateway.getDictIdByGroupCodeAndItemValue(OpenPlatformBatchQueryExecuteStatus.running.groupCode(), OpenPlatformBatchQueryExecuteStatus.running.itemValue());

		// 修改状态为执行中
		openapiBatchQueryRecordDO.setExecuteStatusDictId(runningDictId);
		// 修改traceid为执行中的traceid
		openapiBatchQueryRecordDO.setTraceId(TraceTool.getTraceId());

		iOpenplatformOpenapiBatchQueryRecordService.updateById(openapiBatchQueryRecordDO);

		OpenplatformOpenapiMergedDTO openplatformOpenapiMergedDTO = getOpenplatformOpenapiMergedDTO(openapiBatchQueryRecordDO.getOpenplatformOpenapiId());


		// 	根据开放接口 appid 获取 app 相关信息
		OpenplatformAppDO openplatformAppDO = iOpenplatformAppService.getById(openapiBatchQueryRecordDO.getOpenplatformAppId());
		String clientId = openplatformAppDO.getAppId();
		// 	根据appid获取密钥
		String clientSecret= openplatformOauth2RegisteredClientGateway.getSecretByAppId(clientId);

		OpenApiClientTool.ClientConfig withSHA256DigestAndNoSign = OpenApiClientTool.ClientConfig.createWithSHA256DigestAndNoSign(clientId, clientSecret);


		Long finishedDictId = openplatformDictGateway.getDictIdByGroupCodeAndItemValue(OpenPlatformBatchQueryExecuteStatus.finished.groupCode(), OpenPlatformBatchQueryExecuteStatus.finished.itemValue());
		// 分页处理
		ServiceHelperTool.<OpenplatformOpenapiBatchQueryRecordDetailDO>pageExecute(page -> {
					LambdaQueryWrapper<OpenplatformOpenapiBatchQueryRecordDetailDO> eq = Wrappers.<OpenplatformOpenapiBatchQueryRecordDetailDO>lambdaQuery()
							.eq(OpenplatformOpenapiBatchQueryRecordDetailDO::getOpenplatformOpenapiBatchQueryRecordId, openplatformOpenapiBatchQueryRecordId);
					return iOpenplatformOpenapiBatchQueryRecordDetailService.page(page,eq);
				},
				page -> {
					page.getRecords().forEach(openplatformOpenapiBatchQueryRecordDetailDO -> {

						// 已经完成的不再处理
						if (finishedDictId.equals(openplatformOpenapiBatchQueryRecordDetailDO.getExecuteStatusDictId())) {
							return;
						}

						// 每一次查询重新设置请求流水号
						withSHA256DigestAndNoSign.setNonce(UUID.fastUUID().toString(true));

						String requestParam = openplatformOpenapiBatchQueryRecordDetailDO.getRequestParam();
						String queryString = openplatformOpenapiBatchQueryRecordDetailDO.getQueryString();

						LocalDateTime now = LocalDateTime.now();

						String requestResult = null;
						Boolean isSuccess = false;
						try {
							requestResult = doRequest(openplatformOpenapiMergedDTO.getUrl(), requestParam, queryString, withSHA256DigestAndNoSign,
									openplatformOpenapiMergedDTO.getRequestType(), openplatformOpenapiMergedDTO.getRequestHttpContentType()
							);
							isSuccess = true;
						} catch (Exception e) {
							isSuccess = false;
							log.error("batchQuery failed，openplatformDocApiName={},requestParam={},queryString={}",
									openplatformOpenapiMergedDTO.getOpenplatformDocApiDO().getName(),requestParam,queryString, e);
						}

						openplatformOpenapiBatchQueryRecordDetailDO.setExecuteStatusDictId(finishedDictId);
						openplatformOpenapiBatchQueryRecordDetailDO.setIsSuccess(isSuccess);
						openplatformOpenapiBatchQueryRecordDetailDO.setRequestNonce(withSHA256DigestAndNoSign.getNonce());
						openplatformOpenapiBatchQueryRecordDetailDO.setQueryAt(now);
						openplatformOpenapiBatchQueryRecordDetailDO.setTraceId(TraceTool.getTraceId());
						openplatformOpenapiBatchQueryRecordDetailDO.setSpanId(TraceTool.getSpanId());
						openplatformOpenapiBatchQueryRecordDetailDO.setResponseResult(requestResult);

						iOpenplatformOpenapiBatchQueryRecordDetailService.updateById(openplatformOpenapiBatchQueryRecordDetailDO);
						if (isSuccess) {
							iOpenplatformOpenapiBatchQueryRecordService.plusForColumnById(openapiBatchQueryRecordDO.getId(),OpenplatformOpenapiBatchQueryRecordDO::getSuccessCount,1);
						}else {
							iOpenplatformOpenapiBatchQueryRecordService.plusForColumnById(openapiBatchQueryRecordDO.getId(),OpenplatformOpenapiBatchQueryRecordDO::getFailCount,1);
						}
					});
				},"batchQuery recordId=" + openplatformOpenapiBatchQueryRecordId);


	}

	/**
	 * 导出查询结果，转出为excel
	 * @param openplatformOpenapiBatchQueryRecordId
	 */
	public void batchQueryExport(Long openplatformOpenapiBatchQueryRecordId) {
		OpenplatformOpenapiBatchQueryRecordDO openapiBatchQueryRecordDO = iOpenplatformOpenapiBatchQueryRecordService.getById(openplatformOpenapiBatchQueryRecordId);
		OpenplatformOpenapiMergedDTO openplatformOpenapiMergedDTO = getOpenplatformOpenapiMergedDTO(openapiBatchQueryRecordDO.getOpenplatformOpenapiId());

		Map<String, List<OpenplatformDocApiDocParamFieldBasicVO>> finalOpenplatformDocApiDocParamFieldBasicVOsMap = openplatformOpenapiMergedDTO.getFinalOpenplatformDocApiDocParamFieldBasicVOsMap();

		// 获取根请求参数字段
		List<OpenplatformDocApiDocParamFieldBasicVO> rootRequestParamOpenplatformDocApiDocParamFieldBasicVOS = openplatformOpenapiMergedDTO.getRootRequestParamOpenplatformDocApiDocParamFieldBasicVOS();
		List<OpenplatformDocApiDocParamFieldBasicVO> rootResponseParamOpenplatformDocApiDocParamFieldBasicVOS = openplatformOpenapiMergedDTO.getRootResponseParamOpenplatformDocApiDocParamFieldBasicVOS();

		// 接口名称
		String openplatformDocApiName = openplatformOpenapiMergedDTO.getOpenplatformDocApiDO().getName();

		Long fieldTypeArrayDictId = openplatformDictGateway.getDictIdByGroupCodeAndItemValue(FieldType.array.groupCode(), FieldType.array.itemValue());
		Long fieldTypeObjectDictId = openplatformDictGateway.getDictIdByGroupCodeAndItemValue(FieldType.object.groupCode(), FieldType.object.itemValue());

		ExcelWriter writer = ExcelUtil.getWriter(true);
		String requestParamSheetName = "请求参数信息";
		writer.renameSheet(requestParamSheetName);
		String sheetNamePrefix = "响应参数信息";
		Map<String,Integer> sheetNameAndRowIndexMap = new HashMap<>();
		// 写入请求参数 标题
		writeParam(writer, 0,0, rootRequestParamOpenplatformDocApiDocParamFieldBasicVOS,null,
				Lists.newArrayList(
						"是否成功",
						"请求时间",
						"请求流水号"),true);

		int startColumnIndex = 0;
		// 查询明细 分页处理
		ServiceHelperTool.<OpenplatformOpenapiBatchQueryRecordDetailDO>pageExecute(page -> {
					LambdaQueryWrapper<OpenplatformOpenapiBatchQueryRecordDetailDO> eq = Wrappers.<OpenplatformOpenapiBatchQueryRecordDetailDO>lambdaQuery().eq(OpenplatformOpenapiBatchQueryRecordDetailDO::getOpenplatformOpenapiBatchQueryRecordId, openplatformOpenapiBatchQueryRecordId);
					return iOpenplatformOpenapiBatchQueryRecordDetailService.page(page,eq);
				},
				page -> {
					page.getRecords().forEach(openplatformOpenapiBatchQueryRecordDetailDO -> {
						// 请求参数
						writeRequestParamValue(writer,
								getAndIncrementRowIndex(sheetNameAndRowIndexMap,requestParamSheetName,1),
								startColumnIndex,
								rootRequestParamOpenplatformDocApiDocParamFieldBasicVOS,
								openplatformOpenapiBatchQueryRecordDetailDO,
								openplatformOpenapiMergedDTO,requestParamSheetName);

						// 响应参数
						writeResponseParamValue(writer,
								rootResponseParamOpenplatformDocApiDocParamFieldBasicVOS,
								openplatformOpenapiBatchQueryRecordDetailDO,
								openplatformOpenapiMergedDTO,
								fieldTypeArrayDictId,
								fieldTypeObjectDictId,
								sheetNamePrefix,
								sheetNameAndRowIndexMap);

					});
				},"batchQuery export recordId=" + openplatformOpenapiBatchQueryRecordId);

		String newFileName = SnowflakeIdTool.nextId() + "_" + openplatformDocApiName + "_批量查询结果" + ExcelTool.xlsx_extension_suffix;
		String objectName =  Optional.ofNullable(batch_query_export_path).orElse("") + "/" + DateUtil.date().toDateStr() + "/" + newFileName;

		String tempFilePath = FilePathTool.concat(FileUtil.getTmpDirPath(), objectName);
		writer.flush(new File(tempFilePath));
		String upload = openplatformOssClientGateway.upload(objectName, tempFilePath, ExcelTool.xlsx_response_content_type);

		// 	处理完成后更新地址
		OpenplatformOpenapiBatchQueryRecordDO openapiBatchQueryRecordDOForUpdate = iOpenplatformOpenapiBatchQueryRecordService.getById(openplatformOpenapiBatchQueryRecordId);
		openapiBatchQueryRecordDOForUpdate.setExportFileUrl(upload);
		iOpenplatformOpenapiBatchQueryRecordService.updateById(openapiBatchQueryRecordDOForUpdate);
	}

	/**
	 * 获取并增长索引
	 * @param sheetNameAndRowIndexMap
	 * @param sheetName
	 * @return
	 */
	private Integer getAndIncrementRowIndex(Map<String,Integer> sheetNameAndRowIndexMap,String sheetName,int startRowIndex){
		Integer rowIndex = sheetNameAndRowIndexMap.get(sheetName);
		if (rowIndex == null) {
			rowIndex = startRowIndex;
		}else {
			rowIndex++;
		}
		sheetNameAndRowIndexMap.put(sheetName,rowIndex);
		return rowIndex;
	}
	/**
	 * 获取并增长索引
	 * @param sheetNameAndRowIndexMap
	 * @param sheetName
	 * @return
	 */
	private Integer getAndDecrementRowIndex(Map<String,Integer> sheetNameAndRowIndexMap,String sheetName){
		Integer rowIndex = sheetNameAndRowIndexMap.get(sheetName);
		if (rowIndex == null) {
			return rowIndex;
		}else {
			rowIndex--;
		}
		sheetNameAndRowIndexMap.put(sheetName,rowIndex);
		return rowIndex;
	}

	/**
	 * 输出响应参数值，入口
	 * @param writer
	 * @param rootResponseParamOpenplatformDocApiDocParamFieldBasicVOS
	 * @param openplatformOpenapiBatchQueryRecordDetailDO
	 * @param openplatformOpenapiMergedDTO
	 * @param fieldTypeArrayDictId
	 * @param fieldTypeObjectDictId
	 * @param sheetNamePrefix
	 * @param sheetNameAndRowIndexMap
	 */
	private void writeResponseParamValue(ExcelWriter writer,
										 List<OpenplatformDocApiDocParamFieldBasicVO> rootResponseParamOpenplatformDocApiDocParamFieldBasicVOS,
										 OpenplatformOpenapiBatchQueryRecordDetailDO openplatformOpenapiBatchQueryRecordDetailDO,
										 OpenplatformOpenapiMergedDTO openplatformOpenapiMergedDTO,
										 Long fieldTypeArrayDictId,
										 Long fieldTypeObjectDictId,
										 String sheetNamePrefix,
										 Map<String,Integer> sheetNameAndRowIndexMap){
		/**
		 * 响应数据里面的格式，按常规的开放接口返回，必须是{@link Response}的子类
		 */
		String responseResult = openplatformOpenapiBatchQueryRecordDetailDO.getResponseResult();
		Object responseResultObj = strToObject(responseResult);

		// 取data节点作为数据
		OpenplatformDocApiDocParamFieldBasicVO currentParentOpenplatformDocApiDocParamFieldBasicVO = null;
		if (CollectionUtil.isNotEmpty(rootResponseParamOpenplatformDocApiDocParamFieldBasicVOS)) {
			OpenplatformDocApiDocParamFieldBasicVO dataOpenplatformDocApiDocParamFieldBasicVO = rootResponseParamOpenplatformDocApiDocParamFieldBasicVOS.stream()
					.filter(item -> item.getName().equals(Response.FIELD_DATA))
					.findFirst().orElse(null);
			currentParentOpenplatformDocApiDocParamFieldBasicVO = dataOpenplatformDocApiDocParamFieldBasicVO;
		}

		doWriteResponseParamValue(writer,
				currentParentOpenplatformDocApiDocParamFieldBasicVO,
				openplatformOpenapiBatchQueryRecordDetailDO,
				openplatformOpenapiMergedDTO,
				fieldTypeArrayDictId,
				fieldTypeObjectDictId,
				sheetNamePrefix,
				null,
				sheetNameAndRowIndexMap,
				responseResultObj,
				Response.FIELD_DATA,true);
	}

	/**
	 * 输出响应参数中的值
	 * @param writer
	 * @param currentParentOpenplatformDocApiDocParamFieldBasicVO
	 * @param openplatformOpenapiBatchQueryRecordDetailDO
	 * @param openplatformOpenapiMergedDTO
	 * @param fieldTypeArrayDictId
	 * @param fieldTypeObjectDictId
	 * @param sheetNamePrefix
	 * @param sheetNameSuffix
	 * @param sheetNameAndRowIndexMap
	 * @param responseResultObj
	 * @param responseResultObjFieldName
	 * @param isHandleChildrenArrayOrObject
	 */
	private void doWriteResponseParamValue(ExcelWriter writer,
										   OpenplatformDocApiDocParamFieldBasicVO currentParentOpenplatformDocApiDocParamFieldBasicVO,
										 OpenplatformOpenapiBatchQueryRecordDetailDO openplatformOpenapiBatchQueryRecordDetailDO,
										 OpenplatformOpenapiMergedDTO openplatformOpenapiMergedDTO,
										 Long fieldTypeArrayDictId,
										 Long fieldTypeObjectDictId,
										 String sheetNamePrefix,
										 String sheetNameSuffix,
										 Map<String,Integer> sheetNameAndRowIndexMap,
										 Object responseResultObj,
										 String responseResultObjFieldName,
										   Boolean isHandleChildrenArrayOrObject){

		// 存放数据
		Object responseResultObjData = null;
		if (responseResultObj != null) {
			if (responseResultObj instanceof Map) {
				responseResultObjData = ((Map<?, ?>) responseResultObj).get(responseResultObjFieldName);
			}else {
				// 不支持
			}
		}
		// 存放数据对应的数组
		List responseResultObjDataList = null;
		if (responseResultObjData != null) {
			if (responseResultObjData instanceof Map) {
				responseResultObjDataList = Lists.newArrayList(responseResultObjData);
			}else if (responseResultObjData instanceof Collection){
				Collection<?> responseResultObjDataCollection = (Collection<?>) responseResultObjData;
				responseResultObjDataList = new ArrayList(responseResultObjDataCollection.size());
				for (Object o : responseResultObjDataCollection) {
					responseResultObjDataList.add(o);
				}
			}else {
				responseResultObjDataList = Lists.newArrayList(responseResultObjData);
			}

		}
		// 类型
		Long dataFieldTypeDictId = null;
		// 嵌套类型
		Long dataNestedFieldTypeDictId = null;
		Long dataOpenplatformDocApiDocParamFieldBasicVOId = null;

		if (currentParentOpenplatformDocApiDocParamFieldBasicVO != null) {

			dataFieldTypeDictId = currentParentOpenplatformDocApiDocParamFieldBasicVO.getTypeDictId();
			dataNestedFieldTypeDictId = currentParentOpenplatformDocApiDocParamFieldBasicVO.getNestTypeDictId();
			dataOpenplatformDocApiDocParamFieldBasicVOId = currentParentOpenplatformDocApiDocParamFieldBasicVO.getId();
		}

		// 获取数据节点的响应参数
		List<OpenplatformDocApiDocParamFieldBasicVO> currentResponseParamOpenplatformDocApiDocParamFieldBasicVOS = null;
		if (dataOpenplatformDocApiDocParamFieldBasicVOId != null) {
			Map<String, List<OpenplatformDocApiDocParamFieldBasicVO>> finalOpenplatformDocApiDocParamFieldBasicVOsMap = openplatformOpenapiMergedDTO.getFinalOpenplatformDocApiDocParamFieldBasicVOsMap();
			List<OpenplatformDocApiDocParamFieldBasicVO> responseParamOpenplatformDocApiDocParamFieldBasicVOS = finalOpenplatformDocApiDocParamFieldBasicVOsMap.get(OpenPlatformDocParamFieldType.response_param.itemValue());
			// 数据节点响应参数
			Long finalDataOpenplatformDocApiDocParamFieldBasicVOId = dataOpenplatformDocApiDocParamFieldBasicVOId;
			currentResponseParamOpenplatformDocApiDocParamFieldBasicVOS = responseParamOpenplatformDocApiDocParamFieldBasicVOS.stream()
					.filter(openplatformDocApiDocParamFieldBasicVO -> finalDataOpenplatformDocApiDocParamFieldBasicVOId.equals(openplatformDocApiDocParamFieldBasicVO.getParentId()))
					.collect(Collectors.toList());
		}
		if (currentResponseParamOpenplatformDocApiDocParamFieldBasicVOS != null) {
			String sheetName = sheetNamePrefix;
			if (StrUtil.isNotEmpty(sheetNameSuffix)) {
				sheetName = sheetName + "-" + sheetNameSuffix;
			}
			// 过滤掉数组和object类型的参数，来作为基本参数，单独建立一个sheet
			List<OpenplatformDocApiDocParamFieldBasicVO> basicResponseParamOpenplatformDocApiDocParamFieldBasicVOS = currentResponseParamOpenplatformDocApiDocParamFieldBasicVOS.stream()
					.filter(openplatformDocApiDocParamFieldBasicVO -> !fieldTypeArrayDictId.equals(openplatformDocApiDocParamFieldBasicVO.getTypeDictId()) && !fieldTypeObjectDictId.equals(openplatformDocApiDocParamFieldBasicVO.getTypeDictId())).collect(Collectors.toList());
			if (!basicResponseParamOpenplatformDocApiDocParamFieldBasicVOS.isEmpty()) {
				Integer rowIndex = getAndIncrementRowIndex(sheetNameAndRowIndexMap, sheetName, 0);
				if (rowIndex == 0) {
					// 写表头
					writer.setSheet(sheetName);
					writeParam(writer, rowIndex, 0, basicResponseParamOpenplatformDocApiDocParamFieldBasicVOS,null,Lists.newArrayList(
							"请求流水号"),false);
				}else {
					getAndDecrementRowIndex(sheetNameAndRowIndexMap, sheetName);
				}
				// 写数据
				writer.setSheet(sheetName);
				// 数组类型
				if (fieldTypeArrayDictId.equals(dataFieldTypeDictId)) {
					// 数组中嵌套对象类型
					if (fieldTypeObjectDictId.equals(dataNestedFieldTypeDictId)) {
						if (responseResultObjDataList != null) {
							for (int i = 0; i < responseResultObjDataList.size(); i++) {
								Map dataMap = (Map) responseResultObjDataList.get(i);
								writeParam(writer, getAndIncrementRowIndex(sheetNameAndRowIndexMap, sheetName, 0), 0, basicResponseParamOpenplatformDocApiDocParamFieldBasicVOS,dataMap,
										Lists.newArrayList(openplatformOpenapiBatchQueryRecordDetailDO.getRequestNonce()),false);
							}

						}
					}
					// 数组中嵌套其它类型
					else {
						if (responseResultObjDataList != null) {
							for (Object o : responseResultObjDataList) {
								Map dataMap = new HashMap(1);
								String firstName = basicResponseParamOpenplatformDocApiDocParamFieldBasicVOS.iterator().next().getName();
								dataMap.put(firstName, o);
								writeParam(writer, getAndIncrementRowIndex(sheetNameAndRowIndexMap, sheetName, 0), 0, basicResponseParamOpenplatformDocApiDocParamFieldBasicVOS,dataMap,
										Lists.newArrayList(openplatformOpenapiBatchQueryRecordDetailDO.getRequestNonce()),false);
							}
						}
					}
				}
				// 对象类型
				else if (fieldTypeObjectDictId.equals(dataFieldTypeDictId)) {
					if (responseResultObjDataList != null) {
						for (Object o : responseResultObjDataList) {
							Map dataMap = (Map) o;
							writeParam(writer, getAndIncrementRowIndex(sheetNameAndRowIndexMap, sheetName, 0), 0, basicResponseParamOpenplatformDocApiDocParamFieldBasicVOS,dataMap,
									Lists.newArrayList(openplatformOpenapiBatchQueryRecordDetailDO.getRequestNonce()),false);
						}
					}
				}
				// 其它类型
				else {
					if (responseResultObjDataList != null) {
						for (Object o : responseResultObjDataList) {
							Map dataMap = new HashMap(1);
							String firstName = basicResponseParamOpenplatformDocApiDocParamFieldBasicVOS.iterator().next().getName();
							dataMap.put(firstName, o);
							writeParam(writer, getAndIncrementRowIndex(sheetNameAndRowIndexMap, sheetName, 0), 0, basicResponseParamOpenplatformDocApiDocParamFieldBasicVOS,dataMap,
									Lists.newArrayList(openplatformOpenapiBatchQueryRecordDetailDO.getRequestNonce()),false);
						}
					}
				}
			}

			// 如何不处理嵌套数组和object类型，直接返回
			if (!isHandleChildrenArrayOrObject) {
				return;
			}
			// 数组类型，单独建立一个sheet
			List<OpenplatformDocApiDocParamFieldBasicVO> arrayResponseParamOpenplatformDocApiDocParamFieldBasicVOS = currentResponseParamOpenplatformDocApiDocParamFieldBasicVOS.stream()
					.filter(openplatformDocApiDocParamFieldBasicVO -> fieldTypeArrayDictId.equals(openplatformDocApiDocParamFieldBasicVO.getTypeDictId())).collect(Collectors.toList());
			if (!arrayResponseParamOpenplatformDocApiDocParamFieldBasicVOS.isEmpty()) {
				for (OpenplatformDocApiDocParamFieldBasicVO arrayResponseParamOpenplatformDocApiDocParamFieldBasicVO : arrayResponseParamOpenplatformDocApiDocParamFieldBasicVOS) {
					doWriteResponseParamValue(writer,
							arrayResponseParamOpenplatformDocApiDocParamFieldBasicVO,
							openplatformOpenapiBatchQueryRecordDetailDO,
							openplatformOpenapiMergedDTO,
							fieldTypeArrayDictId,
							fieldTypeObjectDictId,
							sheetNamePrefix + "-" + arrayResponseParamOpenplatformDocApiDocParamFieldBasicVO.getExplanation(),
							arrayResponseParamOpenplatformDocApiDocParamFieldBasicVO.getExplanation(),
							sheetNameAndRowIndexMap,
							responseResultObjData,
							arrayResponseParamOpenplatformDocApiDocParamFieldBasicVO.getName(),
							false);
				}
			}
			// 对象类型，单独建立一个sheet
			List<OpenplatformDocApiDocParamFieldBasicVO> objectResponseParamOpenplatformDocApiDocParamFieldBasicVOS = currentResponseParamOpenplatformDocApiDocParamFieldBasicVOS.stream()
					.filter(openplatformDocApiDocParamFieldBasicVO -> fieldTypeObjectDictId.equals(openplatformDocApiDocParamFieldBasicVO.getTypeDictId())).collect(Collectors.toList());
			if (!arrayResponseParamOpenplatformDocApiDocParamFieldBasicVOS.isEmpty()) {
				for (OpenplatformDocApiDocParamFieldBasicVO objectResponseParamOpenplatformDocApiDocParamFieldBasicVO : objectResponseParamOpenplatformDocApiDocParamFieldBasicVOS) {
					doWriteResponseParamValue(writer,
							objectResponseParamOpenplatformDocApiDocParamFieldBasicVO,
							openplatformOpenapiBatchQueryRecordDetailDO,
							openplatformOpenapiMergedDTO,
							fieldTypeArrayDictId,
							fieldTypeObjectDictId,
							sheetNamePrefix + "-" + objectResponseParamOpenplatformDocApiDocParamFieldBasicVO.getExplanation(),
							objectResponseParamOpenplatformDocApiDocParamFieldBasicVO.getExplanation(),
							sheetNameAndRowIndexMap,
							responseResultObjData,
							objectResponseParamOpenplatformDocApiDocParamFieldBasicVO.getName(),
							false);
				}
			}
		}
	}
	/**
	 * 将字符串转换为对象，目前只考虑了json格式
	 * @param str
	 * @return
	 */
	private Object strToObject(String str){
		if (StrUtil.isNotEmpty(str)) {
			String strTrim = str.trim();
			// 对象
			if (strTrim.startsWith("{") && strTrim.endsWith("}")) {
				return JSONUtil.toBean(strTrim, Map.class);
			}
			// 数组
			else if (strTrim.startsWith("[") && strTrim.endsWith("]")) {
				return JSONUtil.toList(strTrim, Map.class);
			}else {
				return str;
			}
		}
		return str;
	}
	/**
	 * 写入请求参数
	 * @param writer
	 * @param rowIndex
	 * @param startColumnIndex
	 * @param rootRequestParamOpenplatformDocApiDocParamFieldBasicVOS
	 * @param openplatformOpenapiBatchQueryRecordDetailDO
	 * @param openplatformOpenapiMergedDTO
	 */
	private void writeRequestParamValue(ExcelWriter writer,
										int rowIndex,
										int startColumnIndex,
										List<OpenplatformDocApiDocParamFieldBasicVO> rootRequestParamOpenplatformDocApiDocParamFieldBasicVOS,
										OpenplatformOpenapiBatchQueryRecordDetailDO openplatformOpenapiBatchQueryRecordDetailDO,
										OpenplatformOpenapiMergedDTO openplatformOpenapiMergedDTO,String sheetName){
		Boolean isSuccess = openplatformOpenapiBatchQueryRecordDetailDO.getIsSuccess();
		String isSuccessStr = isSuccess != null && isSuccess ? "查询成功" : "查询失败";

		String requestParam = openplatformOpenapiBatchQueryRecordDetailDO.getRequestParam();
		Object requestParamObj = null;
		if (StrUtil.isNotEmpty(requestParam)) {
			requestParamObj = paramConvert(null,
					requestParam,
					openplatformOpenapiMergedDTO.getIsRequestHttpContentTypeJson(),
					false,
					openplatformOpenapiMergedDTO.getRequestParamFieldType(),
					openplatformOpenapiMergedDTO.getRequestParamNestFieldType()
			);
		}
		// 额外数据注意和表头对应
		List<Object> extValues = Lists.newArrayList(isSuccessStr,
				openplatformOpenapiBatchQueryRecordDetailDO.getQueryAt(),
				openplatformOpenapiBatchQueryRecordDetailDO.getRequestNonce());
		Map<String,Object> dataMap = Collections.emptyMap();
		if (requestParamObj != null) {
			if (requestParamObj instanceof Map) {
				dataMap = (Map) requestParamObj;
			}else if (requestParamObj instanceof List) {
				// 数组只支持第一个参数
				List requestParamObjList = (List) requestParamObj;
				dataMap = ((Map) requestParamObjList.iterator().next());
			}else {

				if (!rootRequestParamOpenplatformDocApiDocParamFieldBasicVOS.isEmpty()) {
					OpenplatformDocApiDocParamFieldBasicVO openplatformDocApiDocParamFieldBasicVO = rootRequestParamOpenplatformDocApiDocParamFieldBasicVOS.iterator().next();

					Map<String, Object> map = new HashMap<>(1);
					map.put(openplatformDocApiDocParamFieldBasicVO.getName(),requestParamObj);
					dataMap = map;
				}
			}
		}
		writer.setSheet(sheetName);
		writeParam(writer, rowIndex,startColumnIndex, rootRequestParamOpenplatformDocApiDocParamFieldBasicVOS, dataMap,extValues,true);

	}

	/**
	 * 写入请求参数
	 * @param writer
	 * @param rootRequestParamOpenplatformDocApiDocParamFieldBasicVOS
	 */
	private void writeParam(ExcelWriter writer,
							int rowIndex,
							int startColumnIndex,
							List<OpenplatformDocApiDocParamFieldBasicVO> rootRequestParamOpenplatformDocApiDocParamFieldBasicVOS,
							Map<String, Object> dataMap,
							List<Object> extValues,Boolean isRequest) {
		int size = rootRequestParamOpenplatformDocApiDocParamFieldBasicVOS.size();
		for (int i = 0; i < size; i++) {
			OpenplatformDocApiDocParamFieldBasicVO openplatformDocApiDocParamFieldBasicVO = rootRequestParamOpenplatformDocApiDocParamFieldBasicVOS.get(i);
			Object value = null;
			if (dataMap != null) {
				value = dataMap.get(openplatformDocApiDocParamFieldBasicVO.getName());
				// 将对象转为json字符串
				if (value instanceof Map || value instanceof Collection) {
					value = JsonTool.toJsonStr(value);
				}
			}else {
				if (isRequest) {
					value = StrUtil.format("{}({})",
							openplatformDocApiDocParamFieldBasicVO.getExplanation(),
							openplatformDocApiDocParamFieldBasicVO.getIsRequired() ? "必填" : "选填"
					);
				}else {
					value = openplatformDocApiDocParamFieldBasicVO.getExplanation();
				}

			}
			writer.writeCellValue(i + startColumnIndex, rowIndex, value);
		}
		if (extValues != null) {

			for (int i = 0; i < extValues.size(); i++) {
				Object extTitle = extValues.get(i);
				writer.writeCellValue(i + startColumnIndex + size, rowIndex, extTitle);
			}
		}
	}
	/**
	 * 请求参数转换，主要完成两种逻辑，因为这里面if else 逻辑较多，为了利用if else 逻辑，避免逻辑不一致
	 * 1、将从excel中获取的值，转换成对应的参数类型字符串
	 * 2、将字符串转解析成对象，比如：json，array，object等
	 * @param mapValue
	 * @param bodyParam
	 * @param isRequestHttpContentTypeJson
	 * @param isExcelToBodyParamStr 为true时返回字符串，为false时返回对象（也有可能是不支持的情况返回字符串）
	 * @param requestParamFieldType
	 * @param requestParamNestFieldType
	 * @return
	 */
	private Object paramConvert(
			Map<String,Object> mapValue,
			String bodyParam,
			boolean isRequestHttpContentTypeJson,
			boolean isExcelToBodyParamStr,
			FieldType requestParamFieldType,FieldType requestParamNestFieldType) {
		String tempBodyParam = null;
		Object tempMapValue = bodyParam;
		// 只有一个值，该值在特定情况下可能会使用
		Object singleValue = null;
		if (mapValue != null && !mapValue.isEmpty()) {
			singleValue = mapValue.entrySet().iterator().next().getValue();
		}
		if (requestParamFieldType == FieldType.object) {
			if (isRequestHttpContentTypeJson) {
				if (isExcelToBodyParamStr) {
					tempBodyParam = this.toJsonStr(mapValue);
				}else {
					tempMapValue = this.toObject(bodyParam, false);
				}
			}else {
				// 	暂不支持
			}
		}else if (requestParamFieldType == FieldType.array) {
			if (requestParamNestFieldType == FieldType.object) {
				if (isRequestHttpContentTypeJson) {
					if (isExcelToBodyParamStr) {
						tempBodyParam = this.toJsonStr(Lists.newArrayList(mapValue));
					}else {
						tempMapValue = this.toObject(bodyParam, true);
					}
				}else {
					// 	暂不支持
				}

			}else {
				if (isRequestHttpContentTypeJson) {
					if (isExcelToBodyParamStr) {
						if (singleValue == null) {
							tempBodyParam = this.toJsonStr(Collections.emptyList());
						}else {
							tempBodyParam = JsonTool.toJsonStr(Lists.newArrayList(singleValue));
						}
					}else {
						tempMapValue = this.toObject(bodyParam, true);
					}


				}else {
					// 	暂不支持
				}

			}
		}else {
			if (isExcelToBodyParamStr) {
				// 当字符串处理
				if (singleValue != null) {
					tempBodyParam = singleValue.toString();
				}
			}else {
				// 当字符串处理
				tempMapValue = bodyParam;
			}

		}

		if (isExcelToBodyParamStr) {
			return tempBodyParam;
		}else {
			return tempMapValue;
		}
	}

	/**
	 * 安全的转为json字符串
	 * @param o
	 * @return
	 */
	private String toJsonStr(Object o) {
		if (o == null) {
			return null;
		}
		return JsonTool.toJsonStr(o);
	}

	/**
	 * 安全的转为对象
	 * @param jsonStr
	 * @param isList
	 * @return
	 */
	private Object toObject(String jsonStr, Boolean isList) {
		if (StrUtil.isEmpty(jsonStr)) {
			return null;
		}
		if (isList) {
			// LinkedHashMap 保证key顺序
			return JSONUtil.toList(jsonStr, LinkedHashMap.class);
		}
		return JSONUtil.toBean(jsonStr, LinkedHashMap.class);
	}

	/**
	 * 发起请求
	 * @param url
	 * @param bodyParam
	 * @param queryString
	 * @param withSHA256DigestAndNoSign
	 * @param requestType
	 * @param requestContentType
	 * @return
	 */
	private String doRequest(String url,
							 String bodyParam,
							 String queryString,
							 OpenApiClientTool.ClientConfig withSHA256DigestAndNoSign,
							 HttpMethod requestType,
							 HttpContentType requestContentType) {
		// 根据请求体类型分别请求
		// post json
		if (HttpMethod.post == requestType) {
			if (HttpContentType.application_json == requestContentType) {

				try {
					String postJsonResultStr = OpenApiClientTool.postJson(url, bodyParam, queryString, withSHA256DigestAndNoSign, null);
					return (postJsonResultStr);
				} catch (IOException | ParseException | URISyntaxException e) {
					throw new RuntimeException(e);
				}
			}
			// post form
			else if (HttpContentType.application_x_www_form_urlencoded == requestContentType) {
				Map<String,String> formParam = null;
				if (StrUtil.isNotEmpty(bodyParam)) {
					formParam = (Map<String,String>)this.toObject(bodyParam, false);
				}
				try {
					String postFormResultStr = OpenApiClientTool.postForm(url, formParam, queryString, withSHA256DigestAndNoSign, null);
					return (postFormResultStr);
				} catch (IOException | ParseException | URISyntaxException e) {
					throw new RuntimeException(e);
				}
			}
			else {
				throw new BizException("不支持的请求内容类型 " + requestContentType.itemValue());
			}

		}else if (HttpMethod.get == requestType) {
			try {
				String getResultStr = OpenApiClientTool.get(url, queryString, withSHA256DigestAndNoSign, null);
				return (getResultStr);
			} catch (IOException | ParseException | URISyntaxException e) {
				throw new RuntimeException(e);
			}
		}else {
			throw new BizException("不支持的请求方法 " + requestType.itemValue());
		}
	}

	/**
	 * 添加批量查询记录
	 * @param openplatformOpenapiBatchQueryCommand
	 * @return
	 */
	private SingleResponse<OpenplatformOpenapiBatchQueryRecordVO> createBatchQueryRecord(OpenplatformOpenapiBatchQueryCommand openplatformOpenapiBatchQueryCommand,Long notStartDictId,Integer totalCount,String uploadFileName)
	{OpenplatformOpenapiBatchQueryRecordCreateCommand openplatformOpenapiBatchQueryRecordCreateCommand = new OpenplatformOpenapiBatchQueryRecordCreateCommand();
		openplatformOpenapiBatchQueryRecordCreateCommand.setOpenplatformAppId(openplatformOpenapiBatchQueryCommand.getOpenplatformAppId());
		openplatformOpenapiBatchQueryRecordCreateCommand.setOpenplatformOpenapiId(openplatformOpenapiBatchQueryCommand.getOpenplatformOpenapiId());
		openplatformOpenapiBatchQueryRecordCreateCommand.setUserId(openplatformOpenapiBatchQueryCommand.getUserId());
		openplatformOpenapiBatchQueryRecordCreateCommand.setTraceId(TraceTool.getTraceId());
		openplatformOpenapiBatchQueryRecordCreateCommand.setUploadFileName(uploadFileName);
		openplatformOpenapiBatchQueryRecordCreateCommand.setCustomerId(null);
		openplatformOpenapiBatchQueryRecordCreateCommand.setExecuteStatusDictId(notStartDictId);

		openplatformOpenapiBatchQueryRecordCreateCommand.setSuccessCount(0);
		openplatformOpenapiBatchQueryRecordCreateCommand.setFailCount(0);
		openplatformOpenapiBatchQueryRecordCreateCommand.setTotalCount(totalCount);

		openplatformOpenapiBatchQueryRecordCreateCommand.setQueryAt(LocalDateTime.now());
		openplatformOpenapiBatchQueryRecordCreateCommand.setRemark(openplatformOpenapiBatchQueryCommand.getRemark());

		return openplatformOpenapiBatchQueryRecordCreateCommandExecutor.execute(openplatformOpenapiBatchQueryRecordCreateCommand);
	}

	/**
	 * 添加批量查询记录明细
	 * @return
	 */
	private SingleResponse<OpenplatformOpenapiBatchQueryRecordDetailVO> createBatchQueryRecordDetail(Long openplatformOpenapiBatchQueryRecordId,
																									 Long notStartDictId,
																									 String requestParam,
																									 String queryString) {
		OpenplatformOpenapiBatchQueryRecordDetailCreateCommand openplatformOpenapiBatchQueryRecordDetailCreateCommand = new OpenplatformOpenapiBatchQueryRecordDetailCreateCommand();
		openplatformOpenapiBatchQueryRecordDetailCreateCommand.setOpenplatformOpenapiBatchQueryRecordId(openplatformOpenapiBatchQueryRecordId);
		openplatformOpenapiBatchQueryRecordDetailCreateCommand.setExecuteStatusDictId(notStartDictId);

		openplatformOpenapiBatchQueryRecordDetailCreateCommand.setRequestParam(requestParam);
		openplatformOpenapiBatchQueryRecordDetailCreateCommand.setQueryString(queryString);

		return openplatformOpenapiBatchQueryRecordDetailCreateCommandExecutor.execute(openplatformOpenapiBatchQueryRecordDetailCreateCommand);
	}
	/**
	 * 获取集合中的下标对应的值
	 * @param index
	 * @param objectList
	 * @return
	 */
	private Object getObjectListValueByIndex(int index,List<Object> objectList) {
		if (objectList == null) {
			return null;
		}
		if (objectList.size() - 1 >= index) {
			return objectList.get(index);
		}
		return null;
	}


	/**
	 * 获取接口定义的信息
	 * @param openplatformOpenapiId 开放接口id
	 * @return
	 */
	private OpenplatformOpenapiMergedDTO getOpenplatformOpenapiMergedDTO(Long openplatformOpenapiId) {


		// 获取开放接口文档主数据
		OpenplatformDocApiDO openplatformDocApiDO = iOpenplatformDocApiService.getByOpenplatformOpenapiId(openplatformOpenapiId);
		// 获取开放接口文档内容数据

		OpenplatformDocApiDocVO finalOpenplatformDocApiDocVO = openplatformDocApiQueryCommandExecutor.getFinalOpenplatformDocApiDocVO(openplatformDocApiDO.getId());

		// 获取请求参数字段
		Map<String, List<OpenplatformDocApiDocParamFieldBasicVO>> finalOpenplatformDocApiDocParamFieldBasicVOsMap = openplatformDocApiQueryCommandExecutor.getFinalOpenplatformDocApiDocParamFieldBasicVOsMap(finalOpenplatformDocApiDocVO.getId(), finalOpenplatformDocApiDocVO.getOpenplatformDocApiDocTemplateId());
		List<OpenplatformDocApiDocParamFieldBasicVO> requestParamOpenplatformDocApiDocParamFieldBasicVOS = finalOpenplatformDocApiDocParamFieldBasicVOsMap.get(OpenPlatformDocParamFieldType.request_param.itemValue());
		// 仅支持根节点请求参数
		List<OpenplatformDocApiDocParamFieldBasicVO> rootRequestParamOpenplatformDocApiDocParamFieldBasicVOS = requestParamOpenplatformDocApiDocParamFieldBasicVOS.stream().filter(openplatformDocApiDocParamFieldBasicVO -> openplatformDocApiDocParamFieldBasicVO.getParentId() == null).collect(Collectors.toList());

		// 获取响应参数字段
		List<OpenplatformDocApiDocParamFieldBasicVO> responseParamOpenplatformDocApiDocParamFieldBasicVOS = finalOpenplatformDocApiDocParamFieldBasicVOsMap.get(OpenPlatformDocParamFieldType.response_param.itemValue());
		// 根节点响应参数
		List<OpenplatformDocApiDocParamFieldBasicVO> rootResponseParamOpenplatformDocApiDocParamFieldBasicVOS = responseParamOpenplatformDocApiDocParamFieldBasicVOS.stream().filter(openplatformDocApiDocParamFieldBasicVO -> openplatformDocApiDocParamFieldBasicVO.getParentId() == null).collect(Collectors.toList());



		// 请求参数默认为对象
		FieldType requestParamFieldType = FieldType.object;
		Long requestParamTypeDictId = finalOpenplatformDocApiDocVO.getRequestParamTypeDictId();
		if (requestParamTypeDictId != null) {
			String requestParamTypeStr = openplatformDictGateway.getDictValueById(requestParamTypeDictId);
			requestParamFieldType = FieldType.parse(requestParamTypeStr);
		}
		// 嵌套请求参数默认为对象
		FieldType requestParamNestFieldType = null;
		Long requestParamNestTypeDictId = finalOpenplatformDocApiDocVO.getRequestParamNestTypeDictId();
		if (requestParamNestTypeDictId != null) {
			String requestParamNestTypeStr = openplatformDictGateway.getDictValueById(requestParamNestTypeDictId);
			requestParamNestFieldType = FieldType.parse(requestParamNestTypeStr);
		}
		Assert.isFalse(requestParamFieldType == FieldType.array && requestParamNestFieldType == FieldType.array, "暂不支持请求参数类型和嵌套请求参数类型同时为 array");

		// 获取请求方法
		Long requestTypeDictId = finalOpenplatformDocApiDocVO.getRequestTypeDictId();
		String requestTypeStr = openplatformDictGateway.getDictValueById(requestTypeDictId);
		HttpMethod requestType = HttpMethod.valueOf(requestTypeStr);
		// 获取请求体类型
		Long requestBodyTypeDictId = finalOpenplatformDocApiDocVO.getRequestBodyTypeDictId();
		String requestContentTypeStr = openplatformDictGateway.getDictValueById(requestBodyTypeDictId);
		HttpContentType requestHttpContentType = HttpContentType.valueOf(requestContentTypeStr);
		boolean isRequestHttpContentTypeJson = HttpContentType.application_json == requestHttpContentType || HttpContentType.multipart_form_data == requestHttpContentType || HttpContentType.application_x_www_form_urlencoded == requestHttpContentType;


		// 获取开放接口文档内容数据中的请求前缀，这一般为如：http://example.com
		String requestUrlPrefix = finalOpenplatformDocApiDocVO.getRequestUrlIntranetPrefix();
		// 获取请求地址，如：/user/list
		String requestUrl = finalOpenplatformDocApiDocVO.getRequestUrl();
		// 完整地址，如：http://example.com/user/list
		String url = NetPathTool.concat(requestUrlPrefix, requestUrl);

		OpenplatformOpenapiMergedDTO openplatformOpenapiMergedDTO = new OpenplatformOpenapiMergedDTO();
		openplatformOpenapiMergedDTO.setRequestType(requestType);
		openplatformOpenapiMergedDTO.setRequestHttpContentType(requestHttpContentType);
		openplatformOpenapiMergedDTO.setIsRequestHttpContentTypeJson(isRequestHttpContentTypeJson);
		openplatformOpenapiMergedDTO.setRequestParamFieldType(requestParamFieldType);
		openplatformOpenapiMergedDTO.setRequestParamNestFieldType(requestParamNestFieldType);
		openplatformOpenapiMergedDTO.setUrl(url);
		openplatformOpenapiMergedDTO.setOpenplatformDocApiDO(openplatformDocApiDO);
		openplatformOpenapiMergedDTO.setFinalOpenplatformDocApiDocParamFieldBasicVOsMap(finalOpenplatformDocApiDocParamFieldBasicVOsMap);
		openplatformOpenapiMergedDTO.setRootRequestParamOpenplatformDocApiDocParamFieldBasicVOS(rootRequestParamOpenplatformDocApiDocParamFieldBasicVOS);
		openplatformOpenapiMergedDTO.setRootResponseParamOpenplatformDocApiDocParamFieldBasicVOS(rootResponseParamOpenplatformDocApiDocParamFieldBasicVOS);

		return openplatformOpenapiMergedDTO;
	}

	/**
	 * 定义一个类，方便复用，存储开放接口相关信息
	 */
	@Data
	private static class OpenplatformOpenapiMergedDTO {

		/**
		 * 请求方法
		 */
		private HttpMethod requestType;
		/**
		 * 请求内容类型
		 */
		private HttpContentType requestHttpContentType;
		/**
		 * 是否请求内容类型为json
		 */
		private Boolean isRequestHttpContentTypeJson;
		/**
		 * 请求参数类型
 		 */
		private FieldType requestParamFieldType;

		/**
		 * 嵌套请求参数类型
		 */
		private FieldType requestParamNestFieldType;

		/**
		 * 完整地址，如：http://example.com/user/list
		 */
		private String url;

		/**
		 * 获取开放接口文档主数据
		 */
		private OpenplatformDocApiDO openplatformDocApiDO;
		/**
		 * 参数列表，key参考：{@link OpenPlatformDocParamFieldType}
		 */
		private Map<String, List<OpenplatformDocApiDocParamFieldBasicVO>> finalOpenplatformDocApiDocParamFieldBasicVOsMap;

		/**
		 * 请求参数根节点们
		 */
		private List<OpenplatformDocApiDocParamFieldBasicVO> rootRequestParamOpenplatformDocApiDocParamFieldBasicVOS;
		/**
		 * 响应参数根节点们
		 */
		private List<OpenplatformDocApiDocParamFieldBasicVO> rootResponseParamOpenplatformDocApiDocParamFieldBasicVOS;
	}


	@Autowired
	public void setIOpenplatformOpenapiService(IOpenplatformOpenapiService iOpenplatformOpenapiService) {
		this.iOpenplatformOpenapiService = iOpenplatformOpenapiService;
	}
	@Autowired
	public void setIOpenplatformAppOpenapiService(IOpenplatformAppOpenapiService iOpenplatformAppOpenapiService) {
		this.iOpenplatformAppOpenapiService = iOpenplatformAppOpenapiService;
	}
	@Autowired
	public void setIOpenplatformAppService(IOpenplatformAppService iOpenplatformAppService) {
		this.iOpenplatformAppService = iOpenplatformAppService;
	}

	@Autowired
	public void setIOpenplatformDocApiDocService(IOpenplatformDocApiDocService iOpenplatformDocApiDocService) {
		this.iOpenplatformDocApiDocService = iOpenplatformDocApiDocService;
	}

	@Autowired
	public void setIOpenplatformDocApiService(IOpenplatformDocApiService iOpenplatformDocApiService) {
		this.iOpenplatformDocApiService = iOpenplatformDocApiService;
	}
	@Autowired
	public void setOpenplatformOauth2RegisteredClientGateway(OpenplatformOauth2RegisteredClientGateway openplatformOauth2RegisteredClientGateway) {
		this.openplatformOauth2RegisteredClientGateway = openplatformOauth2RegisteredClientGateway;
	}
	@Autowired
	public void setOpenplatformDictGateway(OpenplatformDictGateway openplatformDictGateway) {
		this.openplatformDictGateway = openplatformDictGateway;
	}
	@Autowired
	public void setOpenplatformDocApiQueryCommandExecutor(OpenplatformDocApiQueryCommandExecutor openplatformDocApiQueryCommandExecutor) {
		this.openplatformDocApiQueryCommandExecutor = openplatformDocApiQueryCommandExecutor;
	}
	@Autowired
	public void setOpenplatformOpenapiBatchQueryRecordCreateCommandExecutor(OpenplatformOpenapiBatchQueryRecordCreateCommandExecutor openplatformOpenapiBatchQueryRecordCreateCommandExecutor) {
		this.openplatformOpenapiBatchQueryRecordCreateCommandExecutor = openplatformOpenapiBatchQueryRecordCreateCommandExecutor;
	}
	@Autowired
	public void setOpenplatformOpenapiBatchQueryRecordDetailCreateCommandExecutor(OpenplatformOpenapiBatchQueryRecordDetailCreateCommandExecutor openplatformOpenapiBatchQueryRecordDetailCreateCommandExecutor) {
		this.openplatformOpenapiBatchQueryRecordDetailCreateCommandExecutor = openplatformOpenapiBatchQueryRecordDetailCreateCommandExecutor;
	}
	@Autowired
	public void setiOpenplatformOpenapiBatchQueryRecordService(IOpenplatformOpenapiBatchQueryRecordService iOpenplatformOpenapiBatchQueryRecordService) {
		this.iOpenplatformOpenapiBatchQueryRecordService = iOpenplatformOpenapiBatchQueryRecordService;
	}
	@Autowired
	public void setiOpenplatformOpenapiBatchQueryRecordDetailService(IOpenplatformOpenapiBatchQueryRecordDetailService iOpenplatformOpenapiBatchQueryRecordDetailService) {
		this.iOpenplatformOpenapiBatchQueryRecordDetailService = iOpenplatformOpenapiBatchQueryRecordDetailService;
	}
}
