package br.com.apipostagem.controller;



import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.apipostagem.models.Usuario;
import br.com.apipostagem.repository.ClienteRepository;
import io.restassured.http.ContentType;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

@WebMvcTest // -> adicionando um contexto para os meus teste no caso e um contexto web
class Teste_ClienteController {
	
	
	// instanciando meu objeto de teste
	@Autowired // -> spring vai ler e vai fazer a  instancia do meu objeto automatico
	UsuarioController controle;
	
	@MockBean // --> não estou instanciando o objeto real apenas uma imagem
	ClienteRepository repositorio;
	
	@BeforeEach // -> ele ocorrera antes de todos os teste
	public void set() {
		standaloneSetup(this.controle);
	}
	
	@Test // -> notação está indicando que é um teste
	void devRetornaUma_listaDeUsuario() {
		
		given() // ->dado tal informação
		  .accept(ContentType.JSON)
		 .when()
		   .get("/cliente")
		 .then()
		   .statusCode(HttpStatus.OK.value());
		
	}
	
	@Test
	void deveRetornaUm_usuarioPesquisado() {
		Optional<Usuario> user = repositorio.findById(1L);
		Usuario querido = user.get();
		when( this.controle.buscar(1L))
		.thenReturn( ResponseEntity.ok(querido) );
		 
		given()
		   .accept(ContentType.JSON)
		  .when()
		   .get("/cliente/{id}", 1L)
		 .then()
		   .statusCode(HttpStatus.OK.value());
	}

}
