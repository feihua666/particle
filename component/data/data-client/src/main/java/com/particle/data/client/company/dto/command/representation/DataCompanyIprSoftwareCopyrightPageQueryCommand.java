package com.particle.data.client.company.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业知识产权软件著作 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:01
 */
@Data
@Schema
public class DataCompanyIprSoftwareCopyrightPageQueryCommand extends AbstractBasePageQueryCommand {



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


    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
    








}
