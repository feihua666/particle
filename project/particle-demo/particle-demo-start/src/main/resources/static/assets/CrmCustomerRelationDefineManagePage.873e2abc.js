import{q as R,s as v}from"./crmCompItem.d9c3dd30.js";import{p as w}from"./crmCustomerRelationDefineManage.f63f4489.js";import{d as C,r as f,a as x,b as t,o as h,c as A,e as o,w as r,f as E,F as M}from"./index.bd091b8a.js";import"./crmCustomerTagAdminApi.9e96f732.js";const T=C({name:"CrmCustomerRelationDefineManagePage"}),G=C({...T,setup(k){const d=f(null),u=x({form:{},formComps:w,tableColumns:[{prop:"name",label:"\u5173\u7CFB\u5B9A\u4E49\u540D\u79F0"},{prop:"code",label:"\u5173\u7CFB\u5B9A\u4E49\u7F16\u7801"},{prop:"isBidirectional",label:"\u5173\u7CFB\u7C7B\u578B",formatter:(e,a,n,s)=>n?"\u53CC\u5411\u5173\u7CFB":"\u5355\u5411\u5173\u7CFB"},{prop:"bidirectionalName",label:"\u5BF9\u5E94\u53CC\u5411\u5173\u7CFB"},{prop:"remark",label:"\u5907\u6CE8"}]}),m=f({buttonText:"\u67E5\u8BE2",loading:!1,permission:"admin:web:crmCustomerRelationDefine:pageQuery"}),c=()=>{d.value.refreshData()},b=({pageQuery:e})=>R({...u.form,...e}),_={permission:m.value.permission},B=({row:e,column:a,$index:n})=>n<0?[]:[{txt:"\u7F16\u8F91",text:!0,permission:"admin:web:crmCustomerRelationDefine:update",route:{path:"/admin/CrmCustomerRelationDefineManageUpdate",query:{id:e.id}}},{txt:"\u5220\u9664",text:!0,permission:"admin:web:crmCustomerRelationDefine:delete",methodConfirmText:`\u786E\u5B9A\u8981\u5220\u9664 ${e.name} \u5417\uFF1F`,method(){return v({id:e.id}).then(l=>(c(),Promise.resolve(l)))}}];return(e,a)=>{const n=t("PtButton"),s=t("PtForm"),i=t("PtButtonGroup"),l=t("el-table-column"),F=t("PtTable"),P=t("PtRouteViewPopover");return h(),A(M,null,[o(s,{form:u.form,method:c,defaultButtonsShow:"submit,reset",submitAttrs:m.value,inline:"",comps:u.formComps},{buttons:r(()=>[o(n,{permission:"admin:web:crmCustomerRelationDefine:create",route:"/admin/CrmCustomerRelationDefineManageAdd"},{default:r(()=>[E("\u6DFB\u52A0")]),_:1})]),_:1},8,["form","submitAttrs","comps"]),o(F,{ref_key:"tableRef",ref:d,"default-expand-all":"",dataMethod:b,onDataMethodDataLoading:a[0]||(a[0]=p=>m.value.loading=p),paginationProps:_,columns:u.tableColumns},{defaultAppend:r(()=>[o(l,{label:"\u64CD\u4F5C",width:"180"},{default:r(({row:p,column:D,$index:g})=>[o(i,{options:B({row:p,column:D,$index:g})},null,8,["options"])]),_:1})]),_:1},8,["columns"]),o(P,{level:3})],64)}}});export{G as default};