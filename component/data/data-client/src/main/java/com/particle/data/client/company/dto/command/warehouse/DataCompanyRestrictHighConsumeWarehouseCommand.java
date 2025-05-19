package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyRestrictHighConsumeExWarehouseVO;
import com.particle.data.common.tool.SomeMd5Tool;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Objects;
import cn.hutool.core.util.NumberUtil;

/**
 * <p>
 * 企业限制高消费入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyRestrictHighConsumeWarehouseCommand extends AbstractBaseCommand {


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

    @Schema(description = "数据md5")
    private String dataMd5;

    public String obtainDataMd5() {
        if (StrUtil.isEmpty(dataMd5)) {
            dataMd5 = SomeMd5Tool.dataCompanyRestrictHighConsumeDataMd5(caseNo,fileCaseDate,publishDate,executeCourtName);
        }
        return dataMd5;
    }

    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return StrUtil.isEmpty(caseNo)
                && StrUtil.isEmpty(attachUrl)
                && StrUtil.isEmpty(attachSnapshotUrl)
                && Objects.isNull(fileCaseDate)
                && Objects.isNull(publishDate)
                && Objects.isNull(executeCourtCompanyId)
                && StrUtil.isEmpty(executeCourtName);
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyRestrictHighConsumeExWarehouseVO exWarehouseVO) {
        if (Objects.equals(caseNo, exWarehouseVO.getCaseNo())) {
            this.caseNo = null;
        }
        if (Objects.equals(attachUrl, exWarehouseVO.getAttachUrl())) {
            this.attachUrl = null;
        }
        if (Objects.equals(attachSnapshotUrl, exWarehouseVO.getAttachSnapshotUrl())) {
            this.attachSnapshotUrl = null;
        }
        if (Objects.equals(fileCaseDate, exWarehouseVO.getFileCaseDate())) {
            this.fileCaseDate = null;
        }
        if (Objects.equals(publishDate, exWarehouseVO.getPublishDate())) {
            this.publishDate = null;
        }
        if (Objects.equals(executeCourtCompanyId, exWarehouseVO.getExecuteCourtCompanyId())) {
            this.executeCourtCompanyId = null;
        }
        if (Objects.equals(executeCourtName, exWarehouseVO.getExecuteCourtName())) {
            this.executeCourtName = null;
        }

    }

    public static DataCompanyRestrictHighConsumeWarehouseCommand createByDataCompanyRestrictHighConsumeExWarehouseVO(DataCompanyRestrictHighConsumeExWarehouseVO dataCompanyBasicWarehouseCommand){
        DataCompanyRestrictHighConsumeWarehouseCommand command = new DataCompanyRestrictHighConsumeWarehouseCommand();
        command.caseNo = dataCompanyBasicWarehouseCommand.getCaseNo();
        command.attachUrl = dataCompanyBasicWarehouseCommand.getAttachUrl();
        command.attachSnapshotUrl = dataCompanyBasicWarehouseCommand.getAttachSnapshotUrl();
        command.fileCaseDate = dataCompanyBasicWarehouseCommand.getFileCaseDate();
        command.publishDate = dataCompanyBasicWarehouseCommand.getPublishDate();
        command.executeCourtCompanyId = dataCompanyBasicWarehouseCommand.getExecuteCourtCompanyId();
        command.executeCourtName = dataCompanyBasicWarehouseCommand.getExecuteCourtName();

        return command;
    }
}
