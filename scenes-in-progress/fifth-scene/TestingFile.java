import javafx.scene.text.Font;

public class TestingFile{

    public static void main(String [] args){
        System.out.println("Fonts");
        for(String fontName : Font.getFontNames())
            System.out.println(fontName);
    }
}