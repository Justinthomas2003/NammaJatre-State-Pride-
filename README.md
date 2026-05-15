# Namma Jatre State Pride

An Android app for Karnataka Jatre/Festival built with Kotlin, Jetpack Compose, Hilt, Firebase, Google Maps and Material 3.

## Table of Contents
- About
- Prerequisites
- Setup
  - Firebase
  - Google Maps API
  - Local environment (.env)
- Build & Run
- Git & GitHub
- Security notes

## About
Namma Jatre State Pride showcases festival events, ticket booking, maps, gallery and support features. This repository contains the Android app codebase.

## Prerequisites
- JDK 17
- Android Studio (Arctic Fox or later recommended)
- Android SDK & emulator/device
- Gradle (bundled with project)
- (Optional) GitHub CLI (`gh`) for repo creation

## Setup

1. Clone or copy this repository locally.

2. Firebase
- Create a Firebase project and add an Android app (package name should match).
- Download `google-services.json` and place it in `app/` (root of the module).
  - NOTE: it's recommended to avoid committing `google-services.json` to the public repo; keep it local and add to `.gitignore`. See "Untrack sensitive files" below.

3. Google Maps API
- Create an API key with Maps SDK for Android enabled.
- Add `MAPS_API_KEY` to your `local.properties` (or use safe injection via build configs/CI).
