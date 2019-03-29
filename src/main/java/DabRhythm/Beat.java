package DabRhythm;

import java.util.ArrayList;
import java.util.HashMap;

import Audio.Music;
import Graphics.Models.Texture;

public class Beat {

    public Music audioFile;
    public final HashMap<Float, String> hits = new HashMap<>();
    public Texture background;
    public int id;
    public String title;
    public float BPM;
    
}