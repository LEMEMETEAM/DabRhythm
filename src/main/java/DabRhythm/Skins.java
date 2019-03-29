package DabRhythm;

import java.io.File;
import java.util.ArrayList;
import java.util.WeakHashMap;

public class Skins {

    public static final File skinFolder = new File("Skins");
    public static final WeakHashMap<String, File> skins = new WeakHashMap<>();

    public static void updateSkins(){
        outerloop:for(final File f : skinFolder.listFiles()){
            if(f.isDirectory()){
                String skinName = f.getName();
                for(String s : skins.keySet()){
                    if(!skinName.equals(s)){
                        continue;
                    }
                    else{
                        continue outerloop;
                    }
                }
                skins.put(skinName, f);
            }
        }
    }
}