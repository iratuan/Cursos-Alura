package br.com.alura.jpa.testes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class TestaMediaDiariaMovimentacoes {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("alura");
		EntityManager em = emf.createEntityManager();
		String jpql = "select avg(m.valor) from Movimentacao m group by day(m.data), month(m.data), year(m.data)";
		TypedQuery<Double> query = em.createQuery(jpql, Double.class);
		List<Double> mediasMovimentacoes = query.getResultList();
		for(Double media: mediasMovimentacoes) {
			System.out.println(media);
		}	
	}
}
