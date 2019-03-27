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
    
    public MainMenu(){
        Panel panel = new Panel(){
            {
                pos = new Vector2f(0);
                size = new Vector2f(Main.engine.getMainWindow().getWidth(), Main.engine.getMainWindow().getHeight());
            }
        };
        Button start = new Button(){

            {
                pos = new Vector2f(Main.engine.getMainWindow().getWidth()/2, (Main.engine.getMainWindow().getHeight()/2)-45);
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
                pos = new Vector2f(Main.engine.getMainWindow().getWidth()/2, (Main.engine.getMainWindow().getHeight()/2)+45);
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

        panel.addToPanel(start);
        panel.addToPanel(exit);

        obj.add(panel);
    }
}