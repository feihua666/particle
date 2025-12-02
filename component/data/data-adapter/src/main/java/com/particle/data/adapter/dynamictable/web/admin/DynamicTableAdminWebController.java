package com.particle.data.adapter.dynamictable.web.admin;

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
import com.particle.data.client.dynamicdata.dto.command.DynamicDataIndicatorCategoryUploadRecordCreateCommand;
import com.particle.data.client.dynamictable.api.IDynamicTableApplicationService;
import com.particle.data.client.dynamictable.api.IDynamicTableUploadRecordApplicationService;
import com.particle.data.client.dynamictable.api.representation.IDynamicTableFieldRepresentationApplicationService;
import com.particle.data.client.dynamictable.api.representation.IDynamicTableRepresentationApplicationService;
import com.particle.data.client.dynamictable.dto.command.*;
import com.particle.data.client.dynamictable.dto.command.representation.*;
import com.particle.data.client.dynamictable.dto.data.DynamicTableFieldVO;
import com.particle.data.client.dynamictable.dto.data.DynamicTableUploadRecordVO;
import com.particle.data.client.dynamictable.dto.data.DynamicTableVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.global.document.template.GlobalDocumentTemplate;
import com.particle.global.document.template.GlobalDocumentTemplateService;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.ExceptionFactory;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
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
 * 动态数据表格后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:29:35
 */
@Tag(name = "动态数据表格pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/dynamic_table")
public class DynamicTableAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IDynamicTableApplicationService iDynamicTableApplicationService;
    @Autowired
    private IDynamicTableRepresentationApplicationService iDynamicTableRepresentationApplicationService;
    @Autowired
    private IDynamicTableFieldRepresentationApplicationService iDynamicTableFieldRepresentationApplicationService;
    @Autowired
    private GlobalDocumentTemplateService globalDocumentTemplateService;
    @Autowired
    private GlobalOssClientService globalOssClientService;

    @Autowired
    private IDynamicTableUploadRecordApplicationService iDynamicTableUploadRecordApplicationService;

    @PreAuthorize("hasAuthority('admin:web:dynamicTable:create')")
    @Operation(summary = "添加动态数据表格")
    @PostMapping("/create")
    @OpLog(name = "添加动态数据表格",module = OpLogConstants.Module.data,type = OpLogConstants.Type.create)
    public SingleResponse<DynamicTableVO> create(@RequestBody DynamicTableCreateCommand dynamicTableCreateCommand){
        return iDynamicTableApplicationService.create(dynamicTableCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dynamicTable:delete')")
    @Operation(summary = "删除动态数据表格")
    @DeleteMapping("/delete")
    @OpLog(name = "删除动态数据表格",module = OpLogConstants.Module.data,type = OpLogConstants.Type.delete)
    public SingleResponse<DynamicTableVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDynamicTableApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dynamicTable:update')")
    @Operation(summary = "更新动态数据表格")
    @PutMapping("/update")
    @OpLog(name = "更新动态数据表格",module = OpLogConstants.Module.data,type = OpLogConstants.Type.update)
    public SingleResponse<DynamicTableVO> update(@RequestBody DynamicTableUpdateCommand dynamicTableUpdateCommand){
        dynamicTableUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iDynamicTableApplicationService.update(dynamicTableUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dynamicTable:update')")
    @Operation(summary = "动态数据表格更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<DynamicTableVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iDynamicTableRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dynamicTable:detail')")
    @Operation(summary = "动态数据表格详情展示")
    @GetMapping("/detail")
    public SingleResponse<DynamicTableVO> queryDetail(IdCommand detailCommand){
        return iDynamicTableRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dynamicTable:queryList')")
    @Operation(summary = "列表查询动态数据表格")
    @GetMapping("/list")
    public MultiResponse<DynamicTableVO> queryList(DynamicTableQueryListCommand dynamicTableQueryListCommand){
        dynamicTableQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDynamicTableRepresentationApplicationService.queryList(dynamicTableQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dynamicTable:pageQuery')")
    @Operation(summary = "分页查询动态数据表格")
    @GetMapping("/page")
    public PageResponse<DynamicTableVO> pageQueryList(DynamicTablePageQueryCommand dynamicTablePageQueryCommand){
        dynamicTablePageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iDynamicTableRepresentationApplicationService.pageQuery(dynamicTablePageQueryCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:dynamicTable:exportTemplate')")
    @Operation(summary = "动态数据表格导出模板")
    @GetMapping("/exportTemplate")
    public void export(HttpServletResponse response,@Validated DynamicTableExportTemplateCommand dynamicTableExportTemplateCommand) throws IOException {

        DynamicTableFieldQueryListCommand dynamicTableFieldQueryListCommand = new DynamicTableFieldQueryListCommand();
        dynamicTableFieldQueryListCommand.setDynamicTableId(dynamicTableExportTemplateCommand.getId());
        MultiResponse<DynamicTableFieldVO> dynamicTableFieldVOMultiResponse = iDynamicTableFieldRepresentationApplicationService.queryList(dynamicTableFieldQueryListCommand);
        if (dynamicTableFieldVOMultiResponse == null || dynamicTableFieldVOMultiResponse.getData() == null || dynamicTableFieldVOMultiResponse.getData().isEmpty()) {
            throw ExceptionFactory.bizException(ErrorCodeGlobalEnum.BAD_REQUEST_ERROR, "未找到表格字段信息，请先添加字段信息");
        }
        List<DynamicTableFieldVO> dynamicTableFieldVOList = dynamicTableFieldVOMultiResponse.getData();

        SingleResponse<DynamicTableVO> dynamicTableVOSingleResponse = queryDetail(dynamicTableExportTemplateCommand);
        DynamicTableVO dynamicTableVO = dynamicTableVOSingleResponse.getData();
        /**
         * 参考 {@link JakartaServletUtil#write(HttpServletResponse, InputStream, String, String)}
         */
        GlobalDocumentTemplate dynamicTableTemplate = globalDocumentTemplateService.resolveTemplate("dynamicTableTemplate");

        String contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        String fileName = dynamicTableVO.getComment() + "-动态数据表格模板.xlsx";

        final String charset = ObjectUtil.defaultIfNull(response.getCharacterEncoding(), CharsetUtil.UTF_8);
        final String encodeText = URLUtil.encodeAll(fileName, CharsetUtil.charset(charset));
        response.setHeader("Content-Disposition",
                StrUtil.format("attachment;filename=\"{}\";filename*={}''{}", encodeText, charset, encodeText));
        response.setContentType(contentType);

        /**
         * 以下参考 {@link ExcelTool#writeBeanAll(InputStream, List, Class, OutputStream)}
         */
        Workbook book = WorkbookUtil.createBook(dynamicTableTemplate.getInputStream());
        int sheetIndex = 0;
        Sheet sheetAt = book.getSheetAt(sheetIndex);
        ExcelWriter writer = new CustomExcelWriter(book, sheetAt.getSheetName());

        writer.setStyleSet(new CustomStyleSet(book, sheetIndex));

        List<String> data = dynamicTableFieldVOList.stream()
                .filter(dynamicTableFieldVO -> {
                    List<Long> dynamicTableFieldIds = dynamicTableExportTemplateCommand.getDynamicTableFieldIds();
                    if (CollectionUtil.isEmpty(dynamicTableFieldIds)){
                        return true;
                    }
                    return dynamicTableFieldIds.contains(dynamicTableFieldVO.getId());
                })
                .filter(dynamicTableFieldVO -> {
                    List<Long> dynamicTableFieldNames = dynamicTableExportTemplateCommand.getDynamicTableFieldNames();
                    if (CollectionUtil.isEmpty(dynamicTableFieldNames)){
                        return true;
                    }
                    return dynamicTableFieldNames.contains(dynamicTableFieldVO.getName());
                })
                .filter(dynamicTableFieldVO -> {
                    List<Long> dynamicTableFieldComments = dynamicTableExportTemplateCommand.getDynamicTableFieldComments();
                    if (CollectionUtil.isEmpty(dynamicTableFieldComments)){
                        return true;
                    }
                    return dynamicTableFieldComments.contains(dynamicTableFieldVO.getComment());
                })
                .map(DynamicTableFieldVO::getComment).collect(Collectors.toList());
        writer.writeHeadRow(data);
        OutputStream out = response.getOutputStream();
        writer.flush(out, true);
        writer.close();
        IoUtil.close(out);
    }

    @PreAuthorize("hasAuthority('admin:web:dynamicTable:uploadImportData')")
    @Operation(summary = "动态数据表格上传导入数据")
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
    @OpLog(name = "动态数据表格上传导入数据",module = OpLogConstants.Module.data,type = OpLogConstants.Type.create)
    public  Response uploadImportData(@Valid DynamicTableUploadImportDataCommand uploadCommand) throws IOException {

        DynamicTableFieldQueryListCommand dynamicTableFieldQueryListCommand = new DynamicTableFieldQueryListCommand();
        dynamicTableFieldQueryListCommand.setDynamicTableId(uploadCommand.getDynamicTableId());
        MultiResponse<DynamicTableFieldVO> dynamicTableFieldVOMultiResponse = iDynamicTableFieldRepresentationApplicationService.queryList(dynamicTableFieldQueryListCommand);
        if (dynamicTableFieldVOMultiResponse == null || dynamicTableFieldVOMultiResponse.getData() == null || dynamicTableFieldVOMultiResponse.getData().isEmpty()) {
            throw ExceptionFactory.bizException(ErrorCodeGlobalEnum.BAD_REQUEST_ERROR, "未找到表格字段信息，请先添加字段信息");
        }
        List<DynamicTableFieldVO> dynamicTableFieldVOList = dynamicTableFieldVOMultiResponse.getData();

        // 将上传的文件先保存到本地
        MultipartFile fileTemp = uploadCommand.getFile();
        String originalFilename = fileTemp.getOriginalFilename();
        InputStream inputStream = fileTemp.getInputStream();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName = IdUtil.fastSimpleUUID()+ "--" + originalFilename;
        String objectName =  Optional.ofNullable("/dynamictable").orElse("") + "/" + DateUtil.date().toDateStr() + "/" + newFileName;
        // 不能添加前缀斜杠，经测试 阿里 oss key不支持以斜杠开头
        objectName = NetPathTool.ensureNotBeginSlash(objectName);
        Long fileLength = fileTemp.getSize();



        String tmpFilePath = FilePathTool.concat(FileUtil.getTmpDirPath(), newFileName);
        // 此方法会自动关闭输入流
        FileUtil.writeFromStream(inputStream, tmpFilePath);
        InputStream inputStreamTemp = FileUtil.getInputStream(tmpFilePath);


        ExcelReader reader = ExcelUtil.getReader(inputStreamTemp);
        Map headerAlias = new HashMap();
        for (DynamicTableFieldVO dynamicTableFieldVO : dynamicTableFieldVOList) {
            headerAlias.put(dynamicTableFieldVO.getComment(),dynamicTableFieldVO.getName());
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
        boolean isPublic = false;
        DynamicTableUploadRecordCreateCommand dynamicTableUploadRecordCreateCommand = new DynamicTableUploadRecordCreateCommand();
        dynamicTableUploadRecordCreateCommand.setDynamicTableId(uploadCommand.getDynamicTableId());
        dynamicTableUploadRecordCreateCommand.setUploadFileName(originalFilename);
        dynamicTableUploadRecordCreateCommand.setUploadFileUrl(upload);
        dynamicTableUploadRecordCreateCommand.setUploadTableFieldNum(maxKey);
        dynamicTableUploadRecordCreateCommand.setUploadTableDataNum(data.size());
        dynamicTableUploadRecordCreateCommand.setIsPublic(isPublic);
        SingleResponse<DynamicTableUploadRecordVO> dynamicTableUploadRecordVOSingleResponse = iDynamicTableUploadRecordApplicationService.create(dynamicTableUploadRecordCreateCommand);
        Long batchId = dynamicTableUploadRecordVOSingleResponse.getData().getId();


        DynamicTableImportDataCommand dynamicTableImportDataCommand = new DynamicTableImportDataCommand();
        dynamicTableImportDataCommand.setData(data);
        dynamicTableImportDataCommand.setDynamicTableId(uploadCommand.getDynamicTableId());

        Response response =  iDynamicTableApplicationService.importData(dynamicTableImportDataCommand,isPublic,batchId);

        return response;
    }
    @PreAuthorize("hasAuthority('admin:web:dynamicTable:dataPageQuery')")
    @Operation(summary = "分页查询动态数据表格数据")
    @GetMapping("/dataPage")
    public PageResponse<Map<String,Object>> dataPageQuery(DynamicTableDataPageQueryCommand dynamicTableDataPageQueryCommand){
        dynamicTableDataPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());

        PageResponse<Map<String, Object>> mapPageResponse = iDynamicTableRepresentationApplicationService.dataPageQuery(dynamicTableDataPageQueryCommand);

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
    @PreAuthorize("hasAuthority('admin:web:dynamicTable:dataDelete')")
    @Operation(summary = "删除动态数据表格数据")
    @DeleteMapping("/dataDelete")
    @OpLog(name = "删除动态数据表格数据",module = OpLogConstants.Module.data,type = OpLogConstants.Type.delete)
    public SingleResponse<Map<String,Object>> dataDelete(@RequestBody DynamicTableDataDeleteCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iDynamicTableApplicationService.dataDelete(deleteCommand);
    }
}
