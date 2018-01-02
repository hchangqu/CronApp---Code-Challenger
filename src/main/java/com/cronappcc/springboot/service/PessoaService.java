package com.cronappcc.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cronappcc.springboot.model.Pessoa;
import com.cronappcc.springboot.repository.PessoaRepository;

@Service
@Transactional
public class PessoaService{

	@Autowired 
	private PessoaRepository pessoaRepository;

	public Pessoa findById(Long id) {
		return pessoaRepository.findOne(id);
	}

	public Pessoa findByNome(String nome) {
		return pessoaRepository.findByNome(nome);
	}

	public void save(Pessoa pessoa) {
		pessoaRepository.save(pessoa);
	}
	
	public void deletePessoaById(Long id){
		pessoaRepository.delete(id);
	}

	public void deleteAll(){
		pessoaRepository.deleteAll();
	}

	public List<Pessoa> findAll(){
		return pessoaRepository.findAll();
	}

	public boolean existePessoa(Pessoa pessoa) {
		return findByNome(pessoa.getNome()) != null;
	}

}
