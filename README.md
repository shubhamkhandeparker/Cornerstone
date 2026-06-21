# PROGRESS.md — June 20, 2026

## App: Cornerstone — current state
Empty app that runs on phone and shows a placeholder screen. Has a working local database (Room) that CAN store a fighter's profile, but nothing reads or writes it yet — no UI hooked up.

## ✅ Done so far
- Android Studio project created (Kotlin + Compose), package com.shubham.cornerstone
- Git initialised, pushed to PRIVATE GitHub repo (shubhamkhandeparker/Cornerstone)
- Added libraries: Room, ViewModel-Compose, Navigation-Compose, DataStore, KSP plugin
- Fixed dependency clashes (core-ktx pinned to 1.13.1)
- Built data layer: UserProfile (entity), UserProfileDao, AppDatabase
- BUILD SUCCESSFUL — Room generates code cleanly

## 🔧 Current step (where we stopped)
- Database layer complete and verified. No ViewModel or screens yet.

## ➡️ Next session — start here
- Create UserProfileRepository (clean wrapper around the DAO)
- Then create OnboardingViewModel
- Then build the first onboarding screen (asks: sport + level, saves to DB)

## 📁 Project structure so far
app/src/main/java/com/shubham/cornerstone/
├── MainActivity.kt
├── UserProfile.kt        (Room entity)
├── UserProfileDao.kt     (read/write commands)
├── AppDatabase.kt        (the database)
└── ui/theme/             (default Compose theme)

## ⚠️ Open issues / decisions pending
- Onboarding must stay TINY (sport + level only at first). Resist adding more questions.
- Weight-cut has no real test data yet (no fight booked) — revisit in Phase 3.

## 💰 Path-to-money checklist
- [ ] Free hook: shadowboxing combos + drills (Phase 2)
- [ ] Paid wedge: weight-cut tracking (Phase 3)
- [ ] Google Play Billing + subscription (Phase 4)
- [ ] Store listing, privacy policy, screenshots (Phase 5)
- ~8% of the way to Play Store
