import{p as T,a as E}from"./openplatformOpenapiRecordManage.68fda8d6.js";import{d as c,r as d,a as C,b as p,o as _,c as P,e as l,w as f,F as g}from"./index.858f5c96.js";import"./openplatformAppCompItem.298cf5d8.js";import"./openplatformAppAdminApi.cfd907bd.js";import"./openplatformOpenapiCompItem.0c8bc673.js";const R=c({name:"OpenplatformOpenapiRecordManagePage"}),N=c({...R,setup(D){const m=d(null),a=C({form:{},formComps:T,tableColumns:[{prop:"id",label:"id",showOverflowTooltip:!0},{prop:"openplatformAppName",label:"\u5E94\u7528",showOverflowTooltip:!0},{prop:"appId",label:"appId",showOverflowTooltip:!0},{prop:"userNickname",label:"\u7528\u6237\u6635\u79F0",showOverflowTooltip:!0},{prop:"isApp",label:"\u8C03\u7528\u65B9\u5F0F",formatter:(e,t,o,u)=>o?"\u5E94\u7528":"\u7528\u6237"},{prop:"customerName",label:"\u5BA2\u6237",showOverflowTooltip:!0},{prop:"openplatformOpenapiName",label:"\u5F00\u653E\u63A5\u53E3",showOverflowTooltip:!0},{prop:"requestUrl",label:"\u63A5\u53E3\u5730\u5740",showOverflowTooltip:!0},{prop:"requestTimestamp",label:"\u8BF7\u6C42\u65F6\u95F4\u6233",showOverflowTooltip:!0},{prop:"requestNonce",label:"\u8BF7\u6C42\u6D41\u6C34\u53F7",showOverflowTooltip:!0},{prop:"requestSignature",label:"\u8BF7\u6C42\u7B7E\u540D",showOverflowTooltip:!0},{prop:"requestParameterMd5",label:"\u8BF7\u6C42\u53C2\u6570md5",showOverflowTooltip:!0},{prop:"requestTimestampDateTimeStr",label:"\u8BF7\u6C42\u65F6\u95F4",showOverflowTooltip:!0},{prop:"requestHandleAt",label:"\u8BF7\u6C42\u5904\u7406\u65F6\u95F4",showOverflowTooltip:!0},{prop:"createAt",label:"\u521B\u5EFA\u65F6\u95F4",showOverflowTooltip:!0},{prop:"responseResultMd5",label:"\u54CD\u5E94\u7ED3\u679Cmd5",showOverflowTooltip:!0},{prop:"traceId",label:"traceId",showOverflowTooltip:!0},{prop:"handleDuration",label:"\u5904\u7406\u65F6\u957F(ms)"},{prop:"isResponseHasEffectiveValue",label:"\u54CD\u5E94\u6570\u636E",showOverflowTooltip:!0,formatter:(e,t,o,u)=>o?"\u6B63\u5E38\u54CD\u5E94\u6570\u636E":"\u65E0\u6570\u636E"},{prop:"responseHttpStatus",label:"http\u72B6\u6001\u7801"},{prop:"responseBusinessStatus",label:"\u4E1A\u52A1\u72B6\u6001\u7801",showOverflowTooltip:!0},{prop:"isCacheHit",label:"\u7F13\u5B58\u547D\u4E2D",formatter:(e,t,o,u)=>o?"\u662F":"\u5426"}]}),s=d({buttonText:"\u67E5\u8BE2",loading:!1,permission:"admin:web:openplatformOpenapiRecord:pageQuery"}),w=()=>{m.value.refreshData()},b=({pageQuery:e})=>E({...a.form,...e}),F={permission:s.value.permission},h=({row:e,column:t,$index:o})=>{if(o<0)return[];let u={id:e.id},r={openplatformOpenapiRecordId:e.id},n=[{txt:"\u67E5\u770B\u53C2\u6570",text:!0,permission:"admin:web:openplatformOpenapiRecordParam:detail",route:{path:"/admin/openplatformOpenapiRecordManageParamView",query:u}}];return e.isExistProviderRecord&&n.push({txt:"\u67E5\u770B\u4F9B\u5E94\u5546\u8C03\u7528\u8BB0\u5F55",text:!0,position:"more",permission:"admin:web:openplatformProviderRecord:pageQuery",route:{path:"/admin/openplatformProviderRecordManagePage",query:r}}),n};return(e,t)=>{const o=p("PtForm"),u=p("PtButtonGroup"),r=p("el-table-column"),n=p("PtTable"),v=p("PtRouteViewPopover");return _(),P(g,null,[l(o,{form:a.form,labelWidth:"100",method:w,defaultButtonsShow:"submit,reset",submitAttrs:s.value,inline:"",comps:a.formComps},null,8,["form","submitAttrs","comps"]),l(n,{ref_key:"tableRef",ref:m,"default-expand-all":"",dataMethod:b,onDataMethodDataLoading:t[0]||(t[0]=i=>s.value.loading=i),paginationProps:F,columns:a.tableColumns},{defaultAppend:f(()=>[l(r,{label:"\u64CD\u4F5C",width:"200"},{default:f(({row:i,column:B,$index:O})=>[l(u,{options:h({row:i,column:B,$index:O}),dropdownTriggerButtonOptions:{text:!0,buttonText:"\u66F4\u591A"}},null,8,["options"])]),_:1})]),_:1},8,["columns"]),l(v,{level:3})],64)}}});export{N as default};