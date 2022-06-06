package cn.com.tcc.ofa.erm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author hsw
 * @date 2022/4/29 10:47
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"cn.com.tcc.ofa"})
@ComponentScan(basePackages = {"cn.com.tcc.ofa"})
@MapperScan("cn.com.tcc.ofa.*.mapper")
@EnableTransactionManagement
public class ErmApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErmApplication.class, args);
    }




}
