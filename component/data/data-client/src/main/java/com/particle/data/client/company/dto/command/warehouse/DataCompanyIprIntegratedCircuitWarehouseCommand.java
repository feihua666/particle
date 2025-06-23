package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprIntegratedCircuitExWarehouseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

/**
 * <p>
 * 企业知识产权集成电路入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyIprIntegratedCircuitWarehouseCommand extends AbstractBaseCommand {



    @Schema(description = "公告号")
    private String publicNo;


    @Schema(description = "公告日期")
    private LocalDate publicDate;


    @Schema(description = "布图设计名称")
    private String name;


    @Schema(description = "布图设计登记号")
    private String regNo;


    @Schema(description = "布图设计申请日")
    private LocalDate applyDate;


    @Schema(description = "布图设计权利人名称")
    private String rightHolder;


    @Schema(description = "是否权利人为自然人")
    private Boolean isRightHolderNaturalPerson;


    @Schema(description = "权利人公司id")
    private Long rightHolderCompanyId;


    @Schema(description = "权利人个人id")
    private Long rightHolderCompanyPersonId;


    @Schema(description = "布图设计权利人国籍")
    private String rightHolderCountry;


    @Schema(description = "布图设计权利人地址")
    private String rightHolderAddress;


    @Schema(description = "布图设计创作人")
    private String designCreator;


    @Schema(description = "布图设计创作完成日")
    private LocalDate completeDate;


    @Schema(description = "布图设计类别")
    private String typeName;


    @Schema(description = "首次商业利用日期 ")
    private LocalDate firstBusinessDate;


    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return StrUtil.isEmpty(publicNo)
                && Objects.isNull(publicDate)
                && StrUtil.isEmpty(name)
                && StrUtil.isEmpty(regNo)
                && Objects.isNull(applyDate)
                && StrUtil.isEmpty(rightHolder)
                && Objects.isNull(isRightHolderNaturalPerson)
                && Objects.isNull(rightHolderCompanyId)
                && Objects.isNull(rightHolderCompanyPersonId)
                && StrUtil.isEmpty(rightHolderCountry)
                && StrUtil.isEmpty(rightHolderAddress)
                && StrUtil.isEmpty(designCreator)
                && Objects.isNull(completeDate)
                && StrUtil.isEmpty(typeName)
                && Objects.isNull(firstBusinessDate)
;
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyIprIntegratedCircuitExWarehouseVO exWarehouseVO) {
        if (Objects.equals(publicNo, exWarehouseVO.getPublicNo())) {
            this.publicNo = null;
        }
        if (Objects.equals(publicDate, exWarehouseVO.getPublicDate())) {
            this.publicDate = null;
        }
        if (Objects.equals(name, exWarehouseVO.getName())) {
            this.name = null;
        }
        if (Objects.equals(regNo, exWarehouseVO.getRegNo())) {
            this.regNo = null;
        }
        if (Objects.equals(applyDate, exWarehouseVO.getApplyDate())) {
            this.applyDate = null;
        }
        if (Objects.equals(rightHolder, exWarehouseVO.getRightHolder())) {
            this.rightHolder = null;
        }
        if (Objects.equals(isRightHolderNaturalPerson, exWarehouseVO.getIsRightHolderNaturalPerson())) {
            this.isRightHolderNaturalPerson = null;
        }
        if (Objects.equals(rightHolderCompanyId, exWarehouseVO.getRightHolderCompanyId())) {
            this.rightHolderCompanyId = null;
        }
        if (Objects.equals(rightHolderCompanyPersonId, exWarehouseVO.getRightHolderCompanyPersonId())) {
            this.rightHolderCompanyPersonId = null;
        }
        if (Objects.equals(rightHolderCountry, exWarehouseVO.getRightHolderCountry())) {
            this.rightHolderCountry = null;
        }
        if (Objects.equals(rightHolderAddress, exWarehouseVO.getRightHolderAddress())) {
            this.rightHolderAddress = null;
        }
        if (Objects.equals(designCreator, exWarehouseVO.getDesignCreator())) {
            this.designCreator = null;
        }
        if (Objects.equals(completeDate, exWarehouseVO.getCompleteDate())) {
            this.completeDate = null;
        }
        if (Objects.equals(typeName, exWarehouseVO.getTypeName())) {
            this.typeName = null;
        }
        if (Objects.equals(firstBusinessDate, exWarehouseVO.getFirstBusinessDate())) {
            this.firstBusinessDate = null;
        }
    }

    public static DataCompanyIprIntegratedCircuitWarehouseCommand createByDataCompanyIprIntegratedCircuitExWarehouseVO(DataCompanyIprIntegratedCircuitExWarehouseVO exWarehouseVO) {
        DataCompanyIprIntegratedCircuitWarehouseCommand command = new DataCompanyIprIntegratedCircuitWarehouseCommand();
        command.publicNo = exWarehouseVO.getPublicNo();
        command.publicDate = exWarehouseVO.getPublicDate();
        command.name = exWarehouseVO.getName();
        command.regNo = exWarehouseVO.getRegNo();
        command.applyDate = exWarehouseVO.getApplyDate();
        command.rightHolder = exWarehouseVO.getRightHolder();
        command.isRightHolderNaturalPerson = exWarehouseVO.getIsRightHolderNaturalPerson();
        command.rightHolderCompanyId = exWarehouseVO.getRightHolderCompanyId();
        command.rightHolderCompanyPersonId = exWarehouseVO.getRightHolderCompanyPersonId();
        command.rightHolderCountry = exWarehouseVO.getRightHolderCountry();
        command.rightHolderAddress = exWarehouseVO.getRightHolderAddress();
        command.designCreator = exWarehouseVO.getDesignCreator();
        command.completeDate = exWarehouseVO.getCompleteDate();
        command.typeName = exWarehouseVO.getTypeName();
        command.firstBusinessDate = exWarehouseVO.getFirstBusinessDate();



        return command;
    }

}
