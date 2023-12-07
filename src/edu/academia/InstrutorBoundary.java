package edu.academia;

import java.time.format.DateTimeFormatter;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;
import javafx.util.converter.LocalDateTimeStringConverter;
import javafx.util.converter.NumberStringConverter;

public class InstrutorBoundary implements BoundaryRender {
    private TextField txtId = new TextField();
    private TextField txtNome = new TextField();
    private TextField txtSobrenome = new TextField();
    private TextField txtArea = new TextField();
    private TextField txtSexo = new TextField();
    private Button btnAdicionar = new Button("Adicionar");
    private Button btnPesquisar = new Button("Pesquisar");
    private TableView<Instrutor> table = new TableView<>();

    private InstrutorControl control = new InstrutorControl();

    public void generateBindings() {
        Bindings.bindBidirectional(txtId.textProperty(), control.idProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(txtNome.textProperty(), control.nomeProperty());
        Bindings.bindBidirectional(txtSobrenome.textProperty(), control.sobreNomeProperty());
        Bindings.bindBidirectional(txtArea.textProperty(), control.areaProperty());
        Bindings.bindBidirectional(txtSexo.textProperty(), control.sexoProperty());
    }

    public void generateTable() {
        table.setItems( control.getLista() );

        TableColumn<Instrutor, Long> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(
                new PropertyValueFactory<Instrutor, Long>("id") );

        TableColumn<Instrutor, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(
                itemData ->	new ReadOnlyStringWrapper(itemData.getValue().getNome()));

        TableColumn<Instrutor, String> colSobrenome = new TableColumn<>("Sobrenome");
        colSobrenome.setCellValueFactory(
                itemData ->	new ReadOnlyStringWrapper(itemData.getValue().getSobrenome()));

        TableColumn<Instrutor, String> colArea = new TableColumn<>("Área");
        colArea.setCellValueFactory(
                itemData ->	new ReadOnlyStringWrapper(itemData.getValue().getArea()));

        TableColumn<Instrutor, String> colSexo = new TableColumn<>("Sexo");
        colSexo.setCellValueFactory(
                itemData ->	new ReadOnlyStringWrapper(itemData.getValue().getSexo()));

        table.getColumns().addAll(colId, colNome, colSobrenome, colSexo, colArea);
    }

    @Override
    public Pane render() {
        BorderPane panPrincipal = new BorderPane();
        GridPane panFormulario = new GridPane();
        panFormulario.add(new Label("Código: "), 0, 0);
        panFormulario.add(txtId, 1, 0);
        panFormulario.add(new Label("Nome: "), 0, 1);
        panFormulario.add(txtNome, 1, 1);
        panFormulario.add(new Label("Sobrenome: "), 0, 2);
        panFormulario.add(txtSobrenome, 1, 2);
        panFormulario.add(new Label("Sexo: "), 0, 3);
        panFormulario.add(txtSexo, 1, 3);
        panFormulario.add(new Label("Área: "), 0, 4);
        panFormulario.add(txtArea, 1, 4);
        panFormulario.add(btnAdicionar, 0, 5);
        panFormulario.add(btnPesquisar, 1, 5);

        btnAdicionar.setOnAction( e-> control.salvar() );
        btnPesquisar.setOnAction( e -> control.ler() );

        panPrincipal.setCenter(table);
        panPrincipal.setTop(panFormulario);

        generateBindings();
        generateTable();

        return panPrincipal;
    }

//    @Override
//    public void start(Stage stage) throws Exception {
//        BorderPane panPrincipal = new BorderPane();
//        Scene scn = new Scene(panPrincipal, 600, 400);
//
//        GridPane panFormulario = new GridPane();
//        panFormulario.add(new Label("Código: "), 0, 0);
//        panFormulario.add(txtId, 1, 0);
//        panFormulario.add(new Label("Nome: "), 0, 1);
//        panFormulario.add(txtNome, 1, 1);
//        panFormulario.add(new Label("Sobrenome: "), 0, 2);
//        panFormulario.add(txtSobrenome, 1, 2);
//        panFormulario.add(new Label("Sexo: "), 0, 3);
//        panFormulario.add(txtSexo, 1, 3);
//        panFormulario.add(new Label("Área: "), 0, 4);
//        panFormulario.add(txtArea, 1, 4);
//        panFormulario.add(btnAdicionar, 0, 5);
//        panFormulario.add(btnPesquisar, 1, 5);
//
//        btnAdicionar.setOnAction( e-> control.salvar() );
//        btnPesquisar.setOnAction( e -> control.ler() );
//
//        panPrincipal.setCenter(table);
//        panPrincipal.setTop(panFormulario);
//
//        generateBindings();
//        generateTable();
//
//        stage.setScene(scn);
//        stage.setTitle("Gestão de Instrutores");
//        stage.show();
//
//    }
//
//    public static void main(String args[]) {
//        Application.launch(InstrutorBoundary.class, args);
//    }
}
