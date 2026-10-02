plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.sgm"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.sgm"
        minSdk = 24
        targetSdk = 36
        versionCode = 2
        versionName = "2.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

configurations.configureEach {
    exclude(group = "org.jetbrains.kotlin", module = "kotlin-stdlib-jdk7")
    exclude(group = "org.jetbrains.kotlin", module = "kotlin-stdlib-jdk8")
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.7.1")
    implementation("androidx.fragment:fragment:1.8.9")
}
