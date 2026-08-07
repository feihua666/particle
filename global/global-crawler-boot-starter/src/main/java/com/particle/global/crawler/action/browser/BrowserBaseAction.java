package com.particle.global.crawler.action.browser;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.particle.global.crawler.action.BaseAction;
import lombok.Data;

/**
 * 浏览器操作 Action 基类
 * <p>
 * 所有浏览器相关 Action 的基类。
 * </p>
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Data
public abstract class BrowserBaseAction extends BaseAction {

}
