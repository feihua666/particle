import{p as D,r as A}from"./lowcodeSegmentTemplateAdminApi.9a1138bc.js";import{p as v}from"./lowcodeSegmentTemplateManage.14c0c7e7.js";import{d as f,r as w,a as x,b as o,o as P,c as C,e as a,w as n,f as S,F as M}from"./index.858f5c96.js";import"./treeQueryComps.5854cd36.js";const y=f({name:"LowcodeSegmentTemplateManagePage"}),k=f({...y,setup(R){const d=w(null),u=x({form:{},formComps:v,tableColumns:[{label:"\u6A21\u677F\u540D\u79F0",prop:"name",showOverflowTooltip:!0,width:250},{label:"\u7F16\u7801",prop:"code",showOverflowTooltip:!0},{label:"\u8F93\u51FA\u7C7B\u578B",prop:"outputTypeDictName"},{label:"\u7236\u7EA7",prop:"parentName"},{label:"\u540D\u79F0\u6A21\u677F",prop:"nameTemplate",showOverflowTooltip:!0},{label:"\u540D\u79F0\u8F93\u51FA\u53D8\u91CF\u540D",prop:"nameOutputVariable",showOverflowTooltip:!0},{label:"\u5185\u5BB9\u8F93\u51FA\u53D8\u91CF\u540D",prop:"outputVariable",showOverflowTooltip:!0},{label:"\u5F15\u7528\u6A21\u677F",prop:"referenceSegmentTemplateName"},{label:"\u5171\u4EAB\u53D8\u91CF\u540D",prop:"shareVariables"},{label:"\u63CF\u8FF0",prop:"remark",showOverflowTooltip:!0}]}),r=w({buttonText:"\u67E5\u8BE2",loading:!1,permission:"admin:web:lowcodeSegmentTemplate:pageQuery"}),c=()=>{d.value.refreshData()},g=({pageQuery:e})=>D({...u.form,...e}),T={permission:r.value.permission},F=({row:e,column:l,$index:m})=>{if(m<0)return[];let t={id:e.id},p={id:e.id,parentId:e.parentId};return[{txt:"\u7F16\u8F91",text:!0,permission:"admin:web:lowcodeSegmentTemplate:update",route:{path:"/admin/lowcodeSegmentTemplateManageUpdate",query:t}},{txt:"\u5220\u9664",text:!0,permission:"admin:web:lowcodeSegmentTemplate:delete",methodConfirmText:`\u786E\u5B9A\u8981\u5220\u9664 ${e.name} \u5417\uFF1F`,method(){return A(t).then(s=>(c(),Promise.resolve(s)))}},{txt:"\u6E32\u67D3\u6D4B\u8BD5",position:"more",text:!0,permission:"admin:web:lowcodeSegmentTemplate:renderTest",route:{path:"/admin/lowcodeSegmentTemplateManageRenderTest",query:t}},{txt:"\u6DFB\u52A0\u5B50\u7EA7",position:"more",text:!0,permission:"admin:web:lowcodeSegmentTemplate:create",route:{path:"/admin/lowcodeSegmentTemplateManageAdd",query:t}},{txt:"\u590D\u5236\u8282\u70B9",text:!0,position:"more",permission:"admin:web:lowcodeSegmentTemplate:copy",route:{path:"/admin/lowcodeSegmentTemplateManageCopy",query:p}}]};return(e,l)=>{const m=o("PtButton"),t=o("PtForm"),p=o("PtButtonGroup"),b=o("el-table-column"),s=o("PtTable"),_=o("PtRouteViewPopover");return P(),C(M,null,[a(t,{form:u.form,method:c,defaultButtonsShow:"submit,reset",submitAttrs:r.value,inline:"",comps:u.formComps},{buttons:n(()=>[a(m,{permission:"admin:web:lowcodeSegmentTemplate:create",route:"/admin/lowcodeSegmentTemplateManageAdd"},{default:n(()=>[S("\u6DFB\u52A0")]),_:1})]),_:1},8,["form","submitAttrs","comps"]),a(s,{ref_key:"tableRef",ref:d,"default-expand-all":"",dataMethod:g,onDataMethodDataLoading:l[0]||(l[0]=i=>r.value.loading=i),dataMethodResultHandleConvertToTree:!0,paginationProps:T,columns:u.tableColumns},{defaultAppend:n(()=>[a(b,{label:"\u64CD\u4F5C",width:"220"},{default:n(({row:i,column:B,$index:h})=>[a(p,{options:F({row:i,column:B,$index:h}),dropdownTriggerButtonOptions:{text:!0,buttonText:"\u66F4\u591A"}},null,8,["options"])]),_:1})]),_:1},8,["columns"]),a(_,{level:3})],64)}}});export{k as default};