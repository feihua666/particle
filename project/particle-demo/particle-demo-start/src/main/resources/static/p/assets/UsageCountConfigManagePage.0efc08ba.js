import{d as C,r as g,a as h,b as t,o as v,c as w,e as m,f as o,w as a,g as x,F as E,_ as M}from"./index.3ad8a272.js";import{b as T,e as R}from"./usageCountCompItem.d69694c1.js";import{p as U}from"./usageCountConfigManage.a9ce80e4.js";const N=C({name:"UsageCountConfigManagePage"}),y=C({...N,setup(V){const p=g(null),u=h({form:{},formComps:U,tableColumns:[{prop:"name",label:"\u540D\u79F0"},{prop:"usageCountDefineName",label:"\u4F7F\u7528\u6B21\u6570\u5B9A\u4E49"},{prop:"limitCount",label:"\u9650\u5236\u7684\u6700\u5927\u4F7F\u7528\u6B21\u6570"},{prop:"limitRuleTypeDictName",label:"\u9650\u5236\u89C4\u5219\u7C7B\u578B"},{prop:"limitPeriodDictName",label:"\u9650\u5236\u5468\u671F"},{prop:"limitTenantName",label:"\u9650\u5236\u79DF\u6237\u540D\u79F0"},{prop:"exceedTip",label:"\u8D85\u51FA\u63D0\u793A\u4FE1\u606F"},{prop:"remark",label:"\u5907\u6CE8"}]}),n=g({buttonText:"\u67E5\u8BE2",loading:!1,permission:"admin:web:usageCountConfig:pageQuery"}),d=()=>{p.value.refreshData()},b=({pageQuery:e})=>T({...u.form,...e}),_={permission:n.value.permission},F=({row:e,column:s,$index:r})=>r<0?[]:[{txt:"\u7F16\u8F91",text:!0,permission:"admin:web:usageCountConfig:update",route:{path:"/admin/UsageCountConfigManageUpdate",query:{id:e.id}}},{txt:"\u5220\u9664",text:!0,permission:"admin:web:usageCountConfig:delete",methodConfirmText:`\u786E\u5B9A\u8981\u5220\u9664 ${e.name} \u5417\uFF1F`,method(){return R({id:e.id}).then(i=>(d(),Promise.resolve(i)))}}];return(e,s)=>{const r=t("PtButton"),c=t("PtForm"),f=t("PtButtonGroup"),i=t("el-table-column"),B=t("PtTable"),P=t("PtRouteViewPopover");return v(),w(E,null,[m(" \u67E5\u8BE2\u8868\u5355 "),o(c,{form:u.form,method:d,labelWidth:"100",defaultButtonsShow:"submit,reset",submitAttrs:n.value,inline:"",comps:u.formComps},{buttons:a(()=>[o(r,{permission:"admin:web:usageCountConfig:create",route:"/admin/UsageCountConfigManageAdd"},{default:a(()=>[x("\u6DFB\u52A0")]),_:1})]),_:1},8,["form","submitAttrs","comps"]),m(" \u6307\u5B9A dataMethod\uFF0C\u9ED8\u8BA4\u52A0\u8F7D\u6570\u636E "),o(B,{ref_key:"tableRef",ref:p,"default-expand-all":"",dataMethod:b,onDataMethodDataLoading:s[0]||(s[0]=l=>n.value.loading=l),paginationProps:_,columns:u.tableColumns},{defaultAppend:a(()=>[o(i,{label:"\u64CD\u4F5C",width:"180"},{default:a(({row:l,column:D,$index:A})=>[o(f,{options:F({row:l,column:D,$index:A})},null,8,["options"])]),_:1})]),_:1},8,["columns"]),m(" \u5B50\u7EA7\u8DEF\u7531 "),o(P,{level:3})],64)}}}),I=M(y,[["__file","/Users/yw/fh/git-source/particle/web/component/pc/usagecount/views/admin/UsageCountConfigManagePage.vue"]]);export{I as default};