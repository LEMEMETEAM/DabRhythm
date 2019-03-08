package Systems;

import org.joml.Vector3f;
import org.joml.Vector4f;

import Components.CBatchable;
import DabEngineResources.DabEngineResources;
import Entities.Components.CRender;
import Entities.Components.CTransform;
import GUI.Components.CText;
import Graphics.Batch.TextBatch;
import Graphics.Models.Texture;
import System.System;
import Entities.*;

public class TextRenderSystem extends System {

    private final TextBatch tb = new TextBatch(new Texture(DabEngineResources.class, "Fonts/Consolas_font.png", 16, 16));
    
    @Override
    public void update() {
        
    }

    @Override
    public void render() {
        tb.begin();
        for(Entity e : EntityManager.entitiesWithComponents(CBatchable.class, CText.class)){
            CText text = e.getComponent(CText.class);
            CRender render = e.getComponent(CRender.class);
            CTransform transform = e.getComponent(CTransform.class);
            tb.draw(text.text, transform.pos.x, transform.pos.y, transform.size.y, new Vector4f(render.color.x, render.color.y, render.color.z, render.color.w));
        }
        tb.end();
    }

}