package Factories;

import org.joml.Vector2f;
import org.joml.Vector4f;

import Components.CBeatLine;
import Components.CNote;
import DabRhythm.LanePosCalculator;
import DabRhythm.Main;
import Entities.EntityManager;
import Entities.Components.CPolygon;
import Entities.Components.CSprite;
import Entities.Components.CTransform;
import Graphics.Batch.Polygon;
import Graphics.Models.Texture;

public class NoteFactory {

    public static void spawnNote(int lane, float beatTime){
        EntityManager.createEntity(
            new CSprite(){
                {
                    texture = new Texture("Skins/mania-note" + lane + ".png");
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
                }
            }
        );
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