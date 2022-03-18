package com.algaworks.algalog.domain.service;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.algaworks.algalog.domain.exception.NegocioException;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.model.StatusEntrega;
import com.algaworks.algalog.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FinalizarEntregaService {
	
	private EntregaRepository entregaRepository;
	private BuscaEntregaService buscaEntregaService;
	
	@Transactional
	public void finalizar(Long entregaId) {
		Entrega entrega = buscaEntregaService.buscar(entregaId);
		if (!entrega.getStatus().equals(StatusEntrega.PENDENTE)) {
			throw new NegocioException("Entrega não pode ser finalizada");	
		}				
		entrega.setStatus(StatusEntrega.FINALIZADA);
		entregaRepository.save(entrega);
	}

}
