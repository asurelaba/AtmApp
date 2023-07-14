package com.solvd.util;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;

public class Printer {

    public static void print(String content) throws PrinterException {
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        Printable printable = (graphics, pageFormat, pageIndex) -> {
            if (pageIndex > 0) {
                return Printable.NO_SUCH_PAGE;
            }

            Graphics2D g2d = (Graphics2D) graphics;
            g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

            String[] lines = content.split("\n");
            int y = 100;
            Font font = new Font("Arial", Font.PLAIN, 12);
            g2d.setFont(font);
            for (String line : lines) {
                g2d.drawString(line, 100, y);
                y += 15; // Adjust the line spacing as needed
            }

            return Printable.PAGE_EXISTS;
        };

        printerJob.setPrintable(printable);

        PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();
        if (defaultPrintService != null) {
            printerJob.setPrintService(defaultPrintService);
            printerJob.print();
        } else {
            throw new PrinterException("No default printer available.");
        }
    }

}
