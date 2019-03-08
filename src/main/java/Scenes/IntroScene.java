package Scenes;

import Entities.*;
import Entities.Components.CRender;
import Entities.Components.CTransform;
import GUI.Components.CText;
import Graphics.Models.Texture;
import Systems.FadeSystem;
import Systems.SpriteRenderSystem;
import Systems.TextRenderSystem;

import org.joml.*;

import Components.CBatchable;
import Components.CFade;
import Components.CSprite;
import DabRhythm.Main;
import Utils.*;

public class IntroScene extends Scene {

    @Override
    public void init() {
        EntityManager.entities.clear();
        Entity background = EntityManager.createEntity(
            new CRender(){
                {
                    texture = new Texture("src/main/resources/bg.jpg");
                    color = new Vector4f(1, 1, 1, 0);
                    center_anchor = false;
                }
            },
            new CTransform(){
                {
                    size = new Vector2f(Main.engine.getMainWindow().getWidth(), Main.engine.getMainWindow().getWidth());
                }
            },
            new CBatchable(),
            new CSprite(),
            new CFade(){
                {
                    time = 3;
                    in_and_out = 1;
                    timeBetweenInOut = 2;
                }
            }
        );

        Entity text = EntityManager.createEntity(
            new CRender(){
                {
                    color = new Vector4f(1, 1, 1, 0);
                }
            },
            new CTransform(){
                {
                    pos = new Vector2f(25, Main.engine.getMainWindow().getHeight()/2);
                    size = new Vector2f(24);
                }
            },
            new CText(){
                {
                    text = "DabRhythm";
                }
            },
            new CBatchable(),
            new CFade(){
                {
                    time = 3;
                    in_and_out = 1;
                    timeBetweenInOut = 2;
                }
            }
        );
        
        this.addSystem(new TextRenderSystem());
        this.addSystem(new SpriteRenderSystem());
        this.addSystem(new FadeSystem());
    }

    @Override
    public void tick() {
        Timer.start();
        super.tick();
        if(Timer.counter >= 8 * Main.engine.TARGET_FPS){
            Timer.stop();
            SceneManager.setCurrentScene(SceneManager.getScene(MenuScene.class));
        }
    }

}