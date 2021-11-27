package me.hyunsoo.aboutdbcp;

import java.sql.*;
/**
 * Statement 와 PrepareStatement의 차이점
 *
 * PrepareStatement
 * 데이터 베이스 관리 시스템에서 동일하거나, 비슷한 데이터베이스 문을 높은 효율성으로
 * 반복적으로 실행하기 위해 사용되는 기능을 말합니다.
 *
 * PreparedStatment는
 * 1. 준비 (Prepare) : 먼저 애플리케이션은 문의 틀을 만들고, 이를 DBMS로 보내게 됩니다.
 * 특정 값은 지정하지 않은 채로 남겨집니다.
 * INSERT INTO PRD (name, age) VALUES (?, ?)
 *
 * 2.이 후, DBMS는 문의 틀을 컴파일하며(최적화 및 변환) 아직 실행하지 않고 결과만 저장합니다.
 *
 * 3.실행 (Execute) : 나중에 애플리케이션이 문 틀의 변수에 값(바인드)을 지정하면 DBMS 는
 * (결과를 변활할 수 도 있는) 문을 실행합니다. 애플리케이션은 여러 값으로 원하는 횟수만큼 문을
 * 실핼할 수 있습니다.
 *
 * 결국 Statement vs PreparedStatement의 큰 차이는
 * Cache 사용 여부입니다.
 *
 * Statement를 사용하면 매번 쿼리를 수행할 때마다,
 * 4단계를 거치게 되고, PreparedStatement는
 * 처음 한번만 3단계를 거친 후 캐시에 담아 재사용합니다.
 *
 * 만약 동일한 쿼리를 반복적으로 수행한다면,
 * PreparedStatement가 DB에 훨씬 적은 부화를 주며, 성능도 좋습니다.
 *
 * Statement는 SQL 문을 수행하는 과정에서 매번 컴파일을 하기 때문에,
 * PreparedStatement에 비해 효율성이 떨어지게 됩니다.
 *
 *
 *
 * PreparedStatement 와 Statement의 가장 큰 차이점은 캐시(cache) 사용여부이다.
 1) 쿼리 문장 분석
 2) 컴파일
 3) 실행
 Statement를 사용하면 매번 쿼리를 수행할 때마다 1) ~ 3) 단계를 거치게 되고, PreparedStatement는 처음 한 번만 세 단계를 거친 후 캐시에 담아 재사용을 한다는 것이다. 만약 동일한 쿼리를 반복적으로 수행한다면 PreparedStatment가 DB에 훨씬 적은 부하를 주며, 성능도 좋다.



 Commons DBCP 속성 설정

 Commons DBCP 의 속성은 BasicDataSource 클래스의 세터 메서드로 설정할 수 있다.
 Spring Framework 프레임워크를 사용한다면, 다음과 같이 bean 설정으로 속성을 등록한다.

 <bean id="dataSource" class="org.apache.commons.dbcp2.BasicDataSource"
 destroy-method="close"
 p:driverClassName="${db.driverClassName }"
 p:url="${db.url}"
 p:username="${db.username}"
 p:password="${db.password}"
 p:maxTotal="${db.maxTotal}"
 p:maxIdle="${db.maxIdle}"
 p:maxWaitMillis="${db.maxWaitMills}""
 />

 CursarableLinkedList <LIFO>

 initialSize - 초기 커넥션 생성의 수
 maxActive = maxTotal - 동시에 사용할 수 있는 커넥션의 수
 maxIdle = 커넥션 풀에 반납할 때, 최대로 유지될 수 있는 커넥션 수
 minIdle = 최소한으로 유지되는 커넥션 수

 maxActive값은 DBMS

 BasicDataSource 의 maxWait은
 기본적으로 무한정
 커넥션 풀 안에 커넥션이 고갈 되었을 때, 커넥션 반납을 대기하는
 시간이며, 기본적으로 무한정. maxWait 속성을 적절하게 설정하지 않아도
 일반적인 상황에서는 큰 문제가 되지 않는다.
 하지만 사용자가 갑자기 급증하거나 DBMS에 장애가 발생했을 때
 장애를 더욱 크게 확산시킬 수 있어 주의해야 한다.

 적절한 maxWait 값을 설정하려면 TPS(transaction per seconds)와
 Tomcat에서 처리 가능한 스레드 개수 등을 이해해야 한다.
 예를 들어 자세히 살펴보겠다.

 TPS -> Transaction Per Seconds
 maxActive = 5
 maxIdle = 5
 minIdle = 5

 1번의 request에 실행 쿼리 수 10개 / 개당 50ms
 = 총 1 request 당 500ms = 0.5 초 소요

 동시에 5개의 요청 처리가능

 1초에 최대 몇개의 트랜잭션이 가능하냐? 가 바로 TPS

 0.5초에 5개의 리퀘스트 처리가능

 1초에는 10개의 리퀘스트 처리가능

 즉 TPS = 10

 동시에 10개의 요청이 처리가능하다면?

 0.5초에 10개의 리퀘스트 처리가능하니,

 1초에는 20개의 리퀘스트 처리가능

 즉 TPS = 20

 Tomcat 도 Thread Pool 을 사용하니,
 Tomcat에서 사용자의 연결을 처리하는 최대의 스레드 개수는 server.xml 파일에서 maxThread 속성으로 지정한다.
 Tomcat - DBCP - DB
  1 thread per 1 request

 심각: All threads (512) are currently busy, waiting.
 Increase maxThreads (512) or check the servlet status

 더욱 억울한 것은 결국 10초 동안의 대기 상태가 해제되고 커넥션을 획득해 사용자의 요청을 열심히 처리하고 응답을 보내도 그 응답을 받을 사용자는 이미 떠나고 난 뒤라는 점이다. 클릭 후 2~3초 내에 반응이 없으면 페이지를 새로 고치거나 다른 페이지로 이동하는 것이 보통인 인터넷 사용자의 행동을 생각하면 쉽게 이해되리라.
 결국은 기다리는 사람도 없는 요청에 응답하기 위해 자원을 낭비한 셈이 된다. 사용자가 인내할 수 있는 시간을 넘어서는 maxWait 값은 아무런 의미가 없다.


 그럼 반대로 너무 작게 설정하면 어떤 문제가 발생할까? 상상대로다. 과부하 시 커넥션 풀에 여분의 커넥션이 없을 때마다
 오류가 반환될 것이고 사용자는 너무 자주 오류 메시지를 볼 것이다.

 이렇듯 maxWait 값도 사용자의 대기 가능한 시간 같은,
 애플리케이션의 특성과 다른 주변의 설정,
 자원의 상황 등을 고려해 판단해야 한다.
 만약 갑작스럽게 사용자가 증가해 maxWait 값 안에 커넥션을 얻지 못하는 빈도가 늘어난다면 maxWait 값을 더 줄여서 시스템에서 사용하는
 스레드가 한도에 도달하지 않도록 방어할 수 있다. 전체 시스템 장해는 피하고 '간헐적 오류'가 발생하는 정도로 장애의 영향을 줄이는 것이다.
 이런 상황이 자주 있다면 Commons DBCP의 maxActive 값과 Tomcat의 maxThread 값을 동시에 늘이는 것을 고려한다.
 그러나 시스템 자원의 한도를 많이 넘는 요청이 있다면 설정을 어떻게 변해도 장애를 피할 수 없다. 애플리케이션 서버의 자원이
 설정 변경을 수용할 만큼 충분하지 않다면 시스템을 확충해야 할 것이다.
 */

public class JdbcRunner {

    public static void main(String[] args) throws ClassNotFoundException{

        String username = "hyunsooryu";
        String password = "pass";
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/springboot?useSSL=false";

        Class.forName(driver);

        try(Connection con = DriverManager.getConnection(url, username, password)){
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM USER WHERE ID = ? AND NAME = ?");
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, "HYUNSOO");
           // Statement statement = con.createStatement();
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                System.out.println("ID : " + resultSet.getInt("ID"));
                System.out.println("name : " + resultSet.getString("name"));
            }
        }catch (Exception e){

        }

    }
}
