package com.particle.global.dataaudit.audit;

import com.particle.global.dataaudit.audit.dto.DataAuditResultWithOpLogDTO;
import com.particle.global.dataaudit.op.OpLogAspect;

import java.util.List;

/**
 * <p>
 * 默认的数据审计结果处理器，该处理器主要配合操作日志
 * </p>
 *
 * @author yangwei
 * @since 2023-05-06 15:32
 */
public class DefaultDataAuditResultHandler implements IDataAuditResultHandler{
	@Override
	public void handle(List<DataAuditResultWithOpLogDTO> dataAuditResultWithOpLogDTOS) {

		/**
		 * 这里只做收集到本地线程变量中，统一由 {@link OpLogAspect} 处理
		 * 如果这里添加别的收集，需要同步修改 {@link DataAuditTransactionalEventListener#handleCustomHandlerApplicationEvent(com.particle.global.dataaudit.audit.DataAuditCustomHandlerApplicationEvent)} 中异步获取逻辑
		 */
		DataAuditCollectTool.collectResultWithOpLog(dataAuditResultWithOpLogDTOS);
	}
}
