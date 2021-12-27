package com.atjh.linux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication
@ComponentScan(basePackages = {"com.atjh"}) // swagger扫描路径
@CrossOrigin
public class LinuxApplication {

    public static void main(String[] args) {
        SpringApplication.run(LinuxApplication.class, args);
    }

//    ConstantPropertiesUtil爆红
//    @Bean
//    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
//
//        PropertySourcesPlaceholderConfigurer c = new PropertySourcesPlaceholderConfigurer();
//
//        c.setIgnoreUnresolvablePlaceholders(true);
//
//        return c;
//    }


}
