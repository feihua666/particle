import{p as _,r as D}from"./opLogAdminApi-BJtQYGiV.js";import{d as I,r as d,a as T,b as r,o as y,c as x,e as m,w as f,F as O}from"./index-_8rL1G8m.js";const A=[{field:{name:"name"},element:{comp:"el-input",formItemProps:{label:"操作名称"},compProps:{clearable:!0,placeholder:"左前缀匹配，如：添加用户"}}},{field:{name:"moduleDictId"},element:{comp:"PtDictFrontSelect",formItemProps:{label:"模块字典"},compProps:{dictParam:{groupCode:"op_log_module"}}}},{field:{name:"module"},element:{comp:"el-input",formItemProps:{label:"模块"},compProps:{clearable:!0,placeholder:"左前缀匹配，如：user"}}},{field:{name:"typeDictId"},element:{comp:"PtDictFrontSelect",formItemProps:{label:"类型字典"},compProps:{dictParam:{groupCode:"op_log_type"}}}},{field:{name:"type"},element:{comp:"el-input",formItemProps:{label:"类型"},compProps:{clearable:!0,placeholder:"左前缀匹配，如：create"}}},{field:{name:"userName"},element:{comp:"el-input",formItemProps:{label:"操作用户姓名"},compProps:{clearable:!0,placeholder:"左前缀匹配"}}},{field:{name:"userNickname"},element:{comp:"el-input",formItemProps:{label:"操作用户昵称"},compProps:{clearable:!0,placeholder:"左前缀匹配"}}},{field:{name:"url"},element:{comp:"el-input",formItemProps:{label:"请求地址"},compProps:{clearable:!0}}},{field:{name:"mainDataId"},element:{comp:"el-input",formItemProps:{label:"主数据id"},compProps:{}}},{field:{name:"mainDataTable"},element:{comp:"el-input",formItemProps:{label:"主数据表名"},compProps:{clearable:!0}}},{field:{name:"mainDataEntity"},element:{comp:"el-input",formItemProps:{label:"主数据载体"},compProps:{clearable:!0}}},{field:{name:"operateAtStart"},element:{comp:"PtDatePicker",formItemProps:{label:"操作时间开始"},compProps:{type:"datetime"}}},{field:{name:"operateAtEnd"},element:{comp:"PtDatePicker",formItemProps:{label:"操作时间结束"},compProps:{type:"datetime"}}}],k=I({__name:"OpLogManagePage",setup(C){const u=d(null),p=T({form:{},formComps:A,tableColumns:[{prop:"name",label:"操作名称",showOverflowTooltip:!0},{prop:"module",label:"模块(名称)",formatter:(e,l,t,a)=>{let o=t;return e.moduleDictName&&(o=o+"("+e.moduleDictName+")"),o},showOverflowTooltip:!0},{prop:"type",label:"类型(名称)",formatter:(e,l,t,a)=>{let o=t;return e.typeDictName&&(o=o+"("+e.typeDictName+")"),o},showOverflowTooltip:!0},{prop:"userName",label:"用户姓名(昵称)",formatter:(e,l,t,a)=>{let o=t||"";return e.userNickname&&(o=o+"("+e.userNickname+")"),o},showOverflowTooltip:!0},{prop:"userAvatar",label:"用户头像",columnView:"image"},{prop:"url",label:"请求地址",showOverflowTooltip:!0},{prop:"ip",label:"ip",showOverflowTooltip:!0},{prop:"mainDataId",label:"主数据id",showOverflowTooltip:!0},{prop:"mainDataTable",label:"主数据表名",showOverflowTooltip:!0},{prop:"mainDataEntity",label:"主数据载体",showOverflowTooltip:!0},{prop:"operateAt",label:"操作时间",showOverflowTooltip:!0}]}),n=d({buttonText:"查询",loading:!1,permission:"admin:web:opLog:pageQuery"}),c=()=>{u.value.refreshData()},b=({pageQuery:e})=>_({...p.form,...e}),P={permission:n.value.permission},g=({row:e,column:l,$index:t})=>t<0?[]:(e.id,[{txt:"审计数据",text:!0,permission:"admin:web:opLogAuditData:pageQuery",route:{path:"/admin/opLogAuditDataManagePopoverPage",query:{opLogId:e.id,opLogName:e.name}}},{txt:"删除",text:!0,permission:"admin:web:opLog:delete",methodConfirmText:`确定要删除 ${e.name} 吗？`,method(){return D({id:e.id}).then(i=>(c(),Promise.resolve(i)))}}]);return(e,l)=>{const t=r("PtForm"),a=r("PtButtonGroup"),o=r("el-table-column"),i=r("PtTable"),h=r("PtRouteViewPopover");return y(),x(O,null,[m(t,{form:p.form,method:c,"label-width":"120",defaultButtonsShow:"submit,reset",submitAttrs:n.value,inline:"",comps:p.formComps},null,8,["form","submitAttrs","comps"]),m(i,{ref_key:"tableRef",ref:u,"default-expand-all":"",dataMethod:b,onDataMethodDataLoading:l[0]||(l[0]=s=>n.value.loading=s),dataMethodResultHandleConvertToTree:!0,paginationProps:P,columns:p.tableColumns},{defaultAppend:f(()=>[m(o,{label:"操作",width:"220"},{default:f(({row:s,column:w,$index:v})=>[m(a,{options:g({row:s,column:w,$index:v})},null,8,["options"])]),_:1})]),_:1},8,["columns"]),m(h,{level:3})],64)}}});export{k as default};