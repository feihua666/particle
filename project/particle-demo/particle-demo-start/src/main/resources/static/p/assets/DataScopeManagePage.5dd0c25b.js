import{d as F,r as f,a as w,b as a,o as x,c as P,e as d,f as n,w as s,g as h,F as v,_ as S}from"./index.49d89bd8.js";import{p as T,r as M}from"./dataScopeAdminApi.1fd2a536.js";import{p as R}from"./dataScopeManage.af919ae9.js";import"./dataconstraintCompItem.21dee9a6.js";import"./dataObjectAdminApi.ad460683.js";const y=F({name:"DataScopeManagePage"}),O=F({...y,setup(N){const c=f(null),r=w({form:{},formComps:R,tableColumns:[{prop:"code",label:"\u7F16\u7801"},{prop:"name",label:"\u540D\u79F0"},{prop:"dataObjectName",label:"\u6570\u636E\u5BF9\u8C61"},{prop:"constraintContentTypeDictName",label:"\u7EA6\u675F\u6761\u4EF6\u5185\u5BB9\u7C7B\u578B",showOverflowTooltip:!0},{prop:"constraintContent",label:"\u7EA6\u675F\u6761\u4EF6\u5185\u5BB9",showOverflowTooltip:!0},{prop:"isCustom",label:"\u81EA\u5B9A\u4E49\u6570\u636E",formatter:(t,o,e,u)=>e?"\u662F":"\u5426"},{prop:"isForDelete",label:"\u7528\u4E8E\u5220\u9664",formatter:(t,o,e,u)=>e?"\u662F":"\u5426"},{prop:"isForUpdate",label:"\u7528\u4E8E\u4FEE\u6539",formatter:(t,o,e,u)=>e?"\u662F":"\u5426"},{prop:"isForQuery",label:"\u7528\u4E8E\u67E5\u8BE2",formatter:(t,o,e,u)=>e?"\u662F":"\u5426"},{prop:"isForOther",label:"\u7528\u4E8E\u5176\u5B83",formatter:(t,o,e,u)=>e?"\u662F":"\u5426"},{prop:"remark",label:"\u63CF\u8FF0",showOverflowTooltip:!0}]}),p=f({buttonText:"\u67E5\u8BE2",loading:!1,permission:"admin:web:dataScope:pageQuery"}),b=()=>{c.value.refreshData()},_=({pageQuery:t})=>T({...r.form,...t}),B={permission:p.value.permission},D=({row:t,column:o,$index:e})=>{if(e<0)return[];let u={id:t.id},m={dataScopeId:t.id,dataScopeName:t.name,dataObjectId:t.dataObjectId};return[{txt:"\u7F16\u8F91",text:!0,permission:"admin:web:dataScope:update",route:{path:"/admin/DataScopeManageUpdate",query:u}},{txt:"\u5220\u9664",text:!0,permission:"admin:web:dataScope:delete",methodConfirmText:`\u786E\u5B9A\u8981\u5220\u9664 ${t.name} \u5417\uFF1F`,method(){return M({id:t.id}).then(l=>(b(),Promise.resolve(l)))}},{txt:"\u6570\u636E\u8303\u56F4\u5206\u914D\u81EA\u5B9A\u4E49\u6570\u636E",position:"more",disabled:!t.isCustom,disabledReason:t.isCustom?void 0:"\u53EA\u6709\u81EA\u5B9A\u4E49\u6570\u636E\u8303\u56F4\u624D\u80FD\u5206\u914D\u81EA\u5B9A\u4E49\u6570\u636E",text:!0,permission:"admin:web:dataScopeCustomDataRel:dataScopeAssignCustomData",route:{path:"/admin/dataScopeManageDataScopeAssignCustomData",query:m}}]};return(t,o)=>{const e=a("PtButton"),u=a("PtForm"),m=a("PtButtonGroup"),E=a("el-table-column"),l=a("PtTable"),g=a("PtRouteViewPopover");return x(),P(v,null,[d(" \u67E5\u8BE2\u8868\u5355 "),n(u,{form:r.form,method:b,defaultButtonsShow:"submit,reset",submitAttrs:p.value,inline:"",comps:r.formComps},{buttons:s(()=>[n(e,{permission:"admin:web:dataScope:create",route:"/admin/DataScopeManageAdd"},{default:s(()=>[h("\u6DFB\u52A0")]),_:1})]),_:1},8,["form","submitAttrs","comps"]),d(" \u6307\u5B9A dataMethod\uFF0C\u9ED8\u8BA4\u52A0\u8F7D\u6570\u636E "),n(l,{ref_key:"tableRef",ref:c,"default-expand-all":"",dataMethod:_,onDataMethodDataLoading:o[0]||(o[0]=i=>p.value.loading=i),paginationProps:B,columns:r.tableColumns},{defaultAppend:s(()=>[n(E,{label:"\u64CD\u4F5C",width:"210"},{default:s(({row:i,column:A,$index:C})=>[n(m,{options:D({row:i,column:A,$index:C}),dropdownTriggerButtonOptions:{text:!0,buttonText:"\u66F4\u591A"}},null,8,["options"])]),_:1})]),_:1},8,["columns"]),d(" \u5B50\u7EA7\u8DEF\u7531 "),n(g,{level:3})],64)}}}),U=S(O,[["__file","/Users/yw/fh/git-source/particle/web/component/pc/dataconstraint/views/admin/DataScopeManagePage.vue"]]);export{U as default};