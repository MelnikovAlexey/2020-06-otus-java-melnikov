package org.otus.education.hw13;

import org.otus.education.hw13.appcontainer.AppComponentsContainerImpl;
import org.otus.education.hw13.appcontainer.api.AppComponentsContainer;
import org.otus.education.hw13.config.AppConfig;
import org.otus.education.hw13.services.GameProcessor;
import org.otus.education.hw13.services.GameProcessorImpl;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How to start GameProcessor:");
        System.out.println("1 - interface  (GameProcessor.class (default))");
        System.out.println("2 - implementation class (GameProcessorImpl.class)");
        System.out.println("3 - component name (  \"gameProcessor\")");
        System.out.print("");
        GameProcessor gameProcessor = initGame(scanner.next());

        gameProcessor.startGame();
    }

    private static GameProcessor initGame(String value) {
        AppComponentsContainer container = new AppComponentsContainerImpl(AppConfig.class);
        switch (value) {
            default:
            case "1":
                return container.getAppComponent(GameProcessor.class);
            case "2":
                return container.getAppComponent(GameProcessorImpl.class);
            case "3":
                return container.getAppComponent("gameProcessor");
        }
    }
}
