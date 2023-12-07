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
import org.w3c.dom.Text;

public class AlunoBoundary implements BoundaryRender{

    private TextField txtId = new TextField();
    private TextField txtNome = new TextField();
    private TextField txtSobrenome = new TextField();
    private TextField txtSexo = new TextField();
    private TextField txtEsporte = new TextField();
    private TextField txtDataRegistro = new TextField();
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private Button btnAdicionar = new Button("Adicionar");
    private Button btnPesquisar = new Button("Pesquisar");

    private TableView<Aluno> table = new TableView<>();

    private AlunoControl control = new AlunoControl();

    public void generateBindings() {
        Bindings.bindBidirectional(txtId.textProperty(), control.idProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(txtNome.textProperty(), control.nomeProperty());
        Bindings.bindBidirectional(txtSobrenome.textProperty(), control.sobrenomeProperty());
        Bindings.bindBidirectional(txtEsporte.textProperty(), control.esporteProperty());
        Bindings.bindBidirectional(txtSexo.textProperty(), control.sexoProperty());

        StringConverter localDtf = new LocalDateStringConverter(dtf, dtf);
        Bindings.bindBidirectional(txtDataRegistro.textProperty(), control.dataRegistroProperty(), localDtf);
    }

    public void generateTable() {

        table.setItems(control.getLista());

        TableColumn<Aluno, Long> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(
                new PropertyValueFactory<Aluno, Long>("id"));

        TableColumn<Aluno, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(
                itemData -> new ReadOnlyStringWrapper(itemData.getValue().getNome()));

        TableColumn<Aluno, String> colSobrenome = new TableColumn<>("Sobrnome");
        colSobrenome.setCellValueFactory(
                itemData -> new ReadOnlyStringWrapper(itemData.getValue().getSobrenome()));

        TableColumn<Aluno, String> colSexo = new TableColumn<>("Sexo");
        colSexo.setCellValueFactory(
                itemData -> new ReadOnlyStringWrapper(itemData.getValue().getSexo()));

        TableColumn<Aluno, String> colEsporte = new TableColumn<>("Esporte");
        colEsporte.setCellValueFactory(
                itemData -> new ReadOnlyStringWrapper(itemData.getValue().getEsporte()));

        TableColumn<Aluno, String> colDataReg = new TableColumn<>("Data de Registro");
        colDataReg.setCellValueFactory(
                itemData -> new ReadOnlyStringWrapper(
                        dtf.format(itemData.getValue().getDataRegistro())
                ));

        table.getColumns().addAll(colId, colNome, colSobrenome, colSexo, colEsporte, colDataReg);

    }

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
        panFormulario.add(new Label("Esporte: "), 0, 4);
        panFormulario.add(txtEsporte, 1, 4);
        panFormulario.add(new Label("Data de Registro: "), 0, 5);
        panFormulario.add(txtDataRegistro, 1, 5);

        panFormulario.add(btnAdicionar, 0, 7);
        panFormulario.add(btnPesquisar, 1, 7);

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
//        panFormulario.add(new Label("Esporte: "), 0, 4);
//        panFormulario.add(txtEsporte, 1, 4);
//        panFormulario.add(new Label("Data de Registro: "), 0, 5);
//        panFormulario.add(txtDataRegistro, 1, 5);
//
//        panFormulario.add(btnAdicionar, 0, 7);
//        panFormulario.add(btnPesquisar, 1, 7);
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
//        stage.setTitle("Gestão de Alunos");
//        stage.show();
//    }

//    public static void main(String args[]) {
//        Application.launch(AlunoBoundary.class, args);
//    }
}
