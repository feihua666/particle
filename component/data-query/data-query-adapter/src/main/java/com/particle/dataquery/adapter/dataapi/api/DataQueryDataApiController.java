package com.particle.dataquery.adapter.dataapi.api;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.particle.common.adapter.api.AbstractBaseApiAdapter;
import com.particle.dataquery.client.dataapi.api.representation.IDataQueryDataApiRepresentationApplicationService;
import com.particle.dataquery.client.dataapi.dto.command.representation.DataQueryDataApiQueryCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 数据查询数据接口前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * 基础路径 {@link DataQueryDataApiController#API_REQUEST_MAPPING} 中 dq 表示 data_query的缩写，为了缩短路径
 * </p>
 *
 * @author yw
 * @since 2023-03-21 13:11:05
 */
@Api(tags = "数据查询数据服务接口")
@RestController
@RequestMapping(DataQueryDataApiController.API_REQUEST_MAPPING)
public class DataQueryDataApiController extends AbstractBaseApiAdapter {
	public static final String API_REQUEST_MAPPING = "/api/dq";
	public static final String API_ENTRY = "";
	public static final String API_ENTRY_PREFIX = API_REQUEST_MAPPING + API_ENTRY;

	@Autowired
	private IDataQueryDataApiRepresentationApplicationService iDataQueryDataApiRepresentationApplicationService;


	@ApiOperation("数据查询数据服务接口入口")
	@PostMapping(API_ENTRY  + "/**")
	public Object dataQueryDataApiEntry(@RequestBody Object param,HttpServletRequest request){
		DataQueryDataApiQueryCommand dataQueryDataApiQueryCommand = new DataQueryDataApiQueryCommand();
		dataQueryDataApiQueryCommand.setUrl(getDataApiUrl(request));
		dataQueryDataApiQueryCommand.setParam(param);

		return iDataQueryDataApiRepresentationApplicationService.dataApiQuery(dataQueryDataApiQueryCommand);
	}

	/**
	 * 获取 dataApi 中的自定义地址
	 * @param request
	 * @return
	 */
	private String getDataApiUrl(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		requestURI = URLUtil.decode(requestURI);

		String dataApiUrl = StrUtil.subAfter(requestURI, getPrefix(), false);

		return dataApiUrl;
	}

	/**
	 * 获取地址请求前缀
	 * @return
	 */
	public String getPrefix(){
		return API_ENTRY_PREFIX;
	}
}