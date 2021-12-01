package unicundi.edu.co.libreria.ServiceImp;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import unicundi.edu.co.libreria.Entity.AutorEditorial;
import unicundi.edu.co.libreria.Exception.ArgumentRequiredException;
import unicundi.edu.co.libreria.Exception.ConflictException;
import unicundi.edu.co.libreria.Exception.ModelNotFoundException;
import unicundi.edu.co.libreria.Repo.IAutorEditorialRepo;
import unicundi.edu.co.libreria.Repo.IAutorRepo;
import unicundi.edu.co.libreria.Repo.IEditorialRepo;
import unicundi.edu.co.libreria.Service.IAutorEditorialService;

@Service
public class AutorEditorialServiceImp implements IAutorEditorialService{

	@Autowired
	IAutorEditorialRepo repo;
	
	@Autowired
	IAutorRepo repoA;
	
	@Autowired
	IEditorialRepo repoE;
	
	@Override
	public Page<AutorEditorial> retornarPaginado(int page, int size) {
		// TODO Auto-generated method stub
		Page<AutorEditorial> autorEditorial = repo.findAll(PageRequest.of(page, size));
		return autorEditorial;
	}

	@Override
	public Page<AutorEditorial> retornarPaginado(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AutorEditorial retonarPorId(Integer id, boolean bandera) throws ModelNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void guardar(AutorEditorial obj)
			throws ConflictException, ArgumentRequiredException, ModelNotFoundException {
		// TODO Auto-generated method stub
		if(repoA.findById(obj.getAutor().getId()).isPresent()) {
			
		}else {
			throw new ModelNotFoundException("Autor no encontrado");
		}
		if(repoE.findById(obj.getEditorial().getId()).isPresent()) {
			
		}else {
			throw new ModelNotFoundException("Editorial no encontrada");
		}
		this.repo.guardarNativo(obj.getAutor().getId(), obj.getEditorial().getId(), obj.getFecha());
		
	}

	@Override
	public void editar(AutorEditorial estudiante)
			throws ArgumentRequiredException, ModelNotFoundException, ConflictException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(int id) throws ModelNotFoundException {
		// TODO Auto-generated method stub
	}

	@Override
	public void eliminarNativo(int idAutor, int idEditorial) throws ModelNotFoundException {
		// TODO Auto-generated method stub
		this.repo.eliminarNativo(idAutor, idEditorial);
	}

	@Override
	public List<AutorEditorial> editorialPorAutor(int idAutor) {
		// TODO Auto-generated method stub
		List<AutorEditorial> lista = repo.findByAutor_Id(idAutor);
		for(AutorEditorial l: lista) {
			l.setAutor(null);
			l.setFecha(null);
		};
		return lista;
	}

	@Override
	public List<AutorEditorial> autorPorEditorial(int idEditorial) {
		// TODO Auto-generated method stub
		List<AutorEditorial> lista = repo.findByEditorial_Id(idEditorial);
		for(AutorEditorial l: lista) {
			l.setEditorial(null);
			l.setFecha(null);
		};
		return lista;
	}

}
