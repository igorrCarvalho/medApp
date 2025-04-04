package app.medapp.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.media.MediaScannerConnection
import android.os.Environment
import android.widget.Toast
import app.medapp.R
import app.medapp.data.models.Test
import java.io.File
import java.io.FileOutputStream

object PdfGenerator {

    fun generatePdf(context: Context, test: Test, answers: Map<Int, Int>, totalScore: Int): String? {
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

        // Load the logo from drawable and draw it as header instead of text.
        val logoBitmap: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.senior_care_logo)
        // Optionally, scale the bitmap if needed:
        val desiredLogoWidth = 50 // change this as needed
        val scaleFactor = desiredLogoWidth.toFloat() / logoBitmap.width
        val desiredLogoHeight = (logoBitmap.height * scaleFactor).toInt()
        val scaledLogo = Bitmap.createScaledBitmap(logoBitmap, desiredLogoWidth, desiredLogoHeight, true)
        // Draw the logo at x=40, y=yPos
        canvas.drawBitmap(scaledLogo, 40f, yPos, null)
        // Update yPos based on the height of the logo plus some margin
        yPos += desiredLogoHeight + 20f

        // Draw top purple line for the patient info block
        val blockTopY = yPos
        canvas.drawLine(40f, blockTopY, pageInfo.pageWidth - 40f, blockTopY, linePaint)

// Draw patient info text with increased padding
        val pacientLine = "Paciente: ${test.pacientName}"
        val dateLine = "Data: ${test.date}"
        val doctorLine = "Médico Responsável: ${test.doctorName}"
        val ageLine = "Idade do Paciente: ${test.pacientAge}"

// Increase vertical offsets to add more padding inside the borders.
        canvas.drawText("$pacientLine    $dateLine", 40f, blockTopY + 30f, textPaint)
        canvas.drawText("$doctorLine    $ageLine", 40f, blockTopY + 60f, textPaint)

// Draw bottom purple line for the patient info block with extra spacing
        val blockBottomY = blockTopY + 90f  // Increased from 50f to 90f for extra padding
        canvas.drawLine(40f, blockBottomY, pageInfo.pageWidth - 40f, blockBottomY, linePaint)

// Set new yPos for subsequent elements with extra gap after the bottom border
        yPos = blockBottomY + 40f  // Increased from 30f to 40f

        // Draw test title
        canvas.drawText(test.testName, 40f, yPos, textPaint)
        yPos += 40f

        if (test.id != 6) {
            canvas.drawText("Pontuação: $totalScore", 40f, yPos, textPaint)
            yPos += 50f
        }

        if (test.id != 6) {
            val refLabel = "Referência: "
            if (test.testLimits.reference.isNotEmpty()) {
                // Draw the first line: label + first reference text
                canvas.drawText(refLabel + test.testLimits.reference.first(), 40f, yPos, textPaint)
                yPos += 20f

                // Calculate the width of the label for indentation
                val indent = textPaint.measureText(refLabel)

                // Draw the remaining reference lines with the same indentation
                test.testLimits.reference.drop(1).forEach { refLine ->
                    canvas.drawText(refLine, 40f + indent, yPos, textPaint)
                    yPos += 20f
                }
            }
        }

        // Determine the final message using resultMappings:
        val finalMsg = test.testLimits.resultMappings.find { mapping ->
            totalScore in mapping.minScore..mapping.maxScore
        }?.message ?: "Sem mensagem definida"
        yPos += 50f
        canvas.drawText("Resultado: $finalMsg", 40f, yPos, textPaint)

        pdfDocument.finishPage(page)

        return try {
            // Save the PDF in the public Downloads directory
            val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val file = File(downloadsDir, "${test.testName}.pdf")
            pdfDocument.writeTo(FileOutputStream(file))
            // Trigger media scan so file appears in file managers
            MediaScannerConnection.scanFile(context, arrayOf(file.absolutePath), null, null)
            file.absolutePath
        } catch (e: Exception) {
            Toast.makeText(context, "Error saving PDF: ${e.message}", Toast.LENGTH_LONG).show()
            null
        } finally {
            pdfDocument.close()
        }
    }
}
