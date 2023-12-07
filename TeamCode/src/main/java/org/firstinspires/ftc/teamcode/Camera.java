//package org.firstinspires.ftc.teamcode;
//import org.opencv.core.*;
//import org.opencv.imgproc.Imgproc;
//import org.opencv.videoio.VideoCapture;
//import javax.swing.*;
//import java.awt.image.BufferedImage;
//
//public class Camera {
//    private static final Scalar LOWER_BLUE = new Scalar(100, 100, 100);
//    private static final Scalar UPPER_BLUE = new Scalar(130, 255, 255);
//    private static final Scalar LOWER_RED = new Scalar(0, 100, 100);
//    private static final Scalar UPPER_RED = new Scalar(10, 255, 255);
//
//    public static void main(String[] args) {
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//
//        JFrame frame = new JFrame("Color Detection");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(640, 480);
//        frame.setVisible(true);
//
//        VideoCapture capture = new VideoCapture(0);
//
//        if (!capture.isOpened()) {
//            System.out.println("Error: Camera not detected!");
//            return;
//        }
//
//        Mat frameMat = new Mat();
//
//        while (capture.isOpened()) {
//            if (capture.read(frameMat)) {
//                Mat maskBlue = detectColor(frameMat, LOWER_BLUE, UPPER_BLUE);
//                Mat maskRed = detectColor(frameMat, LOWER_RED, UPPER_RED);
//
//                int blueCount = countNonZero(maskBlue);
//                int redCount = countNonZero(maskRed);
//
//                if (blueCount > redCount)
//                    displayImage(frame, matToBufferedImage(maskBlue));
//                else
//                if (redCount > blueCount)
//                    displayImage(frame, matToBufferedImage(maskRed));
//
//                maskBlue.release();
//                maskRed.release();
//            } else {
//                System.out.println("Error: Could not read frame");
//                break;
//            }
//        }
//        capture.release();
//    }
//
//    private static Mat detectColor(Mat frame, Scalar lowerColor, Scalar upperColor) {
//        Mat hsvImage = new Mat();
//        Imgproc.cvtColor(frame, hsvImage, Imgproc.COLOR_BGR2HSV);
//
//        Mat mask = new Mat();
//        Core.inRange(hsvImage, lowerColor, upperColor, mask);
//
//        hsvImage.release();
//
//        return mask;
//    }
//
//    private static int countNonZero(Mat mat) {
//        Mat nonZero = new Mat();
//        Core.findNonZero(mat, nonZero);
//
//        int count = (int) nonZero.total();
//
//        nonZero.release();
//
//        return count;
//    }
//
//    private static void displayImage(JFrame frame, BufferedImage image) {
//        frame.setContentPane(new JLabel(new ImageIcon(image)));
//        frame.pack();
//    }
//
//    private static BufferedImage matToBufferedImage(Mat matrix) {
//        int cols = matrix.cols();
//        int rows = matrix.rows();
//        int elemSize = (int) matrix.elemSize();
//        byte[] data = new byte[cols * rows * elemSize];
//        matrix.get(0, 0, data);
//        int type;
//        if (matrix.channels() == 1)
//            type = BufferedImage.TYPE_BYTE_GRAY;
//        else
//            type = BufferedImage.TYPE_3BYTE_BGR;
//
//        BufferedImage image = new BufferedImage(cols, rows, type);
//        image.getRaster().setDataElements(0, 0, cols, rows, data);
//
//        return image;
//    }
//}