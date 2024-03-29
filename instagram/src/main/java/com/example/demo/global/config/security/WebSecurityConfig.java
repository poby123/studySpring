package com.example.demo.global.config.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationEntryPointFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.demo.domain.member.service.CustomUserDetailsService;
import com.example.demo.domain.member.service.EmailCodeService;
import com.example.demo.domain.member.service.ResetPasswordCodeUserDetailService;
import com.example.demo.global.config.security.filter.CustomExceptionHandleFilter;
import com.example.demo.global.config.security.filter.CustomUsernamePasswordAuthenticationFilter;
import com.example.demo.global.config.security.filter.JwtAuthenticationFilter;
import com.example.demo.global.config.security.filter.ReissueAuthenticationFilter;
import com.example.demo.global.config.security.filter.ResetPasswordCodeAuthenticationFilter;
import com.example.demo.global.config.security.handler.CustomAccessDeniedHandler;
import com.example.demo.global.config.security.handler.CustomAuthenticationEntryPoint;
import com.example.demo.global.config.security.handler.CustomAuthenticationFailureHandler;
import com.example.demo.global.config.security.handler.CustomAuthenticationSuccessHandler;
import com.example.demo.global.config.security.provider.JwtAuthenticationProvider;
import com.example.demo.global.config.security.provider.ReissueAuthenticationProvider;
import com.example.demo.global.config.security.provider.ResetPasswordCodeAuthenticationProvider;
import com.example.demo.global.config.security.util.JwtUtil;
import com.example.demo.global.result.ResultCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String[] AUTH_WHITELIST_SWAGGER = {
			/* swagger v2 */
			"/v2/api-docs",
			"/swagger-resources",
			"/swagger-resources/**",
			"/configuration/ui",
			"/configuration/security",
			"/swagger-ui.html",
			"/webjars/**",

			/* swagger v3 */
			"/v3/api-docs/**",
			"/swagger-ui/**"
	};

	private static final String[] AUTH_WHITELIST_STATIC = { "/static/css/**", "/static/js/**", "*.ico" };
	private static final String[] AUTH_WHITELIST = {
			"/login",
			"/login/recovery",
			"/accounts",
			"/accounts/check",
			"/accounts/email",
			"/accounts/password/email",
			"/accounts/password/reset",
			"/reissue",
			"/logout/only/cookie",
			"/**/without",
			"/ws-connection/**"
	};

	private final JwtUtil jwtUtil;
	private final ResetPasswordCodeUserDetailService resetPasswordCodeUserDetailService;
	private final CustomUserDetailsService jwtUserDetailsService;
	private final EmailCodeService emailCodeService;

	// Provider
	private final JwtAuthenticationProvider jwtAuthenticationProvider;
	private final ReissueAuthenticationProvider reissueAuthenticationProvider;

	// Handler
	private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
	private final CustomAccessDeniedHandler customAccessDeniedHandler;
	private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

	// Filter
	private final CustomExceptionHandleFilter customExceptionHandleFilter;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
		daoAuthenticationProvider.setUserDetailsService(jwtUserDetailsService);
		return daoAuthenticationProvider;
	}

	@Bean
	public ResetPasswordCodeAuthenticationProvider resetPasswordCodeAuthenticationProvider() {
		return new ResetPasswordCodeAuthenticationProvider(resetPasswordCodeUserDetailService, emailCodeService);
	}

	@Bean
	public AuthenticationEntryPointFailureHandler authenticationEntryPointFailureHandler() {
		return new AuthenticationEntryPointFailureHandler(customAuthenticationEntryPoint);
	}

	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
		final List<String> skipPaths = new ArrayList<>();
		skipPaths.addAll(Arrays.stream(AUTH_WHITELIST_SWAGGER).collect(Collectors.toList()));
		skipPaths.addAll(Arrays.stream(AUTH_WHITELIST_STATIC).collect(Collectors.toList()));
		skipPaths.addAll(Arrays.stream(AUTH_WHITELIST).collect(Collectors.toList()));
		final RequestMatcher matcher = new CustomRequestMatcher(skipPaths);
		final JwtAuthenticationFilter filter = new JwtAuthenticationFilter(matcher, jwtUtil);

		filter.setAuthenticationManager(super.authenticationManager());
		filter.setAuthenticationFailureHandler(authenticationEntryPointFailureHandler());

		return filter;
	}

	@Bean
	public CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter() throws Exception {
		final CustomUsernamePasswordAuthenticationFilter filter = new CustomUsernamePasswordAuthenticationFilter();
		filter.setAuthenticationManager(super.authenticationManager());
		filter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
		filter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);
		return filter;
	}

	@Bean
	public ResetPasswordCodeAuthenticationFilter resetPasswordCodeAuthenticationFilter() throws Exception {
		final ResetPasswordCodeAuthenticationFilter filter = new ResetPasswordCodeAuthenticationFilter();
		filter.setAuthenticationManager(super.authenticationManager());
		filter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
		filter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);
		return filter;
	}

	@Bean
	public ReissueAuthenticationFilter reissueAuthenticationFilter() throws Exception {
		final ReissueAuthenticationFilter filter = new ReissueAuthenticationFilter();
		filter.setAuthenticationManager(super.authenticationManager());
		filter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
		filter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);
		return filter;
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(jwtAuthenticationProvider)
				.authenticationProvider(resetPasswordCodeAuthenticationProvider())
				.authenticationProvider(reissueAuthenticationProvider)
				.authenticationProvider(daoAuthenticationProvider());
	}

	@Bean
	public CorsConfigurationSource configurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();

		configuration.addAllowedOriginPattern("*");
		configuration.addAllowedHeader("*");
		configuration.addAllowedMethod("*");
		configuration.setAllowCredentials(true);
		configuration.setMaxAge(3600L);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);

		return source;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(AUTH_WHITELIST_STATIC);
		web.ignoring().antMatchers(AUTH_WHITELIST_SWAGGER);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		configureCustomBeans();

		http.exceptionHandling()
			.authenticationEntryPoint(customAuthenticationEntryPoint)
			.accessDeniedHandler(customAccessDeniedHandler);

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.logout().disable()
			.formLogin().disable()
			.httpBasic().disable();

		http.cors()
				.configurationSource(configurationSource())
				.and()
				.csrf()
				.disable()
				.authorizeRequests()
				.requestMatchers(CorsUtils::isPreFlightRequest)
				.permitAll()
				.antMatchers(AUTH_WHITELIST)
				.permitAll()
				.anyRequest().hasAuthority("ROLE_MEMBER");

		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(customExceptionHandleFilter, JwtAuthenticationFilter.class);
		http.addFilterBefore(customUsernamePasswordAuthenticationFilter(), JwtAuthenticationFilter.class);
		http.addFilterBefore(resetPasswordCodeAuthenticationFilter(), JwtAuthenticationFilter.class);
		http.addFilterBefore(reissueAuthenticationFilter(), JwtAuthenticationFilter.class);
	}

	private void configureCustomBeans() {
		final Map<String, ResultCode> map = new HashMap<>();
		map.put("/login", ResultCode.LOGIN_SUCCESS);
		map.put("/reissue", ResultCode.REISSUE_SUCCESS);
		map.put("/login/recovery", ResultCode.LOGIN_WITH_CODE_SUCCESS);
		customAuthenticationSuccessHandler.setResultCodeMap(map);
	}

}