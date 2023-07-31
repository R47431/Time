package com.example.time.temporizador;

import java.io.File;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Time {

     static Scanner scanner = new Scanner(System.in);

    private static MediaPlayer mediaPlayer;


    public static void digitar() {

        System.out.print("Digite o número de horas (ou 'stop' para parar): ");
        String inputHoras = scanner.nextLine();
        if (inputHoras.equalsIgnoreCase("stop")) {
            stopAlertSound();
            digitar();
        }
        int horas = Integer.parseInt(inputHoras);

        System.out.print("Digite o número de minutos (ou 'stop' para parar): ");
        String inputMinutos = scanner.nextLine();
        if (inputMinutos.equalsIgnoreCase("stop")) {
            stopAlertSound();
            digitar();
        }
        int minutos = Integer.parseInt(inputMinutos);

        System.out.print("Digite o número de segundos (ou 'stop' para parar): ");
        String inputSegundos = scanner.nextLine();
        if (inputSegundos.equalsIgnoreCase("stop")) {
            stopAlertSound();
            digitar();
        }
        int segundos = Integer.parseInt(inputSegundos);

        if (horas < 0 || horas >= 24 || minutos < 0 || minutos >= 60 || segundos < 0 || segundos >= 60) {
            System.out.println("Entrada inválida. Certifique-se de digitar valores válidos para horas, minutos e segundos.");
        } else {
            iniciarTemporizador(horas, minutos, segundos);
            stopAlertSound();
        }

    }

    public static void iniciarTemporizador(int horas, int minutos, int segundos) {
        final int[] contagem = {horas * 3600 + minutos * 60 + segundos};

        Timer timer = new Timer();
        TimerTask tarefa = new TimerTask() {
            @Override
            public void run() {
                if (contagem[0] > 0) {
                    int horas = contagem[0] / 3600;
                    int minutos = (contagem[0] % 3600) / 60;
                    int segundos = contagem[0] % 60;

                    System.out.printf("\rTempo restante: %02d:%02d:%02d", horas, minutos, segundos);
                    contagem[0]--;


                } else {
                    System.out.println("Temporizador finalizado!");
                    timer.cancel();
                    playAlertSound();
                    digitar();
                }
            }
        };

        timer.scheduleAtFixedRate(tarefa, 0, 1000);
    }

    public static void playAlertSound() {
        new JFXPanel();

        String audioFile = "/home/rafael/Downloads/time/src/main/java/com/example/time/temporizador/alarme.mp3";
        Media sound = new Media(new File(audioFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    public static void stopAlertSound() {
        if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.stop();
        }
    }

}


