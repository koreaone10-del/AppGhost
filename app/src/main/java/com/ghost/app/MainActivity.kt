```kotlin
package com.ghost.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // واجهة بسيطة لتشغيل الخدمة (يمكن جعلها تلقائية)
        val intent = Intent(this, GhostService::class.java).apply {
            putExtra("TARGET_PACKAGE", "com.example.targetapp") // استبدله بحزمة تطبيقك
        }
        startForegroundService(intent)
        finish()
    }
}
```
