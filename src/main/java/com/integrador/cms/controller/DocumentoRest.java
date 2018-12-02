package com.integrador.cms.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.integrador.cms.entity.Documento;
import com.integrador.cms.model.MDocumento;
import com.integrador.cms.services.DocumentoService;

@RestController
@RequestMapping("/v1")
public class DocumentoRest {
	@Autowired
	@Qualifier("documentoService")
	private DocumentoService documentoService;

	@GetMapping("/documento")
	public List<MDocumento> listar() {
		return documentoService.listar();
	}

	@GetMapping("/documento/{id}")
	public Documento listar(@PathVariable("id") Long id) {
		return documentoService.buscarUno(id);
	}

	@PutMapping("/documento")
	public boolean agregar(@RequestBody @Valid Documento documento) {
		return documentoService.guardar(documento);
	}

	@PostMapping("/documento")
	public boolean actualizar(@RequestBody @Valid Documento documento) {
		return documentoService.guardar(documento);
	}

	@DeleteMapping("/documento/{id}")
	public boolean eliminar(@PathVariable("id") Long id) {
		return documentoService.eliminar(id);
	}

}
