package br.com.apipostagem.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.apipostagem.models.Usuario;
import br.com.apipostagem.repository.ClienteRepository;

@RestController
@RequestMapping(value="/cliente")
public class UsuarioController {
	
	@Autowired
	ClienteRepository repositorio;
	
	
	@GetMapping(value ="")
	public List<Usuario> mostrarTodos() {
		return repositorio.findAll();
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscar(@PathVariable long id) {
		Optional<Usuario> optional = repositorio.findById(id);
		
		if(optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		}
		
		
		return ResponseEntity.notFound().build();
		
	}
	
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED) //Segundo a arquitetura rest precisamos enviar um status no caso do post o creted
	public Usuario novaMensagem(@Valid @RequestBody Usuario user ) {
		repositorio.save(user);
		return user;
	}
	
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> alterarMensagem(@PathVariable long id, @Valid @RequestBody Usuario user) {
		Optional<Usuario> usuario = repositorio.findById(id);
		
		if(usuario.isPresent()) {
			BeanUtils.copyProperties(user, usuario.get(),"id");
			Usuario userSalvo = repositorio.save(usuario.get());
			
			return ResponseEntity.ok(userSalvo);
			
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable long id) {
		if(repositorio.existsById(id)) {
			repositorio.deleteById(id);
			
			return ResponseEntity.ok().build();
		}
		
		
		
		return ResponseEntity.notFound().build();
	}
	
	
}
