import{d as g,r as b,a as C,a8 as v,b as o,o as w,c as D,e as t,w as a,f as _,F as M,a9 as T,aa as R}from"./index.4ca05ef1.js";import{p as y}from"./funcManage.f4323e08.js";import"./treeQueryComps.5854cd36.js";const E=g({name:"FuncManagePage"}),N=g({...E,setup(V){const p=b(null),u=C({form:{},formComps:y,tableColumns:v}),i=b({buttonText:"\u67E5\u8BE2",loading:!1,permission:"admin:web:func:pageQuery"}),c=()=>{p.value.refreshData()},P=({pageQuery:e})=>T({...u.form,...e}),F={permission:i.value.permission},x=({row:e,column:m,$index:r})=>{if(r<0)return[];let n={id:e.id},s=[{txt:"\u7F16\u8F91",text:!0,permission:"admin:web:func:update",route:{path:"/admin/funcManageUpdate",query:n}},{txt:"\u5220\u9664",text:!0,permission:"admin:web:func:delete",methodConfirmText:`\u786E\u5B9A\u8981\u5220\u9664 ${e.name} \u5417\uFF1F`,method(){return R({id:e.id}).then(d=>(c(),Promise.resolve(d)))}},{txt:"\u6DFB\u52A0\u5B50\u7EA7",position:"more",text:!0,permission:"admin:web:func:create",route:{path:"/admin/funcManageAdd",query:n}}];return e.typeDictValue=="page"&&s.push({txt:"\u6DFB\u52A0Crud",position:"more",text:!0,permission:"admin:web:func:create",route:{path:"/admin/funcManageCrudAdd",query:n}}),s};return(e,m)=>{const r=o("PtButton"),n=o("PtForm"),s=o("PtButtonGroup"),f=o("el-table-column"),d=o("PtTable"),B=o("PtRouteViewPopover");return w(),D(M,null,[t(n,{form:u.form,method:c,defaultButtonsShow:"submit,reset",submitAttrs:i.value,inline:"",labelWidth:"80",comps:u.formComps},{buttons:a(()=>[t(r,{permission:"admin:web:func:create",route:"/admin/funcManageAdd"},{default:a(()=>[_("\u6DFB\u52A0")]),_:1}),t(r,{permission:"admin:web:func:create",route:"/admin/funcManageCrudAdd"},{default:a(()=>[_("\u6DFB\u52A0Crud")]),_:1})]),_:1},8,["form","submitAttrs","comps"]),t(d,{ref_key:"tableRef",ref:p,"default-expand-all":"",dataMethod:P,onDataMethodDataLoading:m[0]||(m[0]=l=>i.value.loading=l),dataMethodResultHandleConvertToTree:!0,paginationProps:F,columns:u.tableColumns},{defaultAppend:a(()=>[t(f,{label:"\u64CD\u4F5C",width:"220"},{default:a(({row:l,column:h,$index:A})=>[t(s,{options:x({row:l,column:h,$index:A}),dropdownTriggerButtonOptions:{text:!0,buttonText:"\u66F4\u591A"}},null,8,["options"])]),_:1})]),_:1},8,["columns"]),t(B,{level:3})],64)}}});export{N as default};