import{u as p,d as g}from"./oauth2RegisteredClientAdminApi-QBp5_aO7.js";import{a as h,c as b,b as C}from"./oauth2RegisteredClientManage-BiT0rd8w.js";import{_}from"./Oauth2RegisteredClientManageSettingConfigs.vue_vue_type_script_setup_true_name_Oauth2RegisteredClientManageSettingConfigs_lang-DmF1791W.js";import{d as v,r as o,a as D,b as R,o as F,c as P,e as n,F as S}from"./index-B_FSv1tI.js";const T=v({__name:"Oauth2RegisteredClientManageUpdatePage",props:{oauth2RegisteredClientId:{type:String}},setup(m){const a=o(null),r=m,e=D({form:{id:r.oauth2RegisteredClientId,version:1},formData:{}}),i=o(h({oauth2RegisteredClientManageSettingConfigsRef:a,isForAdd:!1})),u=o({buttonText:"确认修改",permission:"admin:web:oauth2RegisteredClient:update"}),d=t=>p(b(t)),l=()=>g({id:r.oauth2RegisteredClientId}).then(t=>{let s=t.data.data;return t.data.data=C(s),Promise.resolve(t)}),f=()=>"修改成功，请刷新数据查看";return(t,s)=>{const c=R("PtForm");return F(),P(S,null,[n(c,{form:e.form,formData:e.formData,labelWidth:"120",dataMethod:l,method:d,methodSuccess:f,defaultButtonsShow:"submit,reset",submitAttrs:u.value,buttonsTeleportProps:t.$route.meta.formButtonsTeleportProps,inline:"",layout:[2,1,1,1,1],comps:i.value},null,8,["form","formData","submitAttrs","buttonsTeleportProps","comps"]),n(_,{form:e.form,formData:e.formData,ref_key:"oauth2RegisteredClientManageSettingConfigsRef",ref:a},null,8,["form","formData"])],64)}}});export{T as default};