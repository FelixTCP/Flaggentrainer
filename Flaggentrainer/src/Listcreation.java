import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Listcreation {
    public static ArrayList<Land> create() throws IOException {
        //In landListe werden die Objekte der Klasse Land verwaltet
        ArrayList<Land> landListe = new ArrayList<>();

        //names wird benötigt, um die Objetke der Klasse Land entsprechend mit name und tdl zu erstellen
        ArrayList<String> names = new ArrayList<>();

        //das Dokument "flag.txt" wird eingescannt und in names eingefügt
        String path = new File("Flaggentrainer\\resources")
                .getAbsolutePath();
        try (BufferedReader br = new BufferedReader(new FileReader(path + "\\names.txt"))) {
            while (br.ready()) {
                names.add(br.readLine());
            }
        }

        //Die Objekte der Klasse Land werden erstellt und in landListe eingefügt
        for (int i = 0; i < names.size(); i++) {
            String n = names.get(i);
            String[] constructArray = n.split("/"); //aus dem String "name/tdl" wir dein Array [name,tdl]
            String flagDir = path + "\\flags\\" + constructArray[1] + ".png";

            landListe.add(new Land(constructArray[0], i, flagDir, constructArray[1]));
        }
        return landListe;
    }
}
