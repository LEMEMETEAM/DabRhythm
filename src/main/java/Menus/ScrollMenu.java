package Menus;

import org.joml.Vector2f;
import org.joml.Vector4f;

import GUI.*;
import GUI.Objects.Button;
import GUI.Objects.Panel;
import Graphics.Models.Texture;

public class ScrollMenu extends AbstractMenu {
    
    public ScrollMenu(){
        Button button = new Button(){
            {
                pos = new Vector2f(50);
                size = new Vector2f(88, 40);
                tex = new Texture("src/main/resources/button_start.png");
                color = new Vector4f(1);
            }
        };
        Panel panel = new Panel(){
            {
                pos = new Vector2f(500);
                size = new Vector2f(100);
                color = new Vector4f(1);
                addToPanel(button);
            }
        };
        obj.add(panel);
    }
}