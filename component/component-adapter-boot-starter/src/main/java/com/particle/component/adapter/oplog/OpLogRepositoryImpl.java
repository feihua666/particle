package com.particle.component.adapter.oplog;

import brave.Tracer;
import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.particle.component.light.share.dict.oplog.OpLogAuditDataChangeType;
import com.particle.component.light.share.dict.oplog.OpLogModule;
import com.particle.component.light.share.dict.oplog.OpLogType;
import com.particle.global.dataaudit.audit.dto.DataAuditResultDTO;
import com.particle.global.dataaudit.op.OpLogRepository;
import com.particle.global.dataaudit.op.dto.OpLogAndDataAuditResultsDTO;
import com.particle.global.dataaudit.op.dto.OpLogDTO;
import com.particle.global.mybatis.plus.config.GlobalMybatisExecutorsConfig;
import com.particle.global.security.security.login.LoginUser;
import com.particle.global.security.security.login.LoginUserTool;
import com.particle.global.security.tenant.TenantTool;
import com.particle.global.tool.servlet.RequestTool;
import com.particle.oplog.domain.gateway.OpLogDictGateway;
import com.particle.oplog.infrastructure.dos.OpLogAuditDataDO;
import com.particle.oplog.infrastructure.dos.OpLogDO;
import com.particle.oplog.infrastructure.service.IOpLogAuditDataService;
import com.particle.oplog.infrastructure.service.IOpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

/**
 * <p>
 * 操作日志持久化实现
 * </p>
 *
 * @author yangwei
 * @since 2023-05-08 22:16
 */
public class OpLogRepositoryImpl implements OpLogRepository {

	@Autowired
	private IOpLogService iOpLogService;
	@Autowired
	private IOpLogAuditDataService iOpLogAuditDataService;

	@Autowired
	private OpLogDictGateway opLogDictGateway;

	@Qualifier(GlobalMybatisExecutorsConfig.commonDbTaskExecutor)
	@Autowired
	private ExecutorService commonDbTaskExecutor;

	@Autowired(required = false)
	private HttpServletRequest httpServletRequest;
	@Autowired
	private Tracer tracer;

	private static TimedCache<String, Map<String,Long>> cache = CacheUtil.newTimedCache(10 * 60 * 1000);


	@Override
	public void save(List<OpLogAndDataAuditResultsDTO> opLogAndDataAuditResultsDTOList) {
		if (CollectionUtil.isNotEmpty(opLogAndDataAuditResultsDTOList)) {
			Long tenantId = TenantTool.getTenantId();
			LoginUser loginUser = LoginUserTool.getLoginUser();
			LocalDateTime now = LocalDateTime.now();
			String requestUrl = null;
			String clientIP = null;
			if (httpServletRequest != null) {
				requestUrl = httpServletRequest.getRequestURL().toString();
				clientIP = RequestTool.getClientIP(httpServletRequest);
			}
			String finalRequestUrl = requestUrl;
			String finalClientIP = clientIP;
			String remark = StrUtil.format("traceId={}", tracer.currentSpan().context().traceIdString());
			commonDbTaskExecutor.execute(()->{
				try {
					TenantTool.setTenantId(tenantId);
					LoginUserTool.saveToThreadContext(loginUser);
					for (OpLogAndDataAuditResultsDTO opLogAndDataAuditResultsDTO : opLogAndDataAuditResultsDTOList) {
						OpLogDO opLogDO = opLogDTOToOpLogDO(opLogAndDataAuditResultsDTO.getOpLogDTO(), loginUser, finalRequestUrl, finalClientIP, now,remark);
						if (opLogDO != null) {
							opLogDO = iOpLogService.add(opLogDO);
							List<DataAuditResultDTO> dataAuditResultDTOS = opLogAndDataAuditResultsDTO.getDataAuditResultDTOS();
							List<OpLogAuditDataDO> opLogAuditDataDOS = dataAuditResultDTOsToOpLogAuditDataDOs(dataAuditResultDTOS, opLogDO.getUserId(), opLogDO.getId());
							if (CollectionUtil.isNotEmpty(opLogAuditDataDOS)) {
								iOpLogAuditDataService.saveBatch(opLogAuditDataDOS);
							}
						}
					}


				} finally {
					TenantTool.clear();
					LoginUserTool.clear();
				}
			});
		}
	}

	private OpLogDO opLogDTOToOpLogDO(OpLogDTO opLogDTO, LoginUser loginUser, String url, String ip, LocalDateTime operateAt,String remark) {
		if (opLogDTO == null) {
			return null;
		}
		OpLogDO opLogDO = new OpLogDO();
		opLogDO.setDataAuditEnabled(false);


		// 手动设置id
		opLogDO.setId(opLogDTO.getId());
		opLogDO.setParentId(opLogDTO.getParentId());
		opLogDO.setName(opLogDTO.getName());
		opLogDO.setModuleDictId(getDictId(OpLogModule.Group.op_log_module.groupCode(), opLogDTO.getModule()));
		opLogDO.setModule(opLogDTO.getModule());
		opLogDO.setTypeDictId(getDictId(OpLogType.Group.op_log_type.groupCode(), opLogDTO.getType()));
		opLogDO.setType(opLogDTO.getType());
		if (loginUser != null) {
			opLogDO.setUserId(loginUser.getId());
			opLogDO.setUserName(loginUser.getName());

			opLogDO.setUserNickname(loginUser.getNickname());
			opLogDO.setUserAvatar(loginUser.getAvatar());
		}
		opLogDO.setUrl(url);
		opLogDO.setIp(ip);
		opLogDO.setMainDataId(opLogDTO.getMainDataId());
		opLogDO.setMainDataTable(opLogDTO.getMainDataTable());
		opLogDO.setMainDataEntity(opLogDTO.getMainDataEntity());
		opLogDO.setOperateAt(operateAt);
		opLogDO.setRemark(remark);
		return opLogDO;
	}

	private List<OpLogAuditDataDO> dataAuditResultDTOsToOpLogAuditDataDOs(List<DataAuditResultDTO> dataAuditResultDTOS,Long userId,Long opLogId){
		if (CollectionUtil.isEmpty(dataAuditResultDTOS)) {
			return Collections.emptyList();
		}
		return dataAuditResultDTOS.stream().map(item -> {
			OpLogAuditDataDO opLogAuditDataDO = new OpLogAuditDataDO();
			opLogAuditDataDO.setDataAuditEnabled(false);

			opLogAuditDataDO.setName(item.getName());
			opLogAuditDataDO.setPropertyName(item.getProperty());
			opLogAuditDataDO.setOldValue(item.getOldValue());
			opLogAuditDataDO.setNewValue(item.getNewValue());
			opLogAuditDataDO.setChangeTypeDictId(getDictId(OpLogAuditDataChangeType.Group.op_log_audit_data_change_type.groupCode(), item.getChangeType()));
			opLogAuditDataDO.setChangeType(item.getChangeType());
			opLogAuditDataDO.setTypeDictId(getDictId(OpLogType.Group.op_log_type.groupCode(), item.getType()));

			opLogAuditDataDO.setType(item.getType());
			opLogAuditDataDO.setUserId(userId);

			opLogAuditDataDO.setDataId(item.getDataId());
			opLogAuditDataDO.setDataTable(item.getDataTable());
			opLogAuditDataDO.setDataEntity(item.getDataEntity());
			opLogAuditDataDO.setOpLogId(opLogId);
			return opLogAuditDataDO;
		}).collect(Collectors.toList());
	}



	private Long getDictId(String groupCode, String value) {

		Map<String, Long> stringLongMap = cache.get(groupCode,()->{
			return opLogDictGateway.getItemsByGroupCode(groupCode);
		});

		return stringLongMap.get(value);
	}
}
