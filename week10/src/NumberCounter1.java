// original from: http://rosettacode.org/wiki/Pi_set#Java
// modified for color

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.zip.GZIPInputStream;

public class NumberCounter1 extends JFrame {

    private final int LENGTH_OF_SQUARE = 3;
    private final int LENGTH 	       = 330;
    private final int LENGTH_OF_WINDOW = LENGTH * LENGTH_OF_SQUARE;
    private BufferedImage theImage;
    private String fileName = null;
    Reader input;

    public NumberCounter1() {
        super("Visual");

        setBounds(100, 100, LENGTH_OF_WINDOW, LENGTH_OF_WINDOW);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public NumberCounter1(String fileName) {
        this();
        this.fileName = fileName;
    }

    private char nextDigit(BufferedReader input) throws IOException {
        char buf[] = new char[1];
        if (input.read() != '\n' || (char)input.read() != '.') {
            buf [0] = (char)input.read();
        }
        return buf[0];
    }


    private void saveImage(BufferedImage theImage)	{
        String suffix = "png";
        String outputFileName = fileName == null ? "output" : fileName + "_output" + "." + suffix ;
        try {
            File outputfile = new File(outputFileName);
            ImageIO.write(theImage, suffix, outputfile);
        } catch (Exception e )	{
            e.printStackTrace();
        }

    }
    public void fillSquare(int xOrig, int yOrig, int color)	{
        for (int x = 0; x < LENGTH_OF_SQUARE; x ++ )
            for (int y = 0; y < LENGTH_OF_SQUARE; y ++ )
                theImage.setRGB(xOrig + x, yOrig + y , color);
    }
    public void createImage()	{
        theImage = new BufferedImage(getWidth(), getHeight(),
                BufferedImage.TYPE_INT_RGB);
        int red = Color.RED.getRGB();		// you might like to change the colors if you like
        int blue = Color.BLUE.getRGB();		// you might like to change the colors if you like
        int colorUsed;
            try{
                BufferedReader input = fileReader();

            for (int y = 0; y < getHeight(); y += LENGTH_OF_SQUARE) {
                for (int x = 0; x < getWidth(); x += LENGTH_OF_SQUARE) {
                    char digit = nextDigit(input);
                    fillSquare(x, y,  digit % 2 == 0 ? red : blue );
                }

            }
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }
        repaint();
        saveImage(theImage);
        System.exit(0);
    }

    public BufferedReader fileReader() throws IOException {
        try{
            if (this.fileName == null){
                return new BufferedReader(new InputStreamReader(System.in));
            }
            if (this.fileName.contains(".gz")) {
                return new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(this.fileName))));
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return new BufferedReader(new FileReader(this.fileName));
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(theImage, 0, 0, this);
    }

    public static void main(String[] args) {
        NumberCounter1 aNumberCounter1 = new NumberCounter1(args.length == 1 ? args[0] : null );
        aNumberCounter1.setVisible(true);
        aNumberCounter1.createImage();
    }
}
