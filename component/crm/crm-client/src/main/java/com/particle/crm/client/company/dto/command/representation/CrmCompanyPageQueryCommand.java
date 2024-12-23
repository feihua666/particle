package com.particle.crm.client.company.dto.command.representation;

import com.particle.common.client.dto.command.tree.AbstractBaseTreePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import com.particle.global.light.share.mybatis.anno.OrderBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 客户公司 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-04-11 13:44:00
 */
@OrderBy("seq")
@Data
@Schema
public class CrmCompanyPageQueryCommand extends AbstractBaseTreePageQueryCommand {



    @Like
        @Schema(description = "公司编码,左前缀匹配")
    private String code;


    @Like
        @Schema(description = "公司名称,左前缀匹配")
    private String name;


    @Like
        @Schema(description = "公司简称名称,左前缀匹配")
    private String nameSimple;


    @Schema(description = "备注")
    private String remark;


    @Schema(description = "排序")
    private Integer seq;





















}
