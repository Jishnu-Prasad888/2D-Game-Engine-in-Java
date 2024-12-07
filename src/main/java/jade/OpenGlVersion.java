package jade;


import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

public class OpenGlVersion {
    public static void main(String[] args) {
        // Initialize GLFW
        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // Create a windowed mode window and its OpenGL context
        long window = GLFW.glfwCreateWindow(800, 600, "OpenGL Version", 0, 0);
        if (window == 0) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        // Make the OpenGL context current
        GLFW.glfwMakeContextCurrent(window);
        GLFW.glfwSwapInterval(1); // Enable v-sync
        GLFW.glfwShowWindow(window);

        // Initialize OpenGL
        GL.createCapabilities();

        // Retrieve OpenGL version and GLSL version
        String glVersion = GL11.glGetString(GL11.GL_VERSION);
//        String glslVersion = GL11.glGetString(GL11.GL_SHADING_LANGUAGE_VERSION);
        String glslVersion = GL20.glGetString(GL20.GL_SHADING_LANGUAGE_VERSION);

        System.out.println("OpenGL Version: " + glVersion);
        System.out.println("GLSL Version: " + glslVersion);

        // Clean up and terminate
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }
}
