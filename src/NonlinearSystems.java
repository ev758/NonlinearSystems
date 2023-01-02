import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class NonlinearSystems {

    public static void main(String[] args) {
        //declarations
        String equation = "";
        int option = 0;
        Scanner input = new Scanner(System.in);

        //get user input
        System.out.println("(a) f(x) = 2x^3 - 11.7x^2 + 17.7x - 5");
        System.out.println("(b) f(x) = x + 10 - xcosh(50/x)");
        System.out.print("Select a function: ");
        equation = input.nextLine();
        System.out.print("\n");

        if (equation.equals("a")) {
            System.out.println("1) Bisection");
            System.out.println("2) Newton-Raphson");
            System.out.println("3) Secant");
            System.out.println("4) False-Position");
            System.out.print("\n");

            System.out.print("Select an option: ");
            option = input.nextInt();
            System.out.print("\n");

            if (option == 1) {
                bisection(input, equation);
            }
            else if (option == 2) {
                newtonRaphson(input, equation);
            }
            else if (option == 3) {
                secant(input, equation);
            }
            else if (option == 4) {
                falsePosition(input, equation);
            }
            else {
                System.out.print("Exiting");
                input.close();
                System.exit(0);
            }
        }
        else if (equation.equals("b")) {
            System.out.println("1) Bisection");
            System.out.println("2) Newton-Raphson");
            System.out.println("3) Secant");
            System.out.println("4) False-Position");
            System.out.print("\n");

            System.out.print("Select an option: ");
            option = input.nextInt();
            System.out.print("\n");

            if (option == 1) {
                bisection(input, equation);
            }
            else if (option == 2) {
                newtonRaphson(input, equation);
            }
            else if (option == 3) {
                secant(input, equation);
            }
            else if (option == 4) {
                falsePosition(input, equation);
            }
            else {
                System.out.print("Exiting");
                input.close();
                System.exit(0);
            }
        }
        else {
            System.out.print("Exiting");
            input.close();
            System.exit(0);
        }

        input.close();
    }

    //calculates function a and b to get relative error with Bisection
    public static void bisection(Scanner inputPar, String equationPar) {
        //declarations
        double a = 0.0;
        double b = 0.0;
        double fa = 0.0;
        double fb = 0.0;
        double c = 0.0;
        double fc = 0.0;
        double prevC = 0.0;
        double relativeError = 0.0;
        String relativeErrorText = "";

        //get user input
        System.out.print("Enter the start value of a: ");
        a = inputPar.nextDouble();
        System.out.print("\n");

        System.out.print("Enter the start value of b: ");
        b = inputPar.nextDouble();
        System.out.print("\n");

        System.out.print("n\t\ta\t\tf(a)\t\tb\t\tf(b)\t\tc\t\tf(c)\t\trelative error\t\tnext interval");
        System.out.print("\n");

        //calculates a's function
        if (equationPar.equals("a")) {

            for (int i = 0; i <= 100; i++) {
                //calculates f(a), f(b), c, f(c), and relative error
                fa = ((2 * Math.pow(a, 3)) - (11.7 * Math.pow(a, 2)) + (17.7 * a) - 5);
                fb = ((2 * Math.pow(b, 3)) - (11.7 * Math.pow(b, 2)) + (17.7 * b) - 5);
                c = (a + b) / 2;
                fc = ((2 * Math.pow(c, 3)) - (11.7 * Math.pow(c, 2)) + (17.7 * c) - 5);

                if (i > 0) {
                    relativeError = Math.abs(c - prevC) / Math.abs(c);
                    prevC = c;
                    System.out.printf(i + "\t   %.4f\t%.4f\t   %.4f\t%.4f\t   %.4f\t" +
                            "%.4f\t     %.4f", a, fa, b, fb, c, fc, relativeError);
                    relativeErrorText = relativeErrorText + relativeError + "\n";
                }
                else {
                    prevC = c;
                    System.out.printf(i + "\t   %.4f\t%.4f\t   %.4f\t%.4f\t   %.4f\t" +
                            "%.4f\t     -\t", a, fa, b, fb, c, fc);
                }

                //gets next interval
                if (fa * fc < 0) {
                    if (a < c) {
                        b = c;
                    }
                    else {
                        b = a;
                        a = c;
                    }
                    System.out.print("\t\t\t\t" + "[" + a + ", " + b + "]\n");
                }
                else {
                    if (b < c) {
                        a = b;
                        b = c;
                    }
                    else {
                        a = c;
                    }
                    System.out.print("\t\t\t\t" + "[" + a + ", " + b + "]\n");
                }

                //relative errors are put in a text file
                if (i > 0 && relativeError < 0.01) {
                    try {
                        FileWriter textFile = new FileWriter("bisection_a.txt");
                        textFile.write(relativeErrorText);
                        textFile.close();
                        System.exit(1);
                    }
                    catch (IOException fileError) {
                        System.out.print("file error");
                        System.exit(0);
                    }
                }
            }
        }
        //calculates b's function
        else if (equationPar.equals("b")) {
            for (int i = 0; i <= 100; i++) {
                //calculates f(a), f(b), c, f(c), and relative error
                fa = a + 10 - (a * Math.cosh(50 / a));
                fb = b + 10 - (b * Math.cosh(50 / b));
                c = (a + b) / 2;
                fc = c + 10 - (c * Math.cosh(50 / c));

                if (i > 0) {
                    relativeError = Math.abs(c - prevC) / Math.abs(c);
                    prevC = c;
                    System.out.printf(i + "\t   %.4f\t%.4f\t   %.4f\t%.4f\t   %.4f\t" +
                            "%.4f\t     %.4f", a, fa, b, fb, c, fc, relativeError);
                    relativeErrorText = relativeErrorText + relativeError + "\n";
                }
                else {
                    prevC = c;
                    System.out.printf(i + "\t   %.4f\t%.4f\t   %.4f\t%.4f\t   %.4f\t" +
                            "%.4f\t     -\t", a, fa, b, fb, c, fc);
                }

                //gets next interval
                if (fa * fc < 0) {
                    if (a < c) {
                        b = c;
                    }
                    else {
                        b = a;
                        a = c;
                    }
                    System.out.print("\t\t\t\t" + "[" + a + ", " + b + "]\n");
                }
                else {
                    if (b < c) {
                        a = b;
                        b = c;
                    }
                    else {
                        a = c;
                    }
                    System.out.print("\t\t\t\t" + "[" + a + ", " + b + "]\n");
                }

                //relative errors are put in a text file
                if (i > 0 && relativeError < 0.01) {
                    try {
                        FileWriter textFile = new FileWriter("bisection_b.txt");
                        textFile.write(relativeErrorText);
                        textFile.close();
                        System.exit(1);
                    }
                    catch (IOException fileError) {
                        System.out.print("file error");
                        System.exit(0);
                    }
                }
            }
        }
    }

    //calculates function a and b to get relative error with Newton-Raphson
    public static void newtonRaphson(Scanner inputPar, String equationPar) {
        //declarations
        double x = 0;
        double newX = 0;
        double fx = 0;
        double fxDerivative = 0;
        double d = 0;
        double relativeError = 0;
        String relativeErrorText = "";

        //get user input
        System.out.print("Enter the start value of x: ");
        x = inputPar.nextDouble();
        System.out.print("\n");

        System.out.print("n\t\tXn\t\tf(Xn)\t\tf'(Xn)\t\td\t\tXn+1\t\trelative error");
        System.out.print("\n");

        //calculates a's function
        if (equationPar.equals("a")) {
            for (int i = 0; i <= 100; i++) {
                //calculates f(Xn), f'(Xn), d, Xn+1, and relative error
                fx = ((2 * Math.pow(x, 3)) - (11.7 * Math.pow(x, 2)) + (17.7 * x) - 5);
                fxDerivative = (((3 * 2) * Math.pow(x, 2)) - ((2 * 11.7) * x) + 17.7);
                d = fx / fxDerivative;
                newX = x - d;

                if (i > 0) {
                    relativeError = Math.abs(newX - x) / Math.abs(newX);
                    System.out.printf(i + "\t   %.4f\t%.4f\t   %.4f" +
                            "\t%.4f\t   %.4f\t\t%.4f", x, fx, fxDerivative, d, newX, relativeError);
                    System.out.print("\n");
                    relativeErrorText = relativeErrorText + relativeError + "\n";

                    //relative errors are put in a text file
                    if (relativeError < 0.01) {
                        try {
                            FileWriter textFile = new FileWriter("newton_raphson_a.txt");
                            textFile.write(relativeErrorText);
                            textFile.close();
                            System.exit(1);
                        }
                        catch (IOException fileError) {
                            System.out.print("file error");
                            System.exit(0);
                        }
                    }

                    x = newX;
                }
                else {
                    System.out.printf(i + "\t   %.4f\t%.4f\t   %.4f" +
                            "\t%.4f\t   %.4f\t\t-", x, fx, fxDerivative, d, newX);
                    System.out.print("\n");
                    x = newX;
                }
            }
        }
        //calculates b's function
        else if (equationPar.equals("b")) {
            for (int i = 0; i <= 100; i++) {
                //calculates fx, derivative fx, d, Xn+1, and relative error
                fx = x + 10 - (x * Math.cosh(50 / x));
                fxDerivative = 1 - ((x * (-1 * (50 / Math.pow(x, 2)) * -Math.sinh(50 / x)))
                + (Math.cosh(50 / x) * 1));
                d = fx / fxDerivative;
                newX = x - d;

                if (i > 0) {
                    relativeError = Math.abs(newX - x) / Math.abs(newX);
                    System.out.printf(i + "\t   %.4f\t%.4f\t   %.4f" +
                            "\t%.4f\t   %.4f\t\t%.4f", x, fx, fxDerivative, d, newX, relativeError);
                    System.out.print("\n");
                    relativeErrorText = relativeErrorText + relativeError + "\n";

                    //relative errors are put in a text file
                    if (relativeError < 0.01) {
                        try {
                            FileWriter textFile = new FileWriter("newton_raphson_b.txt");
                            textFile.write(relativeErrorText);
                            textFile.close();
                            System.exit(1);
                        }
                        catch (IOException fileError) {
                            System.out.print("file error");
                            System.exit(0);
                        }
                    }

                    x = newX;
                }
                else {
                    System.out.printf(i + "\t   %.4f\t%.4f\t   %.4f" +
                            "\t%.4f\t   %.4f\t\t-", x, fx, fxDerivative, d, newX);
                    System.out.print("\n");
                    x = newX;
                }
            }
        }
    }

    //calculates function a and b to get relative error with Secant
    public static void secant(Scanner inputPar, String equationPar) {
        //declarations
        double x = 0.0;
        double newX = 0.0;
        double prevX = 0.0;
        double fx = 0.0;
        double prevfx = 0.0;
        double relativeError = 0.0;
        String relativeErrorText = "";

        //get user input
        System.out.print("Enter the start value of x0: ");
        prevX = inputPar.nextDouble();
        System.out.print("\n");

        System.out.print("Enter the start value of x1: ");
        x = inputPar.nextDouble();
        System.out.print("\n");

        System.out.print("n\t\tXn-1\t\tXn\t\tf(Xn)\t\tf(Xn-1)\t\tXn+1\t\trelative error");
        System.out.print("\n");

        //calculates a's function
        if (equationPar.equals("a")) {
            for (int i = 1; i <= 100; i++) {
                //calculates f(Xn), f(Xn-1), Xn+1, and relative error
                fx = ((2 * Math.pow(x, 3)) - (11.7 * Math.pow(x, 2)) + (17.7 * x) - 5);
                prevfx = ((2 * Math.pow(prevX, 3)) - (11.7 * Math.pow(prevX, 2)) + (17.7 * prevX) - 5);
                newX = x - ((fx * (x - prevX)) / (fx - prevfx));

                relativeError = Math.abs(newX - x) / Math.abs(newX);
                System.out.printf(i + "\t   %.4f\t   %.4f\t%.4f" +
                        "\t   %.4f\t   %.4f\t\t%.4f", prevX, x, fx, prevfx, newX, relativeError);
                System.out.print("\n");
                relativeErrorText = relativeErrorText + relativeError + "\n";

                //relative errors are put in a text file
                if (relativeError < 0.01) {
                    try {
                        FileWriter textFile = new FileWriter("secant_a.txt");
                        textFile.write(relativeErrorText);
                        textFile.close();
                        System.exit(1);
                    }
                    catch (IOException fileError) {
                        System.out.print("file error");
                        System.exit(0);
                    }
                }

                prevX = x;
                x = newX;
            }
        }
        //calculates b's function
        else if (equationPar.equals("b")) {
            for (int i = 1; i <= 100; i++) {
                //calculates f(Xn), f(Xn-1), Xn+1, and relative error
                fx = x + 10 - (x * Math.cosh(50 / x));
                prevfx = prevX + 10 - (prevX * Math.cosh(50 / prevX));
                newX = x - ((fx * (x - prevX)) / (fx - prevfx));

                relativeError = Math.abs(newX - x) / Math.abs(newX);
                System.out.printf(i + "\t   %.4f\t   %.4f\t%.4f" +
                        "\t   %.4f\t   %.4f\t\t%.4f", prevX, x, fx, prevfx, newX, relativeError);
                System.out.print("\n");
                relativeErrorText = relativeErrorText + relativeError + "\n";

                //relative errors are put in a text file
                if (relativeError < 0.01) {
                    try {
                        FileWriter textFile = new FileWriter("secant_b.txt");
                        textFile.write(relativeErrorText);
                        textFile.close();
                        System.exit(1);
                    }
                    catch (IOException fileError) {
                        System.out.print("file error");
                        System.exit(0);
                    }
                }

                prevX = x;
                x = newX;
            }
        }
    }

    //calculates function a and b to get relative error with False-Position
    public static void falsePosition(Scanner inputPar, String equationPar) {
        //declarations
        double a = 0.0;
        double b = 0.0;
        double fa = 0.0;
        double fb = 0.0;
        double c = 0.0;
        double fc = 0.0;
        double prevC = 0.0;
        double relativeError = 0.0;
        String relativeErrorText = "";

        //get user input
        System.out.print("Enter the start value of a: ");
        a = inputPar.nextDouble();
        System.out.print("\n");

        System.out.print("Enter the start value of b: ");
        b = inputPar.nextDouble();
        System.out.print("\n");

        System.out.print("n\t\ta\t\tb\t\tf(a)\t\tf(b)\t\tc\t\tf(c)\t\trelative error\t\tnext interval");
        System.out.print("\n");

        //calculates a's function
        if (equationPar.equals("a")) {

            for (int i = 0; i <= 100; i++) {
                //calculates f(a), f(b), c, f(c), and relative error
                fa = ((2 * Math.pow(a, 3)) - (11.7 * Math.pow(a, 2)) + (17.7 * a) - 5);
                fb = ((2 * Math.pow(b, 3)) - (11.7 * Math.pow(b, 2)) + (17.7 * b) - 5);
                c = a + ((-fa * (b - a)) / (fb - fa));
                fc = ((2 * Math.pow(c, 3)) - (11.7 * Math.pow(c, 2)) + (17.7 * c) - 5);

                if (i > 0) {
                    relativeError = Math.abs(c - prevC) / Math.abs(c);
                    prevC = c;
                    System.out.printf(i + "\t   %.4f\t%.4f\t%.4f\t   %.4f\t   %.4f\t" +
                            "%.4f\t     %.4f", a, b, fa, fb, c, fc, relativeError);
                    relativeErrorText = relativeErrorText + relativeError + "\n";
                }
                else {
                    prevC = c;
                    System.out.printf(i + "\t   %.4f\t%.4f\t%.4f\t   %.4f\t   %.4f\t" +
                            "%.4f\t     -\t", a, b, fa, fb, c, fc);
                }

                //gets next interval
                if (fa * fc < 0) {
                    if (a < c) {
                        b = c;
                    }
                    else {
                        b = a;
                        a = c;
                    }
                    System.out.print("\t\t\t\t" + "[" + a + ", " + b + "]\n");
                }
                else {
                    if (b < c) {
                        a = b;
                        b = c;
                    }
                    else {
                        a = c;
                    }
                    System.out.print("\t\t\t\t" + "[" + a + ", " + b + "]\n");
                }

                //relative errors are put in a text file
                if (i > 0 && relativeError < 0.01) {
                    try {
                        FileWriter textFile = new FileWriter("false_position_a.txt");
                        textFile.write(relativeErrorText);
                        textFile.close();
                        System.exit(1);
                    }
                    catch (IOException fileError) {
                        System.out.print("file error");
                        System.exit(0);
                    }
                }
            }
        }
        //calculates b's function
        else if (equationPar.equals("b")) {
            for (int i = 0; i <= 100; i++) {
                //calculates f(a), f(b), c, f(c), and relative error
                fa = a + 10 - (a * Math.cosh(50 / a));
                fb = b + 10 - (b * Math.cosh(50 / b));
                c = a + ((-fa * (b - a)) / (fb - fa));
                fc = c + 10 - (c * Math.cosh(50 / c));

                if (i > 0) {
                    relativeError = (c - prevC) / c;
                    prevC = c;
                    System.out.printf(i + "\t   %.4f\t%.4f\t%.4f\t   %.4f\t   %.4f\t" +
                            "%.4f\t     %.4f", a, b, fa, fb, c, fc, relativeError);
                    relativeErrorText = relativeErrorText + relativeError + "\n";
                }
                else {
                    prevC = c;
                    System.out.printf(i + "\t   %.4f\t%.4f\t%.4f\t   %.4f\t   %.4f\t" +
                            "%.4f\t     -\t", a, b, fa, fb, c, fc);
                }

                //gets next interval
                if (fa * fc < 0) {
                    if (a < c) {
                        b = c;
                    }
                    else {
                        b = a;
                        a = c;
                    }
                    System.out.print("\t\t\t\t" + "[" + a + ", " + b + "]\n");
                }
                else {
                    if (b < c) {
                        a = b;
                        b = c;
                    }
                    else {
                        a = c;
                    }
                    System.out.print("\t\t\t\t" + "[" + a + ", " + b + "]\n");
                }

                //relative errors are put in a text file
                if (i > 0 && relativeError < 0.01) {
                    try {
                        FileWriter textFile = new FileWriter("false_position_b.txt");
                        textFile.write(relativeErrorText);
                        textFile.close();
                        System.exit(1);
                    }
                    catch (IOException fileError) {
                        System.out.print("file error");
                        System.exit(0);
                    }
                }
            }
        }
    }
}
