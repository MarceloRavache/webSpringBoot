package com.ufc.br.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ufc.br.security.UserDetailsServiceImplementation;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsServiceImplementation userdetailService;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(userdetailService).passwordEncoder(new BCryptPasswordEncoder());;
		
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		
		web.ignoring().antMatchers("/css/**","/js/**","/imgs/**","/images/**");
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		
		.antMatchers(HttpMethod.GET,"/").permitAll()
		.antMatchers("/galery").permitAll()
		.antMatchers(HttpMethod.POST,"/pessoa/login").permitAll()
		.antMatchers("/pessoa/cadastrar").permitAll()
		.antMatchers(HttpMethod.POST,"/pessoa/cadastrarPessoa").permitAll()
		.antMatchers(HttpMethod.GET,"/carrinho/telaCarrinho").permitAll()
		.antMatchers("/carrinho/compra").permitAll()
		.antMatchers("/carrinho/listar").permitAll()
		.antMatchers("/admin/cadastrar").permitAll()
		.antMatchers("/admin/cadastrarPrato").permitAll()
		.antMatchers("/admin/lista").permitAll()
		.antMatchers("/admin/listar").permitAll()
		.antMatchers("/admin/remover/*").permitAll()
		.antMatchers("/admin/editar/*").permitAll()
		.antMatchers("/carrinho/add/*").permitAll()
		.antMatchers("/carrinho/remove/*").permitAll()
		.antMatchers("/compra/finalizar/*").permitAll()
		.antMatchers("/compra/listarCompras/*").permitAll()
		.antMatchers("/compra/listarCompra/*").permitAll()
		.antMatchers("/compra/final").permitAll()

		
		
		.anyRequest().authenticated()

		.and()
        .formLogin()
        .loginPage("/pessoa/login")
        .permitAll()
        .defaultSuccessUrl("/galery")
        .permitAll()
		
		.and()
        .logout()
        .invalidateHttpSession(true)
        .clearAuthentication(true)
        .logoutUrl("/pessoa/logout")
        .permitAll();
	}
	
}