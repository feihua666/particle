import{p as g,r as Q,d as T,a as M,c as _,b as C,e as R}from"./dataQueryDataApiAdminApi-ByQtG2-r.js";import{p as B}from"./dataQueryDataApiManage-Bt6zI6_k.js";import{d as N,r as b,a as S,b as s,o as V,c as F,e as n,w as p,f as $,F as k}from"./index-_8rL1G8m.js";import"./dataQueryDatasourceApiManage-DUUysU_G.js";import"./dataQueryDatasourceAdminApi-BUk5qnX0.js";import"./dataqueryProviderCompItem-CXdVZONI.js";import"./dataQueryProviderAdminApi-CC8a5fD0.js";const U=N({__name:"DataQueryDataApiManagePage",props:{url:{type:String}},setup(h){const c=b(null),i=S({form:{url:h.url||null},formComps:B,tableColumns:[{prop:"url",label:"接口地址"},{prop:"name",label:"接口名称"},{prop:"dataQueryDatasourceApiName",label:"直连数据源接口"},{prop:"adaptTypeDictName",label:"适配类型"},{prop:"inParamTypeDictName",label:"入参类型"},{prop:"outParamTypeDictName",label:"出参类型"},{prop:"responseTypeDictName",label:"输出类型"},{prop:"isPublished",label:"是否发布",formatter:(e,o,a,r)=>a?"已发布":"未发布"},{prop:"isMaster",label:"配置分支",formatter:(e,o,a,r)=>a?"master":"dev"},{prop:"isTestPassed",label:"是否测试通过",formatter:(e,o,a,r)=>a?"测试通过":"未测试通过"},{prop:"remark",label:"描述"}]}),u=b({buttonText:"查询",loading:!1,permission:"admin:web:dataQueryDataApi:pageQuery"}),d=()=>{c.value.refreshData()},f=({pageQuery:e})=>g({...i.form,...e}),y={permission:u.value.permission},D=({row:e,column:o,$index:a})=>{if(a<0)return[];let r={id:e.id},m=[{txt:"接口测试",text:!0,permission:"admin:web:dataQueryDataApi:test",route:{path:"/admin/DataQueryDataApiManageTest",query:r}},{txt:"编辑",text:!0,disabled:e.isPublished,disabledReason:e.isPublished?"已发布不能编辑，可以通过复制dev来修改提交":void 0,position:"more",permission:"admin:web:dataQueryDataApi:update",route:{path:"/admin/DataQueryDataApiManageUpdate",query:r}},{txt:"删除",text:!0,disabled:e.isPublished,disabledReason:e.isPublished?"已发布不能删除":void 0,position:"more",permission:"admin:web:dataQueryDataApi:delete",methodConfirmText:`确定要删除 ${e.name} 吗？`,method(){return Q({id:e.id}).then(t=>(d(),Promise.resolve(t)))}},{txt:"删除缓存",text:!0,position:"more",permission:"admin:web:dataQueryDataApi:deleteCache",methodConfirmText:`确定要删除 ${e.name} 缓存吗？如果部署多个实例可能要多次执行`,methodSuccess:t=>"删除缓存成功 "+t.data.data,method(){return T({id:e.id}).then(t=>Promise.resolve(t))}},{txt:"刷新缓存",text:!0,position:"more",permission:"admin:web:dataQueryDataApi:refreshCache",methodSuccess:t=>"刷新缓存成功,如果部署多个实例可能要多次执行。 "+t.data.data,method(){return M({id:e.id}).then(t=>Promise.resolve(t))}},{txt:"复制",text:!0,disabled:!e.isMaster,disabledReason:e.isMaster?void 0:"仅支持master复制",position:"more",permission:"admin:web:dataQueryDataApi:copy",methodConfirmText:`确定要复制 ${e.name} 吗？`,methodSuccess:t=>{i.form.name=t.data.data.name,d()},method(){return _({id:e.id}).then(t=>Promise.resolve(t))}},{txt:"复制dev",text:!0,disabled:!e.isMaster,disabledReason:e.isMaster?void 0:"仅支持master复制",position:"more",permission:"admin:web:dataQueryDataApi:copydev",methodConfirmText:`确定要复制 ${e.name} 吗？复制后可以修改并提交到主分支`,methodSuccess:t=>{i.form.name=t.data.data.name,d()},method(){return C({id:e.id}).then(t=>Promise.resolve(t))}},{txt:"查看",text:!0,position:"more",permission:"admin:web:dataQueryDataApi:view",route:{path:"/admin/DataQueryDataApiManageView",query:r}}];return e.isMaster||m.push({txt:"提交至master",text:!0,disabled:e.isMaster,disabledReason:e.isMaster?"仅支持dev":void 0,position:"more",permission:"admin:web:dataQueryDatasourceApi:devMergeToMaster",methodConfirmText:`确定要将 ${e.name} 提交至master吗？提交成功后本数据将会被删除！`,methodSuccess:t=>{d()},method(){return R({id:e.id}).then(t=>Promise.resolve(t))}}),e.adaptTypeDictValue=="single_direct"&&m.push({txt:"查看数据源接口",text:!0,position:"more",permission:"admin:web:dataQueryDatasourceApi:pageQuery",route:{path:"/admin/dataQueryDatasourceApiManagePage",query:{dataQueryDatasourceApiName:e.dataQueryDatasourceApiName}}}),m};return(e,o)=>{const a=s("PtButton"),r=s("PtForm"),m=s("PtButtonGroup"),t=s("el-table-column"),x=s("PtTable"),v=s("PtRouteViewPopover");return V(),F(k,null,[n(r,{form:i.form,method:d,defaultButtonsShow:"submit,reset",submitAttrs:u.value,inline:"",labelWidth:"120",comps:i.formComps},{buttons:p(()=>[n(a,{permission:"admin:web:dataQueryDataApi:create",route:"/admin/DataQueryDataApiManageAdd"},{default:p(()=>o[1]||(o[1]=[$("添加")])),_:1})]),_:1},8,["form","submitAttrs","comps"]),n(x,{ref_key:"tableRef",ref:c,"default-expand-all":"",dataMethod:f,onDataMethodDataLoading:o[0]||(o[0]=l=>u.value.loading=l),dataMethodResultHandleConvertToTree:!0,paginationProps:y,columns:i.tableColumns},{defaultAppend:p(()=>[n(t,{label:"操作",width:"220"},{default:p(({row:l,column:P,$index:A})=>[n(m,{options:D({row:l,column:P,$index:A}),dropdownTriggerButtonOptions:{text:!0,buttonText:"更多"}},null,8,["options"])]),_:1})]),_:1},8,["columns"]),n(v,{level:3})],64)}}});export{U as default};