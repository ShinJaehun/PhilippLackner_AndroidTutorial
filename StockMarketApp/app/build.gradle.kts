plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.android)
//    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.shinjaehun.stockmarketapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.shinjaehun.stockmarketapp"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //Dagger - Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // hiltViewModel()
    implementation(libs.hilt.navigation.compose)

    //retrofit2
    implementation(libs.retrofit2)
    implementation(libs.converter.moshi)
    implementation(libs.okhttp3)
    implementation(libs.logging.interceptor)

    //room
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)
    implementation(libs.room.ktx)

    //coil 필요없다고 합니다;;;
    implementation(libs.coil.compose)

    //compose-destination
    implementation(libs.compose.destination.core)
    ksp(libs.compose.destination.ksp)

    //opencsv
    implementation(libs.opencsv)

    // Accompanist SwipeRefresh Library
    implementation(libs.accompanist.swiperefresh)

    implementation("androidx.compose.material3:material3:1.4.0-alpha02")

}