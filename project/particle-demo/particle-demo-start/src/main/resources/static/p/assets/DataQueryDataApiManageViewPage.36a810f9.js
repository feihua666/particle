import{d as s,r as e,a as u,b as c,o as D,c as d,e as l,f as t,F as _,_ as g}from"./index.49d89bd8.js";import{h as y}from"./dataQueryDataApiAdminApi.559b21d1.js";import{a as A}from"./dataQueryDataApiManage.b9fe96fa.js";import{D as F}from"./DataQueryDatasourceApiFormItemConfigs.30f9fa84.js";import{D as Q}from"./DataQueryDatasApiFormItemConfigs.132788a1.js";import"./dataQueryDatasourceApiManage.515c9251.js";import"./dataQueryDatasourceAdminApi.510cc77e.js";import"./dataqueryProviderCompItem.f2a93b0b.js";import"./dataQueryProviderAdminApi.bd7af3ab.js";const C=s({name:"AreaManageViewPage"}),I=s({...C,props:{dataQueryDataApiId:{type:String}},setup(f){const o=f,r=e(null),m=e(null),a=u({form:{id:o.dataQueryDataApiId,version:1,isMaster:!0},formData:{}}),i=e(A({form:a.form,formData:a.formData,dataQueryDatasourceApiFormItemConfigsRef:r,dataQueryDataApiFormItemConfigsRef:m,addPublished:!0})),n=()=>y({id:o.dataQueryDataApiId});return(h,v)=>{const p=c("PtForm");return D(),d(_,null,[l(" \u6DFB\u52A0\u8868\u5355 "),t(p,{form:a.form,formData:a.formData,labelWidth:"80",dataMethod:n,defaultButtonsShow:"",inline:"",comps:i.value},null,8,["form","formData","comps"]),t(F,{form:a.form,formData:a.formData,ref_key:"dataQueryDatasourceApiFormItemConfigsRef",ref:r},null,8,["form","formData"]),t(Q,{form:a.form,formData:a.formData,ref_key:"dataQueryDataApiFormItemConfigsRef",ref:m},null,8,["form","formData"])],64)}}}),N=g(I,[["__file","/Users/yw/fh/git-source/particle/web/component/pc/dataquery/views/dataapi/admin/DataQueryDataApiManageViewPage.vue"]]);export{N as default};