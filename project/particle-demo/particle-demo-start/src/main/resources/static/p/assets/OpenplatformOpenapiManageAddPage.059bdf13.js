import{d as a,a as i,r,b as c,o as f,c as l,e as d,f as _,F as b,a6 as F,_ as P}from"./index.49d89bd8.js";import{c as B}from"./openplatformOpenapiCompItem.3f3d378b.js";import{a as v}from"./openplatformOpenapiManage.f59be60d.js";import"./openplatformOpenapiFeeCompItem.bf832c0b.js";import"./openplatformOpenapiFeeAdminApi.419b5c24.js";import"./treeQueryComps.5854cd36.js";import"./openplatformProviderCompItem.2d08c002.js";import"./openplatformProviderAdminApi.b827b019.js";const A=a({name:"OpenplatformOpenapiManageAddPage"}),g=a({...A,setup(h){const t=i({form:{},formData:{}}),s=r(v),m=r({buttonText:"\u786E\u8BA4\u6DFB\u52A0",permission:"admin:web:openplatformOpenapi:create"}),n=o=>{let e=F(o);return e.openplatformProviderIds&&(e.openplatformProviderIds=e.openplatformProviderIds.join(",")),B(e)},p=()=>"\u6DFB\u52A0\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(o,e)=>{const u=c("PtForm");return f(),l(b,null,[d(" \u6DFB\u52A0\u8868\u5355 "),_(u,{form:t.form,formData:t.formData,labelWidth:"120",method:n,methodSuccess:p,defaultButtonsShow:"submit,reset",submitAttrs:m.value,buttonsTeleportProps:o.$route.meta.formButtonsTeleportProps,inline:"",layout:[3,1],comps:s.value},null,8,["form","formData","submitAttrs","buttonsTeleportProps","comps"])],2112)}}}),x=P(g,[["__file","/Users/yw/fh/git-source/particle/web/component/pc/openplatform/views/openapi/admin/OpenplatformOpenapiManageAddPage.vue"]]);export{x as default};