package com.particle.crm.client.company.dto.command.representation;
import com.particle.common.client.dto.command.tree.AbstractBaseTreePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.mybatis.anno.Like;
/**
 * <p>
 * 客户公司部门 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-04-24 10:16:52
 */
@Data
@Schema
public class CrmDeptPageQueryCommand extends AbstractBaseTreePageQueryCommand {



    @Like
        @Schema(description = "部门编码,左前缀匹配")
    private String code;


    @Like
        @Schema(description = "部门名称,左前缀匹配")
    private String name;


    @Schema(description = "客户公司id")
    private Long crmCompanyId;


    @Schema(description = "备注")
    private String remark;


    @Schema(description = "排序")
    private Integer seq;





















}
