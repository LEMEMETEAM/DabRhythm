package Systems;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.logging.Level;

import org.joml.Vector2f;

import Components.CHitButton;
import Components.CNote;
import Entities.Entity;
import Entities.EntityManager;
import Entities.Components.CCollision;
import Graphics.Graphics;
import Scenes.GameScene;
import System.System;
import Utils.Pair;

public class NoteHitSystem extends System {

    private ArrayList<Entity> skip = new ArrayList<>();
    private int notesHit = 0;

    @Override
    public void update() {
        for(Entity e : EntityManager.entitiesWithComponents(CHitButton.class)){
            CHitButton hb = e.getComponent(CHitButton.class);
            if(hb.pressed){
                for(Entity e2 : EntityManager.entitiesWithComponents(CNote.class)){
                    CNote n = e2.getComponent(CNote.class);
                    if(hb.lane == n.lane_num){
                        CCollision e_c = e.getComponent(CCollision.class);
                        CCollision e2_c = e2.getComponent(CCollision.class);
                        Pair<Boolean, Vector2f> temp;
                        if((temp = e2_c.bounds.intersects(e_c.bounds)).left && !skip.contains(e2)){
                            GameScene.combo++;
                            notesHit++;
                            GameScene.accuracy = ((float)notesHit / this.scene.getSystem(SongManagerSystem.class).beat.hits.size()) * 100f;
                            if((temp.right.y > -17 && temp.right.y <= 0) || (temp.right.y < -100+17 && temp.right.y >= -100)){
                                GameScene.score += 2 * GameScene.combo;
                            }
                            else if((temp.right.y > -34 && temp.right.y <= -17) || (temp.right.y < -100+34 && temp.right.y >= -100+17)){
                                GameScene.score += 5 * GameScene.combo;
                            }
                            else if((temp.right.y > -50 && temp.right.y <= -34) || (temp.right.y < -100+50 && temp.right.y >= -100+34)){
                                GameScene.score += 10 * GameScene.combo;
                            }
                            EntityManager.deleteEntity(e2.entityID);
                        }
                        else{
                            GameScene.combo = 0;
                            skip.add(e2);
                        }
                    }
                    if(!EntityManager.entities.containsValue(e2)){
                        if(skip.contains(e2)){
                            skip.remove(e2);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {

    }
    
}