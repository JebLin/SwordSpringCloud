
1、Where does the name come from?

hystrix对应的中文名字是“豪猪”，豪猪周身长满了刺，能保护自己不受天敌的伤害，代表了一种防御机制，这与hystrix本身的功能不谋而合，因此Netflix团队将该框架命名为Hystrix，并使用了对应的卡通形象做作为logo。


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
