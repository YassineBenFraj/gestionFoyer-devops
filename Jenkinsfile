pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "yassinebenfraj/test-devops:latest"
        GIT_REPO = "https://github.com/YassineBenFraj/gestionFoyer-devops.git"
        DOCKER_REGISTRY = "https://index.docker.io/v1/"
    }

    triggers {
        pollSCM('* * * * *')
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main', url: "${env.GIT_REPO}"
            }
        }

        stage('Clean & Build') {
            steps {
                sh 'chmod +x mvnw'
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    sh """
                    docker build --pull --no-cache -t ${env.DOCKER_IMAGE} .
                    """
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                withDockerRegistry([credentialsId: 'DOCKER_CREDENTIALS_ID', url: "${env.DOCKER_REGISTRY}"]) {
                    sh "docker push ${env.DOCKER_IMAGE}"
                }
            }
        }
    }

    post {
        success {
            echo "Pipeline terminé avec succès ! L'image Docker est prête et pushée."
        }
        failure {
            echo "Pipeline échoué ! Vérifie les logs et les credentials Docker."
        }
    }
}
