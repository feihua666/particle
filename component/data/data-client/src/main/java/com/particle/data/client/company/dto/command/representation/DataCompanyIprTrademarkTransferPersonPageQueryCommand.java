package com.particle.data.client.company.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业知识产权商标转让人 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:13
 */
@Data
@Schema
public class DataCompanyIprTrademarkTransferPersonPageQueryCommand extends AbstractBasePageQueryCommand {



    @Schema(description = "企业知识产权商标转让表id")
    private Long companyIprTrademarkTransferId;


    @Schema(description = "原始转让人名称")
    private String transferName;


    @Schema(description = "中文转让人名称")
    private String transferNameCn;


    @Schema(description = "英文转让人名称")
    private String transferNameEn;


    @Schema(description = "是否被转让人")
    private Boolean isTransferred;


    @Schema(description = "数据md5")
    private String dataMd5;


    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
    








}
