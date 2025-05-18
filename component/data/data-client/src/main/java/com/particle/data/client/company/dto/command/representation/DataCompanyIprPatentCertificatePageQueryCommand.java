package com.particle.data.client.company.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业知识产权专利证书信息 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:02
 */
@Data
@Schema
public class DataCompanyIprPatentCertificatePageQueryCommand extends AbstractBasePageQueryCommand {



    @Schema(description = "企业知识产权专利表id")
    private Long companyIprPatentId;


    @Schema(description = "专利证书发文日")
    private LocalDate publicDate;
    

    @Schema(description = "专利证书挂号")
    private String mailNo;


    @Schema(description = "专利证书收件人姓名")
    private String receiverName;


    @Schema(description = "专利证书收件人地址")
    private String receiverAddress;

	@Schema(description = "数据md5,public_date + mail_no + receiver_name + receiver_address")
	private String dataMd5;


    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
    








}