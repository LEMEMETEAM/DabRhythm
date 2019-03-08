package Systems;

import System.System;

import java.util.logging.Level;

import Components.CFade;
import DabRhythm.Main;
import Entities.*;
import Entities.Components.CRender;

public class FadeSystem extends System {

    private int counter;
    private boolean in = true, out, waiting;

    @Override
    public void update() {
        for(Entity e : EntityManager.entitiesWithComponents(CFade.class, CRender.class)){
            CFade f = e.getComponent(CFade.class);
            CRender r = e.getComponent(CRender.class);

            if(f.in_or_out == 0 && f.in_and_out == 0){
                r.color.w += 1.0 / (f.time * Main.engine.TARGET_FPS);
                if(r.color.w == 1.0){
                    e.removeComponent(CFade.class);
                }
            }
            else if(f.in_or_out == 1){
                r.color.w -= 1.0 / (f.time * Main.engine.TARGET_FPS);
                if(r.color.w == 0.0){
                    e.removeComponent(CFade.class);
                }
            }
            else if(f.in_and_out == 1){
                if(in){
                    f.in = true;
                    r.color.w += 1.0 / (f.time * Main.engine.TARGET_FPS);
                    if(r.color.w >= 1.0){
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
                    r.color.w -= 1.0 / (f.time * Main.engine.TARGET_FPS);
                    if(r.color.w <= 0){
                        e.removeComponent(CFade.class);
                        out = false;
                        f.out = false;
                    }
                }
            }
        }
    }

    @Override
    public void render() {

    }
    
}