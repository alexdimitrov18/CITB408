package org.example.helpers;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import org.example.entities.ItemQuantity;
import org.example.entities.Receipt;


import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReceiptPrinter {

    private static int fileCounter = 0;

    private static final String FILE_PATH = "C:\\Users\\Alex\\Desktop\\shop-main\\shop-main\\src\\receipt";

    private static final String FILE_EXT = ".pdf";

    private static final String PRINTED_SUCCESSFULLY = "PDF created successfully at: " + FILE_PATH;

    /**
     * Prints a list of Orders to a PDF document.
     *
     * @param receipts The list of Order objects to be printed.
     */
    public static void printReceipts(List<Receipt> receipts) {
        for (Receipt receipt : receipts) {
            printReceipt(receipt);
        }
    }

    public static void printReceipt(Receipt receipt) {
        fileCounter++;
        Receipt receipt1 = new Receipt();
        try (PdfWriter writer = new PdfWriter(FILE_PATH + fileCounter + FILE_EXT)) {
            try (PdfDocument pdf = new PdfDocument(writer)) {
                try (Document document = new Document(pdf)) {
                    // Set up font
                    PdfFont boldFont = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
                    DeviceRgb lightBlue = new DeviceRgb(173, 216, 230);
                    // Shop name
                    Paragraph title = new Paragraph(receipt.getShop().getName())
                            .setFont(boldFont)
                            .setFontSize(24)
                            .setBold()
                            .setTextAlignment(TextAlignment.CENTER)
                            .setBackgroundColor(lightBlue); // Background color
                    document.add(title);

                    // Receipt ID and other details
                    document.add(new Paragraph("Receipt #" + receipt.getId())
                            .setFontSize(12)
                            .setTextAlignment(TextAlignment.CENTER));

                    document.add(new Paragraph("Cashier: " + receipt.getCashier().getName())
                            .setTextAlignment(TextAlignment.CENTER));

                    // Add a table for items
                    Table table = new Table(UnitValue.createPercentArray(new float[]{3, 1, 2}));
                    table.addHeaderCell(new Cell().add(new Paragraph("Item")).setBackgroundColor(ColorConstants.GRAY).setBold().setTextAlignment(TextAlignment.CENTER));
                    table.addHeaderCell(new Cell().add(new Paragraph("Price")).setBackgroundColor(ColorConstants.GRAY).setBold().setTextAlignment(TextAlignment.CENTER));
                    table.addHeaderCell(new Cell().add(new Paragraph("Quantity")).setBackgroundColor(ColorConstants.GRAY).setBold().setTextAlignment(TextAlignment.CENTER));

                    for (ItemQuantity itemQuantity : receipt.getItemQuantities()) {
                        table.addCell(new Paragraph(itemQuantity.getItem().getName()));
                        table.addCell(new Paragraph(String.format("%.2f lv", receipt1.getItemPrice(itemQuantity,receipt))));
                        table.addCell(new Paragraph(String.valueOf(itemQuantity.getQuantity())).setTextAlignment(TextAlignment.CENTER));
                    }

                    // Add total cost and purchase date
                    document.add(table);
                    document.add(new Paragraph("Total Cost: " + String.format("%.2f lv", receipt1.getTotalCost(receipt)))
                            .setFontSize(14)
                            .setBold()
                            .setTextAlignment(TextAlignment.RIGHT)
                            .setMarginTop(10));

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    document.add(new Paragraph("Purchase Date: " + receipt.getPurchaseDate().format(formatter))
                            .setTextAlignment(TextAlignment.RIGHT));
                }
            }
            System.out.println(PRINTED_SUCCESSFULLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
