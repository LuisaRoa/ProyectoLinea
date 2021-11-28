package unicundi.edu.co.libreria.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "editorial")
public class Editorial {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "Nit es obligatorio")
	@Column(name = "nit", length = 12, nullable = false, unique = true)
	private String nit;	
	
	@NotNull(message = "Nombre es obligatorio")
	@Size(min = 3, max = 25, message = "El nombre debe estar entre 3 y 25 caracteres")
	@Column(name = "nombre", length = 25, nullable = false)
	private String nombre;
	
	@NotNull(message = "correo es obligatorio")
	@Email(message = "Email incorrecto")
	@Column(name = "correo", length = 60, nullable = false, unique = true)
	private String correo;
	
	public Editorial() {
		
	}

	public Editorial(@NotNull(message = "Nit es obligatorio") String nit,
			@NotNull(message = "Nombre es obligatorio") @Size(min = 3, max = 25, message = "El nombre debe estar entre 3 y 25 caracteres") String nombre,
			@NotNull(message = "correo es obligatorio") @Email(message = "Email incorrecto") String correo) {
		super();
		this.nit = nit;
		this.nombre = nombre;
		this.correo = correo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
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
		Editorial other = (Editorial) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
