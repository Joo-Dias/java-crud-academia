package edu.academia;

import java.util.List;

public interface InstrutorDAO {

    void salvar(Instrutor i);
    List<Instrutor> lerTodos();
    List<Instrutor> pesquisarNome(String nome);
}
