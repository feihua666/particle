import{p as R,a as M,r as w}from"./openplatformProviderRecordPrdMonthBillManage-17VExkig.js";import{d as x,r as f,a as C,b as t,o as A,c as F,e as o,w as n,f as D,F as T}from"./index-_8rL1G8m.js";const V=x({__name:"OpenplatformProviderRecordPrdMonthBillManagePage",setup(k){const i=f(null),a=C({form:{},formComps:R,tableColumns:[{prop:"openplatformProviderId",label:"供应商id"},{prop:"year",label:"年"},{prop:"month",label:"月"},{prop:"totalCall",label:"调用总量"},{prop:"totalFeeCall",label:"调用计费总量"},{prop:"averageUnitPriceAmount",label:"平均单价金额"},{prop:"totalFeeAmount",label:"总消费金额（分）"},{prop:"remark",label:"描述"}]}),l=f({buttonText:"查询",loading:!1,permission:"admin:web:openplatformProviderRecordPrdMonthBill:pageQuery"}),d=()=>{i.value.refreshData()},P=({pageQuery:e})=>M({...a.form,...e}),b={permission:l.value.permission},_=({row:e,column:r,$index:p})=>p<0?[]:[{txt:"编辑",text:!0,permission:"admin:web:openplatformProviderRecordPrdMonthBill:update",route:{path:"/admin/OpenplatformProviderRecordPrdMonthBillManageUpdate",query:{id:e.id}}},{txt:"删除",text:!0,permission:"admin:web:openplatformProviderRecordPrdMonthBill:delete",methodConfirmText:`确定要删除 ${e.name} 吗？`,method(){return w({id:e.id}).then(m=>(d(),Promise.resolve(m)))}}];return(e,r)=>{const p=t("PtButton"),u=t("PtForm"),c=t("PtButtonGroup"),m=t("el-table-column"),v=t("PtTable"),h=t("PtRouteViewPopover");return A(),F(T,null,[o(u,{form:a.form,method:d,defaultButtonsShow:"submit,reset",submitAttrs:l.value,inline:"",comps:a.formComps},{buttons:n(()=>[o(p,{permission:"admin:web:openplatformProviderRecordPrdMonthBill:create",route:"/admin/OpenplatformProviderRecordPrdMonthBillManageAdd"},{default:n(()=>r[1]||(r[1]=[D("添加")])),_:1})]),_:1},8,["form","submitAttrs","comps"]),o(v,{ref_key:"tableRef",ref:i,"default-expand-all":"",dataMethod:P,onDataMethodDataLoading:r[0]||(r[0]=s=>l.value.loading=s),paginationProps:b,columns:a.tableColumns},{defaultAppend:n(()=>[o(m,{label:"操作",width:"180"},{default:n(({row:s,column:B,$index:g})=>[o(c,{options:_({row:s,column:B,$index:g})},null,8,["options"])]),_:1})]),_:1},8,["columns"]),o(h,{level:3})],64)}}});export{V as default};