package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprGeograApproveAnnouncementExWarehouseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

/**
 * <p>
 * 企业知识产权地理标识核准公告入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyIprGeograApproveAnnouncementWarehouseCommand extends AbstractBaseCommand {



    @Schema(description = "企业知识产权地理标识id")
    private Long companyIprGeograId;


    @Schema(description = "核准公告编号")
    private String approvePublicNo;


    @Schema(description = "企业名称")
    private String companyName;


    @Schema(description = "核准地址")
    private String approveAddress;


    @Schema(description = "核准法人代表")
    private String approveLegalPersonName;


    @Schema(description = "产品名称")
    private String productName;


    @Schema(description = "核准商标")
    private String approveTrademark;


    @Schema(description = "核准日期")
    private LocalDate approveDate;


    @Schema(description = "核准备注")
    private String approveRemark;



    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return Objects.isNull(companyIprGeograId)
                && StrUtil.isEmpty(approvePublicNo)
                && StrUtil.isEmpty(companyName)
                && StrUtil.isEmpty(approveAddress)
                && StrUtil.isEmpty(approveLegalPersonName)
                && StrUtil.isEmpty(productName)
                && StrUtil.isEmpty(approveTrademark)
                && Objects.isNull(approveDate)
                && StrUtil.isEmpty(approveRemark)
;
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyIprGeograApproveAnnouncementExWarehouseVO exWarehouseVO) {
                if (Objects.equals(companyIprGeograId, exWarehouseVO.getCompanyIprGeograId())) {
            this.companyIprGeograId = null;
        }
        if (Objects.equals(approvePublicNo, exWarehouseVO.getApprovePublicNo())) {
            this.approvePublicNo = null;
        }
        if (Objects.equals(companyName, exWarehouseVO.getCompanyName())) {
            this.companyName = null;
        }
        if (Objects.equals(approveAddress, exWarehouseVO.getApproveAddress())) {
            this.approveAddress = null;
        }
        if (Objects.equals(approveLegalPersonName, exWarehouseVO.getApproveLegalPersonName())) {
            this.approveLegalPersonName = null;
        }
        if (Objects.equals(productName, exWarehouseVO.getProductName())) {
            this.productName = null;
        }
        if (Objects.equals(approveTrademark, exWarehouseVO.getApproveTrademark())) {
            this.approveTrademark = null;
        }
        if (Objects.equals(approveDate, exWarehouseVO.getApproveDate())) {
            this.approveDate = null;
        }
        if (Objects.equals(approveRemark, exWarehouseVO.getApproveRemark())) {
            this.approveRemark = null;
        }

    }

    public static DataCompanyIprGeograApproveAnnouncementWarehouseCommand createByDataCompanyIprGeograApproveAnnouncementExWarehouseVO(DataCompanyIprGeograApproveAnnouncementExWarehouseVO exWarehouseVO) {
        DataCompanyIprGeograApproveAnnouncementWarehouseCommand command = new DataCompanyIprGeograApproveAnnouncementWarehouseCommand();
        command.companyIprGeograId = exWarehouseVO.getCompanyIprGeograId();
        command.approvePublicNo = exWarehouseVO.getApprovePublicNo();
        command.companyName = exWarehouseVO.getCompanyName();
        command.approveAddress = exWarehouseVO.getApproveAddress();
        command.approveLegalPersonName = exWarehouseVO.getApproveLegalPersonName();
        command.productName = exWarehouseVO.getProductName();
        command.approveTrademark = exWarehouseVO.getApproveTrademark();
        command.approveDate = exWarehouseVO.getApproveDate();
        command.approveRemark = exWarehouseVO.getApproveRemark();


        return command;
    }
}
