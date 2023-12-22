package org.javaacadmey.wonder_field.game;

import org.javaacadmey.wonder_field.domain.Exercise;
import org.javaacadmey.wonder_field.player.Player;
import org.javaacadmey.wonder_field.player.PlayerAnswer;
import org.javaacadmey.wonder_field.player.TypeAnswer;

public class Yakubovich {
    //  Условие 4.2
    public void startGame() {
        System.out.println("Якубович: Здравствуйте, уважаемые дамы и господа! Пятница! В эфире капитал-шоу «Поле чудес»!");
    }

    //  Условие 4.3
    public void endGame() {
        System.out.println("Якубович: Мы прощаемся с вами ровно на одну неделю! Здоровья вам, до встречи!");
    }

    //  Условие 4.4
    public void invitePlayers(Player[] players, int numberOfRounds) {
        if (numberOfRounds < 3) {
            System.out.println("Якубович: приглашаю " + (numberOfRounds + 1) + " тройку игроков: " + concatNames(players));
        } else {
            System.out.println("Якубович: приглашаю победителей групповых этапов: " + concatNames(players));
        }
    }

    private String concatNames(Player[] players) {
        return String.join(", ", Game.takeNamePlayers(players));
    }

    //  Условие 4.5
    public void askQuestion(Exercise exercise) {
        System.out.println("Якубович: Внимание вопрос!\n" + exercise.getQuestion());
    }

    //  Условие 4.6
    public void nameWinner(Player player, int numberOfRounds) {
        if (numberOfRounds < 3) {
            System.out.printf("Якубович: Молодец! %s из %s проходит в финал! Колличество очков: %d \n",
                    player.getName(),
                    player.getCity(),
                    player.getGamePoints());
        } else if (numberOfRounds == 3) {
            System.out.printf("Якубович: И перед нами победитель четырех руадов Капитал шоу поле чудес! Это %s из %s. Колличество очков: %d .Призовые деньги %d\n",
                    player.getName(),
                    player.getCity(),
                    player.getGamePoints(),
                    player.getMoney());
        } else {
            System.out.printf("Якубович: И перед нами победитель Капитал шоу поле чудес! Это %s из %s. Колличество очков: %d .Призовые деньги %d. Призы %s\n",
                    player.getName(),
                    player.getCity(),
                    player.getGamePoints(),
                    player.getMoney(),
                    player.getItems());
        }
    }

    //  Условие 4.7
    public boolean checkAnswerPlayer(PlayerAnswer playerAnswer, Exercise exercise, Tableau tableau) {
        String correctAnswer = exercise.getAnswer();

        if (playerAnswer.getTypeAnswer().equals(TypeAnswer.LETTER)) {
            return checkLetter(playerAnswer, correctAnswer, tableau);
        } else {
            return checkWord(playerAnswer, correctAnswer, tableau);
        }
    }

    private boolean checkLetter(PlayerAnswer playerAnswer, String correctAnswer, Tableau tableau) {
        if (tableau.checkOpenLetters(playerAnswer)) {
            System.out.println("Якубович: Такая буква уже открыта!");
            tableau.showTableau();
            return true;
        } else {
            String[] split = correctAnswer.split("");
            for (String str : split) {
                if (str.toLowerCase().contains(playerAnswer.getAnswer().toLowerCase())) {
                    System.out.println("Якубович: Есть такая буква, откройте ее!");
                    tableau.openLetter(playerAnswer);
                    tableau.showTableau();
                    return true;
                }
            }
        }
        System.out.println("Якубович: Нет такой буквы! Следующий игрок, крутите барабан!");
        System.out.println("__________________________________");
        return false;
    }

    private boolean checkWord(PlayerAnswer playerAnswer, String correctAnswer, Tableau tableau) {
        String answer = playerAnswer.getAnswer();
        if (answer.equalsIgnoreCase(correctAnswer)) {
            System.out.println("Якубович: " + answer + "! Абсолютно верно!");
            tableau.openCorrectAnswer(answer);
            return true;
        } else {
            System.out.println("Якубович: Неверно! Следующий игрок!");
            System.out.println("__________________________________");
            return false;
        }
    }

    public void skipPlayer() {
        System.out.println("На барабане пропуск хода! Следующий игрок, крутите барабан!");
    }

    public boolean askAboutSuperGame(Player winner) {
        while (true) {
            System.out.println(winner.getName() + ", будете ли вы участвовоать в супер игре? Если 'да' - то введите 'д', если 'нет', то введите 'н'");
            String answer = Game.readConsole();
            switch (answer) {
                case ("д") -> {
                    return true;
                }
                case ("н") -> {
                    return false;
                }
                default -> System.out.println("Некорректное значение, введите 'д' или 'н'");
            }
        }
    }
}
