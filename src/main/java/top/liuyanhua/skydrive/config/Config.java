package top.liuyanhua.skydrive.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Created with IDEA
 *
 * @author: liuyanhua
 * @date:2019-06-08 15:48
 * @description //TODO $
 */
@Configuration
public class Config {
    @Bean
    public CorsFilter corsFilter(){
        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
       //  log.debug("启用跨域，允许域名：{}",corsAllowOriginUrl);
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}
