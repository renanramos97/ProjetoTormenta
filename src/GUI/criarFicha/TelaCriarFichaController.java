package GUI.criarFicha;

import GUI.criarFicha.selecaoRaca.TelaRacasController;
import static database.Database.executarQuery;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import parser.Ficha;

public class TelaCriarFichaController implements Initializable {
        
    @FXML
    private Label textoDado, textoRolagens, raca, classe, modForca, modDestreza, modConstituicao, modInteligencia, modSabedoria, modCarisma;
    
    @FXML
    private Button btnClasses, btnRacas, btnDado, btnNext;
    
    @FXML
    private TextField campoNome, campoForca, campoDestreza, campoConstituicao, campoInteligencia, campoSabedoria, campoCarisma;
    
    private int ficha_id;
    public void setFicha_id(int ficha_id) {
        this.ficha_id = ficha_id;
    }
    
    private Ficha ficha;
    
    @FXML
    public void escolherClasse() throws Exception{
        Stage stage = (Stage) btnClasses.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/criarFicha/selecaoClasse/TelaClasses.fxml"));
        
        Scene telaEscolherClasse = new Scene(loader.load(), 1000, 690);
        stage.setScene(telaEscolherClasse);
    }
    
    @FXML
    public void escolherRaca() throws Exception{
        Stage stage = (Stage) btnRacas.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/criarFicha/selecaoRaca/TelaRacas.fxml"));
        Parent root = loader.load();
        TelaRacasController controller = loader.getController();
        controller.setFicha_id(ficha_id);
        
        Scene telaEscolherRaca = new Scene(root, 1000, 690);
        stage.setScene(telaEscolherRaca);
    }
    
    @FXML
    public void rolarDado() throws Exception{
        Random random = new Random();
        ArrayList<Integer> rolagens = new ArrayList<>();
        int resultado = 0;
        
        for (int i = 0; i < 4; i++){ rolagens.add(random.nextInt(6) + 1); }
        
        int menor = Collections.min(rolagens);
        for (int x : rolagens){ resultado += x; }
        resultado -= menor;
        
        textoDado.setText("Resultado: " + Integer.toString(resultado));
        textoRolagens.setText("Rolagens: " + rolagens);
    }
    
    @FXML
    public void prosseguir() throws Exception{
        //TODO
    }
    
    public void preencherCampos() throws Exception{
        String sql = "SELECT * FROM FICHAS WHERE ID=" + ficha_id;
        ResultSet result;
        result = executarQuery(sql);
        ficha = new Ficha(result);
        result.getStatement().close();
        //System.out.println("Fechando conexão: " + result.getStatement().getConnection());
        result.getStatement().getConnection().close();
        
        campoForca.setText(Integer.toString(ficha.getForca()));
        campoDestreza.setText(Integer.toString(ficha.getDestreza()));
        campoConstituicao.setText(Integer.toString(ficha.getConstituicao()));
        campoInteligencia.setText(Integer.toString(ficha.getInteligencia()));
        campoSabedoria.setText(Integer.toString(ficha.getSabedoria()));
        campoCarisma.setText(Integer.toString(ficha.getCarisma()));
            
        if (ficha.getId_raca() != 0){
            result = executarQuery("SELECT NOME FROM RACAS WHERE ID=" + ficha.getId_raca());
            raca.setText(result.getString("NOME"));
            result.getStatement().close();
            //System.out.println("Fechando conexão: " + result.getStatement().getConnection());
            result.getStatement().getConnection().close();
        } else {
            raca.setText("NÃO ESCOLHIDO");
        }
        if (ficha.getId_classe() != 0){
            result = executarQuery("SELECT NOME FROM CLASSES WHERE ID=" + ficha.getId_classe());
            classe.setText(result.getString("NOME"));
            result.getStatement().close();
            //System.out.println("Fechando conexão: " + result.getStatement().getConnection());
            result.getStatement().getConnection().close();
        } else {
            classe.setText("NÃO ESCOLHIDO");
        }
        
        modForca.setText(Integer.toString(ficha.getModForca()));
        modDestreza.setText(Integer.toString(ficha.getModDestreza()));
        modConstituicao.setText(Integer.toString(ficha.getModConstituicao()));
        modInteligencia.setText(Integer.toString(ficha.getModInteligencia()));
        modSabedoria.setText(Integer.toString(ficha.getModSabedoria()));
        modCarisma.setText(Integer.toString(ficha.getModCarisma()));
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }    
}
