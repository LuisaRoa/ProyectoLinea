package unicundi.edu.co.libreria.ServiceImp;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import unicundi.edu.co.libreria.Entity.Usuario;
import unicundi.edu.co.libreria.Exception.ArgumentRequiredException;
import unicundi.edu.co.libreria.Exception.ConflictException;
import unicundi.edu.co.libreria.Exception.ModelNotFoundException;
import unicundi.edu.co.libreria.Repo.IUsuarioRepo;
import unicundi.edu.co.libreria.Service.IUsuarioService;

@Service
public class UsuarioServiceImp implements IUsuarioService, UserDetailsService{
	
	@Autowired
	private IUsuarioRepo repo;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@Override
	public Page<Usuario> retornarPaginado(int page, int size) {
		// TODO Auto-generated method stub
		Page<Usuario> usuario = repo.findAll(PageRequest.of(page, size));
		return usuario;
	}

	@Override
	public Page<Usuario> retornarPaginado(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario retonarPorId(Integer id, boolean bandera) throws ModelNotFoundException {
		// TODO Auto-generated method stub
		Optional<Usuario> usuario = repo.findById(id);
		if (usuario.isPresent()) {
			return usuario.get();
		} else {
			throw new ModelNotFoundException("Usuario no encontrado");
		}
	}

	@Override
	public void guardar(Usuario usuario)
			throws ConflictException, ArgumentRequiredException, ModelNotFoundException {
		// TODO Auto-generated method stub
		if (repo.existsByCedula(usuario.getCedula())) {
			throw new ConflictException("Cedula ya existe");
		}
		usuario.setClave(bcrypt.encode(usuario.getClave()));
		this.repo.save(usuario);
	}

	@Override
	public void editar(Usuario usuario) throws ArgumentRequiredException, ModelNotFoundException, ConflictException {
		// TODO Auto-generated method stub
		if (validarExistenciaPorId(usuario.getId())) {
			Usuario usuarioAux = this.repo.findById(usuario.getId()).get();
			usuarioAux.setNombre(usuario.getNombre());
			usuarioAux.setApellido(usuario.getApellido());
			usuarioAux.setNick(usuario.getNick());
			if (usuario.getCedula() != null) {
				if (usuario.getCedula().equals(usuarioAux.getCedula())) {

				} else {
					if (repo.existsByCedula(usuario.getCedula())) {
						throw new ConflictException("Cedula duplicada");
					} else {
						usuarioAux.setCedula(usuario.getCedula());
					}
				}
			}
			this.repo.save(usuarioAux);

		} else
			throw new ModelNotFoundException("Usuario no encontradO");
	}

	@Override
	public void eliminar(int id) throws ModelNotFoundException {
		// TODO Auto-generated method stub
		if (validarExistenciaPorId(id))
			this.repo.deleteById(id);
		else
			throw new ModelNotFoundException("Usuario no encontrado");
	}
	
	private Boolean validarExistenciaPorId(int id) {
		return repo.existsById(id);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Usuario usuario = repo.findOneByNick(username);
		if (usuario == null) {
			new UsernameNotFoundException("--Usuario no encontrado");
		}
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority(usuario.getRol().getNombre()));
		UserDetails ud = new User(usuario.getNick(), usuario.getClave(), roles);
		return ud;
	}

}
