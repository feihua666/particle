import{p as F,a as k,r as x}from"./navigationFriendshipLinkManage-DkWnHh_8.js";import{d as B,r as c,a as L,b as o,o as C,c as R,e as n,w as r,f as T,F as A}from"./index-BGwvwH0Y.js";const V=B({__name:"NavigationFriendshipLinkManagePage",setup(D){const u=c(null),i=L({form:{},formComps:F,tableColumns:[{prop:"name",label:"网站名称"},{prop:"title",label:"网站标题"},{prop:"logoUrl",label:"网站logo图标地址",showOverflowTooltip:!0},{prop:"url",label:"网站地址",showOverflowTooltip:!0},{prop:"collectionAt",label:"收录时间"},{prop:"isPublished",label:"是否已发布",formatter:(e,t,a,l)=>a?"已发布":"未发布"},{prop:"unpublishedReason",label:"下架原因"},{prop:"remark",label:"描述"}]}),s=c({buttonText:"查询",loading:!1,permission:"admin:web:navigationFriendshipLink:pageQuery"}),d=()=>{u.value.refreshData()},f=({pageQuery:e})=>k({...i.form,...e}),g={permission:s.value.permission},_=({row:e,column:t,$index:a})=>a<0?[]:[{txt:"编辑",text:!0,permission:"admin:web:navigationFriendshipLink:update",route:{path:"/admin/NavigationFriendshipLinkManageUpdate",query:{id:e.id}}},{txt:"删除",text:!0,permission:"admin:web:navigationFriendshipLink:delete",methodConfirmText:`确定要删除 ${e.name} 吗？`,method(){return x({id:e.id}).then(p=>(d(),Promise.resolve(p)))}}];return(e,t)=>{const a=o("PtButton"),l=o("PtForm"),b=o("PtButtonGroup"),p=o("el-table-column"),v=o("PtTable"),h=o("PtRouteViewPopover");return C(),R(A,null,[n(l,{form:i.form,method:d,defaultButtonsShow:"submit,reset",submitAttrs:s.value,inline:"",comps:i.formComps},{buttons:r(()=>[n(a,{permission:"admin:web:navigationFriendshipLink:create",route:"/admin/NavigationFriendshipLinkManageAdd"},{default:r(()=>t[1]||(t[1]=[T("添加")])),_:1})]),_:1},8,["form","submitAttrs","comps"]),n(v,{ref_key:"tableRef",ref:u,"default-expand-all":"",dataMethod:f,onDataMethodDataLoading:t[0]||(t[0]=m=>s.value.loading=m),paginationProps:g,columns:i.tableColumns},{defaultAppend:r(()=>[n(p,{label:"操作",width:"180"},{default:r(({row:m,column:P,$index:w})=>[n(b,{options:_({row:m,column:P,$index:w})},null,8,["options"])]),_:1})]),_:1},8,["columns"]),n(h,{level:3})],64)}}});export{V as default};