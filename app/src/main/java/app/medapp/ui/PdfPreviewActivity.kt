package app.medapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import app.medapp.R
import java.io.File
import app.medapp.MainActivity

class PdfPreviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_preview)

        val txtResult = findViewById<TextView>(R.id.txtResult)
        val txtReference = findViewById<TextView>(R.id.txtReference)
        val btnDownload = findViewById<Button>(R.id.btnDownload)
        val btnHome = findViewById<Button>(R.id.btnHome)

        // Retrieve extras
        val pdfFilePath = intent.getStringExtra("pdfFilePath")
        val totalScore = intent.getIntExtra("totalScore", 0)
        val referenceText = intent.getStringExtra("referenceText") ?: ""

        // Set text for result and reference
        txtResult.text = "Resultado: $totalScore"
        txtReference.text = "Referência: $referenceText"

        btnDownload.setOnClickListener {
            pdfFilePath?.let {
                val pdfFile = File(it)
                val uri = FileProvider.getUriForFile(
                    this,
                    "app.medapp.fileprovider", // Must match the authority in your manifest
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
            } ?: Toast.makeText(this, "PDF file not found", Toast.LENGTH_SHORT).show()
        }

        btnHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
                // These flags clear all the existing activities on top of MainActivity.
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            startActivity(intent)
            finish()
        }
    }
}
