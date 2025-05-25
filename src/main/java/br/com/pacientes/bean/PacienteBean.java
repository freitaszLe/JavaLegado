
package br.com.pacientes.bean;

import br.com.pacientes.dao.PacienteDAO;
import br.com.pacientes.model.Paciente;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean
@ViewScoped
public class PacienteBean {
    private Paciente paciente = new Paciente();
    private PacienteDAO dao = new PacienteDAO();

    public void salvar() {
        dao.salvar(paciente);
        paciente = new Paciente();
    }

    public void remover(Paciente p) {
        dao.remover(p);
    }

    public List<Paciente> getPacientes() {
        return dao.listar();
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
