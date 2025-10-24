# 🌤️ Spring Weather App

[![Live Demo](https://img.shields.io/badge/demo-online-brightgreen)](https://spring-weather-app.onrender.com)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.6-brightgreen)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-21-blue)](https://openjdk.org/projects/jdk/21/)
[![Docker](https://img.shields.io/badge/Docker-ready-blue)](https://www.docker.com/)
[![License](https://img.shields.io/badge/license-MIT-lightgrey)](./LICENSE)

---

## ✨ Overview

**Spring Weather App** is a sleek and modern web application built with **Spring Boot** and **Thymeleaf**, showcasing clean design and real-time weather data integration.  
It allows users to instantly view the current temperature and hourly forecast for any city in the world, all within an elegant glass-morphism UI.

This project demonstrates the ability to combine **Java backend logic**, **REST API integration**, and **intuitive frontend design** — making it a perfect full-stack showcase for modern portfolio presentation.

---

## 🚀 Live Demo

👉 [**Click here to open the app**](https://spring-weather-app.onrender.com/?city=Krakow)

> ⚠️ The app may take ~30 seconds to start after inactivity — free hosting goes into sleep mode.

---

## 💡 Features

- 🌍 **City Search** – instantly fetch weather data for any location
- ☀️ **Real-Time Weather** – current temperature & conditions via [Open-Meteo API](https://open-meteo.com/)
- ⏱️ **Hourly Forecast Modal** – interactive scrollable table with icons and temperatures
- 💎 **Elegant UI** – glass-morphism style with dynamic gradients and smooth layout
- ⚡ **Error Handling** – graceful fallback to default city (Kraków) if input is invalid
- 🐳 **Containerized Deployment** – fully runnable via Docker or Render (Free Tier)

---

## 🧱 Tech Stack

| Layer | Technology |
|:------|:------------|
| **Backend** | Java 21 · Spring Boot 3.5.6 |
| **Frontend** | Thymeleaf · HTML · CSS · Vanilla JS |
| **API** | Open-Meteo (Geocoding + Forecast) |
| **Build Tool** | Maven |
| **Deployment** | Docker · Render.com |
| **JSON Parsing** | Gson |

---

## ⚙️ Project Structure

```text
src/
 ├─ main/
 │   ├─ java/com/project/spring/weatherapp/
 │   │   ├─ WeatherAppApplication.java       ← entry point
 │   │   ├─ controllers/WeatherController.java
 │   │   ├─ services/WeatherService.java
 │   │   ├─ translate/
 │   │   ├─ coordinationDetales/
 │   │   └─ weatherDetales/
 │   └─ resources/
 │       ├─ templates/weatherApp.html
 │       └─ static/
 │           ├─ css/
 │           ├─ js/
 │           └─ icons/
 ├─ Dockerfile
 ├─ pom.xml
 └─ render.yaml
```

---

## 🧩 How to Run Locally

### 🔸 Using Docker (recommended)
```bash
docker build -t weatherapp:latest .
docker run --rm -p 8080:8080 weatherapp:latest
```

Then open:
```
http://localhost:8080/?city=Krakow
```

### 🔸 Using Maven (if installed)
```bash
mvn spring-boot:run
```

---

## 🌤️ API Integration

The app integrates the [Open-Meteo API](https://open-meteo.com/) to fetch:
- **Geocoding data** (latitude & longitude for a given city)
- **Current weather** (`temperature_2m`, `weather_code`)
- **Hourly forecast** (temperature and weather code visualization)

No API keys are required — it’s free and open.

---

## 🧠 Key Learning Points

- Clean separation between **controller**, **service**, and **model** layers
- Handling REST requests using **Java HttpClient**
- Working with JSON using **Gson**
- Implementing **graceful fallbacks** for user errors
- Building responsive UIs with **Thymeleaf** and pure CSS
- Packaging and deploying a **Spring Boot Docker container**

---

## 🐳 Docker Support

This project includes a multi-stage Dockerfile for minimal runtime size:

```dockerfile
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -q -DskipTests -Dstart-class=com.project.spring.weatherapp.WeatherAppApplication clean package
FROM eclipse-temurin:21-jre
WORKDIR /app
ENV JAVA_OPTS="-Xms128m -Xmx256m"
EXPOSE 8080
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
```

---

## 📦 Deployment

This app is fully deployable to [Render](https://render.com) using the free tier.  
Every commit to `main` triggers an auto-deploy.

🟢 **Live Demo:**  
[https://spring-weather-app.onrender.com](https://spring-weather-app.onrender.com)

---

## 🧾 License

This project is licensed under the [MIT License](./LICENSE).

---

> *“Simple idea, elegant execution — a small but complete full-stack web app demonstrating modern Spring Boot development.”*
