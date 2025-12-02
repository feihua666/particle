package com.particle.data.client.dynamictable.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * 动态数据表格导入数据 创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-11-09 17:52:35
 */
@Data
@Schema
public class DynamicTableImportDataCommand extends AbstractBaseCommand {
    @NotNull(message = "动态数据表格id 不能为空")
    @Schema(description = "动态数据表格id")
    private Long dynamicTableId;

    /**
     *  数据
     *  Map 表示一行数据,一般为 Map<String,Object> 类型
     */
    @NotEmpty(message = "数据 不能为空")
    @Schema(description = "数据")
    private List<Map<String,Object>> data;

}
