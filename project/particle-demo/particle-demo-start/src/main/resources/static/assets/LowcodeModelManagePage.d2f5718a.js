import{p as C,r as P}from"./lowcodeModelAdminApi.9420224f.js";import{p as x}from"./lowcodeModelManage.808c414a.js";import{d as f,r as b,a as h,b as o,o as E,c as v,e as t,w as n,f as y,F as T}from"./index.661e0f85.js";const R=f({name:"LowcodeModelManagePage"}),L=f({...R,setup(I){const i=b(null),a=h({form:{},formComps:x,tableColumns:[{prop:"name",label:"\u540D\u79F0"},{prop:"nameEn",label:"\u82F1\u6587\u540D\u79F0"},{prop:"nameEnEntity",label:"\u5B9E\u4F53\u540D\u79F0"},{prop:"nameEnEntityVar",label:"\u5B9E\u4F53\u53D8\u91CF\u540D\u79F0"},{prop:"tableName",label:"\u8868\u540D\u79F0"},{prop:"tableTypeDictName",label:"\u6A21\u578B\u8868\u7C7B\u578B"},{prop:"requestPath",label:"\u8BF7\u6C42\u8DEF\u5F84"},{prop:"remark",label:"\u63CF\u8FF0"}]}),l=b({buttonText:"\u67E5\u8BE2",loading:!1,permission:"admin:web:lowcodeModel:pageQuery"}),c=()=>{i.value.refreshData()},B=({pageQuery:e})=>C({...a.form,...e}),w={permission:l.value.permission},_=({row:e,column:r,$index:s})=>{if(s<0)return[];let u={id:e.id},m={lowcodeModelId:e.id};return[{txt:"\u7F16\u8F91",text:!0,permission:"admin:web:lowcodeModel:update",route:{path:"/admin/lowcodeModelManageUpdate",query:u}},{txt:"\u5220\u9664",text:!0,permission:"admin:web:lowcodeModel:delete",methodConfirmText:`\u786E\u5B9A\u8981\u5220\u9664 ${e.name} \u5417\uFF1F`,method(){return P(u).then(d=>(c(),Promise.resolve(d)))}},{txt:"\u52A0\u8F7D\u8F7D\u6A21\u578B\u9879",text:!0,position:"more",permission:"admin:web:lowcodeModel:loadByModelAndDatasource",methodConfirmText:`\u5982\u679C\u5B58\u5728\u5DF2\u6DFB\u52A0\u6216\u88C5\u8F7D\u7684\u6A21\u578B\u9879\u5C06\u4F1A\u88AB\u6E05\u7A7A\uFF0C\u786E\u5B9A\u8981\u88C5\u8F7D ${e.name} \u5417\uFF1F`,route:{path:"/admin/lowcodeModelManageLoadModelItemByModelAndDataSource",query:u}},{txt:"\u67E5\u770B\u6A21\u578B\u9879",text:!0,position:"more",permission:"admin:web:lowcodeModelItem:pageQuery",route:{path:"/admin/lowcodeModelItemManagePage",query:m}}]},M={text:!0,buttonText:"\u66F4\u591A"};return(e,r)=>{const s=o("PtButton"),u=o("PtForm"),m=o("PtButtonGroup"),F=o("el-table-column"),d=o("PtTable"),D=o("PtRouteViewPopover");return E(),v(T,null,[t(u,{form:a.form,method:c,defaultButtonsShow:"submit,reset",submitAttrs:l.value,inline:"",comps:a.formComps},{buttons:n(()=>[t(s,{permission:"admin:web:lowcodeModel:create",route:"/admin/lowcodeModelManageAdd"},{default:n(()=>[y("\u6DFB\u52A0")]),_:1})]),_:1},8,["form","submitAttrs","comps"]),t(d,{ref_key:"tableRef",ref:i,"default-expand-all":"",dataMethod:B,onDataMethodDataLoading:r[0]||(r[0]=p=>l.value.loading=p),paginationProps:w,columns:a.tableColumns},{defaultAppend:n(()=>[t(F,{label:"\u64CD\u4F5C",width:"220"},{default:n(({row:p,column:A,$index:g})=>[t(m,{options:_({row:p,column:A,$index:g}),dropdownTriggerButtonOptions:M},null,8,["options"])]),_:1})]),_:1},8,["columns"]),t(D,{level:3})],64)}}});export{L as default};