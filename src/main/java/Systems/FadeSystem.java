package Systems;

import System.System;

import java.util.logging.Level;

import Components.CFade;
import Entities.Components.CSprite;
import DabRhythm.Main;
import Entities.*;
import Graphics.Graphics;

public class FadeSystem extends System {

    private int counter;
    private boolean in = true, out, waiting;

    @Override
    public void update() {
        for(Entity e : EntityManager.entitiesWithComponents(CFade.class, CSprite.class)){
            CFade f = e.getComponent(CFade.class);
            CSprite s = e.getComponent(CSprite.class);

            if(f.in_or_out == 0 && f.in_and_out == 0){
                s.color.w += 1.0 / (f.time * Main.engine.TARGET_FPS);
                if(s.color.w == 1.0){
                    e.removeComponent(CFade.class);
                }
            }
            else if(f.in_or_out == 1){
                s.color.w -= 1.0 / (f.time * Main.engine.TARGET_FPS);
                if(s.color.w == 0.0){
                    e.removeComponent(CFade.class);
                }
            }
            else if(f.in_and_out == 1){
                if(in){
                    f.in = true;
                    s.color.w += 1.0 / (f.time * Main.engine.TARGET_FPS);
                    if(s.color.w >= 1.0){
                        in = false;
                        f.in = false;
                        waiting = true;
                        f.waiting = true;
                    }
                }
                if(waiting){
                    counter++;
                    if(counter >= f.timeBetweenInOut * Main.engine.TARGET_FPS){
                        waiting = false;
                        f.waiting = false;
                        out = true;
                        f.out = true;
                    }
                }
                if(out){
                    s.color.w -= 1.0 / (f.time * Main.engine.TARGET_FPS);
                    if(s.color.w <= 0){
                        e.removeComponent(CFade.class);
                        out = false;
                        f.out = false;
                    }
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {

    }
    
}