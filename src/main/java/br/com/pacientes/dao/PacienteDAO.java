
package br.com.pacientes.dao;

import br.com.pacientes.model.Paciente;
import javax.persistence.*;
import java.util.List;

public class PacienteDAO {
    private EntityManager em = Persistence.createEntityManagerFactory("default").createEntityManager();

    public void salvar(Paciente paciente) {
        em.getTransaction().begin();
        if (paciente.getId() == null) {
            em.persist(paciente);
        } else {
            em.merge(paciente);
        }
        em.getTransaction().commit();
    }

    public List<Paciente> listar() {
        return em.createQuery("FROM Paciente", Paciente.class).getResultList();
    }

    public void remover(Paciente paciente) {
        em.getTransaction().begin();
        paciente = em.find(Paciente.class, paciente.getId());
        em.remove(paciente);
        em.getTransaction().commit();
    }
}
