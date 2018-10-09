# JDBC - Statement vs PrepareStatement


- Statement适用于静态sql
- PrepareStatement适用与参数化查询
- CallableStatement用于存储过程

上述都是用来与数据库执行SQL接口，通过数据库Connection创建

### PrepareStatement优势

- PrepareStatement可以支持动态参数化查询
- SQL语句会预编译在数据库系统中。执行计划同样会被缓存起来，它允许数据库做参数化查询。使用预处理语句比普通的查询更快，因为它做的工作更少（数据库对SQL语句的分析，编译，优化已经在第一次查询前完成了）。为了减少数据库的负载，生产环境中德JDBC代码你应该总是使用PreparedStatement 。
- PreparedStatement可以防止SQL注入式攻击：

***
1.PreparedStatement实现类的setString();方法内部做了单引号的转义，而Statement不能防止sql注入，就是因为它没有把单引号做转义，而是简单粗暴的直接拼接字符串，所以达不到防止sql注入的目的
2. PrepareStatement使用预编译，这个步骤是在con.prepareStatement（“”）语句执行的时候，服务器就这个sql发送给了数据库，然后数据库将该sql编译后放入到缓存池中。等到服务器执行execute的时候，传给数据库的 ，` 张三‘ or '1=1 `，编译器并不进行编译，而是这找到原来的sql模板，传参，执行。
***


### mysql’#‘与’$‘区别
- #{ } 解析为一个 JDBC 预编译语句（prepared statement）的参数标记符。
- ${ } 仅仅为一个纯碎的 string 替换，在动态 SQL 解析阶段将会进行变量替换

