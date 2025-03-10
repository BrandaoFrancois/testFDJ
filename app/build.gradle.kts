plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    kotlin("plugin.serialization") version "1.9.22"
}

android {
    namespace = "com.test.fdj"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.test.fdj"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            buildConfigField("String", "SPORTSDB_BASE_URL", "\"https://www.thesportsdb.com/api/v1/json/\"")
            buildConfigField("String", "SPORTSDB_API_KEY", "\"3\"")

            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            buildConfigField("String", "SPORTSDB_BASE_URL", "\"https://www.thesportsdb.com/api/v1/json/\"")
            buildConfigField("String", "SPORTSDB_API_KEY", "\"3\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.13"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    val versionRetrofit = "2.9.0"
    val versionOkHTTP = "4.12.0"
    val versionHilt = "2.48"
    val versionCoil = "2.6.0"
    val versionCoroutine = "1.7.3"

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:$versionRetrofit")
    // Retrofit with Kotlin serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")

    // Add Logs to Retrofit
    implementation(platform("com.squareup.okhttp3:okhttp-bom:$versionOkHTTP"))
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")

    // Hilt
    implementation("com.google.dagger:hilt-android:$versionHilt")
    kapt("com.google.dagger:hilt-android-compiler:$versionHilt")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    // Coil
    implementation("io.coil-kt:coil:$versionCoil")
    implementation("io.coil-kt:coil-compose:$versionCoil")

    // Coroutine Test
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$versionCoroutine")
}

kapt {
    correctErrorTypes = true
}

hilt {
    enableAggregatingTask = true
}