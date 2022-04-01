步骤:
1.新建maven项目
2.加入maven的依赖
    1 ) spring依赖
    2 ) mybatis依赖
    3 ) mysql驱动
    4 ) spring的事务的依赖
    5 ) mybatis和spring集成的依赖:mybatis官方体用的，用来在spring项目中创建mybatis
        的sqlsesissonFactory , dao对象的
3.创建实体类
4.创建dao接口和mapper文件
5.创建mybatis主配置文件
6.创建service接口和实现类，属性是dao。
7.创建spring的配置文件:声明mybatis的对象交给spring创建
    1)数据源
    2)sqlsessionFactory
    3)Dao对象
    4)Service对象
