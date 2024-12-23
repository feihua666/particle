package com.particle.crm.client.customer.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 客户联系方式 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:27:56
 */
@Data
@Schema
public class CrmCustomerContactPageQueryCommand extends AbstractBasePageQueryCommand {



    @Schema(description = "客户id")
    private Long crmCustomerId;


    @Schema(description = "联系方式类型")
    private Long contactTypeDictId;


    @Like
    @Schema(description = "联系方式，左前缀匹配")
    private String contact;


    @Schema(description = "备注")
    private String remark;









}
