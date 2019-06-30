package com.ufc.br.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.model.Prato;
import com.ufc.br.service.PratoService;

@Controller
@RequestMapping("/admin")
public class AdministradorController {
	@Autowired
	PratoService servicePrato;
	
	@RequestMapping("/cadastrar")
	public ModelAndView cadastrar() {
		ModelAndView mav = new ModelAndView("cadastrarAdmin");
		mav.addObject(new Prato());
		return mav;
	}
	@RequestMapping(value="/cadastrarPrato")
	public ModelAndView cadastrarPrato(Prato prato,@RequestParam(value="imagem") MultipartFile imagem) {
		ModelAndView mav = new ModelAndView("redirect:/admin/lista");
		prato.setStatus(1);
		servicePrato.cadastrarPrato(prato,imagem);
		
		return mav;
	}
	@RequestMapping("/lista")
	public ModelAndView lista() {
		List<Prato> pratos = servicePrato.buscarPratos();
		ModelAndView mav = new ModelAndView("listaAdmin");
		mav.addObject("listaPratos",pratos);
		return mav;
	}
	@RequestMapping("/remover/{id}")
	public ModelAndView remover(@PathVariable("id") long id) {
		ModelAndView mav = new ModelAndView("redirect:/galery");
		
		Prato prato = servicePrato.buscarPratoId(id);
		
		servicePrato.removerPrato(prato);
		
		return mav;
	}
	@RequestMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable("id") long id) {
		ModelAndView mav = new ModelAndView("/admin/cadastrar");
		
		Prato prato = servicePrato.buscarPratoId(id);
		
		mav.addObject(prato);
		
		return mav;
	} 
}
