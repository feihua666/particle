import{d as n,a as l,r as c,b as d,o as f,c as u,e as P,f as v,F as _,_ as g}from"./index.49d89bd8.js";import{b as R,c as O}from"./openplatformProviderRecordManage.6a4ba345.js";import"./dataqueryProviderCompItem.f2a93b0b.js";import"./dataQueryProviderAdminApi.bd7af3ab.js";import"./openplatformProviderCompItem.2d08c002.js";import"./openplatformProviderAdminApi.b827b019.js";import"./crmCompItem.a6ee91d7.js";import"./crmCustomerTagAdminApi.245e486d.js";const b=n({name:"OpenplatformProviderRecordManageParamViewPage"}),h=n({...b,props:{openplatformProviderRecordId:{type:String}},setup(m){const a=m,s=l({form:{id:a.openplatformProviderRecordId,version:1},formData:{}}),p=c(R),i=()=>O({id:a.openplatformProviderRecordId}).then(o=>{let r=o.data.data;try{let e=r.requestParam;if(e){let t=JSON.parse(e);r.requestParam=JSON.stringify(t,null,"   ")}}catch{}try{let e=r.responseResult;if(e){let t=JSON.parse(e);r.responseResult=JSON.stringify(t,null,"   ")}}catch{}return Promise.resolve(o)});return(o,r)=>{const e=d("PtForm");return f(),u(_,null,[P(" \u6DFB\u52A0\u8868\u5355 "),v(e,{form:s.form,formData:s.formData,labelWidth:"80",dataMethod:i,defaultButtonsShow:"",buttonsTeleportProps:o.$route.meta.formButtonsTeleportProps,inline:"",layout:1,comps:p.value},null,8,["form","formData","buttonsTeleportProps","comps"])],2112)}}}),I=g(h,[["__file","/Users/yw/fh/git-source/particle/web/component/pc/openplatform/views/providerrecord/admin/OpenplatformProviderRecordManageParamViewPage.vue"]]);export{I as default};