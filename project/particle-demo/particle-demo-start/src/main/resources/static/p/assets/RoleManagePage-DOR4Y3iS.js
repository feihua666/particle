import{p as R,r as T}from"./roleAdminApi-DEKs4yJg.js";import{p as A}from"./roleManage-CsSZSR3X.js";import{d as D,r as g,a as B,b as a,o as C,c as F,e as r,w as l,f as M,F as y,j as _}from"./index-B_FSv1tI.js";import"./treeQueryComps-CWIG2rsy.js";import"./roleCompItem-BusJC2tO.js";const Q=D({__name:"RoleManagePage",setup(N){const b=g(null),s=B({form:{},formComps:A,tableColumns:[{prop:"name",label:"名称",width:150,showOverflowTooltip:!0},{prop:"code",label:"编码",showOverflowTooltip:!0},{prop:"parentName",label:"父级",showOverflowTooltip:!0},{prop:"isDisabled",label:"是否禁用",width:80,formatter:(e,o,n,m)=>{let t=n?"禁用":"启用";return n&&e.disabledReason&&(t=t+`(${e.disabledReason})`),t}},{prop:"seq",label:"排序",width:60},{prop:"remark",label:"描述",showOverflowTooltip:!0}]}),i=g({buttonText:"查询",loading:!1,permission:"admin:web:role:pageQuery"}),f=()=>{b.value.refreshData()},h=({pageQuery:e})=>R({...s.form,...e}),w={permission:i.value.permission},x=({row:e,column:o,$index:n})=>{if(n<0)return[];let t=[{txt:"编辑",text:!0,permission:"admin:web:role:update",route:{path:"/admin/roleManageUpdate",query:{id:e.id}}},{txt:"删除",position:"more",text:!0,permission:"admin:web:role:delete",methodConfirmText:`确定要删除 ${e.name} 吗？`,method(){return T({id:e.id}).then(d=>(f(),Promise.resolve(d)))}}],p={roleId:e.id,roleName:e.name},u={roleId:e.id,roleName:e.name};return _("func")&&t.push({txt:"角色分配功能菜单",position:"more",text:!0,permission:"admin:web:roleFuncRel:roleAssignFunc",route:{path:"/admin/roleManageRoleAssignFunc",query:p}}),_("data-constraint")&&t.push({txt:"角色分配数据范围",position:"more",text:!0,permission:"admin:web:roleDataScopeRel:roleAssignDataScope",route:{path:"/admin/roleManageRoleAssignDataScope",query:u}}),t};return(e,o)=>{const n=a("PtButton"),m=a("PtForm"),t=a("PtButtonGroup"),p=a("el-table-column"),u=a("PtTable"),d=a("PtRouteViewPopover");return C(),F(y,null,[r(m,{form:s.form,method:f,defaultButtonsShow:"submit,reset",submitAttrs:i.value,inline:"",comps:s.formComps},{buttons:l(()=>[r(n,{permission:"admin:web:role:create",route:"/admin/roleManageAdd"},{default:l(()=>o[1]||(o[1]=[M("添加")])),_:1})]),_:1},8,["form","submitAttrs","comps"]),r(u,{ref_key:"tableRef",ref:b,"default-expand-all":"",dataMethod:h,onDataMethodDataLoading:o[0]||(o[0]=c=>i.value.loading=c),dataMethodResultHandleConvertToTree:!0,paginationProps:w,columns:s.tableColumns},{defaultAppend:l(()=>[r(p,{label:"操作",width:"180"},{default:l(({row:c,column:P,$index:v})=>[r(t,{options:x({row:c,column:P,$index:v}),dropdownTriggerButtonOptions:{text:!0,buttonText:"更多"}},null,8,["options"])]),_:1})]),_:1},8,["columns"]),r(d,{level:3})],64)}}});export{Q as default};