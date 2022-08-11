package com.particle.global.notification.notify;

import brave.Tracer;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ClassLoaderUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.global.light.share.constant.ClassAdapterConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 *  通知抽象监听实现，主要可以支持多种通知参数，甚至可以适用于业务通知，比如发送极光推送、微信模板消息等通知
 * </p>
 *
 * @author yangwei
 * @since 2021-08-25 13:38
 */
@Slf4j
public abstract class AbstractNotifyListener implements INotifyListener{


	protected Tracer tracer;

	/**
	 * 单个类型支持
	 * @param param
	 * @return
	 */
	protected boolean support(NotifyParam param){
		if (StrUtil.isEmpty(param.getTypes())) {
			return false;
		}
		return Arrays.stream(param.getTypes().split(",")).collect(Collectors.toList()).contains(supportType());

	}

	/**
	 * 支持的类型
	 * @return
	 */
	protected abstract String supportType();

	@Override
	public void notify(NotifyParam notifyParam) {
		if(support(notifyParam)){
			try {
				doNotify(notifyParam);
			}catch (Exception e){
				log.error("通知异常,type={}",supportType(),e);
				if (ClassLoaderUtil.isPresent(ClassAdapterConstants.MONITOR_TOOL_TOOL_CLASS_NAME)) {
					com.particle.global.actuator.monitor.MonitorTool.count("notify.exception","通知异常","supportType",supportType());

				}
			}
		}
	}

	/**
	 * 格式化内容,提供一个工具方法
	 * @param notifyParam
	 * @return
	 */
	protected List<String> formatParam(NotifyParam notifyParam){
		List<String> result = new ArrayList<>();
		if (StrUtil.isNotEmpty(notifyParam.getTitle())) {
			result.add(StrUtil.format("[标题] : {}", notifyParam.getTitle()));
		}
		if (StrUtil.isNotEmpty(notifyParam.getContentType())) {
			result.add(StrUtil.format("[内容类型] : {}", notifyParam.getContentType()));
		}
		if (StrUtil.isNotEmpty(notifyParam.getLinkUrl())) {
			result.add(StrUtil.format("[访问地址] : {}",linkUrlHrefIfNecessary( notifyParam.getLinkUrl())));
		}
		if (StrUtil.isNotEmpty(notifyParam.getSuggest())) {
			result.add(StrUtil.format("[提示] : {}", notifyParam.getSuggest()));
		}
		// 添加时间
		result.add(StrUtil.format("[通知时间] : {}", DateUtil.now()));
		if (includeTraceInfo()) {

			if (tracer != null) {
				String traceId = Optional.ofNullable(tracer).map(Tracer::currentSpan).map(i -> i.context()).map(i -> i.traceIdString()).orElse(null);
				if (StrUtil.isNotEmpty(traceId)) {
					result.add(StrUtil.format("[traceId|spanId] : {}|{}",tracer.currentSpan().context().traceIdString(),tracer.currentSpan().context().spanIdString()));
				}
			}else {
				log.warn("tracer is null,notice will not include traceinfo. check your config or consider depend global-sleuth-boot-starter module");
			}

		}
		if (StrUtil.isNotEmpty(notifyParam.getContent())) {
			result.add(StrUtil.format("[内容] : {}", notifyParam.getContent()));
		}
		return result;
	}

	/**
	 * linkUrl是否添加a链接
	 * @return
	 */
	protected boolean linkUrlHrefFormat(){
		return true;
	}

	/**
	 * 包含trace信息
	 * @return
	 */
	protected boolean includeTraceInfo(){
		return true;
	}

	/**
	 * 包含时间
	 * @return
	 */
	protected boolean includeTime(){
		return true;
	}
	/**
	 * 转url为链接
	 * @param linkUrlOrigin
	 * @return
	 */
	private String linkUrlHrefIfNecessary(String linkUrlOrigin){
		return linkUrlHrefFormat() ? StrUtil.format("<a href=\"{}\">{}</a>", linkUrlOrigin, linkUrlOrigin) : linkUrlOrigin;
	}

	/**
	 * 发送通知
	 * @param notifyParam
	 */
	public abstract void doNotify(NotifyParam notifyParam);

	public Tracer getTracer() {
		return tracer;
	}

	@Autowired(required = false)
	public void setTracer(Tracer tracer) {
		this.tracer = tracer;
	}
}
