package com.ufc.br.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ufc.br.model.Pessoa;
import com.ufc.br.repository.PessoaRepository;

@Repository
@Transactional
public class UserDetailsServiceImplementation implements UserDetailsService{

	@Autowired
	private PessoaRepository repo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Pessoa pessoa = repo.findByEmail(username);
		
		if(pessoa == null) {
			throw new UsernameNotFoundException("pessoa not");
			
		}
		return new User(pessoa.getEmail(),pessoa.getSenha(),true,true,true,true,pessoa.getAuthorities());
	}

}
