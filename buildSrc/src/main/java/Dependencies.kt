object Kotlin {
    private val core_ktx_version = "1.6.0"

    val stdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.5.31"
    val coreKtx = "androidx.core:core-ktx:$core_ktx_version"
}

object UI {
    private val material_version = "1.4.0"

    val materialComponents = "com.google.android.material:material:$material_version"
}

object AndroidX {
    private const val app_compat_version = "1.3.0"
    private const val constraint_layout_version = "2.1.1"
    private const val fragment_container_view_version = "1.3.6"
    private const val recycler_view_version = "1.2.1"
    private const val lifecycle_version = "2.4.0-rc01"
    private const val lifecycle_process_version = "2.1.0"

    val appCompat = "androidx.appcompat:appcompat:$app_compat_version"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:$constraint_layout_version"
    val fragmentContainer = "androidx.fragment:fragment-ktx:$fragment_container_view_version"
    val recyclerView = "androidx.recyclerview:recyclerview:$recycler_view_version"
    val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version"
    val processLifecycle = "androidx.lifecycle:lifecycle-process:$lifecycle_process_version"
}

object DI {
    private const val koin_version = "3.1.2"

    val coreKoin = "io.insert-koin:koin-core:$koin_version"
    val androidKoin = "io.insert-koin:koin-android:$koin_version"
}

object Log {
    private val timber_version = "4.7.1"

    val timber = "com.jakewharton.timber:timber:$timber_version"
}

object Image {
    private val coil_version = "1.4.0"

    val coil = "io.coil-kt:coil:$coil_version"
}

object Concurrency {
    private val coroutines_version = "1.5.2"

    val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version"
}

object Database {
    private val room_version = "2.4.0"

    val room = "androidx.room:room-runtime:$room_version"
    val roomCompiler = "androidx.room:room-compiler:$room_version"
    val roomFlow = "androidx.room:room-ktx:$room_version"
}

object WorkManager {
    private val work_manager_version = "2.7.0"

    val workManager = "androidx.work:work-runtime-ktx:$work_manager_version"
}
