import{p as h,r as w}from"./navigationCategoryAdminApi.cb25ccb6.js";import{p as x}from"./navigationCategoryManage.277f47cd.js";import{d as b,r as g,a as y,b as e,o as D,c as A,e as o,w as n,f as R,F as T}from"./index.6cdd7c8b.js";import"./navigationCategoryCompItem.20edcd76.js";const M=b({name:"NavigationCategoryManagePage"}),G=b({...M,setup(E){const m=g(null),a=y({form:{},formComps:x,tableColumns:[{prop:"name",label:"\u540D\u79F0"},{prop:"code",label:"\u7F16\u7801"},{prop:"icon",label:"\u56FE\u6807",columnView:"image"},{prop:"remark",label:"\u63CF\u8FF0"},{prop:"seq",label:"\u6392\u5E8F"}]}),r=g({buttonText:"\u67E5\u8BE2",loading:!1,permission:"admin:web:navigationCategory:pageQuery"}),p=()=>{m.value.refreshData()},f=({pageQuery:t})=>h({...a.form,...t}),_={permission:r.value.permission},v=({row:t,column:u,$index:s})=>s<0?[]:[{txt:"\u7F16\u8F91",text:!0,permission:"admin:web:navigationCategory:update",route:{path:"/admin/NavigationCategoryManageUpdate",query:{id:t.id}}},{txt:"\u5220\u9664",text:!0,permission:"admin:web:navigationCategory:delete",methodConfirmText:`\u786E\u5B9A\u8981\u5220\u9664 ${t.name} \u5417\uFF1F`,method(){return w({id:t.id}).then(i=>(p(),Promise.resolve(i)))}}];return(t,u)=>{const s=e("PtButton"),d=e("PtForm"),c=e("PtButtonGroup"),i=e("el-table-column"),C=e("PtTable"),F=e("PtRouteViewPopover");return D(),A(T,null,[o(d,{form:a.form,method:p,defaultButtonsShow:"submit,reset",submitAttrs:r.value,inline:"",comps:a.formComps},{buttons:n(()=>[o(s,{permission:"admin:web:navigationCategory:create",route:"/admin/NavigationCategoryManageAdd"},{default:n(()=>[R("\u6DFB\u52A0")]),_:1})]),_:1},8,["form","submitAttrs","comps"]),o(C,{ref_key:"tableRef",ref:m,"default-expand-all":"",dataMethod:f,onDataMethodDataLoading:u[0]||(u[0]=l=>r.value.loading=l),dataMethodResultHandleConvertToTree:!0,paginationProps:_,columns:a.tableColumns},{defaultAppend:n(()=>[o(i,{label:"\u64CD\u4F5C",width:"180"},{default:n(({row:l,column:P,$index:B})=>[o(c,{options:v({row:l,column:P,$index:B})},null,8,["options"])]),_:1})]),_:1},8,["columns"]),o(F,{level:3})],64)}}});export{G as default};