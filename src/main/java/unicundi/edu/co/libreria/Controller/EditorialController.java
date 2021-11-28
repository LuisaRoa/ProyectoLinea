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

import unicundi.edu.co.libreria.Entity.AutorEditorial;
import unicundi.edu.co.libreria.Entity.Editorial;
import unicundi.edu.co.libreria.Exception.ArgumentRequiredException;
import unicundi.edu.co.libreria.Exception.ConflictException;
import unicundi.edu.co.libreria.Exception.ModelNotFoundException;
import unicundi.edu.co.libreria.Service.IAutorEditorialService;
import unicundi.edu.co.libreria.Service.IEditorialService;

@RestController
@RequestMapping("/editoriales")
@PreAuthorize("hasAuthority('Administrador')")
public class EditorialController {

	@Autowired
	private IEditorialService service;
	
	@Autowired
	private IAutorEditorialService serviceAE;
	
	@PreAuthorize("hasAuthority('Vendedor') OR hasAuthority('Administrador')")
	@GetMapping(value = "/obtenerPaginado/{page}/{size}" ,produces = "application/json")
	public ResponseEntity<?> retonarPaginado(@PathVariable int page, @PathVariable int size) {
		Page<Editorial> listaEditorial = service.retornarPaginado(page, size);
		return new ResponseEntity<Page<Editorial>>(listaEditorial, HttpStatus.OK);	
	}	
	
	@GetMapping(value = "/obtenerPaginado" ,produces = "application/json")
	public ResponseEntity<?> retonarPaginado(Pageable page) {
		Page<Editorial> listaEditorial = service.retornarPaginado(page);
		return new ResponseEntity<Page<Editorial>>(listaEditorial, HttpStatus.OK);	
	}	
	
	@PreAuthorize("hasAuthority('Vendedor') OR hasAuthority('Administrador')")
	@GetMapping(value = "/obtenerPorId/{id}" ,produces = "application/json")
	public ResponseEntity<?> retonarPorId(@PathVariable int id, @PathVariable boolean bandera) throws ModelNotFoundException {
		Editorial editorial = service.retonarPorId(id, true);
		return new ResponseEntity<Editorial>(editorial, HttpStatus.OK);	
	}		
	
	@PostMapping(value = "/insertar", consumes = "application/json")
	public ResponseEntity<?> guardar(@Valid @RequestBody Editorial editorial) throws ConflictException, ArgumentRequiredException, ModelNotFoundException {
		service.guardar(editorial);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	
	@PutMapping(value = "/editar", consumes = "application/json")
	public ResponseEntity<?> editar(@Valid @RequestBody Editorial editorial)  
				throws ArgumentRequiredException, ModelNotFoundException, ConflictException{
		this.service.editar(editorial);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}	
	
	@DeleteMapping(value = "/eliminar/{i}")
	public ResponseEntity<?> eliminar(@PathVariable int i)  throws ModelNotFoundException{
		this.service.eliminar(i);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(value = "/editorialPorAutor/{idAutor}" ,produces = "application/json")
	public ResponseEntity<?> retonarEditoriales(@PathVariable int idAutor) {
		List<AutorEditorial> listaAutorEdit = serviceAE.editorialPorAutor(idAutor);
		return new ResponseEntity<List<AutorEditorial>>(listaAutorEdit, HttpStatus.OK);	
	}	
}
