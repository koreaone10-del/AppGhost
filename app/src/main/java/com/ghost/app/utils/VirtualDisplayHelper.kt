```kotlin
package com.ghost.app.utils

import android.content.Context
import android.hardware.display.DisplayManager
import android.hardware.display.VirtualDisplay
import android.view.Display
import android.view.Surface

object VirtualDisplayHelper {
    private var virtualDisplay: VirtualDisplay? = null

    fun createMinimalDisplay(context: Context) {
        if (virtualDisplay != null) return

        val dm = context.getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
        val dummySurface = createDummySurface()

        virtualDisplay = dm.createVirtualDisplay(
            "GhostDisplay",
            1, 1,               // الحجم: 1×1 بكسل
            Display.DENSITY_LOW,
            dummySurface,
            DisplayManager.VIRTUAL_DISPLAY_FLAG_PUBLIC or
                    DisplayManager.VIRTUAL_DISPLAY_FLAG_OWN_CONTENT_ONLY or
                    DisplayManager.VIRTUAL_DISPLAY_FLAG_PRESENTATION
        )
    }

    fun getDisplayId(): Int = virtualDisplay?.display?.displayId ?: -1

    fun destroyDisplay() {
        virtualDisplay?.release()
        virtualDisplay = null
    }

    private fun createDummySurface(): Surface {
        // استخدام SurfaceTexture بمعرف 0 لإنشاء سطح لا يعرض شيئاً
        val texture = android.graphics.SurfaceTexture(0)
        texture.setDefaultBufferSize(1, 1)
        return Surface(texture)
    }
}
```
