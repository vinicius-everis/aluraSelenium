package br.com.alura.leilao.login;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest {
	
	private LoginPage paginaDeLogin;
	
	@BeforeEach
	public void beforeEach() {
		this.paginaDeLogin = new LoginPage();	
	}
	
	@AfterEach
	public void afterEach() {
		this.paginaDeLogin.fechar();
	}

	@Test
	void deveriaEfetuarLoginComDadosValidos() {
		paginaDeLogin.preencheFormularioDeLogin("fulano", "pass");
		paginaDeLogin.efetuaLogin();
		
		assertFalse(paginaDeLogin.isPaginaDeLogin());
		assertEquals("fulano", paginaDeLogin.getNomeUsuarioLogado());
	}
	
	@Test
	void naoDeveriaEfetuarLoginComDadosInvalidos() {
		paginaDeLogin.preencheFormularioDeLogin("invalido", "1234");
		paginaDeLogin.efetuaLogin();
		
		assertTrue(paginaDeLogin.isPaginaDeLoginComErro());
		assertNull(paginaDeLogin.getNomeUsuarioLogado());
		assertTrue(paginaDeLogin.contemTexto("Usuário e senha inválidos."));
	}
	
	@Test
	public void naoDeveriaAcessarPaginaRestritaSemEstarLogado() {
		paginaDeLogin.navegaParaPaginaDeLances();
		
		assertTrue(paginaDeLogin.isPaginaDeLogin());
		assertFalse(paginaDeLogin.contemTexto("Dados do Leilão"));
	}

}
