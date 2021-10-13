package com.phisoft.bookstorekotlin.configurations;

import com.phisoft.bookstorekotlin.authentication.AccountService;
import com.phisoft.bookstorekotlin.authentication.JwtTokenVerifier;
import com.phisoft.bookstorekotlin.authentication.JwtUsernamePasswordAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * This the main project configuration class
 */
@Configuration
@EnableSwagger2
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ProjectConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * {@inheritDoc}
     */
    @Bean
    @Override
    public UserDetailsService userDetailsService(){
        return new AccountService();
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                 .and()
                 .addFilter(new JwtUsernamePasswordAuthFilter(authenticationManager()))
                .addFilterAfter(new JwtTokenVerifier(),JwtUsernamePasswordAuthFilter.class)
                .authorizeRequests()
                .antMatchers("/api/v1/**")
                .authenticated()
                .anyRequest()
                .permitAll();
    }


    /**
     * {@inheritDoc}
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userDetailsService());
        return authenticationProvider;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }


    /**
     * {@inheritDoc}
     */
    @Bean
    public Docket swaggerConfiguration(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/api/**"))
                .apis(RequestHandlerSelectors.basePackage("com.phisoft"))
                .build()
                .apiInfo(apiDetails());
    }

    /**
     * Configures the swagger UI
     * @return the Api details needed for swagger configuration
     */

    private ApiInfo apiDetails(){
        return new ApiInfo("Book-Store Api","This is an Api that enables somebody to upload book to a book our store based on author,category,type and publisher. " +
                "To see the list of authors you can upload their book to our store, call getAllAuthors endpoint in the book-attribute-controller. " +
                "To see the list of book categories you can use to categorize book you want to upload, call getAllBookCategories endpoint in the book-attribute-controller. " +
                "To see the list of book publisher you can use to upload book to our book store, call getAllBookPublishers endpoint in book-attribute-controller. " +
                "To see the list of book type you can use to tag your books before uploading to book store, use getAllBookType endpoint in book-attribute-controller"+
                ". But before any operation can be performed on the bookstore, you have to register with username and password. Username can be anything and password can be anything also ."+
                "After successful registration, you go to /login, use the username and password used in registration to login to our book store. When you have logged in successfully, you will be issued an authentication token that have to be in the head of every subsequent requests you make to our store. The token expires within a day period. After one day, you renew the token by logging in again with your username and password","1.0","Free to use",
                new springfox.documentation.service.Contact("Replace","Replace","Replace"),
                "Api Licence","Replace", Collections.emptyList());
    }

}
