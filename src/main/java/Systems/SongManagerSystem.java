package Systems;

import java.util.Arrays;
import java.util.Set;
import Utils.Timer;
import java.util.Map.Entry;
import java.util.logging.Level;

import Audio.Audio;
import DabRhythm.Beat;
import Factories.NoteFactory;
import Graphics.Graphics;
import Scenes.GameScene;
import System.System;

public class SongManagerSystem extends System {

    public Beat beat;
    public float songPosInBeats;
    public float secPerBeat;
    private int currentIndex;
    private float scroll_num;

    private boolean XMOD = true, CMOD;
    private int XMOD_val = 2, CMOD_val;

    public float noteFallTimeInSeconds;

    public SongManagerSystem(Beat beat) {
        this.beat = beat;

        secPerBeat = 60f / beat.BPM;

        noteFallTimeInSeconds = XMOD == true && CMOD == false ? secPerBeat * XMOD_val : XMOD == false && CMOD == true ? secPerBeat = CMOD_val : 0;
    }

    boolean executed;

    @Override
    public void update() {
        if(GameScene.beat_start){
            if(!executed){
                beat.audioFile.playSample();
                executed = true;
            }
            songPosInBeats = (beat.audioFile.getSamplePos()/1000000f) / secPerBeat; 
            float[] keys = toFloatArray(beat.hits.keySet());
            Arrays.sort(keys);
            if(currentIndex < keys.length && songPosInBeats + (noteFallTimeInSeconds/secPerBeat) > keys[currentIndex]){
                String[] lane_info = beat.hits.get(keys[currentIndex]).split("");
                for(int i = 0; i < lane_info.length; i++){
                    if(Integer.parseInt(lane_info[i]) != 0){
                        NoteFactory.spawnNote(i + 1, keys[currentIndex]);
                    }
                }
                currentIndex++;
            }
            if(songPosInBeats + (noteFallTimeInSeconds/secPerBeat) > scroll_num){
                NoteFactory.spawnBeatLine(scroll_num);
                scroll_num += 4;
            }
        }
    }

    @Override
    public void render(Graphics g) {

    }

    private float[] toFloatArray(Set<Float> keyset){
        float[] temp = new float[keyset.size()];
        int i = 0;
        for(Float float1 : keyset){
            temp[i++] = float1;
        }
        return temp;
    }

}