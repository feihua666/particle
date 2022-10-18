package ${injection.pkg};

import ${injection.queryDetailForUpdateCommand.pkg}.${injection.queryDetailForUpdateCommand.className};
import ${injection.queryDetailCommand.pkg}.${injection.queryDetailCommand.className};
import ${injection.pageQueryCommand.pkg}.${injection.pageQueryCommand.className};
import ${injection.queryListCommand.pkg}.${injection.queryListCommand.className};
import ${injection.vo.pkg}.${injection.vo.className};
<#list injection.imports as im>
import ${im};
</#list>
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.common.client.api.IBaseApplicationService;

/**
 * <p>
 * ${injection.tableComment} 应用门面展示服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
public interface ${injection.className} extends IBaseApplicationService {

	<#if injection.method.queryDetailForUpdate>
	/**
	 * 查询详情，仅更新时使用
	 * @param ${injection.queryDetailForUpdateCommand.classNameVar}
	 * @return
	 */
	SingleResponse<${injection.vo.className}> queryDetailForUpdate(${injection.queryDetailForUpdateCommand.className} ${injection.queryDetailForUpdateCommand.classNameVar});
	</#if>

	<#if injection.method.queryDetail>
	/**
	 * 查询详情，仅展示详情使用
	 * @param ${injection.queryDetailCommand.classNameVar}
	 * @return
	 */
	SingleResponse<${injection.vo.className}> queryDetail(${injection.queryDetailCommand.className} ${injection.queryDetailCommand.classNameVar});
	</#if>

	<#if injection.method.queryList>
	/**
	 * 列表查询
	 * @param ${injection.queryListCommand.classNameVar}
	 * @return
	 */
	MultiResponse<${injection.vo.className}> queryList(${injection.queryListCommand.className} ${injection.queryListCommand.classNameVar});
	</#if>

	<#if injection.method.queryPage>
	/**
	 * 分页查询
	 * @param ${injection.pageQueryCommand.classNameVar}
	 * @return
	 */
	PageResponse<${injection.vo.className}> pageQuery(${injection.pageQueryCommand.className} ${injection.pageQueryCommand.classNameVar});
	</#if>

}
