
准备项目
    销售模块(hystrix 放在这）
    会员模块
    
    First-114 -> spring-hy-server
    First-police-> spring-hy-member 
        MemberApp、Member、 MemberController
    First-person -> spring-hy-sale
        pom.xml  Member MemberService  SaleApp TestContoller
    全部启动跑起来


缓存
    新建过滤器
    使用缓存注解
删除缓存
    配置命令key
    使用注解删除缓存
    
    在上一个项目
     spring-hy-sale  MemeberService MyFilter SaleAPP 加注解
    CacheController ,CacheService
    CollService CollController
    
合并请求
    请求收集器
