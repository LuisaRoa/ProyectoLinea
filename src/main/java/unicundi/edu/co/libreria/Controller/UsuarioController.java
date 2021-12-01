package unicundi.edu.co.libreria.Controller;

import java.awt.print.Pageable;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import unicundi.edu.co.libreria.Entity.Usuario;
import unicundi.edu.co.libreria.Exception.ArgumentRequiredException;
import unicundi.edu.co.libreria.Exception.ConflictException;
import unicundi.edu.co.libreria.Exception.ModelNotFoundException;
import unicundi.edu.co.libreria.Service.IUsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private IUsuarioService service;
	
	
	@GetMapping(value = "/obtenerPaginado/{page}/{size}" ,produces = "application/json")
	public ResponseEntity<?> retonarPaginado(@PathVariable int page, @PathVariable int size) {
		Page<Usuario> listaUsuario = service.retornarPaginado(page, size);
		return new ResponseEntity<Page<Usuario>>(listaUsuario, HttpStatus.OK);	
	}	
	
	@GetMapping(value = "/obtenerPaginado" ,produces = "application/json")
	public ResponseEntity<?> retonarPaginado(Pageable page) {
		Page<Usuario> listaUsuario = service.retornarPaginado(page);
		return new ResponseEntity<Page<Usuario>>(listaUsuario, HttpStatus.OK);	
	}	
	
	@GetMapping(value = "/obtenerPorId/{id}" ,produces = "application/json")
	public ResponseEntity<?> retonarPorId(@PathVariable int id, @PathVariable boolean bandera) throws ModelNotFoundException {
		Usuario usuario = service.retonarPorId(id, true);
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);	
	}		
	
	@PostMapping(value = "/insertar", consumes = "application/json")
	public ResponseEntity<?> guardar(@Valid @RequestBody Usuario usuario) throws ConflictException, ArgumentRequiredException, ModelNotFoundException {
		service.guardar(usuario);
		return new ResponseEntity<Object>(HttpStatus.CREATED)
	}
	
	
	@PutMapping(value = "/editar", consumes = "application/json")
	public ResponseEntity<?> editar(@Valid @RequestBody Usuario usuario)  
				throws ArgumentRequiredException, ModelNotFoundException, ConflictException{
		this.service.editar(usuario);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}	
	
	@DeleteMapping(value = "/eliminar/{i}")
	public ResponseEntity<?> eliminar(@PathVariable int i)  throws ModelNotFoundException{
		this.service.eliminar(i);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
	
}
