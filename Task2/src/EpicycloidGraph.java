package Task2.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.Path2D;
import java.util.Random;

public class EpicycloidGraph extends JPanel {
    private Color graphColor = Color.RED;
    private Stroke graphStroke = new BasicStroke(2.0f);
    private String authorInfo = "Автор: Андрощук Станіслав";
    private String variantInfo = "№ варіанта: 1";
    private double A = 35.0;
    private double a = 6.0;

    private double scaleX = 50.0; // Масштаб по X
    private double scaleY = 50.0; // Масштаб по Y
    private double offsetX = 0.0; // Зсув по X
    private double offsetY = 0.0; // Зсув по Y

    public EpicycloidGraph() {
        setBackground(Color.WHITE);

        // Обробник для зміни параметрів графіка при кліку мишкою
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changeGraphParameters();
                repaint();
            }
        });

        // Обробник для зміни масштабу колесом миші
        addMouseWheelListener(new MouseAdapter() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                int notches = e.getWheelRotation();
                if (notches < 0) {
                    scaleX *= 1.1;
                    scaleY *= 1.1;
                } else {
                    scaleX /= 1.1;
                    scaleY /= 1.1;
                }
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        // Розміри вікна
        int width = getWidth();
        int height = getHeight();

        // Очистка вікно
        g2d.clearRect(0, 0, width, height);

        // Координатна площина
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(1.0f));
        int xCenter = width / 2;
        int yCenter = height / 2;
        g2d.drawLine(0, yCenter, width, yCenter); // Горизонтальна вісь
        g2d.drawLine(xCenter, 0, xCenter, height); // Вертикальна вісь


        // Вивід інформацію
        g2d.setColor(Color.BLACK);
        g2d.drawString(authorInfo, 10, (int) g.getClipBounds().getHeight()-20);
        g2d.drawString(variantInfo, 10, (int)g.getClipBounds().getHeight()-40);

        // Епіциклоїда
        g2d.setColor(graphColor);
        g2d.setStroke(graphStroke);

        Path2D path = new Path2D.Double();

        for (double t = 0; t <= 20 * Math.PI; t += 0.01) { // мінімум 4 * pi для цього варіанту
            double x = (A + a) * Math.cos(t) - a * Math.cos((A / a + 1) * t);
            double y = (A + a) * Math.sin(t) - a * Math.sin((A / a + 1) * t);
            double xScaled = x * scaleX + xCenter + offsetX;
            double yScaled = -y * scaleY + yCenter + offsetY;

            if (t == 0) {
                path.moveTo(xScaled, yScaled);
            } else {
                path.lineTo(xScaled, yScaled);
            }
        }

        g2d.draw(path);
    }

    private void changeGraphParameters() {
        // Зміна параметрів графіка випадковим чином
        Random random = new Random();
        graphColor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        float strokeWidth = random.nextFloat() * 5.0f + 1.0f;
        graphStroke = new BasicStroke(strokeWidth);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Епіциклоїда");
            EpicycloidGraph epicycloidGraph = new EpicycloidGraph();
            frame.add(epicycloidGraph);
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
