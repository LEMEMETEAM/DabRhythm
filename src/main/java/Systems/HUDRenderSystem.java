package Systems;

import Components.CHUD;
import Components.CHUD.HUD;
import DabRhythm.Main;
import Entities.Entity;
import Entities.EntityManager;
import Entities.Components.CSprite;
import GUI.Components.CText;
import Graphics.Graphics;
import Scenes.GameScene;
import System.System;

public class HUDRenderSystem extends System {

    @Override
    public void update() {
        for(Entity e : EntityManager.entitiesWithComponents(CHUD.class)){
            CSprite s = e.getComponent(CSprite.class);
            CHUD h = e.getComponent(CHUD.class);

            if(h.hud == HUD.SCORE){
                String st = String.valueOf(GameScene.score);
                for(int i = st.length() - 1, I = GameScene.scoreLength; i >= 0 && I > 0; i--, I--){
                    if(I == h.unit){
                        char c = st.charAt(i);
                        s.texture = Main.Skin.score[Integer.parseInt(String.valueOf(c))];
                    }
                }
            }
            else if(h.hud == HUD.COMBO){
                String st = String.valueOf(GameScene.combo);
                for(int i = st.length() - 1, I = GameScene.comboLength; i >= 0 && I > 0; i--, I--){
                    if(I == h.unit){
                        char c = st.charAt(i);
                        s.texture = Main.Skin.combo[Integer.parseInt(String.valueOf(c))];
                    }
                }
            }
            if(h.hud == HUD.ACC){
                //s.text = String.valueOf(GameScene.accuracy);
            }
        }
    }

    @Override
    public void render(Graphics g) {

    }

}