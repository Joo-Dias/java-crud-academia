package edu.academia;

import java.time.LocalDate;
import java.util.List;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AlunoControl {

    private IntegerProperty id = new SimpleIntegerProperty(0);
    private StringProperty nome = new SimpleStringProperty("");
    private StringProperty sobrenome = new SimpleStringProperty("");
    private StringProperty sexo = new SimpleStringProperty("");
    private StringProperty esporte = new SimpleStringProperty("");
    private ObjectProperty<LocalDate> dataRegistro = new SimpleObjectProperty<>(LocalDate.now());
    private ObservableList<Aluno> lista = FXCollections.observableArrayList();
    private AlunoDAO alunoDAO = new AlunoDAOImpl();

    public IntegerProperty idProperty() {
        return this.id;
    }

    public StringProperty nomeProperty() {
        return this.nome;
    }

    public StringProperty sobrenomeProperty() {
        return this.sobrenome;
    }

    public StringProperty sexoProperty() {
        return this.sexo;
    }

    public StringProperty esporteProperty() {
        return this.esporte;
    }

    public ObjectProperty<LocalDate> dataRegistroProperty() {
        return this.dataRegistro;
    }

    public ObservableList<Aluno> getLista() {
        return this.lista;
    }

    public void salvar() {
        Aluno a = new Aluno();
        a.setId(id.get());
        a.setNome(nome.get());
        a.setSobrenome(sobrenome.get());
        a.setSexo(sexo.get());
        a.setEsporte(esporte.get());
        a.setDataRegistro(dataRegistro.get());

        alunoDAO.salvar(a);
        lerTodos();
    }

    public void lerTodos() {
        List<Aluno> alunos = alunoDAO.lerTodos();
        lista.clear();
        lista.addAll( alunos );
    }

    public void ler() {
        List<Aluno> alunos = alunoDAO.pesquisarNome(nome.get());
        lista.clear();
        lista.addAll( alunos );
    }

}
