package com.particle.global.dataaudit.op;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.global.dataaudit.audit.DataAuditCollectTool;
import com.particle.global.dataaudit.audit.dto.DataAuditResultDTO;
import com.particle.global.dataaudit.audit.dto.DataAuditResultWithOpLogDTO;
import com.particle.global.dataaudit.op.dto.OpLogAndDataAuditResultsDTO;
import com.particle.global.dataaudit.op.dto.OpLogDTO;
import com.particle.global.tool.id.SnowflakeIdTool;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;

/**
 * <p>
 * 拦截并打印切面
 * 考虑到远程调用的场景，一个操作可能远程调用其它操作，同时也会记录日志，这时就会有父子关系，这种关系需要体现
 * 这在远程调用时需要传递关系
 * </p>
 *
 * @author yangwei
 * @since 2023-05-05 15:10:53
 */
@Data
@Aspect
@Slf4j
public class OpLogAspect {

	/**
	 * 该构造函数不能去掉，依赖 SnowflakeIdTool bean
	 * @param snowflakeIdTool
	 */
	public OpLogAspect(SnowflakeIdTool snowflakeIdTool) {

	}

	@Autowired(required = false)
	private HttpServletRequest request;

	@Autowired(required = false)
	private List<IOpLogHandler> iOpLogHandlerList;

	/**
	 * {@code @Pointcut} 注解参考 {@url https://blog.csdn.net/zhengchao1991/article/details/53391244}
	 */
	@Pointcut("@annotation(com.particle.global.dataaudit.op.OpLog)")
	public void pointcut(){
	}

	@Around(value = "pointcut()")
	public Object around(ProceedingJoinPoint joinPoint ) throws Throwable {
		// 记录处理开始时间
		long startTime =  0;
		MethodSignature ms = (MethodSignature)joinPoint.getSignature();
		Method method = ms.getMethod();
		OpLog annotation = method.getAnnotation(OpLog.class);
		// 前置处理
		before(joinPoint,annotation);
		Object response = null;
		try {

			response = joinPoint.proceed();
			// 从这里开始计时，计算oplog实际消耗
			startTime =  System.currentTimeMillis();
			// 后置处理
			after(joinPoint,annotation);
		}
		catch (Throwable e){
			throw e;
		}finally {
			OpLogTool.clear();
			DataAuditCollectTool.clear();
			// 只记录后置处理的时间
			if (startTime >0) {
				log.debug("opLogAspect cost : {}ms",System.currentTimeMillis() - startTime);
			}
		}

		return response ;
	}


	/**
	 * 前置处理
	 * 设置父级及当前id
	 */
	private void before(ProceedingJoinPoint joinPoint, OpLog annotation) {

		// todo 暂不支持 OpLog 嵌套的情况，比如在controller的方法上加了OpLog注解，又在下面调用的service上加了OpLog注解，这时service层的注解会优先于controller层的注释嵌套生效
		// 但父级id问题会混乱，我认为这种情况本应该会形成父子关系，但实际上落地的数据是平级关系，甚至数据混乱，目前建议只在controller 层 添加 OpLog 注解
		// 解决办法是这里设置当前线程的父级id和id时应该是一个栈的数据结构，支持先进后出，暂不考虑实现，如果要实现请考虑 {@link com.particle.global.dataaudit.op.OpLogTool.setMainDataId}
		if (request != null) {
			String parentId = request.getHeader(OpLogTool.parentIdHeader);
			if (StrUtil.isNotEmpty(parentId)) {
				OpLogTool.setParentId(Long.parseLong(parentId));
			}
		}
		// todo 在远程调用时，需要将当前id传递到下游
		OpLogTool.setCurrentId(SnowflakeIdTool.nextId());
		OpLogTool.setStart();
		if (annotation.ignoreDataAuditPublish()) {
			DataAuditCollectTool.setIgnorePublish();
		}
	}

	/**
	 * 后置处理，在没有获取到操作日志注释和审计数据时，将不处理任何动作
	 * 如果只收集了审计数据，没有操作日志，根本原因是在收集时没有指定操作日志对象
	 */
	@SneakyThrows
	private void after(ProceedingJoinPoint joinPoint, OpLog annotation ) {

		OpLogDTO opLogDTO = null;
		String name = annotation.name();
		if (StrUtil.isEmpty(name)) {
			//	todo 如果为空，尝试获取 swagget apiAction 注解
		}
		opLogDTO = OpLogDTO.create(SnowflakeIdTool.nextId(),name, annotation.module(), annotation.type(),OpLogTool.getParentId(),OpLogTool.getMainDataId(),null,null);

		// 被处理的最终结果存储在这里
		List<OpLogAndDataAuditResultsDTO> opLogAndDataAuditResultsDTOList = new ArrayList<>();
		// 收集所有的未归属到操作日志的数据审计数据
		List<DataAuditResultDTO> collectResultTemp = new ArrayList<>();

		List<DataAuditResultDTO> collectResult = DataAuditCollectTool.getResult();
		if (CollectionUtil.isNotEmpty(collectResult)) {
			collectResultTemp.addAll(collectResult);
		}
		List<DataAuditResultWithOpLogDTO> resultWithOpLog = DataAuditCollectTool.getResultWithOpLog();
		if (CollectionUtil.isEmpty(resultWithOpLog)) {
			resultWithOpLog = new ArrayList<>();
		}
		// 异步获取的
		List<Future<List<DataAuditResultWithOpLogDTO>>> resultWithOpLogFuture = DataAuditCollectTool.getResultWithOpLogFuture();
		if (resultWithOpLogFuture != null) {
			for (Future<List<DataAuditResultWithOpLogDTO>> listFuture : resultWithOpLogFuture) {
				List<DataAuditResultWithOpLogDTO> dataAuditResultWithOpLogDTOS = listFuture.get();
				if (CollectionUtil.isNotEmpty(dataAuditResultWithOpLogDTOS)) {
					resultWithOpLog.addAll(dataAuditResultWithOpLogDTOS);
				}
			}
		}
		if (resultWithOpLog != null) {
			for (DataAuditResultWithOpLogDTO dataAuditResultWithOpLogDTO : resultWithOpLog) {
				List<DataAuditResultDTO> dataAuditResultDTOS = dataAuditResultWithOpLogDTO.getDataAuditResultDTOS();
				OpLogDTO opLogDTO1 = dataAuditResultWithOpLogDTO.getOpLogDTO();
				if (opLogDTO1 == null && CollectionUtil.isEmpty(dataAuditResultDTOS)) {
					continue;
				}
				// 只要有操作日志就可以最终添加
				if (opLogDTO1 != null) {
					OpLogAndDataAuditResultsDTO opLogAndDataAuditResultsDTO = OpLogAndDataAuditResultsDTO.create(opLogDTO1, dataAuditResultDTOS);
					opLogAndDataAuditResultsDTOList.add(opLogAndDataAuditResultsDTO);
					continue;
				}
				// 没有归属操作日志，添加到根上
				if (opLogDTO1 == null && CollectionUtil.isNotEmpty(dataAuditResultDTOS)) {
					collectResultTemp.addAll(dataAuditResultDTOS);
				}
			}
		}

		OpLogAndDataAuditResultsDTO opLogAndDataAuditResultsDTO = null;
		// 如果全部收集到的审计数据没有归属，添加一个默认的归属
		if (CollectionUtil.isNotEmpty(collectResultTemp)) {
			if (opLogDTO == null) {
				// 如果没有设置一个默认的
				opLogDTO = OpLogDTO.create(SnowflakeIdTool.nextId(),"未知操作", OpLogModule.unknown.name(), OpLogType.unknown.name(),OpLogTool.getParentId(),OpLogTool.getMainDataId(),null,null);
			}
			opLogAndDataAuditResultsDTO = OpLogAndDataAuditResultsDTO.create(opLogDTO, collectResultTemp);
		}else {
			// 如果没有收集到数据审计日志，操作日志不为空也要添加处理
			if (opLogDTO != null) {
				opLogAndDataAuditResultsDTO = OpLogAndDataAuditResultsDTO.create(opLogDTO, Collections.emptyList());
			}
		}


		if (opLogAndDataAuditResultsDTO != null) {
			opLogAndDataAuditResultsDTOList.add(opLogAndDataAuditResultsDTO);
		}


		// 记录的操作日志不为空，开始处理
		if (CollectionUtil.isNotEmpty(opLogAndDataAuditResultsDTOList) && iOpLogHandlerList != null) {
			// 处理一下 mainDataId
			opLogAndDataAuditResultsDTOList.forEach(this::handleOpLogAndDataAuditResultsDTO);

			for (IOpLogHandler iOpLogHandler : iOpLogHandlerList) {
				iOpLogHandler.handle(opLogAndDataAuditResultsDTOList);
			}
		}
	}

	/**
	 * 对 oplog做最后的处理，主要是自动根据主数据id添加主表名和主实体数据
	 * @param opLogAndDataAuditResultsDTO
	 */
	private void handleOpLogAndDataAuditResultsDTO(OpLogAndDataAuditResultsDTO opLogAndDataAuditResultsDTO) {
		OpLogDTO opLogDTO1 = opLogAndDataAuditResultsDTO.getOpLogDTO();
		if (opLogDTO1 != null) {
			if (opLogDTO1.getMainDataId() != null) {
				List<DataAuditResultDTO> dataAuditResultDTOS = opLogAndDataAuditResultsDTO.getDataAuditResultDTOS();
				if (CollectionUtil.isNotEmpty(dataAuditResultDTOS)) {
					DataAuditResultDTO dataAuditResultDTO = dataAuditResultDTOS.stream().filter(d -> opLogDTO1.getMainDataId().equals(d.getDataId())).findFirst().orElse(null);
					if (dataAuditResultDTO != null) {
						opLogDTO1.setMainDataEntity(dataAuditResultDTO.getDataEntity());
						opLogDTO1.setMainDataTable(dataAuditResultDTO.getDataTable());
					}
				}
			}
		}
	}
}
