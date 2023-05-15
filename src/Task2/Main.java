package Task2;

import javax.swing.*;
import java.awt.*;


public class Main {
    public static void main(String[] args) {
        // Створення і налаштування графічного вікна
        JFrame frame = new JFrame("Рух об'єктів");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);




        // Клас для малювання об'єктів
        class DrawingPanel extends JPanel {
            private int x1 = 0; // Початкова позиція об'єкту 1 по осі X
            private int y2 = 0; // Початкова позиція об'єкту 2 по осі Y

            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(151, 6, 49));
                g.fillRect(x1, getHeight() / 2 - 10, 20, 20); // Малювання об'єкту 1
                g.setColor(new Color(0, 114, 62));
                g.fillRect(getWidth() / 2 - 10, y2, 20, 20); // Малювання об'єкту 2
            }
        }




        // Додавання панелі для малювання об'єктів до графічного вікна
        DrawingPanel panel = new DrawingPanel();
        frame.add(panel);
        frame.setVisible(true);

        // Поток для руху об'єкту 1 по осі X
        Thread thread1 = new Thread(() -> {
            int velocity = 5; // Швидкість руху об'єкту 1 по осі X
            while (true) {
                panel.x1 += velocity;
                int newX = panel.x1;
                if (newX > frame.getWidth()) {
                    panel.x1 = 0;
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Поток для руху об'єкту 2 по осі Y
        Thread thread2 = new Thread(() -> {
            int velocity = 2;   // Швидкість руху об'єкту 2 по осі Y
            while (true) {
                panel.y2 += velocity;
                int newY = panel.y2;
                if (newY > frame.getWidth()) {
                    panel.y2 = 0;
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        Thread thread3 = new Thread(() -> {
            while (true) {
                panel.repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Запуск потоків
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
