package com.particle.global.notification.notify;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.global.tool.json.JsonTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 注入该 bean 可以直接发起通知
 * </p>
 *
 * @author yangwei
 * @since 2021-08-13 17:19
 */
@Slf4j
@Component
public class NotifyHelper {


	private List<INotifyListener> notifyListenerList;

	/**
	 * 发起通知
	 * @param notifyParam
	 */
	void notify(NotifyParam notifyParam){
		if (CollectionUtil.isNotEmpty(notifyListenerList)) {
			for (INotifyListener iNotifyListener : notifyListenerList) {
				iNotifyListener.notify(notifyParam);
			}
		}else {
			log.warn("未配置 INotifyListener 实例，未能发出通知，notifyParam={}", JsonTool.toJsonStr(notifyParam));
		}
	}

	@Autowired(required = false)
	public void setNotifyListenerList(List<INotifyListener> notifyListenerList) {
		this.notifyListenerList = notifyListenerList;
	}
}
