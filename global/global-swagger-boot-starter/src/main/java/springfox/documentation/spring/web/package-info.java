package springfox.documentation.spring.web;
/**
 * 解决springboot新版本2.6.6不兼容swagger3.0.0的问题
 * https://blog.csdn.net/u011943534/article/details/121656010
 *
 * 修改 WebMvcRequestHandlerProvider 第65行
 * 需配置配置
 *   mvc:
 *     pathmatch:
 *       # 解决 spring新版本中，swagger3兼容的问题，但这不是长久问题
 *       matching-strategy: ant_path_matcher
 */