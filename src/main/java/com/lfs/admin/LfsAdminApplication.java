package com.lfs.admin;

import com.lfs.annotation.LfsApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.lfs.*")
@MapperScan("com.lfs.**.dao")
@EnableFeignClients("com.lfs.*")
public class LfsAdminApplication {

	public static void main(String[] args) {

		try {
			SpringApplication.run(LfsAdminApplication.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
