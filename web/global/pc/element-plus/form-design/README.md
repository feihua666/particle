#表单设计器组件
表单设计器组件旨在方便particle项目使用，以直观的形式帮助理解elementPlus组件动态表现  
同时支撑代码生成更直观的修改并最终以PtForm组件渲染  

当然也可以用于其它用途，如：简单表单使用，但可能需要在生成的代码基础上开发配置以满足需求  
## 表单设计器能力
本表单设计器的设计区渲染能力理论上支持vue3支持的所以ui框架组件表单项及非表单项，但目前只开发了PtForm渲染预览和代码生成，SFC组件暂未实现

## 缺陷
目前不支持的情况说明如下：
1. 不支持组件插槽配置化（后续会支持，看时间而定）
2. 不支持布局可拖拽（待第1点完成后方可支持，但目前建议渲染为PtFrom，布局通过配置项实现）
3. 不支持事件配置化
以上如果需要支持，建议自己封装组件实现  

## 内置可用组件项说明
本组件目前支持的可用表单项分为两个部分：
1. particle封装的组件 参见：formDesignParticleBuiltInComponents.ts
2. elementPlus原生组件 参见：formDesignElementPlusBuiltInComponents.ts

## 表单设计器开发思路
1. 表计设计器页面整体分为三部分左、中、右。左边为可拖拽组件项，中间为实时渲染设计区，右边为当前选中组件项属性信息
2. 针对第一点三个部分，开发目录也同样对应，左=comp目录，中=main目录，右=attr目录，toolbar目录对应中间操作按钮区相关
3. 整个表单设计器维护一份数据对象包括可用组件formDesignItems（这是一个数组，其中数组中每一个数据对象都存在对应属性值表单formItemForm和compForm，及对应的实时属性对象formItemProps和compProps），中间实时渲染表单formDesignForm，
表单设置值表单formDesignFormSettingForm和表单预览实时属性对象formDesignFormSettingFormProps
4. 当选中组件属性变化时，从表单值对象form转化为对应的属性对象props并动态绑定到组件以实时在中间实时渲染

## 额外添加组件项说明
如需添加额外组件需要以下几个步骤：
1. 如果是内置组件，参照以前的内置组件配置方式直接在 formDesignParticleBuiltInComponents.ts或formDesignElementPlusBuiltInComponents.ts配置即可  
2. 如果判断为不是内置组件如想额外分类目前没有预留空间，需要在FormDesignCompsContainer.vue中添加一个折叠区，其它参照内置组件配置即可  
3. 关于具体组件配置可参考formDesignItemType.ts类型定义，其中属性配置与PtForm保持一致  

本组件参考了 https://www.vform666.com/ vForm3 的实现原理，在此表示感谢！vForm3支持拖拽布局和支持多出输出包括SFC  