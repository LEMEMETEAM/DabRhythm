package DabRhythm;

import java.io.File;

import Graphics.Models.Texture;

public class SkinLoader {

    public static boolean load(String skinName){
        File temp = Skins.skins.get(skinName);
        for(File f : temp.listFiles()){
            if(f.isFile()){
                String name = stripExtension(f.getName()).replace("@2x", "");
                if(name.contains("mania-key"))
            }
        }
    }

    private static String stripExtension (String str) {
        // Handle null case specially.

        if (str == null) return null;

        // Get position of last '.'.

        int pos = str.lastIndexOf(".");

        // If there wasn't any '.' just return the string as is.

        if (pos == -1) return str;

        // Otherwise return the string, up to the dot.

        return str.substring(0, pos);
    }
}