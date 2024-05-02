package com.turkcell.authserver;

import com.turkcell.core.security.BaseJwtService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan(basePackages = {"com.turkcell.core.security"}) // Ana proje dışında, tarama yapılmasını istediğiniz paket isimleri.
// @Import(BaseJwtService.class)
// @EnableMySecurity
public class AuthserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthserverApplication.class, args);
	}

}
