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
# Hilt (Dagger)
-keep class dagger.** { *; }
-dontwarn dagger.**

# Hilt Internal
-keep class javax.inject.** { *; }
-dontwarn javax.inject.**


# Keep generated Hilt components
-keep class * extends dagger.hilt.internal.GeneratedComponent { *; }

# Retrofit
-dontwarn okhttp3.**
-dontwarn retrofit2.**
-dontwarn okio.**
-keep class retrofit2.** { *; }

# Gson
-keep class com.google.gson.** { *; }
# Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}
# LeakCanary
-dontwarn leakcanary.**
-keep class leakcanary.** { *; }

# SQLCipher
-keep class net.sqlcipher.** { *; }
-dontwarn net.sqlcipher.**

# SQLite support internal
-keep class org.sqlite.database.** { *; }
-dontwarn org.sqlite.database.**

# AndroidX SQLite
-keep class androidx.sqlite.** { *; }
-dontwarn androidx.sqlite.**

# Gson - keep all model classes and their fields
-keep class com.example.mycapstonesubmission.core.data.source.remote.response.** {
    *;
}
-keepclassmembers class com.example.mycapstonesubmission.core.data.source.remote.response.** {
    *;
}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile