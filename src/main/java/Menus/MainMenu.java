package Menus;

import org.joml.Vector2f;
import org.joml.Vector4f;

import DabRhythm.Main;
import GUI.*;
import GUI.Objects.Button;
import GUI.Objects.Panel;
import Graphics.Batch.Polygon;
import Graphics.Models.Texture;
import Input.KeyEvent;
import Input.MouseEvent;
import Observer.Event;
import Scenes.SceneManager;
import Scenes.SongBrowserScene;

import static org.lwjgl.glfw.GLFW.*;

public class MainMenu extends AbstractMenu {
    
    private boolean main_button_pressed_once;

    public MainMenu(){
        Panel panel = new Panel(){
            {
                pos = new Vector2f(0);
                size = new Vector2f(Main.engine.getMainWindow().getWidth(), Main.engine.getMainWindow().getHeight());
            }
        };
    
        Button start = new Button(){

            {
                pos = new Vector2f((Main.engine.getMainWindow().getWidth()/2)+200f, (Main.engine.getMainWindow().getHeight()/2)-45);
                size = new Vector2f(88, 40);

                label = "Start";
                poly = new Polygon(
                    new int[]{
                        0,1,2,
                        0,3,2
                    }, 
                    new Vector2f[]{
                        new Vector2f(-1, 1),
                        new Vector2f(-1, -1),
                        new Vector2f(1, -1),
                        new Vector2f(1, 1)
                    }
                );
                color = new Vector4f(0.24f, 0.52f, 0.78f, 1f);
                label_color = new Vector4f(1);
                label_pos = new Vector2f(0);
                label_size = 24f;
            }

            @Override
            public void onHover() {
                super.onHover();
                color = new Vector4f(0.03f, 0.22f, 0.39f, 1f);
            }

            @Override
            public void onExit() {
                super.onExit();
                color = new Vector4f(0.24f, 0.52f, 0.78f, 1f);
            }

            @Override
            public void action() {
                SceneManager.setCurrentScene(SceneManager.getScene(SongBrowserScene.class));
            }
        };

        Button exit = new Button(){

            {
                pos = new Vector2f((Main.engine.getMainWindow().getWidth()/2)+200f, (Main.engine.getMainWindow().getHeight()/2)+45);
                size = new Vector2f(88, 40);

                label = "Exit";
                poly = new Polygon(
                    new int[]{
                        0,1,2,
                        0,3,2
                    }, 
                    new Vector2f[]{
                        new Vector2f(-1, 1),
                        new Vector2f(-1, -1),
                        new Vector2f(1, -1),
                        new Vector2f(1, 1)
                    }
                );
                color = new Vector4f(0.24f, 0.52f, 0.78f, 1f);
                label_color = new Vector4f(1);
                label_pos = new Vector2f(0);
                label_size = 24f;
            }
            
            @Override
            public void onHover() {
                super.onHover();
                color = new Vector4f(0.03f, 0.22f, 0.39f, 1f);
            }

            @Override
            public void onExit() {
                super.onExit();
                color = new Vector4f(0.24f, 0.52f, 0.78f, 1f);
            }

            @Override
            public void action() {
                Main.engine.end();
            }
        };

        Button main_button = new Button(){
            {
                pos = new Vector2f(Main.engine.getMainWindow().getWidth()/2, Main.engine.getMainWindow().getHeight()/2);
                size = new Vector2f(Main.engine.getMainWindow().getHeight()*0.65f);

                label = "DabRhythm";
                poly = new Polygon(
                    new int[]{
                        0,1,2,
                        0,3,2
                    },
                    new Vector2f[]{
                        new Vector2f(-1, -1),
                        new Vector2f(-1, 1),
                        new Vector2f(1, 1),
                        new Vector2f(1, -1)
                    }
                );
                color = new Vector4f(0.45f);
                label_color = new Vector4f(1);
                label_size = 72f;
                label_pos = new Vector2f(0);
            }

            @Override
            public void onHover() {
                super.onHover();
                size = new Vector2f((Main.engine.getMainWindow().getHeight()*0.65f)+50f);
                label_size = 72f+50f;
            }

            @Override
            public void onExit() {
                super.onExit();
                size = new Vector2f((Main.engine.getMainWindow().getHeight()*0.65f)-50f);
                label_size = 72f;
            }

            @Override
            public void action() {
                if(!main_button_pressed_once){
                    pos.x -= 100f;
                    main_button_pressed_once = true;
                    panel.addToPanel(start);
                    panel.addToPanel(exit);
                    return;
                }
                if(main_button_pressed_once){
                    pos.x += 100f;
                    main_button_pressed_once = false;
                    panel.panel_objects.remove(start);
                    panel.panel_objects.remove(exit);
                    return;
                }
            }
        };

        panel.addToPanel(main_button);

        obj.add(panel);
    }
}