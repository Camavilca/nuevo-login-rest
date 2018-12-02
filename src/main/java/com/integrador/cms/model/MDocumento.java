package com.integrador.cms.model;

import java.util.Date;

import com.integrador.cms.entity.Documento;
public class MDocumento {
	
	
	private Long id;
	private String titulo;
	private String descripcion;
	private String archivo;
	private Date fechaCreacion;
	
	
	public MDocumento(Documento documento) {
		this.id = documento.getId();
		this.titulo = documento.getTitulo();
		this.descripcion = documento.getDescripcion();
		this.archivo = documento.getArchivo();
		this.fechaCreacion = documento.getFechaCreacion();
	}
	
	public MDocumento(Long id, String titulo, String descripcion, String archivo, Date fechaCreacion) {
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.archivo = archivo;
		this.fechaCreacion = fechaCreacion;
	}

	public MDocumento() {

	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getArchivo() {
		return archivo;
	}
	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	
	
	
}
