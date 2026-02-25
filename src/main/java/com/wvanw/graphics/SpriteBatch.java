package com.wvanw.graphics;

import org.joml.Vector4f;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.system.MemoryUtil.memAllocFloat;
import static org.lwjgl.system.MemoryUtil.memFree;

public class SpriteBatch {

    private static final int MAX_SPRITES = 1000;
    private static final int SPRITE_VERTICES = 4;
    private static final int SPRITE_INDICES = 6;
    private static final int VERTEX_FLOATS = 8;

    private final int vao;
    private final int vbo;
    private final int ebo;

    private final FloatBuffer vertexBuffer;
    private final Vector4f vertexColor;
    private int spriteCount = 0;

    public SpriteBatch() {
        vao = glGenVertexArrays();
        glBindVertexArray(vao);
        int stride = VERTEX_FLOATS * Float.BYTES;

        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        int bufferSize = MAX_SPRITES * SPRITE_VERTICES * VERTEX_FLOATS * Float.BYTES;
        glBufferData(GL_ARRAY_BUFFER, bufferSize, GL_DYNAMIC_DRAW);

        // Position Attribute
        glVertexAttribPointer(0, 2, GL_FLOAT, false, stride, 0);
        glEnableVertexAttribArray(0);

        // Texture Coordinates Attribute
        glVertexAttribPointer(1, 2, GL_FLOAT, false, stride, 2L * Float.BYTES);
        glEnableVertexAttribArray(1);

        // Color Attribute
        glVertexAttribPointer(2, 4, GL_FLOAT, false, stride, 4L * Float.BYTES);
        glEnableVertexAttribArray(2);

        int[] indices = new int[MAX_SPRITES * SPRITE_INDICES];
        int offset = 0;
        for (int i = 0; i < indices.length; i += SPRITE_INDICES) {
            indices[i] = offset;
            indices[i + 1] = offset + 1;
            indices[i + 2] = offset + 2;
            indices[i + 3] = offset + 2;
            indices[i + 4] = offset + 3;
            indices[i + 5] = offset;
            offset += SPRITE_VERTICES;
        }

        ebo = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ebo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices, GL_STATIC_DRAW);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);

        vertexBuffer = memAllocFloat(MAX_SPRITES * SPRITE_VERTICES * VERTEX_FLOATS);
        vertexColor = new Vector4f(1f, 1f, 1f, 1f);
    }

    private void flush() {
        glBindVertexArray(vao);
        glDrawElements(GL_TRIANGLES, spriteCount * SPRITE_INDICES, GL_UNSIGNED_INT, 0);
        glBindVertexArray(0);
        spriteCount = 0;
        vertexBuffer.clear();
    }

    private void putVertex(float x, float y, float u, float v) {
        vertexBuffer.put(x);
        vertexBuffer.put(y);
        vertexBuffer.put(u);
        vertexBuffer.put(v);
        vertexBuffer.put(vertexColor.x);
        vertexBuffer.put(vertexColor.y);
        vertexBuffer.put(vertexColor.z);
        vertexBuffer.put(vertexColor.w);
    }

    public void begin() {
        spriteCount = 0;
        vertexBuffer.clear();
    }

    public void draw(float x, float y, float width, float height) {
        if (spriteCount >= MAX_SPRITES)
            flush();
        putVertex(x, y, 0f, 0f);
        putVertex(x + width, y, 1f, 0f);
        putVertex(x + width, y + height, 1f, 1f);
        putVertex(x, y + height, 0f, 1f);
        spriteCount++;
    }

    public void end() {
        vertexBuffer.flip();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferSubData(GL_ARRAY_BUFFER, 0, vertexBuffer);
        flush();
    }

    public void destroy() {
        memFree(vertexBuffer);
        glDeleteBuffers(vbo);
        glDeleteBuffers(ebo);
        glDeleteVertexArrays(vao);
    }
}
