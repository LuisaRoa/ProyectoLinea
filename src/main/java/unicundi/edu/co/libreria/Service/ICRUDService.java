package unicundi.edu.co.libreria.Service;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;

import unicundi.edu.co.libreria.Exception.ArgumentRequiredException;
import unicundi.edu.co.libreria.Exception.ConflictException;
import unicundi.edu.co.libreria.Exception.ModelNotFoundException;

public interface ICRUDService<T, ID> {

	public Page<T> retornarPaginado(int page, int size);
	
	public Page<T> retornarPaginado(Pageable page);
	
	public T retonarPorId(ID id, boolean bandera) throws ModelNotFoundException;
		
	public void guardar(T estudiante)  throws ConflictException, ArgumentRequiredException, ModelNotFoundException;
	
	public void editar(T estudiante)  throws ArgumentRequiredException, ModelNotFoundException, ConflictException;
	
	public void eliminar(int id) throws ModelNotFoundException;
}
