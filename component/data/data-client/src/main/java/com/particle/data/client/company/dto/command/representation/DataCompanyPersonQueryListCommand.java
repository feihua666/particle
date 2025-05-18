package com.particle.data.client.company.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业个人 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:50
 */
@Data
@Schema
public class DataCompanyPersonQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "姓名")
    private String name;


    @Schema(description = "证号")
    private String idNo;

	@Schema(description = "脱敏证号")
	private String idNoDesensitized;

	@Schema(description = "证号md5")
	private String idNoMd5;

	@Schema(description = "证号sha256")
	private String idNoSha256;

	@Schema(description = "证号sm3")
	private String idNoSm3;

	@Schema(description = "证号密文")
	private String idNoEncrypt;


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


    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
    








}
