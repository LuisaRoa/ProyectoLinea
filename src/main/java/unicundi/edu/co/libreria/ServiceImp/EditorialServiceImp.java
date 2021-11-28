package unicundi.edu.co.libreria.ServiceImp;

import java.awt.print.Pageable;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import unicundi.edu.co.libreria.Entity.Editorial;
import unicundi.edu.co.libreria.Exception.ArgumentRequiredException;
import unicundi.edu.co.libreria.Exception.ConflictException;
import unicundi.edu.co.libreria.Exception.ModelNotFoundException;
import unicundi.edu.co.libreria.Repo.IEditorialRepo;
import unicundi.edu.co.libreria.Service.IEditorialService;


@Service
public class EditorialServiceImp implements IEditorialService{

	@Autowired
	IEditorialRepo repo;
	
	@Override
	public Page<Editorial> retornarPaginado(int page, int size) {
		// TODO Auto-generated method stub
		Page<Editorial> editorial = repo.findAll(PageRequest.of(page, size));
		return editorial;
	}

	@Override
	public Page<Editorial> retornarPaginado(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Editorial retonarPorId(Integer id, boolean bandera) throws ModelNotFoundException {
		// TODO Auto-generated method stub
		Optional<Editorial> editorial = repo.findById(id);
		if (editorial.isPresent()) {
			return editorial.get();
		} else {
			throw new ModelNotFoundException("Editorial no encontrada");
		}
	}

	@Override
	public void guardar(Editorial editorial) throws ConflictException, ArgumentRequiredException, ModelNotFoundException {
		// TODO Auto-generated method stub
		if (repo.existsByNit(editorial.getNit())) {
			throw new ConflictException("Nit ya existe");
		}
		if (repo.existsByCorreo(editorial.getCorreo())) {
			throw new ConflictException("Correo ya existe");
		}

		this.repo.save(editorial);
	}

	@Override
	public void editar(Editorial editorial) throws ArgumentRequiredException, ModelNotFoundException, ConflictException {
		// TODO Auto-generated method stub
		if (validarExistenciaPorId(editorial.getId())) {
			Editorial editorialAux = this.repo.findById(editorial.getId()).get();
			editorialAux.setNombre(editorial.getNombre());
			if (editorial.getNit() != null) {
				if (editorial.getNit().equals(editorialAux.getNit())) {

				} else {
					if (repo.existsByNit(editorial.getNit())) {
						throw new ConflictException("Nit duplicado");
					} else {
						editorialAux.setNit(editorial.getNit());
					}
				}
			}
			if (repo.existsByCorreo(editorial.getCorreo())) {
				throw new ConflictException("Correo ya existe");
			} else {
				editorialAux.setCorreo(editorial.getCorreo());
			}
			this.repo.save(editorialAux);

		} else
			throw new ModelNotFoundException("Editorial no encontrada");
	}

	@Override
	public void eliminar(int id) throws ModelNotFoundException {
		// TODO Auto-generated method stub
		if (validarExistenciaPorId(id))
			this.repo.deleteById(id);
		else
			throw new ModelNotFoundException("Editorial no encontrada");
	}

	private Boolean validarExistenciaPorId(int id) {
		return repo.existsById(id);
	}
}
