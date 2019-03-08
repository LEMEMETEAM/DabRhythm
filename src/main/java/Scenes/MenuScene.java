package Scenes;

import org.joml.Vector2f;
import org.joml.Vector4f;

import DabRhythm.Main;
import Entities.*;
import Entities.Components.*;
import GUI.AbstractMenu;
import GUI.GUIObject;
import Graphics.Models.Texture;
import Menus.MainMenu;
import System.*;

public class MenuScene extends Scene {

    @Override
    public void init() {
        EntityManager.entities.clear();
        Entity main_menu = EntityManager.createEntity(
            new CMenu(){
                {
                    menu = new MainMenu();
                }
            }
        );

        addSystem(new MenuSystem());
    }

}