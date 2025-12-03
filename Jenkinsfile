pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "yassinebenfraj/test-devops:latest"
        DOCKER_REGISTRY = "https://index.docker.io/v1/"
    }

    triggers {
        pollSCM('* * * * *')
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
                sh 'git clean -fdx'
            }
        }

        stage('Build Project') {
            steps {
                sh 'chmod +x mvnw'
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${DOCKER_IMAGE}", "--pull --no-cache .")
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry(DOCKER_REGISTRY, 'DOCKER_CREDENTIALS_ID') {
                        docker.image("${DOCKER_IMAGE}").push('latest')
                    }
                }
            }
        }
    }

    post {
        success {
            echo "Pipeline terminé avec succès ! Image Docker pushée."
        }
        failure {
            echo "Pipeline échoué ! Vérifie les logs et les credentials Docker."
        }
    }
}
