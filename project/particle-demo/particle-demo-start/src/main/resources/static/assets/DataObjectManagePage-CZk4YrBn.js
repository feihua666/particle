import{p as h,r as x}from"./dataObjectAdminApi-DRHTeweL.js";import{p as O}from"./dataObjectManage-DiQxDzeI.js";import{d as T,r as b,a as B,b as n,o as C,c as R,e as r,w as m,f as j,F as A}from"./index-BGwvwH0Y.js";const N=T({__name:"DataObjectManagePage",setup(M){const d=b(null),s=B({form:{},formComps:O,tableColumns:[{prop:"code",label:"编码",showOverflowTooltip:!0},{prop:"name",label:"名称",showOverflowTooltip:!0},{prop:"customDataUrl",label:"自定义数据url",showOverflowTooltip:!0},{prop:"isCustomDataLazy",label:"自定义数据是否懒加载",formatter:(t,o,e,l)=>e?"懒加载":"一次性数据"},{prop:"customDataTypeDictName",label:"自定义数据交互方式"},{prop:"customDataConfigJson",label:"数据交互方式内容",showOverflowTooltip:!0},{prop:"isDisabled",label:"是否禁用",width:80,formatter:(t,o,e,l)=>{let a=e?"禁用":"启用";return e&&t.disabledReason&&(a=a+`(${t.disabledReason})`),a}},{prop:"remark",label:"描述",showOverflowTooltip:!0}]}),i=b({buttonText:"查询",loading:!1,permission:"admin:web:dataObject:pageQuery"}),c=()=>{d.value.refreshData()},f=({pageQuery:t})=>h({...s.form,...t}),_={permission:i.value.permission},w=({row:t,column:o,$index:e})=>e<0?[]:[{txt:"编辑",text:!0,permission:"admin:web:dataObject:update",route:{path:"/admin/DataObjectManageUpdate",query:{id:t.id}}},{txt:"删除",text:!0,permission:"admin:web:dataObject:delete",methodConfirmText:`确定要删除 ${t.name} 吗？`,method(){return x({id:t.id}).then(p=>(c(),Promise.resolve(p)))}}];return(t,o)=>{const e=n("PtButton"),l=n("PtForm"),a=n("PtButtonGroup"),p=n("el-table-column"),P=n("PtTable"),v=n("PtRouteViewPopover");return C(),R(A,null,[r(l,{form:s.form,method:c,defaultButtonsShow:"submit,reset",submitAttrs:i.value,inline:"",comps:s.formComps},{buttons:m(()=>[r(e,{permission:"admin:web:dataObject:create",route:"/admin/DataObjectManageAdd"},{default:m(()=>o[1]||(o[1]=[j("添加")])),_:1})]),_:1},8,["form","submitAttrs","comps"]),r(P,{ref_key:"tableRef",ref:d,"default-expand-all":"",dataMethod:f,onDataMethodDataLoading:o[0]||(o[0]=u=>i.value.loading=u),paginationProps:_,columns:s.tableColumns},{defaultAppend:m(()=>[r(p,{label:"操作",width:"180"},{default:m(({row:u,column:g,$index:D})=>[r(a,{options:w({row:u,column:g,$index:D})},null,8,["options"])]),_:1})]),_:1},8,["columns"]),r(v,{level:3})],64)}}});export{N as default};