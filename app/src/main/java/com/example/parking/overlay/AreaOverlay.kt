package com.example.parking.overlay

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Overlay

class AreaOverlay(val coordinates: List<GeoPoint>) : Overlay() {
    override fun draw(canvas: Canvas?, mapView: MapView?, shadow: Boolean) {
        super.draw(canvas, mapView, shadow)

        if (canvas == null || mapView == null) return

        val projection = mapView.projection

        val fillPaint = Paint().apply {
            color = Color.argb(102, 66, 97, 244) // 40% alpha of #4261F4
            style = Paint.Style.FILL
            isAntiAlias = true
        }

        val fillPath = Path()
        val startPoint = projection.toPixels(coordinates[0], null)
        fillPath.moveTo(startPoint.x.toFloat(), startPoint.y.toFloat())

        for (i in 1 until coordinates.size) {
            val point = projection.toPixels(coordinates[i], null)
            fillPath.lineTo(point.x.toFloat(), point.y.toFloat())
        }

        fillPath.lineTo(startPoint.x.toFloat(), startPoint.y.toFloat())
        fillPath.close()

        canvas.drawPath(fillPath, fillPaint)

        val strokePaint = Paint().apply {
            color = Color.BLUE // Stroke color
            style = Paint.Style.STROKE
            strokeWidth = 5f
            isAntiAlias = true
        }

        val path = Path()
        val startStrokePoint = projection.toPixels(coordinates[0], null)
        path.moveTo(startStrokePoint.x.toFloat(), startStrokePoint.y.toFloat())

        for (i in 1 until coordinates.size) {
            val point = projection.toPixels(coordinates[i], null)
            path.lineTo(point.x.toFloat(), point.y.toFloat())
        }

        path.lineTo(startStrokePoint.x.toFloat(), startStrokePoint.y.toFloat())
        path.close()

        canvas.drawPath(path, strokePaint)
    }
}