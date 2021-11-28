package unicundi.edu.co.libreria.ServiceImp;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import unicundi.edu.co.libreria.Entity.Autor;
import unicundi.edu.co.libreria.Entity.Libro;
import unicundi.edu.co.libreria.Exception.ArgumentRequiredException;
import unicundi.edu.co.libreria.Exception.ConflictException;
import unicundi.edu.co.libreria.Exception.ModelNotFoundException;
import unicundi.edu.co.libreria.Repo.IAutorRepo;
import unicundi.edu.co.libreria.Service.IAutorService;

@Service
public class AutorServiceImp implements IAutorService {

	@Autowired
	IAutorRepo repo;

	@Override
	public Page<Autor> retornarPaginado(int page, int size) {
		// TODO Auto-generated method stub
		Page<Autor> autor = repo.findAll(PageRequest.of(page, size));
		autor.getContent().forEach(aut -> {
			aut.setLibro(new ArrayList<>());
		});
		return autor;
	}

	@Override
	public Page<Autor> retornarPaginado(Pageable page) {
		// TODO Auto-generated method stub
		return null; // repo.findAll(page);
	}

	@Override
	public Autor retonarPorId(Integer idAutor, boolean bandera) throws ModelNotFoundException {
		// TODO Auto-generated method stub
		Optional<Autor> autor = repo.findById(idAutor);
		if (autor.isPresent()) {
			if (bandera == true) {
				return autor.get();
			} else {
				Autor autorAux = autor.get();
				autorAux.setLibro(null);
				return autorAux;
			}
		} else {
			throw new ModelNotFoundException("Autor no encontrado");
		}

	}

	@Override
	public void guardar(Autor autor) throws ConflictException, ArgumentRequiredException, ModelNotFoundException {
		// TODO Auto-generated method stub
		if (repo.buscarPorCedula(autor.getCedula())) {
			throw new ConflictException("Autor ya existe");
		}
		if (repo.existsByCorreo(autor.getCorreo())) {
			throw new ConflictException("Correo ya existe");
		}
		if (autor.getLibro() != null) {
			for (Libro lib : autor.getLibro()) {
				lib.setAutor(autor);
			}
		}

		this.repo.save(autor);

	}

	@Override
	public void editar(Autor autor)
			throws ArgumentRequiredException, ModelNotFoundException, ConflictException {
		// TODO Auto-generated method stub
		if (validarExistenciaPorId(autor.getId())) {
			Autor autorAux = this.repo.findById(autor.getId()).get();
			autorAux.setNombre(autor.getNombre());
			autorAux.setApellido(autor.getApellido());
			if (autor.getCedula() != null) {
				if (autor.getCedula().equals(autorAux.getCedula())) {

				} else {
					if (repo.buscarPorCedula(autor.getCedula())) {
						throw new ConflictException("Cedula duplicada");
					} else {
						autorAux.setCedula(autor.getCedula());
					}
				}
			}
			if (repo.buscarPorCorreo(autor.getCorreo(), autor.getId())) {
				throw new ConflictException("Correo ya existe");
			} else {
				autorAux.setCorreo(autor.getCorreo());
			}
			this.repo.save(autorAux);

		} else
			throw new ModelNotFoundException("Autor no encontrado");

	}

	@Override
	public void eliminar(int idAutor) throws ModelNotFoundException {
		// TODO Auto-generated method stub
		if (validarExistenciaPorId(idAutor))
			this.repo.deleteById(idAutor);
		else
			throw new ModelNotFoundException("Autor no encontrado");
	}

	private Boolean validarExistenciaPorId(int idAutor) {
		return repo.existsById(idAutor);
	}

}
