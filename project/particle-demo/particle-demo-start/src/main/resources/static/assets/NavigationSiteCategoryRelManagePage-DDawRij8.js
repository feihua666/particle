import{p as I,r as B}from"./navigationSiteCategoryRelAdminApi-BT3wKf4P.js";import{p as _}from"./navigationSiteCategoryRelManage-DLZ163HG.js";import{d as P,r as b,a as c,b as n,o as A,c as M,e as a,w as i,f as r,F as w}from"./index-BGwvwH0Y.js";import"./navigationCategoryCompItem-CQ4NGqYj.js";import"./navigationCategoryAdminApi-CHybieh4.js";import"./navigationSiteCompItem-BLk12lju.js";import"./navigationSiteAdminApi-DBfQIG6y.js";const k=P({__name:"NavigationSiteCategoryRelManagePage",setup(h){const C=b(null),s=c({form:{},formComps:_,tableColumns:[{prop:"navigationSiteName",label:"导航网站",width:150,showOverflowTooltip:!0},{prop:"navigationCategoryName",label:"导航分类",width:150,showOverflowTooltip:!0},{prop:"seq",label:"排序",width:100}]}),m=b({buttonText:"查询",loading:!1,permission:"admin:web:navigationSiteCategoryRel:pageQuery"}),S=()=>{C.value.refreshData()},f=({pageQuery:t})=>I({...s.form,...t}),R={permission:m.value.permission},x=({row:t,column:e,$index:o})=>{if(o<0)return[];let l={id:t.id},v={id:t.id,navigationSiteId:t.navigationSiteId,navigationSiteName:t.navigationSiteName},d={navigationCategoryId:t.navigationCategoryId,navigationCategoryName:t.navigationCategoryName},u={navigationCategoryId:t.navigationCategoryId,navigationCategoryName:t.navigationCategoryName},p={navigationSiteId:t.navigationSiteId,navigationSiteName:t.navigationSiteName},g={navigationSiteId:t.navigationSiteId,navigationSiteName:t.navigationSiteName};return[{txt:"为该导航分类分配导航网站",text:!0,permission:"admin:web:navigationSiteCategoryRel:navigationCategoryAssignNavigationSite",route:{path:"/admin/navigationSiteCategoryRelManageNavigationCategoryAssignNavigationSite",query:d}},{txt:"为该导航网站分配导航分类",text:!0,permission:"admin:web:navigationSiteCategoryRel:navigationSiteAssignNavigationCategory",route:{path:"/admin/navigationSiteCategoryRelManageNavigationSiteAssignNavigationCategory",query:p}},{txt:"为该导航分类清空导航网站",text:!0,methodConfirmText:`您将清空导航分类 ${t.navigationCategoryName} 所有导航网站,该导航分类将不再分配给任何导航网站，同时拥有涉及对应导航网站的用户导航分类将受到影响，请谨慎操作！！！，确定要清空吗？`,permission:"admin:web:navigationSiteCategoryRel:deleteByNavigationCategoryId",route:{path:"/admin/navigationSiteCategoryRelManageDeleteByNavigationCategoryId",query:u}},{txt:"为该导航网站清空导航分类",text:!0,methodConfirmText:`您将清空导航网站 ${t.navigationSiteName} 所有导航分类,该导航网站将不再拥有任何导航分类，同时拥有该导航网站的用户导航分类将受到影响，请谨慎操作！！！，确定要清空吗？`,permission:"admin:web:navigationSiteCategoryRel:deleteByNavigationSiteId",route:{path:"/admin/navigationSiteCategoryRelManageDeleteByNavigationSiteId",query:g}},{txt:"编辑",text:!0,permission:"admin:web:navigationSiteCategoryRel:update",route:{path:"/admin/navigationSiteCategoryRelManageUpdate",query:v}},{txt:"删除",text:!0,permission:"admin:web:navigationSiteCategoryRel:delete",methodConfirmText:`删除后导航网站 ${t.navigationSiteName} 将不再拥有导航分类 ${t.navigationCategoryName}，确定要删除吗？`,method(){return B(l).then(y=>(S(),Promise.resolve(y)))}}]};return(t,e)=>{const o=n("PtButton"),l=n("PtForm"),v=n("PtButtonGroup"),d=n("el-table-column"),u=n("PtTable"),p=n("PtRouteViewPopover");return A(),M(w,null,[a(l,{form:s.form,method:S,defaultButtonsShow:"submit,reset",submitAttrs:m.value,inline:"",comps:s.formComps},{buttons:i(()=>[a(o,{permission:"admin:web:navigationSiteCategoryRel:create",route:"/admin/NavigationSiteCategoryRelManageAdd"},{default:i(()=>e[1]||(e[1]=[r("添加")])),_:1}),a(o,{permission:"admin:web:navigationSiteCategoryRel:navigationCategoryAssignNavigationSite",route:"/admin/navigationSiteCategoryRelManageNavigationCategoryAssignNavigationSite"},{default:i(()=>e[2]||(e[2]=[r("导航分类分配导航网站")])),_:1}),a(o,{permission:"admin:web:navigationSiteCategoryRel:navigationSiteAssignNavigationCategory",route:"/admin/navigationSiteCategoryRelManageNavigationSiteAssignNavigationCategory"},{default:i(()=>e[3]||(e[3]=[r("导航网站分配导航分类")])),_:1}),a(o,{permission:"admin:web:navigationSiteCategoryRel:deleteByNavigationCategoryId",route:"/admin/navigationSiteCategoryRelManageDeleteByNavigationCategoryId"},{default:i(()=>e[4]||(e[4]=[r("清空导航分类导航网站")])),_:1}),a(o,{permission:"admin:web:navigationSiteCategoryRel:deleteByNavigationSiteId",route:"/admin/navigationSiteCategoryRelManageDeleteByNavigationSiteId"},{default:i(()=>e[5]||(e[5]=[r("清空导航网站导航分类")])),_:1})]),_:1},8,["form","submitAttrs","comps"]),a(u,{ref_key:"tableRef",ref:C,"default-expand-all":"",dataMethod:f,onDataMethodDataLoading:e[0]||(e[0]=g=>m.value.loading=g),paginationProps:R,columns:s.tableColumns},{defaultAppend:i(()=>[a(d,{label:"操作"},{default:i(({row:g,column:N,$index:y})=>[a(v,{options:x({row:g,column:N,$index:y})},null,8,["options"])]),_:1})]),_:1},8,["columns"]),a(p,{level:3})],64)}}});export{k as default};