package com.particle.data.client.company.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业知识产权集成电路 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:28
 */
@Data
@Schema
public class DataCompanyIprIntegratedCircuitPageQueryCommand extends AbstractBasePageQueryCommand {



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
    

    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
    








}
