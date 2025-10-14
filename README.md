# Gasoline Cost  Calculator / CLASS PROJECT
This Android app helps users estimate the cost of gasoline for a round trip based on various driving factors. Built using **Android Studio**
with **Java**, the app provides a simple and intuitive interface for calculating a rough estimate of fuel expenses.

---

## Features 
- Input your car's **highway MPG**, trip **distance**, and **fuel cost**.
- Account for driving conditions:
  - **A/C usage**
  - **Average Speed**
  - **Driver aggressiveness** (Highway, city, or mixed driving)
- Calculates a **fuel economy modifier** to adjust the MPG based on user inputs.
- Computes the **final round-trip fuel cost** and displays it to the user.

---

## Fuel Cost Calculation Logic

1. **Calculate Fuel Economy Modifier**
   - **A/C Usage**: +15 points if on
   - **Average Speed**: +5 points for every mph over 50
   - **Driver Aggressiveness**: modifier varies depending on driving style and environment.
2. **Adjust MPG**
   ```java
   Final MPG = Initial MPG * ((100 - modifier) / 100)

---

## ScreenShot

<p align="center">
  <img src="https://github.com/user-attachments/assets/cb1b3852-cbd8-404b-8ecb-b2dffda31f67" width="200">
</p>

