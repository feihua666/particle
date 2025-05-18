package com.particle.data.client.company.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * <p>
 * 企业 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:44
 */
@Data
@Schema
public class DataCompanyPageQueryCommand extends AbstractBasePageQueryCommand {



    @Schema(description = "企业名称")
    private String name;


    @Schema(description = "统一社会信用代码")
    private String uscc;


    @Schema(description = "注册号")
    private String regNo;


    @Schema(description = "组织机构代码")
    private String orgCode;


    @Schema(description = "英文名称")
    private String enName;


    @Schema(description = "父级id")
    private Long parentId;


    @Schema(description = "最后更新时间")
    private LocalDateTime latestUpdateAt;

	@Schema(description = "最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动")
	private LocalDateTime latestHandleAt;









}
