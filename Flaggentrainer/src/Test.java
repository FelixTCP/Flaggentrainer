import java.io.*;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) throws IOException {
        //In landListe werden die Objekte der Klasse Land verwantet
        ArrayList<Land> landListe = new ArrayList<>();

        //names wird benötigt, um die Objetke der Klasse Land entsprechend mit name und tdl zu erstellen
        ArrayList<String> names = new ArrayList<>();


        //das Dokument "C:\\demo\\flag.txt" wird eingescannt und in names eingefügt
        String path = new File("Flaggentrainer\\resources")
                .getAbsolutePath();
        try (BufferedReader br = new BufferedReader(new FileReader(path+"\\names.txt"))) {
            while (br.ready()) {
                names.add(br.readLine());
            }
        }

        //Die Objekte der Klasse Land werden erstellt und in landListe eingefügt
        for (int i = 0; i < names.size(); i++) {
             String n = names.get(i);
             String[] constructArray = n.split("/"); //aus dem String "name/tdl" wir dein Array [name,tdl]
             String flagDir = path+"\\flags\\"+constructArray[1]+".png";

             landListe.add(new Land(constructArray[0],i,flagDir,constructArray[1]));
        }

        //Es wir eine Zufällige Zahl generiert
        int b = (int) (Math.random() * landListe.size());

        //Das zugehörige Objekt in landListe wird ausgegeben
        System.out.println(landListe.get(b).getName());
    }
}
