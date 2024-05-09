package com.particle.crm.client.relation.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 客户与客户关系 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:30:39
 */
@Data
@Schema
public class CrmCustomerRelationQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "客户id")
    private Long crmCustomerId;


    @Schema(description = "另一个客户id")
    private Long anotherCrmCustomerId;


    @Schema(description = "关系id")
    private Long crmCustomerRelationDefineId;


    @Schema(description = "关系详情描述")
    private String relationDetail;









}
