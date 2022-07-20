# 笔记
1. mybatis的@Mapper注解，如果在依赖jar包中使用，依然不起作用（不是在jar中使用，是可以不用添加@MapperScan注解的），需要添加@MapperScan注解指定路径  
2. 关于json序列化问题，小写boolean和包装类型Boolean的使用
   关键问题在于根据javaBean规范boolean类型使用isXxxx和setXxxx,阿里规范不建议用is开头的数据库字段。  
   因为在生成对应的实体时，如果使用小写boolean类型，在json框架一般是根据get方法获取字段（Gson除外，没有这个问题），如果为小写boolean类型isDelete,则get方法和字段名会相同，导致区分不开真正的字段。  
   本项目中仍然使用is开关的数据库字段，因为实体都使用包装类型Boolean，其get方法不是is开关，并无此问题，方便前端判断类型。本项目一般局部变量用小写boolean，
   对象属性用大写包装类型Boolean。