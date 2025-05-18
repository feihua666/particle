package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyJudgmentDocumentContentExWarehouseVO;
import com.particle.data.common.tool.SomeMd5Tool;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Objects;

/**
 * <p>
 * 企业裁判文书内容入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyJudgmentDocumentContentWarehouseCommand extends AbstractBaseCommand {



    @NotNull(message = "裁判文书id 不能为空")
    @Schema(description = "裁判文书id")
    private Long companyJudgmentDocumentId;


    @Schema(description = "裁判内容")
    private String content;

    public String obtainDataMd5() {
        return SomeMd5Tool.dataCompanyJudgmentDocumentContentDataMd5(content);
    }
    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return Objects.isNull(companyJudgmentDocumentId)
                && StrUtil.isEmpty(content);
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyJudgmentDocumentContentExWarehouseVO exWarehouseVO) {
                if (Objects.equals(companyJudgmentDocumentId, exWarehouseVO.getCompanyJudgmentDocumentId())) {
            this.companyJudgmentDocumentId = null;
        }
        if (Objects.equals(content, exWarehouseVO.getContent())) {
            this.content = null;
        }

    }

    public static DataCompanyJudgmentDocumentContentWarehouseCommand createByDataCompanyJudgmentDocumentContentExWarehouseVO(DataCompanyJudgmentDocumentContentExWarehouseVO exWarehouseVO) {
        DataCompanyJudgmentDocumentContentWarehouseCommand command = new DataCompanyJudgmentDocumentContentWarehouseCommand();
        command.companyJudgmentDocumentId = exWarehouseVO.getCompanyJudgmentDocumentId();
        command.content = exWarehouseVO.getContent();

        return command;
    }
}
