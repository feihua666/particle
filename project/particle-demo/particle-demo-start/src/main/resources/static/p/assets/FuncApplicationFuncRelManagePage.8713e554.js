import{d as g,r as B,a as h,b as a,o as y,c as x,e as d,f as n,w as e,g as i,F as P,_ as I}from"./index.49d89bd8.js";import{p as N,r as v}from"./funcApplicationFuncRelAdminApi.76fa9390.js";import{p as w}from"./funcApplicationFuncRelManage.85105fa7.js";import"./funcApplicationAdminApi.58b01f31.js";const M=g({name:"FuncApplicationFuncRelManagePage"}),T=g({...M,setup(Q){const D=B(null),o=h({form:{},formComps:w,tableColumns:[{prop:"funcApplicationName",label:"\u529F\u80FD\u5E94\u7528\u540D\u79F0",showOverflowTooltip:!0,width:120},{prop:"funcName",label:"\u529F\u80FD\u540D\u79F0",showOverflowTooltip:!0,width:120}]}),p=B({buttonText:"\u67E5\u8BE2",loading:!1,permission:"admin:web:funcApplicationFuncRel:pageQuery"}),E=()=>{D.value.refreshData()},_=({pageQuery:u})=>N({...o.form,...u}),b={permission:p.value.permission},R=({row:u,column:F,$index:t})=>{if(t<0)return[];let l={id:u.id},s={funcId:u.funcId,funcName:u.funcName},m={funcId:u.funcId,funcName:u.funcName},r={funcApplicationId:u.funcApplicationId,funcApplicationName:u.funcApplicationName},A={funcApplicationId:u.funcApplicationId,funcApplicationName:u.funcApplicationName};return[{txt:"\u4E3A\u8BE5\u529F\u80FD\u5206\u914D\u529F\u80FD\u5E94\u7528",text:!0,permission:"admin:web:funcApplicationFuncRel:funcAssignFuncApplication",route:{path:"/admin/funcApplicationFuncRelManageFuncAssignFuncApplication",query:s}},{txt:"\u4E3A\u8BE5\u529F\u80FD\u5E94\u7528\u5206\u914D\u529F\u80FD",text:!0,permission:"admin:web:funcApplicationFuncRel:funcApplicationAssignFunc",route:{path:"/admin/funcApplicationFuncRelManageFuncApplicationAssignFunc",query:r}},{txt:"\u4E3A\u8BE5\u529F\u80FD\u6E05\u7A7A\u529F\u80FD\u5E94\u7528",text:!0,methodConfirmText:`\u60A8\u5C06\u6E05\u7A7A\u529F\u80FD ${u.funcName} \u6240\u6709\u529F\u80FD\u5E94\u7528,\u8BE5\u529F\u80FD\u5C06\u4E0D\u518D\u62E5\u6709\u4EFB\u4F55\u529F\u80FD\u5E94\u7528\uFF0C\u8BF7\u8C28\u614E\u64CD\u4F5C\uFF01\uFF01\uFF01\uFF0C\u786E\u5B9A\u8981\u6E05\u7A7A\u5417\uFF1F`,permission:"admin:web:funcApplicationFuncRel:deleteByFuncId",route:{path:"/admin/funcApplicationFuncRelManageDeleteByFuncId",query:m}},{txt:"\u4E3A\u8BE5\u529F\u80FD\u5E94\u7528\u6E05\u7A7A\u529F\u80FD",text:!0,methodConfirmText:`\u60A8\u5C06\u6E05\u7A7A\u529F\u80FD\u5E94\u7528 ${u.funcApplicationName} \u6240\u6709\u529F\u80FD\u5E94\u7528,\u8BE5\u529F\u80FD\u5E94\u7528\u5C06\u4E0D\u518D\u5206\u914D\u7ED9\u4EFB\u4F55\u529F\u80FD\uFF0C\u8BF7\u8C28\u614E\u64CD\u4F5C\uFF01\uFF01\uFF01\uFF0C\u786E\u5B9A\u8981\u6E05\u7A7A\u5417\uFF1F`,permission:"admin:web:funcApplicationFuncRel:deleteByFuncApplicationId",route:{path:"/admin/funcApplicationFuncRelManageDeleteByFuncApplicationId",query:A}},{txt:"\u5220\u9664",text:!0,permission:"admin:web:funcApplicationFuncRel:delete",methodConfirmText:`\u5220\u9664\u540E\u529F\u80FD\u5E94\u7528 ${u.funcApplicationName} \u5C06\u4E0D\u518D\u62E5\u6709\u529F\u80FD ${u.funcName}\uFF0C\u786E\u5B9A\u8981\u5220\u9664\u5417\uFF1F`,method(){return v(l).then(f=>(E(),Promise.resolve(f)))}}]};return(u,F)=>{const t=a("PtButton"),l=a("PtForm"),s=a("PtButtonGroup"),m=a("el-table-column"),r=a("PtTable"),A=a("PtRouteViewPopover");return y(),x(P,null,[d(" \u67E5\u8BE2\u8868\u5355 "),n(l,{form:o.form,method:E,defaultButtonsShow:"submit,reset",submitAttrs:p.value,inline:"",comps:o.formComps},{buttons:e(()=>[n(t,{permission:"admin:web:funcApplicationFuncRel:create",route:"/admin/FuncApplicationFuncRelManageAdd"},{default:e(()=>[i("\u6DFB\u52A0")]),_:1}),n(t,{permission:"admin:web:funcApplicationFuncRel:funcAssignFuncApplication",route:"/admin/funcApplicationFuncRelManageFuncAssignFuncApplication"},{default:e(()=>[i("\u529F\u80FD\u5206\u914D\u529F\u80FD\u5E94\u7528")]),_:1}),n(t,{permission:"admin:web:funcApplicationFuncRel:funcApplicationAssignFunc",route:"/admin/funcApplicationFuncRelManageFuncApplicationAssignFunc"},{default:e(()=>[i("\u529F\u80FD\u5E94\u7528\u5206\u914D\u529F\u80FD")]),_:1}),n(t,{permission:"admin:web:funcApplicationFuncRel:deleteByFuncId",route:"/admin/funcApplicationFuncRelManageDeleteByFuncId"},{default:e(()=>[i("\u6E05\u7A7A\u529F\u80FD\u529F\u80FD\u5E94\u7528")]),_:1}),n(t,{permission:"admin:web:funcApplicationFuncRel:deleteByFuncApplicationId",route:"/admin/funcApplicationFuncRelManageDeleteByFuncApplicationId"},{default:e(()=>[i("\u6E05\u7A7A\u529F\u80FD\u5E94\u7528\u529F\u80FD")]),_:1})]),_:1},8,["form","submitAttrs","comps"]),d(" \u6307\u5B9A dataMethod\uFF0C\u9ED8\u8BA4\u52A0\u8F7D\u6570\u636E "),n(r,{ref_key:"tableRef",ref:D,"default-expand-all":"",dataMethod:_,onDataMethodDataLoading:F[0]||(F[0]=c=>p.value.loading=c),paginationProps:b,columns:o.tableColumns},{defaultAppend:e(()=>[n(m,{label:"\u64CD\u4F5C"},{default:e(({row:c,column:f,$index:C})=>[n(s,{options:R({row:c,column:f,$index:C})},null,8,["options"])]),_:1})]),_:1},8,["columns"]),d(" \u5B50\u7EA7\u8DEF\u7531 "),n(A,{level:3})],64)}}}),G=I(T,[["__file","/Users/yw/fh/git-source/particle/web/component/pc/func/views/admin/FuncApplicationFuncRelManagePage.vue"]]);export{G as default};