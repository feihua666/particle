import{d as a,a as d,r as e,b as f,o as l,c as D,e as _,f as b,F,_ as A}from"./index.3ad8a272.js";import{j as g,k as h}from"./openplatformDocCompItem.87abe25b.js";import{u as B}from"./openplatformDocApiDocManage.1fa25741.js";import"./openplatformDocDirNameAdminApi.a3f0367b.js";import"./openplatformDocApiAdminApi.5762ebfa.js";import"./openplatformDocApiDocTemplateAdminApi.f24d2324.js";const P=a({name:"OpenplatformDocApiDocManageUpdatePage"}),v=a({...P,props:{openplatformDocApiDocId:{type:String}},setup(r){const o=r,t=d({form:{id:o.openplatformDocApiDocId,version:1},formData:{}}),s=e(B),p=e({buttonText:"\u786E\u8BA4\u4FEE\u6539",permission:"admin:web:openplatformDocApiDoc:update"}),m=()=>g,n=()=>h({id:o.openplatformDocApiDocId}),u=()=>"\u4FEE\u6539\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(c,E)=>{const i=f("PtForm");return l(),D(F,null,[_(" \u6DFB\u52A0\u8868\u5355 "),b(i,{form:t.form,formData:t.formData,labelWidth:"80",dataMethod:n,method:m(),methodSuccess:u,defaultButtonsShow:"submit,reset",submitAttrs:p.value,buttonsTeleportProps:c.$route.meta.formButtonsTeleportProps,inline:"",layout:[3,3,[8,8],[8,16],1,1,1,1,1],comps:s.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])],2112)}}}),T=A(v,[["__file","/Users/yw/fh/git-source/particle/web/component/pc/openplatform/views/doc/admin/OpenplatformDocApiDocManageUpdatePage.vue"]]);export{T as default};