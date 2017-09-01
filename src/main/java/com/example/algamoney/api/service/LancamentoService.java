package com.example.algamoney.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.algamoney.api.model.Lancamento;
import com.example.algamoney.api.model.Pessoa;
import com.example.algamoney.api.repository.LancamentoRepository;
import com.example.algamoney.api.repository.PessoaRepository;
import com.example.algamoney.api.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;

//	public Lancamento atualizar(Long codigo, Lancamento lancamento) {
//		Lancamento lancamentoSalva = buscarLancamentoPeloCodigo(codigo);
//
//		BeanUtils.copyProperties(lancamento, lancamentoSalva, "codigo");
//		return lancamentoRepository.save(lancamentoSalva);
//	}

//	private Lancamento buscarLancamentoPeloCodigo(Long codigo) {
//		Lancamento lancamentoSalva = lancamentoRepository.findOne(codigo);
//
//		if (lancamentoSalva == null) {
//			throw new EmptyResultDataAccessException(1);
//		}
//		return lancamentoSalva;
//	}

	public Lancamento salvar(Lancamento lancamento) {
		Pessoa pessoa = pessoaRepository.findOne(lancamento.getPessoa().getCodigo());
		if (pessoa == null || pessoa.isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
		
		return lancamentoRepository.save(lancamento);
	}

}
