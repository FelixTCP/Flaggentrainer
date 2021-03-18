import java.io.*;
import java.util.ArrayList;


public class Test {
    public static void main(String[] args) throws IOException {
        ArrayList<Land> landListe = new ArrayList<>();

        ArrayList<String> names = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("C:\\demo\\flag.txt"))) {
            while (br.ready()) {
                names.add(br.readLine());
            }
        }

        for (int i = 0; i < names.size(); i++) {
             String n = names.get(i);
             landListe.add(new Land(n,i,"/"));
        }

        int b = (int) (Math.random() * landListe.size());

        System.out.println(landListe.get(b).getName());
    }
}
