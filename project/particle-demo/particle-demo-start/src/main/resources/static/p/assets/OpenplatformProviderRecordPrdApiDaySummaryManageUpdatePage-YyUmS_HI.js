import{u as i,d as c,e as f}from"./openplatformProviderRecordPrdApiDaySummaryManage-DumojtEE.js";import{d as l,a as P,r,b,o as v,g as y}from"./index-B_FSv1tI.js";const A=l({__name:"OpenplatformProviderRecordPrdApiDaySummaryManageUpdatePage",props:{openplatformProviderRecordPrdApiDaySummaryId:{type:String}},setup(e){const o=e,t=P({form:{id:o.openplatformProviderRecordPrdApiDaySummaryId,version:1},formData:{}}),a=r(i),m=r({buttonText:"确认修改",permission:"admin:web:openplatformProviderRecordPrdApiDaySummary:update"}),s=()=>c,p=()=>f({id:o.openplatformProviderRecordPrdApiDaySummaryId}),n=()=>"修改成功，请刷新数据查看";return(d,D)=>{const u=b("PtForm");return v(),y(u,{form:t.form,formData:t.formData,labelWidth:"80",dataMethod:p,method:s(),methodSuccess:n,defaultButtonsShow:"submit,reset",submitAttrs:m.value,buttonsTeleportProps:d.$route.meta.formButtonsTeleportProps,inline:"",comps:a.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{A as default};