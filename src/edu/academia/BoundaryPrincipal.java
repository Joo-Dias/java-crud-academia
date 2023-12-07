package edu.academia;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class BoundaryPrincipal extends Application {
    private BoundaryRender alunoBoundary = new AlunoBoundary();
    private BoundaryRender instrutorBoundary = new InstrutorBoundary();
    private BorderPane panPrincipal = new BorderPane();
    @Override
    public void start(Stage stage) throws Exception {
        Scene scn = new Scene(panPrincipal, 800, 600);

        MenuBar mnuPrincipal = new MenuBar();
        Menu mnuFile = new Menu("Arquivo");
        Menu mnuCadastro = new Menu("Gerenciamento");
        MenuItem mnuItemSair = new MenuItem("Sair");
        MenuItem mnuItemAlunos = new MenuItem("Alunos");
        MenuItem mnuItemInstrutores = new MenuItem("Instrutores");
        mnuPrincipal.getMenus().addAll(mnuFile, mnuCadastro);
        mnuFile.getItems().addAll(mnuItemSair);
        mnuCadastro.getItems().addAll(mnuItemAlunos, mnuItemInstrutores);

        mnuItemSair.setOnAction(
                e-> {
                    Platform.exit();
                    System.exit(0);
                }
        );

        mnuItemAlunos.setOnAction(
                e-> {
                    panPrincipal.setCenter(alunoBoundary.render());
                }
        );

        mnuItemInstrutores.setOnAction(
                e-> {
                    panPrincipal.setCenter(instrutorBoundary.render());
                }
        );
        panPrincipal.setTop(mnuPrincipal);
        panPrincipal.setCenter(null);

        stage.setScene(scn);
        stage.setTitle("Gestão Escolar");
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(BoundaryPrincipal.class, args);
    }
}
