import{d as B,r as b,a as g,b as o,o as C,c as P,e as s,f as t,w as p,g as h,F as R,_ as v}from"./index.49d89bd8.js";import{p as E,r as x}from"./openplatformOpenapiRecordAppMonthBillAdminApi.d7d116ed.js";import{p as T}from"./openplatformOpenapiRecordAppMonthBillManage.35dc66a1.js";import"./openplatformAppCompItem.6545e816.js";import"./openplatformAppAdminApi.edd9306c.js";import"./oauth2authorizationRegisteredClientCompItem.5ebf5298.js";import"./oauth2RegisteredClientAdminApi.a64c7859.js";import"./crmCompItem.a6ee91d7.js";import"./crmCustomerTagAdminApi.245e486d.js";const N=B({name:"OpenplatformOpenapiRecordAppMonthBillManagePage"}),y=B({...N,setup(S){const m=b(null),a=g({form:{},formComps:T,tableColumns:[{prop:"openplatformAppName",label:"\u5E94\u7528\u540D\u79F0",showOverflowTooltip:!0},{prop:"appId",label:"appId",showOverflowTooltip:!0},{prop:"customerName",label:"\u5BA2\u6237\u540D\u79F0",showOverflowTooltip:!0},{prop:"year",label:"\u5E74"},{prop:"month",label:"\u6708"},{prop:"totalCall",label:"\u8C03\u7528\u603B\u91CF"},{prop:"totalFeeCall",label:"\u8C03\u7528\u8BA1\u8D39\u603B\u91CF"},{prop:"totalFeeAmount",label:"\u603B\u6D88\u8D39\u91D1\u989D\uFF08\u5206\uFF09"},{prop:"statusDictName",label:"\u8D26\u5355\u72B6\u6001"},{prop:"remark",label:"\u63CF\u8FF0",showOverflowTooltip:!0}]}),u=b({buttonText:"\u67E5\u8BE2",loading:!1,permission:"admin:web:openplatformOpenapiRecordAppMonthBill:pageQuery"}),c=()=>{m.value.refreshData()},_=({pageQuery:e})=>E({...a.form,...e}),F={permission:u.value.permission},A=({row:e,column:l,$index:n})=>n<0?[]:[{txt:"\u7F16\u8F91",text:!0,permission:"admin:web:openplatformOpenapiRecordAppMonthBill:update",route:{path:"/admin/OpenplatformOpenapiRecordAppMonthBillManageUpdate",query:{id:e.id}}},{txt:"\u5220\u9664",text:!0,permission:"admin:web:openplatformOpenapiRecordAppMonthBill:delete",methodConfirmText:`\u786E\u5B9A\u8981\u5220\u9664 ${e.name} \u5417\uFF1F`,method(){return x({id:e.id}).then(r=>(c(),Promise.resolve(r)))}}];return(e,l)=>{const n=o("PtButton"),d=o("PtForm"),f=o("PtButtonGroup"),r=o("el-table-column"),M=o("PtTable"),w=o("PtRouteViewPopover");return C(),P(R,null,[s(" \u67E5\u8BE2\u8868\u5355 "),t(d,{form:a.form,method:c,defaultButtonsShow:"submit,reset",submitAttrs:u.value,inline:"",comps:a.formComps},{buttons:p(()=>[t(n,{permission:"admin:web:openplatformOpenapiRecordAppMonthBill:lastMonthStatistic",route:"/admin/openplatformOpenapiRecordAppMonthBillLastMonthStatistic"},{default:p(()=>[h("\u7EDF\u8BA1\u4E0A\u6708\u6570\u636E")]),_:1}),t(n,{permission:"admin:web:openplatformOpenapiRecordAppMonthBill:thisMonthStatistic",route:"/admin/openplatformOpenapiRecordAppMonthBillThisMonthStatistic"},{default:p(()=>[h("\u7EDF\u8BA1\u672C\u6708\u6570\u636E")]),_:1})]),_:1},8,["form","submitAttrs","comps"]),s(" \u6307\u5B9A dataMethod\uFF0C\u9ED8\u8BA4\u52A0\u8F7D\u6570\u636E "),t(M,{ref_key:"tableRef",ref:m,"default-expand-all":"",dataMethod:_,onDataMethodDataLoading:l[0]||(l[0]=i=>u.value.loading=i),paginationProps:F,columns:a.tableColumns},{defaultAppend:p(()=>[t(r,{label:"\u64CD\u4F5C",width:"180"},{default:p(({row:i,column:D,$index:O})=>[t(f,{options:A({row:i,column:D,$index:O})},null,8,["options"])]),_:1})]),_:1},8,["columns"]),s(" \u5B50\u7EA7\u8DEF\u7531 "),t(w,{level:3})],64)}}}),j=v(y,[["__file","/Users/yw/fh/git-source/particle/web/component/pc/openplatform/views/bill/admin/OpenplatformOpenapiRecordAppMonthBillManagePage.vue"]]);export{j as default};