import{p as R,a as g,r as C,b as P}from"./reportReportApiManage.9af25d43.js";import{d as b,r as f,a as T,b as o,o as x,c as E,e as u,w as p,f as D,F as O}from"./index.d93ffe12.js";import"./reportSegmentTemplateCompItem.78d83033.js";import"./reportSegmentTemplateAdminApi.aa1ac20f.js";import"./treeQueryComps.5854cd36.js";const M=b({name:"ReportReportApiManagePage"}),I=b({...M,setup(k){const d=f(null),a=T({form:{},formComps:R,tableColumns:[{prop:"name",label:"\u540D\u79F0",showOverflowTooltip:!0},{prop:"code",label:"\u7F16\u7801",showOverflowTooltip:!0},{prop:"isGroup",label:"\u5206\u7EC4/\u63A5\u53E3",formatter:(e,n,r,s)=>r?"\u5206\u7EC4":"\u63A5\u53E3"},{prop:"permissions",label:"\u63A5\u53E3\u6743\u9650\u7801",showOverflowTooltip:!0},{prop:"url",label:"\u63A5\u53E3\u5730\u5740",showOverflowTooltip:!0},{prop:"reportSegmentTemplateName",label:"\u62A5\u544A\u7247\u6BB5\u6A21\u677F",showOverflowTooltip:!0},{prop:"inParamExampleConfigJson",label:"\u5165\u53C2\u793A\u4F8B",showOverflowTooltip:!0},{label:"\u7236\u7EA7",prop:"parentName",showOverflowTooltip:!0},{prop:"remark",label:"\u63CF\u8FF0",showOverflowTooltip:!0}]}),l=f({buttonText:"\u67E5\u8BE2",loading:!1,permission:"admin:web:reportReportApi:pageQuery"}),c=()=>{d.value.refreshData()},A=({pageQuery:e})=>g({...a.form,...e}),w={permission:l.value.permission},h=({row:e,column:n,$index:r})=>r<0?[]:[{txt:"\u7F16\u8F91",text:!0,permission:"admin:web:reportReportApi:update",route:{path:"/admin/ReportReportApiManageUpdate",query:{id:e.id}}},{txt:"\u5220\u9664",text:!0,position:"more",permission:"admin:web:reportReportApi:delete",methodConfirmText:`\u786E\u5B9A\u8981\u5220\u9664 ${e.name} \u5417\uFF1F`,method(){return C({id:e.id}).then(t=>(c(),Promise.resolve(t)))}},{txt:"\u5237\u65B0\u7F13\u5B58",text:!0,position:"more",permission:"admin:web:reportReportApi:refreshCache",methodSuccess:t=>"\u5237\u65B0\u7F13\u5B58\u6210\u529F,\u5982\u679C\u90E8\u7F72\u591A\u4E2A\u5B9E\u4F8B\u53EF\u80FD\u8981\u591A\u6B21\u6267\u884C\u3002 "+t.data.data,method(){return P({url:e.url,isIncludeSegmentTemplate:!0}).then(t=>Promise.resolve(t))}}];return(e,n)=>{const r=o("PtButton"),s=o("PtForm"),i=o("PtButtonGroup"),t=o("el-table-column"),F=o("PtTable"),_=o("PtRouteViewPopover");return x(),E(O,null,[u(s,{form:a.form,method:c,labelWidth:"100",defaultButtonsShow:"submit,reset",submitAttrs:l.value,inline:"",comps:a.formComps},{buttons:p(()=>[u(r,{permission:"admin:web:reportReportApi:create",route:"/admin/ReportReportApiManageAdd"},{default:p(()=>[D("\u6DFB\u52A0")]),_:1})]),_:1},8,["form","submitAttrs","comps"]),u(F,{ref_key:"tableRef",ref:d,"default-expand-all":"",dataMethod:A,onDataMethodDataLoading:n[0]||(n[0]=m=>l.value.loading=m),dataMethodResultHandleConvertToTree:!0,paginationProps:w,columns:a.tableColumns},{defaultAppend:p(()=>[u(t,{label:"\u64CD\u4F5C",width:"180"},{default:p(({row:m,column:B,$index:v})=>[u(i,{options:h({row:m,column:B,$index:v}),dropdownTriggerButtonOptions:{text:!0,buttonText:"\u66F4\u591A"}},null,8,["options"])]),_:1})]),_:1},8,["columns"]),u(_,{level:3})],64)}}});export{I as default};