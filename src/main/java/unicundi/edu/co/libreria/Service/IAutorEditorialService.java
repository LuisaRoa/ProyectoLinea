package unicundi.edu.co.libreria.Service;

import java.util.List;

import unicundi.edu.co.libreria.Entity.AutorEditorial;
import unicundi.edu.co.libreria.Exception.ModelNotFoundException;

public interface IAutorEditorialService extends ICRUDService<AutorEditorial, Integer>{

	void eliminarNativo (int idAutor, int idEditorial) throws ModelNotFoundException;

	List<AutorEditorial> editorialPorAutor(int idAutor);

	List<AutorEditorial> autorPorEditorial(int idEditorial);
}
