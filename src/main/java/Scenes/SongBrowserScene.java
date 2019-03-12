package Scenes;

import DabRhythm.Beat;
import DabRhythm.Beats;
import DabRhythm.Main;
import Entities.*;
import Entities.Components.*;
import GUI.AbstractMenu;
import Menus.*;
import System.*;

public class SongBrowserScene extends Scene {

    @Override
    public void init() {
        EntityManager.entities.clear();
        Entity scroll = EntityManager.createEntity(
            new CMenu(){
                {
                    /* menu = new AbstractMenu() {
                        {
                            float width = 50;
                            float posx = Main.engine.getMainWindow().getWidth() - width;
                            for(Beat b : Beats.beats){
                                
                            }
                        }
                    }; */
                    menu = new ScrollMenu();
                }
            }
        );
        addSystem(new MenuSystem());
    }

}