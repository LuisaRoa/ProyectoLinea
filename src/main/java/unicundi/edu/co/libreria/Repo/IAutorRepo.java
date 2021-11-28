package unicundi.edu.co.libreria.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import unicundi.edu.co.libreria.Entity.Autor;

public interface IAutorRepo extends JpaRepository<Autor,Integer>{

	public Autor findByCedula(String cedula);
	
	public Autor findByCorreo(String correo);
	
	//SQL Nativa	
	@Query(value = "select exists(select * from autor where cedula = :cedula)", nativeQuery  = true)
	Boolean buscarPorCedula(@Param("cedula") String cedula);
	
	//SQL Nativa	
	@Query(value = "select exists(select * from autor where correo = :correo and id <> :id)", nativeQuery  = true)
	Boolean buscarPorCorreo(@Param("correo") String correo, @Param("id") Integer id);
		
	public Boolean existsByCedula(String cedula);
	
	public Boolean existsByCorreo(String correo);
}
