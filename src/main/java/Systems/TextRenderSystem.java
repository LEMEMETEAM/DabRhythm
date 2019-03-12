package Systems;

import org.joml.Vector3f;
import org.joml.Vector4f;

import DabEngineResources.DabEngineResources;
import Graphics.Graphics;
import Entities.Components.CTransform;
import GUI.Components.CText;
import Graphics.Batch.TextBatch;
import Graphics.Models.Texture;
import System.System;
import Entities.*;

public class TextRenderSystem extends System {
    
    @Override
    public void update() {
        
    }

    @Override
    public void render(Graphics g) {
        g.getBatch(TextBatch.class).begin();
        for(Entity e : EntityManager.entitiesWithComponents(CTransform.class, CText.class)){
            CText text = e.getComponent(CText.class);
            CTransform transform = e.getComponent(CTransform.class);
            g.getBatch(TextBatch.class).draw(text.text, transform.pos.x, transform.pos.y, transform.size.y, new Vector4f(text.color.x, text.color.y, text.color.z, text.color.w));
        }
        g.getBatch(TextBatch.class).end();
    }

}