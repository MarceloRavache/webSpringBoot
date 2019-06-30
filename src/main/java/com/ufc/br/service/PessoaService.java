package com.ufc.br.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ufc.br.model.Compra;
import com.ufc.br.model.Item;
import com.ufc.br.model.Pessoa;
import com.ufc.br.model.Prato;
import com.ufc.br.model.Role;
import com.ufc.br.repository.CompraRepository;
import com.ufc.br.repository.PessoaRepository;
import com.ufc.br.repository.PratoRepository;
import com.ufc.br.repository.RoleRepository;



@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repoPessoa;
	@Autowired
	private RoleRepository repo;
	
	public Pessoa buscarEmail(String email) {
		return repoPessoa.findByEmail(email);
	}
	
	public void cadastrar(Pessoa pessoa) {
		pessoa.setSenha(new BCryptPasswordEncoder().encode(pessoa.getSenha()));

        Role role = repo.findByPapel("ROLE_USER");
        role.setPapel("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        roles.add(role);

        pessoa.setRoles(roles);

        repoPessoa.save(pessoa);
	}
	public Pessoa buscarId(Long id) {
		return repoPessoa.getOne(id);
	}
	
}
