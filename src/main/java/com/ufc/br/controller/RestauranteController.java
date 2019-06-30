package com.ufc.br.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.model.Pessoa;
import com.ufc.br.model.Prato;
import com.ufc.br.service.PessoaService;
import com.ufc.br.service.PratoService;

@Controller
public class RestauranteController {
	
	@Autowired
	PratoService servicePrato;
	@RequestMapping("/")
	public String paginaInicial(){
		return "index";
	}
	@RequestMapping("/galery")
	public ModelAndView lista() {
		List<Prato> pratos = servicePrato.buscarPratos();
		ModelAndView mav = new ModelAndView("galery");
		mav.addObject("listaPratos",pratos);
		return mav;
	}
}