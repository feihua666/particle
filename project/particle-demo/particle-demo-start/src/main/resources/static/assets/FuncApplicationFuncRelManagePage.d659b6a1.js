import{p as C,r as y}from"./funcApplicationFuncRelAdminApi.d0246cc0.js";import{p as h}from"./funcApplicationFuncRelManage.7fa0c732.js";import{d as B,r as E,a as x,b as i,o as I,c as P,e as n,w as e,f as o,F as N}from"./index.4ca05ef1.js";import"./funcApplicationAdminApi.ce1ff3a3.js";const v=B({name:"FuncApplicationFuncRelManagePage"}),V=B({...v,setup(w){const d=E(null),a=x({form:{},formComps:h,tableColumns:[{prop:"funcApplicationName",label:"\u529F\u80FD\u5E94\u7528\u540D\u79F0",showOverflowTooltip:!0,width:120},{prop:"funcName",label:"\u529F\u80FD\u540D\u79F0",showOverflowTooltip:!0,width:120}]}),p=E({buttonText:"\u67E5\u8BE2",loading:!1,permission:"admin:web:funcApplicationFuncRel:pageQuery"}),D=()=>{d.value.refreshData()},b=({pageQuery:u})=>C({...a.form,...u}),R={permission:p.value.permission},g=({row:u,column:F,$index:t})=>{if(t<0)return[];let l={id:u.id},s={funcId:u.funcId,funcName:u.funcName},m={funcId:u.funcId,funcName:u.funcName},r={funcApplicationId:u.funcApplicationId,funcApplicationName:u.funcApplicationName},A={funcApplicationId:u.funcApplicationId,funcApplicationName:u.funcApplicationName};return[{txt:"\u4E3A\u8BE5\u529F\u80FD\u5206\u914D\u529F\u80FD\u5E94\u7528",text:!0,permission:"admin:web:funcApplicationFuncRel:funcAssignFuncApplication",route:{path:"/admin/funcApplicationFuncRelManageFuncAssignFuncApplication",query:s}},{txt:"\u4E3A\u8BE5\u529F\u80FD\u5E94\u7528\u5206\u914D\u529F\u80FD",text:!0,permission:"admin:web:funcApplicationFuncRel:funcApplicationAssignFunc",route:{path:"/admin/funcApplicationFuncRelManageFuncApplicationAssignFunc",query:r}},{txt:"\u4E3A\u8BE5\u529F\u80FD\u6E05\u7A7A\u529F\u80FD\u5E94\u7528",text:!0,methodConfirmText:`\u60A8\u5C06\u6E05\u7A7A\u529F\u80FD ${u.funcName} \u6240\u6709\u529F\u80FD\u5E94\u7528,\u8BE5\u529F\u80FD\u5C06\u4E0D\u518D\u62E5\u6709\u4EFB\u4F55\u529F\u80FD\u5E94\u7528\uFF0C\u8BF7\u8C28\u614E\u64CD\u4F5C\uFF01\uFF01\uFF01\uFF0C\u786E\u5B9A\u8981\u6E05\u7A7A\u5417\uFF1F`,permission:"admin:web:funcApplicationFuncRel:deleteByFuncId",route:{path:"/admin/funcApplicationFuncRelManageDeleteByFuncId",query:m}},{txt:"\u4E3A\u8BE5\u529F\u80FD\u5E94\u7528\u6E05\u7A7A\u529F\u80FD",text:!0,methodConfirmText:`\u60A8\u5C06\u6E05\u7A7A\u529F\u80FD\u5E94\u7528 ${u.funcApplicationName} \u6240\u6709\u529F\u80FD\u5E94\u7528,\u8BE5\u529F\u80FD\u5E94\u7528\u5C06\u4E0D\u518D\u5206\u914D\u7ED9\u4EFB\u4F55\u529F\u80FD\uFF0C\u8BF7\u8C28\u614E\u64CD\u4F5C\uFF01\uFF01\uFF01\uFF0C\u786E\u5B9A\u8981\u6E05\u7A7A\u5417\uFF1F`,permission:"admin:web:funcApplicationFuncRel:deleteByFuncApplicationId",route:{path:"/admin/funcApplicationFuncRelManageDeleteByFuncApplicationId",query:A}},{txt:"\u5220\u9664",text:!0,permission:"admin:web:funcApplicationFuncRel:delete",methodConfirmText:`\u5220\u9664\u540E\u529F\u80FD\u5E94\u7528 ${u.funcApplicationName} \u5C06\u4E0D\u518D\u62E5\u6709\u529F\u80FD ${u.funcName}\uFF0C\u786E\u5B9A\u8981\u5220\u9664\u5417\uFF1F`,method(){return y(l).then(f=>(D(),Promise.resolve(f)))}}]};return(u,F)=>{const t=i("PtButton"),l=i("PtForm"),s=i("PtButtonGroup"),m=i("el-table-column"),r=i("PtTable"),A=i("PtRouteViewPopover");return I(),P(N,null,[n(l,{form:a.form,method:D,defaultButtonsShow:"submit,reset",submitAttrs:p.value,inline:"",comps:a.formComps},{buttons:e(()=>[n(t,{permission:"admin:web:funcApplicationFuncRel:create",route:"/admin/FuncApplicationFuncRelManageAdd"},{default:e(()=>[o("\u6DFB\u52A0")]),_:1}),n(t,{permission:"admin:web:funcApplicationFuncRel:funcAssignFuncApplication",route:"/admin/funcApplicationFuncRelManageFuncAssignFuncApplication"},{default:e(()=>[o("\u529F\u80FD\u5206\u914D\u529F\u80FD\u5E94\u7528")]),_:1}),n(t,{permission:"admin:web:funcApplicationFuncRel:funcApplicationAssignFunc",route:"/admin/funcApplicationFuncRelManageFuncApplicationAssignFunc"},{default:e(()=>[o("\u529F\u80FD\u5E94\u7528\u5206\u914D\u529F\u80FD")]),_:1}),n(t,{permission:"admin:web:funcApplicationFuncRel:deleteByFuncId",route:"/admin/funcApplicationFuncRelManageDeleteByFuncId"},{default:e(()=>[o("\u6E05\u7A7A\u529F\u80FD\u529F\u80FD\u5E94\u7528")]),_:1}),n(t,{permission:"admin:web:funcApplicationFuncRel:deleteByFuncApplicationId",route:"/admin/funcApplicationFuncRelManageDeleteByFuncApplicationId"},{default:e(()=>[o("\u6E05\u7A7A\u529F\u80FD\u5E94\u7528\u529F\u80FD")]),_:1})]),_:1},8,["form","submitAttrs","comps"]),n(r,{ref_key:"tableRef",ref:d,"default-expand-all":"",dataMethod:b,onDataMethodDataLoading:F[0]||(F[0]=c=>p.value.loading=c),paginationProps:R,columns:a.tableColumns},{defaultAppend:e(()=>[n(m,{label:"\u64CD\u4F5C"},{default:e(({row:c,column:f,$index:_})=>[n(s,{options:g({row:c,column:f,$index:_})},null,8,["options"])]),_:1})]),_:1},8,["columns"]),n(A,{level:3})],64)}}});export{V as default};