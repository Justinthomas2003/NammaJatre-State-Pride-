plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    id("com.google.gms.google-services")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    namespace = "com.example.nammajatrestatepride"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.nammajatrestatepride"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner =
            "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false

            proguardFiles(
                getDefaultProguardFile(
                    "proguard-android-optimize.txt"
                ),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
}

dependencies {

    // ---------------- CORE ANDROID ----------------

    implementation("androidx.core:core-ktx:1.13.1")

    implementation(
        "androidx.lifecycle:lifecycle-runtime-ktx:2.8.3"
    )

    implementation(
        "androidx.activity:activity-compose:1.9.0"
    )

    // ---------------- COMPOSE BOM ----------------

    implementation(
        platform("androidx.compose:compose-bom:2024.06.00")
    )

    // ---------------- COMPOSE UI ----------------

    implementation("androidx.compose.ui:ui")

    implementation("androidx.compose.ui:ui-graphics")

    implementation(
        "androidx.compose.ui:ui-tooling-preview"
    )

    implementation("androidx.compose.material3:material3")

    // ---------------- MATERIAL ----------------

    implementation(
        "androidx.compose.material:material:1.6.8"
    )

    implementation(
        "androidx.compose.material:material-icons-extended:1.6.8"
    )

    // ---------------- NAVIGATION ----------------

    implementation(
        "androidx.navigation:navigation-compose:2.7.7"
    )

    // ---------------- VIEWMODEL ----------------

    implementation(
        "androidx.lifecycle:lifecycle-viewmodel-compose:2.8.3"
    )

    // ---------------- FIREBASE ----------------

    implementation(
        platform("com.google.firebase:firebase-bom:33.1.2")
    )

    implementation(
        "com.google.firebase:firebase-auth-ktx"
    )

    implementation(
        "com.google.firebase:firebase-firestore-ktx"
    )

    implementation(
        "com.google.firebase:firebase-storage-ktx"
    )

    // ---------------- HILT ----------------

    implementation(
        "com.google.dagger:hilt-android:2.51.1"
    )

    kapt(
        "com.google.dagger:hilt-compiler:2.51.1"
    )

    implementation(
        "androidx.hilt:hilt-navigation-compose:1.2.0"
    )

    // ---------------- COROUTINES ----------------

    implementation(
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1"
    )

    implementation(
        "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.8.1"
    )

    // ---------------- COIL IMAGE LOADING ----------------

    implementation(
        "io.coil-kt:coil-compose:2.4.0"
    )

    // ---------------- GOOGLE MAPS ----------------

    implementation(
        "com.google.maps.android:maps-compose:4.3.3"
    )

    implementation(
        "com.google.android.gms:play-services-maps:18.2.0"
    )

    // ---------------- QR CODE GENERATION ----------------

    implementation(
        "com.google.zxing:core:3.5.2"
    )

    // ---------------- DATE & TIME PICKER ----------------
    implementation("io.github.cdimascio:dotenv-kotlin:6.4.1")

    // ---------------- IMAGE CROPPER ----------------

    implementation(
        "com.github.CanHub:Android-Image-Cropper:4.5.0"
    )

    // ---------------- DEBUG ----------------

    debugImplementation(
        "androidx.compose.ui:ui-tooling"
    )

    debugImplementation(
        "androidx.compose.ui:ui-test-manifest"
    )

    // ---------------- UNIT TESTING ----------------

    testImplementation(
        "junit:junit:4.13.2"
    )

    // ---------------- ANDROID TESTING ----------------

    androidTestImplementation(
        "androidx.test.ext:junit:1.2.1"
    )

    androidTestImplementation(
        "androidx.test.espresso:espresso-core:3.6.1"
    )

    androidTestImplementation(
        platform("androidx.compose:compose-bom:2024.06.00")
    )

    androidTestImplementation(
        "androidx.compose.ui:ui-test-junit4"
    )
}

kapt {
    correctErrorTypes = true
}