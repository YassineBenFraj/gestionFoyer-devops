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
                sh './mvnw clean compile -DskipTests'
            }
        }

        stage('SonarQube Analysis') {
            environment {
                SONAR_TOKEN = credentials('sonar-test')
            }
            steps {
                script {
                    echo "Using SonarQube token: ${SONAR_TOKEN}"
                    sh """
                        ./mvnw sonar:sonar \
                        -Dsonar.projectKey=test-devops \
                        -Dsonar.host.url=http://192.168.1.109:9000 \
                        -Dsonar.login=${SONAR_TOKEN} \
                        -Dsonar.ws.timeout=600 \
                        -Dsonar.scm.disabled=true \
                        -Dsonar.java.binaries=target/classes \
                        -Dsonar.sourceEncoding=UTF-8
                    """
                }
            }
        }
        
        stage('Package Application') {
            steps {
                sh './mvnw package -DskipTests'
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
            echo "✅ Pipeline terminé avec succès ! SonarQube analysis completed."
            sh '''
                echo "SonarQube report available at: http://192.168.1.109:9000/dashboard?id=test-devops"
            '''
        }
    }
}
