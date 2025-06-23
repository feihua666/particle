package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkLicenseExWarehouseVO;
import com.particle.data.common.tool.SomeMd5Tool;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

/**
 * <p>
 * 企业知识产权商标许可信息入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyIprTrademarkLicenseWarehouseCommand extends AbstractBaseCommand {

    @NotNull(message = "企业知识产权商标id 不能为空")
        @Schema(description = "企业知识产权商标id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyIprTrademarkId;


    @Schema(description = "许可期号")
    private String licenseIssueNo;


    @Schema(description = "许可页码")
    private String licensePageNo;


    @Schema(description = "许可备案号")
    private String licenseFilingNo;


    @Schema(description = "许可备案公告日期")
    private LocalDate licenseFilingPublicDate;


    @Schema(description = "许可期限")
    private String licenseTerm;


    @Schema(description = "许可使用的商品服务")
    private String licenseGoodService;

    @Schema(description = "数据md5")
    private String dataMd5;

    public String obtainDataMd5() {
        if (StrUtil.isEmpty(dataMd5)) {
            dataMd5 = SomeMd5Tool.dataCompanyIprTrademarkLicenseDataMd5(licenseIssueNo);
        }
        return dataMd5;
    }

    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return Objects.isNull(companyIprTrademarkId)
                && StrUtil.isEmpty(licenseIssueNo)
                && StrUtil.isEmpty(licensePageNo)
                && StrUtil.isEmpty(licenseFilingNo)
                && Objects.isNull(licenseFilingPublicDate)
                && StrUtil.isEmpty(licenseTerm)
                && StrUtil.isEmpty(licenseGoodService)
;
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyIprTrademarkLicenseExWarehouseVO exWarehouseVO) {
        if (Objects.equals(companyIprTrademarkId, exWarehouseVO.getCompanyIprTrademarkId())) {
            this.companyIprTrademarkId = null;
        }
        if (Objects.equals(licenseIssueNo, exWarehouseVO.getLicenseIssueNo())) {
            this.licenseIssueNo = null;
        }
        if (Objects.equals(licensePageNo, exWarehouseVO.getLicensePageNo())) {
            this.licensePageNo = null;
        }
        if (Objects.equals(licenseFilingNo, exWarehouseVO.getLicenseFilingNo())) {
            this.licenseFilingNo = null;
        }
        if (Objects.equals(licenseFilingPublicDate, exWarehouseVO.getLicenseFilingPublicDate())) {
            this.licenseFilingPublicDate = null;
        }
        if (Objects.equals(licenseTerm, exWarehouseVO.getLicenseTerm())) {
            this.licenseTerm = null;
        }
        if (Objects.equals(licenseGoodService, exWarehouseVO.getLicenseGoodService())) {
            this.licenseGoodService = null;
        }
    }
    public static DataCompanyIprTrademarkLicenseWarehouseCommand createByDataCompanyIprTrademarkLicenseExWarehouseVO(DataCompanyIprTrademarkLicenseExWarehouseVO exWarehouseVO) {
        DataCompanyIprTrademarkLicenseWarehouseCommand command = new DataCompanyIprTrademarkLicenseWarehouseCommand();
        command.companyIprTrademarkId = exWarehouseVO.getCompanyIprTrademarkId();
        command.licenseIssueNo = exWarehouseVO.getLicenseIssueNo();
        command.licensePageNo = exWarehouseVO.getLicensePageNo();
        command.licenseFilingNo = exWarehouseVO.getLicenseFilingNo();
        command.licenseFilingPublicDate = exWarehouseVO.getLicenseFilingPublicDate();
        command.licenseTerm = exWarehouseVO.getLicenseTerm();
        command.licenseGoodService = exWarehouseVO.getLicenseGoodService();

        return command;
    }
}
