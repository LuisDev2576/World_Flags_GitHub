# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# ProGuard rules for AndroidX libraries
-keep class androidx.** { *; }
-dontwarn androidx.**

# ProGuard rules for Kotlin
-keep class kotlin.** { *; }
-dontwarn kotlin.**

# ProGuard rules for Lifecycle (ViewModel, LiveData, etc.)
-keep class androidx.lifecycle.** { *; }
-dontwarn androidx.lifecycle.**

# ProGuard rules for Compose
-keep class androidx.compose.** { *; }
-dontwarn androidx.compose.**

# ProGuard rules for Material3
-keep class com.google.android.material.** { *; }
-dontwarn com.google.android.material.**

# ProGuard rules for Room
-keep class androidx.room.** { *; }
-dontwarn androidx.room.**
-keep interface androidx.room.RoomDatabase$Callback {
    void onCreate(androidx.sqlite.db.SupportSQLiteDatabase);
    void onOpen(androidx.sqlite.db.SupportSQLiteDatabase);
}
-keepclassmembers class ** extends androidx.room.RoomDatabase {
    static final androidx.room.RoomDatabase$Callback *;
}

# ProGuard rules for Hilt
-keep class dagger.hilt.** { *; }
-dontwarn dagger.hilt.**
-keep class javax.inject.** { *; }
-dontwarn javax.inject.**
-keep class dagger.** { *; }
-dontwarn dagger.**
-keepclassmembers class ** {
    @dagger.Provides <methods>;
    @dagger.Binds <methods>;
}
-keepattributes *Annotation*

# ProGuard rules for Retrofit
-keep class retrofit2.** { *; }
-dontwarn retrofit2.**
-keep class okhttp3.** { *; }
-dontwarn okhttp3.**
-keep class com.google.gson.** { *; }
-dontwarn com.google.gson.**
-keep class com.squareup.okhttp3.** { *; }
-dontwarn com.squareup.okhttp3.**

# ProGuard rules for Coil
-keep class coil.** { *; }
-dontwarn coil.**

# ProGuard rules for kotlinx.serialization
-keep class kotlinx.serialization.** { *; }
-dontwarn kotlinx.serialization.**

# ProGuard rules for multidex
-keep class androidx.multidex.** { *; }
-dontwarn androidx.multidex.**

-keep class com.proyect.worldflags.data.remote.response.*
-keep class com.proyect.worldflags.data.mappers.*
