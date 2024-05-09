package com.particle.crm.client.tag.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.mybatis.anno.Like;
/**
 * <p>
 * 客户标签 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:32:09
 */
@Data
@Schema
public class CrmCustomerTagPageQueryCommand extends AbstractBasePageQueryCommand {



    @Like
        @Schema(description = "标签编码,左前缀匹配")
    private String code;


    @Like
        @Schema(description = "标签名称,左前缀匹配")
    private String name;


    @Schema(description = "备注")
    private String remark;









}
