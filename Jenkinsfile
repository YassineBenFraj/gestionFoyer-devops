pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "yassinebenfraj/test-devops"
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

        stage('Setup Test Environment') {
            steps {
                sh '''
                    # S'assurer que H2 est utilisé pour les tests
                    mkdir -p src/test/resources

                    cat > src/test/resources/application.properties << 'EOF'
                    spring.datasource.url=jdbc:h2:mem:testdb
                    spring.datasource.driver-class-name=org.h2.Driver
                    spring.datasource.username=sa
                    spring.datasource.password=
                    spring.jpa.hibernate.ddl-auto=create-drop
                    EOF
                '''
            }
        }

        stage('Build Project') {
            steps {
                sh 'chmod +x mvnw'
                sh './mvnw clean verify'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarQube') {
                    sh """
                        ./mvnw sonar:sonar \
                        -Dsonar.projectKey=test-devops
                    """
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    sh """
                        docker build -t ${DOCKER_IMAGE}:latest .
                    """
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry(DOCKER_REGISTRY, 'DOCKER_CREDENTIALS_ID') {
                        sh "docker push ${DOCKER_IMAGE}:latest"
                    }
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                script {
                    sh """
                        kubectl set image deployment/spring-app spring-app=${DOCKER_IMAGE}:latest -n devops
                        kubectl rollout restart deployment/spring-app -n devops
                        kubectl rollout status deployment/spring-app -n devops
                    """
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
