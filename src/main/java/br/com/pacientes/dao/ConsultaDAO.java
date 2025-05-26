package br.com.pacientes.dao;

import br.com.pacientes.model.Consulta;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class ConsultaDAO {
    private EntityManager em;

    public ConsultaDAO() {
        this.em = Persistence.createEntityManagerFactory("default").createEntityManager();
    }

    public void agendar(Consulta consulta) {
        try {
            em.getTransaction().begin();
            if (consulta.getId() == null) {
                em.persist(consulta);
            } else {
                em.merge(consulta);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao agendar consulta", e);
        }
    }

    public List<Consulta> listarTodas() {
        return em.createQuery(
                        "SELECT DISTINCT c FROM Consulta c " +
                                "LEFT JOIN FETCH c.paciente " +
                                "LEFT JOIN FETCH c.medico",
                        Consulta.class)
                .getResultList();
    }

    public void excluir(Consulta consulta) {
        try {
            em.getTransaction().begin();
            consulta = em.find(Consulta.class, consulta.getId());
            em.remove(consulta);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao excluir consulta", e);
        }
    }
}