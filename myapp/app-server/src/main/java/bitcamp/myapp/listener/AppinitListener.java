package bitcamp.myapp.listener;


import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import bitcamp.myapp.config.AppConfig;

@WebListener
public class AppinitListener implements ServletContextListener{

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    AnnotationConfigWebApplicationContext iocContainer = new AnnotationConfigWebApplicationContext();
    iocContainer.register(AppConfig.class);
    //    iocContainer.refresh();
    //
    //    String[] beanNames = iocContainer.getBeanDefinitionNames();
    //    for (String beanName : beanNames) {
    //      System.out.printf("%s ==> %s\n", beanName, iocContainer.getBean(beanName).getClass().getName());
    //    }

    DispatcherServlet dispatcherServlet = new DispatcherServlet(iocContainer);
    ServletRegistration.Dynamic registration = sce.getServletContext().addServlet("app", dispatcherServlet);

    MultipartConfigElement multipartConfig = new MultipartConfigElement(
        System.getProperty("java.io.tmpdir"), // 클라이언트가 보낸 파일을 임시 보관할 폴더
        10485760, // 한파일 최대 크기
        10485760, // 한 요청당 최대 총파일의 크기
        1024 * 1024 * 1 // 메모리에  임시 저장할 파일 크기. 최대 크기를 초과하면 파일로 내보낸다
        );
    registration.setMultipartConfig(multipartConfig);

    registration.addMapping("/app/*");
    registration.setLoadOnStartup(1);

    System.out.println();

  }
}
