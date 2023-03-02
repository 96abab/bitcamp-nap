package bitcamp.myapp.config;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


//@Configuration

// Spring IoC 컨테이너가 자동 생성할 클래스를 찾을 수 있도록 패키지를 지정한다.
@ComponentScan("bitcamp.myapp")

// JDBC 설정 정보를 담고 있는 .properties 파일을 로딩한다
@PropertySource("classpath:/bitcamp/myapp/config/jdbc.properties")

// Mybatis-Spring 라이브러리에 있는 클래스를 사용 DAO 구현체 사용하기
@MapperScan("bitcamp.myapp.dao")

// @Transactional 애노테이션으로 트랜잭션을 제어하려면 추가해야한다
@EnableTransactionManagement
public class AppConfig {

  //
  // 시스템 property 값 가져오기
  //  @Autowired Environment env;
  // DB 커넥션풀 객체 준비
  //  @Bean
  //  public DataSource dataSource() {
  //    DriverManagerDataSource ds =  new DriverManagerDataSource();
  //    ds.setDriverClassName(env.getProperty("jdbc.driver"));
  //    ds.setUrl(env.getProperty("jdbc.url"));
  //    ds.setUsername(env.getProperty("jdbc.username"));
  //    ds.setPassword(env.getProperty("jdbc.password"));
  //    return ds;
  //  }
  //
  // .propertis 파일에 있는 값 낱개로 주입받기
  //  @Value("${jdbc.driver}") String jdbcDriver;
  //  @Value("${jdbc.url}") String url;
  //  @Value("${jdbc.username}") String username;
  //  @Value("${jdbc.password}") String password;
  // DB 커넥션풀 객체 준비
  //  @Bean
  //  public DataSource dataSource() {
  //    DriverManagerDataSource ds =  new DriverManagerDataSource();
  //    ds.setDriverClassName(env.getProperty("jdbc.driver"));
  //    ds.setUrl(env.getProperty("jdbc.url"));
  //    ds.setUsername(env.getProperty("jdbc.username"));
  //    ds.setPassword(env.getProperty("jdbc.password"));
  //    return ds;
  //  }

  @Bean
  public DataSource dataSource(
      @Value("${jdbc.driver}") String jdbcDriver,
      @Value("${jdbc.url}") String url,
      @Value("${jdbc.username}") String username,
      @Value("${jdbc.password}") String password
      ) {
    DriverManagerDataSource ds =  new DriverManagerDataSource();
    ds.setDriverClassName(jdbcDriver);
    ds.setUrl(url);
    ds.setUsername(username);
    ds.setPassword(password);
    return ds;
  }

  @Bean
  public PlatformTransactionManager transactionManager(DataSource dataSource) throws Exception {
    System.out.println("PlatformTransactionManager 객체 생성! ");
    return new DataSourceTransactionManager(dataSource);
  }

  @Bean
  public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ApplicationContext appCtx) throws Exception {
    System.out.println("SqlSessionFactory 객체 생성!");
    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
    factoryBean.setDataSource(dataSource);
    factoryBean.setTypeAliasesPackage("bitcamp.myapp.vo");
    factoryBean.setMapperLocations(appCtx.getResources("classpath*:bitcamp/myapp/mapper/*Mapper.xml"));
    return factoryBean.getObject();
  }


  // Servlet3.0의 멀티파트 요청데이터를 처리하려면
  // 1) 서블릿에 멀티파트 파일 처리 설정정보를 담은 MultipartConfigElement를 등록
  // 2) 스프링 WebMVC에는 Servlet3.0 API를 이용 MultipartData를 처리후
  //    페이지 컨트롤러에게 값들을 전달해주는 StandardServletMultipartResolver를 등록
  // 주의점
  //  MultipartResolver 구현체 bean 이름은 "multipartResolver" 여야 한다
  @Bean
  public MultipartResolver multipartResolver () {
    return new StandardServletMultipartResolver();
  }

  // Apache commons-fileupload 라이브러리로 멀티파트 요청데이터를 처리하려면
  // 1) 프로젝트에 commons-fileupload 라이브러리를 따로 추가해야한다
  // 2) 스프링 WebMVC에는 이 라이브러리를 이용 MultipartData를 처리후
  //    페이지 컨트롤러에게 값들을 전달해주는 CommonsMultipartResolver를 등록
  // 주의점
  //  MultipartResolver 구현체 bean 이름은 "multipartResolver" 여야 한다
  //  @Bean
  //  public MultipartResolver multipartResolver () {
  //    return new CommonsMultipartResolver();
  //  }

  // 뷰컴포넌트(예: JSP) 의 경로를 다루는 객체 준비
  @Bean
  public ViewResolver viewResolver() {
    // 페이지 컨트롤러가 jsp경로를 리턴하면
    // viewResolver가 그 경로를 가지고 최종 jsp 경로를 확인

    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setViewClass(JstlView.class);
    viewResolver.setPrefix("/WEB-INF/jsp/");
    viewResolver.setSuffix(".jsp");
    return viewResolver;
  }
}








