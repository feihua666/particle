import{p as x,r as A,a as T}from"./openplatformAppAdminApi-BfcI6YuV.js";import{p as C}from"./openplatformAppManage-C5XA6L97.js";import{d as O,r as c,a as B,b as a,o as R,c as F,e as p,w as m,f as N,F as k}from"./index-_8rL1G8m.js";import"./userCompItem-CsXXKKu7.js";import"./userAdminApi-DZfFHVq4.js";import"./oauth2authorizationRegisteredClientCompItem-C8e2o5uV.js";import"./oauth2RegisteredClientAdminApi-DLQ17qs8.js";import"./openplatformOpenapiFeeCompItem-CDoTJAzK.js";import"./openplatformOpenapiFeeAdminApi-CZIOoqKg.js";import"./crmCompItem-BmbgZ0A6.js";import"./crmCustomerTagAdminApi-CEtzmh3W.js";import"./openplatformOpenapiLimitRuleCompItem-3fhm1zVA.js";import"./openplatformOpenapiLimitRuleAdminApi-BFr753sj.js";const W=O({__name:"OpenplatformAppManagePage",setup(D){const d=c(null),s=B({form:{},formComps:C,tableColumns:[{prop:"name",label:"名称",showOverflowTooltip:!0},{prop:"appId",label:"appId",showOverflowTooltip:!0},{prop:"ownerUserNickname",label:"归属用户昵称"},{prop:"ownerCustomerName",label:"归属客户"},{prop:"requestAlgorithmSecretJson",label:"请求配置",showOverflowTooltip:!0},{prop:"responseAlgorithmSecretJson",label:"响应配置",showOverflowTooltip:!0},{prop:"scopes",label:"访问范围配置",showOverflowTooltip:!0},{prop:"openplatformOpenapiFeeName",label:"计费规则名称"},{prop:"openplatformOpenapiLimitRuleName",label:"限制规则名称"},{prop:"isDisabled",label:"是否禁用",formatter:(e,o,t,l)=>{let r=t?"禁用":"启用";return t&&e.disabledReason&&(r=r+`(${e.disabledReason})`),r}},{prop:"isCheckSignature",label:"是否检查签名",formatter:(e,o,t,l)=>t?"检查":"忽略"}]}),i=c({buttonText:"查询",loading:!1,permission:"admin:web:openplatformApp:pageQuery"}),f=()=>{d.value.refreshData()},b=({pageQuery:e})=>x({...s.form,...e}),h={permission:i.value.permission},w=({row:e,column:o,$index:t})=>t<0?[]:(e.id,[{txt:"编辑",text:!0,permission:"admin:web:openplatformApp:update",route:{path:"/admin/OpenplatformAppManageUpdate",query:{id:e.id,ownerUserId:e.ownerUserId,ownerUserNickname:e.ownerUserNickname}}},{txt:"删除",text:!0,position:"more",permission:"admin:web:openplatformApp:delete",methodConfirmText:`确定要删除 ${e.name} 吗？`,method(){return A({id:e.id}).then(n=>(f(),Promise.resolve(n)))}},{txt:"刷新缓存",text:!0,position:"more",permission:"admin:web:openplatformApp:refreshCache",methodSuccess:n=>"刷新缓存成功,如果部署多个实例可能要多次执行。 "+n.data.data,method(){return T({id:e.id}).then(n=>Promise.resolve(n))}}]);return(e,o)=>{const t=a("PtButton"),l=a("PtForm"),r=a("PtButtonGroup"),n=a("el-table-column"),_=a("PtTable"),g=a("PtRouteViewPopover");return R(),F(k,null,[p(l,{form:s.form,labelWidth:"120",method:f,defaultButtonsShow:"submit,reset",submitAttrs:i.value,inline:"",comps:s.formComps},{buttons:m(()=>[p(t,{permission:"admin:web:openplatformApp:create",route:"/admin/OpenplatformAppManageAdd"},{default:m(()=>o[1]||(o[1]=[N("添加")])),_:1})]),_:1},8,["form","submitAttrs","comps"]),p(_,{ref_key:"tableRef",ref:d,"default-expand-all":"",dataMethod:b,onDataMethodDataLoading:o[0]||(o[0]=u=>i.value.loading=u),paginationProps:h,columns:s.tableColumns},{defaultAppend:m(()=>[p(n,{label:"操作",width:"180"},{default:m(({row:u,column:P,$index:v})=>[p(r,{options:w({row:u,column:P,$index:v}),dropdownTriggerButtonOptions:{text:!0,buttonText:"更多"}},null,8,["options"])]),_:1})]),_:1},8,["columns"]),p(g,{level:3})],64)}}});export{W as default};