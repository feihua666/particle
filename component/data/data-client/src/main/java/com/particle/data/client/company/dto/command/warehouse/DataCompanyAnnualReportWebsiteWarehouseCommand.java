package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportWebsiteExWarehouseVO;
import com.particle.data.common.tool.SomeMd5Tool;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Objects;

/**
 * <p>
 * 企业年报网站网店入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyAnnualReportWebsiteWarehouseCommand extends AbstractBaseCommand {



    @NotNull(message = "企业表ID 不能为空")
    @Schema(description = "企业表ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyId;


    @NotNull(message = "企业年报表ID 不能为空")
    @Schema(description = "企业年报表ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyAnnualReportId;


    @NotNull(message = "年报年度 不能为空")
    @Schema(description = "年报年度",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer year;



    @Schema(description = "类型,字典id")
    private Long typeDictId;

	@Schema(description = "类型名称，如：网站、网店")
	private String typeName;


    @Schema(description = "名称")
    private String name;


    @NotEmpty(message = "网址 不能为空")
    @Schema(description = "网址",requiredMode = Schema.RequiredMode.REQUIRED)
    private String url;

    public String obtainDataMd5() {
        return SomeMd5Tool.dataCompanyAnnualReportWebsiteDataMd5(typeName, name, url);
    }


    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return Objects.isNull(companyId)
                && Objects.isNull(companyAnnualReportId)
                && Objects.isNull(year)
                && Objects.isNull(typeDictId)
                && StrUtil.isEmpty(typeName)
                && StrUtil.isEmpty(name)
                && StrUtil.isEmpty(url);
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyAnnualReportWebsiteExWarehouseVO exWarehouseVO) {
                if (Objects.equals(companyId, exWarehouseVO.getCompanyId())) {
            this.companyId = null;
        }
        if (Objects.equals(companyAnnualReportId, exWarehouseVO.getCompanyAnnualReportId())) {
            this.companyAnnualReportId = null;
        }
        if (Objects.equals(year, exWarehouseVO.getYear())) {
            this.year = null;
        }
        if (Objects.equals(typeDictId, exWarehouseVO.getTypeDictId())) {
            this.typeDictId = null;
        }
        if (Objects.equals(typeName, exWarehouseVO.getTypeName())) {
            this.typeName = null;
        }
        if (Objects.equals(name, exWarehouseVO.getName())) {
            this.name = null;
        }
        if (Objects.equals(url, exWarehouseVO.getUrl())) {
            this.url = null;
        }

    }

    public static DataCompanyAnnualReportWebsiteWarehouseCommand createByDataCompanyAnnualReportWebsiteExWarehouseVO(DataCompanyAnnualReportWebsiteExWarehouseVO exWarehouseVO) {
        DataCompanyAnnualReportWebsiteWarehouseCommand command = new DataCompanyAnnualReportWebsiteWarehouseCommand();
        command.companyId = exWarehouseVO.getCompanyId();
        command.companyAnnualReportId = exWarehouseVO.getCompanyAnnualReportId();
        command.year = exWarehouseVO.getYear();
        command.typeDictId = exWarehouseVO.getTypeDictId();
        command.typeName = exWarehouseVO.getTypeName();
        command.name = exWarehouseVO.getName();
        command.url = exWarehouseVO.getUrl();

        return command;
    }
}
