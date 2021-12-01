package unicundi.edu.co.libreria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	//Trae todo lo que configuramos en el SecurityConfig
		@Autowired
		private ResourceServerTokenServices tokenServices;	
		
	    @Value("${security.jwt.resource-ids}")
	    private String resourceIds;	
	    
	    //De donde se van a crear los tokens y la configuración del resourcesIds
	    @Override
	    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
	        resources.resourceId(resourceIds).tokenServices(tokenServices);
	    } 
	    

	    //Url que vamos a proteger y como
	    @Override
	    public void configure(HttpSecurity http) throws Exception {
	                http
	                .exceptionHandling().authenticationEntryPoint(new AuthException())
	                .and()
	                .requestMatchers()
	                .and()
	                .authorizeRequests()                  
	                .antMatchers("/autores/**" ).permitAll()      
	                .antMatchers("/editoriales/**" ).authenticated()   
	                .antMatchers("/libros/**" ).authenticated() 
	                .antMatchers("/usuarios/**" ).permitAll()
	                .antMatchers("/oauth/token/**" ).permitAll()
	                .antMatchers("/cerrarSesion/**" ).permitAll();
	    }    
}
