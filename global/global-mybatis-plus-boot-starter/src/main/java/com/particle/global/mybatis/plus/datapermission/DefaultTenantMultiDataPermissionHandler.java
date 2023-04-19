package com.particle.global.mybatis.plus.datapermission;

import com.particle.global.security.tenant.TenantTool;
import lombok.Data;
import lombok.SneakyThrows;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Table;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * <p>
 * 默认的租户权限过滤处理器
 * </p>
 * tmdp 代表 TenantMultiDataPermission 首字母即租户多表数据权限
 * @author yangwei
 * @since 2023-04-14 23:24
 */
@Data
public class DefaultTenantMultiDataPermissionHandler implements TenantMultiDataPermissionHandler {

	private static final String tenantIdPlaceholder ="{tenantId}";

	/**
	 * 租户级数据权限sql片段
	 * key=表名
	 * value=数据权限条件限制
	 */
	private Map<String, String> tenantDataPermmision;

	@SneakyThrows
	@Override
	public Expression getSqlSegment(Table table, Expression where, String mappedStatementId) {
		if (tenantDataPermmision != null && TenantTool.isTenantEnable() && tenantDataPermmision.containsKey(table.getName())) {
			Long tenantId = TenantTool.getTenantId();
			if (tenantId != null && tenantId >= TenantMultiDataPermissionHandler.minTenantIdUseTenantDataPermisson) {
				String dpSegment = tenantDataPermmision.get(table.getName());
				dpSegment = dpSegment.replace(tenantIdPlaceholder, tenantId.toString());
				return CCJSqlParserUtil.parseCondExpression(dpSegment);
			}
		}
		return where;
	}
}
