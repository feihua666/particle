import{p as F,a as w,r as P}from"./openplatformDocApiDocExampleCodeManage.61ca70ec.js";import{d as D,r as f,a as g,b as o,o as v,c as h,e as t,w as u,f as T,F as O}from"./index.9463e69a.js";import"./openplatformDocCompItem.67bb1589.js";import"./openplatformDocDirNameAdminApi.1ee04e10.js";import"./openplatformDocApiDocTemplateAdminApi.5b37d10e.js";const R=D({name:"OpenplatformDocApiDocExampleCodeManagePage"}),U=D({...R,setup(M){const s=f(null),a=g({form:{},formComps:F,tableColumns:[{prop:"langDictName",label:"\u5F00\u53D1\u8BED\u8A00"},{prop:"exampleCode",label:"\u793A\u4F8B\u4EE3\u7801\u7247\u6BB5",showOverflowTooltip:!0},{prop:"exampleDownloadUrl",label:"\u793A\u4F8B\u4EE3\u7801\u4E0B\u8F7D\u5730\u5740",showOverflowTooltip:!0},{prop:"openplatformDocApiName",label:"\u7ED1\u5B9A\u63A5\u53E3",showOverflowTooltip:!0},{prop:"openplatformDocApiCode",label:"\u7ED1\u5B9A\u63A5\u53E3\u7F16\u7801",showOverflowTooltip:!0},{prop:"openplatformDocApiDocRequestUrl",label:"\u6587\u6863\u5185\u5BB9"},{prop:"seq",label:"\u6392\u5E8F"},{prop:"remark",label:"\u63CF\u8FF0"}]}),p=f({buttonText:"\u67E5\u8BE2",loading:!1,permission:"admin:web:openplatformDocApiDocExampleCode:pageQuery"}),i=()=>{s.value.refreshData()},b=({pageQuery:e})=>w({...a.form,...e}),_={permission:p.value.permission},A=({row:e,column:n,$index:l})=>l<0?[]:[{txt:"\u7F16\u8F91",text:!0,permission:"admin:web:openplatformDocApiDocExampleCode:update",route:{path:"/admin/OpenplatformDocApiDocExampleCodeManageUpdate",query:{id:e.id}}},{txt:"\u5220\u9664",text:!0,permission:"admin:web:openplatformDocApiDocExampleCode:delete",methodConfirmText:`\u786E\u5B9A\u8981\u5220\u9664 ${e.name} \u5417\uFF1F`,method(){return P({id:e.id}).then(r=>(i(),Promise.resolve(r)))}}];return(e,n)=>{const l=o("PtButton"),c=o("PtForm"),d=o("PtButtonGroup"),r=o("el-table-column"),B=o("PtTable"),E=o("PtRouteViewPopover");return v(),h(O,null,[t(c,{form:a.form,method:i,defaultButtonsShow:"submit,reset",submitAttrs:p.value,inline:"",comps:a.formComps},{buttons:u(()=>[t(l,{permission:"admin:web:openplatformDocApiDocExampleCode:create",route:"/admin/OpenplatformDocApiDocExampleCodeManageAdd"},{default:u(()=>[T("\u6DFB\u52A0")]),_:1})]),_:1},8,["form","submitAttrs","comps"]),t(B,{ref_key:"tableRef",ref:s,"default-expand-all":"",dataMethod:b,onDataMethodDataLoading:n[0]||(n[0]=m=>p.value.loading=m),paginationProps:_,columns:a.tableColumns},{defaultAppend:u(()=>[t(r,{label:"\u64CD\u4F5C",width:"180"},{default:u(({row:m,column:x,$index:C})=>[t(d,{options:A({row:m,column:x,$index:C})},null,8,["options"])]),_:1})]),_:1},8,["columns"]),t(E,{level:3})],64)}}});export{U as default};