package app.medapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import app.medapp.MainActivity
import app.medapp.R
import java.io.File

class PdfPreviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_preview)

        // Retrieve views from the layout.
        val txtResult = findViewById<TextView>(R.id.txtResult)
        val txtReference = findViewById<TextView>(R.id.txtReference)
        val btnDownload = findViewById<Button>(R.id.btnDownload)
        val btnHome = findViewById<Button>(R.id.btnHome)

        // Retrieve extras from the intent.
        val pdfFilePath = intent.getStringExtra("pdfFilePath")
        val totalScore = intent.getIntExtra("totalScore", -1)
        val finalMsg = intent.getStringExtra("finalMsg") ?: ""

        // Update UI: show total score only if available.
        if (totalScore == -1) {
            txtResult.visibility = View.GONE
        } else {
            txtResult.text = "Pontuação: $totalScore"
        }
        txtReference.text = "Resultado: $finalMsg"

        // Download button: open PDF via FileProvider.
        btnDownload.setOnClickListener {
            if (pdfFilePath != null) {
                val pdfFile = File(pdfFilePath)
                val uri: Uri = FileProvider.getUriForFile(
                    this,
                    "app.medapp.fileprovider", // Make sure this matches your manifest.
                    pdfFile
                )
                val viewIntent = Intent(Intent.ACTION_VIEW).apply {
                    setDataAndType(uri, "application/pdf")
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                }
                try {
                    startActivity(viewIntent)
                } catch (e: Exception) {
                    Toast.makeText(this, "Nenhum visualizador de PDF instalado", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "PDF não encontrado", Toast.LENGTH_SHORT).show()
            }
        }

        // Home button: navigate back to the start of the app.
        btnHome.setOnClickListener {
            val homeIntent = Intent(this, MainActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            startActivity(homeIntent)
            finish()
        }
    }
}
