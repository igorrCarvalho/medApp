package app.medapp.utils

import android.content.Context
import android.media.MediaScannerConnection
import android.os.Environment
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.widget.Toast
import app.medapp.data.models.Test
import java.io.File
import java.io.FileOutputStream

object PdfGenerator {

    fun generatePdf(context: Context, test: Test, answers: Map<Int, Int>, totalScore: Int) {
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
        val page = pdfDocument.startPage(pageInfo)
        val canvas: Canvas = page.canvas

        // Set up Paint objects
        val textPaint = Paint().apply {
            color = Color.BLACK
            textSize = 16f
        }
        val linePaint = Paint().apply {
            color = Color.parseColor("#6200EE") // Purple color
            strokeWidth = 2f
            style = Paint.Style.STROKE
        }

        var yPos = 40f

        // Draw "MedApp" left-aligned
        canvas.drawText("MedApp", 40f, yPos, textPaint)
        yPos += 30f

        // Draw top purple line (for patient info block)
        val blockTopY = yPos
        canvas.drawLine(40f, blockTopY, pageInfo.pageWidth - 40f, blockTopY, linePaint)

        // Draw patient info text
        val pacientLine = "Paciente: ${test.pacientName}"
        val dateLine = "Data: ${test.date}"
        val doctorLine = "Médico Responsável: ${test.doctorName}"
        val ageLine = "Idade do Paciente: ${test.pacientAge}"
        canvas.drawText("$pacientLine    $dateLine", 40f, blockTopY + 20f, textPaint)
        canvas.drawText("$doctorLine    $ageLine", 40f, blockTopY + 40f, textPaint)

        // Draw bottom purple line (for patient info block)
        val blockBottomY = blockTopY + 50f
        canvas.drawLine(40f, blockBottomY, pageInfo.pageWidth - 40f, blockBottomY, linePaint)
        yPos = blockBottomY + 30f

        // Draw test title
        canvas.drawText(test.testName, 40f, yPos, textPaint)
        yPos += 40f

        // Draw result and reference lines
        canvas.drawText("Resultado: $totalScore", 40f, yPos, textPaint)
        yPos += 20f
        canvas.drawText("referencia: ${test.testLimits.reference}", 40f, yPos, textPaint)

        pdfDocument.finishPage(page)

        try {
            // Save the file in the public Downloads directory
            val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val file = File(downloadsDir, "${test.testName}.pdf")
            pdfDocument.writeTo(FileOutputStream(file))
            // Optionally, scan the file so it appears in the Downloads folder
            MediaScannerConnection.scanFile(context, arrayOf(file.absolutePath), null, null)
            Toast.makeText(context, "PDF saved: ${file.absolutePath}", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(context, "Error saving PDF: ${e.message}", Toast.LENGTH_LONG).show()
        } finally {
            pdfDocument.close()
        }
    }
}
