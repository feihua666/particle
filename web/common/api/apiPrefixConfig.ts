/**
 * api前缀配置
 * 主要用于在微服务时，各模块前缀分开配置
 */

const apiPrefixConfig = {
  allEnable: import.meta.env.VITE_API_PREFIX_ALL_ENABLE === 'true',
  all: import.meta.env.VITE_API_PREFIX_ALL || '',
}
const getApiPrefix = (self: string): string => {
  return apiPrefixConfig.allEnable ? apiPrefixConfig.all : (self || '')
}
export {getApiPrefix}
export default getApiPrefix
