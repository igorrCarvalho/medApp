package app.medapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.barteksc.pdfviewer.PDFView
import app.medapp.R
import java.io.File
import androidx.core.content.FileProvider

class PdfPreviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_preview)

        val pdfView = findViewById<PDFView>(R.id.pdfView)
        val btnDownload = findViewById<Button>(R.id.btnDownload)
        val btnHome = findViewById<Button>(R.id.btnHome)

        // Retrieve the file path from intent
        val pdfFilePath: String? = intent.getStringExtra("pdfFilePath")
        if (pdfFilePath != null) {
            val pdfFile = File(pdfFilePath)
            pdfView.fromFile(pdfFile)
                .enableSwipe(true)
                .load()
        } else {
            Toast.makeText(this, "PDF file not found", Toast.LENGTH_SHORT).show()
        }

        btnDownload.setOnClickListener {
            pdfFilePath?.let {
                val pdfFile = File(it)
                // Use FileProvider to get a content:// URI for the file.
                val uri = FileProvider.getUriForFile(
                    this,
                    "app.medapp.fileprovider", // make sure this matches your manifest declaration
                    pdfFile
                )
                val viewIntent = Intent(Intent.ACTION_VIEW).apply {
                    setDataAndType(uri, "application/pdf")
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                }
                try {
                    startActivity(viewIntent)
                } catch (e: Exception) {
                    Toast.makeText(this, "No PDF viewer installed", Toast.LENGTH_SHORT).show()
                }
            } ?: run {
                Toast.makeText(this, "PDF file not found", Toast.LENGTH_SHORT).show()
            }
        }

        btnHome.setOnClickListener {
            // Return home by finishing this activity
            finish()
        }
    }
}
