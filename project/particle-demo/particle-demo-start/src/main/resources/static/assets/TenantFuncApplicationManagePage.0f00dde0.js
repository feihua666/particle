import{d as A,r as F,a as x,az as B,b as e,o as C,c as D,e as n,w as u,f as I,F as M,aA as R,aB as w,ay as y}from"./index.661e0f85.js";import{p as E}from"./tenantFuncApplicationManage.08c5396f.js";import"./funcApplicationAdminApi.f1f31792.js";const V=A({name:"TenantFuncApplicationManagePage"}),L=A({...V,setup(k){const d=F(null),a=x({form:{},formComps:E,tableColumns:B}),i=F({buttonText:"\u67E5\u8BE2",loading:!1,permission:"admin:web:tenantFuncApplication:pageQuery"}),f=()=>{d.value.refreshData()},_=({pageQuery:t})=>R({...a.form,...t}),b={permission:i.value.permission},T=({row:t,column:s,$index:p})=>{if(p<0)return[];let l={id:t.id};t.tenantId;let r={tenantId:t.tenantId,funcApplicationId:t.funcApplicationId},o=[{txt:"\u7F16\u8F91",text:!0,permission:"admin:web:tenantFuncApplication:update",route:{path:"/admin/TenantFuncApplicationManageUpdate",query:l}},{txt:"\u5220\u9664",text:!0,permission:"admin:web:tenantFuncApplication:delete",methodConfirmText:`\u786E\u5B9A\u8981\u5220\u9664 ${t.name} \u5417\uFF1F`,method(){return w({id:t.id}).then(c=>(f(),Promise.resolve(c)))}}];return!t.isGroup&&o.push({txt:"\u79DF\u6237\u5E94\u7528\u5206\u914D\u529F\u80FD\u83DC\u5355",text:!0,permission:"admin:web:tenantFunc:tenantAssignFunc",route:{path:"/admin/tenantFuncApplication/tenantFuncTenantAssignFunc",query:r}}),o},P=t=>y(t,null,"funcApplicationId","parentFuncApplicationId");return(t,s)=>{const p=e("PtButton"),l=e("PtForm"),r=e("PtButtonGroup"),o=e("el-table-column"),c=e("PtTable"),g=e("PtRouteViewPopover");return C(),D(M,null,[n(l,{form:a.form,method:f,defaultButtonsShow:"submit,reset",submitAttrs:i.value,inline:"",comps:a.formComps},{buttons:u(()=>[n(p,{permission:"admin:web:tenantFuncApplication:create",route:"/admin/TenantFuncApplicationManageAdd"},{default:u(()=>[I("\u6DFB\u52A0")]),_:1})]),_:1},8,["form","submitAttrs","comps"]),n(c,{ref_key:"tableRef",ref:d,"default-expand-all":"",dataMethod:_,onDataMethodDataLoading:s[0]||(s[0]=m=>i.value.loading=m),dataMethodResultHandleConvertToTree:!0,dataMethodResultHandleListToTreeMethod:P,paginationProps:b,columns:a.tableColumns},{defaultAppend:u(()=>[n(o,{label:"\u64CD\u4F5C",width:"180"},{default:u(({row:m,column:h,$index:v})=>[n(r,{options:T({row:m,column:h,$index:v})},null,8,["options"])]),_:1})]),_:1},8,["columns"]),n(g,{level:3})],64)}}});export{L as default};