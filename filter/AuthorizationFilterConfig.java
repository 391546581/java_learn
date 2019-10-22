package com.gvt.apos.common.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AuthorizationFilterConfig {
      @Bean
      public AuthorizationFilter authorizationFilter(){
          return new AuthorizationFilter();
      }
 }