package unicundi.edu.co.libreria.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import unicundi.edu.co.libreria.Entity.Editorial;


public interface IEditorialRepo extends JpaRepository<Editorial,Integer>{

	public Boolean existsByCorreo(String correo);
	
	public Boolean existsByNit(String nit);
}
