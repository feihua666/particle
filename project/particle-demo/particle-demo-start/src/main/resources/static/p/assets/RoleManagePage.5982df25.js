import{d as h,r as _,a as w,b as u,o as v,c as B,e as f,f as a,w as s,g as C,F as E,m as g,_ as M}from"./index.3ad8a272.js";import{p as T,r as y}from"./roleAdminApi.e95de39f.js";import{p as N}from"./roleManage.d2b48c4f.js";import"./treeQueryComps.5854cd36.js";import"./roleCompItem.4cbc3d12.js";const S=h({name:"RoleManagePage"}),k=h({...S,setup(q){const b=_(null),n=w({form:{},formComps:N,tableColumns:[{prop:"name",label:"\u540D\u79F0",width:150,showOverflowTooltip:!0},{prop:"code",label:"\u7F16\u7801",showOverflowTooltip:!0},{prop:"parentName",label:"\u7236\u7EA7"},{prop:"isDisabled",label:"\u662F\u5426\u7981\u7528",width:80,formatter:(e,r,o,i)=>{let t=o?"\u7981\u7528":"\u542F\u7528";return o&&e.disabledReason&&(t=t+`(${e.disabledReason})`),t}},{prop:"seq",label:"\u6392\u5E8F",width:50},{prop:"remark",label:"\u63CF\u8FF0"}]}),l=_({buttonText:"\u67E5\u8BE2",loading:!1,permission:"admin:web:role:pageQuery"}),F=()=>{b.value.refreshData()},D=({pageQuery:e})=>T({...n.form,...e}),A={permission:l.value.permission},P=({row:e,column:r,$index:o})=>{if(o<0)return[];let t=[{txt:"\u7F16\u8F91",text:!0,permission:"admin:web:role:update",route:{path:"/admin/roleManageUpdate",query:{id:e.id}}},{txt:"\u5220\u9664",position:"more",text:!0,permission:"admin:web:role:delete",methodConfirmText:`\u786E\u5B9A\u8981\u5220\u9664 ${e.name} \u5417\uFF1F`,method(){return y({id:e.id}).then(d=>(F(),Promise.resolve(d)))}}],m={roleId:e.id,roleName:e.name},p={roleId:e.id,roleName:e.name};return g("func")&&t.push({txt:"\u89D2\u8272\u5206\u914D\u529F\u80FD\u83DC\u5355",position:"more",text:!0,permission:"admin:web:roleFuncRel:roleAssignFunc",route:{path:"/admin/roleManageRoleAssignFunc",query:m}}),g("data-constraint")&&t.push({txt:"\u89D2\u8272\u5206\u914D\u6570\u636E\u8303\u56F4",position:"more",text:!0,permission:"admin:web:roleDataScopeRel:roleAssignDataScope",route:{path:"/admin/roleManageRoleAssignDataScope",query:p}}),t};return(e,r)=>{const o=u("PtButton"),i=u("PtForm"),t=u("PtButtonGroup"),m=u("el-table-column"),p=u("PtTable"),d=u("PtRouteViewPopover");return v(),B(E,null,[f(" \u67E5\u8BE2\u8868\u5355 "),a(i,{form:n.form,method:F,defaultButtonsShow:"submit,reset",submitAttrs:l.value,inline:"",comps:n.formComps},{buttons:s(()=>[a(o,{permission:"admin:web:role:create",route:"/admin/roleManageAdd"},{default:s(()=>[C("\u6DFB\u52A0")]),_:1})]),_:1},8,["form","submitAttrs","comps"]),f(" \u6307\u5B9A dataMethod\uFF0C\u9ED8\u8BA4\u52A0\u8F7D\u6570\u636E "),a(p,{ref_key:"tableRef",ref:b,"default-expand-all":"",dataMethod:D,onDataMethodDataLoading:r[0]||(r[0]=c=>l.value.loading=c),dataMethodResultHandleConvertToTree:!0,paginationProps:A,columns:n.tableColumns},{defaultAppend:s(()=>[a(m,{label:"\u64CD\u4F5C",width:"180"},{default:s(({row:c,column:x,$index:R})=>[a(t,{options:P({row:c,column:x,$index:R}),dropdownTriggerButtonOptions:{text:!0,buttonText:"\u66F4\u591A"}},null,8,["options"])]),_:1})]),_:1},8,["columns"]),f(" \u5B50\u7EA7\u8DEF\u7531 "),a(d,{level:3})],64)}}}),U=M(k,[["__file","/Users/yw/fh/git-source/particle/web/component/pc/role/views/admin/RoleManagePage.vue"]]);export{U as default};