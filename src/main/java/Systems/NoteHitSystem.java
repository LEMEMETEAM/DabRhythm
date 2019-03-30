package Systems;

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
                        if((temp = e2_c.bounds.intersects(e_c.bounds)).left){
                            GameScene.combo++;
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