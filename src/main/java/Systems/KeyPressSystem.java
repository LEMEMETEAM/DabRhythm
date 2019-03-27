package Systems;

import Components.CHitButton;
import DabRhythm.Main;
import Entities.Entity;
import Entities.EntityManager;
import Entities.Components.CSprite;
import Graphics.Graphics;
import Input.InputHandler;
import System.System;

public class KeyPressSystem extends System {

    @Override
    public void update() {
        for (Entity e : EntityManager.entitiesWithComponents(CHitButton.class)) {
            CHitButton hb = e.getComponent(CHitButton.class);
            CSprite s = e.getComponent(CSprite.class);

            if (InputHandler.INSTANCE.isKeyPressed(Main.Config.left_arrow_button) && hb.lane == 1) {
                hb.pressed = true;
                s.texture = hb.pressTexture;
                continue;
            } else if (InputHandler.INSTANCE.isKeyReleased(Main.Config.left_arrow_button) && hb.lane == 1) {
                hb.pressed = false;
                s.texture = hb.unpressTexture;
                continue;
            }

            if (InputHandler.INSTANCE.isKeyPressed(Main.Config.up_arrow_button) && hb.lane == 2) {
                hb.pressed = true;
                s.texture = hb.pressTexture;
                continue;
            } else if (InputHandler.INSTANCE.isKeyReleased(Main.Config.up_arrow_button) && hb.lane == 2) {
                hb.pressed = false;
                s.texture = hb.unpressTexture;
                continue;
            }

            if (InputHandler.INSTANCE.isKeyPressed(Main.Config.down_arrow_button) && hb.lane == 3) {
                hb.pressed = true;
                s.texture = hb.pressTexture;
                continue;
            } else if (InputHandler.INSTANCE.isKeyReleased(Main.Config.down_arrow_button) && hb.lane == 3) {
                hb.pressed = false;
                s.texture = hb.unpressTexture;
                continue;
            }

            if (InputHandler.INSTANCE.isKeyPressed(Main.Config.right_arrow_button) && hb.lane == 4) {
                hb.pressed = true;
                s.texture = hb.pressTexture;
                continue;
            } else if (InputHandler.INSTANCE.isKeyReleased(Main.Config.right_arrow_button) && hb.lane == 4) {
                hb.pressed = false;
                s.texture = hb.unpressTexture;
                continue;
            }
        }
    }

    @Override
    public void render(Graphics g) {

    }
}