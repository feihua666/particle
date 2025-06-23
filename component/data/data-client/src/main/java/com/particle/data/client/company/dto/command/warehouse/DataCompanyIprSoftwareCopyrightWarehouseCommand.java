package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprSoftwareCopyrightExWarehouseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

/**
 * <p>
 * 企业知识产权软件著作入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyIprSoftwareCopyrightWarehouseCommand extends AbstractBaseCommand {



    @Schema(description = "注册号")
    private String regNo;


    @Schema(description = "分类号")
    private String categoryNo;


    @Schema(description = "软件全称")
    private String name;


    @Schema(description = "软件简称")
    private String nameSimple;


    @Schema(description = "版本号")
    private String versionNo;


    @Schema(description = "著作权人")
    private String copyrightOwner;


    @Schema(description = "是否著作权人为自然人")
    private Boolean isCopyrightOwnerNaturalPerson;


    @Schema(description = "著作权人公司id")
    private Long copyrightOwnerCompanyId;


    @Schema(description = "著作权人个人id")
    private Long copyrightOwnerCompanyPersonId;


    @Schema(description = "首次发表日期")
    private LocalDate firstPublicDate;


    @Schema(description = "登记日期")
    private LocalDate regDate;


    @Schema(description = "国家")
    private String country;

    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return StrUtil.isEmpty(regNo)
                && StrUtil.isEmpty(categoryNo)
                && StrUtil.isEmpty(name)
                && StrUtil.isEmpty(nameSimple)
                && StrUtil.isEmpty(versionNo)
                && StrUtil.isEmpty(copyrightOwner)
                && Objects.isNull(isCopyrightOwnerNaturalPerson)
                && Objects.isNull(copyrightOwnerCompanyId)
                && Objects.isNull(copyrightOwnerCompanyPersonId)
                && Objects.isNull(firstPublicDate)
                && Objects.isNull(regDate)
                && StrUtil.isEmpty(country)
;
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyIprSoftwareCopyrightExWarehouseVO exWarehouseVO) {
        if (Objects.equals(regNo, exWarehouseVO.getRegNo())) {
            this.regNo = null;
        }
        if (Objects.equals(categoryNo, exWarehouseVO.getCategoryNo())) {
            this.categoryNo = null;
        }
        if (Objects.equals(name, exWarehouseVO.getName())) {
            this.name = null;
        }
        if (Objects.equals(nameSimple, exWarehouseVO.getNameSimple())) {
            this.nameSimple = null;
        }
        if (Objects.equals(versionNo, exWarehouseVO.getVersionNo())) {
            this.versionNo = null;
        }
        if (Objects.equals(copyrightOwner, exWarehouseVO.getCopyrightOwner())) {
            this.copyrightOwner = null;
        }
        if (Objects.equals(isCopyrightOwnerNaturalPerson, exWarehouseVO.getIsCopyrightOwnerNaturalPerson())) {
            this.isCopyrightOwnerNaturalPerson = null;
        }
        if (Objects.equals(copyrightOwnerCompanyId, exWarehouseVO.getCopyrightOwnerCompanyId())) {
            this.copyrightOwnerCompanyId = null;
        }
        if (Objects.equals(copyrightOwnerCompanyPersonId, exWarehouseVO.getCopyrightOwnerCompanyPersonId())) {
            this.copyrightOwnerCompanyPersonId = null;
        }
        if (Objects.equals(firstPublicDate, exWarehouseVO.getFirstPublicDate())) {
            this.firstPublicDate = null;
        }
        if (Objects.equals(regDate, exWarehouseVO.getRegDate())) {
            this.regDate = null;
        }
        if (Objects.equals(country, exWarehouseVO.getCountry())) {
            this.country = null;
        }
    }

    public static DataCompanyIprSoftwareCopyrightWarehouseCommand createByDataCompanyIprSoftwareCopyrightExWarehouseVO(DataCompanyIprSoftwareCopyrightExWarehouseVO exWarehouseVO) {
        DataCompanyIprSoftwareCopyrightWarehouseCommand command = new DataCompanyIprSoftwareCopyrightWarehouseCommand();
        command.regNo = exWarehouseVO.getRegNo();
        command.categoryNo = exWarehouseVO.getCategoryNo();
        command.name = exWarehouseVO.getName();
        command.nameSimple = exWarehouseVO.getNameSimple();
        command.versionNo = exWarehouseVO.getVersionNo();
        command.copyrightOwner = exWarehouseVO.getCopyrightOwner();
        command.isCopyrightOwnerNaturalPerson = exWarehouseVO.getIsCopyrightOwnerNaturalPerson();
        command.copyrightOwnerCompanyId = exWarehouseVO.getCopyrightOwnerCompanyId();
        command.copyrightOwnerCompanyPersonId = exWarehouseVO.getCopyrightOwnerCompanyPersonId();
        command.firstPublicDate = exWarehouseVO.getFirstPublicDate();
        command.regDate = exWarehouseVO.getRegDate();
        command.country = exWarehouseVO.getCountry();

        return command;
    }

}
