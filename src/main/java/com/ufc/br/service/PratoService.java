package com.ufc.br.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ufc.br.model.Prato;
import com.ufc.br.repository.PratoRepository;
import com.ufc.br.util.ImagemFile;

@Service
public class PratoService {
	@Autowired
	PratoRepository repoPrato;
	
	public void cadastrarPrato(Prato prato,MultipartFile imagem) {
		repoPrato.save(prato);
		
		String caminho = "/home/overnull/eclipse-workspace/trabalho/images/" + prato.getNome() + ".png";
		ImagemFile.salvarImagem(caminho, imagem);
	}
	public void removerPrato(Prato prato) {
		prato.setStatus(0);
		repoPrato.save(prato);
	}
	public List<Prato> buscarPratos(){
		return repoPrato.findByStatus(1);
	}
	public Prato buscarPratoId(Long id) {
		Prato prato = repoPrato.getOne(id);
		return prato;
	}

}
