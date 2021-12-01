package unicundi.edu.co.libreria.Controller;

import java.awt.print.Pageable;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import unicundi.edu.co.libreria.Entity.Autor;
import unicundi.edu.co.libreria.Entity.AutorEditorial;
import unicundi.edu.co.libreria.Exception.ArgumentRequiredException;
import unicundi.edu.co.libreria.Exception.ConflictException;
import unicundi.edu.co.libreria.Exception.ModelNotFoundException;
import unicundi.edu.co.libreria.Service.IAutorEditorialService;
import unicundi.edu.co.libreria.Service.IAutorService;


@RestController
@RequestMapping("/autores")
//@PreAuthorize("hasAuthority('Administrador')")
public class AutorController {

	@Autowired
	private IAutorService service;
	
	@Autowired
	private IAutorEditorialService serviceAE;
	
	@PreAuthorize("hasAuthority('Vendedor') OR hasAuthority('Administrador')")
	@GetMapping(value = "/obtenerPaginado/{page}/{size}" ,produces = "application/json")
	public ResponseEntity<?> retonarPaginado(@PathVariable int page, @PathVariable int size) {
		Page<Autor> listaAutor = service.retornarPaginado(page, size);
		return new ResponseEntity<Page<Autor>>(listaAutor, HttpStatus.OK);	
	}	
	
	@GetMapping(value = "/obtenerPaginado" ,produces = "application/json")
	public ResponseEntity<?> retonarPaginado(Pageable page) {
		Page<Autor> listaAutor = service.retornarPaginado(page);
		return new ResponseEntity<Page<Autor>>(listaAutor, HttpStatus.OK);	
	}	
	
	@PreAuthorize("hasAuthority('Vendedor') OR hasAuthority('Administrador')")
	@GetMapping(value = "/obtenerPorId/{id}/{bandera}" ,produces = "application/json")
	public ResponseEntity<?> retonarPorId(@PathVariable int id, @PathVariable boolean bandera) throws ModelNotFoundException {
		Autor autor = service.retonarPorId(id, bandera);
		return new ResponseEntity<Autor>(autor, HttpStatus.OK);	
	}		
	
	@PostMapping(value = "/insertar", consumes = "application/json")
	public ResponseEntity<?> guardar(@Valid @RequestBody Autor autor) throws ConflictException, ArgumentRequiredException, ModelNotFoundException {
		service.guardar(autor);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	
	@PutMapping(value = "/editar", consumes = "application/json")
	public ResponseEntity<?> editar(@Valid @RequestBody Autor autor)  
				throws ArgumentRequiredException, ModelNotFoundException, ConflictException{
		this.service.editar(autor);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}	
	
	@DeleteMapping(value = "/eliminar/{i}")
	public ResponseEntity<?> eliminar(@PathVariable int i)  throws ModelNotFoundException{
		this.service.eliminar(i);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}	
	
	@PostMapping(value = "/asociarEditorial", consumes = "application/json")
	public ResponseEntity<?> asociarEditorial(@Valid @RequestBody AutorEditorial autorE) throws ConflictException, ArgumentRequiredException, ModelNotFoundException {
		serviceAE.guardar(autorE);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/eliminarAsociacion/{idAutor}/{idEditorial}")
	public ResponseEntity<?> eliminarAsociacion(@PathVariable int idAutor, @PathVariable int idEditorial)  throws ModelNotFoundException{
		this.serviceAE.eliminarNativo(idAutor, idEditorial);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}	
	
	@GetMapping(value = "/obtenerAsociados/{page}/{size}" ,produces = "application/json")
	public ResponseEntity<?> retonarAsociados(@PathVariable int page, @PathVariable int size) {
		Page<AutorEditorial> listaAutorEdit = serviceAE.retornarPaginado(page, size);
		return new ResponseEntity<Page<AutorEditorial>>(listaAutorEdit, HttpStatus.OK);	
	}	
	
	@GetMapping(value = "/autorPorEditorial/{idEditorial}" ,produces = "application/json")
	public ResponseEntity<?> retonarAutores(@PathVariable int idEditorial) {
		List<AutorEditorial> listaAutorEdit = serviceAE.autorPorEditorial(idEditorial);
		return new ResponseEntity<List<AutorEditorial>>(listaAutorEdit, HttpStatus.OK);	
	}	
}
