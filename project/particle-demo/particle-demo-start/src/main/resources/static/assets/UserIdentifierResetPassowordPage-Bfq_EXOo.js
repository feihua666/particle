import{i as p}from"./userIdentifierPwdAdminApi-BXp1IRGD.js";import{r as u}from"./userCompItem-BIkuEm_h.js";import{d,a as f,r as e,b as c,o as l,g as b}from"./index-BGwvwH0Y.js";import"./userAdminApi-mLq-OWKq.js";const D=d({__name:"UserIdentifierResetPassowordPage",props:{identifierId:{type:String}},setup(o){const t=f({form:{userIdentifierId:o.identifierId},formData:{}}),r=e(u),s=e({buttonText:"确认重置",permission:"admin:web:userIdentifierPwd:identifier:resetPassword"}),m=()=>p,i=()=>"密码重置成功";return(n,h)=>{const a=c("PtForm");return l(),b(a,{form:t.form,formData:t.formData,labelWidth:"80",method:m(),methodSuccess:i,defaultButtonsShow:"submit,reset",submitAttrs:s.value,buttonsTeleportProps:n.$route.meta.formButtonsTeleportProps,inline:"",layout:[1,2],comps:r.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{D as default};