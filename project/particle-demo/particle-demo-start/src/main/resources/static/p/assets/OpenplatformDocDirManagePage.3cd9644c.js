import{d as b,r as D,a as h,b as t,o as E,c as C,e as s,f as o,w as n,g as A,F as x,_ as M}from"./index.3ad8a272.js";import{p as T,r as R}from"./openplatformDocCompItem.87abe25b.js";import{p as O}from"./openplatformDocDirManage.5e6af035.js";import"./openplatformDocDirNameAdminApi.a3f0367b.js";import"./openplatformDocApiAdminApi.5762ebfa.js";import"./openplatformDocApiDocTemplateAdminApi.f24d2324.js";import"./treeQueryComps.5854cd36.js";const N=b({name:"OpenplatformDocDirManagePage"}),V=b({...N,setup(k){const i=D(null),a=h({form:{},formComps:O,tableColumns:[{prop:"name",label:"\u540D\u79F0"},{prop:"nameSimple",label:"\u7B80\u79F0"},{prop:"openplatformDocDirNameName",label:"\u76EE\u5F55\u540D\u79F0"},{prop:"seq",label:"\u6392\u5E8F"},{prop:"parentName",label:"\u7236\u7EA7"},{prop:"remark",label:"\u5907\u6CE8",showOverflowTooltip:!0}]}),r=D({buttonText:"\u67E5\u8BE2",loading:!1,permission:"admin:web:openplatformDocDir:pageQuery"}),c=()=>{i.value.refreshData()},_=({pageQuery:e})=>T({...a.form,...e}),g={permission:r.value.permission},F=({row:e,column:u,$index:p})=>p<0?[]:[{txt:"\u7F16\u8F91",text:!0,permission:"admin:web:openplatformDocDir:update",route:{path:"/admin/OpenplatformDocDirManageUpdate",query:{id:e.id}}},{txt:"\u5220\u9664",text:!0,permission:"admin:web:openplatformDocDir:delete",methodConfirmText:`\u786E\u5B9A\u8981\u5220\u9664 ${e.name} \u5417\uFF1F`,method(){return R({id:e.id}).then(m=>(c(),Promise.resolve(m)))}}];return(e,u)=>{const p=t("PtButton"),d=t("PtForm"),f=t("PtButtonGroup"),m=t("el-table-column"),P=t("PtTable"),B=t("PtRouteViewPopover");return E(),C(x,null,[s(" \u67E5\u8BE2\u8868\u5355 "),o(d,{form:a.form,method:c,defaultButtonsShow:"submit,reset",submitAttrs:r.value,inline:"",comps:a.formComps},{buttons:n(()=>[o(p,{permission:"admin:web:openplatformDocDir:create",route:"/admin/OpenplatformDocDirManageAdd"},{default:n(()=>[A("\u6DFB\u52A0")]),_:1})]),_:1},8,["form","submitAttrs","comps"]),s(" \u6307\u5B9A dataMethod\uFF0C\u9ED8\u8BA4\u52A0\u8F7D\u6570\u636E "),o(P,{ref_key:"tableRef",ref:i,"default-expand-all":"",dataMethod:_,onDataMethodDataLoading:u[0]||(u[0]=l=>r.value.loading=l),dataMethodResultHandleConvertToTree:!0,paginationProps:g,columns:a.tableColumns},{defaultAppend:n(()=>[o(m,{label:"\u64CD\u4F5C",width:"180"},{default:n(({row:l,column:v,$index:w})=>[o(f,{options:F({row:l,column:v,$index:w})},null,8,["options"])]),_:1})]),_:1},8,["columns"]),s(" \u5B50\u7EA7\u8DEF\u7531 "),o(B,{level:3})],64)}}}),L=M(V,[["__file","/Users/yw/fh/git-source/particle/web/component/pc/openplatform/views/doc/admin/OpenplatformDocDirManagePage.vue"]]);export{L as default};