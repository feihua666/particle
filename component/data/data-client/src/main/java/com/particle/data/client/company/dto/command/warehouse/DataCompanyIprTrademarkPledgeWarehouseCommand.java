package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkPledgeExWarehouseVO;
import com.particle.data.common.tool.SomeMd5Tool;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

/**
 * <p>
 * 企业知识产权商标质押信息入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyIprTrademarkPledgeWarehouseCommand extends AbstractBaseCommand {



    @NotNull(message = "企业知识产权商标id 不能为空")
    @Schema(description = "企业知识产权商标id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyIprTrademarkId;


    @Schema(description = "出质人")
    private String pledgor;


    @Schema(description = "质权人")
    private String pledgee;


    @Schema(description = "质权登记起始日期")
    private LocalDate pledgeRegStartDate;


    @Schema(description = "质权登记截止日期")
    private LocalDate pledgeRegEndDate;


    @Schema(description = "质权公告页号")
    private String pledgePublicPageNo;


    @Schema(description = "数据md5")
    private String dataMd5;

    public String obtainDataMd5() {
        if (StrUtil.isEmpty(dataMd5)) {
            dataMd5 = SomeMd5Tool.dataCompanyIprTrademarkPledgeDataMd5(pledgor,pledgee,pledgePublicPageNo);
        }
        return dataMd5;
    }

    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return Objects.isNull(companyIprTrademarkId)
                && StrUtil.isEmpty(pledgor)
                && StrUtil.isEmpty(pledgee)
                && Objects.isNull(pledgeRegStartDate)
                && Objects.isNull(pledgeRegEndDate)
                && Objects.isNull(pledgePublicPageNo)
                && StrUtil.isEmpty(dataMd5)
;
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyIprTrademarkPledgeExWarehouseVO exWarehouseVO) {
        if (Objects.equals(companyIprTrademarkId, exWarehouseVO.getCompanyIprTrademarkId())) {
            this.companyIprTrademarkId = null;
        }
        if (Objects.equals(pledgor, exWarehouseVO.getPledgor())) {
            this.pledgor = null;
        }
        if (Objects.equals(pledgee, exWarehouseVO.getPledgee())) {
            this.pledgee = null;
        }
        if (Objects.equals(pledgeRegStartDate, exWarehouseVO.getPledgeRegStartDate())) {
            this.pledgeRegStartDate = null;
        }
        if (Objects.equals(pledgeRegEndDate, exWarehouseVO.getPledgeRegEndDate())) {
            this.pledgeRegEndDate = null;
        }
        if (Objects.equals(pledgePublicPageNo, exWarehouseVO.getPledgePublicPageNo())) {
            this.pledgePublicPageNo = null;
        }

    }

    public static DataCompanyIprTrademarkPledgeWarehouseCommand createByDataCompanyIprTrademarkPledgeExWarehouseVO(DataCompanyIprTrademarkPledgeExWarehouseVO exWarehouseVO) {
        DataCompanyIprTrademarkPledgeWarehouseCommand command = new DataCompanyIprTrademarkPledgeWarehouseCommand();
        command.companyIprTrademarkId = exWarehouseVO.getCompanyIprTrademarkId();
        command.pledgor = exWarehouseVO.getPledgor();
        command.pledgee = exWarehouseVO.getPledgee();
        command.pledgeRegStartDate = exWarehouseVO.getPledgeRegStartDate();
        command.pledgeRegEndDate = exWarehouseVO.getPledgeRegEndDate();
        command.pledgePublicPageNo = exWarehouseVO.getPledgePublicPageNo();

        return command;
    }
}
