package unicundi.edu.co.libreria.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "autor")
public class Autor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "Cedula es obligatorio")
	@Size(min = 7, max = 12, message = "El Cedula debe estar entre 7 y 12 caracteres")
	@Column(name = "cedula", length = 12, nullable = false, unique = true)
	private String cedula;	
	
	@NotNull(message = "Nombre es obligatorio")
	@Size(min = 3, max = 15, message = "El nombre debe estar entre 3 y 15 caracteres")
	@Column(name = "nombre", length = 15, nullable = false)
	private String nombre;
	
	@NotNull(message = "Apellido es obligatorio")
	@Size(min = 3, max = 15, message = "El apellido debe estar entre 3 y 15 caracteres")	
	@Column(name = "apellido", length = 15, nullable = false)
	private String apellido;
	
	@NotNull(message = "correo es obligatorio")
	@Email(message = "Email incorrecto")
	@Column(name = "correo", length = 60, nullable = false, unique = true)
	private String correo;

	@JsonManagedReference
	@OneToMany(mappedBy = "autor", cascade = { CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Libro> libro;
	
	public Autor() {
		
	}

	public Autor(Integer id,
			@NotNull(message = "Cedula es obligatorio") @Size(min = 7, max = 12, message = "El Cedula debe estar entre 7 y 12 caracteres") String cedula,
			@NotNull(message = "Nombre es obligatorio") @Size(min = 3, max = 15, message = "El nombre debe estar entre 3 y 15 caracteres") String nombre,
			@NotNull(message = "Apellido es obligatorio") @Size(min = 3, max = 15, message = "El apellido debe estar entre 3 y 15 caracteres") String apellido,
			@NotNull(message = "correo es obligatorio") @Email(message = "Email incorrecto") String correo,
			List<Libro> libro) {
		super();
		this.id = id;
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.libro = libro;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	//@JsonManagedReference
	//@JsonIgnore
	public List<Libro> getLibro() {
		return libro;
	}

	public void setLibro(List<Libro> libro) {
		this.libro = libro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Autor other = (Autor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
