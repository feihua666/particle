import{p as g,a as C,r as x,b as D}from"./openplatformAppOpenapiManage.8c2c8bbf.js";import{d as b,r as c,a as v,b as o,o as w,c as E,e as p,w as u,f as N,F as R}from"./index.6cdd7c8b.js";import"./openplatformAppCompItem.91c2941f.js";import"./openplatformAppAdminApi.f8e9dd18.js";import"./openplatformOpenapiCompItem.3a44cfd6.js";import"./openplatformOpenapiFeeCompItem.8ab3f69a.js";import"./openplatformOpenapiFeeAdminApi.398af819.js";import"./openplatformProviderCompItem.ab058855.js";import"./openplatformProviderAdminApi.aa64d266.js";import"./openplatformOpenapiLimitRuleCompItem.790b8480.js";import"./openplatformOpenapiLimitRuleAdminApi.35f68dcc.js";const T=b({name:"OpenplatformAppOpenapiManagePage"}),j=b({...T,setup(M){const s=c(null),a=v({form:{},formComps:g,tableColumns:[{prop:"openplatformAppName",label:"app\u540D\u79F0"},{prop:"openplatformOpenapiName",label:"\u5F00\u653E\u63A5\u53E3\u540D\u79F0"},{prop:"openplatformOpenapiFeeName",label:"\u8BA1\u8D39\u89C4\u5219\u540D\u79F0"},{prop:"openplatformOpenapiLimitRuleName",label:"\u9650\u5236\u89C4\u5219\u540D\u79F0"},{prop:"openplatformProviderName",label:"\u6307\u5B9A\u4F9B\u5E94\u5546\u540D\u79F0"}]}),n=c({buttonText:"\u67E5\u8BE2",loading:!1,permission:"admin:web:openplatformAppOpenapi:pageQuery"}),l=()=>{s.value.refreshData()},F=({pageQuery:e})=>C({...a.form,...e}),_={permission:n.value.permission},A=({row:e,column:r,$index:m})=>m<0?[]:[{txt:"\u7F16\u8F91",text:!0,permission:"admin:web:openplatformAppOpenapi:update",route:{path:"/admin/OpenplatformAppOpenapiManageUpdate",query:{id:e.id}}},{txt:"\u5220\u9664",text:!0,position:"more",permission:"admin:web:openplatformAppOpenapi:delete",methodConfirmText:`\u786E\u5B9A\u8981\u5220\u9664 ${e.openplatformAppName} \u548C ${e.openplatformOpenapiName} \u5417\uFF1F`,method(){return x({id:e.id}).then(t=>(l(),Promise.resolve(t)))}},{txt:"\u5237\u65B0\u7F13\u5B58",text:!0,position:"more",permission:"admin:web:openplatformAppOpenapi:refreshCache",methodSuccess:t=>"\u5237\u65B0\u7F13\u5B58\u6210\u529F,\u5982\u679C\u90E8\u7F72\u591A\u4E2A\u5B9E\u4F8B\u53EF\u80FD\u8981\u591A\u6B21\u6267\u884C\u3002 "+t.data.data,method(){return D({id:e.id}).then(t=>Promise.resolve(t))}}];return(e,r)=>{const m=o("PtButton"),d=o("PtForm"),f=o("PtButtonGroup"),t=o("el-table-column"),B=o("PtTable"),P=o("PtRouteViewPopover");return w(),E(R,null,[p(d,{form:a.form,method:l,defaultButtonsShow:"submit,reset",submitAttrs:n.value,inline:"",comps:a.formComps},{buttons:u(()=>[p(m,{permission:"admin:web:openplatformAppOpenapi:create",route:"/admin/OpenplatformAppOpenapiManageAdd"},{default:u(()=>[N("\u6DFB\u52A0")]),_:1})]),_:1},8,["form","submitAttrs","comps"]),p(B,{ref_key:"tableRef",ref:s,"default-expand-all":"",dataMethod:F,onDataMethodDataLoading:r[0]||(r[0]=i=>n.value.loading=i),paginationProps:_,columns:a.tableColumns},{defaultAppend:u(()=>[p(t,{label:"\u64CD\u4F5C",width:"180"},{default:u(({row:i,column:h,$index:O})=>[p(f,{options:A({row:i,column:h,$index:O}),dropdownTriggerButtonOptions:{text:!0,buttonText:"\u66F4\u591A"}},null,8,["options"])]),_:1})]),_:1},8,["columns"]),p(P,{level:3})],64)}}});export{j as default};