package com.example.falldetectionapp

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity(), SensorEventListener{
        private lateinit var sensorManager: SensorManager
        private var accSensor: Sensor? = null
        private var gyroSensor: Sensor?= null
        public override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
            accSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
            gyroSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        }

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
            // Do something here if sensor accuracy changes.
        }

        override fun onSensorChanged(event: SensorEvent) {
            // The light sensor returns a single value.
            // Many sensors return 3 values, one for each axis.
            // Do something with this sensor value
            val sensor : Sensor = event.sensor
            if(sensor.type==Sensor.TYPE_ACCELEROMETER){
                val accText1 : TextView = findViewById(R.id.acc_axis_value1)
                val accText2 : TextView = findViewById(R.id.acc_axis_value2)
                val accText3 : TextView = findViewById(R.id.acc_axis_value3)
                accText1.text = event.values[0].toString()
                accText2.text = event.values[1].toString()
                accText3.text = event.values[2].toString()
            }
            else if (sensor.type==Sensor.TYPE_GYROSCOPE){
                val gyroText1 : TextView = findViewById(R.id.gyro_axis_value1)
                val gyroText2 : TextView = findViewById(R.id.gyro_axis_value2)
                val gyroText3 : TextView = findViewById(R.id.gyro_axis_value3)
                gyroText1.text = event.values[0].toString()
                gyroText2.text = event.values[1].toString()
                gyroText3.text = event.values[2].toString()
            }
        }

        override fun onResume() {
            super.onResume()
            accSensor?.also { acc ->
                sensorManager.registerListener(this, acc, SensorManager.SENSOR_DELAY_FASTEST)
            }
            gyroSensor?.also { gyro ->
                sensorManager.registerListener(this, gyro, SensorManager.SENSOR_DELAY_FASTEST)
            }
        }

        override fun onPause() {
            super.onPause()
            sensorManager.unregisterListener(this)
        }
}