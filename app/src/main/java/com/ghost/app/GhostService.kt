```kotlin
package com.ghost.app

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.os.PowerManager
import androidx.core.app.NotificationCompat

class GhostService : Service() {

    private lateinit var engine: GhostEngine
    private var wakeLock: PowerManager.WakeLock? = null

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        val notification = buildNotification()
        startForeground(1, notification)

        val pm = getSystemService(Context.POWER_SERVICE) as PowerManager
        wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "Ghost::WakeLock")
        wakeLock?.acquire()

        engine = GhostEngine(this)
        engine.initialize()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val packageName = intent?.getStringExtra("TARGET_PACKAGE")
        if (packageName != null) {
            engine.launchApp(packageName)
        }
        return START_STICKY
    }

    override fun onDestroy() {
        engine.shutdown()
        wakeLock?.release()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun createNotificationChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "ghost_engine",
                "المحرك الخفي",
                NotificationManager.IMPORTANCE_LOW
            )
            getSystemService(NotificationManager::class.java)?.createNotificationChannel(channel)
        }
    }

    private fun buildNotification(): Notification {
        return NotificationCompat.Builder(this, "ghost_engine")
            .setContentTitle("AppGhost نشط")
            .setContentText("التطبيق يعمل في الخلفية بتوفير طاقة...")
            .setSmallIcon(android.R.drawable.ic_menu_camera)
            .build()
    }
}
```
