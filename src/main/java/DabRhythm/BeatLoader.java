package DabRhythm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;

import Audio.Music;
import Graphics.Models.Texture;

public class BeatLoader {

    public static Beat load(final File directory){
        return new Beat(){
            {   
                title = directory.getName().split(" - ")[1];
                for(final File f : directory.listFiles()){
                    if(f.getName().contains(".wav") || f.getName().contains(".mp3")){
                        audioFile = new Music(f.getAbsolutePath());
                    }
                    else if(f.getName().contains(".drf")){
                        try(BufferedReader r = new BufferedReader(new FileReader(f))){
                            String line;
                            while((line = r.readLine()) != null){
                                String[] beat_lane_type = line.split(" ");
                                if(beat_lane_type[0].equals("Song:")) continue;
                                if(beat_lane_type[0].equals("BPM:")){ 
                                    BPM = Float.parseFloat(beat_lane_type[1]);
                                    continue;
                                }
                                hits.put(Float.valueOf(beat_lane_type[0]),  beat_lane_type[1]);
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