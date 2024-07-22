package co.diplomado.iudigital.helpmeiud.app.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) 
@EnableWebSecurity
@EnableAutoConfiguration(
	exclude = SecurityAutoConfiguration.class
)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService usuarioService;
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

	@Override
	@Autowired 
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(this.usuarioService).passwordEncoder(passwordEncoder());
	}
	
	
	@Bean("authenticationManager")
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	
	

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("**/swagger-ui.html").permitAll()
            .antMatchers("/api/v1/delitos/**").hasRole("ADMIN") 
			.anyRequest().authenticated()
			.and()
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);	
	}
	
    @Override
    public void configure(WebSecurity web) throws Exception {
    	 web.ignoring()
         .antMatchers("/app/**/*.{js,html}")
         .antMatchers("/i18n/**")
         .antMatchers("/content/**")
         .antMatchers("/h2-console/**")
		 .antMatchers("/swagger-ui/**")
         .antMatchers("/swagger-ui/index.html")
         .antMatchers("/swagger-ui.html")
         .antMatchers("/v3/api-docs")
		 .antMatchers("/v3/api-docs/**")
         .antMatchers("/test/**");
    }

}
