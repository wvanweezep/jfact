# Card Factory Rougelike

### TODO
 - **General**
   - Grid Rendering
 - **Input Module**
   - Input Action Aliasing
   - Input Event Subscription
   - Input Event Dispatching


<section id="core">

<section id="core-window">
<h2>Window</h2>
<h3>Description</h3>
...
<h3>Further Notes</h3>
...

<a href="src/main/java/com/wvanw/core/Window.java">Jump to source</a>
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

<a href="src/main/java/com/wvanw/core/Input.java">Jump to source</a>
</section>

</section>


