import java.io.*;
import java.util.ArrayList;


public class Test {
    public static void main(String[] args) throws IOException {
        ArrayList<Land> landListe = new ArrayList<Land>();

        ArrayList<String> names = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("C:\\demo\\flag.txt"))) {
            while (br.ready()) {
                names.add(br.readLine());
            }
        }

        /*
        int laenge = 0;

        try {
            File file = new File("C:\\demo\\flag.txt");
            FileInputStream fis = new FileInputStream(file);

            System.out.println("file content: ");
            int r;


            while ((r = fis.read()) != -1) {
                if (r==0){
                    laenge++;
                }else {
                    System.out.print((char) r);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        */

        for (int i = 0; i < names.size(); i++) {
             String n = names.get(i);
             landListe.add(new Land(n,i,"/"));
        }


        int b = (int) (Math.random() * landListe.size());

        System.out.println(landListe.get(b).getName());
    }
}