import{b,d as _,e as g}from"./areaManage-D-ZZlBsg.js";import{_ as h}from"./LocationGeoMapDialog.vue_vue_type_script_setup_true_name_LocationGeoMapDialog_lang-BUDwUVqI.js";import{d as D,r as o,a as P,b as v,o as F,c as M,e as m,F as S}from"./index-D8BtRcSm.js";import"./treeQueryComps-CWIG2rsy.js";const k=D({__name:"AreaManageUpdatePage",props:{areaId:{type:String}},setup(i){const a=o(null),r=i,t=P({form:{id:r.areaId,version:1},formData:{}}),u=o(b({locationGeoMapDialogRef:a})),p=o({buttonText:"确认修改",permission:"admin:web:area:update"}),l=()=>_,c=()=>g({id:r.areaId}),d=()=>"修改成功，请刷新数据查看",f=({str:s,longitude:n,latitude:e})=>{t.form.longitude=n+"",t.form.latitude=e+""};return(s,n)=>{const e=v("PtForm");return F(),M(S,null,[m(e,{form:t.form,formData:t.formData,labelWidth:"80",dataMethod:c,method:l(),methodSuccess:d,defaultButtonsShow:"submit,reset",submitAttrs:p.value,buttonsTeleportProps:s.$route.meta.formButtonsTeleportProps,inline:"",comps:u.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"]),m(h,{ref_key:"locationGeoMapDialogRef",ref:a,submit:f,point:[t.form.longitude,t.form.latitude]},null,8,["point"])],64)}}});export{k as default};