package com.df.student.management.util;

import com.df.student.management.model.Course;
import com.df.student.management.model.Registration;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class PdfUtil {
    private Logger logger = LoggerFactory.getLogger(PdfUtil.class.getName());
    // Method to generate a PDF of the course schedule
    public  byte[] generatePdf(List<Registration> registeredCourses) {
        logger.info("entered method registerForCourse,{}",registeredCourses);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            // Initialize PDF writer and document
            PdfWriter writer = new PdfWriter(byteArrayOutputStream);
            com.itextpdf.kernel.pdf.PdfDocument pdfDocument = new com.itextpdf.kernel.pdf.PdfDocument(writer);
            Document document = new Document(pdfDocument);

            // Add title to PDF
            document.add(new Paragraph("Course Schedule").setBold().setFontSize(18));

            // Create a table to list courses
            float[] columnWidths = {100, 200, 120,150,80};
            Table table = new Table(columnWidths);
            table.addCell("Course ID");
            table.addCell("Course Name");
            table.addCell("Instructor");
            table.addCell("Description");
            table.addCell("Cancelled");

            // Populate table with course details
            for (Registration registration : registeredCourses) {
                Course course=registration.getCourse();
                table.addCell(String.valueOf(course.getId()));
                table.addCell(course.getName());
                table.addCell(course.getInstructorName());
                table.addCell(course.getDescription());
                table.addCell(String.valueOf(registration.getCanceled()));
            }

            // Add table to the document
            document.add(table);

            // Close document
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while generating PDF");
        }

        // Return the generated PDF as a byte array
        return byteArrayOutputStream.toByteArray();
    }
}
