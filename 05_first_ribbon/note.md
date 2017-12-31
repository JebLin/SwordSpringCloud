

The first native ribbon

 Ribbon 是 Netflix 发布的云中间层服务开源项目，其主要功能是提供客户侧软件负载均衡算法，将 Netflix 的中间层服务连接在一起。Eureka 是一个 RESTful 服务，用来定位运行在 AWS 域（Region）中的中间层服务。
 Ribbon 和 Eureka 的集成，其实也就是让 Ribbon 充当 Eureka 架构中的 Application Client 角色。

Why Eureka need Ribbon？
	Eureka 附带客户端库，为何还要 Ribbon 呢？
	Ribbon 的负载均衡算法、区域感知负载均衡器久经考验，可以直接拿来使用。
	
Why Ribbon need Eureka？
	熟悉 Ribbon 的同学都知道，Ribbon 维护了一个服务器列表，如果服务器有宕机现象，Ribbon 能够自行将其剔除；但如果该服务器故障排除，重新启动，或者增加新的负载节点，我们需要手工调用 Ribbon 的接口将其动态添加进 Ribbon 的服务器列表。这样明显不够尽如人意。如何能够在服务节点启动时，自行添加服务列表？—— Eureka。Eureka 提供了 Application Service 客户端的自行注册的功能。此外，Eureka 的缓存机制能够防止大规模宕机带来的灾难性后果。	
	
---

负载均衡框架，支持可插拔式的负载均衡规则
支持多种协议，如HTTP、UDP等
提供负载均衡客户端，客户端的负载均衡器，是用在invoker端的

ribbon-core
ribbon-eureka
ribbon-httpclient

ribbon-core负载均衡器，至少提供以下功能：
要维护各个服务器的IP等信息
根据特定逻辑选取服务器
为了实现基本的负载均衡功能，Ribbon的负载均衡器有三大子模块：
Rule
Ping
ServerList


编写服务
编写客户端
ribbon-service
Pom.xml, ServiceApp,Person,MyController
ribbon-client
Pom.xml ,  TestRibbon | my-ribbon.properties
Github.com/netflix/ribbon

轮询算法，如果没有使用spring cloud 又想使用负载均衡的话，可以在老项目中加入ribbon 框架

配置格式：client.nameSpace.property=values
客户端名称.命名空间.属性 = 属性值
