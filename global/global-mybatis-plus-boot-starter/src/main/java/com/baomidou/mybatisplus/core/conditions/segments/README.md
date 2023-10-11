#说明
需求是这样的，在同时使用 query实体和wrapper查询时，实体与wrapper两个部分之间是使用and连接的，并且wrapper部分是添加了括号包裹。参见：issue：https://github.com/baomidou/mybatis-plus/issues/3418 

如果在wrapper里使用or，其作用域总是提不出来，这导致在生成的sql总是被括号包裹，导致结果不正确
经调试发现可以以最小的改动修改这里解决，解决办法就是直接去掉括号，但这可能会引起其它问题，

发现在嵌套时并没有添加括号，所以替换了 AbstractWrapper类，并在嵌套中添加了控制，暂没发现其它问题，可正常使用  

改动版本 4.5.3.2
修改部分详见：
com.baomidou.mybatisplus.core.conditions.segments.NormalSegmentList.childrenSqlSegment 90 行
com.baomidou.mybatisplus.core.conditions.AbstractWrapper.nested 457 行左右