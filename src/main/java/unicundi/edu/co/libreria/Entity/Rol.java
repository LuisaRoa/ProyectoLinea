package unicundi.edu.co.libreria.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "rol")
public class Rol {

	@Id
	private Integer id;
	
	@NotNull(message = "Nombre es obligatorio")
	@Size(min = 3, max = 15, message = "El nombre debe estar entre 3 y 15 caracteres")
	@Column(name = "nombre", length = 15, nullable = false)
	private String nombre;
	
	@NotNull(message = "Descripción es obligatorio")
	@Column(name = "descripcion", length = 35, nullable = false)
	private String descripcion;

	
	public Rol() {
		
	}
			
	public Rol(@NotNull(message = "Nombre es obligatorio") @Size(min = 3, max = 15, message = "El nombre debe estar entre 3 y 15 caracteres") String nombre,
			@NotNull(message = "Descripción es obligatorio") String descripcion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
