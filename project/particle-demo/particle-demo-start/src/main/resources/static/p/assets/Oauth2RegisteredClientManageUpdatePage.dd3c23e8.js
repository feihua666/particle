import{d as u,r as a,a as g,b as h,o as C,c as b,e as F,f as n,F as _,_ as v}from"./index.49d89bd8.js";import{u as R,d as D}from"./oauth2RegisteredClientAdminApi.a64c7859.js";import{a as P,c as B,b as M}from"./oauth2RegisteredClientManage.8df4b17b.js";import{O as E}from"./Oauth2RegisteredClientManageSettingConfigs.b4db2d9a.js";const S=u({name:"AreaManageUpdatePage"}),A=u({...S,props:{oauth2RegisteredClientId:{type:String}},setup(i){const o=i,r=a(null),e=g({form:{id:o.oauth2RegisteredClientId,version:1},formData:{}}),m=a(P({oauth2RegisteredClientManageSettingConfigsRef:r,isForAdd:!1})),d=a({buttonText:"\u786E\u8BA4\u4FEE\u6539",permission:"admin:web:oauth2RegisteredClient:update"}),l=t=>R(B(t)),c=()=>D({id:o.oauth2RegisteredClientId}).then(t=>{let s=t.data.data;return t.data.data=M(s),Promise.resolve(t)}),f=()=>"\u4FEE\u6539\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(t,s)=>{const p=h("PtForm");return C(),b(_,null,[F(" \u6DFB\u52A0\u8868\u5355 "),n(p,{form:e.form,formData:e.formData,labelWidth:"120",dataMethod:c,method:l,methodSuccess:f,defaultButtonsShow:"submit,reset",submitAttrs:d.value,buttonsTeleportProps:t.$route.meta.formButtonsTeleportProps,inline:"",layout:[2,1,1,1,1],comps:m.value},null,8,["form","formData","submitAttrs","buttonsTeleportProps","comps"]),n(E,{form:e.form,formData:e.formData,ref_key:"oauth2RegisteredClientManageSettingConfigsRef",ref:r},null,8,["form","formData"])],64)}}}),O=v(A,[["__file","/Users/yw/fh/git-source/particle/web/component/pc/oauth2authorization/views/client/admin/Oauth2RegisteredClientManageUpdatePage.vue"]]);export{O as default};