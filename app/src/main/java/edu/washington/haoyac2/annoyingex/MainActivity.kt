package edu.washington.haoyac2.annoyingex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val annoyingExApp = (applicationContext as AnnoyingExApp)

        val annoyingExApp = (application as AnnoyingExApp)
        val workManager = annoyingExApp.hereWeGoManager

        btnHereWeGo.setOnClickListener{
//            annoyingExApp.hereWeGoManager.startAnnoyingTheHeckOuttaPerson()
            workManager.startAnnoyingTheHeckOuttaPerson()
        }

        btnBlock.setOnClickListener{
//            annoyingExApp.hereWeGoManager.stopWork()
            workManager.stopWork()
        }


    }
}