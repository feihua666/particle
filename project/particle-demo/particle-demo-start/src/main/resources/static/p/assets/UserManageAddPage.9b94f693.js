import{d as o,a as m,r as s,l as c,b as d,o as p,c as l,e as f,f as _,u as b,m as F,F as B,n as A,_ as D}from"./index.49d89bd8.js";const g=o({name:"UserManageAddPage"}),h=o({...g,setup(P){const t=m({form:{},formData:{}}),a=s(c.filter(e=>!!e)),r=s({buttonText:"\u786E\u8BA4\u6DFB\u52A0",permission:"admin:web:user:create"}),u=e=>(e.identifiers=[],e.identifiers.push({identifier:e.identifier,identityTypeDictId:e.identityTypeDictId}),A(e)),n=()=>"\u6DFB\u52A0\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(e,v)=>{const i=d("PtForm");return p(),l(B,null,[f(" \u6DFB\u52A0\u8868\u5355 "),_(i,{form:t.form,formData:t.formData,labelWidth:"80",method:u,methodSuccess:n,defaultButtonsShow:"submit,reset",submitAttrs:r.value,buttonsTeleportProps:e.$route.meta.formButtonsTeleportProps,inline:"",layout:[1,3,3,3,3,b(F)("dept")?3:2],comps:a.value},null,8,["form","formData","submitAttrs","buttonsTeleportProps","layout","comps"])],2112)}}}),T=D(h,[["__file","/Users/yw/fh/git-source/particle/web/component/pc/user/views/admin/UserManageAddPage.vue"]]);export{T as default};