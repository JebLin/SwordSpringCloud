

Netflix���շ�����Hystrix���ÿ�ּ��ͨ��������Щ����Զ��ϵͳ������͵�������Ľڵ㣬�Ӷ����ӳٺ͹����ṩ��ǿ����ݴ�������Hystrix�߱�ӵ�л��˻��ƺͶ�·�����ܵ��̺߳��źŸ��룬���󻺴����������request collapsing�����Զ�����������ע�����Լ���غ����õȹ��ܡ�HystrixԴ��Netflix API�Ŷ���2011�������ĵ��Թ��̹�������Ŀǰ����Netflixÿ�촦���������ڵĸ����߳��Լ���ǧ�ڵĸ����źŵ��á�Hystrix�ǻ���Apache License 2.0Э��Ŀ�Դ�ĳ���⣬Ŀǰ�й���GitHub�ϡ�

���ӷֲ�ʽ�ܹ�ͨ�������кܶ����������һ��Ӧ�ò��ܶ����������Ĺ��Ͻ��и��룬�Ǹ�Ӧ�ñ���ʹ��ڱ��Ͽ�ķ����С���һ������������վ�У�ĳ����һ�ĺ��һ�������ӳ٣������������ڵ�������Ӧ����Դ���ľ���



Hystrix�������������ӳٺ͹��Ͻ��з����Ϳ��ơ�����Щ����ͨ������ͨ��������ʵġ�����������ֹ���ϵ�������Ӧ�������������ʧ�ܲ�Ѹ�ٻָ������߻��˲����Ž�����

���潫��ʾHystrix����ι����ġ�����Ҫ��HystrixCommand�����ж��������ý��а�װ��HystrixCommand��������ģʽ������ͨ��������һ���������߳���ִ�С���һ�ε��ú�ʱ������Ԥ�������ֵʱ��һ����ʱ�¼���������HystrixΪÿ��������ά����һ���̳߳أ��źţ�������̳߳ر��ľ������ܾ����󣨶������������Ŷӣ������ṩ��·����������ֹ���ж�ĳ���������󡣵�����ʧ�ܡ����ܾ�����ʱ���·ʱ����Ҳ�����ô���ʵ�ֻ��˵��߼���Hystrixͬ��֧�����󻺴����������

����HystrixCommandһ���򵥵�Hello Worldʵ�֡�

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
���ڶԱ�������ѵ�Ŀ�ģ�group��������ڶ�������з��顣����ͨ�����getFallback()ʵ�����ﵽ���Ž�����Ŀ�ģ��������͵Ĺ��϶����Դ���getFallback()�������쳣����ʱ���̳߳أ��źţ��ܾ��Ͷ�·����·��Hystrix���������execute()����ͬ����synchronously��ִ�С�

String s = new HelloWorldCommand().execute();
Hystrix����Ҳ������queue()�����첽��asynchronously��ִ�С�

java.util.concurrent.Future future = new HelloWorldCommand().queue();
String s = future.get();
Hystrixʹ�òձ�ģʽ��bulkhead pattern�����������������Ʋ������ʡ�ÿ������ʹ�ö������̳߳��Ա�֤������������Լ���ġ��ײ�ִ�е��ӳٽ�ֻ���ڶ�Ӧ�̳߳��кľ����еĿ����̡߳�ʹ���ź���ȡ���̳߳�Ҳ��һ��ѡ���������Խ��н��أ�load shedding�����ǳ�ʱ�����ʹ���̳߳ش���������һ��ʽ�����׵�������ۣ����һ���Ķ� Hystrix����������ι����ġ�

Hystrix�ṩ��һ����صĿ�����壬������Netflix�ڲ�ʹ�õ���һģһ���ġ� Hystrix��������ṩ�˽�ʵʱ�ļ�أ����ѺͲ������ơ�����ʾ�ɹ������ϣ��ɿͻ����׳����쳣������ʱ���ܾ߳̾����û����Զ�̬���޸����ã������ֶ���·ĳ��������

��Ҫ��ʼʹ��Hystrix�Ļ��������Hystrix���ĵ�http://github.com/Netflix/Hystrix/wiki���������������ָ�Ϻ�ʹ�÷���������Ҫ��װJava6����°汾��Java��Maven�û����Բ���Maven������com.netflix.hystrix hystrix-core���������Ϣ�����Ķ�Netflix API ���ܺ͹����ݴ�����Լ��ٷ���Hystrix FAQ����Ҫע������ڱ���׫дʱ��Hystrix���첽������֧����δ��ʵ�֡�