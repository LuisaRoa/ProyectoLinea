package unicundi.edu.co.libreria.Repo;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import unicundi.edu.co.libreria.Entity.AutorEditorial;

@Repository
public interface IAutorEditorialRepo extends JpaRepository<AutorEditorial,Integer>{

	public List<AutorEditorial> findByAutor_Id(Integer id);
	
	public List<AutorEditorial> findByEditorial_Id(Integer id);
	
	@Transactional
	@Modifying
	@Query(value="INSERT INTO autor_editorial(id_autor, id_editorial, fecha) VALUES(:idAutor, :idEditorial, :fecha)", nativeQuery = true)
	void guardarNativo(@Param ("idAutor") Integer idAutor, @Param ("idEditorial") Integer idEditorial, @Param ("fecha") LocalDate fecha);
	
	@Transactional
	@Modifying
	@Query(value="DELETE FROM autor_editorial WHERE id_autor=:idAutor AND id_editorial=:idEditorial", nativeQuery = true)
	void eliminarNativo(@Param ("idAutor") Integer idAutor, @Param ("idEditorial") Integer idEditorial);
	
}
