package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyPersonExWarehouseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Objects;

/**
 * <p>
 * 企业个人入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyPersonWarehouseCommand extends AbstractBaseCommand {



    @NotEmpty(message = "姓名 不能为空")
    @Schema(description = "姓名",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(description = "证号")
    private String idNo;

	@Schema(description = "脱敏证号")
	private String idNoDesensitized;


    @Schema(description = "手机号")
    private String mobile;


    @Schema(description = "手机号运营商")
    private Long mobileOperatorDictId;


    @Schema(description = "手机号1")
    private String mobile1;


    @Schema(description = "手机号1运营商")
    private Long mobile1OperatorDictId;


    @Schema(description = "手机号2")
    private String mobile2;


    @Schema(description = "手机号2运营商")
    private Long mobile2OperatorDictId;

	@Schema(description = "处理备注，用来标识一些处理备注")
	private String handleRemark;


    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return StrUtil.isEmpty(name)
                && StrUtil.isEmpty(idNo)
                && StrUtil.isEmpty(idNoDesensitized)
                && StrUtil.isEmpty(mobile)
                && Objects.isNull(mobileOperatorDictId)
                && StrUtil.isEmpty(mobile1)
                && Objects.isNull(mobile1OperatorDictId)
                && StrUtil.isEmpty(mobile2)
                && Objects.isNull(mobile2OperatorDictId)
                && StrUtil.isEmpty(handleRemark);
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyPersonExWarehouseVO exWarehouseVO) {
        if (Objects.equals(name, exWarehouseVO.getName())) {
            this.name = null;
        }
        if (Objects.equals(idNo, exWarehouseVO.getIdNo())) {
            this.idNo = null;
        }
        if (Objects.equals(idNoDesensitized, exWarehouseVO.getIdNoDesensitized())) {
            this.idNoDesensitized = null;
        }
        if (Objects.equals(mobile, exWarehouseVO.getMobile())) {
            this.mobile = null;
        }
        if (Objects.equals(mobileOperatorDictId, exWarehouseVO.getMobileOperatorDictId())) {
            this.mobileOperatorDictId = null;
        }
        if (Objects.equals(mobile1, exWarehouseVO.getMobile1())) {
            this.mobile1 = null;
        }
        if (Objects.equals(mobile1OperatorDictId, exWarehouseVO.getMobile1OperatorDictId())) {
            this.mobile1OperatorDictId = null;
        }
        if (Objects.equals(mobile2, exWarehouseVO.getMobile2())) {
            this.mobile2 = null;
        }
        if (Objects.equals(mobile2OperatorDictId, exWarehouseVO.getMobile2OperatorDictId())) {
            this.mobile2OperatorDictId = null;
        }
        if (Objects.equals(handleRemark, exWarehouseVO.getHandleRemark())) {
            this.handleRemark = null;
        }

    }

}
