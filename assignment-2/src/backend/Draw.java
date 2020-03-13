package backend;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Draws the images based on input strings of vector commands
 */

public class Draw {

    private ArrayList < Double > myDoubles;
    private String hex[];

    private Matcher doubles;

    String regexDoubles = "[-+]?\\d*\\.?\\d+([eE][-+]?\\d+)?";

    public Draw(Canvas canvas, String str) {

        // Check for PEN Colour
        if (str.contains("PEN")) {
            hex = str.split("\\s+");

            canvas.getGraphicsContext2D().setFill(Color.TRANSPARENT); // removes fill
            canvas.getGraphicsContext2D().setStroke(Color.valueOf(hex[1]));
        }

        // If FILL is ON
        if (str.contains("FILL")) {
            hex = str.split("\\s+");

            canvas.getGraphicsContext2D().setFill(Color.valueOf(hex[1]));
        }

        // If FILL is OFF
        if (str.contains("OFF")) {
            canvas.getGraphicsContext2D().setFill(Color.TRANSPARENT); // removes fill
        }

        // Check for drawing commands
        if (str.contains("LINE")) {
            // Scans for Doubles
            myDoubles = new ArrayList < Double > ();
            doubles = Pattern.compile(regexDoubles).matcher(str);

            while (doubles.find()) {
                double element = Double.parseDouble(doubles.group());
                myDoubles.add(element);
            }

            Double x1 = myDoubles.get(0) * canvas.getWidth();
            Double y1 = myDoubles.get(1) * canvas.getHeight();
            Double x2 = myDoubles.get(2) * canvas.getWidth();
            Double y2 = myDoubles.get(3) * canvas.getHeight();

            canvas.getGraphicsContext2D().strokeLine(x1, y1, x2, y2);
            clear(canvas);

        } else if (str.contains("PLOT")) {

            // Scans for Doubles
            myDoubles = new ArrayList < Double > ();
            doubles = Pattern.compile(regexDoubles).matcher(str);

            while (doubles.find()) {
                double element = Double.parseDouble(doubles.group());
                myDoubles.add(element);
            }

            Double x1 = myDoubles.get(0) * canvas.getWidth();
            Double y1 = myDoubles.get(1) * canvas.getWidth();


            canvas.getGraphicsContext2D().fillRoundRect(x1, y1, 1, 1, 1, 1);
            clear(canvas);

        } else if (str.contains("RECTANGLE")) {

            // Scans for Doubles
            myDoubles = new ArrayList < Double > ();
            doubles = Pattern.compile(regexDoubles).matcher(str);

            while (doubles.find()) {
                double element = Double.parseDouble(doubles.group());
                myDoubles.add(element);
            }

            Double x1 = myDoubles.get(0) * canvas.getWidth();
            Double y1 = myDoubles.get(1) * canvas.getWidth();
            Double x2 = (myDoubles.get(2)) * canvas.getWidth();
            Double y2 = (myDoubles.get(3) * canvas.getWidth());

            canvas.getGraphicsContext2D().fillRect(x1, y1, (x2 - x1), (y2 - y1));
            canvas.getGraphicsContext2D().strokeRect(x1, y1, (x2 - x1), (y2 - y1));

            myDoubles.clear();

        } else if (str.contains("ELLIPSE")) {

            // Scans for Doubles
            myDoubles = new ArrayList < Double > ();
            doubles = Pattern.compile(regexDoubles).matcher(str);

            while (doubles.find()) {
                double element = Double.parseDouble(doubles.group());
                myDoubles.add(element);
            }

            Double x1 = myDoubles.get(0) * canvas.getWidth();
            Double y1 = myDoubles.get(1) * canvas.getHeight();
            Double x2 = myDoubles.get(2) * canvas.getWidth();
            Double y2 = myDoubles.get(3) * canvas.getHeight();

            canvas.getGraphicsContext2D().fillOval(x1, y1, x2 - x1, y2 - y1);
            canvas.getGraphicsContext2D().strokeOval(x1, y1, x2 - x1, y2 - y1);
            clear(canvas);

        } else if (str.contains("POLYGON")) {
            clear(canvas);
        }
    }

    void clear(Canvas canvas) {
        canvas.getGraphicsContext2D().setFill(Color.TRANSPARENT); // removes any fill command if selected
        canvas.getGraphicsContext2D().setStroke(Color.BLACK); // Resets Pen Tool to Black if currently another colour
    }
}