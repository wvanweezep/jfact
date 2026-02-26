<h1>Documentation</h1>


<section id="graphics">

<section id="graphics-spritebatch">
<h2>Sprite Batch</h2>
<h3>Description</h3>
Class handling the optimized rendering of a group of sprites onto the active Context. This class is abstracted 
by the <a href=#graphics-renderer2d>Renderer2D</a> and should not be directly interfaced with. A quick overview:
<ul>
   <li>Each Renderer2D contains exactly one SpriteBatch for rendering.</li>
   <li>To start rendering Sprites, the <code>begin()</code> method must be called.</li>
   <li>To render a Sprite you can use the <code>draw()</code> method passing the vertex position, 
      uv coordinates and colors.</li>
   <li>When all draw calls have been executed, call the <code>end()</code> method to finish rendering.</li>
</ul>

<h3>Methods</h3>
...

<h3>Further Notes</h3>
<ul>
   <li>Use the <code>destroy()</code> method to free buffers allocated by the SpriteBatch.</li>
</ul>

<a href="https://github.com/wvanweezep/jfact/tree/main/src/main/java/com/wvanw/graphics/SpriteBatch.java">Jump to source</a>
</section>


<section id="graphics-renderer2d">
<h2>Renderer2D</h2>
<h3>Description</h3>
...
<h3>Further Notes</h3>
...

<a href="https://github.com/wvanweezep/jfact/tree/main/src/main/java/com/wvanw/graphics/Renderer2D.java">Jump to source</a>
</section>

</section>



<section id="core">

<section id="core-window">
<h2>Window</h2>
<h3>Description</h3>
...
<h3>Further Notes</h3>
...

<a href="https://github.com/wvanweezep/jfact/tree/main/src/main/java/com/wvanw/core/Window.java">Jump to source</a>
</section>

<section id="core-input">
<h2>Input</h2>
<h3>Description</h3>
Class linked to a <a href=#core-window>Window</a> instance to handle input and provide intuitive
methods to deal with user input. Provides support for:
<ul>
    <li>Keyboard input with <code>getKeyDown()</code> and <code>getKeyUp()</code></li>
    <li>Mouse button input with <code>getMouseButtonDown()</code> and <code>getMouseButtonUp()</code></li>
    <li>Mouse position input with <code>getMousePosition()</code> and <code>getMouseDelta()</code></li>
    <li>Mouse scroll delta with <code>getMouseScrollDelta()</code></li>
</ul>

<h3>Further Notes</h3>
<ul>
    <li>Initialization is handled in the constructor, so no further setup is required.</li>
    <li>Use the <code>destroy()</code> method to free the callbacks set by this Class.</li>
    <li>Call the <code>resetDelta()</code> method at the end of an update cycle to prevent stale data.</li>
</ul>

<a href="https://github.com/wvanweezep/jfact/tree/main/src/main/java/com/wvanw/core/Input.java">Jump to source</a>
</section>

</section>


