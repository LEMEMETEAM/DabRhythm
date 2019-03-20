package DabRhythm;

import java.util.ArrayList;
import java.util.HashMap;

import Audio.Audio;
import Graphics.Models.Texture;

public class Beat {

    public Audio audioFile;
    public final HashMap<Integer, Integer> hits = new HashMap<>();
    public Texture background;
    public int id;

}