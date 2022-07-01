import java.io.*;
import java.util.ArrayList;

public class Landliste {
    private ArrayList<Land> deepList;
    private ArrayList<Land> workList;

    /**
     * Override the default constructor
     * Additional information needs to be added
     */
    public Landliste() {
        deepList = new ArrayList<>();
        workList = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();

        InputStream in = getClass().getResourceAsStream("/names.txt");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            while (reader.ready()) {
                names.add(reader.readLine());
            }
            for (String n : names) {
                String[] constructArray = n.split("/"); //aus dem String "name/tdl" wird ein Array [name,tdl]
                //InputStream flagDir = getClass().getResourceAsStream("/flags/" + constructArray[1] + ".png");

                deepList.add(new Land(constructArray[0], constructArray[1], "/flags/" + constructArray[1] + ".png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Land land : deepList) {
            workList.add(land.copy());
        }
    }

    /**
     * Reset the Liste
     */
    public ArrayList<Land> resetListe(){
        workList = new ArrayList<>();
        for (Land land : deepList) {
            workList.add(land.copy());
        }
        return workList;
    }
}