

Netflix近日发布了Hystrix，该库旨在通过控制那些访问远程系统、服务和第三方库的节点，从而对延迟和故障提供更强大的容错能力。Hystrix具备拥有回退机制和断路器功能的线程和信号隔离，请求缓存和请求打包（request collapsing，即自动批处理，译者注），以及监控和配置等功能。Hystrix源于Netflix API团队在2011年启动的弹性工程工作，而目前它在Netflix每天处理着数百亿的隔离线程以及数千亿的隔离信号调用。Hystrix是基于Apache License 2.0协议的开源的程序库，目前托管在GitHub上。

复杂分布式架构通常都具有很多依赖。如果一个应用不能对来自依赖的故障进行隔离，那该应用本身就处在被拖垮的风险中。在一个高流量的网站中，某个单一的后端一旦发生延迟，将会在数秒内导致所有应用资源被耗尽。



Hystrix对来自依赖的延迟和故障进行防护和控制——这些依赖通常都是通过网络访问的。这样可以阻止故障的连锁反应，并允许你快速失败并迅速恢复，或者回退并优雅降级。

下面将显示Hystrix是如何工作的。你需要在HystrixCommand对象中对依赖调用进行包装。HystrixCommand遵照命令模式，而且通常都是在一个单独的线程中执行。当一次调用耗时超过了预定义的阈值时，一个超时事件将发生。Hystrix为每个依赖都维护着一个线程池（信号），如果线程池被耗尽它将拒绝请求（而不是让请求排队）。它提供断路器功能以阻止所有对某依赖的请求。当请求失败、被拒绝、超时或短路时，你也可以用代码实现回退的逻辑。Hystrix同样支持请求缓存和请求打包。

这是HystrixCommand一个简单的Hello World实现。

public class HelloWorldCommand extends HystrixCommand {
    public HelloWorldCommand() {
        super(HystrixCommandGroupKey.Factory.asKey("MyGroup"));
    }

    @Override
    protected String run() {
        return "Hello World";
    }

    @Override
    protected String getFallback() {
        return "Hello Fallback";
    }
}
出于对报告和提醒的目的，group这个键用于对命令进行分组。可以通过添加getFallback()实现来达到优雅降级的目的，所有类型的故障都可以触发getFallback()，比如异常，超时，线程池（信号）拒绝和断路器短路。Hystrix命令可以用execute()方法同步（synchronously）执行。

String s = new HelloWorldCommand().execute();
Hystrix命令也可以用queue()方法异步（asynchronously）执行。

java.util.concurrent.Future future = new HelloWorldCommand().queue();
String s = future.get();
Hystrix使用舱壁模式（bulkhead pattern）来隔离依赖和限制并发访问。每个依赖使用独立的线程池以保证并发请求是受约束的。底层执行的延迟将只会在对应线程池中耗尽所有的可用线程。使用信号来取代线程池也是一种选择，这样可以进行降载（load shedding）而非超时。针对使用线程池处理依赖这一方式的利弊的深度讨论，请进一步阅读 Hystrix隔离性是如何工作的。

Hystrix提供了一个监控的控制面板，该面板和Netflix内部使用的是一模一样的。 Hystrix控制面板提供了近实时的监控，提醒和操作控制。它显示成功，故障（由客户端抛出的异常），超时和线程拒绝。用户可以动态的修改配置，比如手动短路某个依赖。

想要开始使用Hystrix的话，请访问Hystrix的文档http://github.com/Netflix/Hystrix/wiki，这里包含了入门指南和使用方法。你需要安装Java6或更新版本的Java。Maven用户可以查找Maven工件：com.netflix.hystrix hystrix-core。更多的信息，请阅读Netflix API 性能和故障容错介绍以及官方的Hystrix FAQ。需要注意的是在本文撰写时，Hystrix对异步依赖的支持尚未被实现。