import{b as w,e as h}from"./usageCountCompItem-CHHDl-Mp.js";import{p as B}from"./usageCountConfigManage-C4_fj-it.js";import{d as D,r as b,a as T,b as t,o as R,c as A,e as o,w as s,f as M,F as N}from"./index-_8rL1G8m.js";const V=D({__name:"UsageCountConfigManagePage",setup(F){const u=b(null),a=T({form:{},formComps:B,tableColumns:[{prop:"name",label:"名称"},{prop:"usageCountDefineName",label:"使用次数定义"},{prop:"limitCount",label:"限制的最大使用次数"},{prop:"limitRuleTypeDictName",label:"限制规则类型"},{prop:"limitPeriodDictName",label:"限制周期"},{prop:"limitTenantName",label:"限制租户名称"},{prop:"exceedTip",label:"超出提示信息"},{prop:"remark",label:"备注"}]}),r=b({buttonText:"查询",loading:!1,permission:"admin:web:usageCountConfig:pageQuery"}),p=()=>{u.value.refreshData()},c=({pageQuery:e})=>w({...a.form,...e}),g={permission:r.value.permission},C=({row:e,column:n,$index:l})=>l<0?[]:[{txt:"编辑",text:!0,permission:"admin:web:usageCountConfig:update",route:{path:"/admin/UsageCountConfigManageUpdate",query:{id:e.id}}},{txt:"删除",text:!0,permission:"admin:web:usageCountConfig:delete",methodConfirmText:`确定要删除 ${e.name} 吗？`,method(){return h({id:e.id}).then(i=>(p(),Promise.resolve(i)))}}];return(e,n)=>{const l=t("PtButton"),d=t("PtForm"),f=t("PtButtonGroup"),i=t("el-table-column"),_=t("PtTable"),P=t("PtRouteViewPopover");return R(),A(N,null,[o(d,{form:a.form,method:p,labelWidth:"100",defaultButtonsShow:"submit,reset",submitAttrs:r.value,inline:"",comps:a.formComps},{buttons:s(()=>[o(l,{permission:"admin:web:usageCountConfig:create",route:"/admin/UsageCountConfigManageAdd"},{default:s(()=>n[1]||(n[1]=[M("添加")])),_:1})]),_:1},8,["form","submitAttrs","comps"]),o(_,{ref_key:"tableRef",ref:u,"default-expand-all":"",dataMethod:c,onDataMethodDataLoading:n[0]||(n[0]=m=>r.value.loading=m),paginationProps:g,columns:a.tableColumns},{defaultAppend:s(()=>[o(i,{label:"操作",width:"180"},{default:s(({row:m,column:v,$index:x})=>[o(f,{options:C({row:m,column:v,$index:x})},null,8,["options"])]),_:1})]),_:1},8,["columns"]),o(P,{level:3})],64)}}});export{V as default};