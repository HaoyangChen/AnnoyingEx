package edu.washington.haoyac2.annoyingex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val annoyingExApp = (application as AnnoyingExApp)
        val workManager = annoyingExApp.hereWeGoManager

        exPrevMsgContent.text = intent.getStringExtra(AnnoyingNotificationManager.ANNOYING_EX_MSG)

        btnHereWeGo.setOnClickListener{
            workManager.startAnnoyingTheHeckOuttaPerson()
            workManager.startAnnoyingTheHeckOuttaPerson2Days()

        }

        btnBlock.setOnClickListener{
            workManager.stopWork()
        }


    }
}
