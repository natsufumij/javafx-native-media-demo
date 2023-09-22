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

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.util.Objects;
import java.util.ResourceBundle;

public class HelloController {
    public static HelloController controller = null;


    @FXML
    private Button button;

    @FXML
    private Label label;

    @FXML
    private ResourceBundle resources;

    public void initialize() {
        HelloController.controller = this;
        button.setOnAction(e -> {
            label.setText(resources.getString("label.text") + " " + System.getProperty("javafx.version"));
            label.setVisible(! label.isVisible());
            play();
        });
    }


    private static MediaView mediaView;

    private static boolean musicStop = true;
    private static String[] urls = {"m1.mp3","w.mp3","pao.mp3","elephant.mp3"};
    private static int index = 0;

    public static void setMusic(AnchorPane group) {
        mediaView=new MediaView();
        Group group1=new Group();
        group1.getChildren().add(mediaView);
        group.getChildren().add(group1);
    }

    public static void play(){
        //以下代码中的链接是网易云音乐的歌曲，每一首歌只有id不一样，在网易云音乐中查看歌曲链接得到id，套用下面链接（有时可能无效）.
//本地音乐(相对路径)套用这个方法（有时可能无效）
        String url1= Objects.requireNonNull(HelloFXML.class.getResource(urls[index])).toExternalForm();
        System.out.printf("media is %s", url1);
        Media media = new Media(Objects.requireNonNull(url1));
        //使用MediaPlayer 来播放音乐
        if(mediaView.getMediaPlayer()==null){
           MediaPlayer player= initPlayer(media);
           player.play();
        }
        else{
            ++index;
            mediaView.getMediaPlayer().stop();
            url1= Objects.requireNonNull(HelloFXML.class.getResource(urls[index])).toExternalForm();
            System.out.printf("media is %s", url1);
            media = new Media(Objects.requireNonNull(url1));
            MediaPlayer player= initPlayer(media);
            player.play();
        }
    }

    private static MediaPlayer initPlayer(Media media){
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
//播放 mediaPlayer.play();
//循环播放
//        mediaPlayer.setAutoPlay(true);
//暂停
//        mediaPlayer.pause();
//停止
//        mediaPlayer.stop();
//设置音量
        mediaPlayer.setVolume(0.4);
//设置成静音
        mediaPlayer.setMute(false);
//设置播放速度（0-8）
        mediaPlayer.setRate(1);
//设置平衡，1和-1时只有一边耳机（音响）有声音
        mediaPlayer.setBalance(1);
//设置开始时间
//        mediaPlayer.setStartTime(Duration.seconds(40));
//设置结束时间
//        mediaPlayer.setStopTime(Duration.seconds(41));
//监控
        mediaPlayer.setOnPlaying(new Runnable(){
            @Override
            public void run() {
                // 音乐正在播放时执行这里的操作
                System.out.println("Play Music..");
                controller.label.setText(" setOnPlaying...");
            }
        });
        mediaPlayer.setOnReady(new Runnable(){
            @Override
            public void run() {
                // 音乐播放前执行这里的操作
                System.out.println("Player Before");
                controller.label.setText("setOnReady...");
            }
        });
        mediaPlayer.setOnEndOfMedia(()->{
            musicStop=true;
            controller.label.setText("setOnEndOfMedia...");
            index=(index+1)%(urls.length);
            String url1= Objects.requireNonNull(HelloFXML.class.getResource(urls[index])).toExternalForm();
            System.out.printf("media is %s", url1);
            Media media2 = new Media(Objects.requireNonNull(url1));
            MediaPlayer player=initPlayer(media2);
            player.play();
        });
        musicStop=false;
        return mediaPlayer;
    }
}
