package Systems;

import Components.CHUD;
import Components.CHUD.HUD;
import Entities.Entity;
import Entities.EntityManager;
import GUI.Components.CText;
import Graphics.Graphics;
import Scenes.GameScene;
import System.System;

public class HUDRenderSystem extends System {

    @Override
    public void update() {
        for(Entity e : EntityManager.entitiesWithComponents(CHUD.class)){
            CText t = e.getComponent(CText.class);
            CHUD h = e.getComponent(CHUD.class);

            if(h.hud == HUD.SCORE){
                t.text = String.valueOf(GameScene.score);
            }
            else if(h.hud == HUD.COMBO){
                t.text = String.valueOf(GameScene.combo);
            }
            if(h.hud == HUD.ACC){
                t.text = String.valueOf(GameScene.accuracy);
            }
        }
    }

    @Override
    public void render(Graphics g) {

    }

}