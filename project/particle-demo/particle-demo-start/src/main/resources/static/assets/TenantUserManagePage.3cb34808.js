import{u as C,p as D,r as A}from"./tenantUserManage.397621d1.js";import{d as f,r as b,a as h,b as n,o as w,c as R,e as a,w as l,f as T,F as U}from"./index.30ac3b34.js";const E=f({name:"TenantUserManagePage"}),N=f({...E,setup(k){const d=b(null),s=h({form:{},formComps:C({props:{}}),tableColumns:[{prop:"nickname",label:"\u6635\u79F0",showOverflowTooltip:!0},{prop:"name",label:"\u7528\u6237\u59D3\u540D"},{prop:"avatar",label:"\u5934\u50CF",columnView:"image"},{prop:"joinAt",label:"\u52A0\u5165\u65F6\u95F4"},{prop:"isExpired",label:"\u662F\u5426\u8FC7\u671F",formatter:(e,u,t,r)=>{let o=t?"\u5DF2\u8FC7\u671F":"\u6B63\u5E38";return t&&e.expiredReason&&(o=o+`(${e.expiredReason})`),o}},{prop:"expireAt",label:"\u5230\u671F\u65F6\u95F4"},{prop:"isLeave",label:"\u662F\u5426\u79BB\u804C/\u9000\u51FA",formatter:(e,u,t,r)=>{let o=t?"\u79BB\u804C/\u9000\u51FA":"\u6B63\u5E38";return t&&e.leaveReason&&(o=o+`(${e.leaveReason})`),o}},{prop:"leaveAt",label:"\u79BB\u804C/\u9000\u51FA\u65F6\u95F4"},{prop:"isFormal",label:"\u662F\u5426\u6B63\u5F0F",formatter:(e,u,t,r)=>t?"\u6B63\u5F0F":"\u8BD5\u7528"},{prop:"tenantName",label:"\u79DF\u6237\u540D\u79F0"}]}),m=b({buttonText:"\u67E5\u8BE2",loading:!1,permission:"admin:web:tenantUser:pageQuery"}),c=()=>{d.value.refreshData()},_=({pageQuery:e})=>D({...s.form,...e}),B={permission:m.value.permission},P=({row:e,column:u,$index:t})=>t<0?[]:[{txt:"\u7F16\u8F91",text:!0,permission:"admin:web:tenantUser:update",route:{path:"/admin/TenantUserManageUpdate",query:{...{id:e.id},userId:e.userId,userNickname:e.nickname}}},{txt:"\u5220\u9664",text:!0,permission:"admin:web:tenantUser:delete",methodConfirmText:`\u786E\u5B9A\u8981\u5220\u9664 ${e.name} \u5417\uFF1F`,method(){return A({id:e.id}).then(i=>(c(),Promise.resolve(i)))}}];return(e,u)=>{const t=n("PtButton"),r=n("PtForm"),o=n("PtButtonGroup"),F=n("el-table-column"),i=n("PtTable"),v=n("PtRouteViewPopover");return w(),R(U,null,[a(r,{form:s.form,method:c,defaultButtonsShow:"submit,reset",submitAttrs:m.value,inline:"",comps:s.formComps},{buttons:l(()=>[a(t,{permission:"admin:web:tenantUser:create",route:"/admin/TenantUserManageAdd"},{default:l(()=>[T("\u6DFB\u52A0")]),_:1})]),_:1},8,["form","submitAttrs","comps"]),a(i,{ref_key:"tableRef",ref:d,"default-expand-all":"",dataMethod:_,onDataMethodDataLoading:u[0]||(u[0]=p=>m.value.loading=p),paginationProps:B,columns:s.tableColumns},{defaultAppend:l(()=>[a(F,{label:"\u64CD\u4F5C",width:"180"},{default:l(({row:p,column:x,$index:g})=>[a(o,{options:P({row:p,column:x,$index:g})},null,8,["options"])]),_:1})]),_:1},8,["columns"]),a(v,{level:3})],64)}}});export{N as default};