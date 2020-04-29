package br.com.edu.layout;

import br.com.edu.main.ImageHandler;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.IOException;
import java.util.Optional;


public class MainScreen {
    private ImageHandler handler;

    private FileChooser picker;

    @FXML
    private VBox window;
    @FXML
    private ImageView filtered;

    @FXML
    private ImageView original;

    public MainScreen() {
        picker = new FileChooser();
        picker.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("png", "*.png"),
                new FileChooser.ExtensionFilter("jpeg", "*.jpeg"),
                new FileChooser.ExtensionFilter("jpg", "*.jpg")
        );
        handler = new ImageHandler();
    }

    @FXML
    void openFile() {
        picker.setTitle("Abrir Imagem");
        try {
            handler.fileChooser(picker.showOpenDialog(window.getScene().getWindow()));
        } catch (IllegalArgumentException e) {
            return;
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Falha Ao Carregar Imagem");
            alert.showAndWait();
            return;
        }
        original.setImage(SwingFXUtils.toFXImage(handler.getImage(), null));
        filtered.setImage(SwingFXUtils.toFXImage(handler.getFilteredImage(), null));
        if (handler.getImage().getWidth() > 400) {
            original.setFitHeight(400);
            original.setFitWidth(400);
            filtered.setFitHeight(400);
            filtered.setFitWidth(400);
        } else {
            original.setFitHeight(handler.getImage().getHeight());
            original.setFitWidth(handler.getImage().getWidth());
            filtered.setFitHeight(handler.getImage().getHeight());
            filtered.setFitWidth(handler.getImage().getWidth());
        }
    }

    @FXML
    void saveFile() {
        picker.setTitle("Salvar Imagem");
        try {
            handler.saveImage(picker.showSaveDialog(
                    window.getScene().getWindow()), picker.getSelectedExtensionFilter().getDescription());
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro Ao Salvar");
            alert.showAndWait();
        } catch (NullPointerException ignored) {

        }

    }

    @FXML
    void setBandaB() {
        handler.bandaB();
        filtered.setImage(SwingFXUtils.toFXImage(handler.getFilteredImage(), null));
    }

    @FXML
    void setBandaG() {
        handler.bandaG();
        filtered.setImage(SwingFXUtils.toFXImage(handler.getFilteredImage(), null));
    }

    @FXML
    void setBandaR() {
        handler.bandaR();
        filtered.setImage(SwingFXUtils.toFXImage(handler.getFilteredImage(), null));
    }

    @FXML
    void setCinzaB() {
        handler.cinzaB();
        filtered.setImage(SwingFXUtils.toFXImage(handler.getFilteredImage(), null));
    }

    @FXML
    void setCinzaG() {
        handler.cinzaG();
        filtered.setImage(SwingFXUtils.toFXImage(handler.getFilteredImage(), null));
    }

    @FXML
    void setCinzaM() {
        handler.cinzaM();
        filtered.setImage(SwingFXUtils.toFXImage(handler.getFilteredImage(), null));
    }

    @FXML
    void setCinzaR() {
        handler.cinzaR();
        filtered.setImage(SwingFXUtils.toFXImage(handler.getFilteredImage(), null));
    }

    @FXML
    void setNegativeRGB() {
        handler.negativeRGB();
        filtered.setImage(SwingFXUtils.toFXImage(handler.getFilteredImage(), null));
    }

    @FXML
    void setNegativeY() {
        filtered.setImage(SwingFXUtils.toFXImage(handler.negativeYIQ(handler.convertRGBtoYIQ(handler.getImage())), null));
    }

    @FXML
    void convertRGBtoYIQ() {
        filtered.setImage(SwingFXUtils.toFXImage(handler.convertYIQtoRGB(handler.convertRGBtoYIQ(handler.getImage())), null));

    }

    @FXML
    void setBinary() {
        handler.threshold(intInputValue("Digite o valor de medio"));
        filtered.setImage(SwingFXUtils.toFXImage(handler.getFilteredImage(), null));
    }

    @FXML
    void setAumentativoRGB() {
        handler.brilhoAditivoRGB(intInputValue("Digite o valor de Aumento"));
        filtered.setImage(SwingFXUtils.toFXImage(handler.getFilteredImage(), null));
    }

    @FXML
    void setAumentativoYIQ() {
        filtered.setImage(SwingFXUtils.toFXImage(handler.brilhoAditivoYIQ(intInputValue("Digite o valor de Aumento")), null));
    }

    @FXML
    void setMultiplicativoRGB() {
        handler.brilhoMultRGB(doubleInputValue());
        filtered.setImage(SwingFXUtils.toFXImage(handler.getFilteredImage(), null));
    }

    @FXML
    void setMultiplicativoYIQ() {
        filtered.setImage(SwingFXUtils.toFXImage(handler.brilhoMultYIQ(doubleInputValue()), null));
    }

    @FXML
    void setTonB() {
        handler.increaseHueB(intInputValue("Digite o valor de Aumento"));
        filtered.setImage(SwingFXUtils.toFXImage(handler.getFilteredImage(), null));
    }

    @FXML
    void setTonG() {
        handler.increaseHueG(intInputValue("Digite o valor de Aumento"));
        filtered.setImage(SwingFXUtils.toFXImage(handler.getFilteredImage(), null));
    }

    @FXML
    void setTonR() {
        handler.increaseHueR(intInputValue("Digite o valor de Aumento"));
        filtered.setImage(SwingFXUtils.toFXImage(handler.getFilteredImage(), null));
    }

    @FXML
    void setMediana() {
        handler.mediana(intInputValue("Digite a ordem da Matriz"));
        filtered.setImage(SwingFXUtils.toFXImage(handler.getFilteredImage(), null));
    }

    @FXML
    void setModa() {
        handler.moda(intInputValue("Digite a ordem da Matriz"));
        filtered.setImage(SwingFXUtils.toFXImage(handler.getFilteredImage(), null));
    }

    @FXML
    void setConvolucao() {
        handler.convolucao(doubleArrayInputValue());
        filtered.setImage(SwingFXUtils.toFXImage(handler.getFilteredImage(), null));
    }

    @FXML
    void getRuido(){
        handler.ruido();
        filtered.setImage(SwingFXUtils.toFXImage(handler.getFilteredImage(), null));
    }

    @FXML
    void setSaltAndPepper(){
        int valor  = intInputValue("Probalidade de 0 a 100");
        if(valor > 100 || valor < 0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalido");
            alert.setContentText("Valor Invalido!");
            alert.showAndWait();
        }else{
            handler.setSaltPepper(valor);
            filtered.setImage(SwingFXUtils.toFXImage(handler.getFilteredImage(), null));
        }

    }
    @FXML
    void showCredits() {
        System.out.println("OLAS");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Creditos");
        alert.setContentText("Criador: Lucas Andrade\n Icon made by Freepik from www.flaticon.com");
        alert.showAndWait();
    }

    private double doubleInputValue() {
        TextInputDialog dialog = new TextInputDialog("Digite o Valor");
        dialog.setTitle("Digite o valor multiplicativo");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            return Double.parseDouble(result.get());
        } else {
            return 1;
        }
    }

    private int intInputValue(String texto) {
        TextInputDialog dialog = new TextInputDialog("Digite o Valor");
        dialog.setTitle(texto);
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            return Integer.parseInt(result.get());
        } else {
            return 0;
        }
    }

    private double[] doubleArrayInputValue() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Matriz de ordem impar");
        dialog.setContentText("Digite o array kernel(impar > 9),ex(0.1,0.2,0.3...) : ");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String[] valores = result.get().split(",");
            if (valores.length < 9 && valores.length % 2 == 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Invalido");
                alert.setContentText("Array inserido é inválido!");
                alert.showAndWait();
                return null;
            }
            double[] kernel = new double[valores.length];
            for (int i = 0; i < valores.length; i++) {
                try {
                    kernel[i] = Double.parseDouble(valores[i]);
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Invalido");
                    alert.setContentText("Valor inserido é inválido: " + valores[i]);
                    alert.showAndWait();
                    return null;
                }
                System.out.println(kernel[i]);
            }
            return kernel;
        } else {
            return null;
        }
    }
}
