package br.com.pacientes.bean;

import br.com.pacientes.dao.ConsultaDAO;
import br.com.pacientes.dao.MedicoDAO;
import br.com.pacientes.dao.PacienteDAO;
import br.com.pacientes.model.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class ConsultaBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private Consulta consulta = new Consulta();
    private transient ConsultaDAO consultaDAO = new ConsultaDAO();
    private transient MedicoDAO medicoDAO = new MedicoDAO();
    private transient PacienteDAO pacienteDAO = new PacienteDAO();
    private List<Consulta> consultasCache;

    // IDs para seleção
    private Long pacienteId;
    private Long medicoId;

    public void agendar() {
        try {
            // Busca os objetos completos a partir dos IDs
            consulta.setPaciente(pacienteDAO.buscarPorId(pacienteId));
            consulta.setMedico(medicoDAO.buscarPorId(medicoId));

            consultaDAO.agendar(consulta);

            // Limpa o formulário
            consulta = new Consulta();
            pacienteId = null;
            medicoId = null;
            consultasCache = null;
        } catch (Exception e) {
            e.printStackTrace();
            // Adicione aqui tratamento de mensagens para o usuário
        }
    }

    public void excluir(Consulta consulta) {
        try {
            consultaDAO.excluir(consulta);
            consultasCache = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Getters para as listas
    public List<Consulta> getConsultas() {
        if (consultasCache == null) {
            try {
                consultasCache = consultaDAO.listarTodas();
            } catch (Exception e) {
                e.printStackTrace();
                consultasCache = new ArrayList<>();
            }
        }
        return consultasCache;
    }

    public List<Paciente> getPacientes() {
        try {
            return pacienteDAO.listar();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Medico> getMedicos() {
        try {
            return medicoDAO.listar();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Getters e Setters
    public Consulta getConsulta() { return consulta; }
    public void setConsulta(Consulta consulta) { this.consulta = consulta; }
    public Long getPacienteId() { return pacienteId; }
    public void setPacienteId(Long pacienteId) { this.pacienteId = pacienteId; }
    public Long getMedicoId() { return medicoId; }
    public void setMedicoId(Long medicoId) { this.medicoId = medicoId; }
}