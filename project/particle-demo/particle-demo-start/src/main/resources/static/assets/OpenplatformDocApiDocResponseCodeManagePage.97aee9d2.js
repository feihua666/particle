import{p as h,a as P,r as g}from"./openplatformDocApiDocResponseCodeManage.fe903775.js";import{d as b,r as f,a as v,b as t,o as R,c as x,e as u,w as r,f as E,F as T}from"./index.9463e69a.js";import"./openplatformDocCompItem.67bb1589.js";import"./openplatformDocDirNameAdminApi.1ee04e10.js";import"./openplatformDocApiDocTemplateAdminApi.5b37d10e.js";const O=b({name:"OpenplatformDocApiDocResponseCodeManagePage"}),N=b({...O,setup(M){const i=f(null),a=v({form:{},formComps:h,tableColumns:[{prop:"code",label:"\u7F16\u7801"},{prop:"httpCode",label:"http\u72B6\u6001\u7801"},{prop:"isCharge",label:"\u662F\u5426\u8BA1\u8D39",formatter:(o,n,e,p)=>e?"\u662F":"\u5426"},{prop:"explanation",label:"\u5B57\u6BB5\u8BF4\u660E"},{prop:"openplatformDocApiName",label:"\u7ED1\u5B9A\u63A5\u53E3",showOverflowTooltip:!0},{prop:"openplatformDocApiCode",label:"\u7ED1\u5B9A\u63A5\u53E3\u7F16\u7801",showOverflowTooltip:!0},{prop:"openplatformDocApiDocRequestUrl",label:"\u6587\u6863\u5185\u5BB9",showOverflowTooltip:!0},{prop:"isGlobal",label:"\u662F\u5426\u5168\u5C40\u901A\u7528\u7801",formatter:(o,n,e,p)=>e?"\u662F":"\u5426"},{prop:"seq",label:"\u6392\u5E8F"},{prop:"remark",label:"\u63CF\u8FF0",showOverflowTooltip:!0}]}),l=f({buttonText:"\u67E5\u8BE2",loading:!1,permission:"admin:web:openplatformDocApiDocResponseCode:pageQuery"}),c=()=>{i.value.refreshData()},D=({pageQuery:o})=>P({...a.form,...o}),_={permission:l.value.permission},A=({row:o,column:n,$index:e})=>e<0?[]:[{txt:"\u7F16\u8F91",text:!0,permission:"admin:web:openplatformDocApiDocResponseCode:update",route:{path:"/admin/OpenplatformDocApiDocResponseCodeManageUpdate",query:{id:o.id}}},{txt:"\u5220\u9664",text:!0,permission:"admin:web:openplatformDocApiDocResponseCode:delete",methodConfirmText:`\u786E\u5B9A\u8981\u5220\u9664 ${o.code} \u5417\uFF1F`,method(){return g({id:o.id}).then(s=>(c(),Promise.resolve(s)))}}];return(o,n)=>{const e=t("PtButton"),p=t("PtForm"),d=t("PtButtonGroup"),s=t("el-table-column"),B=t("PtTable"),C=t("PtRouteViewPopover");return R(),x(T,null,[u(p,{form:a.form,method:c,defaultButtonsShow:"submit,reset",submitAttrs:l.value,inline:"",comps:a.formComps},{buttons:r(()=>[u(e,{permission:"admin:web:openplatformDocApiDocResponseCode:create",route:"/admin/OpenplatformDocApiDocResponseCodeManageAdd"},{default:r(()=>[E("\u6DFB\u52A0")]),_:1})]),_:1},8,["form","submitAttrs","comps"]),u(B,{ref_key:"tableRef",ref:i,"default-expand-all":"",dataMethod:D,onDataMethodDataLoading:n[0]||(n[0]=m=>l.value.loading=m),paginationProps:_,columns:a.tableColumns},{defaultAppend:r(()=>[u(s,{label:"\u64CD\u4F5C",width:"180"},{default:r(({row:m,column:F,$index:w})=>[u(d,{options:A({row:m,column:F,$index:w})},null,8,["options"])]),_:1})]),_:1},8,["columns"]),u(C,{level:3})],64)}}});export{N as default};