package DabRhythm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;

import Audio.Audio;
import Graphics.Models.Texture;

public class BeatLoader {

    public static Beat load(final File directory){
        return new Beat(){
            {
                for(final File f : directory.listFiles()){
                    if(f.getName().contains(".wav") || f.getName().contains(".mp3")){
                        audioFile = new Audio(f.getAbsolutePath());
                    }
                    else if(f.getName().contains(".drf")){
                        try(BufferedReader r = new BufferedReader(new FileReader(f))){
                            String line;
                            while((line = r.readLine()) != null){
                                String[] beat_lane_type = line.split(" ");
                                hits.add(Integer.valueOf(beat_lane_type[0]),  Integer.valueOf(beat_lane_type[1]));
                            }
                        }
                        catch(IOException e){
                            e.printStackTrace();
                        }
                    }
                    else if(f.getName().contains(".png") || f.getName().contains(".jpg")){
                        background = new Texture(f.getAbsolutePath());
                    }
                }
            }
        };
    }
}