package Menus;

import org.joml.Vector2f;
import org.joml.Vector4f;

import DabRhythm.Main;
import GUI.*;
import Graphics.Models.Texture;
import Input.KeyEvent;
import Input.MouseEvent;
import Observer.Event;
import Scenes.SceneManager;
import Scenes.SongBrowserScene;

import static org.lwjgl.glfw.GLFW.*;

public class MainMenu extends AbstractMenu {
    
    public MainMenu(){
        GUIObject start = new GUIObject(){

            {
                pos = new Vector2f(Main.engine.getMainWindow().getWidth()/2, (Main.engine.getMainWindow().getHeight()/2)-40);
                size = new Vector2f(88, 40);

                tex = new Texture("src/main/resources/button_start.png");
                color = new Vector4f(1);
            }
        
            @Override
            public void onNotify(Event e) {
                
            }
        
            @Override
            public void onMouseRelease(MouseEvent e) {
                
            }
        
            @Override
            public void onMousePress(MouseEvent e) {
                if(hover && e.getButton() == GLFW_MOUSE_BUTTON_LEFT){
                    SceneManager.setCurrentScene(SceneManager.getScene(SongBrowserScene.class));
                }
            }
        
            @Override
            public void onKeyRelease(KeyEvent e) {
                
            }
        
            @Override
            public void onKeyPress(KeyEvent e) {
                
            }
        
            @Override
            public void onHover() {
                tex = new Texture("src/main/resources/button_start_pressed.png");
                hover = true;
            }
        
            @Override
            public void onExit() {
                tex = new Texture("src/main/resources/button_start.png");
                hover = false;
            }
        };

        GUIObject exit = new GUIObject(){

            {
                pos = new Vector2f(Main.engine.getMainWindow().getWidth()/2, (Main.engine.getMainWindow().getHeight()/2)+40);
                size = new Vector2f(76, 40);

                tex = new Texture("src/main/resources/button_exit.png");
                color = new Vector4f(1);
            }
        
            @Override
            public void onNotify(Event e) {
                
            }
        
            @Override
            public void onMouseRelease(MouseEvent e) {
                
            }
        
            @Override
            public void onMousePress(MouseEvent e) {
                if(hover && e.getButton() == GLFW_MOUSE_BUTTON_LEFT){
                    Main.engine.end();
                }
            }
        
            @Override
            public void onKeyRelease(KeyEvent e) {
                
            }
        
            @Override
            public void onKeyPress(KeyEvent e) {
                
            }
        
            @Override
            public void onHover() {
                tex = new Texture("src/main/resources/button_exit_pressed.png");
                hover = true;
            }
        
            @Override
            public void onExit() {
                tex = new Texture("src/main/resources/button_exit.png");
                hover = false;
            }
        };

        obj.add(start);
        obj.add(exit);
    }
}