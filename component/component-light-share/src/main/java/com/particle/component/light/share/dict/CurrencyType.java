package com.particle.component.light.share.dict;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 币种 字典项
 * </p>
 *
 * @author yw
 * @since 2024-02-27 13:38:48
 */
public enum CurrencyType implements IDictItem {

	/**
	 * 人民币
	 */
	CNY
	,
	/**
	 * 美元
	 */
	USD
	,
	/**
	 * 日元
	 */
	JPY
	,
	/**
	 * 欧元
	 */
	EUR
	,
	/**
	 * 英镑
	 */
	GBP
	,
	/**
	 * 德国马克
	 */
	DEM
	,
	/**
	 * 瑞士法郎
	 */
	CHF
	,
	/**
	 * 法国法郎
	 */
	FRF
	,
	/**
	 * 加拿大元
	 */
	CAD
	,
	/**
	 * 澳大利亚元
	 */
	AUD
	,
	/**
	 * 港币
	 */
	HKD
	,
	/**
	 * 奥地利先令
	 */
	ATS
	,
	/**
	 * 芬兰马克
	 */
	FIM
	,
	/**
	 * 比利时法郎
	 */
	BEF
	,
	/**
	 * 爱尔兰镑
	 */
	IEP
	,
	/**
	 * 意大利里拉
	 */
	ITL
	,
	/**
	 * 卢森堡法郎
	 */
	LUF
	,
	/**
	 * 荷兰盾
	 */
	NLG
	,
	/**
	 * 葡萄牙埃斯库多
	 */
	PTE
	,
	/**
	 * 西班牙比塞塔
	 */
	ESP
	,
	/**
	 * 印尼盾
	 */
	IDR
	,
	/**
	 * 马来西亚林吉特
	 */
	MYR
	,
	/**
	 * 新西兰元
	 */
	NZD
	,
	/**
	 * 菲律宾比索
	 */
	PHP
	,
	/**
	 * 俄罗斯卢布
	 */
	SUR
	,
	/**
	 * 新加坡元
	 */
	SGD
	,
	/**
	 * 韩国元
	 */
	KRW
	,
	/**
	 * 泰铢
	 */
	THB
	;

	@Override
	public String itemValue() {
		return this.name();
	}

	@Override
	public String groupCode() {
		return Group.currency_type.groupCode();
	}

	/**
	 * 币种 字典组
	 */
	public enum Group implements IDictGroup {
		currency_type;

		@Override
		public String groupCode() {
			return this.name();
		}
	}
}

