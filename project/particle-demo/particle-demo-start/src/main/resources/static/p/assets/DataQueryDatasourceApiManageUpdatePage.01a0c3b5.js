import{d as m,r as a,a as F,b as A,o as C,c as y,e as s,f as e,F as E,_ as g}from"./index.3ad8a272.js";import{i as _,j as B,k as b}from"./dataQueryDatasourceApiManage.8dda0fa4.js";import{D as Q}from"./DataQueryDatasourceApiFormItemConfigs.a68832f2.js";import{D as h}from"./DataQueryDatasourceApiFormItemBasicConfigs.634d6ae9.js";import"./dataQueryDatasourceAdminApi.0c718d73.js";import"./dataqueryProviderCompItem.2777c275.js";import"./dataQueryProviderAdminApi.a0748a53.js";const I=m({name:"AreaManageUpdatePage"}),P=m({...I,props:{dataQueryDatasourceApiId:{type:String}},setup(n){const o=n,r=a(null),u=a(null),t=F({form:{id:o.dataQueryDatasourceApiId,version:1,isMaster:!0},formData:{}}),i=a(_({form:t.form,formData:t.formData,dataQueryDatasourceApiFormItemConfigsRef:r,dataQueryDatasourceApiFormItemBasicConfigsRef:u,addPublished:!0})),f=a({buttonText:"\u786E\u8BA4\u4FEE\u6539",permission:"admin:web:dataQueryDatasourceApi:update"}),c=()=>B,p=()=>b({id:o.dataQueryDatasourceApiId}),d=()=>"\u4FEE\u6539\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(D,v)=>{const l=A("PtForm");return C(),y(E,null,[s(" \u6DFB\u52A0\u8868\u5355 "),e(l,{form:t.form,formData:t.formData,labelWidth:"120",dataMethod:p,method:c(),methodSuccess:d,defaultButtonsShow:"submit,reset",submitAttrs:f.value,buttonsTeleportProps:D.$route.meta.formButtonsTeleportProps,inline:"",layout:[3,3,1,3,3,3,3,3],comps:i.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"]),e(Q,{form:t.form,formData:t.formData,ref_key:"dataQueryDatasourceApiFormItemConfigsRef",ref:r},null,8,["form","formData"]),s("\u57FA\u7840\u914D\u7F6E\uFF0C\u56E0\u4E3A\u4E0D\u540C\u7684\u6570\u636E\u6E90\uFF0C\u57FA\u7840\u914D\u7F6E\u4E0D\u540C\uFF0C\u8FD9\u91CC\u5355\u72EC\u62BD\u79BB\u51FA\u6765  "),e(h,{form:t.form,formData:t.formData,ref_key:"dataQueryDatasourceApiFormItemBasicConfigsRef",ref:u},null,8,["form","formData"])],64)}}}),x=g(P,[["__file","/Users/yw/fh/git-source/particle/web/component/pc/dataquery/views/datasource/admin/DataQueryDatasourceApiManageUpdatePage.vue"]]);export{x as default};