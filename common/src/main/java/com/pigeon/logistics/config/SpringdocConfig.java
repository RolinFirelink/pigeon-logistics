package com.pigeon.logistics.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.Data;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 默认文档地址：/swagger-ui/index.html
 * knife4J美化UI默认地址：/doc.html
 * 参考文档 <a href="https://springdoc.org/">springdoc</a>
 *
 * @author dxy
 * @date 2022年2月4日
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "openapi3")
@OpenAPIDefinition(servers = {
        @Server(url = "http://localhost:8017", description = "本地开发地址"),
        @Server(url = "http://Hasee-ares-ting:8017/", description = "711局域网测试环境地址"),
        @Server(url = "http://175.178.249.158:8017/", description = "云服务器测试环境地址"),
        @Server(url = "http://106.12.160.172:8017/", description = "生产环境地址")
})
public class SpringdocConfig {

    /**
     * 是否开启swagger配置，生产环境需关闭
     */
    private boolean enabled;

    /**
     * 文档标题
     */
    private String title;

    /**
     * 文档版本
     */
    private String version;

    /**
     * 文档描述
     */
    private String description;

    /**
     * 服务条款
     */
    private String termsOfServiceUrl;

    /**
     * 联系人
     */
    private Contact contact;

    /**
     * 许可证
     */
    private String license;

    /**
     * 许可证链接
     */
    private String licenseUrl;

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("logisticsAdmin")
                .displayName("物流管理Admin")
                .packagesToScan("com.pigeon.logistics.controller")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI springShopOpenApi() {
        return new OpenAPI()
                .info(new Info().title(title)
                        .description(description)
                        .version(version)
                        .contact(contact)
                        .license(new License().name(license).url(licenseUrl)))
                .externalDocs(new ExternalDocumentation()
                        .description("SpringShop Wiki Documentation")
                        .url("https://springshop.wiki.github.org/docs"));
    }

}
