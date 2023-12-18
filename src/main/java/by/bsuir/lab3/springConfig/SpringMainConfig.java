package by.bsuir.lab3.springConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
@Import({ SpringDaoConfig.class, SpringServiceConfig.class })
@ImportResource({"/WEB-INF/MainServlet-servlet.xml"})
public class SpringMainConfig {
	{
		System.out.println("SpringMainConfig");
	}

}
 