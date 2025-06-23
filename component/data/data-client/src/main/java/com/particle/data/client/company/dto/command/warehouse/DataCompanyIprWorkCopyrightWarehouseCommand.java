package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprWorkCopyrightExWarehouseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

/**
 * <p>
 * 企业知识产权作品著作入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyIprWorkCopyrightWarehouseCommand extends AbstractBaseCommand {



    @Schema(description = "注册号")
    private String regNo;


    @Schema(description = "作品名称")
    private String name;


    @Schema(description = "作品类别")
    private String typeName;


    @Schema(description = "著作权人")
    private String copyrightOwner;


    @Schema(description = "是否著作权人为自然人")
    private Boolean isCopyrightOwnerNaturalPerson;


    @Schema(description = "著作权人公司id")
    private Long copyrightOwnerCompanyId;


    @Schema(description = "著作权人个人id")
    private Long copyrightOwnerCompanyPersonId;


    @Schema(description = "国家")
    private String country;


    @Schema(description = "省")
    private String province;


    @Schema(description = "市")
    private String city;


    @Schema(description = "作者")
    private String author;


    @Schema(description = "创作完成日期")
    private LocalDate completeDate;


    @Schema(description = "首次发表日期")
    private LocalDate firstPublicDate;


    @Schema(description = "登记日期")
    private LocalDate regDate;


    @Schema(description = "发布日期")
    private LocalDate publicDate;



    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return StrUtil.isEmpty(regNo)
                && StrUtil.isEmpty(name)
                && StrUtil.isEmpty(typeName)
                && StrUtil.isEmpty(copyrightOwner)
                && Objects.isNull(isCopyrightOwnerNaturalPerson)
                && Objects.isNull(copyrightOwnerCompanyId)
                && Objects.isNull(copyrightOwnerCompanyPersonId)
                && StrUtil.isEmpty(country)
                && StrUtil.isEmpty(province)
                && StrUtil.isEmpty(city)
                && StrUtil.isEmpty(author)
                && Objects.isNull(completeDate)
                && Objects.isNull(firstPublicDate)
                && Objects.isNull(regDate)
                && Objects.isNull(publicDate)
;
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyIprWorkCopyrightExWarehouseVO exWarehouseVO) {
                if (Objects.equals(regNo, exWarehouseVO.getRegNo())) {
            this.regNo = null;
        }
        if (Objects.equals(name, exWarehouseVO.getName())) {
            this.name = null;
        }
        if (Objects.equals(typeName, exWarehouseVO.getTypeName())) {
            this.typeName = null;
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
        if (Objects.equals(country, exWarehouseVO.getCountry())) {
            this.country = null;
        }
        if (Objects.equals(province, exWarehouseVO.getProvince())) {
            this.province = null;
        }
        if (Objects.equals(city, exWarehouseVO.getCity())) {
            this.city = null;
        }
        if (Objects.equals(author, exWarehouseVO.getAuthor())) {
            this.author = null;
        }
        if (Objects.equals(completeDate, exWarehouseVO.getCompleteDate())) {
            this.completeDate = null;
        }
        if (Objects.equals(firstPublicDate, exWarehouseVO.getFirstPublicDate())) {
            this.firstPublicDate = null;
        }
        if (Objects.equals(regDate, exWarehouseVO.getRegDate())) {
            this.regDate = null;
        }
        if (Objects.equals(publicDate, exWarehouseVO.getPublicDate())) {
            this.publicDate = null;
        }

    }

    public static DataCompanyIprWorkCopyrightWarehouseCommand createByDataCompanyIprWorkCopyrightExWarehouseVO(DataCompanyIprWorkCopyrightExWarehouseVO exWarehouseVO) {
        DataCompanyIprWorkCopyrightWarehouseCommand command = new DataCompanyIprWorkCopyrightWarehouseCommand();
        command.regNo = exWarehouseVO.getRegNo();
        command.name = exWarehouseVO.getName();
        command.typeName = exWarehouseVO.getTypeName();
        command.copyrightOwner = exWarehouseVO.getCopyrightOwner();
        command.isCopyrightOwnerNaturalPerson = exWarehouseVO.getIsCopyrightOwnerNaturalPerson();
        command.copyrightOwnerCompanyId = exWarehouseVO.getCopyrightOwnerCompanyId();
        command.copyrightOwnerCompanyPersonId = exWarehouseVO.getCopyrightOwnerCompanyPersonId();
        command.country = exWarehouseVO.getCountry();
        command.province = exWarehouseVO.getProvince();
        command.city = exWarehouseVO.getCity();
        command.author = exWarehouseVO.getAuthor();
        command.completeDate = exWarehouseVO.getCompleteDate();
        command.firstPublicDate = exWarehouseVO.getFirstPublicDate();
        command.regDate = exWarehouseVO.getRegDate();
        command.publicDate = exWarehouseVO.getPublicDate();

        return command;
    }
}
