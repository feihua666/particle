import{d as D,r as b,a as h,b as t,o as P,c as v,e as i,f as u,w as r,g as E,F as R,_ as x}from"./index.49d89bd8.js";import{p as O,a as T,r as M}from"./openplatformDocApiDocResponseCodeManage.29fb9b8e.js";import"./openplatformDocCompItem.c10d6dbc.js";import"./openplatformDocDirNameAdminApi.af5fa58b.js";import"./openplatformDocApiAdminApi.1fb09c96.js";import"./openplatformDocApiDocTemplateAdminApi.753a0efe.js";const V=D({name:"OpenplatformDocApiDocResponseCodeManagePage"}),k=D({...V,setup(y){const c=b(null),n=h({form:{},formComps:O,tableColumns:[{prop:"code",label:"\u4E1A\u52A1\u7F16\u7801"},{prop:"codeStatus",label:"\u4E1A\u52A1\u72B6\u6001\u7801"},{prop:"httpCode",label:"http\u72B6\u6001\u7801"},{prop:"isCharge",label:"\u662F\u5426\u8BA1\u8D39",formatter:(e,a,o,p)=>o?"\u662F":"\u5426"},{prop:"explanation",label:"\u5B57\u6BB5\u8BF4\u660E"},{prop:"openplatformDocApiName",label:"\u7ED1\u5B9A\u63A5\u53E3",showOverflowTooltip:!0},{prop:"openplatformDocApiCode",label:"\u7ED1\u5B9A\u63A5\u53E3\u7F16\u7801",showOverflowTooltip:!0},{prop:"openplatformDocApiDocRequestUrl",label:"\u6587\u6863\u5185\u5BB9",showOverflowTooltip:!0},{prop:"isGlobal",label:"\u662F\u5426\u5168\u5C40\u901A\u7528\u7801",formatter:(e,a,o,p)=>o?"\u662F":"\u5426"},{prop:"seq",label:"\u6392\u5E8F"},{prop:"remark",label:"\u63CF\u8FF0",showOverflowTooltip:!0}]}),s=b({buttonText:"\u67E5\u8BE2",loading:!1,permission:"admin:web:openplatformDocApiDocResponseCode:pageQuery"}),d=()=>{c.value.refreshData()},A=({pageQuery:e})=>T({...n.form,...e}),_={permission:s.value.permission},B=({row:e,column:a,$index:o})=>o<0?[]:[{txt:"\u7F16\u8F91",text:!0,permission:"admin:web:openplatformDocApiDocResponseCode:update",route:{path:"/admin/OpenplatformDocApiDocResponseCodeManageUpdate",query:{id:e.id}}},{txt:"\u5220\u9664",text:!0,permission:"admin:web:openplatformDocApiDocResponseCode:delete",methodConfirmText:`\u786E\u5B9A\u8981\u5220\u9664 ${e.code} \u5417\uFF1F`,method(){return M({id:e.id}).then(l=>(d(),Promise.resolve(l)))}}];return(e,a)=>{const o=t("PtButton"),p=t("PtForm"),f=t("PtButtonGroup"),l=t("el-table-column"),C=t("PtTable"),F=t("PtRouteViewPopover");return P(),v(R,null,[i(" \u67E5\u8BE2\u8868\u5355 "),u(p,{form:n.form,method:d,defaultButtonsShow:"submit,reset",submitAttrs:s.value,inline:"",comps:n.formComps},{buttons:r(()=>[u(o,{permission:"admin:web:openplatformDocApiDocResponseCode:create",route:"/admin/OpenplatformDocApiDocResponseCodeManageAdd"},{default:r(()=>[E("\u6DFB\u52A0")]),_:1})]),_:1},8,["form","submitAttrs","comps"]),i(" \u6307\u5B9A dataMethod\uFF0C\u9ED8\u8BA4\u52A0\u8F7D\u6570\u636E "),u(C,{ref_key:"tableRef",ref:c,"default-expand-all":"",dataMethod:A,onDataMethodDataLoading:a[0]||(a[0]=m=>s.value.loading=m),paginationProps:_,columns:n.tableColumns},{defaultAppend:r(()=>[u(l,{label:"\u64CD\u4F5C",width:"180"},{default:r(({row:m,column:w,$index:g})=>[u(f,{options:B({row:m,column:w,$index:g})},null,8,["options"])]),_:1})]),_:1},8,["columns"]),i(" \u5B50\u7EA7\u8DEF\u7531 "),u(F,{level:3})],64)}}}),L=x(k,[["__file","/Users/yw/fh/git-source/particle/web/component/pc/openplatform/views/doc/admin/OpenplatformDocApiDocResponseCodeManagePage.vue"]]);export{L as default};