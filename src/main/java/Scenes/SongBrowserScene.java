package Scenes;

import DabRhythm.Beat;
import DabRhythm.Beats;
import DabRhythm.Main;
import Entities.*;
import Entities.Components.*;
import GUI.AbstractMenu;

public class SongBrowserScene extends Scene {

    @Override
    public void init() {
        Entity scroll = EntityManager.createEntity(
            new CMenu(){
                {
                    menu = new AbstractMenu() {
                        {
                            float width = 50;
                            float posx = Main.engine.getMainWindow().getWidth() - width;
                            for(Beat b : Beats.beats){
                                
                            }
                        }
                    };
                }
            }
        );
    }

}