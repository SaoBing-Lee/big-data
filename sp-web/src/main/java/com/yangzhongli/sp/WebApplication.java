package com.yangzhongli.sp;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * insert description here
 *
 * @author liyanbing
 * @since 2018/9/29 17:57
 */
@SpringBootApplication
@MapperScan("com.yangzhongli.sp.dao.instance")
@EnableSwagger2
@EnableScheduling
@ServletComponentScan
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }


//    private CorsConfiguration buildConfig() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.addAllowedOrigin("*");
//        corsConfiguration.addAllowedHeader("*");
//        corsConfiguration.addAllowedMethod("*");
//        //corsConfiguration.addExposedHeader(HttpHeaderConStant.X_TOTAL_COUNT);
//        return corsConfiguration;
//    }
//
//    /**
//     * 跨域过滤器
//     *
//     * @return
//     */
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", buildConfig()); // 4
//        return new CorsFilter(source);
//    }

}
