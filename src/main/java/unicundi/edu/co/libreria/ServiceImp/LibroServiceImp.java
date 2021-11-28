package unicundi.edu.co.libreria.ServiceImp;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import unicundi.edu.co.libreria.Entity.Libro;
import unicundi.edu.co.libreria.Exception.ArgumentRequiredException;
import unicundi.edu.co.libreria.Exception.ConflictException;
import unicundi.edu.co.libreria.Exception.ModelNotFoundException;
import unicundi.edu.co.libreria.Repo.IAutorRepo;
import unicundi.edu.co.libreria.Repo.ILibroRepo;
import unicundi.edu.co.libreria.Service.ILibroService;

@Service
public class LibroServiceImp implements ILibroService{

	@Autowired
	ILibroRepo repo;
	
	@Autowired
	IAutorRepo repoAutor;
	
	@Override
	public Page<Libro> retornarPaginado(int page, int size) {
		// TODO Auto-generated method stub
		return repo.findAll(PageRequest.of(page,size));
	}

	@Override
	public Page<Libro> retornarPaginado(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Libro retonarPorId(Integer idLibro, boolean bandera) throws ModelNotFoundException {
		// TODO Auto-generated method stub
		Optional<Libro> libro = repo.findById(idLibro);
		if (libro.isPresent()) {
			if (bandera == true) {
				return libro.get();
			} else {
				Libro libroAux = libro.get();
				libroAux.setAutor(null);
				return libroAux;
			}
		} else {
			throw new ModelNotFoundException("Libro no encontrado");
		}
	}

	@Override
	public void guardar(Libro libro) throws ConflictException, ArgumentRequiredException,  ModelNotFoundException{
		// TODO Auto-generated method stub
		if(libro.getAutor()!=null) {
			repoAutor.findById(libro.getAutor().getId()).orElseThrow(() ->
			new ModelNotFoundException("Autor no encontrado"));
			//libro.setAutor(autor);
			this.repo.save(libro);
		}else {
			throw new ArgumentRequiredException("Autor es requerido");
		}
	     
	}

	@Override
	public void editar(Libro libro) throws ArgumentRequiredException, ModelNotFoundException, ConflictException {
		// TODO Auto-generated method stub
		if(libro.getId() != null) {
			if(validarExistenciaPorId(libro.getId())) {
				repoAutor.findById(libro.getAutor().getId()).orElseThrow(() ->
				new ModelNotFoundException("Autor no encontrado"));
				this.repo.save(libro);
			} else
				throw new ModelNotFoundException("Libro no encontrado");		
		} else {
			throw new ArgumentRequiredException("Id Libro es requerido");
		}
	}

	@Override
	public void eliminar(int idLibro) throws ModelNotFoundException {
		// TODO Auto-generated method stub
		if(validarExistenciaPorId(idLibro))
			this.repo.deleteById(idLibro);
		else
			throw new ModelNotFoundException("Libro no encontrado");
	}
	
	private Boolean validarExistenciaPorId(int idLibro) {
		return repo.existsById(idLibro);
	}
	
	public List<Libro> retonarPorAutor(int idAutor) throws ModelNotFoundException {
		// TODO Auto-generated method stub
		return repo.buscarPorAutor(idAutor);
	}

	@Override
	public Libro retonarPorNombreYPaginas(int id, int paginas) {
		// TODO Auto-generated method stub
		return repo.findByIdOrNumeroPaginas(id, paginas);
	}

	@Override
	public List<Libro> retonarPorAutorYPaginas(int paginas, int id) {
		// TODO Auto-generated method stub
		return repo.findByNumeroPaginasAndAutor_Id(paginas, id);
	}
}
