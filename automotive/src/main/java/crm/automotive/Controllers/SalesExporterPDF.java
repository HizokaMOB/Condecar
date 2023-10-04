package crm.automotive.Controllers;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import crm.automotive.Models.Entity.Sales;
import jakarta.servlet.http.HttpServletResponse;

public class SalesExporterPDF {

    private List<Sales> listsales;

    public SalesExporterPDF(List<Sales> listsales) {
        this.listsales = listsales;
    }

    private void HeaderTable(PdfPTable table) {
        PdfPCell cell = new PdfPCell();

        cell.setBackgroundColor(Color.blue);
        cell.setPadding(5);

        Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
        fuente.setColor(Color.white);

        cell.setPhrase(new Phrase("ID", fuente));
        table.addCell(cell);

        cell.setPhrase(new Phrase("CUSTOMER'S NAME", fuente));
        table.addCell(cell);

        cell.setPhrase(new Phrase("SALES DESCRIPTION", fuente));
        table.addCell(cell);

        cell.setPhrase(new Phrase("TYPE OF SALE", fuente));
        table.addCell(cell);

        cell.setPhrase(new Phrase("DATE OF SALE", fuente));
        table.addCell(cell);

        cell.setPhrase(new Phrase("PRICE", fuente));
        table.addCell(cell);

    }

    private void TableInfo(PdfPTable table) {
        for (Sales sales : listsales) {
            table.addCell(String.valueOf(sales.getId()));
            table.addCell(sales.getCustomername());
            table.addCell(sales.getDescription());
            table.addCell(sales.getType());
            table.addCell(sales.getDate());
            table.addCell(sales.getPrice());
        }

    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fuente.setColor(Color.BLUE);
        fuente.setSize(18);

        Paragraph title = new Paragraph("Sales register report", fuente);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setSpacingBefore(15);

        table.setWidths(new float[] { 1f, 2.9f, 6f, 2.3f, 2.9f, 3.5f});
        table.setWidthPercentage(110);

        HeaderTable(table);
        TableInfo(table);

        document.add(table);
        document.close();

    }

}
