

Feign����Hystrix

    �������
    ��д�ӿ���ʵ�ֻ���
    
    ����������� ���������ɣ�spring-hy-member | spring-hy-sale | spring-hy-server
    Spring-hy-sale  
    pom.xml , application.yml ,HelloClient, FeignController,HelloClient,HelloClientFallBack
    TestMain 
    Spring-hy-member   MemberController , application.yml
    
    Spring-hy-dashboard  ���sale��
    http://localhost:7979/hystrix  �򿪺�����  http://localhost:8081/hystrix.stream 
    sale 8081����member
    pom.xml,DashBoardApp 8082,
    Localhost:8081/hystrix.stream  ����sale����dashborad�� 
    Localhost��8082 /hystrix �򿪿���̨�������� �����ͼ��������塣
    Ȼ���ȵ���һ�� localhost:8081/hello
    �ٵ��� TestMain�����Կ�����·������
     
     
���Զ�·��

    ���ó�ʱʱ��

     
Hystrix���

    �ͻ��˼���Actuator
    �½������Ŀ

