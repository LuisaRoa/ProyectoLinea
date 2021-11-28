package unicundi.edu.co.libreria.Repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import unicundi.edu.co.libreria.Entity.Libro;

public interface ILibroRepo extends JpaRepository<Libro, Integer>{

	//JPQL
	@Transactional
	@Query(value = "from Libro l where l.autor.id = :idAutor")
	List<Libro> buscarPorAutor(@Param("idAutor") int idAutor);
	
	@Transactional
	public Libro findByIdOrNumeroPaginas(int id, Integer paginas);

	@Transactional
	public List<Libro> findByNumeroPaginasAndAutor_Id(Integer paginas, Integer id);

}
