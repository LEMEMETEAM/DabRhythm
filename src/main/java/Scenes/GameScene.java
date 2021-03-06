package Scenes;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;

import org.joml.Vector2f;
import org.joml.Vector4f;

import Components.CHUD;
import Components.CHitButton;
import DabRhythm.Beat;
import DabRhythm.LanePosCalculator;
import DabRhythm.Main;
import Entities.Entity;
import Entities.EntityManager;
import Entities.Components.*;
import GUI.Components.CText;
import Graphics.Models.AABB;
import Graphics.Models.Texture;
import Input.InputHandler;
import Systems.HUDRenderSystem;
import Systems.KeyPressSystem;
import Systems.NoteDeleteSystem;
import Systems.NoteHitSystem;
import Systems.NoteScrollSystem;
import Systems.PolygonRenderSystem;
import Systems.SongManagerSystem;
import Systems.SpriteRenderSystem;
import Systems.TextRenderSystem;
import System.ActionSystem;
import Utils.Timer;

import static org.lwjgl.glfw.GLFW.*;

public class GameScene extends Scene {

    private Beat beat_ref;
    private WeakReference<ArrayList<Entity>> cache = new WeakReference<>(new ArrayList<>());
    public static boolean beat_start;
    public static float strumLine = Main.engine.getMainWindow().getHeight() * 0.85f;
    public static long score;
    public static long combo;
    public static float accuracy;
    public static int scoreLength = 6, comboLength = 2;

    public GameScene(Beat beat_ref) {
        this.beat_ref = beat_ref;
    }

    @Override
    public void init() {
        EntityManager.entities.clear();
        score = 0;
        combo = 0;
        accuracy = 0.00f;
        Entity image = EntityManager.createEntity(
            new CSprite(){
                {
                    texture = beat_ref.background;
                    color = new Vector4f(1f,1f,1f,0.375f);
                    center_anchor = false;
                }
            },
            new CTransform(){
                {
                    pos = new Vector2f();
                    size = new Vector2f(Main.engine.getMainWindow().getWidth(), Main.engine.getMainWindow().getHeight());
                }
            }
        );
        for(int i = 1; i < 5; i++){
            int _lane = i;
            CHitButton hb = new CHitButton(){
                {
                    lane = _lane;
                    pressTexture = Main.Skin.mania_key[lane - 1];
                    unpressTexture = Main.Skin.mania_key_down[lane - 1];
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
            e.addComponent(new CCollision(){
                {
                    bounds = new AABB();
                    bounds.correctBounds(e);
                }
            });
        }

        float pos_Y = 100F;
        for(int c = comboLength - 1; c >= 0; c--){
            int C = c;
            Entity comboE = EntityManager.createEntity(
                new CSprite(){
                    {
                        texture = Main.Skin.combo[0];
                        color = new Vector4f(1);
                    }
                },
                new CTransform(){
                    {
                        size = new Vector2f(Main.Skin.combo[0].width/4, Main.Skin.combo[0].height/4);
                        pos = new Vector2f((size.x * C) + (Main.engine.getMainWindow().getWidth()/2), pos_Y);
                    }
                },
                new CHUD(){
                    {
                        hud = HUD.COMBO;
                        unit = C;
                    }
                }
            );
        }

        for(int s = scoreLength - 1; s >= 0; s--){
            int S = s;
            Entity scoreE = EntityManager.createEntity(
                new CSprite(){
                    {
                        texture = Main.Skin.score[0];
                        color = new Vector4f(1);
                    }
                },
                new CTransform(){
                    {
                        size = new Vector2f(Main.Skin.score[0].width/2, Main.Skin.score[0].height/2);
                        pos = new Vector2f((size.x * S) + (Main.engine.getMainWindow().getWidth()/2), pos_Y - size.y);
                    }
                },
                new CHUD(){
                    {
                        hud = HUD.SCORE;
                        unit = S;
                    }
                }
            );
        }

        /*char[] numsA = String.valueOf(accuracy).toCharArray();
        for(int a = numsA.length - 1; a >= 0; a--){
            int A = a;
            Entity accE = EntityManager.createEntity(
                new CSprite(){
                    {
                        texture = Main.Skin.normal[Integer.parseInt(String.valueOf(numsA[A]))];
                        color = new Vector4f(1);
                    }
                },
                new CTransform(){
                    {
                        size = new Vector2f(Main.Skin.normal[0].width/8, Main.Skin.normal[0].height/8);
                        pos = new Vector2f((size.x * A) + (Main.engine.getMainWindow().getWidth()/2), pos_Y + (Main.Skin.combo[0].height/8));
                    }
                },
                new CHUD(){
                    {
                        hud = HUD.ACC;
                    }
                }
            );
        }*/
        
        addSystem(new KeyPressSystem());
        addSystem(new SpriteRenderSystem());
        addSystem(new ActionSystem());
        addSystem(new SongManagerSystem(beat_ref));
        addSystem(new NoteScrollSystem());
        addSystem(new PolygonRenderSystem());
        addSystem(new NoteDeleteSystem());
        addSystem(new NoteHitSystem());
        addSystem(new TextRenderSystem());
        addSystem(new HUDRenderSystem());

        Timer.start();
    }

    boolean called1, called2, called3, called4, called5;
    @Override
    public void tick() {
        super.tick();
        if(Timer.counter >= 1 * Main.engine.TARGET_FPS && !called1){
            Entity three = spawnCountdown(Main.Skin.count[3 - 1]);
                cache.get().add(three);
                called1 = true;
        }
        if(Timer.counter >= 2 * Main.engine.TARGET_FPS && !called2){
            EntityManager.deleteEntity(cache.get().get(0).entityID);
            cache.get().remove(0);
            Entity two = spawnCountdown(Main.Skin.count[2 - 1]);
            cache.get().add(two);
            called2 = true;
        }
        if(Timer.counter >= 3 * Main.engine.TARGET_FPS && !called3){
            EntityManager.deleteEntity(cache.get().get(0).entityID);
            cache.get().remove(0);
            Entity one = spawnCountdown(Main.Skin.count[1 - 1]);
            cache.get().add(one);
            called3 = true;
        }
        if(Timer.counter >= 4 * Main.engine.TARGET_FPS && !called4){
            EntityManager.deleteEntity(cache.get().get(0).entityID);
            cache.get().remove(0);
            Entity go = spawnCountdown(Main.Skin.go);
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

        if(InputHandler.INSTANCE.isKeyPressed(GLFW_KEY_ESCAPE)){
            beat_ref.audioFile.pauseSample();
            
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