package app.medapp.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.widget.Toast
import app.medapp.data.Test
import java.io.File
import java.io.FileOutputStream

object PdfGenerator {

    fun generatePdf(context: Context, test: Test, answers: Map<Int, Int>, totalScore: Int) {
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
        val page = pdfDocument.startPage(pageInfo)
        val canvas: Canvas = page.canvas

        // Text paint (black)
        val textPaint = Paint().apply {
            color = Color.BLACK
            textSize = 16f
        }

        // Purple line paint
        val linePaint = Paint().apply {
            color = Color.parseColor("#6200EE") // Purple color
            strokeWidth = 2f
            style = Paint.Style.STROKE
        }

        var yPos = 40f

        // 1) Draw "MedApp" left-aligned
        canvas.drawText("MedApp", 40f, yPos, textPaint)
        yPos += 30f

        // 2) Draw top line of the "border"
        val lineTopY = yPos
        val lineBottomY = lineTopY + 50f  // The space for two lines of text
        canvas.drawLine(40f, lineTopY, pageInfo.pageWidth - 40f, lineTopY, linePaint)

        // Patient data lines in between
        val pacientLine = "Paciente: ${test.pacientName}       Data: ${test.date}"
        val doctorLine = "Médico Responsável: ${test.doctorName}    Idade do Paciente: ${test.pacientAge}"
        // We'll position them about 20px and 40px below the top line
        canvas.drawText(pacientLine, 40f, lineTopY + 20f, textPaint)
        canvas.drawText(doctorLine, 40f, lineTopY + 40f, textPaint)

        // 3) Draw bottom line of the "border"
        canvas.drawLine(40f, lineBottomY, pageInfo.pageWidth - 40f, lineBottomY, linePaint)

        // Move yPos below that block
        yPos = lineBottomY + 30f

        // 4) Draw the test name
        canvas.drawText(test.testName, 40f, yPos, textPaint)
        yPos += 40f

        // 5) Draw result and reference
        val resultLine = "Resultado: $totalScore"
        val refLine = "referencia: ${test.testLimits}"

        canvas.drawText(resultLine, 40f, yPos, textPaint)
        yPos += 20f
        canvas.drawText(refLine, 40f, yPos, textPaint)

        pdfDocument.finishPage(page)

        // Save the PDF
        try {
            val file = File(context.getExternalFilesDir(null), "${test.testName}.pdf")
            pdfDocument.writeTo(FileOutputStream(file))
            Toast.makeText(context, "PDF saved: ${file.absolutePath}", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(context, "Error saving PDF: ${e.message}", Toast.LENGTH_LONG).show()
        } finally {
            pdfDocument.close()
        }
    }

}
