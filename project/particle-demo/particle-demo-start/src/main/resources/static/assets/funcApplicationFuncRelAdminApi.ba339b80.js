import{g as e}from"./index.661e0f85.js";let t="/admin/web/func_application_func_rel";const a=n=>e.post(t+"/create",n),s=n=>e.delete(t+"/delete",{data:n}),u=n=>e.get(t+"/page",{params:n}),p=n=>e.post(t+"/funcApplication/assign/func",n),r=n=>e.get(t+"/queryFuncIdsByFuncApplicationId",{params:n}),i=n=>e.delete(t+"/deleteByFuncApplicationId",{data:n}),o=n=>e.post(t+"/func/assign/funcApplication",n),l=n=>e.get(t+"/queryFuncApplicationIdsByFuncId",{params:n}),d=n=>e.delete(t+"/deleteByFuncId",{data:n});export{r as a,p as b,a as c,d,i as e,o as f,u as p,l as q,s as r};