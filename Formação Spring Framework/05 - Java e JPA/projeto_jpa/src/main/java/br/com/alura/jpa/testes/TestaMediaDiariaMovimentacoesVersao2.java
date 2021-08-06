package br.com.alura.jpa.testes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class TestaMediaDiariaMovimentacoesVersao2 {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("alura");
		EntityManager em = emf.createEntityManager();
		String jpql = "select avg(m.valor), day(m.data), month(m.data), year(m.data) from Movimentacao m group by day(m.data), month(m.data), year(m.data)";
		Query query = em.createQuery(jpql);

		@SuppressWarnings("unchecked")
		List<Object[]> mediasMovimentacoes = query.getResultList();
		for (Object[] media : mediasMovimentacoes) {
			System.out.println("MEDIA: " + media[0] + ": DATA: " + media[1] + "/" + media[2] + "/" + media[3]);
		}
	}
}
