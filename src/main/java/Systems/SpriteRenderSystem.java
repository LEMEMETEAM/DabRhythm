package Systems;

import Graphics.Batch.IBatch;
import Graphics.Batch.SpriteBatch;
import Graphics.Batch.TextBatch;
import Graphics.Models.Texture;
import System.System;

import org.joml.Vector3f;
import org.joml.Vector4f;

import Components.CBatchable;
import Components.CSprite;
import DabEngineResources.DabEngineResources;
import Entities.*;
import Entities.Components.CRender;
import Entities.Components.CTransform;
import GUI.Components.CText;

public class SpriteRenderSystem extends System {

    private final SpriteBatch sb = new SpriteBatch();

    @Override
    public void update() {
        
    }

    @Override
    public void render() {
        sb.begin();
        for(Entity e : EntityManager.entitiesWithComponents(CBatchable.class, CSprite.class)){
            CRender render = e.getComponent(CRender.class);
            CTransform transform = e.getComponent(CTransform.class);
            sb.draw(render.texture, new Vector4f(transform.pos.x, transform.pos.y, transform.size.x, transform.size.y), render.color, render.center_anchor);
        }
        sb.end();
    }

}