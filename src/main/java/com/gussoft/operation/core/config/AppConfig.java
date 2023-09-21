package com.gussoft.operation.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

  @Value("${config.customers.endpoint}")
  private String urlc;

  @Value("${config.credits.endpoint}")
  private String urla;

  //@LoadBalanced
  @Bean
  public WebClient.Builder registerWebClient() {
    return  WebClient.builder().baseUrl(urlc);
  }

  @Bean
  public WebClient.Builder registerWebCredits() {
    return  WebClient.builder().baseUrl(urla);
  }

  @Bean
  public WebProperties.Resources resources(){
    return new WebProperties.Resources();
  }

}
