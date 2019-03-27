package Scenes;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import org.joml.Vector2f;
import org.joml.Vector4f;

import Components.CHitButton;
import DabRhythm.Beat;
import DabRhythm.LanePosCalculator;
import DabRhythm.Main;
import Entities.Entity;
import Entities.EntityManager;
import Entities.Components.*;
import Graphics.Models.Texture;
import Systems.KeyPressSystem;
import Systems.NoteDeleteSystem;
import Systems.NoteScrollSystem;
import Systems.PolygonRenderSystem;
import Systems.SongManagerSystem;
import Systems.SpriteRenderSystem;
import System.ActionSystem;
import Utils.Timer;

public class GameScene extends Scene {

    private Beat beat_ref;
    private WeakReference<ArrayList<Entity>> cache = new WeakReference<>(new ArrayList<>());
    public static boolean beat_start;
    public static float strumLine = Main.engine.getMainWindow().getHeight() * 0.85f;

    public GameScene(Beat beat_ref) {
        this.beat_ref = beat_ref;
    }

    @Override
    public void init() {
        EntityManager.entities.clear();
        for(int i = 1; i < 5; i++){
            int _lane = i;
            CHitButton hb = new CHitButton(){
                {
                    lane = _lane;
                    pressTexture = new Texture("Skins/mania-key" + lane + ".png");
                    unpressTexture = new Texture("Skins/mania-key" + lane + "D.png");
                }
            };
            Entity e = EntityManager.createEntity(
                hb,
                new CSprite(){
                    {
                        texture = hb.unpressTexture;
                        color = new Vector4f(1);
                        center_anchor = true;
                    }
                },
                new CTransform(){
                    {   
                        size = new Vector2f(50, 50);
                        pos = new Vector2f(LanePosCalculator.calc(_lane, size.x),
                         strumLine);
                    }
                }
            );
        }

        addSystem(new KeyPressSystem());
        addSystem(new SpriteRenderSystem());
        addSystem(new ActionSystem());
        addSystem(new SongManagerSystem(beat_ref));
        addSystem(new NoteScrollSystem());
        addSystem(new PolygonRenderSystem());
        addSystem(new NoteDeleteSystem());

        Timer.start();
    }

    boolean called1, called2, called3, called4, called5;
    @Override
    public void tick() {
        super.tick();
        if(Timer.counter >= 1 * Main.engine.TARGET_FPS && !called1){
            Entity three = spawnCountdown(new Texture("Skins/count3.png"));
                cache.get().add(three);
                called1 = true;
        }
        if(Timer.counter >= 2 * Main.engine.TARGET_FPS && !called2){
            EntityManager.deleteEntity(cache.get().get(0).entityID);
            cache.get().remove(0);
            Entity two = spawnCountdown(new Texture("Skins/count2.png"));
            cache.get().add(two);
            called2 = true;
        }
        if(Timer.counter >= 3 * Main.engine.TARGET_FPS && !called3){
            EntityManager.deleteEntity(cache.get().get(0).entityID);
            cache.get().remove(0);
            Entity one = spawnCountdown(new Texture("Skins/count1.png"));
            cache.get().add(one);
            called3 = true;
        }
        if(Timer.counter >= 4 * Main.engine.TARGET_FPS && !called4){
            EntityManager.deleteEntity(cache.get().get(0).entityID);
            cache.get().remove(0);
            Entity go = spawnCountdown(new Texture("Skins/Go.png"));
            cache.get().add(go);
            called4 = true;
        }
        if(Timer.counter >= 5 * Main.engine.TARGET_FPS && !called5){
            EntityManager.deleteEntity(cache.get().get(0).entityID);
                cache.get().remove(0);
                cache.clear();
                Timer.stop();
                called5 = true;
                beat_start = true;
        }
    }

    private Entity spawnCountdown(Texture tex){
        Entity num = EntityManager.createEntity
        (
            new CSprite(){
                {
                    texture = tex;
                    color = new Vector4f(1);
                    center_anchor = true;
                }
            },
            new CTransform(){
                {
                    pos = new Vector2f(Main.engine.getMainWindow().getWidth()/2, Main.engine.getMainWindow().getHeight()/2);
                    size = new Vector2f(200);
                }
            }
        );
        num.addComponent(new CAction(){
            {
                action = new CustomAction(){
                    
                    @Override
                    public void update(){
                        CTransform t = num.getComponent(CTransform.class);
                        t.size.sub(50/(1*(float)Main.engine.TARGET_FPS), 50/(1*(float)Main.engine.TARGET_FPS));
                    }

                };
            }
        });
        return num;
    }
}