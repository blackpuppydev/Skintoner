package com.blackpuppydev.skincolor.analyzer

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.Log
import android.view.View
import androidx.core.graphics.toRectF
import androidx.palette.graphics.Palette
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.*

class FaceDetect(context: Context?) {

    var left:Float = 0.0f
    var right:Float = 0.0f
    var top:Float = 0.0f
    var bottom:Float = 0.0f
    lateinit var bounds:Rect

    fun detection(bitmap:Bitmap) :String{






        // High-accuracy landmark detection and face classification
        val highAccuracyOpts = FaceDetectorOptions.Builder()
            .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE)
            .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
            .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
            .build()


        // Real-time contour detection
        val realTimeOpts = FaceDetectorOptions.Builder()
            .setContourMode(FaceDetectorOptions.CONTOUR_MODE_ALL)
            .build()

        val image = InputImage.fromBitmap(bitmap, 0)
        val detector = FaceDetection.getClient()

        Log.d("faceDetect", " bitmap ${bitmap.colorSpace}")
        val result = detector.process(image)
            .addOnSuccessListener { faces ->

                Log.d("faceDetect size ", faces.size.toString())

                for (face in faces) {
                    bounds = face.boundingBox

                    val paint = Paint()
                    paint.apply {
                        color = Color.RED
                        strokeWidth = 200F
                        alpha = 255
                        isAntiAlias = true
                        style = Paint.Style.FILL_AND_STROKE
                    }
                    Log.d("faceDetect paint ", paint.toString())

                    val canvas = Canvas()
                    canvas.drawRect(bounds,paint)
//                    left = bounds.left.toFloat()
//                    right = bounds.right.toFloat()
//                    top = bounds.top.toFloat()
//                    bottom = bounds.bottom.toFloat()


                    Log.d("faceDetect face : ",face.toString())
                    Log.d("faceDetect bounds : ",bounds.toString())

//                    val rotY = face.headEulerAngleY // Head is rotated to the right rotY degrees
//                    val rotZ = face.headEulerAngleZ // Head is tilted sideways rotZ degrees


                    Palette.from(bitmap).generate { it ->
//                        for (i in it.do){

                        it?.dominantSwatch.let {
                            Log.d("faceDetect dominantSwatch ", " : " + it?.rgb?.hexString())
                        }

                        it?.vibrantSwatch.let {
                            Log.d("faceDetect vibrantSwatch ", " : " + it?.rgb?.hexString())
                        }

                        it?.lightVibrantSwatch.let {
                            Log.d("faceDetect lightVibrantSwatch", " : " + it?.rgb?.hexString())
                        }

                        it?.darkVibrantSwatch.let {
                            Log.d("faceDetect darkVibrantSwatch", " : " + it?.rgb?.hexString())
                        }

                        it?.mutedSwatch.let {
                            Log.d("faceDetect mutedSwatch", " : " + it?.rgb?.hexString())
                        }

                        it?.lightMutedSwatch.let {
                            Log.d("faceDetect lightMutedSwatch", " : " + it?.rgb?.hexString())
                        }

                        it?.darkMutedSwatch.let {
                            Log.d("faceDetect darkMutedSwatch", " : " + it?.rgb?.hexString())
                        }

//                        }

                    }

//                    Log.d("FaceDetect", " face ${bounds.centerX()}")
//                    val allContours = face.getContour(0)
////                    allContours.let {
////                        Log.d("FaceDetect"," all contour " + it?.points)
////                    }
//                    Log.d("FaceDetect"," : $allContours")

//                    // If landmark detection was enabled (mouth, ears, eyes, cheeks, and
//                    // nose available):
//                    val leftEar = face.getLandmark(FaceLandmark.LEFT_EAR)
//                    leftEar?.let {
//                        val leftEarPos = leftEar.position
//                    }
//
//                    // If contour detection was enabled:
//                    val leftEyeContour = face.getContour(FaceContour.LEFT_EYE)?.points
//                    val upperLipBottomContour = face.getContour(FaceContour.UPPER_LIP_BOTTOM)?.points
//
////                    // If classification was enabled:
////                    if (face.smilingProbability != null) {
////                        val smileProb = face.smilingProbability
////                    }
////                    if (face.rightEyeOpenProbability != null) {
////                        val rightEyeOpenProb = face.rightEyeOpenProbability
////                    }
//
//                    // If face tracking was enabled:
//                    if (face.trackingId != null) {
//                        val id = face.trackingId
//                    }

//                    face.get
                }

            }
            .addOnFailureListener {
                // Task failed with an exception
                // ...
            }

//        Log.d("FaceDetect"," : " + result.result.toString())

        return ""
    }


    private fun Int.hexString():String{
        return String.format("#%06X", (0xFFFFFF and  this))
    }



}