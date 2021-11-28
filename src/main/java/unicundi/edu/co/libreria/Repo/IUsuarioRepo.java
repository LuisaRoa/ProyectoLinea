package unicundi.edu.co.libreria.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import unicundi.edu.co.libreria.Entity.Usuario;

@Repository
public interface IUsuarioRepo extends JpaRepository<Usuario,Integer>{

	Usuario findOneByNick(String nick);

	boolean existsByCedula(String cedula);
}
