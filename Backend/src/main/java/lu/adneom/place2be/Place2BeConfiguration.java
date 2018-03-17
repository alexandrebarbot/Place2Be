package lu.adneom.place2be;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Configuration
@EnableSwagger2
public class Place2BeConfiguration {

    private static final Set<String> JSON_MEDIA_TYPE = Collections.singleton(MediaType.APPLICATION_JSON_VALUE);

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .consumes(JSON_MEDIA_TYPE)
                .produces(JSON_MEDIA_TYPE)
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, messages())
                .globalResponseMessage(RequestMethod.POST, messages())
                .apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("lu.adneom.place2be"))
                .paths(PathSelectors.any())
                .build();
    }

    private List<ResponseMessage> messages() {
        List<ResponseMessage> messages = new ArrayList<>();
        messages.add(new ResponseMessageBuilder()
                .code(200).
                        message("Everything is fine")
                .build());
        messages.add(
                new ResponseMessageBuilder()
                        .code(404).message("Resource is not available")
                        .build());
        messages.add(
                new ResponseMessageBuilder()
                        .code(401).message("Unauthorized, failed to authenticate")
                        .build());
        messages.add(new ResponseMessageBuilder()
                .code(500)
                .message("Unexpected error")
                .build());

        return messages;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Place2Be REST APIs")
                .description("Place2Be APIs")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0.html")
                .version("1.0")
                .build();
    }

}
