package com.cronappcc.springboot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.cronappcc.springboot.model.Pessoa;
import com.cronappcc.springboot.service.PessoaService;

@RestController
@RequestMapping("/api")
public class RestApiController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	PessoaService pessoaService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/pessoa/", method = RequestMethod.GET)
	public ResponseEntity<List<Pessoa>> listAllPessoas() {
		List<Pessoa> pessoas = pessoaService.findAll();
		if (pessoas.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Pessoa>>(pessoas, HttpStatus.OK);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/pessoa/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getPessoa(@PathVariable("id") long id) {
		Pessoa pessoa = pessoaService.findById(id);
		if (pessoa == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Pessoa>(pessoa, HttpStatus.OK);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/pessoa/", method = RequestMethod.POST)
	public ResponseEntity<?> createPessoa(@RequestBody Pessoa pessoa, UriComponentsBuilder ucBuilder) {

		if (pessoaService.existePessoa(pessoa)) {
			return new ResponseEntity(HttpStatus.CONFLICT);
		}
		pessoaService.save(pessoa);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/pessoa/{id}").buildAndExpand(pessoa.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/pessoa/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updatePessoa(@PathVariable("id") long id, @RequestBody Pessoa pessoa) {
		Pessoa pessoaAtual = pessoaService.findById(id);

		if (pessoaAtual == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

		pessoaAtual.setNome(pessoa.getNome());
		pessoaAtual.setIdade(pessoa.getIdade());
		pessoaAtual.setEndereco(pessoa.getEndereco());
		pessoaAtual.setCargo(pessoa.getCargo());

		pessoaService.save(pessoaAtual);
		return new ResponseEntity<Pessoa>(pessoaAtual, HttpStatus.OK);
	}


	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/pessoa/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletePessoa(@PathVariable("id") long id) {
		Pessoa pessoa = pessoaService.findById(id);
		if (pessoa == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		pessoaService.deletePessoaById(id);
		return new ResponseEntity<Pessoa>(HttpStatus.NO_CONTENT);
	}


	@RequestMapping(value = "/pessoa/", method = RequestMethod.DELETE)
	public ResponseEntity<Pessoa> deleteAllPessoas() {
		pessoaService.deleteAll();
		return new ResponseEntity<Pessoa>(HttpStatus.NO_CONTENT);
	}

}