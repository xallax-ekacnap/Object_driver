import static java.lang.Math.pow;
import java.util.Scanner;

/*
 * Class: Lightbulb
 * By Aaron Hoffman
 * Created as a demo for AP CS on 10/8/23
 */

public class Lightbulb {
    //This class creates a model of a Lightbulb, and can be turned on or off, 
    //  which affects its tempersature.

    public static void main(String[] args) {
        //Main method initialized stuff and starts the while loop
        Lightbulb bulb = new Lightbulb("test");
        System.out.println("Choose a command: status, change (s/c):");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Command: ");
            String command = scanner.nextLine();
            if (command.equals("s")) {
                bulb.printStatus(bulb);
            }

            else if (command.equals("c")) {
                bulb.changeStatus(bulb);
            }

            else if (command.equals("exit")) {
                break;
            }
        } 
        scanner.close();
    }

    private boolean bulb_status;

    public Lightbulb(String set_to) {
        //create new Lightbulb and set off
        this.bulb_status = false;
        this.bulb_temp = 70.0;
    }

    public void printStatus(Lightbulb bulb) {
        //prints the attributes / status of the lighbulb
        bulb.getTemp();
        System.out.println(this.bulb_temp);
        System.out.println(this.bulb_status);
    }

    private long onTime;
    private long offTime;
    public void changeStatus(Lightbulb bulb) {
        //flips the lightbulb on or off, and starts the timer of how long the lightbulb has been on.
        bulb.getTemp();
        this.bulb_status = !this.bulb_status;
        if (bulb_status) {
            this.onTime = System.currentTimeMillis();
        }
        else {
            this.offTime = System.currentTimeMillis();
        }
    }

    private double bulb_temp;
    private double timeSinceOn;
    private double timeSinceOff;
    public double getTemp() {
        //gets the temperature of the lighbulg using the exponential decay equation
        if (this.bulb_status) {
            if (this.timeSinceOn != 0.0) {
                this.timeSinceOn = System.currentTimeMillis() - this.onTime;
            }
            double multiplier = -0.0174970354 * (timeSinceOn / 1000);
            this.bulb_temp = 200 + (this.bulb_temp - 200) * pow(2.718281828, multiplier);
            this.timeSinceOn = 0.0000001;
            return this.bulb_temp;
        } else {
            if (this.timeSinceOff != 0.0) {
                this.timeSinceOff = System.currentTimeMillis() - this.onTime;
            }
            double multiplier = -0.0174970354 * (this.timeSinceOff / 1000);
            this.bulb_temp = 70 + (this.bulb_temp - 70) * pow(2.718281828, multiplier);
            this.timeSinceOff = 0.0000001;
            return this.bulb_temp;
        }
        
    }


}