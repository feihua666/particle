package com.particle.dataquery.infrastructure.dataapi.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseTreeDO;
import lombok.Data;
/**
 * <p>
 * 数据查询数据接口表
 * </p>
 *
 * @author yw
 * @since 2023-03-21 13:11:05
 */
@Data
@TableName("component_data_query_data_api")
public class DataQueryDataApiDO extends BaseTreeDO {

    /**
    * 接口地址
    */
    private String url;

    /**
    * 接口名称
    */
    private String name;

    /**
    * 数据查询数据源接口id，接口直连时使用
    */
    private Long dataQueryDatasourceApiId;

    /**
    * 适配类型，字典id，直连、聚合等
    */
    private Long adaptTypeDictId;

    /**
    * 接口适配配置json
    */
    private String adaptConfigJson;

    /**
    * 入参类型，字典id，为空表示无入参
    */
    private Long inParamTypeDictId;

    /**
    * 入参示例，纯文本展示
    */
    private String inParamExampleConfigJson;

    /**
    * 入参测试用例数据，该数据应该配合入参配置json或json配置识别
    */
    private String inParamTestCaseDataConfigJson;

    /**
    * 入参文档配置json
    */
    private String inParamDocConfigJson;
    /**
     * 入参校验配置json
     */
    private String inParamValidateConfigJson;

	/**
	 * 入参扩展配置json
	 */
	private String inParamExtConfigJson;
    /**
    * 出参类型，字典id
    */
    private Long outParamTypeDictId;

    /**
    * 出参示例，纯文本展示
    */
    private String outParamExampleConfigJson;

    /**
    * 出参文档配置json
    */
    private String outParamDocConfigJson;

    /**
    * 出参成功或失败配置json
    */
    private String outParamSuccessConfigJson;

	/**
	 * 出参翻译配置json
	 */
	private String outParamTransConfigJson;

	/**
	 * 出参扩展配置json
	 */
	private String outParamExtConfigJson;

    /**
    * 输出类型，字典id，用来定义响应数据格式
    */
    private Long responseTypeDictId;

    /**
    * 分页适配信息配置json
    */
    private String pageableAdapterConfigJson;

    /**
    * 字典配置json，为入参和出参字典提供支持
    */
    private String dictConfigJson;

	/**
	 * 是否使用远程服务
	 */
	private Boolean isUseRemote;

	/**
	 * 是否已发布，已发布不能修改和删除
	 */
	private Boolean isPublished;

	/**
	 * 是否为主版本，非主版本视为开发版本
	 */
	private Boolean isMaster;

	/**
	 * 主版本id
	 */
	private Long masterId;

	/**
	 * 是否测试通过，测试通过才能发布
	 */
	private Boolean isTestPassed;

	/**
	 * 数据查询供应商id,用于支持开放接口
	 */
	private Long dataQueryProviderId;

	/**
	 * 接口标识，一般与url对等,用于支持开放接口
	 */
	private String apiIdentifier;

	/**
	 * 接口版本标识，标识同一个接口的不同版本,用于支持开放接口
	 */
	private String apiVersion;

	/**
	 * 是否支持入库,用于支持开放接口
	 */
	private Boolean isSupportWarehouse;

    /**
    * 描述,注意事项等
    */
    private String remark;


}