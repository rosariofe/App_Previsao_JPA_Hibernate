package br.usjt.ccp3bn_bua1_previsao_tempo.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.usjt.ccp3bn_bua1_previsao_tempo.ConverteStringForDate;
import br.usjt.ccp3bn_bua1_previsao_tempo.model.DiaDaSemana;
import br.usjt.ccp3bn_bua1_previsao_tempo.model.PrevisaoTempo;

public class UpdatePrevisaoEDia {
	
	public static void main (String[] args) {
		
		EntityManager manager = JPAUtil.getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		
		transaction.begin();
		PrevisaoTempo previsaoTempo = manager.find(PrevisaoTempo.class, 1L);
		previsaoTempo.setDescricao("Sol com muitas nuvens");
		previsaoTempo.setTempMin(16.0);
		previsaoTempo.setTempMax(25.0);
		previsaoTempo.setUmidade(80);
		
		ConverteStringForDate convData = new ConverteStringForDate();
		previsaoTempo.setDataHora(convData.convertDate("12/04/2020 12:10"));
			
		DiaDaSemana diaDaSemana = manager.find(DiaDaSemana.class, previsaoTempo.getDiaDaSemana().getId());
		
		diaDaSemana.setNomeDaSemana("Domingo");
		
		transaction.commit();
		
		manager.close();
		JPAUtil.close();

	}
}
