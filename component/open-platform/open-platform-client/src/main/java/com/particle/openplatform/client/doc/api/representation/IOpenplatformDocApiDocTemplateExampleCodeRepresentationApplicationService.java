package com.particle.openplatform.client.doc.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocTemplateExampleCodePageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocTemplateExampleCodeQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocTemplateExampleCodeVO;

/**
 * <p>
 * 开放接口文档模板示例代码 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IOpenplatformDocApiDocTemplateExampleCodeRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocApiDocTemplateExampleCodeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocApiDocTemplateExampleCodeVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param openplatformDocApiDocTemplateExampleCodeQueryListCommand
	 * @return
	 */
	MultiResponse<OpenplatformDocApiDocTemplateExampleCodeVO> queryList(OpenplatformDocApiDocTemplateExampleCodeQueryListCommand openplatformDocApiDocTemplateExampleCodeQueryListCommand);

	/**
	 * 分页查询
	 * @param openplatformDocApiDocTemplateExampleCodePageQueryCommand
	 * @return
	 */
	PageResponse<OpenplatformDocApiDocTemplateExampleCodeVO> pageQuery(OpenplatformDocApiDocTemplateExampleCodePageQueryCommand openplatformDocApiDocTemplateExampleCodePageQueryCommand);

}
