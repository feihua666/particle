package com.particle.data.client.company.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业限制高消费 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:19
 */
@Data
@Schema
public class DataCompanyRestrictHighConsumeQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "企业表ID")
    private Long companyId;


    @Schema(description = "案号")
    private String caseNo;


    @Schema(description = "文件链接")
    private String attachUrl;


    @Schema(description = "文件快照链接")
    private String attachSnapshotUrl;


    @Schema(description = "立案日期")
    private LocalDate fileCaseDate;
    

    @Schema(description = "发布日期")
    private LocalDate publishDate;
    

    @Schema(description = "执行法院公司id")
    private Long executeCourtCompanyId;


    @Schema(description = "执行法院名称")
    private String executeCourtName;

	@Schema(description = "数据md5,case_no + file_case_date + publish_date + execute_court_name")
	private String dataMd5;


    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
    








}