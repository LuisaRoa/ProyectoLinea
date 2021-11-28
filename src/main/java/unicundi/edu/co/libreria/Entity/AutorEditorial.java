package unicundi.edu.co.libreria.Entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


@Entity
@Table(name = "autor_editorial")
@IdClass(AutorEditorialPK.class)
public class AutorEditorial {

	@Id
	private Autor autor;
	
	@Id
	private Editorial editorial;
	
	@Column(name= "fecha", nullable= false)
	private LocalDate fecha;
	
	public AutorEditorial() {
		
	}

	public AutorEditorial(Autor autor, Editorial editorial, LocalDate fecha) {
		super();
		this.autor = autor;
		this.editorial = editorial;
		this.fecha = fecha;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public Editorial getEditorial() {
		return editorial;
	}

	public void setEditorial(Editorial editorial) {
		this.editorial = editorial;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	
	
}
