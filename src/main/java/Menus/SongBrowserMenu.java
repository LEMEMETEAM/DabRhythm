package Menus;

import org.joml.Vector2f;
import org.joml.Vector4f;

import DabRhythm.Beat;
import DabRhythm.Beats;
import DabRhythm.Main;
import GUI.*;
import GUI.Objects.*;
import Graphics.Batch.Polygon;
import Graphics.Models.Texture;
import Scenes.GameScene;
import Scenes.SceneManager;

public class SongBrowserMenu extends AbstractMenu {

    private Image current_song_image = new Image(){
        {
            this.pos = new Vector2f(Main.engine.getMainWindow().getWidth()/2, Main.engine.getMainWindow().getHeight()/2);
            this.size = new Vector2f(Main.engine.getMainWindow().getWidth(), Main.engine.getMainWindow().getHeight());
            color = new Vector4f(1);
        }
    };
    
    public SongBrowserMenu(){
        HorizontalScrollPanel scroll = new HorizontalScrollPanel(){
            {
                size = new Vector2f(250, Main.engine.getMainWindow().getHeight());
                Vector2f size_temp = size;
                pos = new Vector2f(Main.engine.getMainWindow().getWidth() - size.x, 0);
                for(int i = 0; i < Beats.beats.size(); i++){
                    int index = i;
                    Beat b = Beats.beats.get(index);
                    addToPanel(
                        new Button(){
                            {   
                                label = b.title;
                                this.size = new Vector2f(250, 100);
                                this.pos = new Vector2f(0, size.y * index);
                                color= new Vector4f(0.5f);
                                label_color = new Vector4f(1);
                                label_pos = new Vector2f(0.0f);
                                label_size = 48f;
                                poly = new Polygon(
                                    new int[]{
                                        0,1,2,
                                        0,3,2
                                    },
                                    new Vector2f[]{
                                        new Vector2f(0,0),
                                        new Vector2f(0,1),
                                        new Vector2f(1,1),
                                        new Vector2f(1,0)
                                    }
                                );
                            }

                            boolean change, change2;
                            @Override
                            public void onHover(){
                                change2 = false;
                                super.onHover();
                                if(!change){
                                    size.add(50, 50);
                                    if(b.background != null){
                                        current_song_image.image = b.background;
                                    }
                                }
                                change = true;
                            }

                            @Override
                            public void onExit(){
                                change = false;
                                super.onExit();
                                if(!change2){
                                    size.sub(50, 50);
                                    current_song_image.image = null;
                                }
                                change2 = true;
                            }

                            @Override
                            public void action(){
                                SceneManager.setCurrentScene(new GameScene(b));
                            }
                        }
                    );
                }
            }
        };
        Panel main_panel = new Panel(){
            {
                this.pos = new Vector2f(0);
                this.size = new Vector2f(Main.engine.getMainWindow().getWidth(), Main.engine.getMainWindow().getHeight());
                addToPanel(current_song_image);
                addToPanel(scroll);

            }
        };
        obj.add(main_panel);
    }
}