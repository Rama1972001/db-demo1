package com.example.demo1;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;

public class CreatePDF {
    private static Font ROMAN_HEADING = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private static Font ROMAN = new Font(Font.FontFamily.TIMES_ROMAN, 14);
    public static void writeToPDF( PatientClass patient) {
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("PatientInfo.pdf"));
            document.open();
            Paragraph header = new Paragraph("Hospital Management System", ROMAN_HEADING);
            header.setAlignment(Element.ALIGN_CENTER);
            document.add(header);
            helper(document, "Medical Report", ROMAN_HEADING);
            helper(document, "Patient ID:    " + patient.getId(), ROMAN);
            helper(document, "Name:           "+ patient.getPname(), ROMAN);
            helper(document, "Age:              "+ patient.getAge(), ROMAN);
            helper(document, "Phone No:     "+ patient.getPhone(), ROMAN);
            helper(document, "Address:        "+ patient.getAddress(), ROMAN);
            helper(document, "Blood Type", ROMAN_HEADING);
            helper(document, patient.getBloodType(), ROMAN);
            helper(document, "Allergy", ROMAN_HEADING);
            helper(document, patient.getAllergy(), ROMAN);
            document.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }
    private static void helper(Document document, String text, Font font) {
        try {
            Paragraph paragraph = new Paragraph(text, font);
            document.add(paragraph);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}

