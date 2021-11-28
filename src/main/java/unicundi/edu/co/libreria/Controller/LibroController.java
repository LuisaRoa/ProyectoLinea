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

import unicundi.edu.co.libreria.Entity.Libro;
import unicundi.edu.co.libreria.Exception.ArgumentRequiredException;
import unicundi.edu.co.libreria.Exception.ConflictException;
import unicundi.edu.co.libreria.Exception.ModelNotFoundException;
import unicundi.edu.co.libreria.Service.ILibroService;

@RestController
@RequestMapping("/libros")
@PreAuthorize("hasAuthority('Vendedor') OR hasAuthority('Autor')")
public class LibroController {

	@Autowired
	private ILibroService service;
	
	
	@GetMapping(value = "/obtenerPaginado/{page}/{size}" ,produces = "application/json")
	public ResponseEntity<?> retonarPaginado(@PathVariable int page, @PathVariable int size) {
		Page<Libro> listaEstudiante = service.retornarPaginado(page, size);
		return new ResponseEntity<Page<Libro>>(listaEstudiante, HttpStatus.OK);	
	}	
	
	@GetMapping(value = "/obtenerPaginado" ,produces = "application/json")
	public ResponseEntity<?> retonarPaginado(Pageable page) {
		Page<Libro> listaLibro = service.retornarPaginado(page);
		return new ResponseEntity<Page<Libro>>(listaLibro, HttpStatus.OK);	
	}	
	
	@GetMapping(value = "/obtenerPorId/{id}/{bandera}" ,produces = "application/json")
	public ResponseEntity<?> retonarPorId(@PathVariable int id, @PathVariable boolean bandera) throws ModelNotFoundException {
		Libro libro = service.retonarPorId(id, bandera);
		return new ResponseEntity<Libro>(libro, HttpStatus.OK);	
	}		
	
	@PostMapping(value = "/insertar", consumes = "application/json")
	public ResponseEntity<?> guardar(@Valid @RequestBody Libro libro) throws ConflictException, ArgumentRequiredException, ModelNotFoundException {
		service.guardar(libro);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	
	@PutMapping(value = "/editar", consumes = "application/json")
	public ResponseEntity<?> editar(@Valid @RequestBody Libro libro)  
				throws ArgumentRequiredException, ModelNotFoundException, ConflictException{
		this.service.editar(libro);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}	
	
	@DeleteMapping(value = "/eliminar/{id}")
	public ResponseEntity<?> eliminar(@PathVariable int id)  throws ModelNotFoundException{
		this.service.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}	
	
	@GetMapping(value = "/obtenerPorAutor/{idAutor}" ,produces = "application/json")
	public ResponseEntity<?> retonarPorAutor(@PathVariable int idAutor) throws ModelNotFoundException {
		List<Libro> listaLibro = service.retonarPorAutor(idAutor);
		return new ResponseEntity<List<Libro>>(listaLibro, HttpStatus.OK);	
	}	
	
	@GetMapping(value = "/obtenerPorIdPaginas/{id}/{paginas}" ,produces = "application/json")
	public ResponseEntity<?> retonarPorNombre(@PathVariable int id, @PathVariable int paginas) {
		Libro libro = service.retonarPorNombreYPaginas(id, paginas);
		return new ResponseEntity<Libro>(libro, HttpStatus.OK);	
	}	
	
	@GetMapping(value = "/obtenerPorAutorPaginas/{paginas}/{idAutor}" ,produces = "application/json")
	public ResponseEntity<?> retonarPorAutorNombre(@PathVariable int paginas, @PathVariable int idAutor) throws ModelNotFoundException {
		List<Libro> listaLibro = service.retonarPorAutorYPaginas(paginas, idAutor);
		return new ResponseEntity<List<Libro>>(listaLibro, HttpStatus.OK);	
	}	
}
