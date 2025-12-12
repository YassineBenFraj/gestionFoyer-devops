🚀 Project: Spring Boot + MySQL + Kubernetes + Jenkins CI/CD
📌 Description

Ce projet met en place une architecture DevOps complète, permettant l’automatisation du build, du déploiement et de la mise à jour continue d’une application Spring Boot dans Kubernetes.

Il inclut :

🟦 Application Spring Boot (Java 17)

🟨 MySQL avec stockage persistant (PV/PVC)

☸️ Kubernetes (Minikube)

🐳 Docker + Docker Hub

🔁 Pipeline CI/CD Jenkins

🔄 Rolling Update Kubernetes

🏗️ Architecture générale
GitHub → Jenkins → Build Maven → Docker Build → Push Docker Hub → Kubernetes Deployment


✔ Déploiement automatique
✔ Mise à jour continue
✔ Zéro interruption grâce au Rolling Update

🐳 Docker
🔧 Build local
mvn clean package -DskipTests
docker build -t yassinebenfraj/test-devops .

▶️ Run local
docker run -p 8081:8081 yassinebenfraj/test-devops

☸ Kubernetes
📁 Namespace
kubectl create namespace devops

🛢️ Déploiement MySQL
kubectl apply -f mysql-deployment.yaml -n devops

☕ Déploiement Spring Boot
kubectl apply -f spring-configmap.yaml -n devops
kubectl apply -f spring-secret.yaml -n devops
kubectl apply -f spring-deployment.yaml -n devops
kubectl apply -f spring-service.yaml -n devops

🌐 Accès à l’application
minikube service spring-service -n devops --url

Sortie typique :

http://192.168.49.2:30080

🔁 Pipeline CI/CD – Jenkins
🧱 Stages du pipeline

1. Checkout GitHub

2. Build Maven

3. Build Docker image

4. Push Docker Hub

5. Deploy to Kubernetes (Rolling Update)

✔ Résultat final

✔ Application Spring Boot déployée sur Kubernetes

✔ MySQL avec PV/PVC et persistance des données

✔ Pipeline Jenkins entièrement automatisé

✔ Image Docker mise à jour automatiquement

✔ Rolling Update fonctionnel (pas d’interruption)

✔ Service exposé via NodePort pour accès externe

📦 Technologies utilisées    Technologie	Rôle
        Spring Boot	         Application backend
            MySQL	             Base de données
           Docker	            Conteneurisation
         Kubernetes	            Orchestration
          Jenkins	                  CI/CD
         Docker Hub	          Registry d’images
          Minikube	        Cluster Kubernetes local

📝 Auteur
👤 Yassine Ben Fraj
DevOps – Kubernetes – Spring Boot
