package com.ufc.br.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.model.Compra;
import com.ufc.br.model.Pessoa;
import com.ufc.br.model.Prato;
import com.ufc.br.repository.PratoRepository;
import com.ufc.br.service.PessoaService;


@Controller
@RequestMapping("/pessoa")
public class PessoaController {

	@Autowired
	private PessoaService servico;
	
	
	@RequestMapping("/login")
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
	@RequestMapping("/cadastrar")
	public ModelAndView cadastrar() {
		ModelAndView mv = new ModelAndView("cadastrar");
		mv.addObject("pessoa", new Pessoa());
		return mv;
	}
	@RequestMapping(value="/cadastrarPessoa", method=RequestMethod.POST)
	public ModelAndView cadastrarPessoa(@Validated Pessoa pessoa) {
		ModelAndView mav = new ModelAndView("redirect:/galery");
		
		servico.cadastrar(pessoa);
		
		
		return mav;
	}
}
