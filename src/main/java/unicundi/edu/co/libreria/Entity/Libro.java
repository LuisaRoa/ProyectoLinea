package unicundi.edu.co.libreria.Entity;

import java.time.LocalDate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.ForeignKey;


@Entity
@Table(name = "libro")
public class Libro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "Nombre es obligatorio")
	@Size(min = 3, max = 50, message = "El nombre debe estar entre 3 y 50 caracteres")
	@Column(name = "nombre", length = 50, nullable = false)
	private String nombre;	
	
	@NotNull(message = "Nombre es obligatorio")
	@Past(message = "Debe ser una fecha anterior a la actual")
	@Column(name = "fechaPublicacion",nullable = false)
	private LocalDate  fechaPublicacion;	
	
	@NotNull(message = "descripcion es obligatorio")
	@Lob @Basic (fetch = FetchType.LAZY)
	@Column (name = "descripcion", nullable = false, columnDefinition = "text") 
	private String descripcion;	
	
	@NotNull(message = "numero es obligatorio")
	@Min(value = 10, message="El libro debe tener minimo 10 paginas")
    @Max(value = 200, message="El libro debe tener máximo 200 paginas")
	@Column(name = "numero_paginas", nullable = false)
	private Integer numeroPaginas;
	

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "id_autor", nullable = false, foreignKey = @ForeignKey(name = "FK_Autor_Libro"))
	private Autor autor;
	
	public Libro() {
		
	}

	public Libro(Integer id,
			@NotNull(message = "Nombre es obligatorio") @Size(min = 3, max = 50, message = "El nombre debe estar entre 3 y 50 caracteres") String nombre,
			@NotNull(message = "Nombre es obligatorio") @Past(message = "Debe ser una fecha anterior a la actual") LocalDate  fechaPublicacion,
			@NotNull(message = "descripcion es obligatorio") String descripcion,
			@NotNull(message = "numero es obligatorio") @Min(value = 10, message = "El libro debe tener minimo 10 paginas") @Max(value = 200, message = "El libro debe tener máximo 200 paginas") Integer numeroPaginas,
			Autor autor) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fechaPublicacion = fechaPublicacion;
		this.descripcion = descripcion;
		this.numeroPaginas = numeroPaginas;
		this.autor = autor;
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

	public LocalDate  getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(LocalDate  fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getNumeroPaginas() {
		return numeroPaginas;
	}

	public void setNumeroPaginas(Integer numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}

	
	//@JsonIgnore
	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	

	
	
	
}
