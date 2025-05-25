package br.com.pacientes.bean;

import br.com.pacientes.dao.MedicoDAO;
import br.com.pacientes.model.Medico;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class MedicoBean {
    private Medico medico = new Medico();
    private MedicoDAO dao = new MedicoDAO();

    public void salvar() {
        dao.salvar(medico);
        medico = new Medico(); // Limpa o formulário
    }

    public void remover(Medico m) {
        dao.remover(m);
    }

    public List<Medico> getMedicos() {
        return dao.listar();
    }

    // Getters para o enum (importante para o select)
    public Medico.Especialidade[] getEspecialidades() {
        return Medico.Especialidade.values();
    }

    // Getters e Setters normais
    public Medico getMedico() { return medico; }
    public void setMedico(Medico medico) { this.medico = medico; }
}