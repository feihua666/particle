import{h as t}from"./index.3ad8a272.js";let o="/admin/web/ssq_code_opened";const r=e=>t.post(o+"/allCodeInit",e,{timeout:6e5}),s=e=>t.put(o+"/allCodeStop",e),n=e=>t.get(o+"/list",{params:e,timeout:6e5}),i=e=>t.get(o+"/page",{params:e,timeout:6e5}),p=e=>t.post(o+"/predictionParameterTuning",e,{timeout:6e5});export{r as a,s as b,p as c,n as l,i as p};