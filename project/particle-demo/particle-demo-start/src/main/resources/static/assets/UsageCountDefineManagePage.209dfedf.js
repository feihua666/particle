import{p as h,r as v}from"./usageCountCompItem.10fd9340.js";import{p as w}from"./usageCountDefineManage.93d21904.js";import{d as b,r as f,a as x,b as t,o as A,c as E,e as o,w as r,f as R,F as T}from"./index.d7ae4034.js";import"./treeQueryComps.5854cd36.js";const M=b({name:"UsageCountDefineManagePage"}),q=b({...M,setup(U){const p=f(null),a=x({form:{},formComps:w,tableColumns:[{prop:"name",label:"\u540D\u79F0"},{prop:"code",label:"\u7F16\u7801"},{prop:"urlPattern",label:"url\u6A21\u5F0F"},{prop:"isGroup",label:"\u662F\u5426\u5206\u7EC4",width:80,formatter:(e,u,n,l)=>n?"\u5206\u7EC4":"\u5B9A\u4E49"},{prop:"seq",label:"\u6392\u5E8F"},{prop:"remark",label:"\u5907\u6CE8"}]}),s=f({buttonText:"\u67E5\u8BE2",loading:!1,permission:"admin:web:usageCountDefine:pageQuery"}),d=()=>{p.value.refreshData()},_=({pageQuery:e})=>h({...a.form,...e}),g={permission:s.value.permission},C=({row:e,column:u,$index:n})=>n<0?[]:[{txt:"\u7F16\u8F91",text:!0,permission:"admin:web:usageCountDefine:update",route:{path:"/admin/UsageCountDefineManageUpdate",query:{id:e.id}}},{txt:"\u5220\u9664",text:!0,permission:"admin:web:usageCountDefine:delete",methodConfirmText:`\u786E\u5B9A\u8981\u5220\u9664 ${e.name} \u5417\uFF1F`,method(){return v({id:e.id}).then(m=>(d(),Promise.resolve(m)))}}];return(e,u)=>{const n=t("PtButton"),l=t("PtForm"),c=t("PtButtonGroup"),m=t("el-table-column"),P=t("PtTable"),F=t("PtRouteViewPopover");return A(),E(T,null,[o(l,{form:a.form,method:d,defaultButtonsShow:"submit,reset",submitAttrs:s.value,inline:"",comps:a.formComps},{buttons:r(()=>[o(n,{permission:"admin:web:usageCountDefine:create",route:"/admin/UsageCountDefineManageAdd"},{default:r(()=>[R("\u6DFB\u52A0")]),_:1})]),_:1},8,["form","submitAttrs","comps"]),o(P,{ref_key:"tableRef",ref:p,"default-expand-all":"",dataMethod:_,onDataMethodDataLoading:u[0]||(u[0]=i=>s.value.loading=i),dataMethodResultHandleConvertToTree:!0,paginationProps:g,columns:a.tableColumns},{defaultAppend:r(()=>[o(m,{label:"\u64CD\u4F5C",width:"180"},{default:r(({row:i,column:D,$index:B})=>[o(c,{options:C({row:i,column:D,$index:B})},null,8,["options"])]),_:1})]),_:1},8,["columns"]),o(F,{level:3})],64)}}});export{q as default};