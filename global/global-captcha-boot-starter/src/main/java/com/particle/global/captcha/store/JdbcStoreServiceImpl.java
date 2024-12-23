package com.particle.global.captcha.store;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.ImmutableMap;
import com.particle.global.captcha.CustomCaptchaScene;
import com.particle.global.captcha.CustomCaptchaType;
import com.particle.global.captcha.gen.CaptchaGenResultDTO;
import com.particle.global.tool.json.JsonTool;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * <p>
 * jdbc 存储实现
 * </p>
 *
 * @author yangwei
 * @since 2023-04-25 11:48
 */
public class JdbcStoreServiceImpl extends AbstractCaptchaStoreService{
	public static String tableName = "global_captcha";
	public static String idColumn = "ID";
	public static String idProperty = "id";
	public static String createAtColumn = "CREATE_AT";
	public static String createAtProperty = "createAt";

	public static String deleteAllSql = String.format("DELETE FROM %s;", tableName);



	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public JdbcStoreServiceImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public boolean doSave(CaptchaGenResultDTO captchaGenResultDTO) {
		String sql = "INSERT INTO "+ tableName +" (ID,CONTENT_JSON, IS_DYNAMIC,CREATE_AT) VALUES (:id,:contentJson, :isDynamic,:createAt);";

		int update = namedParameterJdbcTemplate.update(sql,
				ImmutableMap.of(
						"id", captchaGenResultDTO.getCaptchaUniqueIdentifier(),
						"contentJson", JsonTool.toJsonStr(captchaGenResultDTO),
						// 值不能为null
						"isDynamic", captchaGenResultDTO.getIsDynamic() != null && captchaGenResultDTO.getIsDynamic() ? 1 : 0,
						"createAt", LocalDateTime.now()
				)
		);
		return update > 0;
	}

	@Override
	public CaptchaGenResultDTO doGet(String captchaUniqueIdentifier) {
		String sql = "select ID,CONTENT_JSON, IS_DYNAMIC,CREATE_AT from "+ tableName +" where id = :id;";

		Map<String, Object> stringObjectMap = null;
		try {
			stringObjectMap = namedParameterJdbcTemplate.queryForMap(sql, ImmutableMap.of(
					"id", captchaUniqueIdentifier
			));
		} catch (EmptyResultDataAccessException e) {
		}
		if (stringObjectMap != null) {
			String contentJson = stringObjectMap.get("CONTENT_JSON").toString();
			JSONObject contentJsonObj = JSONUtil.parseObj(contentJson);
			JSONObject captchaScene = contentJsonObj.getJSONObject("captchaScene");
			String captchaType = contentJsonObj.getStr("captchaType");
			// 是一个接口
			contentJsonObj.remove("captchaScene");
			// 是一个接口
			contentJsonObj.remove("captchaType");
			CaptchaGenResultDTO captchaGenResultDTO = JSONUtil.toBean(contentJsonObj, CaptchaGenResultDTO.class);
			captchaGenResultDTO.setCaptchaScene(CustomCaptchaScene.create(captchaScene.getStr("scene")));
			captchaGenResultDTO.setCaptchaType(CustomCaptchaType.create(captchaType));
			return captchaGenResultDTO;
		}
		return null;
	}

	@Override
	public boolean doRemove(String captchaUniqueIdentifier) {
		String sql = "delete from "+ tableName +" where id = :id;";

		int update = namedParameterJdbcTemplate.update(sql, ImmutableMap.of(
				"id", captchaUniqueIdentifier
		));
		return update > 0;
	}
}
