
1��Where does the name come from?

hystrix��Ӧ�����������ǡ������������������˴̣��ܱ����Լ�������е��˺���������һ�ַ������ƣ�����hystrix����Ĺ��ܲ�ı���ϣ����Netflix�Ŷӽ��ÿ������ΪHystrix����ʹ���˶�Ӧ�Ŀ�ͨ��������Ϊlogo��


׼����Ŀ
    ����ģ��(hystrix �����⣩
    ��Աģ��
    
    First-114 -> spring-hy-server
    First-police-> spring-hy-member 
        MemberApp��Member�� MemberController
    First-person -> spring-hy-sale
        pom.xml  Member MemberService  SaleApp TestContoller
    ȫ������������


����
    �½�������
    ʹ�û���ע��
ɾ������
    ��������key
    ʹ��ע��ɾ������
    
    ����һ����Ŀ
     spring-hy-sale  MemeberService MyFilter SaleAPP ��ע��
    CacheController ,CacheService
    CollService CollController
    
�ϲ�����
    �����ռ���

Feign����Hystrix
    �������
    ��д�ӿ���ʵ�ֻ���
    
    ����������� ���������ɣ�spring-hy-member | spring-hy-sale | spring-hy-server
    Spring-hy-sale  
    pom.xml , application.yml ,HelloClient, FeignController,HelloClient,HelloClientFallBack
    TestMain 
    Spring-hy-member   MemberController , application.yml
    
    Spring-hy-dashboard ���sale��sale 8081����member
    pom.xml,DashBoardApp 8082,
    Localhost:8081/hystrix.stream  ����sale����dashborad�� 
    Localhost��8082 /hystrix �򿪿���̨�������� �����ͼ��������塣
    Ȼ���ȵ���һ�� localhost:8081/hello
    �ٵ���TestMain�����Կ�����·������
    
 Hystrix���
    �ͻ��˼���Actuator
    �½������Ŀ
    
    
