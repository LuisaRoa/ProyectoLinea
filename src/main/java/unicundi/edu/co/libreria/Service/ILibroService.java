package unicundi.edu.co.libreria.Service;

import java.util.List;

import unicundi.edu.co.libreria.Entity.Libro;
import unicundi.edu.co.libreria.Exception.ModelNotFoundException;

public interface ILibroService extends ICRUDService<Libro, Integer>{

	List<Libro> retonarPorAutor(int idAutor) throws ModelNotFoundException ;

	Libro retonarPorNombreYPaginas(int id, int paginas);

	List<Libro> retonarPorAutorYPaginas(int paginas, int id);

}
