package Menus;

import org.joml.Vector2f;
import org.joml.Vector4f;

import DabRhythm.Beat;
import DabRhythm.Beats;
import DabRhythm.Main;
import GUI.*;
import GUI.Objects.*;

public class SongBrowserMenu extends AbstractMenu {
    
    public SongBrowserMenu(){
        HorizontalScrollPanel scroll = new HorizontalScrollPanel(){
            {
                size = new Vector2f(150, Main.engine.getMainWindow().getHeight());
                pos = new Vector2f(Main.engine.getMainWindow().getWidth() - size.x, 0);
                for(Beat b : Beats.beats){
                    
                }
            }
        };
    }
}