package com.integrador.cms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.integrador.cms.convertidor.Convertidor;
import com.integrador.cms.entity.Documento;
import com.integrador.cms.model.MDocumento;
import com.integrador.cms.repository.DocumentoRepository;

@Service("documentoService")
public class DocumentoService {
	
	@Autowired
	@Qualifier("documentoRepository")
	private DocumentoRepository documentoRepository;
	
	@Autowired
	private Convertidor convertidor;
	
	public List<MDocumento>listar() {
		return convertidor.convertir(documentoRepository.findAll());
	}
	
	public boolean guardar(Documento documento) {
		try {
			documentoRepository.save(documento);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean eliminar(Long id) {
		try {
			documentoRepository.delete(documentoRepository.findById(id));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public Documento buscarUno(Long id) {
		 return documentoRepository.findById(id);
	}
	
	
}