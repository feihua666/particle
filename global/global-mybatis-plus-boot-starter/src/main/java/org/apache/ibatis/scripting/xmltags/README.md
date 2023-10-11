# 重写一下mybatis动态sql源处理逻辑

旨在支持动态传入以<script>标签的动态sql

动态sql node解析是在mybatis启动时已经处理完成的，如果想要动态传入，只能动态修改其SqlNode  
这里重写一下 DynamicSqlSource类（参见 41-48行修改），配合 DynamicSqlSourceHelperTool完成动态替换，在使用上并没有限制  
这种能力增强后可以支持<script></script>动态传sql，就像使用原生的在如@Select一样如下：  
原生方式：
```
    @Select("<script>"+"select * from component_func " +
								"<where>" +
								"<if test=\"data.data.name != null\"> name = #{data.data.name}</if>" +
								"</where>" + "</script>")
    Map<String,Object> selectOne(@Param("data") Object data);
```
动态传参：
```xml
    @Select(paramSQLExpressin)
    Map<String,Object> selectOne(@Param(paramSQL) String sql,@Param("data") Object data);
```
sql 传值如下：
```
<script> select * from component_func  
<where> 
<if test=data.data.name != null>
    name = #{data.data.name}
</if> 
</where>
</script>
```
修改版本 3.5.13
修改类 org.apache.ibatis.scripting.xmltags.DynamicSqlSource 46行左右