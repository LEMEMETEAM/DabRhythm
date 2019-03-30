package Factories;

import org.joml.Vector2f;
import org.joml.Vector4f;

import Components.CBeatLine;
import Components.CNote;
import DabRhythm.LanePosCalculator;
import DabRhythm.Main;
import Entities.Entity;
import Entities.EntityManager;
import Entities.Components.CCollision;
import Entities.Components.CPolygon;
import Entities.Components.CSprite;
import Entities.Components.CTransform;
import Graphics.Batch.Polygon;
import Graphics.Models.AABB;
import Graphics.Models.Texture;

public class NoteFactory {

    public static void spawnNote(int lane, float beatTime){
        Entity e = EntityManager.createEntity(
            new CSprite(){
                {
                    texture = Main.Skin.note[lane - 1];
                    color = new Vector4f(1);
                    center_anchor = true;
                }
            },
            new CTransform(){
                {
                    size = new Vector2f(50);
                    pos = new Vector2f(LanePosCalculator.calc(lane, size.x), 0);
                }
            },
            new CNote(){
                {
                    noteTime = beatTime;
                    lane_num = lane;
                }
            }
        );
        e.addComponent(new CCollision(){
            {
                bounds = new AABB();
                bounds.correctBounds(e);
            }
        });
    }

    public static void spawnBeatLine(float beatLineTime){
        EntityManager.createEntity(
            new CTransform(){
                {
                    size = new Vector2f(50*4, 5);
                    pos = new Vector2f(Main.engine.getMainWindow().getWidth()/2, 0);
                }
            },
            new CPolygon(){
                {
                    polygon = new Polygon(
                        new int[]{
                            0,1,2,
                            0,3,2
                        },
                        new Vector2f[]{
                            new Vector2f(-1, -1),
                            new Vector2f(-1, 1),
                            new Vector2f(1, 1),
                            new Vector2f(1, -1)
                        }
                    );
                    color = new Vector4f(1);
                }
            },
            new CBeatLine(){
                {
                    beatTime = beatLineTime;
                }
            }
        );
    }
}