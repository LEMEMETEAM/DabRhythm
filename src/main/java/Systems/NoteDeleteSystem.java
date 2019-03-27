package Systems;

import Components.CBeatLine;
import Components.CNote;
import DabRhythm.Main;
import Entities.Entity;
import Entities.EntityManager;
import Entities.Components.CTransform;
import Graphics.Graphics;
import System.System;

public class NoteDeleteSystem extends System {

    @Override
    public void update() {
        for(Entity e : EntityManager.entitiesWithComponents(CNote.class)){
            CTransform t = e.getComponent(CTransform.class);
            if(t.pos.y > Main.engine.getMainWindow().getHeight() + t.size.y){
                EntityManager.deleteEntity(e.entityID);
            }
        }
        for(Entity e : EntityManager.entitiesWithComponents(CBeatLine.class)){
            CTransform t = e.getComponent(CTransform.class);
            if(t.pos.y > Main.engine.getMainWindow().getHeight() + t.size.y){
                EntityManager.deleteEntity(e.entityID);
            }
        }
    }

    @Override
    public void render(Graphics g) {

    }

}