import{p as y,r as A}from"./lowcodeModelItemAdminApi.9abb2813.js";import{p as I}from"./lowcodeModelItemManage.74224b78.js";import{d as F,r as B,a as v,b as o,o as D,c as T,e,w as u,f as w,F as x}from"./index.858f5c96.js";import"./lowcodeModelAdminApi.9861ef74.js";const R=F({name:"LowcodeModelItemManagePage"}),q=F({...R,props:{lowcodeModelId:{type:String}},setup(d){const f=d,i=B(null),l=v({form:{lowcodeModelId:f.lowcodeModelId},formComps:I,tableColumns:[{label:"\u5B57\u6BB5\u540D\u79F0",prop:"columnName"},{label:"\u5B9E\u4F53\u5C5E\u6027\u540D\u79F0",prop:"propertyName"},{label:"\u5B57\u6BB5\u7C7B\u578B",prop:"jdbcType"},{label:"\u5B9E\u4F53\u5C5E\u6027\u7C7B\u578B",prop:"propertyType"},{label:"\u5B9E\u4F53\u5C5E\u6027\u7C7B\u578B\u5168\u8DEF\u5F84",prop:"propertyFullType",showOverflowTooltip:!0},{label:"\u5168\u6CE8\u91CA",prop:"commentFull",showOverflowTooltip:!0},{label:"\u7B80\u6D01\u6CE8\u91CA",prop:"commentSimple",showOverflowTooltip:!0},{label:"\u9ED8\u8BA4\u503C",prop:"defaultValue"},{label:"\u662F\u5426\u552F\u4E00",prop:"isUnique"},{label:"\u662F\u5426\u5FC5\u586B",prop:"isRequired"},{label:"\u662F\u5426\u4E3B\u952E",prop:"isKey"},{label:"\u662F\u5426\u4E3B\u952E\u81EA\u589E",prop:"isKeyIdentity"},{label:"\u662F\u5426\u5173\u952E\u5B57",prop:"isKeyWord"},{label:"\u957F\u5EA6",prop:"columnLength"},{label:"\u5C0F\u6570\u4F4D\u957F\u5EA6",prop:"fractionLength"},{label:"\u662F\u5426\u5916\u952E",prop:"isForeignKey"},{label:"\u6A21\u578B",prop:"lowcodeModelName"},{label:"\u8BBE\u8BA1\u6570\u636E",prop:"designJson",showOverflowTooltip:!0},{label:"\u63CF\u8FF0",prop:"remark"}]}),n=B({buttonText:"\u67E5\u8BE2",loading:!1,permission:"admin:web:lowcodeModelItem:pageQuery"}),c=()=>{i.value.refreshData()},E=({pageQuery:t})=>y({...l.form,...t}),g={permission:n.value.permission},C=({row:t,column:p,$index:a})=>{if(a<0)return[];let r={id:t.id};return[{txt:"\u7F16\u8F91",text:!0,permission:"admin:web:lowcodeModelItem:update",route:{path:"/admin/lowcodeModelItemManageUpdate",query:r}},{txt:"\u5220\u9664",text:!0,permission:"admin:web:lowcodeModelItem:delete",methodConfirmText:`\u786E\u5B9A\u8981\u5220\u9664 ${t.name} \u5417\uFF1F`,method(){return A(r).then(s=>(c(),Promise.resolve(s)))}}]};return(t,p)=>{const a=o("PtButton"),r=o("PtForm"),b=o("PtButtonGroup"),s=o("el-table-column"),_=o("PtTable"),M=o("PtRouteViewPopover");return D(),T(x,null,[e(r,{form:l.form,method:c,defaultButtonsShow:"submit,reset",submitAttrs:n.value,inline:"",comps:l.formComps},{buttons:u(()=>[e(a,{permission:"admin:web:lowcodeModelItem:create",route:"/admin/lowcodeModelItemManageAdd"},{default:u(()=>[w("\u6DFB\u52A0")]),_:1}),e(a,{permission:"admin:web:lowcodeModelItem:pageQuery",route:{path:"/admin/lowcodeModelItemDesignManagePage",query:{lowcodeModelId:d.lowcodeModelId}}},{default:u(()=>[w("\u53BB\u8BBE\u8BA1")]),_:1},8,["route"])]),_:1},8,["form","submitAttrs","comps"]),e(_,{ref_key:"tableRef",ref:i,"default-expand-all":"",dataMethod:E,onDataMethodDataLoading:p[0]||(p[0]=m=>n.value.loading=m),paginationProps:g,columns:l.tableColumns},{defaultAppend:u(()=>[e(s,{label:"\u64CD\u4F5C",width:"180"},{default:u(({row:m,column:P,$index:h})=>[e(b,{options:C({row:m,column:P,$index:h})},null,8,["options"])]),_:1})]),_:1},8,["columns"]),e(M,{level:3})],64)}}});export{q as default};