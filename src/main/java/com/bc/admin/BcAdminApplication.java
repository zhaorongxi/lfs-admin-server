package com.bc.admin;

import com.bc.annotation.BcApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;

@BcApplication("com.bc.*")
@MapperScan("com.bc.**.dao")
public class BcAdminApplication {

	public static void main(String[] args) {

		try {
			SpringApplication.run(BcAdminApplication.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
