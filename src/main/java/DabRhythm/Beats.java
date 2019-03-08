package DabRhythm;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

public class Beats {

    public static final File songFolder = new File("Beats");
    public static ArrayList<Beat> beats = new ArrayList<>();

    public static void updateList(){
        outerloop:for(final File f : songFolder.listFiles()){
            if(f.isDirectory()){
                String[] id_title = f.getName().split(" - ");
                for(Beat b : beats){
                    if(b.id != Integer.valueOf(id_title[0])){
                        continue;
                    }
                    else {
                        continue outerloop;
                    }
                }
                beats.add(BeatLoader.load(f));
            }
        }
    }
}