package Systems;

import Graphics.Batch.IBatch;
import Graphics.Batch.SpriteBatch;
import Graphics.Batch.TextBatch;
import Graphics.Models.Texture;
import System.System;

import org.joml.Vector3f;
import org.joml.Vector4f;

import Entities.Components.CSprite;
import DabEngineResources.DabEngineResources;
import Entities.*;
import Graphics.Graphics;
import Entities.Components.CTransform;
import GUI.Components.CText;

public class SpriteRenderSystem extends System {

    @Override
    public void update() {
        
    }

    @Override
    public void render(Graphics g) {
        g.getBatch(SpriteBatch.class).begin();
        for(Entity e : EntityManager.entitiesWithComponents(CTransform.class, CSprite.class)){
            CSprite sprite = e.getComponent(CSprite.class);
            CTransform transform = e.getComponent(CTransform.class);
            g.getBatch(SpriteBatch.class).draw(sprite.texture, new Vector4f(transform.pos.x, transform.pos.y, transform.size.x, transform.size.y), sprite.color, sprite.center_anchor);
        }
        g.getBatch(SpriteBatch.class).end();
    }

}