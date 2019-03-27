package Systems;

import Components.CBeatLine;
import Components.CNote;
import Entities.Entity;
import Entities.EntityManager;
import Entities.Components.CTransform;
import Graphics.Graphics;
import Scenes.GameScene;
import System.System;

public class NoteScrollSystem extends System {

    private  float progressNote;
    private float progressBeatLine;

    @Override
    public void update() {
        SongManagerSystem sm = this.scene.getSystem(SongManagerSystem.class);
        for(Entity e : EntityManager.entitiesWithComponents(CNote.class)){
            CTransform t = e.getComponent(CTransform.class);
            CNote n = e.getComponent(CNote.class);
            progressNote = 1f - ((n.noteTime - sm.songPosInBeats) / (sm.noteFallTimeInSeconds / sm.secPerBeat));
            t.pos.y = 0 + ((GameScene.strumLine - 0) * progressNote);
        }
        for(Entity e : EntityManager.entitiesWithComponents(CBeatLine.class)){
            CTransform t = e.getComponent(CTransform.class);
            CBeatLine bl = e.getComponent(CBeatLine.class);
            progressBeatLine = 1f - ((bl.beatTime - sm.songPosInBeats) / (sm.noteFallTimeInSeconds / sm.secPerBeat));
            t.pos.y = 0 + ((GameScene.strumLine - 0) * progressBeatLine);
        }
    }

    @Override
    public void render(Graphics g) {

    }

}