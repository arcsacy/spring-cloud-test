package com.tyr.swagger;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.configuration.Swagger2DocumentationConfiguration;

/**
 * @Class : Swagger2Configuration
 * @Description : TODO
 * @Author : Tyrance
 * @Date : 2021/10/18 11:39
 * @Version : 1.0
 */
@ConditionalOnProperty(value = "swagger.lib", matchIfMissing = true)
@Import({Swagger2DocumentationConfiguration.class})
public class Swagger2Configuration {
}
