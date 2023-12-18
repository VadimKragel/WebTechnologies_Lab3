package by.bsuir.lab3.springConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@Import({ SpringServiceConfig.class })
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/newapp/user/chooseSeat/**").hasAnyRole("ADMIN, USER")
				.antMatchers("/newapp/admin/**").hasRole("ADMIN").antMatchers("/messageDelete*").hasRole("ADMIM").and()
				.formLogin().loginProcessingUrl("/j_spring_security_check").loginPage("/newapp/user/login")
				.defaultSuccessUrl("/newapp/user/").usernameParameter("username").passwordParameter("password")
				.failureUrl("/newapp/user/error?error=true").and().csrf().disable().logout()
				.logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/newapp/user/");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
}
