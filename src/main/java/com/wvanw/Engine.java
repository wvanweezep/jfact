package com.wvanw;

import com.wvanw.core.Window;
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

        GL.createCapabilities();
        glClearColor(0.1f, 0.1f, 0.1f, 0.0f);

        while (!window.shouldClose()) {
            window.update();
            window.render();
        }

        window.destroy();

    }

    public static void main(String[] args) {
        new Engine();
    }
}
