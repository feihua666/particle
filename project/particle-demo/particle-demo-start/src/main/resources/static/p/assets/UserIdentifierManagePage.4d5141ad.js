import{d as B,t as h,r as C,a as I,A as v,b as t,o as M,c as y,e as p,f as u,w as r,g as T,F as R,B as U,C as V,_ as k}from"./index.3ad8a272.js";import{b as q}from"./userIdentifierManage.cd59be3b.js";const N=B({name:"UserIdentifierManagePage"}),Q=B({...N,props:{...h},setup(b){const _=b,f=C(null),n=I({form:{},formComps:q({props:_}),tableColumns:v}),s=C({buttonText:"\u67E5\u8BE2",loading:!1,permission:"admin:web:userIdentifier:pageQuery"}),c=()=>{f.value.refreshData()},P=({pageQuery:e})=>U({...n.form,...e}),g={permission:s.value.permission},D=({row:e,column:i,$index:a})=>{if(a<0)return[];let d={id:e.id},o={identifierId:e.id,identifier:e.identifier};return[{txt:"\u7F16\u8F91",text:!0,permission:"admin:web:userIdentifier:update",route:{path:"/admin/userIdentifierManageUpdate",query:o}},{txt:"\u5220\u9664",text:!0,permission:"admin:web:userIdentifier:delete",methodConfirmText:`\u5220\u9664\u7528\u6237\u8D26\u53F7\u4F1A\u9020\u6210\u7528\u6237\u5F53\u524D\u8D26\u53F7\u4E0D\u80FD\u767B\u5F55\uFF0C\u540C\u65F6\u4F1A\u5220\u9664\u5F53\u524D\u8D26\u53F7\u7684\u5BC6\u7801\u6570\u636E\u3002\u786E\u5B9A\u8981\u5220\u9664 ${e.identifier} \u5417\uFF1F`,method(){return V(d).then(m=>(c(),Promise.resolve(m)))}},{txt:"\u91CD\u7F6E\u5BC6\u7801",text:!0,permission:"admin:web:userIdentifierPwd:identifier:resetPassword",position:"more",methodConfirmText:"\u6B64\u64CD\u4F5C\u53EA\u5F71\u54CD\u5F53\u524D\u767B\u5F55\u6807\u8BC6\u5BC6\u7801\uFF0C\u5982\u679C\u7528\u6237\u8FD8\u6709\u5176\u5B83\u767B\u5F55\u6807\u8BC6\uFF08\u5176\u5BC6\u7801\u4E0D\u53D7\u54CD\u5E94\uFF09\uFF0C\u786E\u5B9A\u8981\u91CD\u7F6E\u5BC6\u7801\u5417\uFF1F",route:{path:"/admin/userIdentifierPwdManageResetPassword",query:o}},{txt:"\u7528\u6237\u5BC6\u7801",text:!0,permission:"admin:web:userIdentifierPwd:pageQuery",position:"more",icon:"View",type:"primary",route:{path:"/admin/userIdentifierPwdManagePage",query:o}}]},w={text:!0,buttonText:"\u66F4\u591A"};return(e,i)=>{const a=t("PtButton"),d=t("PtForm"),o=t("PtButtonGroup"),F=t("el-table-column"),m=t("PtTable"),x=t("PtRouteViewPopover");return M(),y(R,null,[p(" \u67E5\u8BE2\u8868\u5355 "),u(d,{form:n.form,method:c,defaultButtonsShow:"submit,reset",submitAttrs:s.value,inline:"",labelWidth:"80",comps:n.formComps},{buttons:r(()=>[u(a,{permission:"admin:web:userIdentifier:create",route:"/admin/userIdentifierManageAdd"},{default:r(()=>[T("\u6DFB\u52A0")]),_:1})]),_:1},8,["form","submitAttrs","comps"]),p(" \u6307\u5B9A dataMethod\uFF0C\u9ED8\u8BA4\u52A0\u8F7D\u6570\u636E "),u(m,{ref_key:"tableRef",ref:f,"default-expand-all":"",dataMethod:P,onDataMethodDataLoading:i[0]||(i[0]=l=>s.value.loading=l),paginationProps:g,columns:n.tableColumns},{defaultAppend:r(()=>[u(F,{label:"\u64CD\u4F5C",width:"220"},{default:r(({row:l,column:A,$index:E})=>[u(o,{options:D({row:l,column:A,$index:E}),dropdownTriggerButtonOptions:w},null,8,["options"])]),_:1})]),_:1},8,["columns"]),p(" \u5B50\u7EA7\u8DEF\u7531 "),u(x,{level:3})],64)}}}),L=k(Q,[["__file","/Users/yw/fh/git-source/particle/web/component/pc/user/views/admin/UserIdentifierManagePage.vue"]]);export{L as default};