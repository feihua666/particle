import{d as B,r as d,a as v,b as o,o as P,c as T,e as i,f as t,w as a,g as E,F as g,_ as O}from"./index.3ad8a272.js";import{e as x,f as y}from"./openplatformDocCompItem.87abe25b.js";import{p as M}from"./openplatformDocApiDocManage.1fa25741.js";import"./openplatformDocDirNameAdminApi.a3f0367b.js";import"./openplatformDocApiAdminApi.5762ebfa.js";import"./openplatformDocApiDocTemplateAdminApi.f24d2324.js";const N=B({name:"OpenplatformDocApiDocManagePage"}),q=B({...N,setup(R){const m=d(null),u=v({form:{},formComps:M,tableColumns:[{prop:"openplatformDocApiName",label:"\u7ED1\u5B9A\u63A5\u53E3",showOverflowTooltip:!0},{prop:"openplatformDocApiCode",label:"\u7ED1\u5B9A\u63A5\u53E3\u7F16\u7801",showOverflowTooltip:!0},{prop:"requestUrlPrefix",label:"\u8BF7\u6C42\u5730\u5740\u524D\u7F00",showOverflowTooltip:!0},{prop:"requestUrlIntranetPrefix",label:"\u5185\u7F51\u8BF7\u6C42\u5730\u5740\u524D\u7F00",showOverflowTooltip:!0},{prop:"requestUrl",label:"\u8BF7\u6C42\u5730\u5740",showOverflowTooltip:!0},{prop:"requestTypeDictName",label:"\u8BF7\u6C42\u7C7B\u578B"},{prop:"requestBodyTypeDictName",label:"\u8BF7\u6C42\u4F53\u7C7B\u578B",showOverflowTooltip:!0},{prop:"requestParamTypeDictName",label:"\u8BF7\u6C42\u53C2\u6570\u7C7B\u578B"},{prop:"requestParamNestTypeDictName",label:"\u8BF7\u6C42\u53C2\u6570\u5D4C\u5957\u7C7B\u578B"},{prop:"responseBodyTypeDictName",label:"\u54CD\u5E94\u4F53\u7C7B\u578B",showOverflowTooltip:!0},{prop:"responseMaxDuration",label:"\u6700\u5927\u54CD\u5E94\u65F6\u957F(ms)"},{prop:"responseMaxDurationDesc",label:"\u54CD\u5E94\u65F6\u957F\u6587\u672C",showOverflowTooltip:!0},{prop:"authenticationType",label:"\u8BA4\u8BC1\u65B9\u5F0F",showOverflowTooltip:!0},{prop:"descriptionDetail",label:"\u8BE6\u7EC6\u63CF\u8FF0",showOverflowTooltip:!0},{prop:"requestParamExample",label:"\u8BF7\u6C42\u53C2\u6570\u793A\u4F8B",showOverflowTooltip:!0},{prop:"responseParamExample",label:"\u54CD\u5E94\u53C2\u6570\u793A\u4F8B",showOverflowTooltip:!0},{prop:"openplatformDocApiDocTemplateName",label:"\u6587\u6863\u5185\u5BB9\u6A21\u677F",showOverflowTooltip:!0}]}),r=d({buttonText:"\u67E5\u8BE2",loading:!1,permission:"admin:web:openplatformDocApiDoc:pageQuery"}),c=()=>{m.value.refreshData()},b=({pageQuery:e})=>x({...u.form,...e}),F={permission:r.value.permission},w=({row:e,column:p,$index:l})=>l<0?[]:[{txt:"\u7F16\u8F91",text:!0,permission:"admin:web:openplatformDocApiDoc:update",route:{path:"/admin/OpenplatformDocApiDocManageUpdate",query:{id:e.id}}},{txt:"\u5220\u9664",text:!0,permission:"admin:web:openplatformDocApiDoc:delete",methodConfirmText:`\u786E\u5B9A\u8981\u5220\u9664 ${e.name} \u5417\uFF1F`,method(){return y({id:e.id}).then(n=>(c(),Promise.resolve(n)))}}];return(e,p)=>{const l=o("PtButton"),f=o("PtForm"),D=o("PtButtonGroup"),n=o("el-table-column"),C=o("PtTable"),A=o("PtRouteViewPopover");return P(),T(g,null,[i(" \u67E5\u8BE2\u8868\u5355 "),t(f,{form:u.form,method:c,defaultButtonsShow:"submit,reset",submitAttrs:r.value,inline:"",comps:u.formComps},{buttons:a(()=>[t(l,{permission:"admin:web:openplatformDocApiDoc:create",route:"/admin/OpenplatformDocApiDocManageAdd"},{default:a(()=>[E("\u6DFB\u52A0")]),_:1})]),_:1},8,["form","submitAttrs","comps"]),i(" \u6307\u5B9A dataMethod\uFF0C\u9ED8\u8BA4\u52A0\u8F7D\u6570\u636E "),t(C,{ref_key:"tableRef",ref:m,"default-expand-all":"",dataMethod:b,onDataMethodDataLoading:p[0]||(p[0]=s=>r.value.loading=s),paginationProps:F,columns:u.tableColumns},{defaultAppend:a(()=>[t(n,{label:"\u64CD\u4F5C",width:"180"},{default:a(({row:s,column:_,$index:h})=>[t(D,{options:w({row:s,column:_,$index:h})},null,8,["options"])]),_:1})]),_:1},8,["columns"]),i(" \u5B50\u7EA7\u8DEF\u7531 "),t(A,{level:3})],64)}}}),Q=O(q,[["__file","/Users/yw/fh/git-source/particle/web/component/pc/openplatform/views/doc/admin/OpenplatformDocApiDocManagePage.vue"]]);export{Q as default};