package com.wvanw.graphics;

import org.joml.Matrix4f;
import org.lwjgl.system.MemoryStack;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.lwjgl.opengl.GL20.*;

public class Shader {

    private final int handle;

    public Shader(String vertPath, String fragPath) {
        int vertShader = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertShader, loadSource(vertPath));
        glCompileShader(vertShader);
        if (glGetShaderi(vertShader, GL_COMPILE_STATUS) == GL_FALSE)
            throw new RuntimeException(glGetShaderInfoLog(vertShader));

        int fragShader = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragShader, loadSource(fragPath));
        glCompileShader(fragShader);
        if (glGetShaderi(fragShader, GL_COMPILE_STATUS) == GL_FALSE)
            throw new RuntimeException(glGetShaderInfoLog(fragShader));

        handle = glCreateProgram();
        glAttachShader(handle, vertShader);
        glAttachShader(handle, fragShader);
        glLinkProgram(handle);
        if (glGetProgrami(handle, GL_LINK_STATUS) == GL_FALSE)
            throw new RuntimeException(glGetProgramInfoLog(handle));

        glDeleteShader(vertShader);
        glDeleteShader(fragShader);
    }

    private static String loadSource(String path) {
        try { return Files.readString(Path.of(path));
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setUniformMat4(String name, Matrix4f matrix) {
        int loc = glGetUniformLocation(handle, name);
        try (MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer buffer = stack.mallocFloat(16);
            matrix.get(buffer);
            glUniformMatrix4fv(loc, false, buffer);
        }
    }

    public void bind() {
        glUseProgram(handle);
    }

    public void unbind() {
        glUseProgram(0);
    }

    public void destroy() {
        glDeleteProgram(handle);
    }
}
