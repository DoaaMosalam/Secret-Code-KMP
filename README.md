![image](https://github.com/user-attachments/assets/4f6f58cf-a9ce-4543-99c4-dd80dd75490f)<head> 
    <body>
 <h1># 🎓 Student Grouping & Secret Code Generator </h1>
   <p align="center">
   <img src= "https://github.com/user-attachments/assets/e7afa29b-2159-49f0-90be-e1316cf3cd77" width="400" height="500" alt="Secret Code"/>
  </p>
  <h2> Icon Program   </h2>
   <img src= "https://github.com/user-attachments/assets/318a617b-c77f-4da6-8b1e-bfb9a60f0f56 " width="400" height="500" alt="Icon Secret Code"/>



  # Overview
  A simple and elegant Kotlin Desktop application that helps you:
- ✅ Read student data from a CSV file
- 📊 Automatically or manually group students
- 🔐 Generate unique secret codes
- 💾 Export the result to a new CSV file

---

## 🖥️ Technologies Used

- 💡 Kotlin (JVM)
- 🧩 Jetpack Compose for Desktop
- 📝 CSV File Handling
- 📂 File Picker Dialogs
- 🔠 Multi-language UI (English / Arabic)

---

## 📸 Screenshots

| Main Interface (English) | Main Interface (Arabic) | Output
|--------------------------|--------------------------| --------------------------|
|![English](https://github.com/user-attachments/assets/fa14eb39-7a4f-4222-b5eb-e58b710a7755) | ![Arabic](https://github.com/user-attachments/assets/beeefe54-1cd2-4664-8dbb-f830fa2d2896) |![output](https://github.com/user-attachments/assets/6f14b0d3-c5f9-4a03-a0cd-cf041047be22)

---

## ⚙️ Features

- 🌍 Language switch (English/Arabic)
- 📁 Choose student input file (.csv)
- 💾 Select where to save the output
- 🔢 Customizable secret code start
- 👥 Grouping modes:
  - Random code
  - Manual grouping by seat range
  - Auto grouping by group size
- ✅ Confirmation dialog with folder open button

---

## 📂 File Format

### Input CSV Format:

```csv
الاسم,رقم الجلوس,اللغة الثانية,الديانة
الاسم,رقم الجلوس,اللغة الثانية,الديانة,المجموعة,الرقم السري
ابانوب جرجس ملاك سامى,1,فرنسي,مسيـحى,8,771
احمد علي سليم علي,2,الماني,مسـلم,8,772
```
## 🛠️ Installation
✅ Steps to install on Windows:
1.Download the .exe installer from the folder:
```
C:\Users\<YourName>\AppData\Local\Programs\Secret Code\
```
2.Run the file Secret Code-1.0.0.exe (or the name of the version you built).

3.Follow the installation steps (Next → Install).

4.By default, the program is installed in:
```
C:\Users\<YourName>\AppData\Local\Programs\Secret Code\
```
You can search for "Secret Code" in the Start Menu and launch it directly.

## ⚙️ If building from source:
Run the following command:
```
./gradlew packageReleaseDistributionForCurrentOS
```
This will generate the .exe installer inside:
```
composeApp/build/compose/binaries/main/exe/
```
## 🚀 Usage
1.Open the app.

2.Select the student CSV file.

3.Choose the output location for the generated file.

4.Enter the secret code start number and choose a grouping mode.

5.Click "✅ Execute".
A confirmation dialog will appear, and you can open the result folder directly.

## 💡 System Requirements
- Java 17 or newer

- Currently supports Windows OS only

- Built using Kotlin Multiplatform + Compose Desktop
  
## video from app

https://github.com/user-attachments/assets/1f73d63e-a890-4684-8fbc-1b5221e9759c

