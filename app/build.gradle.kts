dependencies {
    // مكتبة VirtualApp (يجب بنائها من المصدر أو استخدام نسخة معدلة)
    implementation(files("libs/virtualapp.aar"))
    
    // أدوات الخطاف: استخدام Xposed-like أو انعكاس عادي
    // سنستخدم الانعكاس وتعديل الكائنات يدوياً (بدون روت)
    implementation("com.google.code.gson:gson:2.10.1") // لبعض السيريالايز
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
}
