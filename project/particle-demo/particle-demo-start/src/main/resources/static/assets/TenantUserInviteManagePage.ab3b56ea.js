import{p as g,a as x,r as h}from"./tenantUserInviteManage.e97253d1.js";import{d as f,r as b,a as A,b as t,o as w,c as I,e as o,w as a,f as U,F as D}from"./index.147a787d.js";const E=f({name:"TenantUserInviteManagePage"}),k=f({...E,setup(R){const m=b(null),n=A({form:{},formComps:g,tableColumns:[{prop:"inviteCode",label:"\u9080\u8BF7\u7801"},{prop:"maxInviteCount",label:"\u6700\u5927\u9080\u8BF7\u4EBA\u6570"},{prop:"invitedCount",label:"\u5DF2\u9080\u8BF7\u4EBA\u6570"},{prop:"isExpired",label:"\u662F\u5426\u8FC7\u671F"},{prop:"expiredReason",label:"\u8FC7\u671F\u539F\u56E0"},{prop:"expireAt",label:"\u5230\u671F\u65F6\u95F4"},{prop:"inviterUserId",label:"\u9080\u8BF7\u4EBA\u7528\u6237id"}]}),u=b({buttonText:"\u67E5\u8BE2",loading:!1,permission:"admin:web:tenantUserInvite:pageQuery"}),p=()=>{m.value.refreshData()},_=({pageQuery:e})=>x({...n.form,...e}),F={permission:u.value.permission},v=({row:e,column:r,$index:s})=>s<0?[]:[{txt:"\u7F16\u8F91",text:!0,permission:"admin:web:tenantUserInvite:update",route:{path:"/admin/TenantUserInviteManageUpdate",query:{id:e.id}}},{txt:"\u5220\u9664",text:!0,permission:"admin:web:tenantUserInvite:delete",methodConfirmText:`\u786E\u5B9A\u8981\u5220\u9664 ${e.name} \u5417\uFF1F`,method(){return h({id:e.id}).then(i=>(p(),Promise.resolve(i)))}}];return(e,r)=>{const s=t("PtButton"),d=t("PtForm"),c=t("PtButtonGroup"),i=t("el-table-column"),B=t("PtTable"),P=t("PtRouteViewPopover");return w(),I(D,null,[o(d,{form:n.form,method:p,defaultButtonsShow:"submit,reset",submitAttrs:u.value,inline:"",comps:n.formComps},{buttons:a(()=>[o(s,{permission:"admin:web:tenantUserInvite:create",route:"/admin/TenantUserInviteManageAdd"},{default:a(()=>[U("\u6DFB\u52A0")]),_:1})]),_:1},8,["form","submitAttrs","comps"]),o(B,{ref_key:"tableRef",ref:m,"default-expand-all":"",dataMethod:_,onDataMethodDataLoading:r[0]||(r[0]=l=>u.value.loading=l),dataMethodResultHandleConvertToTree:!0,paginationProps:F,columns:n.tableColumns},{defaultAppend:a(()=>[o(i,{label:"\u64CD\u4F5C",width:"180"},{default:a(({row:l,column:C,$index:T})=>[o(c,{options:v({row:l,column:C,$index:T})},null,8,["options"])]),_:1})]),_:1},8,["columns"]),o(P,{level:3})],64)}}});export{k as default};