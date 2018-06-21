package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class TelaInicialController implements Initializable {
    
    @FXML
    private Button btnCriarFicha, btnConsultDB, btnCarregarFicha;
    
    @FXML
    private void telaCriarFicha(ActionEvent event) throws Exception {
        Scene sceneAtual = btnCriarFicha.getScene();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaCriarFicha.fxml"));
        sceneAtual.setRoot(loader.load());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}