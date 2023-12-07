package edu.academia;

import java.time.LocalDate;
import java.util.List;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InstrutorControl {
    private IntegerProperty id = new SimpleIntegerProperty(0);
    private StringProperty nome = new SimpleStringProperty("");
    private StringProperty sobrenome = new SimpleStringProperty("");
    private StringProperty area = new SimpleStringProperty("");
    private StringProperty sexo = new SimpleStringProperty("");
    private ObservableList<Instrutor> lista = FXCollections.observableArrayList();

    private InstrutorDAO instrutorDAO = new InstrutorDAOImpl();

    public IntegerProperty idProperty() { return this.id; }

    public StringProperty nomeProperty() {
        return this.nome;
    }

    public StringProperty sobreNomeProperty() {
        return  this.sobrenome;
    }

    public StringProperty areaProperty() { return this.area; }

    public StringProperty sexoProperty() { return this.sexo; }

    public ObservableList<Instrutor> getLista() {
        return this.lista;
    }

    public void salvar() {
        Instrutor i = new Instrutor();
        i.setId(id.get());
        i.setNome(nome.get());
        i.setSobrenome(sobrenome.get());
        i.setSexo(sexo.get());
        i.setArea(area.get());

        instrutorDAO.salvar(i);
        lerTodos();
    }

    public void lerTodos() {
        List<Instrutor> instrutores = instrutorDAO.lerTodos();
        lista.clear();
        lista.addAll( instrutores );
    }

    public void ler() {
        List<Instrutor> instrutores = instrutorDAO.pesquisarNome(nome.get());
        lista.clear();
        lista.addAll( instrutores );
    }
}
