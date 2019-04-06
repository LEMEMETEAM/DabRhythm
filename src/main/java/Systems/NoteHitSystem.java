package Systems;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;

import org.joml.Vector2f;
import org.joml.Vector4f;

import Components.CHitButton;
import Components.CNote;
import DabRhythm.Main;
import DabRhythm.Main.Judgement.Judge;
import Entities.Entity;
import Entities.EntityManager;
import Entities.Components.CSprite;
import Entities.Components.CTransform;
import Graphics.Graphics;
import Scenes.GameScene;
import System.System;
import Utils.Timer;

public class NoteHitSystem extends System {

    private ArrayList<Entity> skip = new ArrayList<>();
    private int notesHit = 0;
    private CompletableFuture<Void> signAsync;

    @Override
    public void update() {
        for(Entity hitButton : EntityManager.entitiesWithComponents(CHitButton.class)){
            CHitButton hb = hitButton.getComponent(CHitButton.class);
            for(Entity note : EntityManager.entitiesWithComponents(CNote.class)){
                CNote n = note.getComponent(CNote.class);
                if(hb.lane != n.lane_num){
                    continue;
                }
                if(hb.pressed && !n.skip){
                    float delta = (this.scene.getSystem(SongManagerSystem.class).songPosInBeats * this.scene.getSystem(SongManagerSystem.class).secPerBeat) - (n.noteTime * this.scene.getSystem(SongManagerSystem.class).secPerBeat);
                    Judge j = Main.Judgement.getJudge(delta);
                    switch(j){
                        case HIT_300:
                        case HIT_200:
                        case HIT_100:
                        case HIT_50:
                            if(signAsync != null && !signAsync.isDone()){
                                signAsync.completeAsync(() -> {
                                    Timer.stop();
                                    return null;
                                });
                            }
                            signAsync = CompletableFuture.supplyAsync(() -> {
                                Timer.start();
                                Entity e = EntityManager.createEntity(
                                    new CSprite(){
                                        {
                                            texture = j.getSign();
                                            color = new Vector4f(1);
                                            center_anchor = true;
                                        }
                                    },
                                    new CTransform(){
                                        {
                                            pos = new Vector2f(Main.engine.getMainWindow().getWidth()/2, Main.engine.getMainWindow().getHeight()*0.65f);
                                            size = new Vector2f(Main.Skin.hit300.width, Main.Skin.hit300.height);
                                        }
                                    }
                                ); 
                                while(Timer.counter <= 0.25f * Main.engine.TARGET_FPS);
                                Timer.stop();
                                return e;
                            }).thenAccept(e -> EntityManager.deleteEntity(e.entityID));
                            GameScene.combo++;
                            notesHit++;
                            GameScene.score += j.getScore() * GameScene.combo;
                            EntityManager.deleteEntity(note.entityID);
                            break;
                        case HIT_MISS:
                        case HIT_NULL:
                            GameScene.combo = 0;
                            n.skip = true;
                            break;

                    }
                    GameScene.accuracy = ((float)notesHit / this.scene.getSystem(SongManagerSystem.class).notesSoFar) * 100f;
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {

    }
    
}