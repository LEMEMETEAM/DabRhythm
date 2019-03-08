/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package DabRhythm;

import Core.App;
import Core.Engine;
import Graphics.ProjectionMatrix;
import Graphics.Batch.SpriteBatch;
import Menus.MainMenu;
import Scenes.IntroScene;
import Scenes.MenuScene;
import Scenes.Scene;
import Scenes.SceneManager;
import Scenes.SongBrowserScene;
import System.RenderSystem;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.glfw.GLFW.*;

import java.util.HashMap;

public class Main extends App {

    public static final Engine engine = new Engine();
    public static SpriteBatch mainbatch;

    {
        WIDTH = 800;
        HEIGHT = 600;
        TITLE = "DabRhythm";
        hints = new HashMap<>() {
            {
                put(GLFW_RESIZABLE, 0);
            }
        };
        fullscreen = false;
    }

    public void init() {
        ProjectionMatrix.createProjectionMatrix2D(0, engine.getMainWindow().getWidth(),
                engine.getMainWindow().getHeight(), 0);
        mainbatch = new SpriteBatch();

        SceneManager.addScene(new IntroScene());
        SceneManager.addScene(new MenuScene());
        SceneManager.addScene(new SongBrowserScene());

        SceneManager.setCurrentScene(SceneManager.getScene(IntroScene.class));
    }

    public void render() {
        glClear(GL_COLOR_BUFFER_BIT);
        SceneManager.getCurrentScene().render();
    }

    public void update() {
        SceneManager.getCurrentScene().tick();
    }

    public static void main(String[] args) {
        engine.init(new Main());
        engine.run();
    }
}