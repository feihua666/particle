import{u as D,g as _}from"./dataQueryDataApiAdminApi.7ca133dd.js";import{a as F}from"./dataQueryDataApiManage.cfe7f642.js";import{_ as b}from"./DataQueryDatasourceApiFormItemConfigs.vue_vue_type_script_setup_true_name_DataQueryDatasourceApiFormItemConfigs_lang.e3458b2b.js";import{_ as A}from"./DataQueryDatasApiFormItemConfigs.vue_vue_type_script_setup_true_name_DataQueryDatasApiFormItemConfigs_lang.860fd12c.js";import{d as s,r as a,a as y,b as g,o as h,c as B,e as o,F as C}from"./index.6cdd7c8b.js";import"./dataQueryDatasourceApiManage.d5f1c0ae.js";import"./dataQueryDatasourceAdminApi.1b4491ac.js";import"./dataqueryProviderCompItem.f790a82b.js";import"./dataQueryProviderAdminApi.aee22e81.js";const E=s({name:"AreaManageUpdatePage"}),$=s({...E,props:{dataQueryDataApiId:{type:String}},setup(u){const e=u,r=a(null),m=a(null),t=y({form:{id:e.dataQueryDataApiId,version:1,isMaster:!0},formData:{}}),n=a(F({form:t.form,formData:t.formData,dataQueryDatasourceApiFormItemConfigsRef:r,dataQueryDataApiFormItemConfigsRef:m,addPublished:!0})),f=a({buttonText:"\u786E\u8BA4\u4FEE\u6539",permission:"admin:web:dataQueryDataApi:update"}),i=()=>D,p=()=>_({id:e.dataQueryDataApiId}),d=()=>"\u4FEE\u6539\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(c,I)=>{const l=g("PtForm");return h(),B(C,null,[o(l,{form:t.form,formData:t.formData,labelWidth:"80",dataMethod:p,method:i(),methodSuccess:d,defaultButtonsShow:"submit,reset",submitAttrs:f.value,buttonsTeleportProps:c.$route.meta.formButtonsTeleportProps,inline:"",comps:n.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"]),o(b,{form:t.form,formData:t.formData,ref_key:"dataQueryDatasourceApiFormItemConfigsRef",ref:r},null,8,["form","formData"]),o(A,{form:t.form,formData:t.formData,ref_key:"dataQueryDataApiFormItemConfigsRef",ref:m},null,8,["form","formData"])],64)}}});export{$ as default};