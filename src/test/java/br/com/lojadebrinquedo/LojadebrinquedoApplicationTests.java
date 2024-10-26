package br.com.lojadebrinquedo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class LojadebrinquedoApplicationTests {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	void contextLoads() {
		try {
			jdbcTemplate.execute("SELECT 1");
			System.out.println("Banco de dados Conectado");
		} catch (Exception e) {
			System.out.println("Banco de dados não conectado");
		}
	}
}
