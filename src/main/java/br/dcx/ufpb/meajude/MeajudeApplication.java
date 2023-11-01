package br.dcx.ufpb.meajude;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import br.dcx.ufpb.meajude.filtros.FiltroDeTokensJWT;

@SpringBootApplication
public class MeajudeApplication {
	@Bean
	public FilterRegistrationBean<FiltroDeTokensJWT> filterJwt() {
		FilterRegistrationBean<FiltroDeTokensJWT> filterRB = new FilterRegistrationBean<FiltroDeTokensJWT>();
		filterRB.setFilter(new FiltroDeTokensJWT());
		filterRB.addUrlPatterns("/auth/usuarios");
		return filterRB;
	}

	public static void main(String[] args) {
		SpringApplication.run(MeajudeApplication.class, args);
	}

}
