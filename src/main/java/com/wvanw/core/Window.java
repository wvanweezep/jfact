package com.wvanw.core;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

    private final long handle;
    public final Input input;

    public Window(String title, int x, int y) {
        this.handle = glfwCreateWindow(x, y, title, NULL, NULL);
        if (handle == NULL) throw new RuntimeException(
                "Failed to create a new Window.");
        this.input = new Input(handle);
        setActive();
    }

    public boolean isActive() {
        return handle == glfwGetCurrentContext();
    }

    public void setActive() {
        glfwMakeContextCurrent(handle);
        glfwSwapInterval(1);
    }

    public boolean shouldClose() {
        return glfwWindowShouldClose(handle);
    }

    public void update() {
        glfwPollEvents();
        // TODO: Update logic
        System.out.println(input.getMouseScrollDelta());
        input.resetDelta();
    }

    public void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        // TODO: Render logic
        glfwSwapBuffers(handle);
    }

    public void destroy() {
        input.destroy();
        glfwDestroyWindow(handle);
    }
}
