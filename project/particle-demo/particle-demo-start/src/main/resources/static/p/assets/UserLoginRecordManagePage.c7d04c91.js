import{h as P,d as B,t as E,G as I,r as _,a as D,x as U,I as L,J as M,b as o,o as T,c as S,e as d,f as n,w as c,F as V,_ as k}from"./index.49d89bd8.js";let R="/admin/web/user-login-record";const y=t=>P.delete(R+"/delete",{data:t}),G=t=>P.get(R+"/page",{params:t}),N=B({name:"UserLoginRecordManagePage"}),q=B({...N,props:{...E,...I},setup(t){const p=t,f=_(null),r=D({form:{},formComps:[U({props:p,required:!1}),L({props:p,required:!1,fieldName:"userIdentifierId"}),{field:{name:"deviceId"},element:{comp:"el-input",formItemProps:{label:"\u8BBE\u5907id"},compProps:{}}}],tableColumns:M}),s=_({buttonText:"\u67E5\u8BE2",loading:!1,permission:"admin:web:userLoginRecord:pageQuery"}),g=()=>{f.value.refreshData()},v=({pageQuery:e})=>G({...r.form,...e}),C={permission:s.value.permission},F=({row:e,column:u,$index:a})=>{if(a<0)return[];let i={id:e.id};return e.id,e.identifier,[{txt:"\u5220\u9664",text:!0,permission:"admin:web:userLoginRecord:delete",methodConfirmText:"\u786E\u5B9A\u8981\u5220\u9664 \u767B\u5F55\u8BB0\u5F55 \u5417\uFF1F",method(){return y(i).then(l=>(g(),Promise.resolve(l)))}}]},x={text:!0,buttonText:"\u66F4\u591A"};return(e,u)=>{const a=o("PtForm"),i=o("PtButtonGroup"),b=o("el-table-column"),l=o("PtTable"),h=o("PtRouteViewPopover");return T(),S(V,null,[d(" \u67E5\u8BE2\u8868\u5355 "),n(a,{form:r.form,method:g,defaultButtonsShow:"submit,reset",submitAttrs:s.value,inline:"",labelWidth:"80",comps:r.formComps},{buttons:c(()=>[]),_:1},8,["form","submitAttrs","comps"]),d(" \u6307\u5B9A dataMethod\uFF0C\u9ED8\u8BA4\u52A0\u8F7D\u6570\u636E "),n(l,{ref_key:"tableRef",ref:f,"default-expand-all":"",dataMethod:v,onDataMethodDataLoading:u[0]||(u[0]=m=>s.value.loading=m),paginationProps:C,columns:r.tableColumns},{defaultAppend:c(()=>[n(b,{label:"\u64CD\u4F5C"},{default:c(({row:m,column:w,$index:A})=>[n(i,{options:F({row:m,column:w,$index:A}),dropdownTriggerButtonOptions:x},null,8,["options"])]),_:1})]),_:1},8,["columns"]),d(" \u5B50\u7EA7\u8DEF\u7531 "),n(h,{level:3})],64)}}}),O=k(q,[["__file","/Users/yw/fh/git-source/particle/web/component/pc/user/views/admin/UserLoginRecordManagePage.vue"]]);export{O as default};