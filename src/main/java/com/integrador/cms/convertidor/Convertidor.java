package com.integrador.cms.convertidor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.integrador.cms.entity.Documento;
import com.integrador.cms.model.MDocumento;

@Component("convertidor")
public class Convertidor {

	public List<MDocumento> convertir(List<Documento> documentos) {
		List<MDocumento> mDocumento = new ArrayList<>();
		for (Documento documento : documentos) {
			mDocumento.add(new MDocumento(documento));
		}
		return mDocumento;
	}
}
