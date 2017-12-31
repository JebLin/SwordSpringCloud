第一个Ribbon程序直接使用客户端发送请求
试用负载均衡器

	复制ribbon-client -> User-ribbon
	 pom.xml, LBMain,MyRule,TestMyRule ,TestRibbon
	启动
	改造 first-boot 项目中的FirstApp , Person,MyRestController
	启动两个服务器端8080 8081 （或者把启动文件拉倒另外的server paceage 包启动就好了。免得串项目）
	启动testRibbon

Ribbon内置的负载均衡规则
	RoundRobinRule 默认轮询
	AvailabilityFilteringRule 忽略服务器，
	1.三次连接失败 2. 并发数过高
	WeightedResponseTimeRule 权重
	ZoneAvoidanceRule 可用服务器处理
	BestAvailableRule 忽略短路服务器，选择并发数低的服务器
	RandomRule 随机选择一个可用的服务器
	RetryRule 重试机制的服务器
	
	NFLoadBalancerPingClassName  ping下是否存活
	NFLoadBalancerClassName    指定负载均衡器的实现类，可以自定义负载均衡器
	NIWSServerListClassName  服务器列表处理类，维护服务器列表
	NIWSServerListFilterClassName 服务器拦截类

	
 Ribbon 是 Netflix 发布的云中间层服务开源项目，其主要功能是提供客户侧软件负载均衡算法，将 Netflix 的中间层服务连接在一起。Eureka 是一个 RESTful 服务，用来定位运行在 AWS 域（Region）中的中间层服务。本文介绍 Eureka 和 Ribbon 的集成，附带 Ribbon 自定义负载均衡算法示例。
        Ribbon 和 Eureka 的集成，其实也就是让 Ribbon 充当 Eureka 架构中的 Application Client 角色。本文示例基于前边相关博客中的 demo 而写。
Why Eureka need Ribbon？
	Eureka 附带客户端库，为何还要 Ribbon 呢？
	Ribbon 的负载均衡算法、区域感知负载均衡器久经考验，可以直接拿来使用。
Why Ribbon need Eureka？
	熟悉 Ribbon 的同学都知道，Ribbon 维护了一个服务器列表，如果服务器有宕机现象，Ribbon 能够自行将其剔除；但如果该服务器故障排除，重新启动，或者增加新的负载节点，我们需要手工调用 Ribbon 的接口将其动态添加进 Ribbon 的服务器列表。这样明显不够尽如人意。如何能够在服务节点启动时，自行添加服务列表？—— Eureka。Eureka 提供了 Application Service 客户端的自行注册的功能。此外，Eureka 的缓存机制能够防止大规模宕机带来的灾难性后果。
