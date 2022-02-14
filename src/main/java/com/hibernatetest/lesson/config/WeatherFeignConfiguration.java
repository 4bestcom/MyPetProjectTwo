package com.hibernatetest.lesson.config;

import com.hibernatetest.lesson.web.client.WeatherClient;
import feign.Contract;
import feign.Feign;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@EnableFeignClients
@Import({FeignClientsConfiguration.class})
@Configuration
public class WeatherFeignConfiguration {

    @Value("${weather.url}")
    private String url;

    @Bean
    public WeatherClient getWeatherClient(Contract contract) {
        return Feign.builder()
                .contract(contract)
                .target(WeatherClient.class, url);
    }
}
