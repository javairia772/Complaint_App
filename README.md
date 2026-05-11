# 📋 Complaint Registration App

A Kotlin Android application that allows university students to submit, view, and track complaints in real-time using Firebase Firestore database.

---

## 📱 App Screens

| Screen | Description |
|---|---|
| Splash Screen | Branded launch screen shown for 2 seconds |
| Main Screen | RecyclerView list of all submitted complaints |
| Register Screen | Form to submit a new complaint |
| Detail Screen | Full information of a selected complaint |

---

## ✨ Features

- 🔥 **Firebase Firestore** — Real-time cloud database integration
- 📝 **Complaint Form** — Full input validation before submission
- 📂 **8 Categories** — IT, Library, Transport, Hostel, Accounts, Examination, Cafeteria, Administration
- ⚡ **4 Priority Levels** — Low, Medium, High, Urgent with color-coded badges
- 📋 **RecyclerView List** — Displays all complaints with card layout
- 🕐 **Latest First** — Newest complaints always appear at the top
- 📄 **Detail Screen** — Shows complete complaint info including date and status
- 💬 **Empty State** — Friendly message shown when no complaints exist
- ✅ **Default Status** — Every new complaint is automatically set to "Pending"
- 🔙 **Back Navigation** — Back button on all inner screens

---

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| Kotlin | Primary programming language |
| Android Studio | Development IDE |
| Firebase Firestore | Real-time NoSQL cloud database |
| RecyclerView | Scrollable complaint list |
| CardView | Material card UI for each complaint |
| Material Design | UI components and styling |
| AppCompat | Backward-compatible Android UI |

---

## 📂 Project Structure

```
ComplaintApp/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/example/complaintapp/
│   │       │   ├── Complaint.kt                   # Data model class
│   │       │   ├── ComplaintAdapter.kt             # RecyclerView adapter
│   │       │   ├── SplashActivity.kt               # Launch/splash screen
│   │       │   ├── MainActivity.kt                 # Complaint list screen
│   │       │   ├── RegisterComplaintActivity.kt    # Submit complaint form
│   │       │   └── DetailActivity.kt               # Full complaint details
│   │       ├── res/
│   │       │   ├── layout/
│   │       │   │   ├── activity_splash.xml         # Splash screen layout
│   │       │   │   ├── activity_main.xml           # Main list layout
│   │       │   │   ├── activity_register.xml       # Form layout
│   │       │   │   ├── activity_detail.xml         # Detail layout
│   │       │   │   └── item_complaint.xml          # RecyclerView card layout
│   │       │   └── drawable/
│   │       │       └── badge_bg.xml                # Priority badge background
│   │       └── AndroidManifest.xml
│   ├── google-services.json                        # Firebase config
│   └── build.gradle.kts
├── build.gradle.kts
└── README.md
```

---

## 🚀 Getting Started

### Prerequisites

- Android Studio Hedgehog (2023.1.1) or later
- Android device or emulator running API 24 (Android 7.0) or higher
- Firebase account (free Spark plan is enough)
- Internet connection

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/YOUR_USERNAME/ComplaintApp.git
   cd ComplaintApp
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Click **File → Open**
   - Select the `ComplaintApp` folder
   - Wait for Gradle sync to complete

3. **Connect Firebase**
   - Go to [Firebase Console](https://console.firebase.google.com)
   - Click **Add project** → name it `Complaint App` → Continue
   - Click the **Android icon** to add an Android app
   - Enter package name: `com.example.complaintapp`
   - Click **Register app**
   - Download `google-services.json`
   - Place it inside the `app/` folder of your project

4. **Enable Firestore Database**
   - In Firebase Console → go to **Firestore Database**
   - Click **Create database**
   - Select **Start in test mode**
   - Choose a server location → Click **Enable**

5. **Set Firestore Rules**
   - Go to Firestore → **Rules** tab
   - Replace existing rules with:
   ```
   rules_version = '2';
   service cloud.firestore {
     match /databases/{database}/documents {
       match /{document=**} {
         allow read, write: if true;
       }
     }
   }
   ```
   - Click **Publish**

6. **Run the app**
   - Connect your Android device via USB (enable USB Debugging)
   - Or start an emulator
   - Press **Shift + F10** or click the ▶ Run button

---

## 🗄️ Firestore Database Structure

```
complaints/                        (collection)
  {auto-generated-id}/             (document)
    ├── studentName    : String     e.g. "Ali Ahmed"
    ├── rollNumber     : String     e.g. "BSCS-101"
    ├── title          : String     e.g. "Wi-Fi Issue"
    ├── category       : String     e.g. "IT"
    ├── priority       : String     e.g. "High"
    ├── description    : String     e.g. "Internet not working in Lab 2"
    ├── status         : String     default: "Pending"
    └── createdAt      : Timestamp  auto-generated on submission
```

---

## 🎨 Priority Color Codes

| Priority | Color | Hex Code | Meaning |
|---|---|---|---|
| 🟢 Low | Green | `#4CAF50` | Not urgent |
| 🟡 Medium | Orange | `#FF9800` | Normal attention needed |
| 🔴 High | Red | `#F44336` | Handle quickly |
| 🔴 Urgent | Dark Red | `#B71C1C` | Immediate action required |

---

## 📝 Complaint Categories

| Category | Covers |
|---|---|
| IT | Internet, computer lab, software, system issues |
| Library | Books, library card, seating, library services |
| Transport | Bus, route, timing, driver issues |
| Hostel | Room, cleanliness, water, maintenance |
| Accounts | Fee, challan, payment, account issues |
| Examination | Roll number slip, marks, result issues |
| Cafeteria | Food quality, pricing, service issues |
| Administration | General administrative complaints |

---

## 📋 Form Validation

The registration form validates all fields before submission:

| Field | Validation |
|---|---|
| Student Name | Cannot be empty |
| Roll Number | Cannot be empty |
| Complaint Title | Cannot be empty |
| Category | Selected from dropdown (default: IT) |
| Priority | Selected from dropdown (default: Low) |
| Description | Cannot be empty |

---

## 🔧 Dependencies

```kotlin
// Firebase
implementation(platform("com.google.firebase:firebase-bom:33.1.0"))
implementation("com.google.firebase:firebase-firestore")
implementation("com.google.firebase:firebase-analytics")

// UI Components
implementation("androidx.recyclerview:recyclerview:1.3.2")
implementation("androidx.cardview:cardview:1.0.0")
implementation("androidx.appcompat:appcompat:1.7.0")
implementation("com.google.android.material:material:1.12.0")
```

---

## ⚙️ Build Configuration

| Property | Value |
|---|---|
| Min SDK | 24 (Android 7.0) |
| Target SDK | 36 |
| Compile SDK | 36 |
| Language | Kotlin |
| Build System | Gradle (KTS) |

---

## 🧪 How to Test

1. Launch the app — splash screen appears for 2 seconds
2. Main screen opens — shows empty state message
3. Tap the **+** FAB button — opens complaint form
4. Fill in all fields and tap **Submit Complaint**
5. Success toast appears and form clears
6. Go back — complaint card appears in the list
7. Tap the card — detail screen opens with full info
8. Tap back arrow — returns to list

---

## 🐛 Common Issues

| Issue | Solution |
|---|---|
| App crashes on launch | Check `google-services.json` is inside `app/` folder |
| Firebase not initialized | Make sure `google-services` plugin is in `build.gradle.kts` |
| Permission denied error | Update Firestore rules to allow read/write |
| Complaints not loading | Check internet connection and Firestore rules |
| Build failed duplicate plugin | Remove duplicate `id("com.android.application")` from gradle |

---

## 👨‍💻 Author

Developed as a university Mobile Application Development course project.

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

```
MIT License

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software to use, copy, modify, merge, publish, distribute, and/or sell
copies of the software, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the software.
```

---

## ⭐ Show Your Support

If this project helped you, please give it a ⭐ on GitHub!
