package com.particle.global.light.share.filter;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2026/1/30 16:56
 */
public class FilterConstants {
    /**
     * Useful constant for the highest precedence value.
     * @see java.lang.Integer#MIN_VALUE
     */
    private static int HIGHEST_PRECEDENCE = Integer.MIN_VALUE;

    /**
     * Useful constant for the lowest precedence value.
     * @see java.lang.Integer#MAX_VALUE
     */
    private static int LOWEST_PRECEDENCE = Integer.MAX_VALUE;

    private static int filterSpan = 10;
    /**
     * 注意该位置应该最前放到 trace 之后
     * 参考：{@link org.springframework.web.filter.ServerHttpObservationFilter}
     * 参考：{@link org.springframework.boot.actuate.autoconfigure.observation.web.servlet.WebMvcObservationAutoConfiguration#webMvcObservationFilter(ObservationRegistry, ObjectProvider, org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties)}
     *
     * 放到 spring session 之后
     * 参考：{@link org.springframework.session.web.http.SessionRepositoryFilter#DEFAULT_ORDER}
     */
    private static int filterBaseOrder = HIGHEST_PRECEDENCE + 100;

    public static int corsFilterOrder = filterBaseOrder + filterSpan * 1;
    public static int threadContextFilterOrder = filterBaseOrder + filterSpan * 2;
    public static int responseTraceIdFilterOrder = filterBaseOrder + filterSpan * 3;
    public static int requestBodyReadableFilterOrder = filterBaseOrder + filterSpan * 4;
    public static int requestParamValidateFilterOrder = filterBaseOrder + filterSpan * 5;
    public static int requestResponseLogFilterOrder = filterBaseOrder + filterSpan * 6;
    public static int tenantContextLoginFilterOrder = filterBaseOrder + filterSpan * 7;


    /**
     * 默认情况下的 spring security 过滤器顺序为 -100，建议保持默认值
     * {@link org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration}
     * {@link org.springframework.boot.autoconfigure.security.SecurityProperties#DEFAULT_FILTER_ORDER}
     */
    public static int securityFilterOrder = -100;



    private static int filterBase1Order = LOWEST_PRECEDENCE - 2000;
    public static int tenantContextFilterOrder = filterBase1Order + filterSpan * 1;


    public static int faviconFilterOrder = filterBase1Order + filterSpan * 2;
    public static int logoFilterOrder = filterBase1Order + filterSpan * 3;
    public static int logoTextFilterOrder = filterBase1Order + filterSpan * 4;
    public static int webTitleFilterOrder = filterBase1Order + filterSpan * 5;
    public static int usageCountFilterOrder = filterBase1Order + filterSpan * 6;


}
