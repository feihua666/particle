package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.global.validation.props.PropValid;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 企业融资产品表ID分配企业竞品 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:47:00
 */
@PropValid
@Data
@EqualsAndHashCode(callSuper=false)
@Accessors(chain = true)
@Schema(description = "企业融资产品表ID分配企业竞品表单对象")
public class CompanyVcProductAssignCompanyVcCompetitiveProductCommand extends AbstractBaseCommand {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "企业融资产品表IDid不能为空")
    @Schema(description = "企业融资产品表IDid")
    private Long companyVcProductId;

    @Schema(description = "选择的企业竞品id")
    private List<Long> checkedCompanyVcCompetitiveProductIds;

    @PropValid.DependCondition(message = "未选择的企业竞品id不能为空",dependProp = "isLazyLoad",ifEqual = "true")
    @Schema(title = "未选择的企业竞品id",description = "如果为懒加载请传该值")
    private List<Long> uncheckedCompanyVcCompetitiveProductIds;

    @Schema(description = "页面可选择的数据是否为懒加载")
    private Boolean isLazyLoad = false;

}
