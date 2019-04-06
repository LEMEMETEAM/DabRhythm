package DabRhythm;

import java.io.File;

import Graphics.Models.Texture;

public class SkinLoader {

    public static void load(String skinName){
        File temp = Skins.skins.get(skinName);
        for(File f : temp.listFiles()){
            if(f.isFile()){
                String name = stripExtension(f.getName()).replace("@2x", "");
                if(name.contains("mania-key")){
                    if(name.contains("D")){
                        int num = Integer.valueOf(name.replace("mania-key", "").replace("D", ""));
                        Main.Skin.mania_key_down[num - 1] = new Texture(f.getAbsolutePath());
                        continue;
                    }
                    else{
                        int num = Integer.valueOf(name.replace("mania-key", ""));
                        Main.Skin.mania_key[num - 1] = new Texture(f.getAbsolutePath());
                        continue;
                    }
                }
                if(name.contains("score")){
                    if(name.contains("x")){
                        Main.Skin.score_x = new Texture(f.getAbsolutePath());
                        continue;
                    }
                    else if(name.contains("percent")){
                        Main.Skin.score_percent = new Texture(f.getAbsolutePath());
                        continue;
                    }
                    else{
                        int num = Integer.valueOf(name.replace("score-", ""));
                        Main.Skin.score[num] = new Texture(f.getAbsolutePath());
                        continue;
                    }
                }
                if(name.contains("default")){
                    int num = Integer.valueOf(name.replace("default-", ""));
                    Main.Skin.normal[num] = new Texture(f.getAbsolutePath());
                    continue;
                }
                if(name.contains("combo")){
                    int num = Integer.valueOf(name.replace("combo-", ""));
                    Main.Skin.combo[num] = new Texture(f.getAbsolutePath());
                    continue;
                }
                if(name.contains("note")){
                    int num = Integer.valueOf(name.replace("mania-note", ""));
                    Main.Skin.note[num - 1] = new Texture(f.getAbsolutePath());
                    continue;
                }
                if(name.contains("count")){
                    int num = Integer.valueOf(name.replace("count", ""));
                    Main.Skin.count[num - 1] = new Texture(f.getAbsolutePath());
                    continue;
                }
                if(name.equals("Go")){
                    Main.Skin.go = new Texture(f.getAbsolutePath());
                    continue;
                }
                if(name.contains("mania-hit")){
                    int num = Integer.valueOf(name.replace("mania-hit", ""));
                    switch(num){
                        case 50:
                            Main.Skin.hit50 = new Texture(f.getAbsolutePath());
                        case 100:
                            Main.Skin.hit100 = new Texture(f.getAbsolutePath());
                        case 200:
                            Main.Skin.hit200 = new Texture(f.getAbsolutePath());
                        case 300:
                            Main.Skin.hit300 = new Texture(f.getAbsolutePath());
                        case 0:
                            Main.Skin.hit0 = new Texture(f.getAbsolutePath());
                    }
                }
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