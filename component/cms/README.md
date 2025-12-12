# 内容管理组件

内容管理组件提供网站内容管理功能，支持站点、栏目、内容等多维度管理。

## 功能特性

- 站点管理
- 栏目管理
- 内容管理
- 模板管理

## 核心组件

- 站点管理服务
- 栏目管理服务
- 内容管理服务

## 依赖组件


## 使用说明

内容管理组件提供完整的CMS功能，支持多站点、多栏目的内容管理。

## 页面中可使用的变量

1. site 变量，参见 com.particle.cms.client.constants.CmsConstants.model_site  
参考变量引用对象 com.particle.cms.client.dto.data.dynamic.CmsSiteTemplateModelVO
2. channel 变量，参见  com.particle.cms.client.constants.CmsConstants.model_channel  
参考变量引用对象 com.particle.cms.client.dto.data.dynamic.CmsChannelTemplateModelVO
3. content 变量，参见  com.particle.cms.client.constants.CmsConstants.model_content  
参考变量引用对象 com.particle.cms.client.dto.data.dynamic.CmsContentTemplateModelVO

## 站点指令使用
```aiignore
[@cms_site_list siteId=site.id isPrimeSite=true;item,index]
    ${item.name}${index}
[/@]
```

站点指令属性
1. siteId 可选，指定站点id查询
2. isPrimeSite 可选，指定主站点查询

## 栏目指令使用
```aiignore
[@cms_channel_list channelId=channel.id siteId=site.id parentId=2344;item,index]
    ${item.name}${index}
[/@]
```

栏目指令属性
1. channelId 可选，指定栏目id查询
2. siteId 可选，指定站点id查询
3. parentId 可选，指定栏目的父级id查询

## 内容指令使用
```aiignore
[@cms_content_list contentId=content.id channelId=channel.id siteId=site.id categoryId=222;item,index]
    ${item.name}${index}
[/@]
```

内容指令属性
1. contentId 可选，指定内容id查询
2. channelId 可选，指定栏目id查询
3. siteId 可选，指定站点id查询
4. categoryId 可选，指定内容分类id查询

## 内容分类指令使用
```aiignore
[@cms_content_category_list channelId=channel.id siteId=site.id categoryId=222;item,index]
    ${item.name}${index}
[/@]
```

内容分类指令属性
1. channelId 可选，指定栏目id查询
2. siteId 可选，指定站点id查询
3. categoryId 可选，指定内容分类id查询
