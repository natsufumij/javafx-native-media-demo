/**
 * Copyright (c) 2019 Gluon
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *     * Neither the name of Gluon, any associated website, nor the
 * names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL GLUON BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package hellofx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static hellofx.HelloController.setMusic;

public class HelloFXML extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        AnchorPane root = FXMLLoader.load(HelloFXML.class.getResource("hello.fxml"),
                ResourceBundle.getBundle("hellofx.hello"));
        Scene scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        setMusic(root);
        stage.show();
        String property = System.getProperty("java.library.path");
        System.out.println(property);
    }

    public static void main(String[] args) {
        System.out.println("jfx..");
        URL resource = HelloFXML.class.getResource("w.mp3");
        System.out.println("mp3?"+resource);
        System.setProperty("jfxmedia.platforms","GSTPlatform");
        System.out.println(System.getProperty("jfxmedia.platforms",""));
        System.loadLibrary("glib-lite");
        System.loadLibrary("gstreamer-lite");
        System.loadLibrary("fxplugins");
        System.loadLibrary("jfxmedia");
        launch(args);
    }


}