import{d as I,a as N,r as s,b as d,o as v,c as g,e as m,h as o,w as a,f as r,F as h,K as u}from"./index-_8rL1G8m.js";import{a as y}from"./toolsFrontApi-BQ08W_Pb.js";const F=I({__name:"AddFieldPage",setup(C){const i=N({form:{},formData:{}}),f=s([{field:{name:"afterFieldName"},element:{comp:"el-input",formItemProps:{label:"在哪个字段之后添加",tips:"注意是字段名称不是数据库字段名称"},compProps:{clearable:!0,placeholder:"支持数据库字段名自动转换为驼峰命名"}}},{field:{name:"domainName"},element:{comp:"el-input",formItemProps:{label:"领域模型名称",required:!0},compProps:{clearable:!0,placeholder:"领域模型名称，一般为类名称"}}},{field:{name:"componentBackendAbsolutePath"},element:{comp:"el-input",formItemProps:{label:"后端组件绝对路径",required:!0,tips:"如：/Users/yw/fh/git-source/particle/component/tenant"},compProps:{clearable:!0,placeholder:"如：/Users/yw/fh/git-source/particle/component/tenant"}}},{field:{name:"items1fieldName"},element:{comp:"el-input",formItemProps:{label:"要添加的字段1名称",required:!0},compProps:{clearable:!0,placeholder:"支持数据库字段名自动转换为驼峰命名"}}},{field:{name:"items1fieldComment"},element:{comp:"el-input",formItemProps:{label:"要添加的字段1注释",required:!0},compProps:{clearable:!0}}},{field:{name:"items1fieldType"},element:{comp:"el-input",formItemProps:{label:"要添加的字段1类型",required:!0},compProps:{placeholder:"如：String",clearable:!0}}},{field:{name:"items2fieldName"},element:{comp:"el-input",formItemProps:{label:"要添加的字段2名称"},compProps:{clearable:!0,placeholder:"支持数据库字段名自动转换为驼峰命名"}}},{field:{name:"items2fieldComment"},element:{comp:"el-input",formItemProps:{label:"要添加的字段2注释"},compProps:{clearable:!0}}},{field:{name:"items2fieldType"},element:{comp:"el-input",formItemProps:{label:"要添加的字段2类型"},compProps:{clearable:!0}}},{field:{name:"items3fieldName"},element:{comp:"el-input",formItemProps:{label:"要添加的字段3名称"},compProps:{clearable:!0,placeholder:"支持数据库字段名自动转换为驼峰命名"}}},{field:{name:"items3fieldComment"},element:{comp:"el-input",formItemProps:{label:"要添加的字段3注释"},compProps:{clearable:!0}}},{field:{name:"items3fieldType"},element:{comp:"el-input",formItemProps:{label:"要添加的字段3类型"},compProps:{clearable:!0}}},{field:{name:"items4fieldName"},element:{comp:"el-input",formItemProps:{label:"要添加的字段4名称"},compProps:{clearable:!0,placeholder:"支持数据库字段名自动转换为驼峰命名"}}},{field:{name:"items4fieldComment"},element:{comp:"el-input",formItemProps:{label:"要添加的字段4注释"},compProps:{clearable:!0}}},{field:{name:"items4fieldType"},element:{comp:"el-input",formItemProps:{label:"要添加的字段4类型"},compProps:{clearable:!0}}},{field:{name:"items5fieldName"},element:{comp:"el-input",formItemProps:{label:"要添加的字段5名称"},compProps:{clearable:!0,placeholder:"支持数据库字段名自动转换为驼峰命名"}}},{field:{name:"items5fieldComment"},element:{comp:"el-input",formItemProps:{label:"要添加的字段5注释"},compProps:{clearable:!0}}},{field:{name:"items5fieldType"},element:{comp:"el-input",formItemProps:{label:"要添加的字段5类型"},compProps:{clearable:!0}}}]),c=s({buttonText:"确认添加"}),b=t=>{let e={afterFieldName:u(t.afterFieldName),componentBackendAbsolutePath:t.componentBackendAbsolutePath,domainName:t.domainName,items:[]};for(let n=0;n<5;n++){let l=n+1,p={fieldName:u(t["items"+l+"fieldName"]),fieldComment:t["items"+l+"fieldComment"],fieldType:t["items"+l+"fieldType"]};p.fieldName&&p.fieldComment&&p.fieldType&&e.items.push(p)}return y(e)},P=()=>"添加成功";return(t,e)=>{const n=d("PtForm"),l=d("el-text");return v(),g(h,null,[m(n,{form:i.form,formData:i.formData,labelWidth:"150",method:b,methodSuccess:P,defaultButtonsShow:"submit,reset",submitAttrs:c.value,layout:[2,1],comps:f.value},null,8,["form","formData","submitAttrs","comps"]),o("div",null,[m(l,{type:"success"},{default:a(()=>e[0]||(e[0]=[r("常用字段类型：一般建议要添加的字段类型输入简单名称如：输入String即可")])),_:1})]),o("div",null,[m(l,null,{default:a(()=>e[1]||(e[1]=[r("java.lang.String")])),_:1})]),o("div",null,[m(l,null,{default:a(()=>e[2]||(e[2]=[r("java.lang.Integer")])),_:1})]),o("div",null,[m(l,null,{default:a(()=>e[3]||(e[3]=[r("java.lang.Boolean")])),_:1})]),o("div",null,[m(l,null,{default:a(()=>e[4]||(e[4]=[r("java.lang.Long")])),_:1})]),o("div",null,[m(l,null,{default:a(()=>e[5]||(e[5]=[r("java.math.BigDecimal")])),_:1})])],64)}}});export{F as default};