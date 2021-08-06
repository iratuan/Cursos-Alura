package br.com.alura.jpa.testes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.alura.jpa.modelo.MediaComData;

public class TestaMediaDiariaMovimentacoesVersao3 {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("alura");
		EntityManager em = emf.createEntityManager();
		String jpql = "select new br.com.alura.jpa.modelo.MediaComData(day(m.data), month(m.data), year(m.data), avg(m.valor)) from Movimentacao m group by day(m.data), month(m.data), year(m.data)";
		TypedQuery<MediaComData> query = em.createQuery(jpql, MediaComData.class);

		List<MediaComData> mediasMovimentacoes = query.getResultList();
		for (MediaComData mcd : mediasMovimentacoes) {
			System.out.println("MEDIA: " + mcd.getMedia() + ": DATA: " + mcd.getFullData());
		}
	}
}
