package com.particle.global.dataaudit.audit;

import com.particle.global.tool.diff.DataAuditTool;
import org.javers.core.Javers;
import org.javers.core.diff.Change;

import java.util.List;

/**
 * <p>
 * 数据审计器
 * </p>
 *
 * @author yangwei
 * @since 2023-05-05 21:20
 */
public class DataAuditor {

	private final Javers javers;

	public DataAuditor(Javers javers) {
		this.javers = javers;
	}

	public List<Change> compare(Object oldVersion, Object newVersion) {
		return javers.compare(oldVersion, newVersion).getChanges();
	}

	public List<DataAuditTool.PropertyCompareResult> compareWithResults(Object oldVersion, Object newVersion) {
		List<Change> compare = compare(oldVersion, newVersion);
		return DataAuditTool.changesToPropertyCompareResults(compare);
	}
	/**
	 * 允许外部访问
	 * 这可以结合 {@link DataAuditTool} 实现更灵活的处理
	 * @return
	 */
	public Javers getJavers() {
		return javers;
	}


}
