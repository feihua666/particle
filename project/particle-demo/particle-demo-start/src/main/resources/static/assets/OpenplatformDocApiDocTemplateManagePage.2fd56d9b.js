import{p as w,r as A}from"./openplatformDocApiDocTemplateAdminApi.7a93fa5f.js";import{p as h}from"./openplatformDocApiDocTemplateManage.daeaf935.js";import{d as D,r as f,a as v,b as t,o as g,c as x,e as o,w as a,f as y,F as M}from"./index.6cdd7c8b.js";const N=D({name:"OpenplatformDocApiDocTemplateManagePage"}),V=D({...N,setup(O){const m=f(null),u=v({form:{},formComps:h,tableColumns:[{prop:"name",label:"\u6A21\u677F\u540D\u79F0"},{prop:"requestUrlPrefix",label:"\u8BF7\u6C42\u5730\u5740\u524D\u7F00",showOverflowTooltip:!0},{prop:"requestUrlIntranetPrefix",label:"\u5185\u7F51\u8BF7\u6C42\u5730\u5740\u524D\u7F00",showOverflowTooltip:!0},{prop:"requestTypeDictName",label:"\u8BF7\u6C42\u7C7B\u578B"},{prop:"requestBodyTypeDictName",label:"\u8BF7\u6C42\u4F53\u7C7B\u578B"},{prop:"requestParamTypeDictName",label:"\u8BF7\u6C42\u53C2\u6570\u7C7B\u578B"},{prop:"requestParamNestTypeDictName",label:"\u8BF7\u6C42\u53C2\u6570\u5D4C\u5957\u7C7B\u578B"},{prop:"responseBodyTypeDictName",label:"\u54CD\u5E94\u4F53\u7C7B\u578B"},{prop:"responseMaxDuration",label:"\u6700\u5927\u54CD\u5E94\u65F6\u957F(ms)"},{prop:"responseMaxDurationDesc",label:"\u54CD\u5E94\u65F6\u957F\u6587\u672C",showOverflowTooltip:!0},{prop:"authenticationType",label:"\u8BA4\u8BC1\u65B9\u5F0F",showOverflowTooltip:!0}]}),r=f({buttonText:"\u67E5\u8BE2",loading:!1,permission:"admin:web:openplatformDocApiDocTemplate:pageQuery"}),i=()=>{m.value.refreshData()},b=({pageQuery:e})=>w({...u.form,...e}),B={permission:r.value.permission},F=({row:e,column:n,$index:p})=>p<0?[]:[{txt:"\u7F16\u8F91",text:!0,permission:"admin:web:openplatformDocApiDocTemplate:update",route:{path:"/admin/OpenplatformDocApiDocTemplateManageUpdate",query:{id:e.id}}},{txt:"\u5220\u9664",text:!0,permission:"admin:web:openplatformDocApiDocTemplate:delete",methodConfirmText:`\u786E\u5B9A\u8981\u5220\u9664 ${e.name} \u5417\uFF1F`,method(){return A({id:e.id}).then(l=>(i(),Promise.resolve(l)))}}];return(e,n)=>{const p=t("PtButton"),c=t("PtForm"),d=t("PtButtonGroup"),l=t("el-table-column"),C=t("PtTable"),_=t("PtRouteViewPopover");return g(),x(M,null,[o(c,{form:u.form,method:i,defaultButtonsShow:"submit,reset",submitAttrs:r.value,inline:"",comps:u.formComps},{buttons:a(()=>[o(p,{permission:"admin:web:openplatformDocApiDocTemplate:create",route:"/admin/OpenplatformDocApiDocTemplateManageAdd"},{default:a(()=>[y("\u6DFB\u52A0")]),_:1})]),_:1},8,["form","submitAttrs","comps"]),o(C,{ref_key:"tableRef",ref:m,"default-expand-all":"",dataMethod:b,onDataMethodDataLoading:n[0]||(n[0]=s=>r.value.loading=s),paginationProps:B,columns:u.tableColumns},{defaultAppend:a(()=>[o(l,{label:"\u64CD\u4F5C",width:"180"},{default:a(({row:s,column:T,$index:P})=>[o(d,{options:F({row:s,column:T,$index:P})},null,8,["options"])]),_:1})]),_:1},8,["columns"]),o(_,{level:3})],64)}}});export{V as default};