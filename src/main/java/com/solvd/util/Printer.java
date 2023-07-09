package com.solvd.util;

import java.awt.*;
import java.awt.print.*;

public class Printer {

    public static void print(String content) throws PrinterException {
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        Printable printable = (graphics, pageFormat, pageIndex) -> {
            if (pageIndex > 0) {
                return Printable.NO_SUCH_PAGE;
            }

            Graphics2D g2d = (Graphics2D) graphics;
            g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

            Font font = new Font("Arial", Font.PLAIN, 12);
            g2d.setFont(font);
            g2d.drawString(content, 100, 100);

            return Printable.PAGE_EXISTS;
        };

        printerJob.setPrintable(printable);

        if (printerJob.printDialog()) {
            printerJob.print();
        }
    }
}