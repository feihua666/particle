package com.particle.crm.client.tag.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 客户标签关系 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:32:22
 */
@Data
@Schema
public class CrmCustomerTagRelPageQueryCommand extends AbstractBasePageQueryCommand {



    @Schema(description = "客户id")
    private Long crmCustomerId;


    @Schema(description = "标签id")
    private Long crmCustomerTagId;









}
