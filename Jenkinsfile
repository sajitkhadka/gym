pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'sajit/gym_image'
        SPRING_PROFILES_ACTIVE = 'prod'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], userRemoteConfigs: [[url: 'https://github.com/sajitkhadka/gym.git']]])
            }
        }

        stage('Build and Package Server') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'GYM_DB_USERNAME', variable: 'DB_USERNAME'),
                                     string(credentialsId: 'GYM_DB_URL', variable: 'DB_URL'),
                                     string(credentialsId: 'GYM_DB_PASSWORD', variable: 'DB_PASSWORD')]) {
                        sh 'cd server && chmod +x mvnw && ./mvnw clean install'
                    }
                }
            }
        }

        // stage('Build and Package Next.js App') {
        //     steps {
        //         script {
        //             withNodeJS {
        //                 sh 'cd client && npm install && npm run build'
        //             }
        //         }
        //     }
        // }

        stage('Build server docker') {
            steps {
                script {
                    sh 'docker build -t ${DOCKER_IMAGE}:${BUILD_ID} ./server'
                }
            }
        }

        stage('Deploy Docker Container') {
            steps {
                script {
                    // Stop and remove any previous containers
                    sh "docker-compose down || true"

                    withCredentials([string(credentialsId: 'GYM_DB_USERNAME', variable: 'DB_USERNAME'),
                                     string(credentialsId: 'GYM_DB_URL', variable: 'DB_URL'),
                                     string(credentialsId: 'GYM_DB_PASSWORD', variable: 'DB_PASSWORD')]) {
                        // Set environment variables for Docker Compose
                        env.DB_USERNAME = DB_USERNAME
                        env.DB_PASSWORD = DB_PASSWORD
                        env.DB_URL = DB_URL
                        env.BUILD_ID = BUILD_ID

                        // Start the application using Docker Compose
                        sh 'docker-compose up -d'
                    }
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline succeeded! Deploy your Docker container.'
        }

        failure {
            echo 'Pipeline failed!'
        }
    }
}
