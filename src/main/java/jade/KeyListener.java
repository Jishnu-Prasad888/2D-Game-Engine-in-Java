package jade;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class KeyListener {
    private static KeyListener instance;
    private boolean keyPressed[] = new boolean[350];
    // GLFW defines a range of 0 to 348 (or sometimes a little higher depending on the platform) for keyboard keycodes, which is why the array is sized to accommodate up to 350 keys, providing room for all standard keys, function keys, and other special keys that could be processed.

    private KeyListener() {
        // empty cause we dont need it to do anything
    }

    public static KeyListener get() {
        // the commented code wa in the tutorial but it causes a stackoverflow error or similar
//        if (KeyListener.get() == null) {
//            KeyListener.instance = new KeyListener();
//        }
//        return KeyListener.get();
        if (instance == null) {
            instance = new KeyListener();
        }
        return instance;
    }

    public static void keyCallBack(long window, int key, int scancode, int action, int mods) {
        if (action == GLFW_PRESS) {
            get().keyPressed[key] = true;
        } else if (action == GLFW_RELEASE) {
            get().keyPressed[key] = false;
        }
    }

    public static boolean isKeyPressed(int keycode) {
        if (keycode < get().keyPressed.length) {
            return get().keyPressed[keycode];
        } else {
            System.out.println("Keyboard input out of range");
            return false;
        }
    }
}