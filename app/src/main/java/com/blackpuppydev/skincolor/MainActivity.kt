package com.blackpuppydev.skincolor


import android.content.res.Resources
import android.graphics.*
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.blackpuppydev.skincolor.analyzer.FaceDetect


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.sutest)


        val faceDetect = FaceDetect(baseContext)
        faceDetect.detection(bitmap)

//        val res: Resources = resources
//        val bitmap = BitmapFactory.decodeResource(res, R.drawable.sutest)
//
//        val workingBitmap: Bitmap = Bitmap.createBitmap(1000,1000,Bitmap.Config.ARGB_8888)
////        val mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888, true)
//
////
//        val canvas = Canvas(workingBitmap)
//        canvas.drawBitmap(workingBitmap, null, Rect(10,30,50,50),paint)




    }
}