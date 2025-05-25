package br.com.pacientes.dao;

import br.com.pacientes.model.Medico;
import javax.persistence.EntityManager;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
public class MedicoDAO {
    private EntityManager em = Persistence.createEntityManagerFactory("default").createEntityManager();

    public void salvar(Medico medico) {
        em.getTransaction().begin();
        if (medico.getId() == null) {
            em.persist(medico);
        } else {
            em.merge(medico);
        }
        em.getTransaction().commit();
    }

    public List<Medico> listar() {
        return em.createQuery("FROM Medico", Medico.class).getResultList();
    }

    public void remover(Medico medico) {
        em.getTransaction().begin();
        medico = em.find(Medico.class, medico.getId());
        em.remove(medico);
        em.getTransaction().commit();
    }
}