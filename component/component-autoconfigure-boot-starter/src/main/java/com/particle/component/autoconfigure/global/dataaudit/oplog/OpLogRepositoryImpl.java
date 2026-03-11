package com.particle.component.autoconfigure.global.dataaudit.oplog;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.global.dataaudit.audit.dto.DataAuditResultDTO;
import com.particle.global.dataaudit.op.OpLogRepository;
import com.particle.global.dataaudit.op.dto.OpLogAndDataAuditResultsDTO;
import com.particle.global.dataaudit.op.dto.OpLogDTO;
import com.particle.global.mybatis.plus.config.GlobalMybatisExecutorsConfig;
import com.particle.global.dto.login.LoginUser;
import com.particle.global.tool.login.LoginUserTool;
import com.particle.global.tool.tenant.TenantTool;
import com.particle.global.tool.log.TraceTool;
import com.particle.global.tool.servlet.RequestTool;
import com.particle.oplog.adapter.feign.client.rpc.OpLogRpcFeignClient;
import com.particle.oplog.client.dto.command.OpLogAuditDataCreateCommand;
import com.particle.oplog.client.dto.command.OpLogCreateCommand;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

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
	private OpLogRpcFeignClient opLogRpcFeignClient;

	@Qualifier(GlobalMybatisExecutorsConfig.commonDbTaskExecutor)
	@Autowired
	private ExecutorService commonDbTaskExecutor;

	@Autowired(required = false)
	private HttpServletRequest httpServletRequest;

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
				clientIP = RequestTool.getClientRealIP(httpServletRequest);
			}
			String finalRequestUrl = requestUrl;
			String finalClientIP = clientIP;
			String remark = StrUtil.format("traceId={}", TraceTool.getTraceId());
			commonDbTaskExecutor.execute(()->{
				try {
					TenantTool.setTenantId(tenantId);
					LoginUserTool.saveToThreadContext(loginUser);
					for (OpLogAndDataAuditResultsDTO opLogAndDataAuditResultsDTO : opLogAndDataAuditResultsDTOList) {
						OpLogCreateCommand opLogCreateCommand = opLogDTOToOpLogCreateCommand(opLogAndDataAuditResultsDTO.getOpLogDTO(), loginUser, finalRequestUrl, finalClientIP, now,remark);
						if (opLogCreateCommand != null) {
							List<DataAuditResultDTO> dataAuditResultDTOS = opLogAndDataAuditResultsDTO.getDataAuditResultDTOS();
							List<OpLogAuditDataCreateCommand> opLogAuditDataCreateCommandS = dataAuditResultDTOsToOpLogAuditDataCreateCommands(dataAuditResultDTOS, opLogCreateCommand.getUserId(), opLogCreateCommand.getId());
							if (CollectionUtil.isNotEmpty(opLogAuditDataCreateCommandS)) {
                                opLogCreateCommand.setAuditDataCreateCommandList(opLogAuditDataCreateCommandS);
							}
                            opLogRpcFeignClient.create(opLogCreateCommand);
                        }
					}


				} finally {
					TenantTool.clear();
					LoginUserTool.clear();
				}
			});
		}
	}

	private OpLogCreateCommand opLogDTOToOpLogCreateCommand(OpLogDTO opLogDTO, LoginUser loginUser, String url, String ip, LocalDateTime operateAt,String remark) {
		if (opLogDTO == null) {
			return null;
		}
		OpLogCreateCommand opLogCreateCommand = new OpLogCreateCommand();

		// 手动设置id
		opLogCreateCommand.setId(opLogDTO.getId());
		opLogCreateCommand.setParentId(opLogDTO.getParentId());
		opLogCreateCommand.setName(opLogDTO.getName());
        // 字典值在操作日志添加时会自动处理
		// opLogCreateCommand.setModuleDictId();
		opLogCreateCommand.setModule(opLogDTO.getModule());
        // 字典值在操作日志添加时会自动处理
		// opLogCreateCommand.setTypeDictId();
		opLogCreateCommand.setType(opLogDTO.getType());
		if (loginUser != null) {
			opLogCreateCommand.setUserId(loginUser.getId());
			opLogCreateCommand.setUserName(loginUser.getName());

			opLogCreateCommand.setUserNickname(loginUser.getNickname());
			opLogCreateCommand.setUserAvatar(loginUser.getAvatar());
		}
		opLogCreateCommand.setUrl(url);
		opLogCreateCommand.setIp(ip);
		opLogCreateCommand.setMainDataId(opLogDTO.getMainDataId());
		opLogCreateCommand.setMainDataTable(opLogDTO.getMainDataTable());
		opLogCreateCommand.setMainDataEntity(opLogDTO.getMainDataEntity());
		opLogCreateCommand.setOperateAt(operateAt);
		opLogCreateCommand.setRemark(remark);
		return opLogCreateCommand;
	}

	private List<OpLogAuditDataCreateCommand> dataAuditResultDTOsToOpLogAuditDataCreateCommands(List<DataAuditResultDTO> dataAuditResultDTOS,Long userId,Long opLogId){
		if (CollectionUtil.isEmpty(dataAuditResultDTOS)) {
			return Collections.emptyList();
		}
		return dataAuditResultDTOS.stream().map(item -> {
			OpLogAuditDataCreateCommand opLogAuditDataCreateCommand = new OpLogAuditDataCreateCommand();

			opLogAuditDataCreateCommand.setName(item.getName());
			opLogAuditDataCreateCommand.setPropertyName(item.getProperty());
			opLogAuditDataCreateCommand.setOldValue(item.getOldValue());
			opLogAuditDataCreateCommand.setNewValue(item.getNewValue());
            // 字典值在操作日志添加时会自动处理
			// opLogAuditDataCreateCommand.setChangeTypeDictId();
			opLogAuditDataCreateCommand.setChangeType(item.getChangeType());
            // 字典值在操作日志添加时会自动处理
            // opLogAuditDataCreateCommand.setTypeDictId();

			opLogAuditDataCreateCommand.setType(item.getType());
			opLogAuditDataCreateCommand.setUserId(userId);

			opLogAuditDataCreateCommand.setDataId(item.getDataId());
			opLogAuditDataCreateCommand.setDataTable(item.getDataTable());
			opLogAuditDataCreateCommand.setDataEntity(item.getDataEntity());
			opLogAuditDataCreateCommand.setOpLogId(opLogId);
			return opLogAuditDataCreateCommand;
		}).collect(Collectors.toList());
	}

}
