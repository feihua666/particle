package com.particle.global.dataaudit.audit;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.global.dataaudit.DataAuditAuditAutoConfiguration;
import com.particle.global.dataaudit.audit.dto.DataAuditResultWithOpLogDTO;
import javafx.util.Pair;
import org.javers.common.collections.Lists;
import org.javers.core.diff.Change;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * <p>
 * 数据审计事务提交事件监听
 * </p>
 *
 * @author yangwei
 * @since 2023-05-05 21:22
 */
public class DataAuditTransactionalEventListener {


	private final DataAuditor dataAuditor;
	private final IDataAuditResultHandler dataAuditResultHandler;
	@Qualifier(DataAuditAuditAutoConfiguration.dataAuditTaskExecutor)
	@Autowired(required = false)
	private ExecutorService dataAuditTaskExecutor;

	public DataAuditTransactionalEventListener(DataAuditor dataAuditor,IDataAuditResultHandler dataAuditResultHandler) {
		this.dataAuditor = dataAuditor;
		this.dataAuditResultHandler = dataAuditResultHandler;
	}

	/**
	 * 支持自定义逻辑处理
	 * 数据库事务提交后触发（事件的订阅逻辑交由发布方决定，可以选择存到DB、MQ或者日志文件），
	 * <p>调用数据审计回调处理函数式接口，接口参数为二元函数式接口的具体实现（Javers比较器）
	 * 此处较绕，注意两点就很好理解
	 * 1.面向接口编程，调用接口实际为调用具体实现类
	 * 2.二元函数式接口作为参数，实际为二元函数式接口的具体逻辑实现类（可以理解为策略类）作为参数
	 * </p>
	 *
	 * @param event 通知事件
	 */
	@Transactional(rollbackFor = Exception.class)
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT, fallbackExecution = true)
	public void handleCustomHandlerApplicationEvent(DataAuditCustomHandlerApplicationEvent event) {
		if (dataAuditTaskExecutor != null) {
			Future<List<DataAuditResultWithOpLogDTO>> submit = dataAuditTaskExecutor.submit(() -> {
				try {
					dohandleCustomHandlerApplicationEvent(event);
					/**
					 * todo 这里只读取了 带有操作日志的一种
					 * 因为 {@link DefaultDataAuditResultHandler.handle} 为默认实现，收集的是带有操作日志的，如果有变量请同步修改
					 */
					return DataAuditCollectTool.getResultWithOpLog();
				} finally {
					// 很关键，否则会导致数混乱
					DataAuditCollectTool.clear();
				}

			});
			DataAuditCollectTool.collectResultWithOpLogFuture(Lists.asList(submit));
		}else {
			dohandleCustomHandlerApplicationEvent(event);
		}

	}
	private void dohandleCustomHandlerApplicationEvent(DataAuditCustomHandlerApplicationEvent event) {
		List<DataAuditResultWithOpLogDTO> propertyCompareResults = event.getDataAuditHandler().handle(dataAuditor::compare);
		if (CollectionUtil.isNotEmpty(propertyCompareResults)) {
			if (dataAuditResultHandler != null) {
				dataAuditResultHandler.handle(propertyCompareResults);
			}
		}
	}

	/**
	 * 同 {@link DataAuditTransactionalEventListener#handleCustomHandlerApplicationEvent(com.particle.global.dataaudit.audit.DataAuditCustomHandlerApplicationEvent)}
	 * 只一个重载的功能
	 * @param event
	 */
	@Transactional(rollbackFor = Exception.class)
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT, fallbackExecution = true)
	public void handleObjectApplicationEvent(DataAuditObjectApplicationEvent event) {

		handleCustomHandlerApplicationEvent(new DataAuditCustomHandlerApplicationEvent(t -> {
			Pair source = (Pair) event.getSource();
			List<Change> changes = t.apply(source.getKey(), source.getValue());
			DataAuditResultWithOpLogDTO dataAuditResultWithOpLogDTO = DataAuditResultWithOpLogDTO.create(changes, event.getDataId(), event.getDataTable(), event.getDataEntity(), event.getType());
			return Lists.asList(dataAuditResultWithOpLogDTO);

		}));
	}
}
