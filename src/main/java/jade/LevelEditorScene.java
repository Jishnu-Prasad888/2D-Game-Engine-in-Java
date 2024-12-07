package jade;

import java.awt.event.KeyEvent;

import static org.lwjgl.opengl.GL20.*;

public class LevelEditorScene extends Scene {

    private String VertexShaderSrc ="#version 460 core\n" +
            "layout (location = 0) in vec3 aPos;\n" +
            "layout (location = 1) in vec4 aColor; \n" +
            "\n" +
            "out vec4 fColor;\n" +
            "\n" +
            "void main() {\n" +
            "    fColor = aColor;\n" +
            "    gl_Position = vec4(aPos,1.0);\n" +
            "}";

    private String FragmentShaderSrc = "#version 460 core\n" +
            "\n" +
            "in vec4 fColor;\n" +
            "out vec4 fragColor;\n" +
            "\n" +
            "void main(){\n" +
            "    fragColor = fColor;\n" +
            "}\n";

    private int VertexID,FragementID,ShaderProgram;

//    private boolean ChangingScene = false;
//    private float TimeToChangeScene = 2.0f;

    public LevelEditorScene() {
//            System.out.println("LevelEditorScene constructor");
    }

    @Override
    public void init(){
        // ==========================================
        // Compiling and lining the shaders
        // ==========================================

        // Loading and compiling the vertex shader
        VertexID = glCreateShader(GL_VERTEX_SHADER);
        // Pass the shaders code into the GPU
        glShaderSource(VertexID, VertexShaderSrc);
        glCompileShader(VertexID);

        int success = glGetShaderi(VertexID, GL_COMPILE_STATUS);
        // Gives 1 if it succeeds and 0 if not
        if(success==GL_FALSE){
            int len = glGetShaderi(VertexID, GL_INFO_LOG_LENGTH);
            System.out.println("Error compiling vertex shader 'defaultShader.glsl' : "+glGetShaderInfoLog(VertexID, len));
            assert false : "";
        }

        FragementID = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(FragementID, FragmentShaderSrc);
        glCompileShader(FragementID);
        success = glGetShaderi(FragementID, GL_COMPILE_STATUS);
        // Gives 1 if it succeeds and 0 if not
        if(success==GL_FALSE){
            int len = glGetShaderi(FragementID, GL_INFO_LOG_LENGTH);
            System.out.println("Error compiling fragment shader 'defaultShader.glsl' : "+glGetShaderInfoLog(FragementID, len));
            assert false : "";
        }

        // Link shader adn check for errors
        ShaderProgram = glCreateProgram();
        glAttachShader(ShaderProgram,VertexID);
        glAttachShader(ShaderProgram,FragementID);
        glLinkProgram(ShaderProgram);

        // Check for linking errors
        success = glGetProgrami(ShaderProgram, GL_LINK_STATUS);
        if(success==GL_FALSE){
            int length = glGetProgrami(ShaderProgram, GL_INFO_LOG_LENGTH);
            System.out.println("Error linking shader 'defaultShader.glsl' : "+glGetProgramInfoLog(ShaderProgram, length));
            assert false : "";
        }
    }

    @Override
    public void update(float dt) {
//        System.out.println(1/dt); // to see the fps
//        if(!ChangingScene && KeyListener.isKeyPressed(KeyEvent.VK_SPACE)) {
//            ChangingScene = true;
//        }
//
//        if (ChangingScene && TimeToChangeScene > 0.0f) {
//            TimeToChangeScene = TimeToChangeScene - dt;
//            // taking away time that has passed to change the screen
//            Window.get().r -= dt * 5.0f;
//            Window.get().g -= dt * 5.0f;
//            Window.get().b -= dt * 5.0f;
//            Window.get().a -= dt * 5.0f;
//
//        } else if (ChangingScene) {
//            Window.ChangeScene(1);
//        }

    }
}
