package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.VoltageSensor;

@Autonomous(name = "DriveForward1Sec", group = "Autonomous")
public class DriveAuto extends LinearOpMode
{
    private DcMotor motor1;
    private DcMotor motor2;
    private DcMotor motor3;
    private DcMotor motor4;

    private VoltageSensor VoltageSensor0;
    double MotorSpeed;
    double Raport;
    double VoltajActual;
    double VoltajDorit;

    @Override
    public void runOpMode()
    {
        motor1 = hardwareMap.get(DcMotor.class, "stsus");
        motor2 = hardwareMap.get(DcMotor.class, "drsus");
        motor3 = hardwareMap.get(DcMotor.class, "drjos");
        motor4 = hardwareMap.get(DcMotor.class, "stjos");
        VoltageSensor0 = hardwareMap.voltageSensor.iterator().next();

        motor1.setDirection(DcMotor.Direction.REVERSE);
        motor2.setDirection(DcMotor.Direction.FORWARD);
        motor3.setDirection(DcMotor.Direction.REVERSE);
        motor4.setDirection(DcMotor.Direction.FORWARD);


        waitForStart();


        UpdateVoltage();
        DriveForward(250);
        UpdateVoltage();
        DriveBackwards(250);
        UpdateVoltage();
        DriveLeft(250);
        UpdateVoltage();
        DriveRight(250);

    }
    private void DriveForward(long miliseconds)
    {
        motor1.setPower(MotorSpeed);
        motor2.setPower(MotorSpeed);
        motor3.setPower(MotorSpeed);
        motor4.setPower(MotorSpeed);
        sleep(miliseconds);
    }
    private void DriveBackwards(long miliseconds)
    {
        motor1.setPower(-MotorSpeed);
        motor2.setPower(-MotorSpeed);
        motor3.setPower(-MotorSpeed);
        motor4.setPower(-MotorSpeed);
        sleep(miliseconds);
    }
    private void DriveLeft(long miliseconds)
    {
        motor1.setPower(-MotorSpeed);
        motor2.setPower(MotorSpeed);
        motor3.setPower(-MotorSpeed);
        motor4.setPower(MotorSpeed);
        sleep(miliseconds);
    }
    private void DriveRight(long miliseconds)
    {
        motor1.setPower(MotorSpeed);
        motor2.setPower(-MotorSpeed);
        motor3.setPower(MotorSpeed);
        motor4.setPower(-MotorSpeed);
        sleep(miliseconds);
    }
    private void UpdateVoltage()
    {
        //Voltaju cu care vrei sa mearga motoarele
        VoltajDorit = 12;

        //Voltajul actual
        VoltajActual = VoltageSensor0.getVoltage();

        //raportul pentru calcularea a vitezei
        Raport = VoltajDorit/VoltajActual;

        //Viteza motoarelor modificata de raport
        MotorSpeed = 1 * Raport;
    }

}
