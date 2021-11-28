package unicundi.edu.co.libreria.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "usuario")
public class Usuario {

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
	
	@NotNull(message = "nick es obligatorio")
	@Size(min = 3, max = 15, message = "El nick debe estar entre 3 y 15 caracteres")	
	@Column(name = "nick", length = 15, nullable = false)
	private String nick;
	
	@NotNull(message = "clave es obligatorio")
	@Column(name = "clave", columnDefinition = "TEXT", nullable = false)
	private String clave;
	
	@ManyToOne
	@JoinColumn(name = "id_rol", nullable = false, foreignKey = @ForeignKey(name = "FK_rol"))
	private Rol rol;
	
	public Usuario() {
		
	}

	public Usuario(Integer id,
			@NotNull(message = "Cedula es obligatorio") @Size(min = 7, max = 12, message = "El Cedula debe estar entre 7 y 12 caracteres") String cedula,
			@NotNull(message = "Nombre es obligatorio") @Size(min = 3, max = 15, message = "El nombre debe estar entre 3 y 15 caracteres") String nombre,
			@NotNull(message = "Apellido es obligatorio") @Size(min = 3, max = 15, message = "El apellido debe estar entre 3 y 15 caracteres") String apellido,
			@NotNull(message = "nick es obligatorio") @Size(min = 3, max = 15, message = "El nick debe estar entre 3 y 15 caracteres") String nick,
			@NotNull(message = "clave es obligatorio") String clave, Rol rol) {
		super();
		this.id = id;
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.nick = nick;
		this.clave = clave;
		this.rol = rol;
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

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	
	
	
}

