package com.spring.baseproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.spring.baseproject.enumeration.RolesEnum;
import com.spring.baseproject.service.SecutiryUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		 http.authorizeRequests()
		 
		 	.antMatchers("/login/**", "/cadastro/**","/version/**").permitAll()                
		 	
		    .antMatchers(RolesEnum.MANTER_USUARIOS.getUrl()).access(RolesEnum.MANTER_USUARIOS.getRoleAcess())		    
		    .antMatchers(RolesEnum.MANTER_PRODUTOS.getUrl()).access(RolesEnum.MANTER_PRODUTOS.getRoleAcess())
		    .antMatchers(RolesEnum.MANTER_PERFIS.getUrl()).access(RolesEnum.MANTER_PERFIS.getRoleAcess())
		    		    
			.anyRequest().authenticated()                                                   
		    .and()   
		    
	        .formLogin()
	        .loginPage("/login")
	        .failureUrl("/login?error=true")
	        .defaultSuccessUrl("/home")	        
	        .and()
	        
	        .logout()
	        .logoutSuccessUrl("/login")       
	        .and()
	        
			.csrf().disable()	        
	        .exceptionHandling()
	        .accessDeniedPage("/acesso-negado");
	        	        
	}

	@Autowired
	private SecutiryUserDetailsService users;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(users).passwordEncoder(new Md5PasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/static/**");		
		web.ignoring().antMatchers("/console/**");		
	}
	
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
             

}
