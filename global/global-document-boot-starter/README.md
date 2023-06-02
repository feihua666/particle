# 文档提供支持
旨在对常用的文档操作提供支持，如常用的excel导入导出、文档在线预览等技术支持

## 下载模板
下载模板提供了通用的下载支持，按约定提供配置即可
1. 在 resources 下建一个模板文件  
如：global-document-template/文档测试模板.xlsx
2. 在 resources 下建一个 meta文件，用来描述模板位置及名称  
如：META-INF/template/globalDocumentTemplateTest.meta 其中 META-INF/template 是约定的（已硬编码在DefalutGlobalDocumentTemplateProvider中）
配置内容为：
```
# 必填用来读取模板
particle.document.template.path=global-document-template/文档测试模板.xlsx
# 选填，默认使用 path中的文件名
particle.document.template.name=文档测试模板.xlsx
```
3. 调用下载模板接口，指定参数templateIdentifier为meta文件名称，不带后缀
get   /document/downloadTemplate?templateIdentifier=globalDocumentTemplateTest