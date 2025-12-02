package com.particle.data.adapter.dynamicdata.web.admin;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.*;
import cn.hutool.extra.servlet.JakartaServletUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import cn.hutool.poi.excel.WorkbookUtil;
import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.data.app.dynamicdata.executor.DynamicDataIndicatorCreateCommandExecutor;
import com.particle.data.client.dynamicdata.api.IDynamicDataIndicatorCategoryApplicationService;
import com.particle.data.client.dynamicdata.api.IDynamicDataIndicatorCategoryUploadRecordApplicationService;
import com.particle.data.client.dynamicdata.api.representation.IDynamicDataIndicatorCategoryRepresentationApplicationService;
import com.particle.data.client.dynamicdata.api.representation.IDynamicDataIndicatorRepresentationApplicationService;
import com.particle.data.client.dynamicdata.dto.command.*;
import com.particle.data.client.dynamicdata.dto.command.representation.*;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataIndicatorCategoryUploadRecordVO;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataIndicatorCategoryVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataIndicatorVO;
import com.particle.global.document.template.GlobalDocumentTemplate;
import com.particle.global.document.template.GlobalDocumentTemplateService;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.ExceptionFactory;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.global.light.share.mybatis.anno.In;
import com.particle.global.mybatis.plus.table.DynamicDO;
import com.particle.global.oss.service.GlobalOssClientService;
import com.particle.global.tool.document.excel.CustomExcelWriter;
import com.particle.global.tool.document.excel.CustomStyleSet;
import com.particle.global.tool.document.excel.ExcelTool;
import com.particle.global.tool.file.FileTool;
import com.particle.global.tool.str.FilePathTool;
import com.particle.global.tool.str.NetPathTool;
import com.particle.global.trans.helper.TransHelper;
import com.particle.global.trans.helper.TransTool;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.dataconstraint.DataConstraintContext;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.Response;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 动态数据指标分类后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:58
 */
@Tag(name = "动态数据指标分类pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/dynamic_data_indicator_category")
public class DynamicDataIndicatorCategoryAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDynamicDataIndicatorCategoryApplicationService iDynamicDataIndicatorCategoryApplicationService;
    @Autowired
    private IDynamicDataIndicatorCategoryRepresentationApplicationService iDynamicDataIndicatorCategoryRepresentationApplicationService;
    @Autowired
    private IDynamicDataIndicatorRepresentationApplicationService iDynamicDataIndicatorRepresentationApplicationService;
    @Autowired
    private GlobalDocumentTemplateService globalDocumentTemplateService;
    @Autowired
    private GlobalOssClientService globalOssClientService;
    @Autowired
    private IDynamicDataIndicatorCategoryUploadRecordApplicationService iDynamicDataIndicatorCategoryUploadRecordApplicationService;


    @PreAuthorize("hasAuthority('admin:web:dynamicDataIndicatorCategory:create')")
    @Operation(summary = "添加动态数据指标分类")
    @PostMapping("/create")
    @OpLog(name = "添加动态数据指标分类",module = OpLogConstants.Module.data,type = OpLogConstants.Type.create)
    public SingleResponse<DynamicDataIndicatorCategoryVO> create(@RequestBody DynamicDataIndicatorCategoryCreateCommand dynamicDataIndicatorCategoryCreateCommand){
        return iDynamicDataIndicatorCategoryApplicationService.create(dynamicDataIndicatorCategoryCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dynamicDataIndicatorCategory:delete')")
    @Operation(summary = "删除动态数据指标分类")
    @DeleteMapping("/delete")
    @OpLog(name = "删除动态数据指标分类",module = OpLogConstants.Module.data,type = OpLogConstants.Type.delete)
    public SingleResponse<DynamicDataIndicatorCategoryVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDynamicDataIndicatorCategoryApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dynamicDataIndicatorCategory:update')")
    @Operation(summary = "更新动态数据指标分类")
    @PutMapping("/update")
    @OpLog(name = "更新动态数据指标分类",module = OpLogConstants.Module.data,type = OpLogConstants.Type.update)
    public SingleResponse<DynamicDataIndicatorCategoryVO> update(@RequestBody DynamicDataIndicatorCategoryUpdateCommand dynamicDataIndicatorCategoryUpdateCommand){
        dynamicDataIndicatorCategoryUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDynamicDataIndicatorCategoryApplicationService.update(dynamicDataIndicatorCategoryUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dynamicDataIndicatorCategory:update')")
    @Operation(summary = "动态数据指标分类更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DynamicDataIndicatorCategoryVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDynamicDataIndicatorCategoryRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dynamicDataIndicatorCategory:detail')")
    @Operation(summary = "动态数据指标分类详情展示")
    @GetMapping("/detail")
    public SingleResponse<DynamicDataIndicatorCategoryVO> queryDetail(IdCommand detailCommand){
        return iDynamicDataIndicatorCategoryRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dynamicDataIndicatorCategory:queryList')")
    @Operation(summary = "列表查询动态数据指标分类")
    @GetMapping("/list")
    public MultiResponse<DynamicDataIndicatorCategoryVO> queryList(DynamicDataIndicatorCategoryQueryListCommand dynamicDataIndicatorCategoryQueryListCommand){
        dynamicDataIndicatorCategoryQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDynamicDataIndicatorCategoryRepresentationApplicationService.queryList(dynamicDataIndicatorCategoryQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dynamicDataIndicatorCategory:pageQuery')")
    @Operation(summary = "分页查询动态数据指标分类")
    @GetMapping("/page")
    public PageResponse<DynamicDataIndicatorCategoryVO> pageQueryList(DynamicDataIndicatorCategoryPageQueryCommand dynamicDataIndicatorCategoryPageQueryCommand){
        dynamicDataIndicatorCategoryPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDynamicDataIndicatorCategoryRepresentationApplicationService.pageQuery(dynamicDataIndicatorCategoryPageQueryCommand);
    }


    @PreAuthorize("hasAuthority('admin:web:dynamicDataIndicatorCategory:exportTemplate')")
    @Operation(summary = "动态数据指标分类导出模板")
    @GetMapping("/exportTemplate")
    public void export(HttpServletResponse response, @Validated DynamicDataIndicatorCategoryTemplateCommand dynamicDataIndicatorCategoryTemplateCommand) throws IOException {

        DynamicDataIndicatorQueryListCommand dynamicDataIndicatorQueryListCommand = new DynamicDataIndicatorQueryListCommand();
        dynamicDataIndicatorQueryListCommand.setDynamicDataIndicatorCategoryId(dynamicDataIndicatorCategoryTemplateCommand.getId());
        MultiResponse<DynamicDataIndicatorVO> dynamicDataIndicatorVOMultiResponse = iDynamicDataIndicatorRepresentationApplicationService.queryList(dynamicDataIndicatorQueryListCommand);
        if (dynamicDataIndicatorVOMultiResponse == null || dynamicDataIndicatorVOMultiResponse.getData() == null || dynamicDataIndicatorVOMultiResponse.getData().isEmpty()) {
            throw ExceptionFactory.bizException(ErrorCodeGlobalEnum.BAD_REQUEST_ERROR, "未找到指标信息，请先添加指标信息");
        }
        List<DynamicDataIndicatorVO> dynamicDataIndicatorVOList = dynamicDataIndicatorVOMultiResponse.getData();

        SingleResponse<DynamicDataIndicatorCategoryVO> dynamicDataIndicatorCategoryVOSingleResponse = queryDetail(dynamicDataIndicatorCategoryTemplateCommand);
        DynamicDataIndicatorCategoryVO dynamicDataIndicatorCategoryVO = dynamicDataIndicatorCategoryVOSingleResponse.getData();
        /**
         * 参考 {@link JakartaServletUtil#write(HttpServletResponse, InputStream, String, String)}
         */
        GlobalDocumentTemplate dynamicDataIndicatorCategoryTemplate = globalDocumentTemplateService.resolveTemplate("dynamicDataIndicatorCategoryTemplate");

        String contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        String fileName = dynamicDataIndicatorCategoryVO.getName() + "-动态数据指标分类模板.xlsx";

        final String charset = ObjectUtil.defaultIfNull(response.getCharacterEncoding(), CharsetUtil.UTF_8);
        final String encodeText = URLUtil.encodeAll(fileName, CharsetUtil.charset(charset));
        response.setHeader("Content-Disposition",
                StrUtil.format("attachment;filename=\"{}\";filename*={}''{}", encodeText, charset, encodeText));
        response.setContentType(contentType);

        /**
         * 以下参考 {@link ExcelTool#writeBeanAll(InputStream, List, Class, OutputStream)}
         */
        Workbook book = WorkbookUtil.createBook(dynamicDataIndicatorCategoryTemplate.getInputStream());
        int sheetIndex = 0;
        Sheet sheetAt = book.getSheetAt(sheetIndex);
        ExcelWriter writer = new CustomExcelWriter(book, sheetAt.getSheetName());

        writer.setStyleSet(new CustomStyleSet(book, sheetIndex));

        List<String> data = dynamicDataIndicatorVOList.stream()
                .filter(dynamicTableFieldVO -> {
                    List<Long> dynamicDataIndicatorIds = dynamicDataIndicatorCategoryTemplateCommand.getDynamicDataIndicatorIds();
                    if (CollectionUtil.isEmpty(dynamicDataIndicatorIds)){
                        return true;
                    }
                    return dynamicDataIndicatorIds.contains(dynamicTableFieldVO.getId());
                })
                .filter(dynamicTableFieldVO -> {
                    List<Long> dynamicTableFieldNames = dynamicDataIndicatorCategoryTemplateCommand.getDynamicTableFieldNames();
                    if (CollectionUtil.isEmpty(dynamicTableFieldNames)){
                        return true;
                    }
                    return dynamicTableFieldNames.contains(dynamicTableFieldVO.getName());
                })
                .map(DynamicDataIndicatorVO::getName).collect(Collectors.toList());
        writer.writeHeadRow(data);
        OutputStream out = response.getOutputStream();
        writer.flush(out, true);
        writer.close();
        IoUtil.close(out);
    }

    @PreAuthorize("hasAuthority('admin:web:dynamicDataIndicatorCategory:uploadImportData')")
    @Operation(summary = "动态数据指标分类上传导入数据")
    @PostMapping(value = "/uploadImportData",consumes = "multipart/*",headers = "content-type=multipart/form-data")
    @ResponseStatus(HttpStatus.CREATED)
    // 没有找到好的方式添加文件上传，手动补救一下，参考：https://blog.csdn.net/HHoao/article/details/125767378
    @io.swagger.v3.oas.annotations.parameters.RequestBody(content = {@Content(
            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
            schema = @Schema(type = "object"),
            schemaProperties = {
                    @SchemaProperty(
                            name = "file",
                            schema = @Schema(type = "string",format = "binary")
                    )
            }
    )})
    @OpLog(name = "动态数据指标分类上传导入数据",module = OpLogConstants.Module.data,type = OpLogConstants.Type.create)
    public  Response uploadImportData(@Valid DynamicDataIndicatorCategoryUploadImportDataCommand uploadCommand) throws IOException {

        DynamicDataIndicatorQueryListCommand dynamicDataIndicatorQueryListCommand = new DynamicDataIndicatorQueryListCommand();
        dynamicDataIndicatorQueryListCommand.setDynamicDataIndicatorCategoryId(uploadCommand.getDynamicDataIndicatorCategoryId());
        MultiResponse<DynamicDataIndicatorVO> dynamicDataIndicatorVOMultiResponse = iDynamicDataIndicatorRepresentationApplicationService.queryList(dynamicDataIndicatorQueryListCommand);
        if (dynamicDataIndicatorVOMultiResponse == null || dynamicDataIndicatorVOMultiResponse.getData() == null || dynamicDataIndicatorVOMultiResponse.getData().isEmpty()) {
            throw ExceptionFactory.bizException(ErrorCodeGlobalEnum.BAD_REQUEST_ERROR, "未找到指标信息，请先添加指标信息");
        }
        List<DynamicDataIndicatorVO> dynamicDataIndicatorVOList = dynamicDataIndicatorVOMultiResponse.getData();

        // 将上传的文件先保存到本地
        MultipartFile fileTemp = uploadCommand.getFile();
        String originalFilename = fileTemp.getOriginalFilename();
        InputStream inputStream = fileTemp.getInputStream();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName = IdUtil.fastSimpleUUID()+ "--" + originalFilename;
        String objectName =  Optional.ofNullable("/dynamicdata").orElse("") + "/" + DateUtil.date().toDateStr() + "/" + newFileName;
        // 不能添加前缀斜杠，经测试 阿里 oss key不支持以斜杠开头
        objectName = NetPathTool.ensureNotBeginSlash(objectName);
        Long fileLength = fileTemp.getSize();



        String tmpFilePath = FilePathTool.concat(FileUtil.getTmpDirPath(), newFileName);
        // 此方法会自动关闭输入流
        FileUtil.writeFromStream(inputStream, tmpFilePath);
        InputStream inputStreamTemp = FileUtil.getInputStream(tmpFilePath);


        // 读取结束自动关闭流
        ExcelReader reader = ExcelUtil.getReader(inputStreamTemp);
        Map headerAlias = new HashMap();
        for (DynamicDataIndicatorVO dynamicDataIndicatorVO : dynamicDataIndicatorVOList) {
            headerAlias.put(dynamicDataIndicatorVO.getName(), DynamicDataIndicatorCreateCommandExecutor.wrapTableColumn(dynamicDataIndicatorVO.getId()));
        }

        reader.setHeaderAlias(headerAlias);
        // 这里应该是List<<Map>
        List data = reader.readAll(Map.class);
        // 求 data 中 数据类型为 map 的 key 最大的数
        Integer maxKey = data.stream()
                .mapToInt(map -> ((Map<Object, Object>) map).keySet().size())
                .max()
                .orElse(0);
        // 添加上传记录
        InputStream inputStreamTempForUpload = FileUtil.getInputStream(tmpFilePath);
        String mimeType = FileTool.getMimeType(objectName);
        String upload;
        try {
            upload = globalOssClientService.upload(objectName, inputStreamTempForUpload,mimeType);
        } finally {
            IoUtil.close(inputStreamTempForUpload);
            FileUtil.del(tmpFilePath);
        }
        Boolean isPublic = false;
        DynamicDataIndicatorCategoryUploadRecordCreateCommand dynamicDataIndicatorCategoryUploadRecordCreateCommand = new DynamicDataIndicatorCategoryUploadRecordCreateCommand();
        dynamicDataIndicatorCategoryUploadRecordCreateCommand.setDynamicDataIndicatorCategoryId(uploadCommand.getDynamicDataIndicatorCategoryId());
        dynamicDataIndicatorCategoryUploadRecordCreateCommand.setUploadFileName(originalFilename);
        dynamicDataIndicatorCategoryUploadRecordCreateCommand.setUploadFileUrl(upload);
        dynamicDataIndicatorCategoryUploadRecordCreateCommand.setUploadIndicatorNum(maxKey);
        dynamicDataIndicatorCategoryUploadRecordCreateCommand.setUploadIndicatorDataNum(data.size());
        dynamicDataIndicatorCategoryUploadRecordCreateCommand.setIsPublic(isPublic);
        SingleResponse<DynamicDataIndicatorCategoryUploadRecordVO> dynamicDataIndicatorCategoryUploadRecordVOSingleResponse = iDynamicDataIndicatorCategoryUploadRecordApplicationService.create(dynamicDataIndicatorCategoryUploadRecordCreateCommand);
        Long batchId = dynamicDataIndicatorCategoryUploadRecordVOSingleResponse.getData().getId();

        DynamicDataIndicatorCategoryImportDataCommand dynamicDataIndicatorCategoryImportDataCommand = new DynamicDataIndicatorCategoryImportDataCommand();
        dynamicDataIndicatorCategoryImportDataCommand.setData(data);
        dynamicDataIndicatorCategoryImportDataCommand.setDynamicDataIndicatorCategoryId(uploadCommand.getDynamicDataIndicatorCategoryId());

        Response response = iDynamicDataIndicatorCategoryApplicationService.importData(dynamicDataIndicatorCategoryImportDataCommand,isPublic,batchId);


        return response;
    }
    @PreAuthorize("hasAuthority('admin:web:dynamicDataIndicatorCategory:dataPageQuery')")
    @Operation(summary = "分页查询动态数据指标分类数据")
    @GetMapping("/dataPage")
    public PageResponse<Map<String,Object>> dataPageQuery(DynamicDataIndicatorCategoryDataPageQueryCommand dynamicDataIndicatorCategoryDataPageQueryCommand){
        dynamicDataIndicatorCategoryDataPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        PageResponse<Map<String,Object>> mapPageResponse = iDynamicDataIndicatorCategoryRepresentationApplicationService.dataPageQuery(dynamicDataIndicatorCategoryDataPageQueryCommand);
        // 提示并翻译
        // @TransBy(type = TransConstants.TRANS_USER_BY_ID,byFieldName = "ownerUserId",mapValueField = "nickname")
        // 翻译元数据
        List<TransHelper.TransMeta> transMetas = new ArrayList<>();
        TransHelper.TransMeta transMeta = new TransHelper.TransMeta();
        transMeta.setType(TransConstants.TRANS_USER_BY_ID);
        transMeta.setByFieldName(DynamicDO.COLUMN_CREATE_BY);
        transMeta.setMapValueField("nickname");
        transMeta.setForFieldName(DynamicDO.COLUMN_CREATE_BY + "_nickname");
        transMetas.add(transMeta);
        TransTool.putThreadLocalTransData(TransTool.ThreadLocalTransData.create(transMetas,null));
        try {
            TransTool.trans(mapPageResponse);
        } finally {
            TransTool.clearThreadLocalTransData();
        }
        return mapPageResponse;
    }
    @PreAuthorize("hasAuthority('admin:web:dynamicDataIndicatorCategory:dataDelete')")
    @Operation(summary = "删除动态数据指标分类数据")
    @DeleteMapping("/dataDelete")
    @OpLog(name = "删除动态数据指标分类数据",module = OpLogConstants.Module.data,type = OpLogConstants.Type.delete)
    public SingleResponse<Map<String,Object>> dataDelete(@RequestBody DynamicDataIndicatorCategoryDataDeleteCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDynamicDataIndicatorCategoryApplicationService.dataDelete(deleteCommand);
    }
}
