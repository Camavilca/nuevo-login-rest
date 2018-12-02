package com.integrador.cms.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.integrador.cms.entity.Documento;
import com.integrador.cms.services.DocumentoService;

@Controller
@SessionAttributes("documento")
public class DocumentoControllers {

	@Autowired
	@Qualifier("documentoService")
	private DocumentoService documentoService;

	@RequestMapping(value = "/listar")
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de papers");
		model.addAttribute("documentos", documentoService.listar());
		return "listar";
	}

	@RequestMapping(value = "/form")
	public String form(Map<String, Object> map) {
		Documento documento = new Documento();
		map.put("documento", documento);
		map.put("titulo", "Registro de documentos");
		map.put("btn", "Guardar proyecto");
		return "form";
	}

	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Documento documento, @RequestParam("file") MultipartFile foto, BindingResult result,
			SessionStatus status) {
		if (result.hasErrors()) {
			return "form";
		}
		if (!foto.isEmpty()) {

			if (documento.getId() != null && documento.getId() > 0 && documento.getArchivo() != null
					&& documento.getArchivo().length() > 0) {

				Path rootPath = Paths.get("uploads").resolve(documento.getArchivo()).toAbsolutePath();
				File archivo = rootPath.toFile();
				if (archivo.exists() && archivo.canRead()) {
					archivo.delete();
				}

			}
			String nombreUnico = UUID.randomUUID().toString() + "_" + foto.getOriginalFilename();
			Path rootPath = Paths.get("uploads").resolve(nombreUnico);
			Path rootAbsolutePath = rootPath.toAbsolutePath();

			try {
				Files.copy(foto.getInputStream(), rootAbsolutePath);
				documento.setArchivo(nombreUnico);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		documentoService.guardar(documento);
		status.setComplete();
		return "redirect:/listar";
	}

	@RequestMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String fileName) {
		Path pathFoto = Paths.get("uploads").resolve(fileName).toAbsolutePath();
		Resource recurso = null;
		try {
			recurso = new UrlResource(pathFoto.toUri());
			if (!recurso.exists() && !recurso.isReadable()) {
				throw new RuntimeException("Error no se puede cargar la img");
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment ; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}

	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		Documento documento = null;
		if (id > 0) {
			documento = documentoService.buscarUno(id);
		} else {
			return "redirect:/listar";
		}
		model.put("titulo", "Editar Documentos");
		model.put("documento", documento);
		model.put("btn", "Editar proyecto");
		return "form";
	}

	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable("id") Long id) {
		Documento documento = documentoService.buscarUno(id);

		Path rootPath = Paths.get("uploads").resolve(documento.getArchivo()).toAbsolutePath();
		File archivo = rootPath.toFile();
		if (archivo.exists() && archivo.canRead()) {
			archivo.delete();
		}
		documentoService.eliminar(id);
		return "redirect:/listar";
	}

}
