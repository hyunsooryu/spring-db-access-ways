package me.hyunsoo.aboutjdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * JDBC 란?
 *
 * DAO 패턴이란?
 *
 * Data Access Object Pattern
 *
 * 데이터 액세스 계층이란, DAO 패턴을 적용하여, 비즈니스 로직과, 데이터 액세스 로직을 분리하는 것을 목표로 한다.
 *
 * DAO 패턴은 기존 서비스 계층에 영향을 주지않고, 데이터 액세스 기술을 변경할 수 있다는 장점을 갖는다.
 *
 * 다중 사용이 필수적인 대규모 애플리케이션에서 DataSource의 활용은 필연적이다. - About-DBCP 참조
 * Connection Pooling을 지원하는 것이 -> DataSource 이다.
 *
 * DataSource의 종류
 *  1. SimpleDriverDataSource -> 스프링이 제공하는 가장 단순한 DataSource 구현체이다. getConnection()을 호출 할 때마다,
 *  매번 DB Connection을 새로만들고, 따로 Pool 관리는 하지 않으므로, 테스트에서만 활용해야 한다.
 *
 *  2. SingleConnectionDriverDataSource -> 순차적으로 진행되는 통합테스트에서 사용이 가능한 구현체이다.
 *  매번 DB Conncection을 생성하지는 않기 때문에, SimpleDriverDataSource 보다는 빠르게 동작한다.
 *
 *  About-DBCP 편에서는, Apache Commons DBCP2 를 심층적으로 살펴보았다.
 *
 *  JDBC란?
 *  JDBC 란 모든 자바 데이터 액세스 기술의 근간이 된다. 엔티티 클래스와 애노테이션을 활용하는 ORM 기술에서도
 *  내부적으로는 DB와의 연동을 위해 JDBC를 활용한다.
 *
 *  안정적이며, 유연한 기술이나, 로우 레벨 기술이며, 간단한 SQL 을 실행하는 데도 불필요하게 중복된 코드가 사용되며,
 *  DB에 따라 일관성 없는 정보를 가진채로, Checked Exception으로 처리하기도 한다.
 *
 *  Connection과 같은 공유 리소스를 제대로 릴리즈 해주지않으면 시스템 자원이 바닥이 나는 위험함도 갖는다.
 *
 *  JDBC에 의믜를 두는 것은 Portable Service Abstraction 의 대표적인 사례가 될 수 있기 때문이다.
 *
 *  각자 다른 벤더사에서 제공하는 Connector에도, 하나의 인터페이스로 동일하게 동작하게 된다.
 *
 *  개발자 -> Spring JDBC Template -> JDBC API -> JDBC DRIVER MANAGER -> DATABASE
 *
 *  Spring JDBC 는 JDBC를 한번더 Portable Service Abstraction 한 결과물
 *
 *  커넥션 관리부터 ,Transaction 까지 Spring JDBC 가 책임져준다. -> 실제로 @Transactional Annotation이 먹히는 것은 Spring-JDBC 일때부터,
 *
 *  이 Spring JDBC 를 활용하기 위해서는 DataSource 를 등록해야 한다.
 *
 *  Spring JDBC가 해주는 작업들
 *  1. Connection 열기와 닫기
 *      Connection과 관련된 모든 작업을 Spring JDBC가 필요 시점에러 알아서 진행해준다.
 *      진행중 예외가 발생했을 때도, 열린 모든 Connection 객체를 알아서 닫아준다.
 *  2. Statement 준비와 닫기
 *      SQL 정보가 담긴 Statement 혹은 PreparedStatement을 생성하고, 필요한 준비작업을
 *      해주는 것도 Spring JDBC이다.
 *      Statement도 Connection 과 마찬가지로, 사용이 끝나고 나면 Spring JDBC가 알아서
 *      객체를 닫아준다.
 *  3. Statement 실행
 *      SQL 담긴 Statement를 실행시켜주는 것도 Spring JDBC가 해준다.
 *  4. ResultSet Loop 처리
 *     ResultSet에 담긴 쿼리 실행 결과가 한건이상이면, ResultSet Loop를 알아서 만들어준다.
 *  5. Exception 처리와 반환
 *     JDBC 작업 중 발생하는 모든 예외는 Spring JDBC 예외 변환기가 처리한다.
 *     Checked Exception인 SQLException을 런타임 Exception인
 *     DataAccessException 타입으로 변환해준다.
 *  6. Transaction 처리
       Spring JDBC를 사용하면 transaction 과 관련된 모든 작업에 대해서는 신경을
       쓰지않아도 된다.
 */

@SpringBootApplication
public class AboutJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(AboutJdbcApplication.class, args);
    }

}
