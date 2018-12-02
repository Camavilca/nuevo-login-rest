package com.integrador.cms.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.integrador.cms.entity.Documento;

@Repository("documentoRepository")
public interface DocumentoRepository extends JpaRepository<Documento, Serializable> {
	
	public abstract Documento findById(Long id);

}
