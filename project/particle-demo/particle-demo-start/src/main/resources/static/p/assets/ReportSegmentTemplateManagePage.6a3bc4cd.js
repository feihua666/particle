import{d as g,r as f,a as v,b as o,o as C,c as D,e as d,f as u,w as r,g as P,F as x,_ as E}from"./index.3ad8a272.js";import{p as S,r as R,a as M}from"./reportSegmentTemplateAdminApi.5375e98f.js";import{p as O}from"./reportSegmentTemplateManage.7768b2c6.js";import"./treeQueryComps.5854cd36.js";import"./reportSegmentTemplateCompItem.83003162.js";const y=g({name:"ReportSegmentTemplateManagePage"}),V=g({...y,setup(N){const c=f(null),a=v({form:{},formComps:O,tableColumns:[{label:"\u6A21\u677F\u540D\u79F0",prop:"name",showOverflowTooltip:!0,width:250},{label:"\u7F16\u7801",prop:"code",showOverflowTooltip:!0},{label:"\u6A21\u677F\u6743\u9650\u7801",prop:"permissions",showOverflowTooltip:!0},{label:"\u8F93\u51FA\u7C7B\u578B",prop:"outputTypeDictName",showOverflowTooltip:!0},{label:"\u7236\u7EA7",prop:"parentName"},{label:"\u8BA1\u7B97\u6A21\u677F",prop:"computeTemplate",showOverflowTooltip:!0},{label:"\u540D\u79F0\u8F93\u51FA\u53D8\u91CF\u540D",prop:"nameOutputVariable",showOverflowTooltip:!0},{label:"\u5185\u5BB9\u8F93\u51FA\u53D8\u91CF\u540D",prop:"outputVariable",showOverflowTooltip:!0},{label:"\u5F15\u7528\u6A21\u677F",prop:"referenceSegmentTemplateName"},{label:"\u5171\u4EAB\u53D8\u91CF\u540D",prop:"shareVariables"},{prop:"seq",label:"\u6392\u5E8F",width:50},{label:"\u63CF\u8FF0",prop:"remark",showOverflowTooltip:!0}]}),n=f({buttonText:"\u67E5\u8BE2",loading:!1,permission:"admin:web:reportSegmentTemplate:pageQuery"}),F=()=>{c.value.refreshData()},T=({pageQuery:e})=>S({...a.form,...e}),h={permission:n.value.permission},B=({row:e,column:p,$index:l})=>{if(l<0)return[];let m={id:e.id},s={id:e.id,parentId:e.parentId};return[{txt:"\u7F16\u8F91",text:!0,permission:"admin:web:reportSegmentTemplate:update",route:{path:"/admin/ReportSegmentTemplateManageUpdate",query:m}},{txt:"\u5220\u9664",text:!0,position:"more",permission:"admin:web:reportSegmentTemplate:delete",methodConfirmText:`\u786E\u5B9A\u8981\u5220\u9664 ${e.name} \u5417\uFF1F`,method(){return R({id:e.id}).then(t=>(F(),Promise.resolve(t)))}},{txt:"\u590D\u5236\u8282\u70B9",text:!0,position:"more",permission:"admin:web:reportSegmentTemplate:copy",route:{path:"/admin/reportSegmentTemplateManageCopy",query:s}},{txt:"\u5237\u65B0\u7F13\u5B58",text:!0,position:"more",permission:"admin:web:reportSegmentTemplate:refreshCache",methodSuccess:t=>"\u5237\u65B0\u7F13\u5B58\u6210\u529F,\u5982\u679C\u90E8\u7F72\u591A\u4E2A\u5B9E\u4F8B\u53EF\u80FD\u8981\u591A\u6B21\u6267\u884C\u3002 "+t.data.data,method(){return M({id:e.id}).then(t=>Promise.resolve(t))}}]};return(e,p)=>{const l=o("PtButton"),m=o("PtForm"),s=o("PtButtonGroup"),b=o("el-table-column"),t=o("PtTable"),w=o("PtRouteViewPopover");return C(),D(x,null,[d(" \u67E5\u8BE2\u8868\u5355 "),u(m,{form:a.form,method:F,defaultButtonsShow:"submit,reset",submitAttrs:n.value,inline:"",comps:a.formComps},{buttons:r(()=>[u(l,{permission:"admin:web:reportSegmentTemplate:create",route:"/admin/ReportSegmentTemplateManageAdd"},{default:r(()=>[P("\u6DFB\u52A0")]),_:1})]),_:1},8,["form","submitAttrs","comps"]),d(" \u6307\u5B9A dataMethod\uFF0C\u9ED8\u8BA4\u52A0\u8F7D\u6570\u636E "),u(t,{ref_key:"tableRef",ref:c,"default-expand-all":"",dataMethod:T,onDataMethodDataLoading:p[0]||(p[0]=i=>n.value.loading=i),dataMethodResultHandleConvertToTree:!0,paginationProps:h,columns:a.tableColumns},{defaultAppend:r(()=>[u(b,{label:"\u64CD\u4F5C",width:"220"},{default:r(({row:i,column:_,$index:A})=>[u(s,{options:B({row:i,column:_,$index:A}),dropdownTriggerButtonOptions:{text:!0,buttonText:"\u66F4\u591A"}},null,8,["options"])]),_:1})]),_:1},8,["columns"]),d(" \u5B50\u7EA7\u8DEF\u7531 "),u(w,{level:3})],64)}}}),H=E(V,[["__file","/Users/yw/fh/git-source/particle/web/component/pc/report/views/template/admin/ReportSegmentTemplateManagePage.vue"]]);export{H as default};