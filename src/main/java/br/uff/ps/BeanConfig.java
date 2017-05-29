package br.uff.ps;

import br.uff.ps.model.Admin;
import br.uff.ps.model.Expression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public Class<Expression> expressionClass(){
        return Expression.class;
    }

    @Bean
    public Class<Admin> adminClass(){
        return Admin.class;
    }
}
