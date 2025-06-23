package com.particle.data.client.company.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业知识产权作品著作 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:45
 */
@Data
@Schema
public class DataCompanyIprWorkCopyrightQueryListCommand extends AbstractBaseQueryCommand {



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
    

    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
    








}
