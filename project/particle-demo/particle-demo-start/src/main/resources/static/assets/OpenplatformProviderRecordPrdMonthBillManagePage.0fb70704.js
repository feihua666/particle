import{p as g,a as D,r as R}from"./openplatformProviderRecordPrdMonthBillManage.856d3f4d.js";import{d as P,r as f,a as M,b as t,o as w,c as x,e as o,w as a,f as A,F as E}from"./index.6cdd7c8b.js";const T=P({name:"OpenplatformProviderRecordPrdMonthBillManagePage"}),V=P({...T,setup(k){const m=f(null),r=M({form:{},formComps:g,tableColumns:[{prop:"openplatformProviderId",label:"\u4F9B\u5E94\u5546id"},{prop:"year",label:"\u5E74"},{prop:"month",label:"\u6708"},{prop:"totalCall",label:"\u8C03\u7528\u603B\u91CF"},{prop:"totalFeeCall",label:"\u8C03\u7528\u8BA1\u8D39\u603B\u91CF"},{prop:"averageUnitPriceAmount",label:"\u5E73\u5747\u5355\u4EF7\u91D1\u989D"},{prop:"totalFeeAmount",label:"\u603B\u6D88\u8D39\u91D1\u989D\uFF08\u5206\uFF09"},{prop:"remark",label:"\u63CF\u8FF0"}]}),n=f({buttonText:"\u67E5\u8BE2",loading:!1,permission:"admin:web:openplatformProviderRecordPrdMonthBill:pageQuery"}),i=()=>{m.value.refreshData()},b=({pageQuery:e})=>D({...r.form,...e}),_={permission:n.value.permission},B=({row:e,column:u,$index:l})=>l<0?[]:[{txt:"\u7F16\u8F91",text:!0,permission:"admin:web:openplatformProviderRecordPrdMonthBill:update",route:{path:"/admin/OpenplatformProviderRecordPrdMonthBillManageUpdate",query:{id:e.id}}},{txt:"\u5220\u9664",text:!0,permission:"admin:web:openplatformProviderRecordPrdMonthBill:delete",methodConfirmText:`\u786E\u5B9A\u8981\u5220\u9664 ${e.name} \u5417\uFF1F`,method(){return R({id:e.id}).then(p=>(i(),Promise.resolve(p)))}}];return(e,u)=>{const l=t("PtButton"),d=t("PtForm"),c=t("PtButtonGroup"),p=t("el-table-column"),F=t("PtTable"),v=t("PtRouteViewPopover");return w(),x(E,null,[o(d,{form:r.form,method:i,defaultButtonsShow:"submit,reset",submitAttrs:n.value,inline:"",comps:r.formComps},{buttons:a(()=>[o(l,{permission:"admin:web:openplatformProviderRecordPrdMonthBill:create",route:"/admin/OpenplatformProviderRecordPrdMonthBillManageAdd"},{default:a(()=>[A("\u6DFB\u52A0")]),_:1})]),_:1},8,["form","submitAttrs","comps"]),o(F,{ref_key:"tableRef",ref:m,"default-expand-all":"",dataMethod:b,onDataMethodDataLoading:u[0]||(u[0]=s=>n.value.loading=s),paginationProps:_,columns:r.tableColumns},{defaultAppend:a(()=>[o(p,{label:"\u64CD\u4F5C",width:"180"},{default:a(({row:s,column:h,$index:C})=>[o(c,{options:B({row:s,column:h,$index:C})},null,8,["options"])]),_:1})]),_:1},8,["columns"]),o(v,{level:3})],64)}}});export{V as default};