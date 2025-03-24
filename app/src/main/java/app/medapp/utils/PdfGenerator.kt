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

        val textPaint = Paint().apply {
            color = Color.BLACK
            textSize = 16f
        }

        val linePaint = Paint().apply {
            color = Color.parseColor("#6200EE")
            strokeWidth = 2f
            style = Paint.Style.STROKE
        }

        var yPos = 40f

        canvas.drawText("MedApp", 40f, yPos, textPaint)
        yPos += 30f

        val lineTopY = yPos
        val lineBottomY = lineTopY + 50f
        canvas.drawLine(40f, lineTopY, pageInfo.pageWidth - 40f, lineTopY, linePaint)

        val pacientLine = "Paciente: ${test.pacientName}       Data: ${test.date}"
        val doctorLine = "Médico Responsável: ${test.doctorName}    Idade do Paciente: ${test.pacientAge}"

        canvas.drawText(pacientLine, 40f, lineTopY + 20f, textPaint)
        canvas.drawText(doctorLine, 40f, lineTopY + 40f, textPaint)

        canvas.drawLine(40f, lineBottomY, pageInfo.pageWidth - 40f, lineBottomY, linePaint)

        yPos = lineBottomY + 30f

        canvas.drawText(test.testName, 40f, yPos, textPaint)
        yPos += 40f

        val resultLine = "Resultado: $totalScore"
        val refLine = "referencia: ${test.testLimits}"

        canvas.drawText(resultLine, 40f, yPos, textPaint)
        yPos += 20f
        canvas.drawText(refLine, 40f, yPos, textPaint)

        pdfDocument.finishPage(page)

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
