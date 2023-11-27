package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.Range;
//import com.qualcomm.robotcore.hardware.ColorSensor;
//import com.qualcomm.robotcore.hardware.CRServo;


@TeleOp(name = "DriveTeleOP", group = "TeleOp")
public class DriveTeleOP extends LinearOpMode {
    private DcMotor motor1;
    private DcMotor motor2;
    private DcMotor motor3;
    private DcMotor motor4;
    private DcMotor motorBrat;
    private  VoltageSensor VoltageSensor0;

    private Servo servo1;


    @Override
    public void runOpMode() {
        motor1 = hardwareMap.get(DcMotor.class, "stangasus");
        motor2 = hardwareMap.get(DcMotor.class, "dreaptasus");
        motor3 = hardwareMap.get(DcMotor.class, "dreaptajos");
        motor4 = hardwareMap.get(DcMotor.class, "stangajos");
        motorBrat = hardwareMap.get(DcMotor.class, "motorbrat");
        servo1 = hardwareMap.get(Servo.class, "servo");
        VoltageSensor0 = hardwareMap.voltageSensor.iterator().next();

        // Resetarea encoderului

        motorBrat.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBrat.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        servo1.setPosition(-0.5);


        waitForStart();

        while (opModeIsActive())
        {


            // Gamepad1 AxaXY joystick

            double leftStickY = -gamepad1.left_stick_y;
            double leftStickX = gamepad1.left_stick_x;

            // Gamepad1 L&R Trigger

            double LTrigger = gamepad1.left_trigger;
            double RTrigger = gamepad1.right_trigger;

            // Voltaj

            double Voltaj = VoltageSensor0.getVoltage();

            // Gamepad1 bumper

            boolean HalfSpeed = gamepad1.left_bumper;

            // Gamepad1 butoane

            boolean AisPressed = gamepad1.a;
            boolean XisPressed = gamepad1.x;
            boolean YisPressed = gamepad1.y;
            boolean BisPressed = gamepad1.b;

            // Puterea care o primesc motoarele sub forma de double inregistrate de controller
            double movePower = Range.clip(leftStickY, -1, 1);
            double turnPower = Range.clip(leftStickX, -1, 1);
            double rotatePower = RTrigger - LTrigger;

            double power1 = Range.clip(-movePower - turnPower + rotatePower, -1, 1);
            double power2 = Range.clip(-movePower + turnPower - rotatePower, -1, 1);
            double power3 = Range.clip(movePower + turnPower + rotatePower, -1, 1);
            double power4 = Range.clip(-movePower + turnPower - rotatePower, -1, 1);

            // Alte variabile

            boolean IsClawOpened = true;

            // Controlarea bratului

            if (XisPressed)
            {
                SetPos1();
                motorBrat.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }
            else if (YisPressed)
            {
                SetPos2();
                motorBrat.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }
            else if (BisPressed)
            {
                SetPos3();
                motorBrat.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }

            // Deschiderea si inchiderea clawului

            if(IsClawOpened && AisPressed)
            {
                servo1.setPosition(1);
                IsClawOpened = false;
            }
            else if (!IsClawOpened && AisPressed)
            {
                servo1.setPosition(0);
                IsClawOpened = true;
            }







            // Setarea puterilor motoarelor bazat pe apasarea left bumperului

            if(HalfSpeed)
            {
                motor1.setPower(power1/2);
                motor2.setPower(power2/2);
                motor3.setPower(power3/2);
                motor4.setPower(power4/2);
            }
            else
            {
                motor1.setPower(power1);
                motor2.setPower(power2);
                motor3.setPower(power3);
                motor4.setPower(power4);
            }

            // Setarea telemetriei

            telemetry.addData("Motor Powers", "Motor1: %.2f, Motor2: %.2f, Motor3: %.2f, Motor4: %.2f", power1, power2, power3, power4);
            telemetry.addData("Voltajul Bateriei", "Voltaj: %.2f", Voltaj);
            telemetry.addData("IsClawOpen","IsClawOpened: ", IsClawOpened);



            telemetry.update();


            sleep(10);
        }
    }
    // Functii pentru setarea pozitiilor

    // Setarea bratului pe prima pozitie
    public void SetPos1()

    {
        int targetPosition = 0;
        motorBrat.setTargetPosition(targetPosition);
        motorBrat.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBrat.setPower(0.5);
    }
    // Setarea bratului pe a doua pozitie
    public void SetPos2()


    {
        int TICKS_PER_REVOLUTION = 2150;
        int targetPosition = TICKS_PER_REVOLUTION / 100;
        motorBrat.setTargetPosition(targetPosition);
        motorBrat.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBrat.setPower(-0.5);
    }
    // Setarea bratului pe a treia pozitie
    public void SetPos3()
    {
        int TICKS_PER_REVOLUTION = 2150;
        int targetPosition = TICKS_PER_REVOLUTION / 150;
        motorBrat.setTargetPosition(targetPosition);
        motorBrat.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBrat.setPower(-0.5);
    }

}

