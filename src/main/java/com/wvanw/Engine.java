package com.wvanw;

import com.wvanw.core.Window;
import com.wvanw.graphics.Shader;
import com.wvanw.graphics.SpriteBatch;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;


public class Engine {

    private Window window;

    public Engine() {
        GLFWErrorCallback.createPrint(System.err).set();
        if (!glfwInit()) throw new IllegalStateException("Unable to initialize GLFW");

        this.window = new Window("Window", 1600, 900);

        glClearColor(0.1f, 0.1f, 0.1f, 0.0f);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glDisable(GL_DEPTH_TEST);

        while (!window.shouldClose()) {
            window.setActive();
            window.update();
            window.render();
        }

        window.destroy();

    }

    public static void main(String[] args) {
        new Engine();
    }
}
