import{i as e}from"./index-_8rL1G8m.js";let n="/admin/web/tenant_func_application";const r=t=>e.post(n+"/create",t),p=t=>e.delete(n+"/delete",{data:t}),s=t=>e.put(n+"/update",t),c=t=>e.get(n+"/detail-for-update",{params:t}),o=t=>e.get(n+"/page",{params:t}),i=t=>e.post(n+"/tenant/assign/funcApplication",t),u=t=>e.get(n+"/queryFuncApplicationIdsByTenantId",{params:t});export{r as c,c as d,o as p,u as q,p as r,i as t,s as u};