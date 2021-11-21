package configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
@Import(Configuration_Oracle.class)
@ImportResource("classpath:configuration/configuration_XML.xml")
public class Configuration_XML {
}