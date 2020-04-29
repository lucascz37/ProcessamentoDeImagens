package br.com.edu.main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class ImageHandler {

    private BufferedImage image;
    private BufferedImage filteredImage;

    public ImageHandler(){
        image = null;
        filteredImage = null;
    }

    public void fileChooser(File selected) throws IOException, IllegalArgumentException {
        image = ImageIO.read(selected);
        filteredImage = ImageIO.read(selected);
    }

    public void saveImage(File selected, String extension) throws IOException, NullPointerException {
        String os = System.getProperty("os.name").toLowerCase();
        if(os.contains("win")) {
            ImageIO.write(filteredImage, extension, new File(selected.getPath()));
        }else if(os.contains("nix") || os.contains("nux") || os.indexOf("aix") > 0 ){
            ImageIO.write(filteredImage, extension, new File(selected.getPath() + "." + extension));
        }
    }

    public void bandaR(){
        for (int i = 0; i < image.getHeight(); i++){
            for(int j = 0; j < image.getWidth(); j++){
                Color pixelColor = new Color(image.getRGB(j,i));
                filteredImage.setRGB(j,i, new Color(pixelColor.getRed(),0,0).getRGB());
            }
        }
    }

    public void bandaG(){
        for (int i = 0; i < image.getHeight(); i++){
            for(int j = 0; j < image.getWidth(); j++){
                Color pixelColor = new Color(image.getRGB(j,i));
                filteredImage.setRGB(j,i, new Color(0,pixelColor.getGreen(),0).getRGB());
            }
        }
    }

    public void bandaB(){
        for (int i = 0; i < image.getHeight(); i++){
            for(int j = 0; j < image.getWidth(); j++){
                Color pixelColor = new Color(image.getRGB(j,i));
                filteredImage.setRGB(j,i, new Color(0,0,pixelColor.getBlue()).getRGB());
            }
        }
    }

    public void cinzaR(){
        for (int i = 0; i < image.getHeight(); i++){
            for(int j = 0; j < image.getWidth(); j++){
                Color pixelColor = new Color(image.getRGB(j,i));
                int color = pixelColor.getRed();
                filteredImage.setRGB(j,i, new Color(color,color,color).getRGB());
            }
        }
    }

    public void cinzaG(){
        for (int i = 0; i < image.getHeight(); i++){
            for(int j = 0; j < image.getWidth(); j++){
                Color pixelColor = new Color(image.getRGB(j,i));
                int color = pixelColor.getGreen();
                filteredImage.setRGB(j,i, new Color(color,color,color).getRGB());
            }
        }
    }

    public void cinzaB(){
        for (int i = 0; i < image.getHeight(); i++){
            for(int j = 0; j < image.getWidth(); j++){
                Color pixelColor = new Color(image.getRGB(j,i));
                int color = pixelColor.getBlue();
                filteredImage.setRGB(j,i, new Color(color,color,color).getRGB());
            }
        }
    }

    public void cinzaM(){
        for (int i = 0; i < image.getHeight(); i++){
            for(int j = 0; j < image.getWidth(); j++){
                Color pixelColor = new Color(image.getRGB(j,i));
                int colorM = (pixelColor.getRed()+pixelColor.getGreen()+pixelColor.getBlue())/3;
                filteredImage.setRGB(j,i, new Color(colorM,colorM,colorM).getRGB());
            }
        }
    }

    public PixelYIQ[][] convertRGBtoYIQ(BufferedImage filtered){
        PixelYIQ[][] imageYIQ = new PixelYIQ[filtered.getHeight()][filtered.getWidth()];
        for (int i = 0; i < filtered.getHeight(); i++){
            for(int j = 0; j < filtered.getWidth(); j++){
                Color pixelColor = new Color(filtered.getRGB(j,i));
                double yChannel = (pixelColor.getRed()*0.299) + (pixelColor.getGreen()* 0.587) + (pixelColor.getBlue()*0.114);
                double iChannel = (pixelColor.getRed()*0.596) - (pixelColor.getGreen()* 0.274) - (pixelColor.getBlue()*0.322);
                double qChannel = (pixelColor.getRed()*0.211) - (pixelColor.getGreen()* 0.523) + (pixelColor.getBlue()*0.312);
                imageYIQ[i][j] = new PixelYIQ(yChannel,iChannel,qChannel);
            }
        }
        return imageYIQ;
    }
    
    public BufferedImage convertYIQtoRGB(PixelYIQ[][] imageYIQ){
        BufferedImage convertImage = new BufferedImage(imageYIQ[0].length,imageYIQ.length, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < convertImage.getHeight(); i++){
            for(int j = 0; j < convertImage.getWidth(); j++){
                int red = (int)(imageYIQ[i][j].getY() + (imageYIQ[i][j].getI()*0.956) + (imageYIQ[i][j].getQ()*0.621));
                int green = (int)(imageYIQ[i][j].getY() - (imageYIQ[i][j].getI()*0.272) - (imageYIQ[i][j].getQ()*0.647));
                int blue = (int)(imageYIQ[i][j].getY() - (imageYIQ[i][j].getI()*1.106)+ (imageYIQ[i][j].getQ()*1.703));
                if(red > 255 || red < 0){
                    if(red>255){
                        red = 255;
                    }else{
                        red = 0;
                    }
                }if(green > 255 || green < 0){
                    if (green >255){
                        green = 255;
                    }else{
                        green = 0;
                    }
                }if(blue > 255 || blue <0){
                    if (blue > 255){
                        blue = 255;
                    }else{
                        blue = 0;
                    }
                }
                Color pixelColor = new Color(red, green, blue);
                convertImage.setRGB(j,i,pixelColor.getRGB());
            }
        }
        return convertImage;
    }

    public BufferedImage negativeYIQ(PixelYIQ[][] imageYIQ){
        for (int i = 0; i < imageYIQ.length; i++){
            for(int j = 0; j < imageYIQ[i].length; j++){
                imageYIQ[i][j].setY(255-imageYIQ[i][j].getY());
            }
        }
        return convertYIQtoRGB(imageYIQ);
    }

    public void negativeRGB(){
        for (int i = 0; i < image.getHeight(); i++){
            for(int j = 0; j < image.getWidth(); j++){
                Color pixelColor = new Color(image.getRGB(j,i));
                int colorR = 255-pixelColor.getRed();
                int colorG = 255-pixelColor.getGreen();
                int colorB = 255-pixelColor.getBlue();
                filteredImage.setRGB(j,i, new Color(colorR,colorG,colorB).getRGB());
            }
        }
    }

    public void threshold(int threshold){
        cinzaM();
        for (int i = 0; i < filteredImage.getHeight(); i++){
            for(int j = 0; j < filteredImage.getWidth(); j++){
                Color pixelColor = new Color(filteredImage.getRGB(j,i));
                if(pixelColor.getRed() > threshold){
                    filteredImage.setRGB(j,i, new Color(255, 255, 255).getRGB());
                }else{
                    filteredImage.setRGB(j,i, new Color(0, 0, 0).getRGB());
                }
            }
        }
    }

    public void increaseHueR(int increase){
        for (int i = 0; i < image.getHeight(); i++){
            for(int j = 0; j < image.getWidth(); j++){
                Color pixelColor = new Color(image.getRGB(j,i));
                int colorM = pixelColor.getRed() + increase;
                if(colorM > 255){
                    colorM = 255;
                }
                filteredImage.setRGB(j,i, new Color(colorM, pixelColor.getGreen(), pixelColor.getBlue()).getRGB());
            }
        }
    }

    public void increaseHueG(int increase){
        for (int i = 0; i < image.getHeight(); i++){
            for(int j = 0; j < image.getWidth(); j++){
                Color pixelColor = new Color(image.getRGB(j,i));
                int colorM = pixelColor.getGreen() + increase;
                if(colorM > 255){
                    colorM = 255;
                }
                filteredImage.setRGB(j,i, new Color(pixelColor.getRed(), colorM, pixelColor.getBlue()).getRGB());
            }
        }
    }

    public void increaseHueB(int increase){
        for (int i = 0; i < image.getHeight(); i++){
            for(int j = 0; j < image.getWidth(); j++){
                Color pixelColor = new Color(image.getRGB(j,i));
                int colorM = pixelColor.getBlue() + increase;
                if(colorM > 255){
                    colorM = 255;
                }
                filteredImage.setRGB(j,i, new Color(pixelColor.getRed(), pixelColor.getGreen(), colorM).getRGB());
            }
        }
    }

    public void brilhoAditivoRGB(int increase){
        for (int i = 0; i < image.getHeight(); i++){
            for(int j = 0; j < image.getWidth(); j++){
                Color pixelColor = new Color(image.getRGB(j,i));
                int colorR = pixelColor.getRed() + increase;
                int colorG = pixelColor.getGreen() + increase;
                int colorB = pixelColor.getBlue() + increase;

                if(colorR > 255){
                    colorR = 255;
                }if(colorR < 0){
                    colorR = 0;
                }

                if(colorG > 255){
                    colorG = 255;
                }else if(colorG < 0){
                    colorB = 0;
                }

                if(colorB > 255){
                    colorB = 255;
                }else if(colorB < 0){
                    colorG = 0;
                }

                filteredImage.setRGB(j,i, new Color(colorR, colorG, colorB).getRGB());
            }
        }
    }

    public BufferedImage brilhoAditivoYIQ(int increase){
        PixelYIQ[][] imageYIQ = convertRGBtoYIQ(image);
        for (int i = 0; i < imageYIQ.length; i++){
            for(int j = 0; j < imageYIQ[i].length; j++){
                imageYIQ[i][j].setY(increase+imageYIQ[i][j].getY());
            }
        }
        return convertYIQtoRGB(imageYIQ);
    }

    public void brilhoMultRGB(double increase) {
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                Color pixelColor = new Color(image.getRGB(j, i));
                int colorR = (int) (pixelColor.getRed() * increase);
                int colorG = (int) (pixelColor.getGreen() * increase);
                int colorB = (int) (pixelColor.getBlue() * increase);

                if (colorR > 255) {
                    colorR = 255;
                }
                if (colorR < 0) {
                    colorR = 0;
                }

                if (colorG > 255) {
                    colorG = 255;
                } else if (colorG < 0) {
                    colorB = 0;
                }

                if (colorB > 255) {
                    colorB = 255;
                } else if (colorB < 0) {
                    colorG = 0;
                }

                filteredImage.setRGB(j, i, new Color(colorR, colorG, colorB).getRGB());
            }
        }
    }

    public BufferedImage brilhoMultYIQ(double increase){
        PixelYIQ[][] imageYIQ = convertRGBtoYIQ(image);
        for (int i = 0; i < imageYIQ.length; i++){
            for(int j = 0; j < imageYIQ[i].length; j++){
                imageYIQ[i][j].setY(increase*imageYIQ[i][j].getY());
            }
        }
        return convertYIQtoRGB(imageYIQ);
    }

    public void mediana(int tamMatriz){
        for(int i = 0; i < image.getHeight(); i++){
            for(int j= 0; j < image.getWidth(); j++){
                int[] matriz = new int[tamMatriz*tamMatriz];
                int ordemMatriz = tamMatriz;
                int contador = 0;
                for(int k = 0; k < ordemMatriz; k++){
                    for(int l = 0; l < ordemMatriz; l++){
                        try {
                            Color pixelColor = new Color(image.getRGB(j+l-(ordemMatriz/2),i+k-(ordemMatriz/2)));
                            matriz[contador] = pixelColor.getRed();
                        }catch (ArrayIndexOutOfBoundsException e){
                            matriz[contador] = 0;
                        }
                        contador++;
                    }
                }
                Arrays.sort(matriz);
                int meio = matriz.length/2;
                filteredImage.setRGB(j,i, new Color(matriz[meio], matriz[meio], matriz[meio]).getRGB());
            }
        }
    }

    public void moda(int tamMatriz){
        for(int i = 0; i < image.getHeight(); i++){
            for(int j= 0; j < image.getWidth(); j++){
                int[] matriz = new int[tamMatriz*tamMatriz];
                int ordemMatriz = tamMatriz;
                int contador = 0;
                for(int k = 0; k < ordemMatriz; k++){
                    for(int l = 0; l < ordemMatriz; l++){
                        try {
                            Color pixelColor = new Color(image.getRGB(j+l-(ordemMatriz/2),i+k-(ordemMatriz/2)));
                            matriz[contador] = pixelColor.getRed();
                        }catch (ArrayIndexOutOfBoundsException e){
                            matriz[contador] = 0;
                        }
                        contador++;
                    }
                }
                Arrays.sort(matriz);
                int moda = 0;
                int repeticoes = 0;
                int repeticoesProx = 0;
                for(int m = 0; m<matriz.length-1;m++){
                    if(matriz[m] == matriz[m+1]){
                        if(matriz[m] == moda){
                            repeticoes++;
                            moda=matriz[m];
                        }else{
                            repeticoesProx++;
                            if(repeticoesProx > repeticoes){
                                moda = matriz[m];
                                repeticoes = repeticoesProx;
                                repeticoesProx = 0;
                            }
                        }
                    }else if(matriz[m] != matriz[m+1]){
                        repeticoesProx = 0;
                    }
                }

                if(moda == 0){
                    moda = matriz[matriz.length/2];
                }

                filteredImage.setRGB(j,i, new Color(moda, moda, moda).getRGB());
            }
        }
    }

    public void convolucao(double[] kernel){
        for(int i = 0; i < image.getHeight(); i++){
            for(int j= 0; j < image.getWidth(); j++){
                int ordemMatriz = (int) Math.sqrt(kernel.length);
                int contador = 0;
                int valorPixel = 0;
                for(int k = 0; k < ordemMatriz; k++){
                    for(int l = 0; l < ordemMatriz; l++){
                        try {
                            Color pixelColor = new Color(image.getRGB(j+l-(ordemMatriz/2),i+k-(ordemMatriz/2)));
                            valorPixel += pixelColor.getRed()*kernel[contador];
                        }catch (ArrayIndexOutOfBoundsException e){
                            valorPixel += 0;
                        }
                        contador++;
                    }
                }
                if(valorPixel> 255){
                    valorPixel = 255;
                }else if(valorPixel < 0){
                    valorPixel = 0;
                }
                filteredImage.setRGB(j,i, new Color(valorPixel, valorPixel, valorPixel).getRGB());
            }
        }
    }


    public void ruido(){
        for(int i = 0; i< image.getHeight(); i++){
            for(int j = 0; j< image.getWidth(); j++){
                int valor = image.getRGB(j,i) - filteredImage.getRGB(j,i);
                filteredImage.setRGB(j,i,valor);
            }
        }
    }

    public void setSaltPepper(int probalidade){
        Random random = new Random();
        for(int i = 0; i < image.getHeight(); i++){
            for(int j= 0; j < image.getWidth(); j++){
                if(random.nextInt(100+1) < probalidade){
                    if(random.nextInt(probalidade+1) > probalidade/2){
                        filteredImage.setRGB(j,i, new Color(255, 255, 255).getRGB());
                    }else{
                        filteredImage.setRGB(j,i, new Color(0, 0, 0).getRGB());
                    }
                }else{
                    filteredImage.setRGB(j,i,image.getRGB(j,i));
                }
            }
        }

    }
    public BufferedImage getImage(){
        return image;
    }

    public BufferedImage getFilteredImage(){
        return filteredImage;
    }
}
